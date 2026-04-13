package com.example.shellshot.processor;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ShellComposer.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.processor.ShellComposer", f = "ShellComposer.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX, 50, 53}, m = "compose", n = {"sourceBitmap", "template", "sourceBitmap", "template", "frameBitmap", "sourceBitmap", "template", "frameBitmap", "screenMaskBitmap"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes10.dex */
final class ShellComposer$compose$3 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ShellComposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ShellComposer$compose$3(ShellComposer shellComposer, Continuation<? super ShellComposer$compose$3> continuation) {
        super(continuation);
        this.this$0 = shellComposer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.compose(null, null, this);
    }
}
