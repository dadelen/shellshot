package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.RulerScope;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PagerMeasureResult.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B¿\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0007\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u000f\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u000f\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0002\u0010\u001fJ\t\u0010Y\u001a\u00020JH\u0096\u0001J\u000e\u0010Z\u001a\u00020\u000f2\u0006\u0010[\u001a\u00020\u0007R\u0014\u0010\t\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001e\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00070#X\u0096\u0005¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010!R\u0014\u0010\u0010\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0011\u0010*\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u001a\u0010\u0016\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010,\"\u0004\b.\u0010/R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u00109R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u00103R\u001a\u0010\u0015\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010!\"\u0004\b=\u0010>R\u0012\u0010?\u001a\u00020\u0007X\u0096\u0005¢\u0006\u0006\u001a\u0004\b@\u0010!R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010!R\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010!R\u0011\u0010\u001a\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bE\u0010,R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010,R'\u0010G\u001a\u0015\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020J\u0018\u00010H¢\u0006\u0002\bK8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0014\u0010\r\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010!R\u001a\u0010Q\u001a\u00020R8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0014\u0010\f\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010!R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u00109R\u0012\u0010W\u001a\u00020\u0007X\u0096\u0005¢\u0006\u0006\u001a\u0004\bX\u0010!\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\\"}, d2 = {"Landroidx/compose/foundation/pager/PagerMeasureResult;", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "Landroidx/compose/ui/layout/MeasureResult;", "visiblePagesInfo", "", "Landroidx/compose/foundation/pager/MeasuredPage;", "pageSize", "", "pageSpacing", "afterContentPadding", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "viewportStartOffset", "viewportEndOffset", "reverseLayout", "", "beyondViewportPageCount", "firstVisiblePage", "currentPage", "currentPageOffsetFraction", "", "firstVisiblePageScrollOffset", "canScrollForward", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "measureResult", "remeasureNeeded", "extraPagesBefore", "extraPagesAfter", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Ljava/util/List;IIILandroidx/compose/foundation/gestures/Orientation;IIZILandroidx/compose/foundation/pager/MeasuredPage;Landroidx/compose/foundation/pager/MeasuredPage;FIZLandroidx/compose/foundation/gestures/snapping/SnapPosition;Landroidx/compose/ui/layout/MeasureResult;ZLjava/util/List;Ljava/util/List;Lkotlinx/coroutines/CoroutineScope;)V", "getAfterContentPadding", "()I", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "getAlignmentLines", "()Ljava/util/Map;", "beforeContentPadding", "getBeforeContentPadding", "getBeyondViewportPageCount", "canScrollBackward", "getCanScrollBackward", "()Z", "getCanScrollForward", "setCanScrollForward", "(Z)V", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "getCurrentPage", "()Landroidx/compose/foundation/pager/MeasuredPage;", "getCurrentPageOffsetFraction", "()F", "setCurrentPageOffsetFraction", "(F)V", "getExtraPagesAfter", "()Ljava/util/List;", "getExtraPagesBefore", "getFirstVisiblePage", "getFirstVisiblePageScrollOffset", "setFirstVisiblePageScrollOffset", "(I)V", "height", "getHeight", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "getPageSize", "getPageSpacing", "getRemeasureNeeded", "getReverseLayout", "rulers", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "", "Lkotlin/ExtensionFunctionType;", "getRulers", "()Lkotlin/jvm/functions/Function1;", "getSnapPosition", "()Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "getViewportEndOffset", "viewportSize", "Landroidx/compose/ui/unit/IntSize;", "getViewportSize-YbymL2g", "()J", "getViewportStartOffset", "getVisiblePagesInfo", "width", "getWidth", "placeChildren", "tryToApplyScrollWithoutRemeasure", "delta", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerMeasureResult implements PagerLayoutInfo, MeasureResult {
    public static final int $stable = 8;
    private final /* synthetic */ MeasureResult $$delegate_0;
    private final int afterContentPadding;
    private final int beyondViewportPageCount;
    private boolean canScrollForward;
    private final CoroutineScope coroutineScope;
    private final MeasuredPage currentPage;
    private float currentPageOffsetFraction;
    private final List<MeasuredPage> extraPagesAfter;
    private final List<MeasuredPage> extraPagesBefore;
    private final MeasuredPage firstVisiblePage;
    private int firstVisiblePageScrollOffset;
    private final Orientation orientation;
    private final int pageSize;
    private final int pageSpacing;
    private final boolean remeasureNeeded;
    private final boolean reverseLayout;
    private final SnapPosition snapPosition;
    private final int viewportEndOffset;
    private final int viewportStartOffset;
    private final List<MeasuredPage> visiblePagesInfo;

    @Override // androidx.compose.ui.layout.MeasureResult
    public Map<AlignmentLine, Integer> getAlignmentLines() {
        return this.$$delegate_0.getAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    /* renamed from: getHeight */
    public int get$h() {
        return this.$$delegate_0.get$h();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public Function1<RulerScope, Unit> getRulers() {
        return this.$$delegate_0.getRulers();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    /* renamed from: getWidth */
    public int get$w() {
        return this.$$delegate_0.get$w();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public void placeChildren() {
        this.$$delegate_0.placeChildren();
    }

    public PagerMeasureResult(List<MeasuredPage> list, int pageSize, int pageSpacing, int afterContentPadding, Orientation orientation, int viewportStartOffset, int viewportEndOffset, boolean reverseLayout, int beyondViewportPageCount, MeasuredPage firstVisiblePage, MeasuredPage currentPage, float currentPageOffsetFraction, int firstVisiblePageScrollOffset, boolean canScrollForward, SnapPosition snapPosition, MeasureResult measureResult, boolean remeasureNeeded, List<MeasuredPage> list2, List<MeasuredPage> list3, CoroutineScope coroutineScope) {
        this.visiblePagesInfo = list;
        this.pageSize = pageSize;
        this.pageSpacing = pageSpacing;
        this.afterContentPadding = afterContentPadding;
        this.orientation = orientation;
        this.viewportStartOffset = viewportStartOffset;
        this.viewportEndOffset = viewportEndOffset;
        this.reverseLayout = reverseLayout;
        this.beyondViewportPageCount = beyondViewportPageCount;
        this.firstVisiblePage = firstVisiblePage;
        this.currentPage = currentPage;
        this.currentPageOffsetFraction = currentPageOffsetFraction;
        this.firstVisiblePageScrollOffset = firstVisiblePageScrollOffset;
        this.canScrollForward = canScrollForward;
        this.snapPosition = snapPosition;
        this.remeasureNeeded = remeasureNeeded;
        this.extraPagesBefore = list2;
        this.extraPagesAfter = list3;
        this.coroutineScope = coroutineScope;
        this.$$delegate_0 = measureResult;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ PagerMeasureResult(java.util.List r23, int r24, int r25, int r26, androidx.compose.foundation.gestures.Orientation r27, int r28, int r29, boolean r30, int r31, androidx.compose.foundation.pager.MeasuredPage r32, androidx.compose.foundation.pager.MeasuredPage r33, float r34, int r35, boolean r36, androidx.compose.foundation.gestures.snapping.SnapPosition r37, androidx.compose.ui.layout.MeasureResult r38, boolean r39, java.util.List r40, java.util.List r41, kotlinx.coroutines.CoroutineScope r42, int r43, kotlin.jvm.internal.DefaultConstructorMarker r44) {
        /*
            r22 = this;
            r0 = 131072(0x20000, float:1.83671E-40)
            r0 = r43 & r0
            if (r0 == 0) goto Ld
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r19 = r0
            goto Lf
        Ld:
            r19 = r40
        Lf:
            r0 = 262144(0x40000, float:3.67342E-40)
            r0 = r43 & r0
            if (r0 == 0) goto L1c
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r20 = r0
            goto L1e
        L1c:
            r20 = r41
        L1e:
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r27
            r7 = r28
            r8 = r29
            r9 = r30
            r10 = r31
            r11 = r32
            r12 = r33
            r13 = r34
            r14 = r35
            r15 = r36
            r16 = r37
            r17 = r38
            r18 = r39
            r21 = r42
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerMeasureResult.<init>(java.util.List, int, int, int, androidx.compose.foundation.gestures.Orientation, int, int, boolean, int, androidx.compose.foundation.pager.MeasuredPage, androidx.compose.foundation.pager.MeasuredPage, float, int, boolean, androidx.compose.foundation.gestures.snapping.SnapPosition, androidx.compose.ui.layout.MeasureResult, boolean, java.util.List, java.util.List, kotlinx.coroutines.CoroutineScope, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public List<MeasuredPage> getVisiblePagesInfo() {
        return this.visiblePagesInfo;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public int getPageSize() {
        return this.pageSize;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public int getPageSpacing() {
        return this.pageSpacing;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public int getAfterContentPadding() {
        return this.afterContentPadding;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public int getViewportStartOffset() {
        return this.viewportStartOffset;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public int getViewportEndOffset() {
        return this.viewportEndOffset;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public boolean getReverseLayout() {
        return this.reverseLayout;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public int getBeyondViewportPageCount() {
        return this.beyondViewportPageCount;
    }

    public final MeasuredPage getFirstVisiblePage() {
        return this.firstVisiblePage;
    }

    public final MeasuredPage getCurrentPage() {
        return this.currentPage;
    }

    public final float getCurrentPageOffsetFraction() {
        return this.currentPageOffsetFraction;
    }

    public final void setCurrentPageOffsetFraction(float f) {
        this.currentPageOffsetFraction = f;
    }

    public final int getFirstVisiblePageScrollOffset() {
        return this.firstVisiblePageScrollOffset;
    }

    public final void setFirstVisiblePageScrollOffset(int i) {
        this.firstVisiblePageScrollOffset = i;
    }

    public final boolean getCanScrollForward() {
        return this.canScrollForward;
    }

    public final void setCanScrollForward(boolean z) {
        this.canScrollForward = z;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public SnapPosition getSnapPosition() {
        return this.snapPosition;
    }

    public final boolean getRemeasureNeeded() {
        return this.remeasureNeeded;
    }

    public final List<MeasuredPage> getExtraPagesBefore() {
        return this.extraPagesBefore;
    }

    public final List<MeasuredPage> getExtraPagesAfter() {
        return this.extraPagesAfter;
    }

    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    /* renamed from: getViewportSize-YbymL2g */
    public long mo911getViewportSizeYbymL2g() {
        return IntSizeKt.IntSize(get$w(), get$h());
    }

    @Override // androidx.compose.foundation.pager.PagerLayoutInfo
    public int getBeforeContentPadding() {
        return -getViewportStartOffset();
    }

    public final boolean getCanScrollBackward() {
        MeasuredPage measuredPage = this.firstVisiblePage;
        return ((measuredPage != null ? measuredPage.getIndex() : 0) == 0 && this.firstVisiblePageScrollOffset == 0) ? false : true;
    }

    public final boolean tryToApplyScrollWithoutRemeasure(int delta) {
        float deltaFraction;
        int deltaToFirstItemChange;
        int pageSizeWithSpacing = getPageSize() + getPageSpacing();
        if (!this.remeasureNeeded && !getVisiblePagesInfo().isEmpty() && this.firstVisiblePage != null) {
            int i = this.firstVisiblePageScrollOffset - delta;
            if (i >= 0 && i < pageSizeWithSpacing) {
                if (pageSizeWithSpacing != 0) {
                    deltaFraction = delta / pageSizeWithSpacing;
                } else {
                    deltaFraction = 0.0f;
                }
                float newCurrentPageOffsetFraction = this.currentPageOffsetFraction - deltaFraction;
                if (this.currentPage == null || newCurrentPageOffsetFraction >= 0.5f || newCurrentPageOffsetFraction <= -0.5f) {
                    return false;
                }
                MeasuredPage first = (MeasuredPage) CollectionsKt.first((List) getVisiblePagesInfo());
                MeasuredPage last = (MeasuredPage) CollectionsKt.last((List) getVisiblePagesInfo());
                if (delta < 0) {
                    int deltaToFirstItemChange2 = (first.getOffset() + pageSizeWithSpacing) - getViewportStartOffset();
                    int deltaToLastItemChange = (last.getOffset() + pageSizeWithSpacing) - getViewportEndOffset();
                    deltaToFirstItemChange = Math.min(deltaToFirstItemChange2, deltaToLastItemChange) > (-delta) ? 1 : 0;
                } else {
                    int deltaToFirstItemChange3 = getViewportStartOffset() - first.getOffset();
                    int deltaToLastItemChange2 = getViewportEndOffset() - last.getOffset();
                    deltaToFirstItemChange = Math.min(deltaToFirstItemChange3, deltaToLastItemChange2) > delta ? 1 : 0;
                }
                if (deltaToFirstItemChange == 0) {
                    return false;
                }
                this.currentPageOffsetFraction -= deltaFraction;
                this.firstVisiblePageScrollOffset -= delta;
                List $this$fastForEach$iv = getVisiblePagesInfo();
                int size = $this$fastForEach$iv.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                    MeasuredPage it = (MeasuredPage) item$iv;
                    it.applyScrollDelta(delta);
                }
                List $this$fastForEach$iv2 = this.extraPagesBefore;
                int size2 = $this$fastForEach$iv2.size();
                for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                    Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                    MeasuredPage it2 = (MeasuredPage) item$iv2;
                    it2.applyScrollDelta(delta);
                }
                List $this$fastForEach$iv3 = this.extraPagesAfter;
                int size3 = $this$fastForEach$iv3.size();
                for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
                    Object item$iv3 = $this$fastForEach$iv3.get(index$iv3);
                    MeasuredPage it3 = (MeasuredPage) item$iv3;
                    it3.applyScrollDelta(delta);
                }
                if (!this.canScrollForward && delta > 0) {
                    this.canScrollForward = true;
                }
                return true;
            }
        }
        return false;
    }
}
