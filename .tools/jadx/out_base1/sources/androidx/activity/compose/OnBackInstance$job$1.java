package androidx.activity.compose;

import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedCallback;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PredictiveBackHandler.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.activity.compose.OnBackInstance$job$1", f = "PredictiveBackHandler.kt", i = {0}, l = {121}, m = "invokeSuspend", n = {"completed"}, s = {"L$0"})
/* loaded from: classes.dex */
final class OnBackInstance$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OnBackPressedCallback $callback;
    final /* synthetic */ Function2<Flow<BackEventCompat>, Continuation<? super Unit>, Object> $onBack;
    Object L$0;
    int label;
    final /* synthetic */ OnBackInstance this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    OnBackInstance$job$1(OnBackPressedCallback onBackPressedCallback, Function2<? super Flow<BackEventCompat>, ? super Continuation<? super Unit>, ? extends Object> function2, OnBackInstance onBackInstance, Continuation<? super OnBackInstance$job$1> continuation) {
        super(2, continuation);
        this.$callback = onBackPressedCallback;
        this.$onBack = function2;
        this.this$0 = onBackInstance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnBackInstance$job$1(this.$callback, this.$onBack, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnBackInstance$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0058  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L1b;
                case 1: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L12:
            r0 = r7
            java.lang.Object r1 = r0.L$0
            kotlin.jvm.internal.Ref$BooleanRef r1 = (kotlin.jvm.internal.Ref.BooleanRef) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L54
        L1b:
            kotlin.ResultKt.throwOnFailure(r8)
            r1 = r7
            androidx.activity.OnBackPressedCallback r2 = r1.$callback
            boolean r2 = r2.getIsEnabled()
            if (r2 == 0) goto L69
            kotlin.jvm.internal.Ref$BooleanRef r2 = new kotlin.jvm.internal.Ref$BooleanRef
            r2.<init>()
            kotlin.jvm.functions.Function2<kotlinx.coroutines.flow.Flow<androidx.activity.BackEventCompat>, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r3 = r1.$onBack
            androidx.activity.compose.OnBackInstance r4 = r1.this$0
            kotlinx.coroutines.channels.Channel r4 = r4.getChannel()
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlinx.coroutines.flow.Flow r4 = kotlinx.coroutines.flow.FlowKt.consumeAsFlow(r4)
            androidx.activity.compose.OnBackInstance$job$1$1 r5 = new androidx.activity.compose.OnBackInstance$job$1$1
            r6 = 0
            r5.<init>(r2, r6)
            kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5
            kotlinx.coroutines.flow.Flow r4 = kotlinx.coroutines.flow.FlowKt.onCompletion(r4, r5)
            r1.L$0 = r2
            r5 = 1
            r1.label = r5
            java.lang.Object r3 = r3.invoke(r4, r1)
            if (r3 != r0) goto L52
            return r0
        L52:
            r0 = r1
            r1 = r2
        L54:
            boolean r2 = r1.element
            if (r2 == 0) goto L5a
            r1 = r0
            goto L69
        L5a:
            r2 = 0
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "You must collect the progress flow"
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L69:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.compose.OnBackInstance$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: PredictiveBackHandler.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/activity/BackEventCompat;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.activity.compose.OnBackInstance$job$1$1", f = "PredictiveBackHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.activity.compose.OnBackInstance$job$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super BackEventCompat>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $completed;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.BooleanRef booleanRef, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$completed = booleanRef;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super BackEventCompat> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$completed, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.$completed.element = true;
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
