package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: LazyStaggeredGridMeasurePolicy.kt */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u007f\u0010\u0000\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0002\b\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a)\u0010\u001c\u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001f\u001a)\u0010 \u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001f\u001a!\u0010!\u001a\u00020\u0012*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\"\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"rememberStaggeredGridMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "rememberStaggeredGridMeasurePolicy-qKj4JfE", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;FFLkotlinx/coroutines/CoroutineScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "afterPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/unit/LayoutDirection;)F", "beforePadding", "startPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/unit/LayoutDirection;)F", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridMeasurePolicyKt {

    /* compiled from: LazyStaggeredGridMeasurePolicy.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0052, code lost:
    
        if (r29.changed(r9) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006d, code lost:
    
        if (r29.changed(r10) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008a, code lost:
    
        if (r29.changed(r3) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a7, code lost:
    
        if (r29.changed(r24) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c3, code lost:
    
        if (r29.changed(r25) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e0, code lost:
    
        if (r29.changed(r11) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e8, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e9, code lost:
    
        r2 = (r2 | r6) | r29.changed(r28);
        r6 = r29.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00fc, code lost:
    
        if (r2 != false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0104, code lost:
    
        if (r6 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x011f, code lost:
    
        r6 = (kotlin.jvm.functions.Function2) r6;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x012a, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x012c, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x012f, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0132, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0109, code lost:
    
        r6 = r3;
        r7 = r11;
        r6 = new androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1(r19, r6, r7, r8, r9, r10, r24, r26, r28);
        r29.updateRememberedValue(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e6, code lost:
    
        if ((100663296 & r30) == 67108864) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0037, code lost:
    
        if (r29.changed(r8) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0055  */
    /* renamed from: rememberStaggeredGridMeasurePolicy-qKj4JfE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.jvm.functions.Function2<androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope, androidx.compose.ui.unit.Constraints, androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResult> m884rememberStaggeredGridMeasurePolicyqKj4JfE(final androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState r19, kotlin.jvm.functions.Function0<? extends androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemProvider> r20, androidx.compose.foundation.layout.PaddingValues r21, boolean r22, androidx.compose.foundation.gestures.Orientation r23, final float r24, float r25, final kotlinx.coroutines.CoroutineScope r26, androidx.compose.foundation.lazy.staggeredgrid.LazyGridStaggeredGridSlotsProvider r27, final androidx.compose.ui.graphics.GraphicsContext r28, androidx.compose.runtime.Composer r29, int r30) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt.m884rememberStaggeredGridMeasurePolicyqKj4JfE(androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState, kotlin.jvm.functions.Function0, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.gestures.Orientation, float, float, kotlinx.coroutines.CoroutineScope, androidx.compose.foundation.lazy.staggeredgrid.LazyGridStaggeredGridSlotsProvider, androidx.compose.ui.graphics.GraphicsContext, androidx.compose.runtime.Composer, int):kotlin.jvm.functions.Function2");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float startPadding(PaddingValues $this$startPadding, Orientation orientation, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return PaddingKt.calculateStartPadding($this$startPadding, layoutDirection);
            case 2:
                return $this$startPadding.getTop();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float beforePadding(PaddingValues $this$beforePadding, Orientation orientation, boolean reverseLayout, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return reverseLayout ? $this$beforePadding.getBottom() : $this$beforePadding.getTop();
            case 2:
                if (reverseLayout) {
                    return PaddingKt.calculateEndPadding($this$beforePadding, layoutDirection);
                }
                return PaddingKt.calculateStartPadding($this$beforePadding, layoutDirection);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float afterPadding(PaddingValues $this$afterPadding, Orientation orientation, boolean reverseLayout, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return reverseLayout ? $this$afterPadding.getTop() : $this$afterPadding.getBottom();
            case 2:
                if (reverseLayout) {
                    return PaddingKt.calculateStartPadding($this$afterPadding, layoutDirection);
                }
                return PaddingKt.calculateEndPadding($this$afterPadding, layoutDirection);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
