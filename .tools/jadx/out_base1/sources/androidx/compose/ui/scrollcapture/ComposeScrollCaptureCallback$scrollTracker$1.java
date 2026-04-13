package androidx.compose.ui.scrollcapture;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: ComposeScrollCaptureCallback.android.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "delta"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$scrollTracker$1", f = "ComposeScrollCaptureCallback.android.kt", i = {0}, l = {85}, m = "invokeSuspend", n = {"reverseScrolling"}, s = {"Z$0"})
/* loaded from: classes13.dex */
final class ComposeScrollCaptureCallback$scrollTracker$1 extends SuspendLambda implements Function2<Float, Continuation<? super Float>, Object> {
    /* synthetic */ float F$0;
    boolean Z$0;
    int label;
    final /* synthetic */ ComposeScrollCaptureCallback this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ComposeScrollCaptureCallback$scrollTracker$1(ComposeScrollCaptureCallback composeScrollCaptureCallback, Continuation<? super ComposeScrollCaptureCallback$scrollTracker$1> continuation) {
        super(2, continuation);
        this.this$0 = composeScrollCaptureCallback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ComposeScrollCaptureCallback$scrollTracker$1 composeScrollCaptureCallback$scrollTracker$1 = new ComposeScrollCaptureCallback$scrollTracker$1(this.this$0, continuation);
        composeScrollCaptureCallback$scrollTracker$1.F$0 = ((Number) obj).floatValue();
        return composeScrollCaptureCallback$scrollTracker$1;
    }

    public final Object invoke(float f, Continuation<? super Float> continuation) {
        return ((ComposeScrollCaptureCallback$scrollTracker$1) create(Float.valueOf(f), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Float f, Continuation<? super Float> continuation) {
        return invoke(f.floatValue(), continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SemanticsNode semanticsNode;
        SemanticsNode semanticsNode2;
        boolean reverseScrolling;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                float delta = this.F$0;
                semanticsNode = this.this$0.node;
                Function2 scrollByOffset = ScrollCapture_androidKt.getScrollCaptureScrollByAction(semanticsNode);
                if (scrollByOffset != null) {
                    semanticsNode2 = this.this$0.node;
                    reverseScrolling = ((ScrollAxisRange) semanticsNode2.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getVerticalScrollAxisRange())).getReverseScrolling();
                    if (reverseScrolling) {
                        delta = -delta;
                    }
                    Offset m3911boximpl = Offset.m3911boximpl(OffsetKt.Offset(0.0f, delta));
                    this.Z$0 = reverseScrolling;
                    this.label = 1;
                    Object invoke = scrollByOffset.invoke(m3911boximpl, this);
                    if (invoke != coroutine_suspended) {
                        $result = invoke;
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                } else {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
                    throw new KotlinNothingValueException();
                }
            case 1:
                boolean reverseScrolling2 = this.Z$0;
                ResultKt.throwOnFailure($result);
                reverseScrolling = reverseScrolling2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        long consumed = ((Offset) $result).getPackedValue();
        return Boxing.boxFloat(reverseScrolling ? -Offset.m3923getYimpl(consumed) : Offset.m3923getYimpl(consumed));
    }
}
