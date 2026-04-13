package com.example.shellshot;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.shellshot.ui.MainUiState;
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
@DebugMetadata(c = "com.example.shellshot.ShellShotAppKt$ShellShotApp$2$1", f = "ShellShotApp.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class ShellShotAppKt$ShellShotApp$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<AppTab> $currentTab$delegate;
    final /* synthetic */ State<MainUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ShellShotAppKt$ShellShotApp$2$1(State<MainUiState> state, MutableState<AppTab> mutableState, Continuation<? super ShellShotAppKt$ShellShotApp$2$1> continuation) {
        super(2, continuation);
        this.$uiState$delegate = state;
        this.$currentTab$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ShellShotAppKt$ShellShotApp$2$1(this.$uiState$delegate, this.$currentTab$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ShellShotAppKt$ShellShotApp$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MainUiState ShellShotApp$lambda$0;
        AppTab ShellShotApp$lambda$3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ShellShotApp$lambda$0 = ShellShotAppKt.ShellShotApp$lambda$0(this.$uiState$delegate);
                if (!ShellShotApp$lambda$0.getSettings().getDebugModeEnabled()) {
                    ShellShotApp$lambda$3 = ShellShotAppKt.ShellShotApp$lambda$3(this.$currentTab$delegate);
                    if (ShellShotApp$lambda$3 == AppTab.Logs) {
                        this.$currentTab$delegate.setValue(AppTab.Home);
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
