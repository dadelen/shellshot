package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: _Sequences.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "R", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1", f = "_Sequences.kt", i = {0, 1, 1, 1, 1}, l = {2451, 2456}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence", "accumulator", "element", "index"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
/* loaded from: classes14.dex */
final class SequencesKt___SequencesKt$runningFoldIndexed$1<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ R $initial;
    final /* synthetic */ Function3<Integer, R, T, R> $operation;
    final /* synthetic */ Sequence<T> $this_runningFoldIndexed;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$runningFoldIndexed$1(R r, Sequence<? extends T> sequence, Function3<? super Integer, ? super R, ? super T, ? extends R> function3, Continuation<? super SequencesKt___SequencesKt$runningFoldIndexed$1> continuation) {
        super(2, continuation);
        this.$initial = r;
        this.$this_runningFoldIndexed = sequence;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$runningFoldIndexed$1 sequencesKt___SequencesKt$runningFoldIndexed$1 = new SequencesKt___SequencesKt$runningFoldIndexed$1(this.$initial, this.$this_runningFoldIndexed, this.$operation, continuation);
        sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningFoldIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningFoldIndexed$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0089 -> B:7:0x008b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.L$0
            kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r9.label
            switch(r2) {
                case 0: goto L2f;
                case 1: goto L2b;
                case 2: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            int r2 = r9.I$0
            java.lang.Object r3 = r9.L$3
            java.lang.Object r4 = r9.L$2
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r9.L$1
            kotlin.ResultKt.throwOnFailure(r10)
            r8 = r5
            r5 = r4
            r4 = r8
            r8 = r2
            r2 = r1
            r1 = r0
            r0 = r10
            r10 = r9
            goto L8b
        L2b:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L43
        L2f:
            kotlin.ResultKt.throwOnFailure(r10)
            R r2 = r9.$initial
            r3 = r9
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r9.L$0 = r0
            r4 = 1
            r9.label = r4
            java.lang.Object r2 = r0.yield(r2, r3)
            if (r2 != r1) goto L43
            return r1
        L43:
            r2 = 0
            R r3 = r9.$initial
            kotlin.sequences.Sequence<T> r4 = r9.$this_runningFoldIndexed
            java.util.Iterator r4 = r4.iterator()
            r5 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r10
            r10 = r9
        L53:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L8d
            java.lang.Object r6 = r5.next()
            kotlin.jvm.functions.Function3<java.lang.Integer, R, T, R> r7 = r10.$operation
            int r8 = r3 + 1
            if (r3 >= 0) goto L66
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L66:
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            java.lang.Object r3 = r7.invoke(r3, r4, r6)
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r10.L$0 = r1
            r10.L$1 = r3
            r10.L$2 = r5
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r10.L$3 = r7
            r10.I$0 = r8
            r7 = 2
            r10.label = r7
            java.lang.Object r4 = r1.yield(r3, r4)
            if (r4 != r2) goto L89
            return r2
        L89:
            r4 = r3
            r3 = r6
        L8b:
            r3 = r8
            goto L53
        L8d:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
