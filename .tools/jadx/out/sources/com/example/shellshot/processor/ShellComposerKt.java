package com.example.shellshot.processor;

import android.graphics.RectF;
import com.example.shellshot.template.ScreenRect;
import com.example.shellshot.template.ShellTemplate;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* compiled from: ShellComposer.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a$\u0010\u0003\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0014\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u001c\u0010\f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u0002H\u0002\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0010"}, d2 = {"toRectF", "Landroid/graphics/RectF;", "Lcom/example/shellshot/template/ScreenRect;", "scaledTo", "outputWidth", "", "outputHeight", "template", "Lcom/example/shellshot/template/ShellTemplate;", "inset", "delta", "", "coerceWithin", "isValid", "", "expanded", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ShellComposerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final RectF toRectF(ScreenRect $this$toRectF) {
        return new RectF($this$toRectF.getLeft(), $this$toRectF.getTop(), $this$toRectF.getRight(), $this$toRectF.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScreenRect scaledTo(ScreenRect $this$scaledTo, int outputWidth, int outputHeight, ShellTemplate template) {
        Integer valueOf = Integer.valueOf(template.getOutputWidth());
        if ((valueOf.intValue() > 0 ? 1 : 0) == 0) {
            valueOf = null;
        }
        int designWidth = valueOf != null ? valueOf.intValue() : outputWidth;
        Integer valueOf2 = Integer.valueOf(template.getOutputHeight());
        Integer num = valueOf2.intValue() > 0 ? valueOf2 : null;
        int designHeight = num != null ? num.intValue() : outputHeight;
        if (designWidth == outputWidth && designHeight == outputHeight) {
            return $this$scaledTo;
        }
        float scaleX = outputWidth / designWidth;
        float scaleY = outputHeight / designHeight;
        return new ScreenRect((int) Math.rint($this$scaledTo.getLeft() * scaleX), (int) Math.rint($this$scaledTo.getTop() * scaleY), (int) Math.rint($this$scaledTo.getRight() * scaleX), (int) Math.rint($this$scaledTo.getBottom() * scaleY));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScreenRect inset(ScreenRect $this$inset, float delta) {
        if (delta == 0.0f) {
            return $this$inset;
        }
        int rounded = (int) Math.rint(delta);
        return new ScreenRect($this$inset.getLeft() + rounded, $this$inset.getTop() + rounded, $this$inset.getRight() - rounded, $this$inset.getBottom() - rounded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScreenRect coerceWithin(ScreenRect $this$coerceWithin, int outputWidth, int outputHeight) {
        int safeLeft = RangesKt.coerceIn($this$coerceWithin.getLeft(), 0, outputWidth);
        int safeTop = RangesKt.coerceIn($this$coerceWithin.getTop(), 0, outputHeight);
        int safeRight = RangesKt.coerceIn($this$coerceWithin.getRight(), safeLeft, outputWidth);
        int safeBottom = RangesKt.coerceIn($this$coerceWithin.getBottom(), safeTop, outputHeight);
        return new ScreenRect(safeLeft, safeTop, safeRight, safeBottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isValid(ScreenRect $this$isValid) {
        return $this$isValid.getWidth() > 0 && $this$isValid.getHeight() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RectF expanded(RectF $this$expanded, float delta) {
        if (delta <= 0.0f) {
            return $this$expanded;
        }
        return new RectF($this$expanded.left - delta, $this$expanded.top - delta, $this$expanded.right + delta, $this$expanded.bottom + delta);
    }
}
