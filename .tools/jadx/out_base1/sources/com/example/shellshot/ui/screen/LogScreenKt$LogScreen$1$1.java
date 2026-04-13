package com.example.shellshot.ui.screen;

import androidx.compose.runtime.snapshots.SnapshotStateMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LogScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.screen.LogScreenKt$LogScreen$1$1", f = "LogScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes10.dex */
final class LogScreenKt$LogScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<String> $currentKeys;
    final /* synthetic */ SnapshotStateMap<String, Boolean> $revealedLogKeys;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LogScreenKt$LogScreen$1$1(List<String> list, SnapshotStateMap<String, Boolean> snapshotStateMap, Continuation<? super LogScreenKt$LogScreen$1$1> continuation) {
        super(2, continuation);
        this.$currentKeys = list;
        this.$revealedLogKeys = snapshotStateMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogScreenKt$LogScreen$1$1(this.$currentKeys, this.$revealedLogKeys, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LogScreenKt$LogScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Set currentKeySet = CollectionsKt.toSet(this.$currentKeys);
                Iterable list = CollectionsKt.toList(this.$revealedLogKeys.keySet());
                Collection arrayList = new ArrayList();
                for (Object obj : list) {
                    if (!currentKeySet.contains((String) obj)) {
                        arrayList.add(obj);
                    }
                }
                SnapshotStateMap<String, Boolean> snapshotStateMap = this.$revealedLogKeys;
                Iterator it = ((List) arrayList).iterator();
                while (it.hasNext()) {
                    snapshotStateMap.remove((String) it.next());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
