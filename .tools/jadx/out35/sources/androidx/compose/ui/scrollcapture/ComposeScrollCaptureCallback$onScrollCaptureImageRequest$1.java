package androidx.compose.ui.scrollcapture;

import android.graphics.Rect;
import android.view.ScrollCaptureSession;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.unit.IntRect;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ComposeScrollCaptureCallback.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1", f = "ComposeScrollCaptureCallback.android.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Rect $captureArea;
    final /* synthetic */ Consumer<Rect> $onComplete;
    final /* synthetic */ ScrollCaptureSession $session;
    int label;
    final /* synthetic */ ComposeScrollCaptureCallback this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1(ComposeScrollCaptureCallback composeScrollCaptureCallback, ScrollCaptureSession scrollCaptureSession, Rect rect, Consumer<Rect> consumer, Continuation<? super ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1> continuation) {
        super(2, continuation);
        this.this$0 = composeScrollCaptureCallback;
        this.$session = scrollCaptureSession;
        this.$captureArea = rect;
        this.$onComplete = consumer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1(this.this$0, this.$session, this.$captureArea, this.$onComplete, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ComposeScrollCaptureCallback$onScrollCaptureImageRequest$1 composeScrollCaptureCallback$onScrollCaptureImageRequest$1;
        Object onScrollCaptureImageRequest;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                composeScrollCaptureCallback$onScrollCaptureImageRequest$1 = this;
                composeScrollCaptureCallback$onScrollCaptureImageRequest$1.label = 1;
                onScrollCaptureImageRequest = composeScrollCaptureCallback$onScrollCaptureImageRequest$1.this$0.onScrollCaptureImageRequest(composeScrollCaptureCallback$onScrollCaptureImageRequest$1.$session, RectHelper_androidKt.toComposeIntRect(composeScrollCaptureCallback$onScrollCaptureImageRequest$1.$captureArea), composeScrollCaptureCallback$onScrollCaptureImageRequest$1);
                if (onScrollCaptureImageRequest != coroutine_suspended) {
                    $result = onScrollCaptureImageRequest;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                composeScrollCaptureCallback$onScrollCaptureImageRequest$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        IntRect result = (IntRect) $result;
        composeScrollCaptureCallback$onScrollCaptureImageRequest$1.$onComplete.accept(RectHelper_androidKt.toAndroidRect(result));
        return Unit.INSTANCE;
    }
}
