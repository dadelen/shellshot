package androidx.compose.material3.internal;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.material3.TooltipState;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: BasicTooltip.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1", f = "BasicTooltip.android.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
final class BasicTooltip_androidKt$handleGestures$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TooltipState $state;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BasicTooltip_androidKt$handleGestures$1(TooltipState tooltipState, Continuation<? super BasicTooltip_androidKt$handleGestures$1> continuation) {
        super(2, continuation);
        this.$state = tooltipState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BasicTooltip_androidKt$handleGestures$1 basicTooltip_androidKt$handleGestures$1 = new BasicTooltip_androidKt$handleGestures$1(this.$state, continuation);
        basicTooltip_androidKt$handleGestures$1.L$0 = obj;
        return basicTooltip_androidKt$handleGestures$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((BasicTooltip_androidKt$handleGestures$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: BasicTooltip.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1", f = "BasicTooltip.android.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputScope $$this$pointerInput;
        final /* synthetic */ TooltipState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PointerInputScope pointerInputScope, TooltipState tooltipState, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$$this$pointerInput = pointerInputScope;
            this.$state = tooltipState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$pointerInput, this.$state, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: BasicTooltip.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1", f = "BasicTooltip.android.kt", i = {0, 0, 0, 0, 1, 1, 1, 2}, l = {171, 177, 197}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "isLongPressedFlow", "pass", "longPressTimeout", "$this$awaitEachGesture", "isLongPressedFlow", "pass", "isLongPressedFlow"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "L$0"})
        /* renamed from: androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00621 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ TooltipState $state;
            long J$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00621(CoroutineScope coroutineScope, TooltipState tooltipState, Continuation<? super C00621> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.$state = tooltipState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00621 c00621 = new C00621(this.$$this$coroutineScope, this.$state, continuation);
                c00621.L$0 = obj;
                return c00621;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00621) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:13:0x0118 A[Catch: all -> 0x0127, TRY_LEAVE, TryCatch #4 {all -> 0x0127, blocks: (B:11:0x0114, B:13:0x0118), top: B:10:0x0114 }] */
            /* JADX WARN: Removed duplicated region for block: B:33:0x0110 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:34:0x0111  */
            /* JADX WARN: Removed duplicated region for block: B:48:0x00d8 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:49:0x00d9  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r17) {
                /*
                    Method dump skipped, instructions count: 318
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1.AnonymousClass1.C00621.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            /* compiled from: BasicTooltip.android.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$1", f = "BasicTooltip.android.kt", i = {}, l = {178}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$1, reason: invalid class name and collision with other inner class name */
            static final class C00631 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super PointerInputChange>, Object> {
                final /* synthetic */ PointerEventPass $pass;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00631(PointerEventPass pointerEventPass, Continuation<? super C00631> continuation) {
                    super(2, continuation);
                    this.$pass = pointerEventPass;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00631 c00631 = new C00631(this.$pass, continuation);
                    c00631.L$0 = obj;
                    return c00631;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super PointerInputChange> continuation) {
                    return ((C00631) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            AwaitPointerEventScope $this$withTimeout = (AwaitPointerEventScope) this.L$0;
                            this.label = 1;
                            Object waitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation($this$withTimeout, this.$pass, this);
                            return waitForUpOrCancellation == coroutine_suspended ? coroutine_suspended : waitForUpOrCancellation;
                        case 1:
                            ResultKt.throwOnFailure($result);
                            return $result;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* compiled from: BasicTooltip.android.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2", f = "BasicTooltip.android.kt", i = {}, l = {185, 187, 187}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2, reason: invalid class name */
            static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MutableStateFlow<Boolean> $isLongPressedFlow;
                final /* synthetic */ TooltipState $state;
                Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(MutableStateFlow<Boolean> mutableStateFlow, TooltipState tooltipState, Continuation<? super AnonymousClass2> continuation) {
                    super(2, continuation);
                    this.$isLongPressedFlow = mutableStateFlow;
                    this.$state = tooltipState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass2(this.$isLongPressedFlow, this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:16:0x0060 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
                /* JADX WARN: Type inference failed for: r1v0, types: [int] */
                /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2] */
                /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2] */
                /* JADX WARN: Type inference failed for: r1v5 */
                /* JADX WARN: Type inference failed for: r1v9 */
                /* JADX WARN: Type inference failed for: r2v0 */
                /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.coroutines.Continuation] */
                /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Object] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                    /*
                        r7 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r7.label
                        r2 = 0
                        switch(r1) {
                            case 0: goto L28;
                            case 1: goto L21;
                            case 2: goto L1c;
                            case 3: goto L12;
                            default: goto La;
                        }
                    La:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r0)
                        throw r8
                    L12:
                        r0 = r7
                        java.lang.Object r1 = r0.L$0
                        java.lang.Throwable r1 = (java.lang.Throwable) r1
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L84
                    L1c:
                        r0 = r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L62
                    L21:
                        r1 = r7
                        kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L26
                        goto L47
                    L26:
                        r3 = move-exception
                        goto L66
                    L28:
                        kotlin.ResultKt.throwOnFailure(r8)
                        r1 = r7
                        kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> r3 = r1.$isLongPressedFlow     // Catch: java.lang.Throwable -> L26
                        r4 = 1
                        java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch: java.lang.Throwable -> L26
                        r3.tryEmit(r5)     // Catch: java.lang.Throwable -> L26
                        androidx.compose.material3.TooltipState r3 = r1.$state     // Catch: java.lang.Throwable -> L26
                        androidx.compose.foundation.MutatePriority r5 = androidx.compose.foundation.MutatePriority.PreventUserInput     // Catch: java.lang.Throwable -> L26
                        r6 = r1
                        kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Throwable -> L26
                        r1.label = r4     // Catch: java.lang.Throwable -> L26
                        java.lang.Object r3 = r3.show(r5, r6)     // Catch: java.lang.Throwable -> L26
                        if (r3 != r0) goto L47
                        return r0
                    L47:
                        kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> r3 = r1.$isLongPressedFlow
                        kotlinx.coroutines.flow.Flow r3 = (kotlinx.coroutines.flow.Flow) r3
                        androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2$1 r4 = new androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2$1
                        androidx.compose.material3.TooltipState r5 = r1.$state
                        r4.<init>(r5, r2)
                        kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                        r2 = r1
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        r5 = 2
                        r1.label = r5
                        java.lang.Object r2 = kotlinx.coroutines.flow.FlowKt.collectLatest(r3, r4, r2)
                        if (r2 != r0) goto L61
                        return r0
                    L61:
                        r0 = r1
                    L62:
                        kotlin.Unit r1 = kotlin.Unit.INSTANCE
                        return r1
                    L66:
                        kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> r4 = r1.$isLongPressedFlow
                        kotlinx.coroutines.flow.Flow r4 = (kotlinx.coroutines.flow.Flow) r4
                        androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2$1 r5 = new androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2$1
                        androidx.compose.material3.TooltipState r6 = r1.$state
                        r5.<init>(r6, r2)
                        kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
                        r2 = r1
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        r1.L$0 = r3
                        r6 = 3
                        r1.label = r6
                        java.lang.Object r2 = kotlinx.coroutines.flow.FlowKt.collectLatest(r4, r5, r2)
                        if (r2 != r0) goto L82
                        return r0
                    L82:
                        r0 = r1
                        r1 = r3
                    L84:
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1.AnonymousClass1.C00621.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                }

                /* compiled from: BasicTooltip.android.kt */
                @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "isLongPressed", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2$1", f = "BasicTooltip.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material3.internal.BasicTooltip_androidKt$handleGestures$1$1$1$2$1, reason: invalid class name and collision with other inner class name */
                static final class C00641 extends SuspendLambda implements Function2<Boolean, Continuation<? super Unit>, Object> {
                    final /* synthetic */ TooltipState $state;
                    /* synthetic */ boolean Z$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00641(TooltipState tooltipState, Continuation<? super C00641> continuation) {
                        super(2, continuation);
                        this.$state = tooltipState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00641 c00641 = new C00641(this.$state, continuation);
                        c00641.Z$0 = ((Boolean) obj).booleanValue();
                        return c00641;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Continuation<? super Unit> continuation) {
                        return invoke(bool.booleanValue(), continuation);
                    }

                    public final Object invoke(boolean z, Continuation<? super Unit> continuation) {
                        return ((C00641) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure(obj);
                                boolean isLongPressed = this.Z$0;
                                if (!isLongPressed) {
                                    this.$state.dismiss();
                                }
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture(this.$$this$pointerInput, new C00621($this$coroutineScope, this.$state, null), this) != coroutine_suspended) {
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
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1($this$pointerInput, this.$state, null), this) != coroutine_suspended) {
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
}
