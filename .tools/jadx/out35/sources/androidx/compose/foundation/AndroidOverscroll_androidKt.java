package androidx.compose.foundation;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* compiled from: AndroidOverscroll.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"rememberOverscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/OverscrollEffect;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidOverscroll_androidKt {
    public static final OverscrollEffect rememberOverscrollEffect(Composer $composer, int $changed) {
        NoOpOverscrollEffect noOpOverscrollEffect;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1476348564, "C(rememberOverscrollEffect)64@2834L7,65@2888L7:AndroidOverscroll.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1476348564, $changed, -1, "androidx.compose.foundation.rememberOverscrollEffect (AndroidOverscroll.android.kt:63)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) consume;
        ProvidableCompositionLocal<OverscrollConfiguration> localOverscrollConfiguration = OverscrollConfiguration_androidKt.getLocalOverscrollConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localOverscrollConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        OverscrollConfiguration config = (OverscrollConfiguration) consume2;
        if (config != null) {
            $composer.startReplaceGroup(1586021609);
            ComposerKt.sourceInformation($composer, "67@2937L80");
            ComposerKt.sourceInformationMarkerStart($composer, 1852277609, "CC(remember):AndroidOverscroll.android.kt#9igjgp");
            boolean invalid$iv = $composer.changed(context) | $composer.changed(config);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new AndroidEdgeEffectOverscrollEffect(context, config);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            noOpOverscrollEffect = (AndroidEdgeEffectOverscrollEffect) value$iv;
        } else {
            $composer.startReplaceGroup(1586120933);
            $composer.endReplaceGroup();
            noOpOverscrollEffect = NoOpOverscrollEffect.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return noOpOverscrollEffect;
    }
}
