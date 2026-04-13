package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: TextFieldPressGestureFilter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1", f = "TextFieldPressGestureFilter.kt", i = {}, l = {54}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ State<Function1<Offset, Unit>> $onTapState;
    final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
    final /* synthetic */ CoroutineScope $scope;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1(CoroutineScope coroutineScope, MutableState<PressInteraction.Press> mutableState, MutableInteractionSource mutableInteractionSource, State<? extends Function1<? super Offset, Unit>> state, Continuation<? super TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1> continuation) {
        super(2, continuation);
        this.$scope = coroutineScope;
        this.$pressedInteraction = mutableState;
        this.$interactionSource = mutableInteractionSource;
        this.$onTapState = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1 textFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1 = new TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1(this.$scope, this.$pressedInteraction, this.$interactionSource, this.$onTapState, continuation);
        textFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1.L$0 = obj;
        return textFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$scope, this.$pressedInteraction, this.$interactionSource, null);
                final State<Function1<Offset, Unit>> state = this.$onTapState;
                this.label = 1;
                if (TapGestureDetectorKt.detectTapAndPress($this$pointerInput, anonymousClass1, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m1071invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m1071invokek4lQ0M(long it) {
                        state.getValue().invoke(Offset.m3911boximpl(it));
                    }
                }, this) != coroutine_suspended) {
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

    /* compiled from: TextFieldPressGestureFilter.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "it", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1", f = "TextFieldPressGestureFilter.kt", i = {}, l = {67}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
        final /* synthetic */ CoroutineScope $scope;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CoroutineScope coroutineScope, MutableState<PressInteraction.Press> mutableState, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$scope = coroutineScope;
            this.$pressedInteraction = mutableState;
            this.$interactionSource = mutableInteractionSource;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m1070invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
        }

        /* renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m1070invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$scope, this.$pressedInteraction, this.$interactionSource, continuation);
            anonymousClass1.L$0 = pressGestureScope;
            anonymousClass1.J$0 = j;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: TextFieldPressGestureFilter.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1$1", f = "TextFieldPressGestureFilter.kt", i = {1}, l = {LockFreeTaskQueueCore.FROZEN_SHIFT, 64}, m = "invokeSuspend", n = {"interaction"}, s = {"L$0"})
        /* renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ long $it;
            final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00231(MutableState<PressInteraction.Press> mutableState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super C00231> continuation) {
                super(2, continuation);
                this.$pressedInteraction = mutableState;
                this.$it = j;
                this.$interactionSource = mutableInteractionSource;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00231(this.$pressedInteraction, this.$it, this.$interactionSource, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x0064  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    r9 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r9.label
                    r2 = 0
                    switch(r1) {
                        case 0: goto L26;
                        case 1: goto L1c;
                        case 2: goto L13;
                        default: goto La;
                    }
                La:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L13:
                    r0 = r9
                    java.lang.Object r1 = r0.L$0
                    androidx.compose.foundation.interaction.PressInteraction$Press r1 = (androidx.compose.foundation.interaction.PressInteraction.Press) r1
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L78
                L1c:
                    r1 = r9
                    r3 = 0
                    java.lang.Object r4 = r1.L$0
                    androidx.compose.runtime.MutableState r4 = (androidx.compose.runtime.MutableState) r4
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L51
                L26:
                    kotlin.ResultKt.throwOnFailure(r10)
                    r1 = r9
                    androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.PressInteraction$Press> r3 = r1.$pressedInteraction
                    java.lang.Object r3 = r3.getValue()
                    androidx.compose.foundation.interaction.PressInteraction$Press r3 = (androidx.compose.foundation.interaction.PressInteraction.Press) r3
                    if (r3 == 0) goto L59
                    androidx.compose.foundation.interaction.MutableInteractionSource r4 = r1.$interactionSource
                    androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.PressInteraction$Press> r5 = r1.$pressedInteraction
                    r6 = 0
                    androidx.compose.foundation.interaction.PressInteraction$Cancel r7 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
                    r7.<init>(r3)
                    if (r4 == 0) goto L54
                    r3 = r7
                    androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
                    r1.L$0 = r5
                    r8 = 1
                    r1.label = r8
                    java.lang.Object r3 = r4.emit(r3, r1)
                    if (r3 != r0) goto L4f
                    return r0
                L4f:
                    r4 = r5
                    r3 = r6
                L51:
                    r6 = r3
                    r5 = r4
                L54:
                    r5.setValue(r2)
                L59:
                    androidx.compose.foundation.interaction.PressInteraction$Press r3 = new androidx.compose.foundation.interaction.PressInteraction$Press
                    long r4 = r1.$it
                    r3.<init>(r4, r2)
                    androidx.compose.foundation.interaction.MutableInteractionSource r2 = r1.$interactionSource
                    if (r2 == 0) goto L7a
                    r4 = r3
                    androidx.compose.foundation.interaction.Interaction r4 = (androidx.compose.foundation.interaction.Interaction) r4
                    r5 = r1
                    kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                    r1.L$0 = r3
                    r6 = 2
                    r1.label = r6
                    java.lang.Object r2 = r2.emit(r4, r5)
                    if (r2 != r0) goto L76
                    return r0
                L76:
                    r0 = r1
                    r1 = r3
                L78:
                    r3 = r1
                    r1 = r0
                L7a:
                    androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.PressInteraction$Press> r0 = r1.$pressedInteraction
                    r0.setValue(r3)
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1.AnonymousClass1.C00231.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass1 anonymousClass1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass1 = this;
                    PressGestureScope $this$detectTapAndPress = (PressGestureScope) anonymousClass1.L$0;
                    long it = anonymousClass1.J$0;
                    BuildersKt__Builders_commonKt.launch$default(anonymousClass1.$scope, null, null, new C00231(anonymousClass1.$pressedInteraction, it, anonymousClass1.$interactionSource, null), 3, null);
                    anonymousClass1.label = 1;
                    Object tryAwaitRelease = $this$detectTapAndPress.tryAwaitRelease(anonymousClass1);
                    if (tryAwaitRelease != coroutine_suspended) {
                        $result = tryAwaitRelease;
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                    anonymousClass1 = this;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean success = ((Boolean) $result).booleanValue();
            BuildersKt__Builders_commonKt.launch$default(anonymousClass1.$scope, null, null, new AnonymousClass2(anonymousClass1.$pressedInteraction, success, anonymousClass1.$interactionSource, null), 3, null);
            return Unit.INSTANCE;
        }

        /* compiled from: TextFieldPressGestureFilter.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1$2", f = "TextFieldPressGestureFilter.kt", i = {}, l = {76}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
            final /* synthetic */ boolean $success;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(MutableState<PressInteraction.Press> mutableState, boolean z, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$pressedInteraction = mutableState;
                this.$success = z;
                this.$interactionSource = mutableInteractionSource;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$pressedInteraction, this.$success, this.$interactionSource, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                MutableState<PressInteraction.Press> mutableState;
                PressInteraction interaction;
                AnonymousClass2 anonymousClass2;
                MutableState<PressInteraction.Press> mutableState2;
                AnonymousClass2 anonymousClass22;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        PressInteraction.Press oldValue = this.$pressedInteraction.getValue();
                        if (oldValue != null) {
                            boolean z = this.$success;
                            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                            mutableState = this.$pressedInteraction;
                            if (z) {
                                interaction = new PressInteraction.Release(oldValue);
                            } else {
                                interaction = new PressInteraction.Cancel(oldValue);
                            }
                            if (mutableInteractionSource != null) {
                                this.L$0 = mutableState;
                                this.label = 1;
                                if (mutableInteractionSource.emit(interaction, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                anonymousClass2 = this;
                                mutableState2 = mutableState;
                                anonymousClass22 = null;
                                mutableState = mutableState2;
                            }
                            mutableState.setValue(null);
                        }
                        return Unit.INSTANCE;
                    case 1:
                        anonymousClass2 = this;
                        anonymousClass22 = null;
                        mutableState2 = (MutableState) anonymousClass2.L$0;
                        ResultKt.throwOnFailure($result);
                        mutableState = mutableState2;
                        mutableState.setValue(null);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }
}
