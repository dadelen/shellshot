package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Recomposer;
import androidx.lifecycle.LifecycleOwner;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: WindowRecomposer.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1", f = "WindowRecomposer.android.kt", i = {0}, l = {396}, m = "invokeSuspend", n = {"durationScaleJob"}, s = {"L$0"})
/* loaded from: classes13.dex */
final class WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Recomposer $recomposer;
    final /* synthetic */ WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 $self;
    final /* synthetic */ LifecycleOwner $source;
    final /* synthetic */ Ref.ObjectRef<MotionDurationScaleImpl> $systemDurationScaleSettingConsumer;
    final /* synthetic */ View $this_createLifecycleAwareWindowRecomposer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(Ref.ObjectRef<MotionDurationScaleImpl> objectRef, Recomposer recomposer, LifecycleOwner lifecycleOwner, WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2, View view, Continuation<? super WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1> continuation) {
        super(2, continuation);
        this.$systemDurationScaleSettingConsumer = objectRef;
        this.$recomposer = recomposer;
        this.$source = lifecycleOwner;
        this.$self = windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2;
        this.$this_createLifecycleAwareWindowRecomposer = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 = new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(this.$systemDurationScaleSettingConsumer, this.$recomposer, this.$source, this.$self, this.$this_createLifecycleAwareWindowRecomposer, continuation);
        windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.L$0 = obj;
        return windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0089: IF  (r4 I:??[int, boolean, OBJECT, ARRAY, byte, short, char] A[D('durationScaleJob' kotlinx.coroutines.Job)]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:24:0x008e, block:B:22:0x0089 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1, int] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Job durationScaleJob;
        WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1;
        Job job;
        Job durationScaleJob2;
        StateFlow durationScaleStateFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            switch (r1) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 = this;
                    CoroutineScope $this$launch = (CoroutineScope) windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.L$0;
                    MotionDurationScaleImpl it = windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.$systemDurationScaleSettingConsumer.element;
                    if (it != null) {
                        durationScaleStateFlow = WindowRecomposer_androidKt.getAnimationScaleFlowFor(windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.$this_createLifecycleAwareWindowRecomposer.getContext().getApplicationContext());
                        it.setScaleFactor(((Number) durationScaleStateFlow.getValue()).floatValue());
                        job = BuildersKt__Builders_commonKt.launch$default($this$launch, null, null, new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1$1$1(durationScaleStateFlow, it, null), 3, null);
                    } else {
                        job = null;
                    }
                    durationScaleJob2 = job;
                    windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.L$0 = durationScaleJob2;
                    windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.label = 1;
                    if (windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.$recomposer.runRecomposeAndApplyChanges(windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 = this;
                    durationScaleJob2 = (Job) windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (durationScaleJob2 != null) {
                Job.DefaultImpls.cancel$default(durationScaleJob2, (CancellationException) null, 1, (Object) null);
            }
            windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.$source.getLifecycleRegistry().removeObserver(windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.$self);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            if (durationScaleJob != null) {
                Job.DefaultImpls.cancel$default(durationScaleJob, (CancellationException) null, 1, (Object) null);
            }
            r1.$source.getLifecycleRegistry().removeObserver(r1.$self);
            throw th;
        }
    }
}
