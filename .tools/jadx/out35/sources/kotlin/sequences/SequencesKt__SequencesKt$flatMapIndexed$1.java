package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: Sequences.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "R", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$flatMapIndexed$1", f = "Sequences.kt", i = {0, 0, 0, 0}, l = {383}, m = "invokeSuspend", n = {"$this$sequence", "element", "result", "index"}, s = {"L$0", "L$2", "L$3", "I$0"})
/* loaded from: classes14.dex */
final class SequencesKt__SequencesKt$flatMapIndexed$1<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<C, Iterator<R>> $iterator;
    final /* synthetic */ Sequence<T> $source;
    final /* synthetic */ Function2<Integer, T, C> $transform;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SequencesKt__SequencesKt$flatMapIndexed$1(Sequence<? extends T> sequence, Function2<? super Integer, ? super T, ? extends C> function2, Function1<? super C, ? extends Iterator<? extends R>> function1, Continuation<? super SequencesKt__SequencesKt$flatMapIndexed$1> continuation) {
        super(2, continuation);
        this.$source = sequence;
        this.$transform = function2;
        this.$iterator = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SequencesKt__SequencesKt$flatMapIndexed$1 sequencesKt__SequencesKt$flatMapIndexed$1 = new SequencesKt__SequencesKt$flatMapIndexed$1(this.$source, this.$transform, this.$iterator, continuation);
        sequencesKt__SequencesKt$flatMapIndexed$1.L$0 = obj;
        return sequencesKt__SequencesKt$flatMapIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SequencesKt__SequencesKt$flatMapIndexed$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0077 -> B:7:0x007a). Please report as a decompilation issue!!! */
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
            java.util.Iterator r5 = (java.util.Iterator) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            r11 = r10
            goto L7a
        L28:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = 0
            kotlin.sequences.Sequence<T> r3 = r10.$source
            java.util.Iterator r3 = r3.iterator()
            r5 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            r11 = r10
        L38:
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L7c
            java.lang.Object r4 = r5.next()
            kotlin.jvm.functions.Function2<java.lang.Integer, T, C> r6 = r11.$transform
            int r7 = r3 + 1
            if (r3 >= 0) goto L4b
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L4b:
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            java.lang.Object r3 = r6.invoke(r3, r4)
            kotlin.jvm.functions.Function1<C, java.util.Iterator<R>> r6 = r11.$iterator
            java.lang.Object r6 = r6.invoke(r3)
            java.util.Iterator r6 = (java.util.Iterator) r6
            r8 = r11
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r11.L$0 = r1
            r11.L$1 = r5
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r11.L$2 = r9
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r11.L$3 = r9
            r11.I$0 = r7
            r9 = 1
            r11.label = r9
            java.lang.Object r6 = r1.yieldAll(r6, r8)
            if (r6 != r2) goto L7a
            return r2
        L7a:
            r3 = r7
            goto L38
        L7c:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt__SequencesKt$flatMapIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
