package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: SelectionGestures.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1", f = "SelectionGestures.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SelectionGesturesKt$selectionGestureInput$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
    final /* synthetic */ TextDragObserver $textDragObserver;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectionGesturesKt$selectionGestureInput$1(MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super SelectionGesturesKt$selectionGestureInput$1> continuation) {
        super(2, continuation);
        this.$mouseSelectionObserver = mouseSelectionObserver;
        this.$textDragObserver = textDragObserver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SelectionGesturesKt$selectionGestureInput$1 selectionGesturesKt$selectionGestureInput$1 = new SelectionGesturesKt$selectionGestureInput$1(this.$mouseSelectionObserver, this.$textDragObserver, continuation);
        selectionGesturesKt$selectionGestureInput$1.L$0 = obj;
        return selectionGesturesKt$selectionGestureInput$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((SelectionGesturesKt$selectionGestureInput$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                ClicksCounter clicksCounter = new ClicksCounter($this$pointerInput.getViewConfiguration());
                this.label = 1;
                if (ForEachGestureKt.awaitEachGesture($this$pointerInput, new AnonymousClass1(this.$mouseSelectionObserver, clicksCounter, this.$textDragObserver, null), this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1", f = "SelectionGestures.kt", i = {0}, l = {105, 111, 113}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ClicksCounter $clicksCounter;
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, TextDragObserver textDragObserver, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$clicksCounter = clicksCounter;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$mouseSelectionObserver, this.$clicksCounter, this.$textDragObserver, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0071  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0093  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0091 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00af  */
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
                r3 = 1
                switch(r2) {
                    case 0: goto L34;
                    case 1: goto L27;
                    case 2: goto L1e;
                    case 3: goto L15;
                    default: goto Lc;
                }
            Lc:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L15:
                r1 = r17
                r0 = r18
                kotlin.ResultKt.throwOnFailure(r0)
                goto Lc1
            L1e:
                r1 = r17
                r0 = r18
                kotlin.ResultKt.throwOnFailure(r0)
                goto La7
            L27:
                r1 = r17
                r2 = r18
                java.lang.Object r4 = r1.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                kotlin.ResultKt.throwOnFailure(r2)
                r5 = r2
                goto L4d
            L34:
                kotlin.ResultKt.throwOnFailure(r18)
                r1 = r17
                r2 = r18
                java.lang.Object r4 = r1.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r1.L$0 = r4
                r1.label = r3
                java.lang.Object r5 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$awaitDown(r4, r5)
                if (r5 != r0) goto L4d
                return r0
            L4d:
                androidx.compose.ui.input.pointer.PointerEvent r5 = (androidx.compose.ui.input.pointer.PointerEvent) r5
                boolean r6 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r5)
                r7 = 0
                if (r6 == 0) goto La9
                int r6 = r5.getButtons()
                boolean r6 = androidx.compose.ui.input.pointer.PointerEvent_androidKt.m5335isPrimaryPressedaHzCxE(r6)
                if (r6 == 0) goto La9
                java.util.List r6 = r5.getChanges()
                r8 = 0
                r9 = 0
                r10 = 0
                int r11 = r6.size()
            L6f:
                if (r10 >= r11) goto L8f
                java.lang.Object r12 = r6.get(r10)
                r13 = 0
                androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
                r14 = 0
                boolean r15 = r12.isConsumed()
                r16 = 0
                if (r15 != 0) goto L84
                r12 = r3
                goto L86
            L84:
                r12 = r16
            L86:
                if (r12 != 0) goto L8b
                r3 = r16
                goto L91
            L8b:
                int r10 = r10 + 1
                goto L6f
            L8f:
            L91:
                if (r3 == 0) goto La9
                androidx.compose.foundation.text.selection.MouseSelectionObserver r3 = r1.$mouseSelectionObserver
                androidx.compose.foundation.text.selection.ClicksCounter r6 = r1.$clicksCounter
                r8 = r1
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r1.L$0 = r7
                r7 = 2
                r1.label = r7
                java.lang.Object r3 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$mouseSelection(r4, r3, r6, r5, r8)
                if (r3 != r0) goto La6
                return r0
            La6:
                r0 = r2
            La7:
                r2 = r0
                goto Lc2
            La9:
                boolean r3 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r5)
                if (r3 != 0) goto Lc2
                androidx.compose.foundation.text.TextDragObserver r3 = r1.$textDragObserver
                r6 = r1
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r1.L$0 = r7
                r7 = 3
                r1.label = r7
                java.lang.Object r3 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$touchSelection(r4, r3, r5, r6)
                if (r3 != r0) goto Lc0
                return r0
            Lc0:
                r0 = r2
            Lc1:
                r2 = r0
            Lc2:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
