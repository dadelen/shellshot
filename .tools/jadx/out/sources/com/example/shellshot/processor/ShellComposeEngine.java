package com.example.shellshot.processor;

import android.graphics.Bitmap;
import com.example.shellshot.template.ShellTemplate;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShellComposeEngine.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/example/shellshot/processor/ShellComposeEngine;", "", "shellComposer", "Lcom/example/shellshot/processor/ShellComposer;", "<init>", "(Lcom/example/shellshot/processor/ShellComposer;)V", "compose", "Landroid/graphics/Bitmap;", "sourceBitmap", "template", "Lcom/example/shellshot/template/ShellTemplate;", "(Landroid/graphics/Bitmap;Lcom/example/shellshot/template/ShellTemplate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ShellComposeEngine {
    public static final int $stable = 8;
    private final ShellComposer shellComposer;

    public ShellComposeEngine(ShellComposer shellComposer) {
        Intrinsics.checkNotNullParameter(shellComposer, "shellComposer");
        this.shellComposer = shellComposer;
    }

    public final Object compose(Bitmap sourceBitmap, ShellTemplate template, Continuation<? super Bitmap> continuation) {
        return this.shellComposer.compose(sourceBitmap, template, continuation);
    }
}
