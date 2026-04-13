package androidx.compose.material3.carousel;

import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.shape.GenericShape;
import androidx.compose.material.OutlinedTextFieldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* compiled from: CarouselItemScope.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0017¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0017¢\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0017¢\u0006\u0002\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/material3/carousel/CarouselItemScopeImpl;", "Landroidx/compose/material3/carousel/CarouselItemScope;", "itemInfo", "Landroidx/compose/material3/carousel/CarouselItemInfo;", "(Landroidx/compose/material3/carousel/CarouselItemInfo;)V", "carouselItemInfo", "getCarouselItemInfo", "()Landroidx/compose/material3/carousel/CarouselItemInfo;", "rememberMaskShape", "Landroidx/compose/foundation/shape/GenericShape;", "shape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/shape/GenericShape;", "maskBorder", "Landroidx/compose/ui/Modifier;", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "maskClip", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CarouselItemScopeImpl implements CarouselItemScope {
    public static final int $stable = 8;
    private final CarouselItemInfo itemInfo;

    public CarouselItemScopeImpl(CarouselItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    @Override // androidx.compose.material3.carousel.CarouselItemScope
    /* renamed from: getCarouselItemInfo, reason: from getter */
    public CarouselItemInfo getItemInfo() {
        return this.itemInfo;
    }

    @Override // androidx.compose.material3.carousel.CarouselItemScope
    public Modifier maskClip(Modifier $this$maskClip, Shape shape, Composer $composer, int $changed) {
        $composer.startReplaceGroup(440683050);
        ComposerKt.sourceInformation($composer, "C(maskClip)80@3238L32:CarouselItemScope.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(440683050, $changed, -1, "androidx.compose.material3.carousel.CarouselItemScopeImpl.maskClip (CarouselItemScope.kt:80)");
        }
        Modifier clip = ClipKt.clip($this$maskClip, rememberMaskShape(shape, $composer, (($changed >> 3) & 14) | (($changed >> 3) & 112)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return clip;
    }

    @Override // androidx.compose.material3.carousel.CarouselItemScope
    public Modifier maskBorder(Modifier $this$maskBorder, BorderStroke border, Shape shape, Composer $composer, int $changed) {
        $composer.startReplaceGroup(610897768);
        ComposerKt.sourceInformation($composer, "C(maskBorder)84@3397L32:CarouselItemScope.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(610897768, $changed, -1, "androidx.compose.material3.carousel.CarouselItemScopeImpl.maskBorder (CarouselItemScope.kt:84)");
        }
        Modifier border2 = BorderKt.border($this$maskBorder, border, rememberMaskShape(shape, $composer, (($changed >> 6) & 14) | (($changed >> 6) & 112)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return border2;
    }

    @Override // androidx.compose.material3.carousel.CarouselItemScope
    public GenericShape rememberMaskShape(final Shape shape, Composer $composer, int $changed) {
        Object value$iv;
        $composer.startReplaceGroup(152582312);
        ComposerKt.sourceInformation($composer, "C(rememberMaskShape)88@3548L7,89@3571L319:CarouselItemScope.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(152582312, $changed, -1, "androidx.compose.material3.carousel.CarouselItemScopeImpl.rememberMaskShape (CarouselItemScope.kt:87)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) consume;
        Object itemInfo = getItemInfo();
        ComposerKt.sourceInformationMarkerStart($composer, 332766031, "CC(remember):CarouselItemScope.kt#9igjgp");
        boolean invalid$iv = $composer.changed(itemInfo) | $composer.changed(density);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new GenericShape(new Function3<Path, Size, LayoutDirection, Unit>() { // from class: androidx.compose.material3.carousel.CarouselItemScopeImpl$rememberMaskShape$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Path path, Size size, LayoutDirection layoutDirection) {
                    m2886invoke12SF9DM(path, size.getPackedValue(), layoutDirection);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-12SF9DM, reason: not valid java name */
                public final void m2886invoke12SF9DM(Path $this$$receiver, long size, LayoutDirection direction) {
                    Rect rect = CarouselItemScopeImpl.this.getItemInfo().getMaskRect().intersect(SizeKt.m4012toRectuvyYCjk(size));
                    OutlineKt.addOutline($this$$receiver, shape.mo275createOutlinePq9zytI(rect.m3955getSizeNHjbRc(), direction, density));
                    $this$$receiver.mo4058translatek4lQ0M(OffsetKt.Offset(rect.getLeft(), rect.getTop()));
                }
            });
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        GenericShape genericShape = (GenericShape) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return genericShape;
    }
}
