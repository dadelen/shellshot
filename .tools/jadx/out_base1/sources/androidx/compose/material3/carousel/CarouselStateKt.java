package androidx.compose.material3.carousel;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: CarouselState.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"rememberCarouselState", "Landroidx/compose/material3/carousel/CarouselState;", "initialItem", "", "itemCount", "Lkotlin/Function0;", "(ILkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/carousel/CarouselState;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CarouselStateKt {
    public static final CarouselState rememberCarouselState(final int initialItem, final Function0<Integer> function0, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -217285684, "C(rememberCarouselState)*103@3725L181,103@3679L227:CarouselState.kt#dcf9yb");
        boolean z = true;
        if ((i & 1) != 0) {
            initialItem = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-217285684, $changed, -1, "androidx.compose.material3.carousel.rememberCarouselState (CarouselState.kt:102)");
        }
        Object[] objArr = new Object[0];
        Saver<CarouselState, ?> saver = CarouselState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, -1536566770, "CC(remember):CarouselState.kt#9igjgp");
        boolean z2 = ((($changed & 14) ^ 6) > 4 && $composer.changed(initialItem)) || ($changed & 6) == 4;
        if (((($changed & 112) ^ 48) <= 32 || !$composer.changed(function0)) && ($changed & 48) != 32) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<CarouselState>() { // from class: androidx.compose.material3.carousel.CarouselStateKt$rememberCarouselState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final CarouselState invoke() {
                    return new CarouselState(initialItem, 0.0f, function0);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object m3749rememberSaveable = RememberSaveableKt.m3749rememberSaveable(objArr, saver, (String) null, (Function0<? extends Object>) value$iv, $composer, 0, 4);
        CarouselState $this$rememberCarouselState_u24lambda_u241 = (CarouselState) m3749rememberSaveable;
        $this$rememberCarouselState_u24lambda_u241.getItemCountState().setValue(function0);
        CarouselState carouselState = (CarouselState) m3749rememberSaveable;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return carouselState;
    }
}
