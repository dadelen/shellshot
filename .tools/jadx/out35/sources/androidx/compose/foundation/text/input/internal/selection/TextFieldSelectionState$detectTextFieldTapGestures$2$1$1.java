package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TextFieldSelectionState.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1", f = "TextFieldSelectionState.kt", i = {}, l = {504, FrameMetricsAggregator.EVERY_DURATION}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PressGestureScope $$this$detectTapAndPress;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $offset;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TextFieldSelectionState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionState$detectTextFieldTapGestures$2$1$1(PressGestureScope pressGestureScope, TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super TextFieldSelectionState$detectTextFieldTapGestures$2$1$1> continuation) {
        super(2, continuation);
        this.$$this$detectTapAndPress = pressGestureScope;
        this.this$0 = textFieldSelectionState;
        this.$offset = j;
        this.$interactionSource = mutableInteractionSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = new TextFieldSelectionState$detectTextFieldTapGestures$2$1$1(this.$$this$detectTapAndPress, this.this$0, this.$offset, this.$interactionSource, continuation);
        textFieldSelectionState$detectTextFieldTapGestures$2$1$1.L$0 = obj;
        return textFieldSelectionState$detectTextFieldTapGestures$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionState$detectTextFieldTapGestures$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1", f = "TextFieldSelectionState.kt", i = {1}, l = {496, 501}, m = "invokeSuspend", n = {"press"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ long $offset;
        Object L$0;
        int label;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = textFieldSelectionState;
            this.$offset = j;
            this.$interactionSource = mutableInteractionSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$offset, this.$interactionSource, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x006c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
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
                goto L6f
            L1c:
                r1 = r9
                r3 = 0
                java.lang.Object r4 = r1.L$0
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r4 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState) r4
                kotlin.ResultKt.throwOnFailure(r10)
                goto L4d
            L26:
                kotlin.ResultKt.throwOnFailure(r10)
                r1 = r9
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = r1.this$0
                androidx.compose.foundation.interaction.PressInteraction$Press r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getPressInteraction$p(r3)
                if (r3 == 0) goto L52
                androidx.compose.foundation.interaction.MutableInteractionSource r4 = r1.$interactionSource
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r5 = r1.this$0
                r6 = 0
                androidx.compose.foundation.interaction.PressInteraction$Cancel r7 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
                r7.<init>(r3)
                r3 = r7
                androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
                r1.L$0 = r5
                r8 = 1
                r1.label = r8
                java.lang.Object r3 = r4.emit(r3, r1)
                if (r3 != r0) goto L4b
                return r0
            L4b:
                r4 = r5
                r3 = r6
            L4d:
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$setPressInteraction$p(r4, r2)
            L52:
                androidx.compose.foundation.interaction.PressInteraction$Press r3 = new androidx.compose.foundation.interaction.PressInteraction$Press
                long r4 = r1.$offset
                r3.<init>(r4, r2)
                androidx.compose.foundation.interaction.MutableInteractionSource r2 = r1.$interactionSource
                r4 = r3
                androidx.compose.foundation.interaction.Interaction r4 = (androidx.compose.foundation.interaction.Interaction) r4
                r5 = r1
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r1.L$0 = r3
                r6 = 2
                r1.label = r6
                java.lang.Object r2 = r2.emit(r4, r5)
                if (r2 != r0) goto L6d
                return r0
            L6d:
                r0 = r1
                r1 = r3
            L6f:
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r2 = r0.this$0
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$setPressInteraction$p(r2, r1)
                kotlin.Unit r2 = kotlin.Unit.INSTANCE
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0060  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            switch(r1) {
                case 0: goto L20;
                case 1: goto L19;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L12:
            r0 = r11
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L86
        L19:
            r1 = r11
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r1
            r1 = r12
            goto L52
        L20:
            kotlin.ResultKt.throwOnFailure(r12)
            r1 = r11
            java.lang.Object r2 = r1.L$0
            r3 = r2
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1 r4 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r5 = r1.this$0
            long r6 = r1.$offset
            androidx.compose.foundation.interaction.MutableInteractionSource r8 = r1.$interactionSource
            r9 = 0
            r4.<init>(r5, r6, r8, r9)
            r6 = r4
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r3, r4, r5, r6, r7, r8)
            androidx.compose.foundation.gestures.PressGestureScope r2 = r1.$$this$detectTapAndPress
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 1
            r1.label = r4
            java.lang.Object r2 = r2.tryAwaitRelease(r3)
            if (r2 != r0) goto L4e
            return r0
        L4e:
            r10 = r1
            r1 = r12
            r12 = r2
            r2 = r10
        L52:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = r2.this$0
            androidx.compose.foundation.interaction.PressInteraction$Press r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getPressInteraction$p(r3)
            if (r3 == 0) goto L8a
            androidx.compose.foundation.interaction.MutableInteractionSource r4 = r2.$interactionSource
            r5 = 0
            if (r12 == 0) goto L6e
            androidx.compose.foundation.interaction.PressInteraction$Release r12 = new androidx.compose.foundation.interaction.PressInteraction$Release
            r12.<init>(r3)
            androidx.compose.foundation.interaction.PressInteraction r12 = (androidx.compose.foundation.interaction.PressInteraction) r12
            goto L75
        L6e:
            androidx.compose.foundation.interaction.PressInteraction$Cancel r12 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
            r12.<init>(r3)
            androidx.compose.foundation.interaction.PressInteraction r12 = (androidx.compose.foundation.interaction.PressInteraction) r12
        L75:
            r3 = r12
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            r6 = 2
            r2.label = r6
            java.lang.Object r12 = r4.emit(r3, r2)
            if (r12 != r0) goto L83
            return r0
        L83:
            r12 = r1
            r0 = r2
            r1 = r5
        L86:
            r1 = r12
            r2 = r0
        L8a:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r12 = r2.this$0
            r0 = 0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$setPressInteraction$p(r12, r0)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
