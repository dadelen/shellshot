package androidx.compose.runtime.snapshots;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: SnapshotIdSet.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1", f = "SnapshotIdSet.kt", i = {0, 0, 1, 1, 2, 2}, l = {295, 300, 307}, m = "invokeSuspend", n = {"$this$sequence", "belowBound", "$this$sequence", "index", "$this$sequence", "index"}, s = {"L$0", "L$1", "L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes13.dex */
final class SnapshotIdSet$iterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Integer>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SnapshotIdSet this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SnapshotIdSet$iterator$1(SnapshotIdSet snapshotIdSet, Continuation<? super SnapshotIdSet$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = snapshotIdSet;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SnapshotIdSet$iterator$1 snapshotIdSet$iterator$1 = new SnapshotIdSet$iterator$1(this.this$0, continuation);
        snapshotIdSet$iterator$1.L$0 = obj;
        return snapshotIdSet$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Integer> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SnapshotIdSet$iterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00e0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x00eb -> B:7:0x010e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x010a -> B:7:0x010e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00ad -> B:17:0x00ce). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00ca -> B:17:0x00ce). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0088 -> B:29:0x008b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
