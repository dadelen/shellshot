package androidx.compose.foundation.layout;

import androidx.autofill.HintConstants;
import kotlin.Metadata;

/* compiled from: ContextualFlowLayout.kt */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0084\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r21\u0010\u000e\u001a-\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a\u0084\u0001\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u001921\u0010\u000e\u001a-\u0012\u0004\u0012\u00020\u001a\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u001b\u001a¥\u0001\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010#\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00010%¢\u0006\u0002\b\u00140$2;\u0010&\u001a7\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110'¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0002\u0010)\u001a¥\u0001\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010#\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00010%¢\u0006\u0002\b\u00140$2;\u0010&\u001a7\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110'¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0002\u0010+¨\u0006,"}, d2 = {"ContextualFlowColumn", "", "itemCount", "", "modifier", "Landroidx/compose/ui/Modifier;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "maxItemsInEachColumn", "maxLines", "overflow", "Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "content", "Lkotlin/Function2;", "Landroidx/compose/foundation/layout/ContextualFlowColumnScope;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/ContextualFlowColumnOverflow;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "ContextualFlowRow", "maxItemsInEachRow", "Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "Landroidx/compose/foundation/layout/ContextualFlowRowScope;", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/ContextualFlowRowOverflow;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "contextualColumnMeasureHelper", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "maxItemsInMainAxis", "overflowState", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "overflowComposables", "", "Lkotlin/Function0;", "getComposable", "Landroidx/compose/foundation/layout/FlowLineInfo;", "info", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;ILjava/util/List;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "contextualRowMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;ILjava/util/List;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextualFlowLayoutKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x023d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContextualFlowRow(final int r21, androidx.compose.ui.Modifier r22, androidx.compose.foundation.layout.Arrangement.Horizontal r23, androidx.compose.foundation.layout.Arrangement.Vertical r24, int r25, int r26, androidx.compose.foundation.layout.ContextualFlowRowOverflow r27, final kotlin.jvm.functions.Function4<? super androidx.compose.foundation.layout.ContextualFlowRowScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instructions count: 606
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowRow(int, androidx.compose.ui.Modifier, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, int, int, androidx.compose.foundation.layout.ContextualFlowRowOverflow, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x023d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContextualFlowColumn(final int r21, androidx.compose.ui.Modifier r22, androidx.compose.foundation.layout.Arrangement.Vertical r23, androidx.compose.foundation.layout.Arrangement.Horizontal r24, int r25, int r26, androidx.compose.foundation.layout.ContextualFlowColumnOverflow r27, final kotlin.jvm.functions.Function4<? super androidx.compose.foundation.layout.ContextualFlowColumnScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instructions count: 606
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.ContextualFlowLayoutKt.ContextualFlowColumn(int, androidx.compose.ui.Modifier, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, int, int, androidx.compose.foundation.layout.ContextualFlowColumnOverflow, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        if (r30.changed(r9) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r30.changed(r15) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
    
        if (r30.changed(r14) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a4, code lost:
    
        if (r30.changed(r13) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c1, code lost:
    
        if (r30.changed(r29) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c9, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ca, code lost:
    
        r2 = r2 | r4;
        r7 = r30.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d4, code lost:
    
        if (r2 != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00dc, code lost:
    
        if (r7 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0118, code lost:
    
        r7 = (kotlin.jvm.functions.Function2) r7;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0123, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0125, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0128, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x012b, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e1, code lost:
    
        r7 = new androidx.compose.foundation.layout.FlowMeasureLazyPolicy(true, r8, r9, r8.getSpacing(), androidx.compose.foundation.layout.FlowLayoutKt.getCROSS_AXIS_ALIGNMENT_TOP(), r9.getSpacing(), r13, r14, r15, r26, r28, r29, null).getMeasurePolicy();
        r30.updateRememberedValue(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c7, code lost:
    
        if ((12582912 & r31) != 8388608) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
    
        if (r30.changed(r8) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.jvm.functions.Function2<androidx.compose.ui.layout.SubcomposeMeasureScope, androidx.compose.ui.unit.Constraints, androidx.compose.ui.layout.MeasureResult> contextualRowMeasurementHelper(androidx.compose.foundation.layout.Arrangement.Horizontal r22, androidx.compose.foundation.layout.Arrangement.Vertical r23, int r24, int r25, androidx.compose.foundation.layout.FlowLayoutOverflowState r26, int r27, java.util.List<? extends kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>> r28, kotlin.jvm.functions.Function4<? super java.lang.Integer, ? super androidx.compose.foundation.layout.FlowLineInfo, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, androidx.compose.runtime.Composer r30, int r31) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.ContextualFlowLayoutKt.contextualRowMeasurementHelper(androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, int, int, androidx.compose.foundation.layout.FlowLayoutOverflowState, int, java.util.List, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int):kotlin.jvm.functions.Function2");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        if (r30.changed(r8) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r30.changed(r15) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
    
        if (r30.changed(r14) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a4, code lost:
    
        if (r30.changed(r13) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c1, code lost:
    
        if (r30.changed(r29) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c9, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ca, code lost:
    
        r2 = r2 | r4;
        r7 = r30.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d4, code lost:
    
        if (r2 != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00dc, code lost:
    
        if (r7 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0118, code lost:
    
        r7 = (kotlin.jvm.functions.Function2) r7;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0123, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0125, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0128, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x012b, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e1, code lost:
    
        r7 = new androidx.compose.foundation.layout.FlowMeasureLazyPolicy(false, r8, r9, r9.getSpacing(), androidx.compose.foundation.layout.FlowLayoutKt.getCROSS_AXIS_ALIGNMENT_START(), r8.getSpacing(), r13, r14, r15, r26, r28, r29, null).getMeasurePolicy();
        r30.updateRememberedValue(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c7, code lost:
    
        if ((12582912 & r31) != 8388608) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
    
        if (r30.changed(r9) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.jvm.functions.Function2<androidx.compose.ui.layout.SubcomposeMeasureScope, androidx.compose.ui.unit.Constraints, androidx.compose.ui.layout.MeasureResult> contextualColumnMeasureHelper(androidx.compose.foundation.layout.Arrangement.Vertical r22, androidx.compose.foundation.layout.Arrangement.Horizontal r23, int r24, int r25, androidx.compose.foundation.layout.FlowLayoutOverflowState r26, int r27, java.util.List<? extends kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>> r28, kotlin.jvm.functions.Function4<? super java.lang.Integer, ? super androidx.compose.foundation.layout.FlowLineInfo, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, androidx.compose.runtime.Composer r30, int r31) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.ContextualFlowLayoutKt.contextualColumnMeasureHelper(androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, int, int, androidx.compose.foundation.layout.FlowLayoutOverflowState, int, java.util.List, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int):kotlin.jvm.functions.Function2");
    }
}
