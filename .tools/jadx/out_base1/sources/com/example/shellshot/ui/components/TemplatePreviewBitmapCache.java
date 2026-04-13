package com.example.shellshot.ui.components;

import android.util.LruCache;
import androidx.compose.ui.graphics.ImageBitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplatePreviewThumbnail.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0006J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0007R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/example/shellshot/ui/components/TemplatePreviewBitmapCache;", "", "<init>", "()V", "cache", "Landroid/util/LruCache;", "", "Landroidx/compose/ui/graphics/ImageBitmap;", "get", "key", "put", "", "bitmap", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
final class TemplatePreviewBitmapCache {
    public static final TemplatePreviewBitmapCache INSTANCE = new TemplatePreviewBitmapCache();
    private static final LruCache<String, ImageBitmap> cache = new LruCache<>(32);

    private TemplatePreviewBitmapCache() {
    }

    public final ImageBitmap get(String key) {
        ImageBitmap imageBitmap;
        Intrinsics.checkNotNullParameter(key, "key");
        synchronized (cache) {
            imageBitmap = cache.get(key);
        }
        return imageBitmap;
    }

    public final void put(String key, ImageBitmap bitmap) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        synchronized (cache) {
            cache.put(key, bitmap);
        }
    }
}
