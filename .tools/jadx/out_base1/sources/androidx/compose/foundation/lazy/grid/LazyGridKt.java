package androidx.compose.foundation.lazy.grid;

import kotlin.Metadata;

/* compiled from: LazyGrid.kt */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a~\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017H\u0001¢\u0006\u0002\u0010\u0018\u001a~\u0010\u0019\u001a\u0019\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001a¢\u0006\u0002\b\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0003¢\u0006\u0002\u0010%¨\u0006&"}, d2 = {"LazyGrid", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "slots", "Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "rememberLazyGridMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridKt {
    /* JADX WARN: Removed duplicated region for block: B:103:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0134  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyGrid(androidx.compose.ui.Modifier r28, final androidx.compose.foundation.lazy.grid.LazyGridState r29, final androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider r30, androidx.compose.foundation.layout.PaddingValues r31, boolean r32, final boolean r33, androidx.compose.foundation.gestures.FlingBehavior r34, final boolean r35, final androidx.compose.foundation.layout.Arrangement.Vertical r36, final androidx.compose.foundation.layout.Arrangement.Horizontal r37, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.grid.LazyGridScope, kotlin.Unit> r38, androidx.compose.runtime.Composer r39, final int r40, final int r41, final int r42) {
        /*
            Method dump skipped, instructions count: 968
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid(androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.grid.LazyGridState, androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, androidx.compose.foundation.gestures.FlingBehavior, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004b, code lost:
    
        if (r30.changed(r12) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0066, code lost:
    
        if (r30.changed(r9) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0083, code lost:
    
        if (r30.changed(r10) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a0, code lost:
    
        if (r30.changed(r3) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00bc, code lost:
    
        if (r30.changed(r14) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d9, code lost:
    
        if (r30.changed(r13) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e2, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e3, code lost:
    
        r2 = (r2 | r4) | r30.changed(r29);
        r6 = r30.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f6, code lost:
    
        if (r2 != false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00fe, code lost:
    
        if (r6 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x011b, code lost:
    
        r6 = (kotlin.jvm.functions.Function2) r6;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0126, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0128, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x012b, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x012e, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0103, code lost:
    
        r8 = r3;
        r6 = (kotlin.jvm.functions.Function2) new androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1(r7, r8, r9, r10, r20, r12, r13, r14, r28, r29);
        r30.updateRememberedValue(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e0, code lost:
    
        if ((r31 & 12582912) == 8388608) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0031, code lost:
    
        if (r30.changed(r7) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final kotlin.jvm.functions.Function2<androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope, androidx.compose.ui.unit.Constraints, androidx.compose.ui.layout.MeasureResult> rememberLazyGridMeasurePolicy(final kotlin.jvm.functions.Function0<? extends androidx.compose.foundation.lazy.grid.LazyGridItemProvider> r20, androidx.compose.foundation.lazy.grid.LazyGridState r21, androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider r22, androidx.compose.foundation.layout.PaddingValues r23, boolean r24, boolean r25, androidx.compose.foundation.layout.Arrangement.Horizontal r26, androidx.compose.foundation.layout.Arrangement.Vertical r27, final kotlinx.coroutines.CoroutineScope r28, final androidx.compose.ui.graphics.GraphicsContext r29, androidx.compose.runtime.Composer r30, int r31) {
        /*
            Method dump skipped, instructions count: 303
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridKt.rememberLazyGridMeasurePolicy(kotlin.jvm.functions.Function0, androidx.compose.foundation.lazy.grid.LazyGridState, androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, kotlinx.coroutines.CoroutineScope, androidx.compose.ui.graphics.GraphicsContext, androidx.compose.runtime.Composer, int):kotlin.jvm.functions.Function2");
    }
}
