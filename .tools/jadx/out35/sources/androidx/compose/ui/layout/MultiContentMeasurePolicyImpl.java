package androidx.compose.ui.layout;

import androidx.compose.ui.node.MeasureScopeWithLayoutNodeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MultiContentMeasurePolicy.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\"\u0010\u0011\u001a\u00020\u000e*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\"\u0010\u0017\u001a\u00020\u000e*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J,\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J\"\u0010!\u001a\u00020\u000e*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\"\u0010\"\u001a\u00020\u000e*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"Landroidx/compose/ui/layout/MultiContentMeasurePolicyImpl;", "Landroidx/compose/ui/layout/MeasurePolicy;", "measurePolicy", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "(Landroidx/compose/ui/layout/MultiContentMeasurePolicy;)V", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "maxIntrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final /* data */ class MultiContentMeasurePolicyImpl implements MeasurePolicy {
    public static final int $stable = 0;
    private final MultiContentMeasurePolicy measurePolicy;

    public static /* synthetic */ MultiContentMeasurePolicyImpl copy$default(MultiContentMeasurePolicyImpl multiContentMeasurePolicyImpl, MultiContentMeasurePolicy multiContentMeasurePolicy, int i, Object obj) {
        if ((i & 1) != 0) {
            multiContentMeasurePolicy = multiContentMeasurePolicyImpl.measurePolicy;
        }
        return multiContentMeasurePolicyImpl.copy(multiContentMeasurePolicy);
    }

    /* renamed from: component1, reason: from getter */
    public final MultiContentMeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }

    public final MultiContentMeasurePolicyImpl copy(MultiContentMeasurePolicy measurePolicy) {
        return new MultiContentMeasurePolicyImpl(measurePolicy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MultiContentMeasurePolicyImpl) && Intrinsics.areEqual(this.measurePolicy, ((MultiContentMeasurePolicyImpl) other).measurePolicy);
    }

    public int hashCode() {
        return this.measurePolicy.hashCode();
    }

    public String toString() {
        return "MultiContentMeasurePolicyImpl(measurePolicy=" + this.measurePolicy + ')';
    }

    public MultiContentMeasurePolicyImpl(MultiContentMeasurePolicy measurePolicy) {
        this.measurePolicy = measurePolicy;
    }

    public final MultiContentMeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo32measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
        MultiContentMeasurePolicy $this$measure_3p2s80s_u24lambda_u240 = this.measurePolicy;
        return $this$measure_3p2s80s_u24lambda_u240.mo620measure3p2s80s($this$measure_u2d3p2s80s, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren($this$measure_u2d3p2s80s), constraints);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        MultiContentMeasurePolicy $this$minIntrinsicWidth_u24lambda_u241 = this.measurePolicy;
        return $this$minIntrinsicWidth_u24lambda_u241.minIntrinsicWidth($this$minIntrinsicWidth, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren($this$minIntrinsicWidth), height);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        MultiContentMeasurePolicy $this$minIntrinsicHeight_u24lambda_u242 = this.measurePolicy;
        return $this$minIntrinsicHeight_u24lambda_u242.minIntrinsicHeight($this$minIntrinsicHeight, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren($this$minIntrinsicHeight), width);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        MultiContentMeasurePolicy $this$maxIntrinsicWidth_u24lambda_u243 = this.measurePolicy;
        return $this$maxIntrinsicWidth_u24lambda_u243.maxIntrinsicWidth($this$maxIntrinsicWidth, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren($this$maxIntrinsicWidth), height);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        MultiContentMeasurePolicy $this$maxIntrinsicHeight_u24lambda_u244 = this.measurePolicy;
        return $this$maxIntrinsicHeight_u24lambda_u244.maxIntrinsicHeight($this$maxIntrinsicHeight, MeasureScopeWithLayoutNodeKt.getChildrenOfVirtualChildren($this$maxIntrinsicHeight), width);
    }
}
