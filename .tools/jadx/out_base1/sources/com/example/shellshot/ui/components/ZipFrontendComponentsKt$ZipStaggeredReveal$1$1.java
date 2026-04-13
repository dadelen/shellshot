package com.example.shellshot.ui.components;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: ZipFrontendComponents.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.components.ZipFrontendComponentsKt$ZipStaggeredReveal$1$1", f = "ZipFrontendComponents.kt", i = {}, l = {328}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class ZipFrontendComponentsKt$ZipStaggeredReveal$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ MutableState<Boolean> $visible$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ZipFrontendComponentsKt$ZipStaggeredReveal$1$1(int i, MutableState<Boolean> mutableState, Continuation<? super ZipFrontendComponentsKt$ZipStaggeredReveal$1$1> continuation) {
        super(2, continuation);
        this.$index = i;
        this.$visible$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZipFrontendComponentsKt$ZipStaggeredReveal$1$1(this.$index, this.$visible$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ZipFrontendComponentsKt$ZipStaggeredReveal$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (DelayKt.delay(this.$index * 80, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ZipFrontendComponentsKt.ZipStaggeredReveal$lambda$16(this.$visible$delegate, true);
        return Unit.INSTANCE;
    }
}
