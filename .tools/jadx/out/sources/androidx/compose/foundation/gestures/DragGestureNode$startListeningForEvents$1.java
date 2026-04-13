package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Draggable.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1", f = "Draggable.kt", i = {0, 0, 1, 1, 2, 2, 3, 4, 5}, l = {431, 433, 435, 442, 444, 447}, m = "invokeSuspend", n = {"$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$0"})
/* loaded from: classes.dex */
final class DragGestureNode$startListeningForEvents$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ DragGestureNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DragGestureNode$startListeningForEvents$1(DragGestureNode dragGestureNode, Continuation<? super DragGestureNode$startListeningForEvents$1> continuation) {
        super(2, continuation);
        this.this$0 = dragGestureNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragGestureNode$startListeningForEvents$1 dragGestureNode$startListeningForEvents$1 = new DragGestureNode$startListeningForEvents$1(this.this$0, continuation);
        dragGestureNode$startListeningForEvents$1.L$0 = obj;
        return dragGestureNode$startListeningForEvents$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DragGestureNode$startListeningForEvents$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f9 A[Catch: CancellationException -> 0x0133, TryCatch #0 {CancellationException -> 0x0133, blocks: (B:28:0x00f3, B:30:0x00f9, B:34:0x0118, B:36:0x011e), top: B:27:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0118 A[Catch: CancellationException -> 0x0133, TryCatch #0 {CancellationException -> 0x0133, blocks: (B:28:0x00f3, B:30:0x00f9, B:34:0x0118, B:36:0x011e), top: B:27:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0147 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0151  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
    /* JADX WARN: Type inference failed for: r1v16, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
    /* JADX WARN: Type inference failed for: r1v17, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
    /* JADX WARN: Type inference failed for: r1v18, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
    /* JADX WARN: Type inference failed for: r1v19, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x011c -> B:8:0x0078). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x012e -> B:8:0x0078). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x0148 -> B:8:0x0078). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x014b -> B:8:0x0078). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: Draggable.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00010\u0003H\u008a@"}, d2 = {"<anonymous>", "", "processDelta", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dragDelta"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1", f = "Draggable.kt", i = {0}, l = {438}, m = "invokeSuspend", n = {"processDelta"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Function1<? super DragEvent.DragDelta, ? extends Unit>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<DragEvent> $event;
        /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ DragGestureNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<DragEvent> objectRef, DragGestureNode dragGestureNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$event = objectRef;
            this.this$0 = dragGestureNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$event, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Function1<? super DragEvent.DragDelta, ? extends Unit> function1, Continuation<? super Unit> continuation) {
            return invoke2((Function1<? super DragEvent.DragDelta, Unit>) function1, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Function1<? super DragEvent.DragDelta, Unit> function1, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(function1, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0057 -> B:8:0x0079). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x006a -> B:7:0x0071). Please report as a decompilation issue!!! */
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
                switch(r1) {
                    case 0: goto L24;
                    case 1: goto L12;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L12:
                r1 = r8
                java.lang.Object r2 = r1.L$1
                kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
                java.lang.Object r3 = r1.L$0
                kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
                kotlin.ResultKt.throwOnFailure(r9)
                r4 = r3
                r3 = r2
                r2 = r1
                r1 = r0
                r0 = r9
                goto L71
            L24:
                kotlin.ResultKt.throwOnFailure(r9)
                r1 = r8
                java.lang.Object r2 = r1.L$0
                kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
                r3 = r2
            L2d:
                kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r2 = r1.$event
                T r2 = r2.element
                boolean r2 = r2 instanceof androidx.compose.foundation.gestures.DragEvent.DragStopped
                if (r2 != 0) goto L7c
                kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r2 = r1.$event
                T r2 = r2.element
                boolean r2 = r2 instanceof androidx.compose.foundation.gestures.DragEvent.DragCancelled
                if (r2 != 0) goto L7c
                kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r2 = r1.$event
                T r2 = r2.element
                boolean r4 = r2 instanceof androidx.compose.foundation.gestures.DragEvent.DragDelta
                r5 = 0
                if (r4 == 0) goto L49
                androidx.compose.foundation.gestures.DragEvent$DragDelta r2 = (androidx.compose.foundation.gestures.DragEvent.DragDelta) r2
                goto L4a
            L49:
                r2 = r5
            L4a:
                if (r2 == 0) goto L4f
                r3.invoke(r2)
            L4f:
                kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r2 = r1.$event
                androidx.compose.foundation.gestures.DragGestureNode r4 = r1.this$0
                kotlinx.coroutines.channels.Channel r4 = androidx.compose.foundation.gestures.DragGestureNode.access$getChannel$p(r4)
                if (r4 == 0) goto L79
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r1.L$0 = r3
                r1.L$1 = r2
                r6 = 1
                r1.label = r6
                java.lang.Object r4 = r4.receive(r5)
                if (r4 != r0) goto L6a
                return r0
            L6a:
                r7 = r0
                r0 = r9
                r9 = r4
                r4 = r3
                r3 = r2
                r2 = r1
                r1 = r7
            L71:
                r5 = r9
                androidx.compose.foundation.gestures.DragEvent r5 = (androidx.compose.foundation.gestures.DragEvent) r5
                r9 = r0
                r0 = r1
                r1 = r2
                r2 = r3
                r3 = r4
            L79:
                r2.element = r5
                goto L2d
            L7c:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
