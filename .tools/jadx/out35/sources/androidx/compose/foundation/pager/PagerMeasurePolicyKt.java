package androidx.compose.foundation.pager;

import kotlin.Metadata;

/* compiled from: PagerMeasurePolicy.kt */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a¡\u0001\u0010\u0000\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0002\b\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"rememberPagerMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "pageCount", "rememberPagerMeasurePolicy-8u0NR3k", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;IFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerMeasurePolicyKt {
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        if (r37.changed(r10) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r37.changed(r11) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0082, code lost:
    
        if (r37.changed(r4) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009f, code lost:
    
        if (r37.changed(r9) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00bb, code lost:
    
        if (r37.changed(r12) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d7, code lost:
    
        if (r37.changed(r13) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f3, code lost:
    
        if (r37.changed(r14) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x010c, code lost:
    
        if (r37.changed(r5) == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0125, code lost:
    
        if (r37.changed(r15) == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0142, code lost:
    
        if (r37.changed(r6) == false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
    
        if (r37.changed(r8) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0177 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00a2  */
    /* renamed from: rememberPagerMeasurePolicy-8u0NR3k, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.jvm.functions.Function2<androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope, androidx.compose.ui.unit.Constraints, androidx.compose.ui.layout.MeasureResult> m915rememberPagerMeasurePolicy8u0NR3k(final kotlin.jvm.functions.Function0<androidx.compose.foundation.pager.PagerLazyLayoutItemProvider> r24, androidx.compose.foundation.pager.PagerState r25, androidx.compose.foundation.layout.PaddingValues r26, boolean r27, androidx.compose.foundation.gestures.Orientation r28, int r29, float r30, androidx.compose.foundation.pager.PageSize r31, androidx.compose.ui.Alignment.Horizontal r32, androidx.compose.ui.Alignment.Vertical r33, androidx.compose.foundation.gestures.snapping.SnapPosition r34, final kotlinx.coroutines.CoroutineScope r35, kotlin.jvm.functions.Function0<java.lang.Integer> r36, androidx.compose.runtime.Composer r37, int r38, int r39) {
        /*
            Method dump skipped, instructions count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerMeasurePolicyKt.m915rememberPagerMeasurePolicy8u0NR3k(kotlin.jvm.functions.Function0, androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.gestures.Orientation, int, float, androidx.compose.foundation.pager.PageSize, androidx.compose.ui.Alignment$Horizontal, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.snapping.SnapPosition, kotlinx.coroutines.CoroutineScope, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):kotlin.jvm.functions.Function2");
    }
}
