package com.example.shellshot.template;

import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¨\u0006\u0005"}, d2 = {"normalizedWithin", "Lcom/example/shellshot/template/ScreenRect;", "width", "", "height", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class TemplateRepositoryKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final ScreenRect normalizedWithin(ScreenRect $this$normalizedWithin, int width, int height) {
        int left = RangesKt.coerceAtMost(RangesKt.coerceAtLeast($this$normalizedWithin.getLeft(), 0), width - 2);
        int top = RangesKt.coerceAtMost(RangesKt.coerceAtLeast($this$normalizedWithin.getTop(), 0), height - 2);
        int right = RangesKt.coerceAtMost(RangesKt.coerceAtLeast($this$normalizedWithin.getRight(), left + 1), width);
        int bottom = RangesKt.coerceAtMost(RangesKt.coerceAtLeast($this$normalizedWithin.getBottom(), top + 1), height);
        return new ScreenRect(left, top, right, bottom);
    }
}
