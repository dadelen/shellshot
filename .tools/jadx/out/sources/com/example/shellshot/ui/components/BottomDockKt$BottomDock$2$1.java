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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: BottomDock.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.components.BottomDockKt$BottomDock$2$1", f = "BottomDock.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class BottomDockKt$BottomDock$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NavItem $currentItem;
    final /* synthetic */ MutableState<String> $previousSelectedId$delegate;
    final /* synthetic */ MutableState<Boolean> $sliderVisible$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BottomDockKt$BottomDock$2$1(NavItem navItem, MutableState<String> mutableState, MutableState<Boolean> mutableState2, Continuation<? super BottomDockKt$BottomDock$2$1> continuation) {
        super(2, continuation);
        this.$currentItem = navItem;
        this.$previousSelectedId$delegate = mutableState;
        this.$sliderVisible$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BottomDockKt$BottomDock$2$1(this.$currentItem, this.$previousSelectedId$delegate, this.$sliderVisible$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BottomDockKt$BottomDock$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        String BottomDock$lambda$5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                BottomDock$lambda$5 = BottomDockKt.BottomDock$lambda$5(this.$previousSelectedId$delegate);
                if (!Intrinsics.areEqual(BottomDock$lambda$5, this.$currentItem.getId())) {
                    this.$previousSelectedId$delegate.setValue(this.$currentItem.getId());
                    BottomDockKt.BottomDock$lambda$9(this.$sliderVisible$delegate, true);
                    this.label = 1;
                    if (DelayKt.delay(280L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    BottomDockKt.BottomDock$lambda$9(this.$sliderVisible$delegate, false);
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                BottomDockKt.BottomDock$lambda$9(this.$sliderVisible$delegate, false);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
