package com.example.shellshot;

import android.content.Context;
import com.example.shellshot.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ShellShotApp.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ShellShotAppKt$ShellShotApp$1$1", f = "ShellShotApp.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class ShellShotAppKt$ShellShotApp$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MainViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ShellShotAppKt$ShellShotApp$1$1(MainViewModel mainViewModel, Context context, Continuation<? super ShellShotAppKt$ShellShotApp$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = mainViewModel;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ShellShotAppKt$ShellShotApp$1$1(this.$viewModel, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ShellShotAppKt$ShellShotApp$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.$viewModel.onAppVisible(this.$context);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
