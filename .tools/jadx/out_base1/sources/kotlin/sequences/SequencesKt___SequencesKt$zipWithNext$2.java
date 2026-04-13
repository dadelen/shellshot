package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: _Sequences.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "R", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2", f = "_Sequences.kt", i = {0, 0, 0, 0}, l = {2979}, m = "invokeSuspend", n = {"$this$result", "iterator", "current", "next"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes14.dex */
final class SequencesKt___SequencesKt$zipWithNext$2<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sequence<T> $this_zipWithNext;
    final /* synthetic */ Function2<T, T, R> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt___SequencesKt$zipWithNext$2(Sequence<? extends T> sequence, Function2<? super T, ? super T, ? extends R> function2, Continuation<? super SequencesKt___SequencesKt$zipWithNext$2> continuation) {
        super(2, continuation);
        this.$this_zipWithNext = sequence;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt___SequencesKt$zipWithNext$2 sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.$this_zipWithNext, this.$transform, continuation);
        sequencesKt___SequencesKt$zipWithNext$2.L$0 = obj;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$zipWithNext$2) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0075  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x006b -> B:7:0x0070). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.L$0
            kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            switch(r2) {
                case 0: goto L27;
                case 1: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            java.lang.Object r2 = r10.L$3
            java.lang.Object r3 = r10.L$2
            java.lang.Object r4 = r10.L$1
            java.util.Iterator r4 = (java.util.Iterator) r4
            kotlin.ResultKt.throwOnFailure(r11)
            r5 = r4
            r4 = r3
            r3 = r1
            r1 = r0
            r0 = r11
            r11 = r10
            goto L70
        L27:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.sequences.Sequence<T> r2 = r10.$this_zipWithNext
            java.util.Iterator r2 = r2.iterator()
            boolean r3 = r2.hasNext()
            if (r3 != 0) goto L39
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L39:
            java.lang.Object r3 = r2.next()
            r4 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            r11 = r10
        L42:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L75
            java.lang.Object r5 = r4.next()
            kotlin.jvm.functions.Function2<T, T, R> r6 = r11.$transform
            java.lang.Object r6 = r6.invoke(r3, r5)
            r7 = r11
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r11.L$0 = r1
            r11.L$1 = r4
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r11.L$2 = r8
            r11.L$3 = r5
            r8 = 1
            r11.label = r8
            java.lang.Object r6 = r1.yield(r6, r7)
            if (r6 != r2) goto L6b
            return r2
        L6b:
            r9 = r3
            r3 = r2
            r2 = r5
            r5 = r4
            r4 = r9
        L70:
            r4 = r3
            r3 = r2
            r2 = r4
            r4 = r5
            goto L42
        L75:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
