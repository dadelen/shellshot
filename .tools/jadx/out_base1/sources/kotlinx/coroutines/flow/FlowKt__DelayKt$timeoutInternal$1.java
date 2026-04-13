package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Delay.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downStream", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1", f = "Delay.kt", i = {0, 0}, l = {416}, m = "invokeSuspend", n = {"downStream", "values"}, s = {"L$0", "L$1"})
/* loaded from: classes14.dex */
final class FlowKt__DelayKt$timeoutInternal$1<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T> $this_timeoutInternal;
    final /* synthetic */ long $timeout;
    long J$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__DelayKt$timeoutInternal$1(long j, Flow<? extends T> flow, Continuation<? super FlowKt__DelayKt$timeoutInternal$1> continuation) {
        super(3, continuation);
        this.$timeout = j;
        this.$this_timeoutInternal = flow;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$timeoutInternal$1 flowKt__DelayKt$timeoutInternal$1 = new FlowKt__DelayKt$timeoutInternal$1(this.$timeout, this.$this_timeoutInternal, continuation);
        flowKt__DelayKt$timeoutInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$timeoutInternal$1.L$1 = flowCollector;
        return flowKt__DelayKt$timeoutInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00aa  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0098 -> B:7:0x009f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r17
            int r2 = r1.label
            r3 = 0
            switch(r2) {
                case 0: goto L2e;
                case 1: goto L14;
                default: goto Lc;
            }
        Lc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L14:
            r1 = r17
            r2 = r18
            r4 = 0
            r5 = 0
            r6 = 0
            long r7 = r1.J$0
            java.lang.Object r9 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            kotlin.ResultKt.throwOnFailure(r2)
            r11 = r6
            r6 = r5
            r5 = r4
            r4 = r2
            goto L9f
        L2e:
            kotlin.ResultKt.throwOnFailure(r18)
            r1 = r17
            r2 = r18
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            java.lang.Object r5 = r1.L$1
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            long r6 = r1.$timeout
            kotlin.time.Duration$Companion r8 = kotlin.time.Duration.INSTANCE
            long r8 = r8.m8646getZEROUwyO8pc()
            int r6 = kotlin.time.Duration.m8554compareToLRDsOJo(r6, r8)
            if (r6 <= 0) goto Lb0
            kotlinx.coroutines.flow.Flow<T> r6 = r1.$this_timeoutInternal
            r7 = 0
            r8 = 2
            kotlinx.coroutines.flow.Flow r6 = kotlinx.coroutines.flow.FlowKt.buffer$default(r6, r7, r3, r8, r3)
            kotlinx.coroutines.channels.ReceiveChannel r4 = kotlinx.coroutines.flow.FlowKt.produceIn(r6, r4)
            long r6 = r1.$timeout
            r8 = 0
            r9 = r4
            r10 = r5
            r4 = r8
            r7 = r6
        L5e:
            r5 = 0
            kotlinx.coroutines.selects.SelectImplementation r6 = new kotlinx.coroutines.selects.SelectImplementation
            kotlin.coroutines.CoroutineContext r11 = r1.get$context()
            r6.<init>(r11)
            r11 = 0
            r12 = r6
            kotlinx.coroutines.selects.SelectBuilder r12 = (kotlinx.coroutines.selects.SelectBuilder) r12
            r13 = 0
            kotlinx.coroutines.selects.SelectClause1 r14 = r9.getOnReceiveCatching()
            kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$1 r15 = new kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$1
            r15.<init>(r10, r3)
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r12.invoke(r14, r15)
            kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$2 r14 = new kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$2
            r14.<init>(r7, r3)
            kotlin.jvm.functions.Function1 r14 = (kotlin.jvm.functions.Function1) r14
            kotlinx.coroutines.selects.OnTimeoutKt.m8773onTimeout8Mi8wO0(r12, r7, r14)
            r1.L$0 = r10
            r1.L$1 = r9
            r1.J$0 = r7
            r12 = 1
            r1.label = r12
            java.lang.Object r6 = r6.doSelect(r1)
            if (r6 != r0) goto L98
            return r0
        L98:
            r16 = r4
            r4 = r2
            r2 = r6
            r6 = r5
            r5 = r16
        L9f:
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto Lad
        Laa:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lad:
            r2 = r4
            r4 = r5
            goto L5e
        Lb0:
            kotlinx.coroutines.TimeoutCancellationException r0 = new kotlinx.coroutines.TimeoutCancellationException
            java.lang.String r3 = "Timed out immediately"
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
