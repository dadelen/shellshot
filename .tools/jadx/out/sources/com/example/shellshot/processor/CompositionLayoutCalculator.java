package com.example.shellshot.processor;

import android.graphics.RectF;
import com.example.shellshot.template.ScaleMode;
import com.example.shellshot.template.ScreenRect;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShellComposer.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\u000e"}, d2 = {"Lcom/example/shellshot/processor/CompositionLayoutCalculator;", "", "<init>", "()V", "calculateCoverRect", "Landroid/graphics/RectF;", "sourceWidth", "", "sourceHeight", "targetRect", "Lcom/example/shellshot/template/ScreenRect;", "scaleMode", "Lcom/example/shellshot/template/ScaleMode;", "calculateDestinationRect", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class CompositionLayoutCalculator {
    public static final int $stable = 0;
    public static final CompositionLayoutCalculator INSTANCE = new CompositionLayoutCalculator();

    /* compiled from: ShellComposer.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScaleMode.values().length];
            try {
                iArr[ScaleMode.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ScaleMode.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private CompositionLayoutCalculator() {
    }

    public final RectF calculateCoverRect(int sourceWidth, int sourceHeight, ScreenRect targetRect, ScaleMode scaleMode) {
        float scale;
        Intrinsics.checkNotNullParameter(targetRect, "targetRect");
        Intrinsics.checkNotNullParameter(scaleMode, "scaleMode");
        float targetWidth = targetRect.getWidth();
        float targetHeight = targetRect.getHeight();
        switch (WhenMappings.$EnumSwitchMapping$0[scaleMode.ordinal()]) {
            case 1:
                scale = Math.min(targetWidth / sourceWidth, targetHeight / sourceHeight);
                break;
            case 2:
                scale = Math.max(targetWidth / sourceWidth, targetHeight / sourceHeight);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        float scaledWidth = sourceWidth * scale;
        float scaledHeight = sourceHeight * scale;
        float left = targetRect.getLeft() + ((targetWidth - scaledWidth) / 2.0f);
        float top = targetRect.getTop() + ((targetHeight - scaledHeight) / 2.0f);
        return new RectF(left, top, left + scaledWidth, top + scaledHeight);
    }

    public final RectF calculateDestinationRect(int sourceWidth, int sourceHeight, ScreenRect targetRect, ScaleMode scaleMode) {
        Intrinsics.checkNotNullParameter(targetRect, "targetRect");
        Intrinsics.checkNotNullParameter(scaleMode, "scaleMode");
        return calculateCoverRect(sourceWidth, sourceHeight, targetRect, scaleMode);
    }
}
