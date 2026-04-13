package androidx.compose.foundation;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.TextureView;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AndroidExternalSurface.android.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a]\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00052\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\r\u0010\u0016\u001a\u00020\u0017H\u0003¢\u0006\u0002\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\u001aH\u0003¢\u0006\u0002\u0010\u001b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"AndroidEmbeddedExternalSurface", "", "modifier", "Landroidx/compose/ui/Modifier;", "isOpaque", "", "surfaceSize", "Landroidx/compose/ui/unit/IntSize;", "transform", "Landroidx/compose/ui/graphics/Matrix;", "onInit", "Lkotlin/Function1;", "Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "Lkotlin/ExtensionFunctionType;", "AndroidEmbeddedExternalSurface-sv6N_fY", "(Landroidx/compose/ui/Modifier;ZJ[FLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "AndroidExternalSurface", "zOrder", "Landroidx/compose/foundation/AndroidExternalSurfaceZOrder;", "isSecure", "AndroidExternalSurface-58FFMhA", "(Landroidx/compose/ui/Modifier;ZJIZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "rememberAndroidEmbeddedExternalSurfaceState", "Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "rememberAndroidExternalSurfaceState", "Landroidx/compose/foundation/AndroidExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidExternalSurfaceState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidExternalSurface_androidKt {
    private static final AndroidExternalSurfaceState rememberAndroidExternalSurfaceState(Composer $composer, int $changed) {
        Object value$iv$iv;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -873615933, "C(rememberAndroidExternalSurfaceState)190@7150L24,191@7186L47:AndroidExternalSurface.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-873615933, $changed, -1, "androidx.compose.foundation.rememberAndroidExternalSurfaceState (AndroidExternalSurface.android.kt:189)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart($composer, -954367824, "CC(remember):Effects.kt#9igjgp");
        Object it$iv$iv = $composer.rememberedValue();
        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        CoroutineScope scope = wrapper$iv.getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1983762950, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new AndroidExternalSurfaceState(scope);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        AndroidExternalSurfaceState androidExternalSurfaceState = (AndroidExternalSurfaceState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return androidExternalSurfaceState;
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x021e  */
    /* renamed from: AndroidExternalSurface-58FFMhA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m225AndroidExternalSurface58FFMhA(androidx.compose.ui.Modifier r23, boolean r24, long r25, int r27, boolean r28, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.AndroidExternalSurfaceScope, kotlin.Unit> r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instructions count: 573
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidExternalSurface_androidKt.m225AndroidExternalSurface58FFMhA(androidx.compose.ui.Modifier, boolean, long, int, boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final AndroidEmbeddedExternalSurfaceState rememberAndroidEmbeddedExternalSurfaceState(Composer $composer, int $changed) {
        Object value$iv$iv;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1057437053, "C(rememberAndroidEmbeddedExternalSurfaceState)384@15036L24,385@15072L55:AndroidExternalSurface.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1057437053, $changed, -1, "androidx.compose.foundation.rememberAndroidEmbeddedExternalSurfaceState (AndroidExternalSurface.android.kt:383)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart($composer, -954367824, "CC(remember):Effects.kt#9igjgp");
        Object it$iv$iv = $composer.rememberedValue();
        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        CoroutineScope scope = wrapper$iv.getCoroutineScope();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1142294264, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new AndroidEmbeddedExternalSurfaceState(scope);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = (AndroidEmbeddedExternalSurfaceState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return androidEmbeddedExternalSurfaceState;
    }

    /* renamed from: AndroidEmbeddedExternalSurface-sv6N_fY, reason: not valid java name */
    public static final void m224AndroidEmbeddedExternalSurfacesv6N_fY(Modifier modifier, boolean isOpaque, long surfaceSize, float[] transform, final Function1<? super AndroidExternalSurfaceScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean isOpaque2;
        final long surfaceSize2;
        final float[] transform2;
        Modifier modifier3;
        final boolean isOpaque3;
        Object value$iv;
        final long surfaceSize3;
        Object value$iv2;
        final Modifier modifier4;
        final long surfaceSize4;
        final boolean isOpaque4;
        final float[] transform3;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(217541314);
        ComposerKt.sourceInformation($composer2, "C(AndroidEmbeddedExternalSurface)P(1!1,3:c#ui.unit.IntSize,4:c#ui.graphics.Matrix)455@18917L45,458@18999L203,467@19273L485,457@18968L796:AndroidExternalSurface.android.kt#71ulvw");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            isOpaque2 = isOpaque;
        } else if (($changed & 48) == 0) {
            isOpaque2 = isOpaque;
            $dirty |= $composer2.changed(isOpaque2) ? 32 : 16;
        } else {
            isOpaque2 = isOpaque;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                surfaceSize2 = surfaceSize;
                if ($composer2.changed(surfaceSize2)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                surfaceSize2 = surfaceSize;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            surfaceSize2 = surfaceSize;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(transform != null ? Matrix.m4391boximpl(transform) : null) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            transform3 = transform;
            modifier4 = modifier2;
            isOpaque4 = isOpaque2;
            surfaceSize4 = surfaceSize2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    isOpaque2 = true;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    surfaceSize2 = IntSize.INSTANCE.m6801getZeroYbymL2g();
                }
                if (i5 != 0) {
                    modifier3 = modifier5;
                    transform2 = null;
                    isOpaque3 = isOpaque2;
                } else {
                    transform2 = transform;
                    modifier3 = modifier5;
                    isOpaque3 = isOpaque2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                transform2 = transform;
                modifier3 = modifier2;
                isOpaque3 = isOpaque2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(217541314, $dirty, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:454)");
            }
            final AndroidEmbeddedExternalSurfaceState state = rememberAndroidEmbeddedExternalSurfaceState($composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 184051342, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(state) | (((($dirty & 896) ^ 384) > 256 && $composer2.changed(surfaceSize2)) || ($dirty & 384) == 256) | ((57344 & $dirty) == 16384);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<Context, TextureView>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final TextureView invoke(Context context) {
                        TextureView $this$invoke_u24lambda_u240 = new TextureView(context);
                        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = AndroidEmbeddedExternalSurfaceState.this;
                        long j = surfaceSize2;
                        Function1<AndroidExternalSurfaceScope, Unit> function12 = function1;
                        androidEmbeddedExternalSurfaceState.m213setSurfaceSizeozmzZPI(j);
                        function12.invoke(androidEmbeddedExternalSurfaceState);
                        $this$invoke_u24lambda_u240.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
                        return $this$invoke_u24lambda_u240;
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            Function1 function12 = (Function1) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                    invoke2(textureView);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextureView it) {
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer2, 184060392, "CC(remember):AndroidExternalSurface.android.kt#9igjgp");
            boolean invalid$iv2 = (((($dirty & 896) ^ 384) > 256 && $composer2.changed(surfaceSize2)) || ($dirty & 384) == 256) | $composer2.changedInstance(state) | (($dirty & 112) == 32) | $composer2.changedInstance(transform2 != null ? Matrix.m4391boximpl(transform2) : null);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                surfaceSize3 = surfaceSize2;
                value$iv2 = new Function1<TextureView, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextureView textureView) {
                        invoke2(textureView);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextureView view) {
                        android.graphics.Matrix $this$invoke_u24lambda_u241_u24lambda_u240;
                        SurfaceTexture surfaceTexture;
                        if (!IntSize.m6794equalsimpl0(surfaceSize3, IntSize.INSTANCE.m6801getZeroYbymL2g()) && (surfaceTexture = view.getSurfaceTexture()) != null) {
                            surfaceTexture.setDefaultBufferSize(IntSize.m6796getWidthimpl(surfaceSize3), IntSize.m6795getHeightimpl(surfaceSize3));
                        }
                        state.m213setSurfaceSizeozmzZPI(surfaceSize3);
                        view.setOpaque(isOpaque3);
                        if (transform2 != null) {
                            AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = state;
                            float[] fArr = transform2;
                            $this$invoke_u24lambda_u241_u24lambda_u240 = androidEmbeddedExternalSurfaceState.getMatrix();
                            AndroidMatrixConversions_androidKt.m4033setFromEL8BTi8($this$invoke_u24lambda_u241_u24lambda_u240, fArr);
                        } else {
                            $this$invoke_u24lambda_u241_u24lambda_u240 = null;
                        }
                        view.setTransform($this$invoke_u24lambda_u241_u24lambda_u240);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
                surfaceSize3 = surfaceSize2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AndroidView_androidKt.AndroidView(function12, modifier3, androidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$2, null, (Function1) value$iv2, $composer2, (($dirty << 3) & 112) | 384, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            surfaceSize4 = surfaceSize3;
            isOpaque4 = isOpaque3;
            transform3 = transform2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4
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

                public final void invoke(Composer composer, int i6) {
                    AndroidExternalSurface_androidKt.m224AndroidEmbeddedExternalSurfacesv6N_fY(Modifier.this, isOpaque4, surfaceSize4, transform3, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
