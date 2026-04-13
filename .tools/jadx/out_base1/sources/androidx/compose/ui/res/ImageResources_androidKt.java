package androidx.compose.ui.res;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageResources.android.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"imageResource", "Landroidx/compose/ui/graphics/ImageBitmap;", "Landroidx/compose/ui/graphics/ImageBitmap$Companion;", "res", "Landroid/content/res/Resources;", "id", "", "(Landroidx/compose/ui/graphics/ImageBitmap$Companion;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/ImageBitmap;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ImageResources_androidKt {
    public static final ImageBitmap imageResource(ImageBitmap.Companion $this$imageResource, Resources res, int id) {
        Drawable drawable = res.getDrawable(id, null);
        Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
        return AndroidImageBitmap_androidKt.asImageBitmap(((BitmapDrawable) drawable).getBitmap());
    }

    public static final ImageBitmap imageResource(ImageBitmap.Companion $this$imageResource, int id, Composer $composer, int $changed) {
        Object value$iv;
        Object value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, -304919470, "C(imageResource)53@2046L7,54@2070L25,57@2238L54:ImageResources.android.kt#ccshc7");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-304919470, $changed, -1, "androidx.compose.ui.res.imageResource (ImageResources.android.kt:52)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) consume;
        ComposerKt.sourceInformationMarkerStart($composer, 552189435, "CC(remember):ImageResources.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new TypedValue();
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        TypedValue value = (TypedValue) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        context.getResources().getValue(id, value, true);
        CharSequence charSequence = value.string;
        Intrinsics.checkNotNull(charSequence);
        String key = charSequence.toString();
        ComposerKt.sourceInformationMarkerStart($composer, 552194840, "CC(remember):ImageResources.android.kt#9igjgp");
        boolean invalid$iv = $composer.changed(key);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = imageResource($this$imageResource, context.getResources(), id);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ImageBitmap imageBitmap = (ImageBitmap) value$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return imageBitmap;
    }
}
