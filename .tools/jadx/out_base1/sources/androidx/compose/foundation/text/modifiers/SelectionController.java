package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextPointerIcon_androidKt;
import androidx.compose.foundation.text.selection.MultiWidgetSelectionDelegate;
import androidx.compose.foundation.text.selection.Selectable;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: SelectionController.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0013H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eR\u0016\u0010\u0006\u001a\u00020\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/text/modifiers/SelectionController;", "Landroidx/compose/runtime/RememberObserver;", "selectableId", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "backgroundSelectionColor", "Landroidx/compose/ui/graphics/Color;", "params", "Landroidx/compose/foundation/text/modifiers/StaticTextSelectionParams;", "(JLandroidx/compose/foundation/text/selection/SelectionRegistrar;JLandroidx/compose/foundation/text/modifiers/StaticTextSelectionParams;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "selectable", "Landroidx/compose/foundation/text/selection/Selectable;", "draw", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "onAbandoned", "onForgotten", "onRemembered", "updateGlobalPosition", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "updateTextLayout", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectionController implements RememberObserver {
    public static final int $stable = 8;
    private final long backgroundSelectionColor;
    private final Modifier modifier;
    private StaticTextSelectionParams params;
    private Selectable selectable;
    private final long selectableId;
    private final SelectionRegistrar selectionRegistrar;

    public /* synthetic */ SelectionController(long j, SelectionRegistrar selectionRegistrar, long j2, StaticTextSelectionParams staticTextSelectionParams, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, selectionRegistrar, j2, staticTextSelectionParams);
    }

    private SelectionController(long selectableId, SelectionRegistrar selectionRegistrar, long backgroundSelectionColor, StaticTextSelectionParams params) {
        Modifier makeSelectionModifier;
        this.selectableId = selectableId;
        this.selectionRegistrar = selectionRegistrar;
        this.backgroundSelectionColor = backgroundSelectionColor;
        this.params = params;
        makeSelectionModifier = SelectionControllerKt.makeSelectionModifier(this.selectionRegistrar, this.selectableId, new Function0<LayoutCoordinates>() { // from class: androidx.compose.foundation.text.modifiers.SelectionController$modifier$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LayoutCoordinates invoke() {
                StaticTextSelectionParams staticTextSelectionParams;
                staticTextSelectionParams = SelectionController.this.params;
                return staticTextSelectionParams.getLayoutCoordinates();
            }
        });
        this.modifier = PointerIconKt.pointerHoverIcon$default(makeSelectionModifier, TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ SelectionController(long r9, androidx.compose.foundation.text.selection.SelectionRegistrar r11, long r12, androidx.compose.foundation.text.modifiers.StaticTextSelectionParams r14, int r15, kotlin.jvm.internal.DefaultConstructorMarker r16) {
        /*
            r8 = this;
            r0 = r15 & 8
            if (r0 == 0) goto Lc
            androidx.compose.foundation.text.modifiers.StaticTextSelectionParams$Companion r14 = androidx.compose.foundation.text.modifiers.StaticTextSelectionParams.INSTANCE
            androidx.compose.foundation.text.modifiers.StaticTextSelectionParams r14 = r14.getEmpty()
            r6 = r14
            goto Ld
        Lc:
            r6 = r14
        Ld:
            r7 = 0
            r0 = r8
            r1 = r9
            r3 = r11
            r4 = r12
            r0.<init>(r1, r3, r4, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.SelectionController.<init>(long, androidx.compose.foundation.text.selection.SelectionRegistrar, long, androidx.compose.foundation.text.modifiers.StaticTextSelectionParams, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        this.selectable = this.selectionRegistrar.subscribe(new MultiWidgetSelectionDelegate(this.selectableId, new Function0<LayoutCoordinates>() { // from class: androidx.compose.foundation.text.modifiers.SelectionController$onRemembered$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LayoutCoordinates invoke() {
                StaticTextSelectionParams staticTextSelectionParams;
                staticTextSelectionParams = SelectionController.this.params;
                return staticTextSelectionParams.getLayoutCoordinates();
            }
        }, new Function0<TextLayoutResult>() { // from class: androidx.compose.foundation.text.modifiers.SelectionController$onRemembered$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextLayoutResult invoke() {
                StaticTextSelectionParams staticTextSelectionParams;
                staticTextSelectionParams = SelectionController.this.params;
                return staticTextSelectionParams.getTextLayoutResult();
            }
        }));
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        Selectable localSelectable = this.selectable;
        if (localSelectable != null) {
            this.selectionRegistrar.unsubscribe(localSelectable);
            this.selectable = null;
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        Selectable localSelectable = this.selectable;
        if (localSelectable != null) {
            this.selectionRegistrar.unsubscribe(localSelectable);
            this.selectable = null;
        }
    }

    public final void updateTextLayout(TextLayoutResult textLayoutResult) {
        TextLayoutResult prevTextLayoutResult = this.params.getTextLayoutResult();
        if (prevTextLayoutResult != null && !Intrinsics.areEqual(prevTextLayoutResult.getLayoutInput().getText(), textLayoutResult.getLayoutInput().getText())) {
            this.selectionRegistrar.notifySelectableChange(this.selectableId);
        }
        this.params = StaticTextSelectionParams.copy$default(this.params, null, textLayoutResult, 1, null);
    }

    public final void updateGlobalPosition(LayoutCoordinates coordinates) {
        this.params = StaticTextSelectionParams.copy$default(this.params, coordinates, null, 2, null);
        this.selectionRegistrar.notifyPositionChange(this.selectableId);
    }

    public final void draw(DrawScope drawScope) {
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        Selection selection = this.selectionRegistrar.getSubselections().get(this.selectableId);
        if (selection == null) {
            return;
        }
        int start = !selection.getHandlesCrossed() ? selection.getStart().getOffset() : selection.getEnd().getOffset();
        int end = !selection.getHandlesCrossed() ? selection.getEnd().getOffset() : selection.getStart().getOffset();
        if (start == end) {
            return;
        }
        Selectable selectable = this.selectable;
        int lastOffset = selectable != null ? selectable.getLastVisibleOffset() : 0;
        int clippedStart = RangesKt.coerceAtMost(start, lastOffset);
        int clippedEnd = RangesKt.coerceAtMost(end, lastOffset);
        Path selectionPath = this.params.getPathForRange(clippedStart, clippedEnd);
        if (selectionPath == null) {
            return;
        }
        if (!this.params.getShouldClip()) {
            DrawScope.m4703drawPathLG529CI$default(drawScope, selectionPath, this.backgroundSelectionColor, 0.0f, null, null, 0, 60, null);
            return;
        }
        float right$iv = Size.m3991getWidthimpl(drawScope.mo4713getSizeNHjbRc());
        float bottom$iv = Size.m3988getHeightimpl(drawScope.mo4713getSizeNHjbRc());
        int clipOp$iv = ClipOp.INSTANCE.m4152getIntersectrtfAjoo();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = drawScope.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2.mo4634getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo4637clipRectN_I0leg(0.0f, 0.0f, right$iv, bottom$iv, clipOp$iv);
            try {
            } catch (Throwable th) {
                th = th;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            }
        } catch (Throwable th2) {
            th = th2;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
        }
        try {
            DrawScope.m4703drawPathLG529CI$default(drawScope, selectionPath, this.backgroundSelectionColor, 0.0f, null, null, 0, 60, null);
            $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().restore();
            $this$withTransform_u24lambda_u246$iv$iv2.mo4635setSizeuvyYCjk(previousSize$iv$iv);
        } catch (Throwable th3) {
            th = th3;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
            throw th;
        }
    }
}
