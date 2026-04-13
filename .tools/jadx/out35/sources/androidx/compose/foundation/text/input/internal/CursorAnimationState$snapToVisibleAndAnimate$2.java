package androidx.compose.foundation.text.input.internal;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: CursorAnimationState.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2", f = "CursorAnimationState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class CursorAnimationState$snapToVisibleAndAnimate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CursorAnimationState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CursorAnimationState$snapToVisibleAndAnimate$2(CursorAnimationState cursorAnimationState, Continuation<? super CursorAnimationState$snapToVisibleAndAnimate$2> continuation) {
        super(2, continuation);
        this.this$0 = cursorAnimationState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CursorAnimationState$snapToVisibleAndAnimate$2 cursorAnimationState$snapToVisibleAndAnimate$2 = new CursorAnimationState$snapToVisibleAndAnimate$2(this.this$0, continuation);
        cursorAnimationState$snapToVisibleAndAnimate$2.L$0 = obj;
        return cursorAnimationState$snapToVisibleAndAnimate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((CursorAnimationState$snapToVisibleAndAnimate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        Job launch$default;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                atomicReference = this.this$0.animationJob;
                Job oldJob = (Job) atomicReference.getAndSet(null);
                atomicReference2 = this.this$0.animationJob;
                launch$default = BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AnonymousClass1(oldJob, this.this$0, null), 3, null);
                return Boxing.boxBoolean(atomicReference2.compareAndSet(null, launch$default));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: CursorAnimationState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1", f = "CursorAnimationState.kt", i = {}, l = {69, 77, 79}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $oldJob;
        int label;
        final /* synthetic */ CursorAnimationState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Job job, CursorAnimationState cursorAnimationState, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$oldJob = job;
            this.this$0 = cursorAnimationState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$oldJob, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0051 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0063 A[RETURN] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v6, types: [androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1] */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r1v8 */
        /* JADX WARN: Type inference failed for: r1v9 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0061 -> B:9:0x003e). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 500(0x1f4, double:2.47E-321)
                r4 = 0
                switch(r1) {
                    case 0: goto L26;
                    case 1: goto L21;
                    case 2: goto L1a;
                    case 3: goto L15;
                    default: goto Lc;
                }
            Lc:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L15:
                r1 = r8
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1f
                goto L64
            L1a:
                r1 = r8
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1f
                goto L52
            L1f:
                r0 = move-exception
                goto L65
            L21:
                r1 = r8
                kotlin.ResultKt.throwOnFailure(r9)
                goto L3b
            L26:
                kotlin.ResultKt.throwOnFailure(r9)
                r1 = r8
                kotlinx.coroutines.Job r5 = r1.$oldJob
                if (r5 == 0) goto L3b
                r6 = r1
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r7 = 1
                r1.label = r7
                java.lang.Object r5 = kotlinx.coroutines.JobKt.cancelAndJoin(r5, r6)
                if (r5 != r0) goto L3b
                return r0
            L3b:
            L3d:
                androidx.compose.foundation.text.input.internal.CursorAnimationState r5 = r1.this$0     // Catch: java.lang.Throwable -> L1f
                r6 = 1065353216(0x3f800000, float:1.0)
                androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r5, r6)     // Catch: java.lang.Throwable -> L1f
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Throwable -> L1f
                r6 = 2
                r1.label = r6     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r2, r5)     // Catch: java.lang.Throwable -> L1f
                if (r5 != r0) goto L52
                return r0
            L52:
                androidx.compose.foundation.text.input.internal.CursorAnimationState r5 = r1.this$0     // Catch: java.lang.Throwable -> L1f
                androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r5, r4)     // Catch: java.lang.Throwable -> L1f
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Throwable -> L1f
                r6 = 3
                r1.label = r6     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r2, r5)     // Catch: java.lang.Throwable -> L1f
                if (r5 != r0) goto L64
                return r0
            L64:
                goto L3d
            L65:
                androidx.compose.foundation.text.input.internal.CursorAnimationState r2 = r1.this$0
                androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r2, r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
