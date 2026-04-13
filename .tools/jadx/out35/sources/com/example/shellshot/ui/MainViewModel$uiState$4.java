package com.example.shellshot.ui;

import com.example.shellshot.media.ScreenshotDirectoryRecommendation;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/ui/MainUiState;", "state", "recommendedDirectories", "", "Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$uiState$4", f = "MainViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class MainViewModel$uiState$4 extends SuspendLambda implements Function3<MainUiState, List<? extends ScreenshotDirectoryRecommendation>, Continuation<? super MainUiState>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    MainViewModel$uiState$4(Continuation<? super MainViewModel$uiState$4> continuation) {
        super(3, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(MainUiState mainUiState, List<ScreenshotDirectoryRecommendation> list, Continuation<? super MainUiState> continuation) {
        MainViewModel$uiState$4 mainViewModel$uiState$4 = new MainViewModel$uiState$4(continuation);
        mainViewModel$uiState$4.L$0 = mainUiState;
        mainViewModel$uiState$4.L$1 = list;
        return mainViewModel$uiState$4.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(MainUiState mainUiState, List<? extends ScreenshotDirectoryRecommendation> list, Continuation<? super MainUiState> continuation) {
        return invoke2(mainUiState, (List<ScreenshotDirectoryRecommendation>) list, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MainUiState state = (MainUiState) this.L$0;
        List recommendedDirectories = (List) this.L$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return MainUiState.copy$default(state, null, null, false, null, null, null, false, false, null, false, false, false, false, null, null, null, false, null, null, recommendedDirectories, false, null, 3670015, null);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
