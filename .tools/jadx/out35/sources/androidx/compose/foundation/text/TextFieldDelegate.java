package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;

/* compiled from: TextFieldDelegate.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/text/TextFieldDelegate;", "", "()V", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldDelegate {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: TextFieldDelegate.kt */
    @Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJR\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ:\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 JH\u0010!\u001a\u0014\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00150\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0015H\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010,JE\u0010-\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u0010\u0012\u001a\u00020\u0013H\u0001¢\u0006\u0002\b4J1\u00105\u001a\u00020\u000b2\u0006\u00100\u001a\u0002012\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b09H\u0001¢\u0006\u0002\b:JA\u0010;\u001a\u00020\u000b2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=2\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b092\b\u0010?\u001a\u0004\u0018\u000101H\u0001¢\u0006\u0002\b@JU\u0010A\u001a\u0002012\u0006\u0010B\u001a\u00020C2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00106\u001a\u0002072\u0006\u0010D\u001a\u00020E2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b092\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u000b09H\u0001¢\u0006\u0002\bHJU\u0010I\u001a\u0002012\u0006\u0010B\u001a\u00020C2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00106\u001a\u0002072\u0006\u0010D\u001a\u00020E2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b092\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u000b09H\u0001¢\u0006\u0002\bJJF\u0010K\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020M2\u0006\u0010\u0014\u001a\u00020N2\u0006\u00106\u001a\u0002072\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b09H\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010PJ-\u0010Q\u001a\u00020\u000b2\u0006\u00100\u001a\u0002012\u0006\u0010R\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020NH\u0001¢\u0006\u0002\bS\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006T"}, d2 = {"Landroidx/compose/foundation/text/TextFieldDelegate$Companion;", "", "()V", "applyCompositionDecoration", "Landroidx/compose/ui/text/input/TransformedText;", "compositionRange", "Landroidx/compose/ui/text/TextRange;", "transformed", "applyCompositionDecoration-72CqOWE", "(JLandroidx/compose/ui/text/input/TransformedText;)Landroidx/compose/ui/text/input/TransformedText;", "draw", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "selectionPreviewHighlightRange", "deletionPreviewHighlightRange", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "highlightPaint", "Landroidx/compose/ui/graphics/Paint;", "selectionBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "draw-Q1vqE60$foundation_release", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/text/input/TextFieldValue;JJLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Paint;J)V", "drawHighlight", "range", "paint", "drawHighlight-Le-punE", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Paint;)V", "layout", "Lkotlin/Triple;", "", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "prevResultText", "layout-_EkL_-Y$foundation_release", "(Landroidx/compose/foundation/text/TextDelegate;JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/TextLayoutResult;)Lkotlin/Triple;", "notifyFocusedRect", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "textInputSession", "Landroidx/compose/ui/text/input/TextInputSession;", "hasFocus", "", "notifyFocusedRect$foundation_release", "onBlur", "editProcessor", "Landroidx/compose/ui/text/input/EditProcessor;", "onValueChange", "Lkotlin/Function1;", "onBlur$foundation_release", "onEditCommand", "ops", "", "Landroidx/compose/ui/text/input/EditCommand;", "session", "onEditCommand$foundation_release", "onFocus", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "onImeActionPerformed", "Landroidx/compose/ui/text/input/ImeAction;", "onFocus$foundation_release", "restartInput", "restartInput$foundation_release", "setCursorOffset", "position", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "setCursorOffset-ULxng0E$foundation_release", "(JLandroidx/compose/foundation/text/TextLayoutResultProxy;Landroidx/compose/ui/text/input/EditProcessor;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/jvm/functions/Function1;)V", "updateTextLayoutResult", "textFieldValue", "updateTextLayoutResult$foundation_release", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: layout-_EkL_-Y$foundation_release$default, reason: not valid java name */
        public static /* synthetic */ Triple m1055layout_EkL_Y$foundation_release$default(Companion companion, TextDelegate textDelegate, long j, LayoutDirection layoutDirection, TextLayoutResult textLayoutResult, int i, Object obj) {
            TextLayoutResult textLayoutResult2;
            if ((i & 8) == 0) {
                textLayoutResult2 = textLayoutResult;
            } else {
                textLayoutResult2 = null;
            }
            return companion.m1058layout_EkL_Y$foundation_release(textDelegate, j, layoutDirection, textLayoutResult2);
        }

        @JvmStatic
        /* renamed from: layout-_EkL_-Y$foundation_release, reason: not valid java name */
        public final Triple<Integer, Integer, TextLayoutResult> m1058layout_EkL_Y$foundation_release(TextDelegate textDelegate, long constraints, LayoutDirection layoutDirection, TextLayoutResult prevResultText) {
            TextLayoutResult layoutResult = textDelegate.m1045layoutNN6EwU(constraints, layoutDirection, prevResultText);
            return new Triple<>(Integer.valueOf(IntSize.m6796getWidthimpl(layoutResult.getSize())), Integer.valueOf(IntSize.m6795getHeightimpl(layoutResult.getSize())), layoutResult);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
            	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
            Caused by: java.lang.NullPointerException
            */
        @kotlin.jvm.JvmStatic
        /* renamed from: draw-Q1vqE60$foundation_release, reason: not valid java name */
        public final void m1057drawQ1vqE60$foundation_release(androidx.compose.ui.graphics.Canvas r11, androidx.compose.ui.text.input.TextFieldValue r12, long r13, long r15, androidx.compose.ui.text.input.OffsetMapping r17, androidx.compose.ui.text.TextLayoutResult r18, androidx.compose.ui.graphics.Paint r19, long r20) {
            /*
                r10 = this;
                boolean r0 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r13)
                if (r0 != 0) goto L1d
                r19.mo4042setColor8_81llA(r20)
                r1 = r10
                r2 = r11
                r3 = r13
                r5 = r17
                r6 = r18
                r7 = r19
                r1.m1054drawHighlightLepunE(r2, r3, r5, r6, r7)
                goto La2
            L1d:
                boolean r0 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r15)
                if (r0 != 0) goto L81
                androidx.compose.ui.text.TextLayoutInput r0 = r18.getLayoutInput()
                androidx.compose.ui.text.TextStyle r0 = r0.getStyle()
                long r0 = r0.m6122getColor0d7_KjU()
                androidx.compose.ui.graphics.Color r0 = androidx.compose.ui.graphics.Color.m4153boximpl(r0)
                long r1 = r0.m4173unboximpl()
                r3 = 0
                r4 = r1
                r6 = 0
                r7 = 16
                int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r7 != 0) goto L42
                r7 = 1
                goto L43
            L42:
                r7 = 0
            L43:
                if (r7 != 0) goto L47
                goto L48
            L47:
                r0 = 0
            L48:
                if (r0 == 0) goto L4f
                long r0 = r0.m4173unboximpl()
                goto L55
            L4f:
                androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.INSTANCE
                long r0 = r0.m4189getBlack0d7_KjU()
            L55:
                r2 = r0
                float r0 = androidx.compose.ui.graphics.Color.m4165getAlphaimpl(r2)
                r1 = 1045220557(0x3e4ccccd, float:0.2)
                float r4 = r0 * r1
                r8 = 14
                r9 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                long r0 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r2, r4, r5, r6, r7, r8, r9)
                r7 = r19
                r8 = r2
                r7.mo4042setColor8_81llA(r0)
                r1 = r10
                r2 = r11
                r3 = r15
                r5 = r17
                r6 = r18
                r1.m1054drawHighlightLepunE(r2, r3, r5, r6, r7)
                goto La2
            L81:
                long r0 = r12.getSelection()
                boolean r0 = androidx.compose.ui.text.TextRange.m6086getCollapsedimpl(r0)
                if (r0 != 0) goto La2
                r19.mo4042setColor8_81llA(r20)
                long r3 = r12.getSelection()
                r1 = r10
                r2 = r11
                r5 = r17
                r6 = r18
                r7 = r19
                r1.m1054drawHighlightLepunE(r2, r3, r5, r6, r7)
            La2:
                androidx.compose.ui.text.TextPainter r0 = androidx.compose.ui.text.TextPainter.INSTANCE
                r6 = r18
                r0.paint(r11, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextFieldDelegate.Companion.m1057drawQ1vqE60$foundation_release(androidx.compose.ui.graphics.Canvas, androidx.compose.ui.text.input.TextFieldValue, long, long, androidx.compose.ui.text.input.OffsetMapping, androidx.compose.ui.text.TextLayoutResult, androidx.compose.ui.graphics.Paint, long):void");
        }

        /* renamed from: drawHighlight-Le-punE, reason: not valid java name */
        private final void m1054drawHighlightLepunE(Canvas canvas, long range, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Paint paint) {
            int start = offsetMapping.originalToTransformed(TextRange.m6090getMinimpl(range));
            int end = offsetMapping.originalToTransformed(TextRange.m6089getMaximpl(range));
            if (start != end) {
                Path selectionPath = textLayoutResult.getPathForRange(start, end);
                canvas.drawPath(selectionPath, paint);
            }
        }

        @JvmStatic
        public final void notifyFocusedRect$foundation_release(TextFieldValue value, TextDelegate textDelegate, TextLayoutResult textLayoutResult, LayoutCoordinates layoutCoordinates, TextInputSession textInputSession, boolean hasFocus, OffsetMapping offsetMapping) {
            Rect bbox;
            if (!hasFocus) {
                return;
            }
            int focusOffsetInTransformed = offsetMapping.originalToTransformed(TextRange.m6089getMaximpl(value.getSelection()));
            if (focusOffsetInTransformed < textLayoutResult.getLayoutInput().getText().length()) {
                bbox = textLayoutResult.getBoundingBox(focusOffsetInTransformed);
            } else if (focusOffsetInTransformed != 0) {
                bbox = textLayoutResult.getBoundingBox(focusOffsetInTransformed - 1);
            } else {
                long defaultSize = TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null);
                bbox = new Rect(0.0f, 0.0f, 1.0f, IntSize.m6795getHeightimpl(defaultSize));
            }
            long globalLT = layoutCoordinates.mo5502localToRootMKHz9U(OffsetKt.Offset(bbox.getLeft(), bbox.getTop()));
            textInputSession.notifyFocusedRect(RectKt.m3962Recttz77jQw(OffsetKt.Offset(Offset.m3922getXimpl(globalLT), Offset.m3923getYimpl(globalLT)), SizeKt.Size(bbox.getWidth(), bbox.getHeight())));
        }

        @JvmStatic
        public final void updateTextLayoutResult$foundation_release(TextInputSession textInputSession, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResultProxy textLayoutResult) {
            LayoutCoordinates decorationBoxCoordinates;
            final LayoutCoordinates innerTextFieldCoordinates = textLayoutResult.getInnerTextFieldCoordinates();
            if (innerTextFieldCoordinates != null && innerTextFieldCoordinates.isAttached() && (decorationBoxCoordinates = textLayoutResult.getDecorationBoxCoordinates()) != null) {
                textInputSession.updateTextLayoutResult(textFieldValue, offsetMapping, textLayoutResult.getValue(), new Function1<Matrix, Unit>() { // from class: androidx.compose.foundation.text.TextFieldDelegate$Companion$updateTextLayoutResult$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Matrix matrix) {
                        m1060invoke58bKbWc(matrix.m4414unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-58bKbWc, reason: not valid java name */
                    public final void m1060invoke58bKbWc(float[] matrix) {
                        if (LayoutCoordinates.this.isAttached()) {
                            LayoutCoordinatesKt.findRootCoordinates(LayoutCoordinates.this).mo5506transformFromEL8BTi8(LayoutCoordinates.this, matrix);
                        }
                    }
                }, SelectionManagerKt.visibleBounds(innerTextFieldCoordinates), innerTextFieldCoordinates.localBoundingBoxOf(decorationBoxCoordinates, false));
            }
        }

        @JvmStatic
        public final void onEditCommand$foundation_release(List<? extends EditCommand> ops, EditProcessor editProcessor, Function1<? super TextFieldValue, Unit> onValueChange, TextInputSession session) {
            TextFieldValue newValue = editProcessor.apply(ops);
            if (session != null) {
                session.updateState(null, newValue);
            }
            onValueChange.invoke(newValue);
        }

        @JvmStatic
        /* renamed from: setCursorOffset-ULxng0E$foundation_release, reason: not valid java name */
        public final void m1059setCursorOffsetULxng0E$foundation_release(long position, TextLayoutResultProxy textLayoutResult, EditProcessor editProcessor, OffsetMapping offsetMapping, Function1<? super TextFieldValue, Unit> onValueChange) {
            int offset = offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1080getOffsetForPosition3MmeM6k$default(textLayoutResult, position, false, 2, null));
            onValueChange.invoke(TextFieldValue.m6333copy3r_uNRQ$default(editProcessor.toTextFieldValue(), (AnnotatedString) null, TextRangeKt.TextRange(offset), (TextRange) null, 5, (Object) null));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [T, androidx.compose.ui.text.input.TextInputSession] */
        @JvmStatic
        public final TextInputSession restartInput$foundation_release(TextInputService textInputService, TextFieldValue value, final EditProcessor editProcessor, ImeOptions imeOptions, final Function1<? super TextFieldValue, Unit> onValueChange, Function1<? super ImeAction, Unit> onImeActionPerformed) {
            final Ref.ObjectRef session = new Ref.ObjectRef();
            session.element = textInputService.startInput(value, imeOptions, new Function1<List<? extends EditCommand>, Unit>() { // from class: androidx.compose.foundation.text.TextFieldDelegate$Companion$restartInput$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends EditCommand> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<? extends EditCommand> list) {
                    TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(list, EditProcessor.this, onValueChange, session.element);
                }
            }, onImeActionPerformed);
            return (TextInputSession) session.element;
        }

        @JvmStatic
        public final TextInputSession onFocus$foundation_release(TextInputService textInputService, TextFieldValue value, EditProcessor editProcessor, ImeOptions imeOptions, Function1<? super TextFieldValue, Unit> onValueChange, Function1<? super ImeAction, Unit> onImeActionPerformed) {
            return restartInput$foundation_release(textInputService, value, editProcessor, imeOptions, onValueChange, onImeActionPerformed);
        }

        @JvmStatic
        public final void onBlur$foundation_release(TextInputSession textInputSession, EditProcessor editProcessor, Function1<? super TextFieldValue, Unit> onValueChange) {
            onValueChange.invoke(TextFieldValue.m6333copy3r_uNRQ$default(editProcessor.toTextFieldValue(), (AnnotatedString) null, 0L, (TextRange) null, 3, (Object) null));
            textInputSession.dispose();
        }

        /* renamed from: applyCompositionDecoration-72CqOWE, reason: not valid java name */
        public final TransformedText m1056applyCompositionDecoration72CqOWE(long compositionRange, TransformedText transformed) {
            int startPositionTransformed = transformed.getOffsetMapping().originalToTransformed(TextRange.m6092getStartimpl(compositionRange));
            int endPositionTransformed = transformed.getOffsetMapping().originalToTransformed(TextRange.m6087getEndimpl(compositionRange));
            int start = Math.min(startPositionTransformed, endPositionTransformed);
            int coercedEnd = Math.max(startPositionTransformed, endPositionTransformed);
            AnnotatedString.Builder $this$applyCompositionDecoration_72CqOWE_u24lambda_u243 = new AnnotatedString.Builder(transformed.getText());
            $this$applyCompositionDecoration_72CqOWE_u24lambda_u243.addStyle(new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, TextDecoration.INSTANCE.getUnderline(), (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 61439, (DefaultConstructorMarker) null), start, coercedEnd);
            return new TransformedText($this$applyCompositionDecoration_72CqOWE_u24lambda_u243.toAnnotatedString(), transformed.getOffsetMapping());
        }
    }
}
