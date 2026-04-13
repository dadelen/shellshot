package androidx.compose.foundation.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* compiled from: PagerState.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.pager.PagerStateKt$animateScrollToPage$2", f = "PagerState.kt", i = {}, l = {953}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class PagerStateKt$animateScrollToPage$2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimationSpec<Float> $animationSpec;
    final /* synthetic */ int $targetPage;
    final /* synthetic */ float $targetPageOffsetToSnappedPosition;
    final /* synthetic */ LazyLayoutAnimateScrollScope $this_animateScrollToPage;
    final /* synthetic */ Function2<ScrollScope, Integer, Unit> $updateTargetPage;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    PagerStateKt$animateScrollToPage$2(Function2<? super ScrollScope, ? super Integer, Unit> function2, int i, LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, float f, AnimationSpec<Float> animationSpec, Continuation<? super PagerStateKt$animateScrollToPage$2> continuation) {
        super(2, continuation);
        this.$updateTargetPage = function2;
        this.$targetPage = i;
        this.$this_animateScrollToPage = lazyLayoutAnimateScrollScope;
        this.$targetPageOffsetToSnappedPosition = f;
        this.$animationSpec = animationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PagerStateKt$animateScrollToPage$2 pagerStateKt$animateScrollToPage$2 = new PagerStateKt$animateScrollToPage$2(this.$updateTargetPage, this.$targetPage, this.$this_animateScrollToPage, this.$targetPageOffsetToSnappedPosition, this.$animationSpec, continuation);
        pagerStateKt$animateScrollToPage$2.L$0 = obj;
        return pagerStateKt$animateScrollToPage$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
        return ((PagerStateKt$animateScrollToPage$2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int preJumpPosition;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final ScrollScope $this$scroll = (ScrollScope) this.L$0;
                this.$updateTargetPage.invoke($this$scroll, Boxing.boxInt(this.$targetPage));
                boolean forward = this.$targetPage > this.$this_animateScrollToPage.getFirstVisibleItemIndex();
                int visiblePages = (this.$this_animateScrollToPage.getLastVisibleItemIndex() - this.$this_animateScrollToPage.getFirstVisibleItemIndex()) + 1;
                if (((forward && this.$targetPage > this.$this_animateScrollToPage.getLastVisibleItemIndex()) || (!forward && this.$targetPage < this.$this_animateScrollToPage.getFirstVisibleItemIndex())) && Math.abs(this.$targetPage - this.$this_animateScrollToPage.getFirstVisibleItemIndex()) >= 3) {
                    int i = this.$targetPage;
                    if (forward) {
                        preJumpPosition = RangesKt.coerceAtLeast(i - visiblePages, this.$this_animateScrollToPage.getFirstVisibleItemIndex());
                    } else {
                        preJumpPosition = RangesKt.coerceAtMost(i + visiblePages, this.$this_animateScrollToPage.getFirstVisibleItemIndex());
                    }
                    this.$this_animateScrollToPage.snapToItem($this$scroll, preJumpPosition, 0);
                }
                float displacement = this.$this_animateScrollToPage.calculateDistanceTo(this.$targetPage) + this.$targetPageOffsetToSnappedPosition;
                final Ref.FloatRef previousValue = new Ref.FloatRef();
                this.label = 1;
                if (SuspendAnimationKt.animate$default(0.0f, displacement, 0.0f, this.$animationSpec, new Function2<Float, Float, Unit>() { // from class: androidx.compose.foundation.pager.PagerStateKt$animateScrollToPage$2.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Float f, Float f2) {
                        invoke(f.floatValue(), f2.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float currentValue, float f) {
                        float delta = currentValue - Ref.FloatRef.this.element;
                        float consumed = $this$scroll.scrollBy(delta);
                        Ref.FloatRef.this.element += consumed;
                    }
                }, this, 4, null) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
