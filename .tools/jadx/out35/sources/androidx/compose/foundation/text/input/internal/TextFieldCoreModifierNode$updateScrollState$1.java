package androidx.compose.foundation.text.input.internal;

import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TextFieldCoreModifier.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$updateScrollState$1", f = "TextFieldCoreModifier.kt", i = {}, l = {448, 451}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextFieldCoreModifierNode$updateScrollState$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ float $offsetDifference;
    final /* synthetic */ Rect $rawCursorRect;
    int label;
    final /* synthetic */ TextFieldCoreModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldCoreModifierNode$updateScrollState$1(TextFieldCoreModifierNode textFieldCoreModifierNode, float f, Rect rect, Continuation<? super TextFieldCoreModifierNode$updateScrollState$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldCoreModifierNode;
        this.$offsetDifference = f;
        this.$rawCursorRect = rect;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextFieldCoreModifierNode$updateScrollState$1(this.this$0, this.$offsetDifference, this.$rawCursorRect, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldCoreModifierNode$updateScrollState$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0053 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0054  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L1c;
                case 1: goto L17;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L12:
            r0 = r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L55
        L17:
            r1 = r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L3b
        L1c:
            kotlin.ResultKt.throwOnFailure(r7)
            r1 = r6
            androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode r2 = r1.this$0
            androidx.compose.foundation.ScrollState r2 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.access$getScrollState$p(r2)
            androidx.compose.foundation.gestures.ScrollableState r2 = (androidx.compose.foundation.gestures.ScrollableState) r2
            float r3 = r1.$offsetDifference
            float r3 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierKt.access$roundToNext(r3)
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 1
            r1.label = r5
            java.lang.Object r2 = androidx.compose.foundation.gestures.ScrollExtensionsKt.scrollBy(r2, r3, r4)
            if (r2 != r0) goto L3b
            return r0
        L3b:
            androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode r2 = r1.this$0
            androidx.compose.foundation.text.input.internal.TextLayoutState r2 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.access$getTextLayoutState$p(r2)
            androidx.compose.foundation.relocation.BringIntoViewRequester r2 = r2.getBringIntoViewRequester()
            androidx.compose.ui.geometry.Rect r3 = r1.$rawCursorRect
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 2
            r1.label = r5
            java.lang.Object r2 = r2.bringIntoView(r3, r4)
            if (r2 != r0) goto L54
            return r0
        L54:
            r0 = r1
        L55:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$updateScrollState$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
