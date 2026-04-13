package androidx.compose.ui.graphics.layer;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AndroidGraphicsLayer.android.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.graphics.layer.GraphicsLayer", f = "AndroidGraphicsLayer.android.kt", i = {}, l = {848}, m = "toImageBitmap", n = {}, s = {})
/* loaded from: classes13.dex */
final class GraphicsLayer$toImageBitmap$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GraphicsLayer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GraphicsLayer$toImageBitmap$1(GraphicsLayer graphicsLayer, Continuation<? super GraphicsLayer$toImageBitmap$1> continuation) {
        super(continuation);
        this.this$0 = graphicsLayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.toImageBitmap(this);
    }
}
