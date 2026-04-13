package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

/* compiled from: ProduceState.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0096@¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096\u0003¢\u0006\u0002\u0010\fJ\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\u0017H\u0096\u0003R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00028\u0000X\u0096\u000f¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/ProduceStateScopeImpl;", "T", "Landroidx/compose/runtime/ProduceStateScope;", "Landroidx/compose/runtime/MutableState;", "state", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/compose/runtime/MutableState;Lkotlin/coroutines/CoroutineContext;)V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "awaitDispose", "", "onDispose", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "component1", "component2", "Lkotlin/Function1;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class ProduceStateScopeImpl<T> implements ProduceStateScope<T>, MutableState<T> {
    private final /* synthetic */ MutableState<T> $$delegate_0;
    private final CoroutineContext coroutineContext;

    @Override // androidx.compose.runtime.MutableState
    public T component1() {
        return this.$$delegate_0.component1();
    }

    @Override // androidx.compose.runtime.MutableState
    public Function1<T, Unit> component2() {
        return this.$$delegate_0.component2();
    }

    @Override // androidx.compose.runtime.MutableState, androidx.compose.runtime.State
    public T getValue() {
        return this.$$delegate_0.getValue();
    }

    @Override // androidx.compose.runtime.MutableState
    public void setValue(T t) {
        this.$$delegate_0.setValue(t);
    }

    public ProduceStateScopeImpl(MutableState<T> mutableState, CoroutineContext coroutineContext) {
        this.coroutineContext = coroutineContext;
        this.$$delegate_0 = mutableState;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // androidx.compose.runtime.ProduceStateScope
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object awaitDispose(kotlin.jvm.functions.Function0<kotlin.Unit> r10, kotlin.coroutines.Continuation<?> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1 r0 = (androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1 r0 = new androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1
            r0.<init>(r9, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L37;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2c:
            r10 = 0
            java.lang.Object r1 = r0.L$0
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L35
            goto L6c
        L35:
            r10 = move-exception
            goto L77
        L37:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = 0
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L73
            r3 = 1
            r0.label = r3     // Catch: java.lang.Throwable -> L73
            r4 = r0
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Throwable -> L73
            r5 = 0
            kotlinx.coroutines.CancellableContinuationImpl r6 = new kotlinx.coroutines.CancellableContinuationImpl     // Catch: java.lang.Throwable -> L73
            kotlin.coroutines.Continuation r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r4)     // Catch: java.lang.Throwable -> L73
            r6.<init>(r7, r3)     // Catch: java.lang.Throwable -> L73
            r6.initCancellability()     // Catch: java.lang.Throwable -> L73
            r3 = r6
            kotlinx.coroutines.CancellableContinuation r3 = (kotlinx.coroutines.CancellableContinuation) r3     // Catch: java.lang.Throwable -> L73
            r3 = 0
            java.lang.Object r3 = r6.getResult()     // Catch: java.lang.Throwable -> L73
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch: java.lang.Throwable -> L73
            if (r3 != r4) goto L67
            r4 = r0
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Throwable -> L73
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r4)     // Catch: java.lang.Throwable -> L73
        L67:
            if (r3 != r1) goto L6a
            return r1
        L6a:
            r1 = r10
            r10 = r2
        L6c:
            kotlin.KotlinNothingValueException r10 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L35
            r10.<init>()     // Catch: java.lang.Throwable -> L35
            throw r10     // Catch: java.lang.Throwable -> L35
        L73:
            r1 = move-exception
            r8 = r1
            r1 = r10
            r10 = r8
        L77:
            r1.invoke()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ProduceStateScopeImpl.awaitDispose(kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
