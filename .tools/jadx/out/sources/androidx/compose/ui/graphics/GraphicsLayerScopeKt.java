package androidx.compose.ui.graphics;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.platform.CompositionLocalsKt;
import kotlin.Metadata;

/* compiled from: GraphicsLayerScope.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\u0007\u001a\u00020\b\u001a\r\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"DefaultCameraDistance", "", "DefaultShadowColor", "Landroidx/compose/ui/graphics/Color;", "getDefaultShadowColor", "()J", "J", "GraphicsLayerScope", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "rememberGraphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class GraphicsLayerScopeKt {
    public static final float DefaultCameraDistance = 8.0f;
    private static final long DefaultShadowColor = Color.INSTANCE.m4189getBlack0d7_KjU();

    public static final long getDefaultShadowColor() {
        return DefaultShadowColor;
    }

    public static final GraphicsLayer rememberGraphicsLayer(Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 158092365, "C(rememberGraphicsLayer)257@10239L7,258@10258L53:GraphicsLayerScope.kt#aiphks");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(158092365, $changed, -1, "androidx.compose.ui.graphics.rememberGraphicsLayer (GraphicsLayerScope.kt:256)");
        }
        ProvidableCompositionLocal<GraphicsContext> localGraphicsContext = CompositionLocalsKt.getLocalGraphicsContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localGraphicsContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        GraphicsContext graphicsContext = (GraphicsContext) consume;
        ComposerKt.sourceInformationMarkerStart($composer, 1099487492, "CC(remember):GraphicsLayerScope.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new GraphicsContextObserver(graphicsContext);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        GraphicsLayer graphicsLayer = ((GraphicsContextObserver) value$iv).getGraphicsLayer();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return graphicsLayer;
    }

    public static final GraphicsLayerScope GraphicsLayerScope() {
        return new ReusableGraphicsLayerScope();
    }
}
