package com.example.shellshot.processor;

import android.graphics.RectF;
import com.example.shellshot.template.ScreenRect;
import kotlin.Metadata;

/* compiled from: ShellComposer.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"toRectF", "Landroid/graphics/RectF;", "Lcom/example/shellshot/template/ScreenRect;", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ShellComposerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final RectF toRectF(ScreenRect $this$toRectF) {
        return new RectF($this$toRectF.getLeft(), $this$toRectF.getTop(), $this$toRectF.getRight(), $this$toRectF.getBottom());
    }
}
