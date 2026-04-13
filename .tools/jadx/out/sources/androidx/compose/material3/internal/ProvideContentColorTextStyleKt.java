package androidx.compose.material3.internal;

import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: ProvideContentColorTextStyle.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"ProvideContentColorTextStyle", "", "contentColor", "Landroidx/compose/ui/graphics/Color;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "ProvideContentColorTextStyle-3J-VO9M", "(JLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ProvideContentColorTextStyleKt {
    /* renamed from: ProvideContentColorTextStyle-3J-VO9M, reason: not valid java name */
    public static final void m2906ProvideContentColorTextStyle3JVO9M(final long contentColor, final TextStyle textStyle, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-716124955);
        ComposerKt.sourceInformation($composer2, "C(ProvideContentColorTextStyle)P(1:c#ui.graphics.Color,2)39@1423L7,40@1452L151:ProvideContentColorTextStyle.kt#mqatfk");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(contentColor) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(textStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-716124955, $dirty, -1, "androidx.compose.material3.internal.ProvideContentColorTextStyle (ProvideContentColorTextStyle.kt:38)");
            }
            ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localTextStyle);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextStyle mergedStyle = ((TextStyle) consume).merge(textStyle);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(contentColor)), TextKt.getLocalTextStyle().provides(mergedStyle)}, function2, $composer2, ProvidedValue.$stable | (($dirty >> 3) & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.ProvideContentColorTextStyleKt$ProvideContentColorTextStyle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ProvideContentColorTextStyleKt.m2906ProvideContentColorTextStyle3JVO9M(contentColor, textStyle, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
