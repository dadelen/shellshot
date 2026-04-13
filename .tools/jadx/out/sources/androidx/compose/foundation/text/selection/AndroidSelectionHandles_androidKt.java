package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AndroidSelectionHandles.android.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0001¢\u0006\u0002\u0010\t\u001aD\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a+\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u0019\u001a\u00020\rH\u0001¢\u0006\u0002\u0010\u001a\u001a\u0014\u0010\u001b\u001a\u00020\u001c*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0000\u001a\"\u0010 \u001a\u00020\u0014*\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u0019\u001a\u00020\rH\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"HandlePopup", "", "positionProvider", "Landroidx/compose/foundation/text/selection/OffsetProvider;", "handleReferencePoint", "Landroidx/compose/ui/Alignment;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/OffsetProvider;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SelectionHandle", "offsetProvider", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "handlesCrossed", "minTouchTargetSize", "Landroidx/compose/ui/unit/DpSize;", "modifier", "Landroidx/compose/ui/Modifier;", "SelectionHandle-pzduO1o", "(Landroidx/compose/foundation/text/selection/OffsetProvider;ZLandroidx/compose/ui/text/style/ResolvedTextDirection;ZJLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SelectionHandleIcon", "iconVisible", "isLeft", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "createHandleImage", "Landroidx/compose/ui/graphics/ImageBitmap;", "Landroidx/compose/ui/draw/CacheDrawScope;", "radius", "", "drawSelectionHandle", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidSelectionHandles_androidKt {
    /* JADX WARN: Removed duplicated region for block: B:45:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x011a  */
    /* renamed from: SelectionHandle-pzduO1o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1309SelectionHandlepzduO1o(final androidx.compose.foundation.text.selection.OffsetProvider r18, final boolean r19, androidx.compose.ui.text.style.ResolvedTextDirection r20, boolean r21, long r22, final androidx.compose.ui.Modifier r24, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 481
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt.m1309SelectionHandlepzduO1o(androidx.compose.foundation.text.selection.OffsetProvider, boolean, androidx.compose.ui.text.style.ResolvedTextDirection, boolean, long, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void SelectionHandleIcon(final Modifier modifier, final Function0<Boolean> function0, final boolean isLeft, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2111672474);
        ComposerKt.sourceInformation($composer2, "C(SelectionHandleIcon)P(2)123@5009L129:AndroidSelectionHandles.android.kt#eksfi3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(isLeft) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2111672474, $dirty, -1, "androidx.compose.foundation.text.selection.SelectionHandleIcon (AndroidSelectionHandles.android.kt:122)");
            }
            SpacerKt.Spacer(drawSelectionHandle(SizeKt.m719sizeVpY3zN4(modifier, SelectionHandlesKt.getHandleWidth(), SelectionHandlesKt.getHandleHeight()), function0, isLeft), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandleIcon$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    AndroidSelectionHandles_androidKt.SelectionHandleIcon(Modifier.this, function0, isLeft, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final Modifier drawSelectionHandle(Modifier $this$drawSelectionHandle, final Function0<Boolean> function0, final boolean isLeft) {
        return ComposedModifierKt.composed$default($this$drawSelectionHandle, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv;
                $composer.startReplaceGroup(-196777734);
                ComposerKt.sourceInformation($composer, "C134@5309L7,135@5352L742:AndroidSelectionHandles.android.kt#eksfi3");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-196777734, $changed, -1, "androidx.compose.foundation.text.selection.drawSelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:134)");
                }
                ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer.consume(localTextSelectionColors);
                ComposerKt.sourceInformationMarkerEnd($composer);
                final long handleColor = ((SelectionColors) consume).getSelectionHandleColor();
                ComposerKt.sourceInformationMarkerStart($composer, 1976982956, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
                boolean invalid$iv = $composer.changed(handleColor) | $composer.changed(function0) | $composer.changed(isLeft);
                final Function0<Boolean> function02 = function0;
                final boolean z = isLeft;
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function1) new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DrawResult invoke(CacheDrawScope $this$drawWithCache) {
                            float radius = Size.m3991getWidthimpl($this$drawWithCache.m3812getSizeNHjbRc()) / 2.0f;
                            final ImageBitmap handleImage = AndroidSelectionHandles_androidKt.createHandleImage($this$drawWithCache, radius);
                            final ColorFilter colorFilter = ColorFilter.Companion.m4204tintxETnrds$default(ColorFilter.INSTANCE, handleColor, 0, 2, null);
                            final Function0<Boolean> function03 = function02;
                            final boolean z2 = z;
                            return $this$drawWithCache.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                                    invoke2(contentDrawScope);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(ContentDrawScope $this$onDrawWithContent) {
                                    DrawContext $this$withTransform_u24lambda_u246$iv$iv;
                                    long previousSize$iv$iv;
                                    $this$onDrawWithContent.drawContent();
                                    if (function03.invoke().booleanValue()) {
                                        if (z2) {
                                            ContentDrawScope $this$scale_u2dFgt4K4Q_u24default$iv = $this$onDrawWithContent;
                                            ImageBitmap imageBitmap = handleImage;
                                            ColorFilter colorFilter2 = colorFilter;
                                            long pivot$iv = $this$scale_u2dFgt4K4Q_u24default$iv.mo4712getCenterF1C5BW0();
                                            DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$scale_u2dFgt4K4Q_u24default$iv.getDrawContext();
                                            long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo4634getSizeNHjbRc();
                                            $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
                                            try {
                                                DrawTransform $this$scale_Fgt4K4Q_u24lambda_u242$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
                                                $this$scale_Fgt4K4Q_u24lambda_u242$iv.mo4641scale0AR0LA0(-1.0f, 1.0f, pivot$iv);
                                                previousSize$iv$iv = previousSize$iv$iv2;
                                            } catch (Throwable th) {
                                                th = th;
                                                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                                previousSize$iv$iv = previousSize$iv$iv2;
                                            }
                                            try {
                                                DrawScope.m4697drawImagegbVJVH8$default($this$scale_u2dFgt4K4Q_u24default$iv, imageBitmap, 0L, 0.0f, null, colorFilter2, 0, 46, null);
                                                $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().restore();
                                                $this$withTransform_u24lambda_u246$iv$iv2.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                                                return;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                                                $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                                                throw th;
                                            }
                                        }
                                        DrawScope.m4697drawImagegbVJVH8$default($this$onDrawWithContent, handleImage, 0L, 0.0f, null, colorFilter, 0, 46, null);
                                    }
                                }
                            });
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier drawWithCache = DrawModifierKt.drawWithCache($this$composed, (Function1) value$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return drawWithCache;
            }
        }, 1, null);
    }

    public static final ImageBitmap createHandleImage(CacheDrawScope $this$createHandleImage, float radius) {
        int edge;
        ImageBitmap imageBitmap;
        Canvas canvas;
        CanvasDrawScope drawScope;
        int edge2 = ((int) Math.ceil(radius)) * 2;
        ImageBitmap imageBitmap2 = HandleImageCache.INSTANCE.getImageBitmap();
        Canvas canvas2 = HandleImageCache.INSTANCE.getCanvas();
        CanvasDrawScope drawScope2 = HandleImageCache.INSTANCE.getCanvasDrawScope();
        if (imageBitmap2 == null || canvas2 == null || edge2 > imageBitmap2.getWidth() || edge2 > imageBitmap2.getHeight()) {
            ImageBitmap imageBitmap3 = ImageBitmapKt.m4388ImageBitmapx__hDU$default(edge2, edge2, ImageBitmapConfig.INSTANCE.m4382getAlpha8_sVssgQ(), false, null, 24, null);
            edge = edge2;
            HandleImageCache.INSTANCE.setImageBitmap(imageBitmap3);
            Canvas canvas3 = CanvasKt.Canvas(imageBitmap3);
            HandleImageCache.INSTANCE.setCanvas(canvas3);
            imageBitmap = imageBitmap3;
            canvas = canvas3;
        } else {
            edge = edge2;
            imageBitmap = imageBitmap2;
            canvas = canvas2;
        }
        if (drawScope2 == null) {
            CanvasDrawScope drawScope3 = new CanvasDrawScope();
            HandleImageCache.INSTANCE.setCanvasDrawScope(drawScope3);
            drawScope = drawScope3;
        } else {
            drawScope = drawScope2;
        }
        LayoutDirection layoutDirection$iv = $this$createHandleImage.getLayoutDirection();
        long size$iv = androidx.compose.ui.geometry.SizeKt.Size(imageBitmap.getWidth(), imageBitmap.getHeight());
        CanvasDrawScope this_$iv = drawScope;
        CanvasDrawScope.DrawParams drawParams = this_$iv.getDrawParams();
        Density prevDensity$iv = drawParams.getDensity();
        LayoutDirection prevLayoutDirection$iv = drawParams.getLayoutDirection();
        Canvas prevCanvas$iv = drawParams.getCanvas();
        long prevSize$iv = drawParams.getSize();
        CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u240$iv = this_$iv.getDrawParams();
        $this$draw_yzxVdVo_u24lambda_u240$iv.setDensity($this$createHandleImage);
        $this$draw_yzxVdVo_u24lambda_u240$iv.setLayoutDirection(layoutDirection$iv);
        $this$draw_yzxVdVo_u24lambda_u240$iv.setCanvas(canvas);
        $this$draw_yzxVdVo_u24lambda_u240$iv.m4633setSizeuvyYCjk(size$iv);
        canvas.save();
        CanvasDrawScope $this$createHandleImage_u24lambda_u241 = this_$iv;
        DrawScope.m4707drawRectnJ9OG0$default($this$createHandleImage_u24lambda_u241, Color.INSTANCE.m4189getBlack0d7_KjU(), 0L, $this$createHandleImage_u24lambda_u241.mo4713getSizeNHjbRc(), 0.0f, null, null, BlendMode.INSTANCE.m4078getClear0nO6VwU(), 58, null);
        DrawScope.m4707drawRectnJ9OG0$default($this$createHandleImage_u24lambda_u241, ColorKt.Color(4278190080L), Offset.INSTANCE.m3938getZeroF1C5BW0(), androidx.compose.ui.geometry.SizeKt.Size(radius, radius), 0.0f, null, null, 0, 120, null);
        ImageBitmap imageBitmap4 = imageBitmap;
        Canvas prevCanvas$iv2 = canvas;
        DrawScope.m4694drawCircleVaOC9Bg$default($this$createHandleImage_u24lambda_u241, ColorKt.Color(4278190080L), radius, OffsetKt.Offset(radius, radius), 0.0f, null, null, 0, 120, null);
        prevCanvas$iv2.restore();
        CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u241$iv = this_$iv.getDrawParams();
        $this$draw_yzxVdVo_u24lambda_u241$iv.setDensity(prevDensity$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv.setLayoutDirection(prevLayoutDirection$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv.setCanvas(prevCanvas$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv.m4633setSizeuvyYCjk(prevSize$iv);
        return imageBitmap4;
    }

    public static final void HandlePopup(final OffsetProvider positionProvider, final Alignment handleReferencePoint, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(476043083);
        ComposerKt.sourceInformation($composer2, "C(HandlePopup)P(2,1)248@8967L127,251@9099L190:AndroidSelectionHandles.android.kt#eksfi3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(positionProvider) : $composer2.changedInstance(positionProvider) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(handleReferencePoint) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(476043083, $dirty2, -1, "androidx.compose.foundation.text.selection.HandlePopup (AndroidSelectionHandles.android.kt:247)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1288574970, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
            boolean z = true;
            boolean z2 = ($dirty2 & 112) == 32;
            if (($dirty2 & 14) != 4 && (($dirty2 & 8) == 0 || !$composer2.changed(positionProvider))) {
                z = false;
            }
            boolean invalid$iv = z2 | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new HandlePositionProvider(handleReferencePoint, positionProvider);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            HandlePositionProvider popupPositionProvider = (HandlePositionProvider) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AndroidPopup_androidKt.Popup(popupPositionProvider, null, new PopupProperties(false, false, false, (SecureFlagPolicy) null, true, false, 15, (DefaultConstructorMarker) null), function2, $composer2, (($dirty2 << 3) & 7168) | 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$HandlePopup$1
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
                    AndroidSelectionHandles_androidKt.HandlePopup(OffsetProvider.this, handleReferencePoint, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
