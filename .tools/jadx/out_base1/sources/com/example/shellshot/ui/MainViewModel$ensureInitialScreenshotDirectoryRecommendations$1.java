package com.example.shellshot.ui;

import com.example.shellshot.data.AppContainer;
import com.example.shellshot.data.AppSettings;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$ensureInitialScreenshotDirectoryRecommendations$1", f = "MainViewModel.kt", i = {}, l = {265}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class MainViewModel$ensureInitialScreenshotDirectoryRecommendations$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MainViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$ensureInitialScreenshotDirectoryRecommendations$1(MainViewModel mainViewModel, Continuation<? super MainViewModel$ensureInitialScreenshotDirectoryRecommendations$1> continuation) {
        super(2, continuation);
        this.this$0 = mainViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$ensureInitialScreenshotDirectoryRecommendations$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$ensureInitialScreenshotDirectoryRecommendations$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AppContainer appContainer;
        Object currentSettings;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                appContainer = this.this$0.appContainer;
                this.label = 1;
                currentSettings = appContainer.getAppPrefs().currentSettings(this);
                if (currentSettings == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                currentSettings = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AppSettings settings = (AppSettings) currentSettings;
        if (!settings.getScreenshotDirectoryRecommendationsInitialized()) {
            this.this$0.performScreenshotDirectoryRecommendationsRefresh(true);
        }
        return Unit.INSTANCE;
    }
}
