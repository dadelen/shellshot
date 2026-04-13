package androidx.compose.material;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.ClosedFloatingPointRange;

/* compiled from: Slider.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
/* synthetic */ class SliderKt$Slider$2$2$1 extends FunctionReferenceImpl implements Function1<Float, Float> {
    final /* synthetic */ Ref.FloatRef $maxPx;
    final /* synthetic */ Ref.FloatRef $minPx;
    final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SliderKt$Slider$2$2$1(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef floatRef, Ref.FloatRef floatRef2) {
        super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/internal/Ref$FloatRef;Lkotlin/jvm/internal/Ref$FloatRef;F)F", 0);
        this.$valueRange = closedFloatingPointRange;
        this.$minPx = floatRef;
        this.$maxPx = floatRef2;
    }

    public final Float invoke(float p0) {
        float invoke$scaleToOffset;
        invoke$scaleToOffset = SliderKt$Slider$2.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, p0);
        return Float.valueOf(invoke$scaleToOffset);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Float invoke(Float f) {
        return invoke(f.floatValue());
    }
}
