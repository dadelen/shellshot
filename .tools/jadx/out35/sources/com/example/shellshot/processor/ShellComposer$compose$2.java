package com.example.shellshot.processor;

import android.graphics.Bitmap;
import com.example.shellshot.template.ShellTemplate;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ShellComposer.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.processor.ShellComposer$compose$2", f = "ShellComposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes10.dex */
final class ShellComposer$compose$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ Bitmap $frameBitmap;
    final /* synthetic */ Bitmap $screenMaskBitmap;
    final /* synthetic */ Bitmap $sourceBitmap;
    final /* synthetic */ ShellTemplate $template;
    int label;
    final /* synthetic */ ShellComposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ShellComposer$compose$2(ShellComposer shellComposer, Bitmap bitmap, Bitmap bitmap2, ShellTemplate shellTemplate, Bitmap bitmap3, Continuation<? super ShellComposer$compose$2> continuation) {
        super(2, continuation);
        this.this$0 = shellComposer;
        this.$sourceBitmap = bitmap;
        this.$frameBitmap = bitmap2;
        this.$template = shellTemplate;
        this.$screenMaskBitmap = bitmap3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ShellComposer$compose$2(this.this$0, this.$sourceBitmap, this.$frameBitmap, this.$template, this.$screenMaskBitmap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((ShellComposer$compose$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Bitmap composeInternal;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                composeInternal = this.this$0.composeInternal(this.$sourceBitmap, this.$frameBitmap, this.$template, this.$screenMaskBitmap);
                return composeInternal;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
