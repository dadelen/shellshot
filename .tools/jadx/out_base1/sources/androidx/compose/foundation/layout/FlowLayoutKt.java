package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.FlowLayoutBuildingBlocks;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: FlowLayout.kt */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001ag\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001ag\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u001b2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u001d\u001a%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u000fH\u0001¢\u0006\u0002\u0010!\u001a5\u0010\"\u001a\u00020#2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010&\u001a\u0095\u0001\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172#\u0010.\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002¢\u0006\u0002\u00102\u001a[\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002¢\u0006\u0002\u00106\u001aS\u00107\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000fH\u0002\u001a\u0090\u0001\u00109\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172#\u0010.\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002\u001a%\u0010:\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000fH\u0001¢\u0006\u0002\u0010;\u001a5\u0010<\u001a\u00020#2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010=\u001a\\\u0010>\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020J2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0000ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001a\u001c\u0010M\u001a\u00020\u000f*\u00020+2\u0006\u0010N\u001a\u00020O2\u0006\u0010,\u001a\u00020\u000fH\u0000\u001a\u001c\u0010P\u001a\u00020\u000f*\u00020+2\u0006\u0010N\u001a\u00020O2\u0006\u0010.\u001a\u00020\u000fH\u0000\u001a<\u0010Q\u001a\u00020(*\u00020E2\u0006\u0010A\u001a\u00020B2\u0006\u0010I\u001a\u00020R2\u0014\u0010S\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010T\u0012\u0004\u0012\u00020\u00070\u0014H\u0000ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001aT\u0010W\u001a\u00020?*\u00020@2\u0006\u0010I\u001a\u00020J2\u0006\u0010X\u001a\u00020\u000f2\u0006\u0010Y\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020?0[2\u0006\u0010\\\u001a\u00020B2\u0006\u0010]\u001a\u000204H\u0000ø\u0001\u0000¢\u0006\u0004\b^\u0010_\u001a\u001e\u0010`\u001a\u0004\u0018\u00010E*\b\u0012\u0004\u0012\u00020E0D2\b\u0010a\u001a\u0004\u0018\u00010bH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006c"}, d2 = {"CROSS_AXIS_ALIGNMENT_START", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCROSS_AXIS_ALIGNMENT_START", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "CROSS_AXIS_ALIGNMENT_TOP", "getCROSS_AXIS_ALIGNMENT_TOP", "FlowColumn", "", "modifier", "Landroidx/compose/ui/Modifier;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "maxItemsInEachColumn", "", "maxLines", "overflow", "Landroidx/compose/foundation/layout/FlowColumnOverflow;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowColumnOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FlowRow", "maxItemsInEachRow", "Landroidx/compose/foundation/layout/FlowRowOverflow;", "Landroidx/compose/foundation/layout/FlowRowScope;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowRowOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "columnMeasurementHelper", "Landroidx/compose/ui/layout/MeasurePolicy;", "maxItemsInMainAxis", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "columnMeasurementMultiContentHelper", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "overflowState", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "intrinsicCrossAxisSize", "Landroidx/collection/IntIntPair;", "children", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "mainAxisSize", "Lkotlin/Function3;", "crossAxisSize", "mainAxisAvailable", "mainAxisSpacing", "crossAxisSpacing", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;IIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "mainAxisSizes", "", "crossAxisSizes", "(Ljava/util/List;[I[IIIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "maxIntrinsicMainAxisSize", "crossAxisAvailable", "minIntrinsicMainAxisSize", "rowMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "rowMeasurementMultiContentHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "breakDownItems", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurePolicy", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "measurablesIterator", "", "Landroidx/compose/ui/layout/Measurable;", "mainAxisSpacingDp", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacingDp", "constraints", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "breakDownItems-di9J0FM", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;Ljava/util/Iterator;FFJIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)Landroidx/compose/ui/layout/MeasureResult;", "crossAxisMin", "isHorizontal", "", "mainAxisMin", "measureAndCache", "Landroidx/compose/ui/unit/Constraints;", "storePlaceable", "Landroidx/compose/ui/layout/Placeable;", "measureAndCache-rqJ1uqs", "(Landroidx/compose/ui/layout/Measurable;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;JLkotlin/jvm/functions/Function1;)J", "placeHelper", "mainAxisTotalSize", "crossAxisTotalSize", "items", "Landroidx/compose/runtime/collection/MutableVector;", "measureHelper", "outPosition", "placeHelper-BmaY500", "(Landroidx/compose/ui/layout/MeasureScope;JII[ILandroidx/compose/runtime/collection/MutableVector;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;[I)Landroidx/compose/ui/layout/MeasureResult;", "safeNext", "info", "Landroidx/compose/foundation/layout/FlowLineInfo;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FlowLayoutKt {
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_TOP = CrossAxisAlignment.INSTANCE.vertical$foundation_layout_release(Alignment.INSTANCE.getTop());
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_START = CrossAxisAlignment.INSTANCE.horizontal$foundation_layout_release(Alignment.INSTANCE.getStart());

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0315 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0265  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FlowRow(androidx.compose.ui.Modifier r26, androidx.compose.foundation.layout.Arrangement.Horizontal r27, androidx.compose.foundation.layout.Arrangement.Vertical r28, int r29, int r30, androidx.compose.foundation.layout.FlowRowOverflow r31, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.FlowRowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instructions count: 885
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow(androidx.compose.ui.Modifier, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, int, int, androidx.compose.foundation.layout.FlowRowOverflow, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0315 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0265  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FlowColumn(androidx.compose.ui.Modifier r26, androidx.compose.foundation.layout.Arrangement.Vertical r27, androidx.compose.foundation.layout.Arrangement.Horizontal r28, int r29, int r30, androidx.compose.foundation.layout.FlowColumnOverflow r31, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.FlowColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instructions count: 885
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn(androidx.compose.ui.Modifier, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, int, int, androidx.compose.foundation.layout.FlowColumnOverflow, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        if (r22.changed(r9) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r22.changed(r13) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006e, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006f, code lost:
    
        r2 = r2 | r4;
        r5 = r22.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007a, code lost:
    
        if (r2 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0082, code lost:
    
        if (r5 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0085, code lost:
    
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00bf, code lost:
    
        r7 = (androidx.compose.ui.layout.MeasurePolicy) r7;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ca, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00cc, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00cf, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d2, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0087, code lost:
    
        r6 = new androidx.compose.foundation.layout.FlowMeasurePolicy(true, r8, r9, r8.getSpacing(), androidx.compose.foundation.layout.FlowLayoutKt.CROSS_AXIS_ALIGNMENT_TOP, r9.getSpacing(), r13, Integer.MAX_VALUE, androidx.compose.foundation.layout.FlowRowOverflow.Companion.getVisible().createOverflowState$foundation_layout_release(), null);
        r7 = new androidx.compose.foundation.layout.FlowLayoutKt$rowMeasurementHelper$1$1(r6);
        r22.updateRememberedValue(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
    
        if ((r23 & 384) == 256) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
    
        if (r22.changed(r8) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.layout.MeasurePolicy rowMeasurementHelper(androidx.compose.foundation.layout.Arrangement.Horizontal r19, androidx.compose.foundation.layout.Arrangement.Vertical r20, int r21, androidx.compose.runtime.Composer r22, int r23) {
        /*
            r0 = r22
            r1 = r23
            r2 = 1479255111(0x582ba447, float:7.548882E14)
            java.lang.String r3 = "C(rowMeasurementHelper)P(!1,2)380@13086L883:FlowLayout.kt#2w3rfo"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r2, r3)
            boolean r3 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r3 == 0) goto L19
            r3 = -1
            java.lang.String r4 = "androidx.compose.foundation.layout.rowMeasurementHelper (FlowLayout.kt:379)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r2, r1, r3, r4)
        L19:
            r2 = -75342195(0xfffffffffb825e8d, float:-1.3538326E36)
            java.lang.String r3 = "CC(remember):FlowLayout.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r2, r3)
            r2 = r1 & 14
            r2 = r2 ^ 6
            r3 = 4
            r4 = 0
            r5 = 1
            if (r2 <= r3) goto L33
            r8 = r19
            boolean r2 = r0.changed(r8)
            if (r2 != 0) goto L39
            goto L35
        L33:
            r8 = r19
        L35:
            r2 = r1 & 6
            if (r2 != r3) goto L3b
        L39:
            r2 = r5
            goto L3c
        L3b:
            r2 = r4
        L3c:
            r3 = r1 & 112(0x70, float:1.57E-43)
            r3 = r3 ^ 48
            r6 = 32
            if (r3 <= r6) goto L4d
            r9 = r20
            boolean r3 = r0.changed(r9)
            if (r3 != 0) goto L53
            goto L4f
        L4d:
            r9 = r20
        L4f:
            r3 = r1 & 48
            if (r3 != r6) goto L55
        L53:
            r3 = r5
            goto L56
        L55:
            r3 = r4
        L56:
            r2 = r2 | r3
            r3 = r1 & 896(0x380, float:1.256E-42)
            r3 = r3 ^ 384(0x180, float:5.38E-43)
            r6 = 256(0x100, float:3.59E-43)
            if (r3 <= r6) goto L68
            r13 = r21
            boolean r3 = r0.changed(r13)
            if (r3 != 0) goto L6e
            goto L6a
        L68:
            r13 = r21
        L6a:
            r3 = r1 & 384(0x180, float:5.38E-43)
            if (r3 != r6) goto L6f
        L6e:
            r4 = r5
        L6f:
            r2 = r2 | r4
            r3 = r22
            r4 = 0
            java.lang.Object r5 = r3.rememberedValue()
            r17 = 0
            if (r2 != 0) goto L87
            androidx.compose.runtime.Composer$Companion r6 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r6 = r6.getEmpty()
            if (r5 != r6) goto L85
            goto L87
        L85:
            r7 = r5
            goto Lbf
        L87:
            r18 = 0
            float r10 = r8.getSpacing()
            androidx.compose.foundation.layout.CrossAxisAlignment r11 = androidx.compose.foundation.layout.FlowLayoutKt.CROSS_AXIS_ALIGNMENT_TOP
            float r12 = r9.getSpacing()
            androidx.compose.foundation.layout.FlowRowOverflow$Companion r6 = androidx.compose.foundation.layout.FlowRowOverflow.INSTANCE
            androidx.compose.foundation.layout.FlowRowOverflow r6 = r6.getVisible()
            androidx.compose.foundation.layout.FlowLayoutOverflowState r15 = r6.createOverflowState$foundation_layout_release()
            androidx.compose.foundation.layout.FlowMeasurePolicy r6 = new androidx.compose.foundation.layout.FlowMeasurePolicy
            r7 = 1
            r14 = 2147483647(0x7fffffff, float:NaN)
            r16 = 0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            androidx.compose.ui.layout.MultiContentMeasurePolicy r6 = (androidx.compose.ui.layout.MultiContentMeasurePolicy) r6
            androidx.compose.foundation.layout.FlowLayoutKt$rowMeasurementHelper$1$1 r7 = new androidx.compose.foundation.layout.FlowLayoutKt$rowMeasurementHelper$1$1
            r7.<init>()
            androidx.compose.ui.layout.MeasurePolicy r7 = (androidx.compose.ui.layout.MeasurePolicy) r7
            r3.updateRememberedValue(r7)
        Lbf:
            androidx.compose.ui.layout.MeasurePolicy r7 = (androidx.compose.ui.layout.MeasurePolicy) r7
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto Lcf
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        Lcf:
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.rowMeasurementHelper(androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, int, androidx.compose.runtime.Composer, int):androidx.compose.ui.layout.MeasurePolicy");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        if (r24.changed(r9) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r24.changed(r13) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
    
        if (r24.changed(r14) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0089, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
    
        r2 = (r2 | r4) | r24.changed(r23);
        r5 = r24.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x009b, code lost:
    
        if (r2 != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a3, code lost:
    
        if (r5 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a6, code lost:
    
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ca, code lost:
    
        r6 = (androidx.compose.foundation.layout.FlowMeasurePolicy) r6;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d5, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d7, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00da, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a8, code lost:
    
        r6 = new androidx.compose.foundation.layout.FlowMeasurePolicy(true, r8, r9, r8.getSpacing(), androidx.compose.foundation.layout.FlowLayoutKt.CROSS_AXIS_ALIGNMENT_TOP, r9.getSpacing(), r13, r14, r23, null);
        r24.updateRememberedValue(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0087, code lost:
    
        if ((r25 & 3072) != 2048) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
    
        if (r24.changed(r8) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.layout.MultiContentMeasurePolicy rowMeasurementMultiContentHelper(androidx.compose.foundation.layout.Arrangement.Horizontal r19, androidx.compose.foundation.layout.Arrangement.Vertical r20, int r21, int r22, androidx.compose.foundation.layout.FlowLayoutOverflowState r23, androidx.compose.runtime.Composer r24, int r25) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.rowMeasurementMultiContentHelper(androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, int, int, androidx.compose.foundation.layout.FlowLayoutOverflowState, androidx.compose.runtime.Composer, int):androidx.compose.ui.layout.MultiContentMeasurePolicy");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        if (r22.changed(r8) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r22.changed(r13) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006e, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006f, code lost:
    
        r2 = r2 | r4;
        r5 = r22.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007a, code lost:
    
        if (r2 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0082, code lost:
    
        if (r5 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0085, code lost:
    
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00bd, code lost:
    
        r7 = (androidx.compose.ui.layout.MeasurePolicy) r7;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c8, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ca, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00cd, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d0, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0087, code lost:
    
        r6 = new androidx.compose.foundation.layout.FlowMeasurePolicy(false, r8, r9, r9.getSpacing(), androidx.compose.foundation.layout.FlowLayoutKt.CROSS_AXIS_ALIGNMENT_START, r8.getSpacing(), r13, Integer.MAX_VALUE, androidx.compose.foundation.layout.FlowRowOverflow.Companion.getVisible().createOverflowState$foundation_layout_release(), null);
        r7 = new androidx.compose.foundation.layout.FlowLayoutKt$columnMeasurementHelper$1$1(r6);
        r22.updateRememberedValue(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
    
        if ((r23 & 384) == 256) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
    
        if (r22.changed(r9) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.layout.MeasurePolicy columnMeasurementHelper(androidx.compose.foundation.layout.Arrangement.Vertical r19, androidx.compose.foundation.layout.Arrangement.Horizontal r20, int r21, androidx.compose.runtime.Composer r22, int r23) {
        /*
            r0 = r22
            r1 = r23
            r2 = -2013098357(0xffffffff88028e8b, float:-3.928801E-34)
            java.lang.String r3 = "C(columnMeasurementHelper)P(2)443@15212L856:FlowLayout.kt#2w3rfo"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r2, r3)
            boolean r3 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r3 == 0) goto L19
            r3 = -1
            java.lang.String r4 = "androidx.compose.foundation.layout.columnMeasurementHelper (FlowLayout.kt:442)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r2, r1, r3, r4)
        L19:
            r2 = 1837869538(0x6d8ba9e2, float:5.402974E27)
            java.lang.String r3 = "CC(remember):FlowLayout.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r2, r3)
            r2 = r1 & 14
            r2 = r2 ^ 6
            r3 = 4
            r4 = 0
            r5 = 1
            if (r2 <= r3) goto L33
            r9 = r19
            boolean r2 = r0.changed(r9)
            if (r2 != 0) goto L39
            goto L35
        L33:
            r9 = r19
        L35:
            r2 = r1 & 6
            if (r2 != r3) goto L3b
        L39:
            r2 = r5
            goto L3c
        L3b:
            r2 = r4
        L3c:
            r3 = r1 & 112(0x70, float:1.57E-43)
            r3 = r3 ^ 48
            r6 = 32
            if (r3 <= r6) goto L4d
            r8 = r20
            boolean r3 = r0.changed(r8)
            if (r3 != 0) goto L53
            goto L4f
        L4d:
            r8 = r20
        L4f:
            r3 = r1 & 48
            if (r3 != r6) goto L55
        L53:
            r3 = r5
            goto L56
        L55:
            r3 = r4
        L56:
            r2 = r2 | r3
            r3 = r1 & 896(0x380, float:1.256E-42)
            r3 = r3 ^ 384(0x180, float:5.38E-43)
            r6 = 256(0x100, float:3.59E-43)
            if (r3 <= r6) goto L68
            r13 = r21
            boolean r3 = r0.changed(r13)
            if (r3 != 0) goto L6e
            goto L6a
        L68:
            r13 = r21
        L6a:
            r3 = r1 & 384(0x180, float:5.38E-43)
            if (r3 != r6) goto L6f
        L6e:
            r4 = r5
        L6f:
            r2 = r2 | r4
            r3 = r22
            r4 = 0
            java.lang.Object r5 = r3.rememberedValue()
            r17 = 0
            if (r2 != 0) goto L87
            androidx.compose.runtime.Composer$Companion r6 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r6 = r6.getEmpty()
            if (r5 != r6) goto L85
            goto L87
        L85:
            r7 = r5
            goto Lbd
        L87:
            r18 = 0
            float r10 = r9.getSpacing()
            androidx.compose.foundation.layout.CrossAxisAlignment r11 = androidx.compose.foundation.layout.FlowLayoutKt.CROSS_AXIS_ALIGNMENT_START
            float r12 = r8.getSpacing()
            androidx.compose.foundation.layout.FlowRowOverflow$Companion r6 = androidx.compose.foundation.layout.FlowRowOverflow.INSTANCE
            androidx.compose.foundation.layout.FlowRowOverflow r6 = r6.getVisible()
            androidx.compose.foundation.layout.FlowLayoutOverflowState r15 = r6.createOverflowState$foundation_layout_release()
            androidx.compose.foundation.layout.FlowMeasurePolicy r6 = new androidx.compose.foundation.layout.FlowMeasurePolicy
            r7 = 0
            r14 = 2147483647(0x7fffffff, float:NaN)
            r16 = 0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            androidx.compose.foundation.layout.FlowLayoutKt$columnMeasurementHelper$1$1 r7 = new androidx.compose.foundation.layout.FlowLayoutKt$columnMeasurementHelper$1$1
            r7.<init>()
            androidx.compose.ui.layout.MeasurePolicy r7 = (androidx.compose.ui.layout.MeasurePolicy) r7
            r3.updateRememberedValue(r7)
        Lbd:
            androidx.compose.ui.layout.MeasurePolicy r7 = (androidx.compose.ui.layout.MeasurePolicy) r7
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto Lcd
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        Lcd:
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.columnMeasurementHelper(androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, int, androidx.compose.runtime.Composer, int):androidx.compose.ui.layout.MeasurePolicy");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        if (r24.changed(r8) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r24.changed(r13) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
    
        if (r24.changed(r14) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0089, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
    
        r2 = (r2 | r4) | r24.changed(r23);
        r5 = r24.rememberedValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x009b, code lost:
    
        if (r2 != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a3, code lost:
    
        if (r5 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a6, code lost:
    
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ca, code lost:
    
        r6 = (androidx.compose.foundation.layout.FlowMeasurePolicy) r6;
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d5, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d7, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00da, code lost:
    
        androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a8, code lost:
    
        r6 = new androidx.compose.foundation.layout.FlowMeasurePolicy(false, r8, r9, r9.getSpacing(), androidx.compose.foundation.layout.FlowLayoutKt.CROSS_AXIS_ALIGNMENT_START, r8.getSpacing(), r13, r14, r23, null);
        r24.updateRememberedValue(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0087, code lost:
    
        if ((r25 & 3072) != 2048) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
    
        if (r24.changed(r9) == false) goto L11;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.layout.MultiContentMeasurePolicy columnMeasurementMultiContentHelper(androidx.compose.foundation.layout.Arrangement.Vertical r19, androidx.compose.foundation.layout.Arrangement.Horizontal r20, int r21, int r22, androidx.compose.foundation.layout.FlowLayoutOverflowState r23, androidx.compose.runtime.Composer r24, int r25) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.columnMeasurementMultiContentHelper(androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, int, int, androidx.compose.foundation.layout.FlowLayoutOverflowState, androidx.compose.runtime.Composer, int):androidx.compose.ui.layout.MultiContentMeasurePolicy");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, int crossAxisAvailable, int mainAxisSpacing, int maxItemsInMainAxis) {
        int fixedSpace = 0;
        int currentFixedSpace = 0;
        int lastBreak = 0;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            IntrinsicMeasurable child = (IntrinsicMeasurable) item$iv;
            int index = index$iv;
            int size2 = function3.invoke(child, Integer.valueOf(index), Integer.valueOf(crossAxisAvailable)).intValue() + mainAxisSpacing;
            if ((index + 1) - lastBreak == maxItemsInMainAxis || index + 1 == list.size()) {
                lastBreak = index;
                fixedSpace = Math.max(fixedSpace, (currentFixedSpace + size2) - mainAxisSpacing);
                currentFixedSpace = 0;
            } else {
                currentFixedSpace += size2;
            }
        }
        return fixedSpace;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v13, types: [kotlin.collections.IntIterator] */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.collections.IntIterator] */
    public static final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function32, int crossAxisAvailable, int mainAxisSpacing, int crossAxisSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        int i = maxItemsInMainAxis;
        int i2 = maxLines;
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        int[] crossAxisSizes = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            crossAxisSizes[i3] = 0;
        }
        int size2 = list.size();
        int[] crossAxisSizes2 = new int[size2];
        for (int i4 = 0; i4 < size2; i4++) {
            crossAxisSizes2[i4] = 0;
        }
        int size3 = list.size();
        for (int index = 0; index < size3; index++) {
            IntrinsicMeasurable child = list.get(index);
            int mainAxisItemSize = function3.invoke(child, Integer.valueOf(index), Integer.valueOf(crossAxisAvailable)).intValue();
            crossAxisSizes[index] = mainAxisItemSize;
            crossAxisSizes2[index] = function32.invoke(child, Integer.valueOf(index), Integer.valueOf(mainAxisItemSize)).intValue();
        }
        List<? extends IntrinsicMeasurable> list2 = list;
        int maxItemsThatCanBeShown = Integer.MAX_VALUE;
        if (i2 != Integer.MAX_VALUE && i != Integer.MAX_VALUE) {
            maxItemsThatCanBeShown = i * i2;
        }
        boolean mustHaveEllipsis = (maxItemsThatCanBeShown >= list2.size() || !(overflow.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.ExpandIndicator || overflow.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) ? maxItemsThatCanBeShown >= list2.size() && i2 >= overflow.getMinLinesToShowCollapse$foundation_layout_release() && overflow.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator : true;
        int maxItemsThatCanBeShown2 = Math.min(maxItemsThatCanBeShown - (mustHaveEllipsis ? 1 : 0), list2.size());
        int $this$minIntrinsicMainAxisSize_u24lambda_u249 = ArraysKt.sum(crossAxisSizes);
        int maxMainAxisSize = ((list2.size() - 1) * mainAxisSpacing) + $this$minIntrinsicMainAxisSize_u24lambda_u249;
        if (crossAxisSizes2.length == 0) {
            throw new NoSuchElementException();
        }
        int crossAxisUsed = crossAxisSizes2[0];
        ?? it = new IntRange(1, ArraysKt.getLastIndex(crossAxisSizes2)).iterator();
        while (it.hasNext()) {
            int it2 = crossAxisSizes2[it.nextInt()];
            if (crossAxisUsed < it2) {
                crossAxisUsed = it2;
            }
        }
        if (crossAxisSizes.length == 0) {
            throw new NoSuchElementException();
        }
        int i5 = crossAxisSizes[0];
        int mainAxisUsed = maxMainAxisSize;
        int mainAxisUsed2 = ArraysKt.getLastIndex(crossAxisSizes);
        ?? it3 = new IntRange(1, mainAxisUsed2).iterator();
        while (it3.hasNext()) {
            int it4 = crossAxisSizes[it3.nextInt()];
            if (i5 < it4) {
                i5 = it4;
            }
        }
        int minimumItemSize = i5;
        int itemShown = minimumItemSize;
        int high = maxMainAxisSize;
        while (itemShown <= high) {
            if (crossAxisUsed == crossAxisAvailable) {
                return mainAxisUsed;
            }
            int mid = (itemShown + high) / 2;
            int high2 = high;
            int[] mainAxisSizes = crossAxisSizes;
            int[] mainAxisSizes2 = crossAxisSizes2;
            int low = itemShown;
            boolean mustHaveEllipsis2 = mustHaveEllipsis;
            long pair = intrinsicCrossAxisSize(list2, mainAxisSizes, mainAxisSizes2, mid, mainAxisSpacing, crossAxisSpacing, i, i2, overflow);
            crossAxisUsed = IntIntPair.m22getFirstimpl(pair);
            int itemShown2 = IntIntPair.m23getSecondimpl(pair);
            if (crossAxisUsed > crossAxisAvailable || itemShown2 < maxItemsThatCanBeShown2) {
                int low2 = mid + 1;
                if (low2 > high2) {
                    return low2;
                }
                i = maxItemsInMainAxis;
                i2 = maxLines;
                mainAxisUsed = mid;
                itemShown = low2;
                list2 = list;
                crossAxisSizes2 = mainAxisSizes2;
                crossAxisSizes = mainAxisSizes;
                high = high2;
                mustHaveEllipsis = mustHaveEllipsis2;
            } else {
                if (crossAxisUsed >= crossAxisAvailable) {
                    return mid;
                }
                i = maxItemsInMainAxis;
                i2 = maxLines;
                mainAxisUsed = mid;
                itemShown = low;
                mustHaveEllipsis = mustHaveEllipsis2;
                crossAxisSizes2 = mainAxisSizes2;
                crossAxisSizes = mainAxisSizes;
                high = mid - 1;
                list2 = list;
            }
        }
        return mainAxisUsed;
    }

    private static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, final int[] mainAxisSizes, final int[] crossAxisSizes, int mainAxisAvailable, int mainAxisSpacing, int crossAxisSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        return intrinsicCrossAxisSize(list, new Function3<IntrinsicMeasurable, Integer, Integer, Integer>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$intrinsicCrossAxisSize$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num, Integer num2) {
                return invoke(intrinsicMeasurable, num.intValue(), num2.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable $this$intrinsicCrossAxisSize, int index, int i) {
                return Integer.valueOf(mainAxisSizes[index]);
            }
        }, new Function3<IntrinsicMeasurable, Integer, Integer, Integer>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$intrinsicCrossAxisSize$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num, Integer num2) {
                return invoke(intrinsicMeasurable, num.intValue(), num2.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable $this$intrinsicCrossAxisSize, int index, int i) {
                return Integer.valueOf(crossAxisSizes[index]);
            }
        }, mainAxisAvailable, mainAxisSpacing, crossAxisSpacing, maxItemsInMainAxis, maxLines, overflow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function32, int mainAxisAvailable, int mainAxisSpacing, int crossAxisSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        int remaining;
        List<? extends IntrinsicMeasurable> list2 = list;
        Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function33 = function3;
        if (list2.isEmpty()) {
            return IntIntPair.m18constructorimpl(0, 0);
        }
        FlowLayoutBuildingBlocks buildingBlocks = new FlowLayoutBuildingBlocks(maxItemsInMainAxis, overflow, OrientationIndependentConstraints.m639constructorimpl(0, mainAxisAvailable, 0, Integer.MAX_VALUE), maxLines, mainAxisSpacing, crossAxisSpacing, null);
        IntrinsicMeasurable nextChild = (IntrinsicMeasurable) CollectionsKt.getOrNull(list2, 0);
        int nextCrossAxisSize = nextChild != null ? function32.invoke(nextChild, 0, Integer.valueOf(mainAxisAvailable)).intValue() : 0;
        int nextMainAxisSize = nextChild != null ? function33.invoke(nextChild, 0, Integer.valueOf(nextCrossAxisSize)).intValue() : 0;
        int totalCrossAxisSize = 0;
        int lineIndex = 0;
        int noOfItemsShown = mainAxisAvailable;
        int nextCrossAxisSize2 = nextCrossAxisSize;
        if (buildingBlocks.m595getWrapInfoOpUlnko(list2.size() > 1, 0, IntIntPair.m18constructorimpl(mainAxisAvailable, Integer.MAX_VALUE), nextChild == null ? null : IntIntPair.m15boximpl(IntIntPair.m18constructorimpl(nextMainAxisSize, nextCrossAxisSize)), 0, 0, 0, false, false).getIsLastItemInContainer()) {
            IntIntPair m600ellipsisSizeF35zmw$foundation_layout_release = overflow.m600ellipsisSizeF35zmw$foundation_layout_release(nextChild != null, 0, 0);
            int size = m600ellipsisSizeF35zmw$foundation_layout_release != null ? IntIntPair.m23getSecondimpl(m600ellipsisSizeF35zmw$foundation_layout_release.getPackedValue()) : 0;
            return IntIntPair.m18constructorimpl(size, 0);
        }
        int noOfItemsShown2 = 0;
        int index = 0;
        int size2 = list2.size();
        int nextMainAxisSize2 = 0;
        int lastBreak = 0;
        int nextCrossAxisSize3 = nextCrossAxisSize2;
        while (true) {
            if (index >= size2) {
                break;
            }
            int childCrossAxisSize = nextCrossAxisSize3;
            int childMainAxisSize = nextMainAxisSize;
            int remaining2 = noOfItemsShown - childMainAxisSize;
            int remaining3 = index + 1;
            int currentCrossAxisSize = Math.max(nextMainAxisSize2, childCrossAxisSize);
            IntrinsicMeasurable nextChild2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(list2, index + 1);
            int nextCrossAxisSize4 = nextChild2 != null ? function32.invoke(nextChild2, Integer.valueOf(index + 1), Integer.valueOf(mainAxisAvailable)).intValue() : 0;
            int nextMainAxisSize3 = nextChild2 != null ? function33.invoke(nextChild2, Integer.valueOf(index + 1), Integer.valueOf(nextCrossAxisSize4)).intValue() + mainAxisSpacing : 0;
            int index2 = index;
            int i = size2;
            FlowLayoutBuildingBlocks.WrapInfo wrapInfo = buildingBlocks.m595getWrapInfoOpUlnko(index + 2 < list.size(), (index + 1) - lastBreak, IntIntPair.m18constructorimpl(remaining2, Integer.MAX_VALUE), nextChild2 == null ? null : IntIntPair.m15boximpl(IntIntPair.m18constructorimpl(nextMainAxisSize3, nextCrossAxisSize4)), lineIndex, totalCrossAxisSize, currentCrossAxisSize, false, false);
            int lineIndex2 = lineIndex;
            if (wrapInfo.getIsLastItemInLine()) {
                int totalCrossAxisSize2 = totalCrossAxisSize + currentCrossAxisSize + crossAxisSpacing;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo ellipsisWrapInfo = buildingBlocks.getWrapEllipsisInfo(wrapInfo, nextChild2 != null, lineIndex2, totalCrossAxisSize2, remaining2, (index2 + 1) - lastBreak);
                remaining = mainAxisAvailable;
                lastBreak = index2 + 1;
                int nextMainAxisSize4 = nextMainAxisSize3 - mainAxisSpacing;
                lineIndex = lineIndex2 + 1;
                if (wrapInfo.getIsLastItemInContainer()) {
                    if (ellipsisWrapInfo != null) {
                        long it = ellipsisWrapInfo.getEllipsisSize();
                        if (!ellipsisWrapInfo.getPlaceEllipsisOnLastContentLine()) {
                            totalCrossAxisSize2 += IntIntPair.m23getSecondimpl(it) + crossAxisSpacing;
                        }
                    }
                    totalCrossAxisSize = totalCrossAxisSize2;
                    noOfItemsShown2 = remaining3;
                } else {
                    nextMainAxisSize = nextMainAxisSize4;
                    nextMainAxisSize2 = 0;
                    totalCrossAxisSize = totalCrossAxisSize2;
                }
            } else {
                lineIndex = lineIndex2;
                nextMainAxisSize = nextMainAxisSize3;
                remaining = remaining2;
                nextMainAxisSize2 = currentCrossAxisSize;
            }
            index = index2 + 1;
            noOfItemsShown2 = remaining3;
            size2 = i;
            function33 = function3;
            nextCrossAxisSize3 = nextCrossAxisSize4;
            noOfItemsShown = remaining;
            list2 = list;
        }
        return IntIntPair.m18constructorimpl(totalCrossAxisSize - crossAxisSpacing, noOfItemsShown2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: breakDownItems-di9J0FM, reason: not valid java name */
    public static final MeasureResult m597breakDownItemsdi9J0FM(MeasureScope measureScope, FlowLineMeasurePolicy flowLineMeasurePolicy, Iterator<? extends Measurable> it, float f, float f2, long j, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState) {
        long m642copyyUG9Ft0;
        int i3;
        int i4;
        int i5;
        FlowLineInfo flowLineInfo;
        int i6;
        final Ref.ObjectRef objectRef;
        Measurable measurable;
        IntIntPair intIntPair;
        int i7;
        int i8;
        MutableIntObjectMap mutableIntObjectMap;
        int i9;
        int i10;
        int height;
        int width;
        int i11;
        MutableIntObjectMap mutableIntObjectMap2;
        int i12;
        IntIntPair intIntPair2;
        IntIntPair m15boximpl;
        MutableIntList mutableIntList;
        MutableIntList mutableIntList2;
        int i13;
        int coerceAtLeast;
        MeasureScope measureScope2 = measureScope;
        MutableVector mutableVector = new MutableVector(new MeasureResult[16], 0);
        int m6579getMaxWidthimpl = Constraints.m6579getMaxWidthimpl(j);
        int m6581getMinWidthimpl = Constraints.m6581getMinWidthimpl(j);
        int m6578getMaxHeightimpl = Constraints.m6578getMaxHeightimpl(j);
        MutableIntObjectMap mutableIntObjectMapOf = IntObjectMapKt.mutableIntObjectMapOf();
        ArrayList arrayList = new ArrayList();
        int ceil = (int) Math.ceil(measureScope2.mo364toPx0680j_4(f));
        int ceil2 = (int) Math.ceil(measureScope2.mo364toPx0680j_4(f2));
        long m639constructorimpl = OrientationIndependentConstraints.m639constructorimpl(0, m6579getMaxWidthimpl, 0, m6578getMaxHeightimpl);
        m642copyyUG9Ft0 = OrientationIndependentConstraints.m642copyyUG9Ft0(m639constructorimpl, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(m639constructorimpl) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(m639constructorimpl) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(m639constructorimpl) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(m639constructorimpl) : 0);
        long m654toBoxConstraintsOenEA2s = OrientationIndependentConstraints.m654toBoxConstraintsOenEA2s(m642copyyUG9Ft0, flowLineMeasurePolicy.isHorizontal() ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical);
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        if (it instanceof ContextualFlowItemIterator) {
            i3 = m6579getMaxWidthimpl;
            i4 = m6578getMaxHeightimpl;
            flowLineInfo = new FlowLineInfo(0, 0, measureScope2.mo361toDpu2uoSUM(i3), measureScope2.mo361toDpu2uoSUM(i4), null);
            i5 = 0;
        } else {
            i3 = m6579getMaxWidthimpl;
            i4 = m6578getMaxHeightimpl;
            i5 = 0;
            flowLineInfo = null;
        }
        FlowLineInfo flowLineInfo2 = flowLineInfo;
        Measurable safeNext = !it.hasNext() ? null : safeNext(it, flowLineInfo2);
        if (safeNext != null) {
            i6 = ceil;
            objectRef = objectRef2;
            measurable = safeNext;
            intIntPair = IntIntPair.m15boximpl(m598measureAndCacherqJ1uqs(measurable, flowLineMeasurePolicy, m654toBoxConstraintsOenEA2s, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$nextSize$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                    invoke2(placeable);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable placeable) {
                    objectRef.element = placeable;
                }
            }));
        } else {
            i6 = ceil;
            objectRef = objectRef2;
            measurable = safeNext;
            intIntPair = null;
        }
        IntIntPair intIntPair3 = intIntPair;
        Integer valueOf = intIntPair3 != null ? Integer.valueOf(IntIntPair.m22getFirstimpl(intIntPair3.getPackedValue())) : null;
        Integer valueOf2 = intIntPair3 != null ? Integer.valueOf(IntIntPair.m23getSecondimpl(intIntPair3.getPackedValue())) : null;
        int i14 = 0;
        Integer num = valueOf;
        Measurable measurable2 = measurable;
        MutableIntList mutableIntList3 = new MutableIntList(0, 1, null);
        MutableIntList mutableIntList4 = new MutableIntList(0, 1, null);
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i, flowLayoutOverflowState, j, i2, i6, ceil2, null);
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo = null;
        FlowLayoutBuildingBlocks.WrapInfo m595getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m595getWrapInfoOpUlnko(it.hasNext(), 0, IntIntPair.m18constructorimpl(i3, i4), intIntPair3, 0, 0, 0, false, false);
        if (m595getWrapInfoOpUlnko.getIsLastItemInContainer()) {
            i7 = i3;
            wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(m595getWrapInfoOpUlnko, intIntPair3 != null, -1, 0, i7, 0);
        } else {
            i7 = i3;
        }
        int i15 = m6581getMinWidthimpl;
        int i16 = 0;
        int i17 = i5;
        Measurable measurable3 = measurable2;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo2 = wrapEllipsisInfo;
        int i18 = 0;
        int i19 = 0;
        FlowLayoutBuildingBlocks.WrapInfo wrapInfo = m595getWrapInfoOpUlnko;
        int i20 = i4;
        int i21 = 0;
        while (!wrapInfo.getIsLastItemInContainer() && measurable3 != null) {
            Intrinsics.checkNotNull(num);
            int intValue = num.intValue();
            Intrinsics.checkNotNull(valueOf2);
            MutableIntList mutableIntList5 = mutableIntList4;
            int i22 = m6579getMaxWidthimpl;
            int i23 = i19 + intValue;
            int max = Math.max(i18, valueOf2.intValue());
            int i24 = i7 - intValue;
            flowLayoutOverflowState.setItemShown$foundation_layout_release(i21 + 1);
            arrayList.add(measurable3);
            mutableIntObjectMapOf.set(i21, objectRef.element);
            int i25 = (i21 + 1) - i14;
            boolean z = i25 < i;
            if (flowLineInfo2 != null) {
                int i26 = z ? i17 : i17 + 1;
                i11 = i21;
                int i27 = z ? i25 : 0;
                if (z) {
                    i12 = i25;
                    i13 = RangesKt.coerceAtLeast(i24 - i6, 0);
                } else {
                    i12 = i25;
                    i13 = i22;
                }
                float f3 = measureScope2.mo361toDpu2uoSUM(i13);
                if (z) {
                    mutableIntObjectMap2 = mutableIntObjectMapOf;
                    coerceAtLeast = i20;
                } else {
                    mutableIntObjectMap2 = mutableIntObjectMapOf;
                    coerceAtLeast = RangesKt.coerceAtLeast((i20 - max) - ceil2, 0);
                }
                flowLineInfo2.m608update4j6BHR0$foundation_layout_release(i26, i27, f3, measureScope2.mo361toDpu2uoSUM(coerceAtLeast));
            } else {
                i11 = i21;
                mutableIntObjectMap2 = mutableIntObjectMapOf;
                i12 = i25;
            }
            measurable3 = !it.hasNext() ? null : safeNext(it, flowLineInfo2);
            objectRef.element = null;
            if (measurable3 == null) {
                intIntPair2 = null;
            } else {
                intIntPair2 = IntIntPair.m15boximpl(m598measureAndCacherqJ1uqs(measurable3, flowLineMeasurePolicy, m654toBoxConstraintsOenEA2s, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                        invoke2(placeable);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable placeable) {
                        objectRef.element = placeable;
                    }
                }));
            }
            IntIntPair intIntPair4 = intIntPair2;
            Integer valueOf3 = intIntPair4 != null ? Integer.valueOf(IntIntPair.m22getFirstimpl(intIntPair4.getPackedValue()) + i6) : null;
            valueOf2 = intIntPair4 != null ? Integer.valueOf(IntIntPair.m23getSecondimpl(intIntPair4.getPackedValue())) : null;
            boolean hasNext = it.hasNext();
            long m18constructorimpl = IntIntPair.m18constructorimpl(i24, i20);
            if (intIntPair4 == null) {
                m15boximpl = null;
            } else {
                Intrinsics.checkNotNull(valueOf3);
                int intValue2 = valueOf3.intValue();
                Intrinsics.checkNotNull(valueOf2);
                m15boximpl = IntIntPair.m15boximpl(IntIntPair.m18constructorimpl(intValue2, valueOf2.intValue()));
            }
            FlowLayoutBuildingBlocks.WrapInfo m595getWrapInfoOpUlnko2 = flowLayoutBuildingBlocks.m595getWrapInfoOpUlnko(hasNext, i12, m18constructorimpl, m15boximpl, i17, i16, max, false, false);
            if (!m595getWrapInfoOpUlnko2.getIsLastItemInLine()) {
                i7 = i24;
                mutableIntList = mutableIntList3;
                Integer num2 = valueOf3;
                mutableIntList2 = mutableIntList5;
                num = num2;
                i18 = max;
                i19 = i23;
            } else {
                i15 = Math.min(Math.max(i15, i23), i22);
                int i28 = i16 + max;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo3 = flowLayoutBuildingBlocks.getWrapEllipsisInfo(m595getWrapInfoOpUlnko2, intIntPair4 != null, i17, i28, i24, (i11 + 1) - i14);
                Integer num3 = valueOf3;
                mutableIntList2 = mutableIntList5;
                mutableIntList2.add(max);
                int i29 = (m6578getMaxHeightimpl - i28) - ceil2;
                int i30 = i11 + 1;
                i22 = i22;
                mutableIntList = mutableIntList3;
                mutableIntList.add(i11 + 1);
                Integer valueOf4 = num3 != null ? Integer.valueOf(num3.intValue() - i6) : null;
                i17++;
                i19 = 0;
                i14 = i30;
                i7 = i22;
                i20 = i29;
                num = valueOf4;
                i16 = i28 + ceil2;
                wrapEllipsisInfo2 = wrapEllipsisInfo3;
                i18 = 0;
            }
            i21 = i11 + 1;
            mutableIntList4 = mutableIntList2;
            mutableIntList3 = mutableIntList;
            m6579getMaxWidthimpl = i22;
            wrapInfo = m595getWrapInfoOpUlnko2;
            mutableIntObjectMapOf = mutableIntObjectMap2;
        }
        MutableIntObjectMap mutableIntObjectMap3 = mutableIntObjectMapOf;
        MutableIntList mutableIntList6 = mutableIntList4;
        MutableIntList mutableIntList7 = mutableIntList3;
        if (wrapEllipsisInfo2 != null) {
            FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo4 = wrapEllipsisInfo2;
            arrayList.add(wrapEllipsisInfo4.getEllipsis());
            mutableIntObjectMap = mutableIntObjectMap3;
            mutableIntObjectMap.set(arrayList.size() - 1, wrapEllipsisInfo4.getPlaceable());
            int i31 = mutableIntList7._size - 1;
            if (wrapEllipsisInfo4.getPlaceEllipsisOnLastContentLine()) {
                int size = mutableIntList7.getSize() - 1;
                i8 = i15;
                mutableIntList6.set(i31, Math.max(mutableIntList6.get(i31), IntIntPair.m23getSecondimpl(wrapEllipsisInfo4.getEllipsisSize())));
                mutableIntList7.set(size, mutableIntList7.last() + 1);
            } else {
                i8 = i15;
                mutableIntList6.add(IntIntPair.m23getSecondimpl(wrapEllipsisInfo4.getEllipsisSize()));
                mutableIntList7.add(mutableIntList7.last() + 1);
            }
        } else {
            i8 = i15;
            mutableIntObjectMap = mutableIntObjectMap3;
        }
        int size2 = arrayList.size();
        Placeable[] placeableArr = new Placeable[size2];
        for (int i32 = 0; i32 < size2; i32++) {
            placeableArr[i32] = mutableIntObjectMap.get(i32);
        }
        int size3 = mutableIntList7.getSize();
        int[] iArr = new int[size3];
        for (int i33 = 0; i33 < size3; i33++) {
            iArr[i33] = 0;
        }
        int size4 = mutableIntList7.getSize();
        int[] iArr2 = new int[size4];
        for (int i34 = 0; i34 < size4; i34++) {
            iArr2[i34] = 0;
        }
        int i35 = 0;
        MutableIntList mutableIntList8 = mutableIntList7;
        int[] iArr3 = mutableIntList8.content;
        int i36 = mutableIntList8._size;
        int i37 = 0;
        int i38 = 0;
        int i39 = i8;
        Placeable[] placeableArr2 = placeableArr;
        while (i37 < i36) {
            long j2 = m654toBoxConstraintsOenEA2s;
            int i40 = iArr3[i37];
            int i41 = i37;
            int[] iArr4 = iArr;
            MutableIntList mutableIntList9 = mutableIntList6;
            int i42 = i18;
            int i43 = i20;
            MutableIntList mutableIntList10 = mutableIntList8;
            int i44 = i6;
            MutableIntObjectMap mutableIntObjectMap4 = mutableIntObjectMap;
            MeasureResult measure = RowColumnMeasurePolicyKt.measure(flowLineMeasurePolicy, i39, Constraints.m6580getMinHeightimpl(m639constructorimpl), Constraints.m6579getMaxWidthimpl(m639constructorimpl), mutableIntList6.get(i41), i44, measureScope2, arrayList, placeableArr2, i35, i40, iArr4, i41);
            Placeable[] placeableArr3 = placeableArr2;
            i35 = i40;
            ArrayList arrayList2 = arrayList;
            if (flowLineMeasurePolicy.isHorizontal()) {
                height = measure.getWidth();
                width = measure.getHeight();
            } else {
                height = measure.getHeight();
                width = measure.getWidth();
            }
            iArr2[i41] = width;
            i38 += width;
            i39 = Math.max(i39, height);
            mutableVector.add(measure);
            i37++;
            iArr = iArr4;
            arrayList = arrayList2;
            placeableArr2 = placeableArr3;
            i18 = i42;
            mutableIntList8 = mutableIntList10;
            mutableIntList6 = mutableIntList9;
            i20 = i43;
            mutableIntObjectMap = mutableIntObjectMap4;
            measureScope2 = measureScope;
            i6 = i44;
            m654toBoxConstraintsOenEA2s = j2;
        }
        int[] iArr5 = iArr;
        if (!mutableVector.isEmpty()) {
            i9 = i39;
            i10 = i38;
        } else {
            i9 = 0;
            i10 = 0;
        }
        return m599placeHelperBmaY500(measureScope, j, i9, i10, iArr2, mutableVector, flowLineMeasurePolicy, iArr5);
    }

    private static final Measurable safeNext(Iterator<? extends Measurable> it, FlowLineInfo info) {
        Measurable next;
        try {
            if (it instanceof ContextualFlowItemIterator) {
                Intrinsics.checkNotNull(info);
                next = ((ContextualFlowItemIterator) it).getNext$foundation_layout_release(info);
            } else {
                next = it.next();
            }
            return next;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static final int mainAxisMin(IntrinsicMeasurable $this$mainAxisMin, boolean isHorizontal, int crossAxisSize) {
        if (isHorizontal) {
            return $this$mainAxisMin.minIntrinsicWidth(crossAxisSize);
        }
        return $this$mainAxisMin.minIntrinsicHeight(crossAxisSize);
    }

    public static final int crossAxisMin(IntrinsicMeasurable $this$crossAxisMin, boolean isHorizontal, int mainAxisSize) {
        if (isHorizontal) {
            return $this$crossAxisMin.minIntrinsicHeight(mainAxisSize);
        }
        return $this$crossAxisMin.minIntrinsicWidth(mainAxisSize);
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_TOP() {
        return CROSS_AXIS_ALIGNMENT_TOP;
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_START() {
        return CROSS_AXIS_ALIGNMENT_START;
    }

    /* renamed from: measureAndCache-rqJ1uqs, reason: not valid java name */
    public static final long m598measureAndCacherqJ1uqs(Measurable $this$measureAndCache_u2drqJ1uqs, FlowLineMeasurePolicy measurePolicy, long constraints, Function1<? super Placeable, Unit> function1) {
        FlowLayoutData flowLayoutData;
        if (RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData($this$measureAndCache_u2drqJ1uqs)) == 0.0f) {
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData($this$measureAndCache_u2drqJ1uqs);
            if (((rowColumnParentData == null || (flowLayoutData = rowColumnParentData.getFlowLayoutData()) == null) ? null : Float.valueOf(flowLayoutData.getFillCrossAxisFraction())) == null) {
                Placeable placeable = $this$measureAndCache_u2drqJ1uqs.mo5491measureBRTryo0(constraints);
                function1.invoke(placeable);
                int mainAxis = measurePolicy.mainAxisSize(placeable);
                int crossAxis = measurePolicy.crossAxisSize(placeable);
                return IntIntPair.m18constructorimpl(mainAxis, crossAxis);
            }
        }
        int mainAxis2 = mainAxisMin($this$measureAndCache_u2drqJ1uqs, measurePolicy.isHorizontal(), Integer.MAX_VALUE);
        int crossAxis2 = crossAxisMin($this$measureAndCache_u2drqJ1uqs, measurePolicy.isHorizontal(), mainAxis2);
        return IntIntPair.m18constructorimpl(mainAxis2, crossAxis2);
    }

    /* renamed from: placeHelper-BmaY500, reason: not valid java name */
    public static final MeasureResult m599placeHelperBmaY500(MeasureScope $this$placeHelper_u2dBmaY500, long constraints, int mainAxisTotalSize, int crossAxisTotalSize, int[] crossAxisSizes, final MutableVector<MeasureResult> mutableVector, FlowLineMeasurePolicy measureHelper, int[] outPosition) {
        int totalCrossAxisSize;
        int layoutWidth;
        int layoutHeight;
        boolean isHorizontal = measureHelper.isHorizontal();
        Arrangement.Vertical verticalArrangement = measureHelper.getVerticalArrangement();
        Arrangement.Horizontal horizontalArrangement = measureHelper.getHorizontalArrangement();
        if (isHorizontal) {
            if (verticalArrangement == null) {
                throw new IllegalArgumentException("null verticalArrangement".toString());
            }
            int totalCrossAxisSpacing = $this$placeHelper_u2dBmaY500.mo358roundToPx0680j_4(verticalArrangement.getSpacing()) * (mutableVector.getSize() - 1);
            int totalCrossAxisSize2 = RangesKt.coerceIn(crossAxisTotalSize + totalCrossAxisSpacing, Constraints.m6580getMinHeightimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints));
            verticalArrangement.arrange($this$placeHelper_u2dBmaY500, totalCrossAxisSize2, crossAxisSizes, outPosition);
            totalCrossAxisSize = totalCrossAxisSize2;
        } else {
            if (horizontalArrangement == null) {
                throw new IllegalArgumentException("null horizontalArrangement".toString());
            }
            int totalCrossAxisSpacing2 = $this$placeHelper_u2dBmaY500.mo358roundToPx0680j_4(horizontalArrangement.getSpacing()) * (mutableVector.getSize() - 1);
            totalCrossAxisSize = RangesKt.coerceIn(crossAxisTotalSize + totalCrossAxisSpacing2, Constraints.m6580getMinHeightimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints));
            horizontalArrangement.arrange($this$placeHelper_u2dBmaY500, totalCrossAxisSize, crossAxisSizes, $this$placeHelper_u2dBmaY500.getLayoutDirection(), outPosition);
        }
        int finalMainAxisTotalSize = RangesKt.coerceIn(mainAxisTotalSize, Constraints.m6581getMinWidthimpl(constraints), Constraints.m6579getMaxWidthimpl(constraints));
        if (isHorizontal) {
            layoutWidth = finalMainAxisTotalSize;
            layoutHeight = totalCrossAxisSize;
        } else {
            layoutWidth = totalCrossAxisSize;
            layoutHeight = finalMainAxisTotalSize;
        }
        return MeasureScope.layout$default($this$placeHelper_u2dBmaY500, layoutWidth, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$placeHelper$5
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
                MutableVector this_$iv = mutableVector;
                int size$iv = this_$iv.getSize();
                if (size$iv <= 0) {
                    return;
                }
                int i$iv = 0;
                Object[] content$iv = this_$iv.getContent();
                do {
                    MeasureResult measureResult = (MeasureResult) content$iv[i$iv];
                    measureResult.placeChildren();
                    i$iv++;
                } while (i$iv < size$iv);
            }
        }, 4, null);
    }
}
