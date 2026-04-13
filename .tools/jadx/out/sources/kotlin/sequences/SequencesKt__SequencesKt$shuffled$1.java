package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.random.Random;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Sequences.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$shuffled$1", f = "Sequences.kt", i = {0, 0, 0, 0, 0}, l = {178}, m = "invokeSuspend", n = {"$this$sequence", "buffer", "last", "value", "j"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
/* loaded from: classes14.dex */
final class SequencesKt__SequencesKt$shuffled$1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Random $random;
    final /* synthetic */ Sequence<T> $this_shuffled;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt__SequencesKt$shuffled$1(Sequence<? extends T> sequence, Random random, Continuation<? super SequencesKt__SequencesKt$shuffled$1> continuation) {
        super(2, continuation);
        this.$this_shuffled = sequence;
        this.$random = random;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt__SequencesKt$shuffled$1 sequencesKt__SequencesKt$shuffled$1 = new SequencesKt__SequencesKt$shuffled$1(this.$this_shuffled, this.$random, continuation);
        sequencesKt__SequencesKt$shuffled$1.L$0 = obj;
        return sequencesKt__SequencesKt$shuffled$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super T> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt__SequencesKt$shuffled$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0078 -> B:7:0x007b). Please report as a decompilation issue!!! */
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
                case 0: goto L28;
                case 1: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            int r2 = r10.I$0
            java.lang.Object r3 = r10.L$3
            java.lang.Object r4 = r10.L$2
            java.lang.Object r5 = r10.L$1
            java.util.List r5 = (java.util.List) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r6 = r3
            r3 = r1
            r1 = r0
            r0 = r11
            r11 = r10
            goto L7b
        L28:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.sequences.Sequence<T> r2 = r10.$this_shuffled
            java.util.List r2 = kotlin.sequences.SequencesKt.toMutableList(r2)
            r5 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            r11 = r10
        L36:
            r3 = r5
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L7d
            kotlin.random.Random r3 = r11.$random
            int r4 = r5.size()
            int r3 = r3.nextInt(r4)
            java.lang.Object r4 = kotlin.collections.CollectionsKt.removeLast(r5)
            int r6 = r5.size()
            if (r3 >= r6) goto L58
            java.lang.Object r6 = r5.set(r3, r4)
            goto L59
        L58:
            r6 = r4
        L59:
            r7 = r11
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r11.L$0 = r1
            r11.L$1 = r5
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r11.L$2 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r11.L$3 = r8
            r11.I$0 = r3
            r8 = 1
            r11.label = r8
            java.lang.Object r7 = r1.yield(r6, r7)
            if (r7 != r2) goto L78
            return r2
        L78:
            r9 = r3
            r3 = r2
            r2 = r9
        L7b:
            r2 = r3
            goto L36
        L7d:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt__SequencesKt$shuffled$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
