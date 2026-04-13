package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: Surface.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0087\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001ac\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u008f\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0095\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00072\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001f\u001a,\u0010 \u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u0010H\u0003ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a8\u0010&\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010'\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006*"}, d2 = {"Surface", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "color", "Landroidx/compose/ui/graphics/Color;", "contentColor", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "elevation", "Landroidx/compose/ui/unit/Dp;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Landroidx/compose/runtime/Composable;", "Surface-LPr_se0", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Surface-F-jzlyU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "selected", "Surface-Ny5ogXk", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "checked", "onCheckedChange", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "surfaceColorAtElevation", "elevationOverlay", "Landroidx/compose/material/ElevationOverlay;", "absoluteElevation", "surfaceColorAtElevation-cq6XJ1M", "(JLandroidx/compose/material/ElevationOverlay;FLandroidx/compose/runtime/Composer;I)J", "surface", "backgroundColor", "surface-8ww4TTg", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JLandroidx/compose/foundation/BorderStroke;F)Landroidx/compose/ui/Modifier;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SurfaceKt {
    /* renamed from: Surface-F-jzlyU, reason: not valid java name */
    public static final void m1660SurfaceFjzlyU(Modifier modifier, Shape shape, long color, long contentColor, BorderStroke border, float elevation, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        long color2;
        long contentColor2;
        BorderStroke border2;
        float f;
        final float elevation2;
        final Modifier modifier3;
        final Shape shape3;
        final long color3;
        final BorderStroke border3;
        final long contentColor3;
        final Shape shape4;
        final long color4;
        final BorderStroke border4;
        final float elevation3;
        final Modifier modifier4;
        int i2;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(1412203386);
        ComposerKt.sourceInformation($composer2, "C(Surface)P(5,6,1:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp)103@5115L6,104@5157L22,*109@5332L7,113@5495L755,110@5356L894:Surface.kt#jmzs0o");
        int $dirty = $changed;
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            shape2 = shape;
        } else if (($changed & 48) == 0) {
            shape2 = shape;
            $dirty |= $composer2.changed(shape2) ? 32 : 16;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i3 = 256;
                    $dirty |= i3;
                }
            } else {
                color2 = color;
            }
            i3 = 128;
            $dirty |= i3;
        } else {
            color2 = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                if ($composer2.changed(contentColor2)) {
                    i2 = 2048;
                    $dirty |= i2;
                }
            } else {
                contentColor2 = contentColor;
            }
            i2 = 1024;
            $dirty |= i2;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            border2 = border;
        } else if (($changed & 24576) == 0) {
            border2 = border;
            $dirty |= $composer2.changed(border2) ? 16384 : 8192;
        } else {
            border2 = border;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = elevation;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            f = elevation;
            $dirty |= $composer2.changed(f) ? 131072 : 65536;
        } else {
            f = elevation;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shape4 = shape2;
            contentColor3 = contentColor2;
            modifier4 = modifier2;
            elevation3 = f;
            color4 = color2;
            border4 = border2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1482getSurface0d7_KjU();
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(color2, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    border2 = null;
                }
                if (i7 != 0) {
                    elevation2 = Dp.m6626constructorimpl(0);
                    modifier3 = modifier2;
                    shape3 = shape2;
                    color3 = color2;
                    border3 = border2;
                } else {
                    elevation2 = f;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    color3 = color2;
                    border3 = border2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    elevation2 = f;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    color3 = color2;
                    border3 = border2;
                } else {
                    elevation2 = f;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    color3 = color2;
                    border3 = border2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1412203386, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:108)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) consume).m6640unboximpl();
            final float absoluteElevation = Dp.m6626constructorimpl(arg0$iv + elevation2);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(contentColor2)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m6624boximpl(absoluteElevation))}, ComposableLambdaKt.rememberComposableLambda(-1822160838, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0197  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
                    /*
                        Method dump skipped, instructions count: 411
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SurfaceKt$Surface$1.invoke(androidx.compose.runtime.Composer, int):void");
                }

                /* compiled from: Surface.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material.SurfaceKt$Surface$1$2", f = "Surface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material.SurfaceKt$Surface$1$2, reason: invalid class name */
                static final class AnonymousClass2 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                    int label;

                    AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentColor3 = contentColor2;
            shape4 = shape3;
            color4 = color3;
            border4 = border3;
            elevation3 = elevation2;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$2
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

                public final void invoke(Composer composer, int i8) {
                    SurfaceKt.m1660SurfaceFjzlyU(Modifier.this, shape4, color4, contentColor3, border4, elevation3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Surface-LPr_se0, reason: not valid java name */
    public static final void m1661SurfaceLPr_se0(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, long color, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        long color2;
        int i2;
        long contentColor2;
        int i3;
        int i4;
        final MutableInteractionSource interactionSource2;
        final BorderStroke border2;
        final float elevation2;
        final boolean enabled3;
        final Shape shape3;
        int $dirty;
        final long color3;
        final long contentColor3;
        final Modifier modifier3;
        final Shape shape4;
        final long color4;
        final BorderStroke border3;
        final float elevation3;
        final MutableInteractionSource interactionSource3;
        final boolean enabled4;
        int i5;
        int i6;
        Composer $composer2 = $composer.startRestartGroup(1560876237);
        ComposerKt.sourceInformation($composer2, "C(Surface)P(8,7,5,9,1:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp,6)212@10592L6,213@10634L22,*219@10866L7,223@11029L859,220@10890L998:Surface.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty2 |= $composer2.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty2 |= 3072;
            shape2 = shape;
        } else if (($changed & 3072) == 0) {
            shape2 = shape;
            $dirty2 |= $composer2.changed(shape2) ? 2048 : 1024;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i6 = 16384;
                    $dirty2 |= i6;
                }
            } else {
                color2 = color;
            }
            i6 = 8192;
            $dirty2 |= i6;
        } else {
            color2 = color;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                i2 = i7;
                contentColor2 = contentColor;
                if ($composer2.changed(contentColor2)) {
                    i5 = 131072;
                    $dirty2 |= i5;
                }
            } else {
                i2 = i7;
                contentColor2 = contentColor;
            }
            i5 = 65536;
            $dirty2 |= i5;
        } else {
            i2 = i7;
            contentColor2 = contentColor;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changed(border) ? 1048576 : 524288;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty2 |= 12582912;
            i3 = i11;
        } else if (($changed & 12582912) == 0) {
            i3 = i11;
            $dirty2 |= $composer2.changed(elevation) ? 8388608 : 4194304;
        } else {
            i3 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty2 |= 100663296;
            i4 = i12;
        } else if (($changed & 100663296) == 0) {
            i4 = i12;
            $dirty2 |= $composer2.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i4 = i12;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty2;
        if (($dirty2 & 306783379) == 306783378 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            long j = contentColor2;
            enabled4 = enabled2;
            contentColor3 = j;
            modifier3 = modifier2;
            shape4 = shape2;
            color4 = color2;
            border3 = border;
            elevation3 = elevation;
            interactionSource3 = interactionSource;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    enabled2 = true;
                }
                if (i9 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 16) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1482getSurface0d7_KjU();
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(color2, $composer2, ($dirty3 >> 12) & 14);
                    $dirty3 &= -458753;
                }
                BorderStroke border4 = i10 != 0 ? null : border;
                float elevation4 = i3 != 0 ? Dp.m6626constructorimpl(0) : elevation;
                if (i4 != 0) {
                    border2 = border4;
                    elevation2 = elevation4;
                    interactionSource2 = null;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    $dirty = $dirty3;
                    color3 = color2;
                } else {
                    interactionSource2 = interactionSource;
                    border2 = border4;
                    elevation2 = elevation4;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    $dirty = $dirty3;
                    color3 = color2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty3 & (-458753);
                    enabled3 = enabled2;
                    shape3 = shape2;
                    color3 = color2;
                } else {
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    $dirty = $dirty3;
                    color3 = color2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1560876237, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:218)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) consume).m6640unboximpl();
            final float absoluteElevation = Dp.m6626constructorimpl(arg0$iv + elevation2);
            final Function0<Unit> function03 = function02;
            final Modifier modifier4 = modifier2;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(contentColor2)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m6624boximpl(absoluteElevation))}, ComposableLambdaKt.rememberComposableLambda(2031491085, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$3
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x01a7  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
                    /*
                        Method dump skipped, instructions count: 427
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SurfaceKt$Surface$3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentColor3 = contentColor2;
            modifier3 = modifier4;
            shape4 = shape3;
            color4 = color3;
            border3 = border2;
            elevation3 = elevation2;
            interactionSource3 = interactionSource2;
            enabled4 = enabled3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$4
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

                public final void invoke(Composer composer, int i13) {
                    SurfaceKt.m1661SurfaceLPr_se0(function0, modifier3, enabled4, shape4, color4, contentColor3, border3, elevation3, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Surface-Ny5ogXk, reason: not valid java name */
    public static final void m1662SurfaceNy5ogXk(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, long color, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function02;
        Modifier modifier2;
        boolean z2;
        Shape shape2;
        final long color2;
        int $dirty;
        int $dirty1;
        int i2;
        int i3;
        int i4;
        int $dirty12;
        Modifier.Companion modifier3;
        long contentColor2;
        final MutableInteractionSource interactionSource2;
        final BorderStroke border2;
        final float elevation2;
        final boolean enabled2;
        final Shape shape3;
        int $dirty2;
        int i5;
        final long color3;
        final long contentColor3;
        final Shape shape4;
        final BorderStroke border3;
        final float elevation3;
        final MutableInteractionSource interactionSource3;
        final boolean enabled3;
        final Modifier modifier4;
        int $dirty3;
        int i6;
        int i7;
        Composer $composer2 = $composer.startRestartGroup(262027249);
        ComposerKt.sourceInformation($composer2, "C(Surface)P(9,8,7,5,10,1:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp,6)326@16318L6,327@16360L22,*333@16592L7,337@16755L901,334@16616L1040:Surface.kt#jmzs0o");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty4 |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty4 |= $composer2.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty4 |= 3072;
            z2 = enabled;
        } else if (($changed & 3072) == 0) {
            z2 = enabled;
            $dirty4 |= $composer2.changed(z2) ? 2048 : 1024;
        } else {
            z2 = enabled;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty4 |= 24576;
            shape2 = shape;
        } else if (($changed & 24576) == 0) {
            shape2 = shape;
            $dirty4 |= $composer2.changed(shape2) ? 16384 : 8192;
        } else {
            shape2 = shape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i7 = 131072;
                    $dirty4 |= i7;
                }
            } else {
                color2 = color;
            }
            i7 = 65536;
            $dirty4 |= i7;
        } else {
            color2 = color;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                if ($composer2.changed(contentColor)) {
                    i6 = 1048576;
                    $dirty = $dirty3 | i6;
                }
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            i6 = 524288;
            $dirty = $dirty3 | i6;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 12582912;
            i2 = i11;
        } else if (($changed & 12582912) == 0) {
            i2 = i11;
            $dirty |= $composer2.changed(border) ? 8388608 : 4194304;
        } else {
            i2 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
            i3 = i12;
        } else if (($changed & 100663296) == 0) {
            i3 = i12;
            $dirty |= $composer2.changed(elevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i12;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty |= 805306368;
            i4 = i13;
        } else if (($changed & 805306368) == 0) {
            i4 = i13;
            $dirty |= $composer2.changed(interactionSource) ? 536870912 : 268435456;
        } else {
            i4 = i13;
        }
        if ((i & 1024) != 0) {
            $dirty12 = $dirty1 | 6;
        } else {
            int $dirty13 = $changed1 & 6;
            if ($dirty13 == 0) {
                $dirty1 |= $composer2.changedInstance(function2) ? 4 : 2;
            }
            $dirty12 = $dirty1;
        }
        if (($dirty & 306783379) == 306783378 && ($dirty12 & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            contentColor3 = contentColor;
            elevation3 = elevation;
            modifier4 = modifier2;
            enabled3 = z2;
            shape4 = shape2;
            border3 = border;
            interactionSource3 = interactionSource;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : z2;
                if (i10 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 32) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1482getSurface0d7_KjU();
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(color2, $composer2, ($dirty >> 15) & 14);
                    $dirty &= -3670017;
                } else {
                    contentColor2 = contentColor;
                }
                BorderStroke border4 = i2 != 0 ? null : border;
                float elevation4 = i3 != 0 ? Dp.m6626constructorimpl(0) : elevation;
                if (i4 != 0) {
                    border2 = border4;
                    elevation2 = elevation4;
                    enabled2 = enabled4;
                    interactionSource2 = null;
                    shape3 = shape2;
                    $dirty2 = $dirty;
                    i5 = 262027249;
                    color3 = color2;
                } else {
                    interactionSource2 = interactionSource;
                    border2 = border4;
                    elevation2 = elevation4;
                    enabled2 = enabled4;
                    shape3 = shape2;
                    $dirty2 = $dirty;
                    i5 = 262027249;
                    color3 = color2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    $dirty2 = $dirty & (-3670017);
                    color3 = color2;
                    modifier3 = modifier2;
                    enabled2 = z2;
                    shape3 = shape2;
                    i5 = 262027249;
                    contentColor2 = contentColor;
                } else {
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled2 = z2;
                    shape3 = shape2;
                    $dirty2 = $dirty;
                    i5 = 262027249;
                    contentColor2 = contentColor;
                    color3 = color2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i5, $dirty2, $dirty12, "androidx.compose.material.Surface (Surface.kt:332)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) consume).m6640unboximpl();
            final float absoluteElevation = Dp.m6626constructorimpl(arg0$iv + elevation2);
            final Modifier modifier5 = modifier3;
            final boolean z3 = z;
            final Function0<Unit> function03 = function02;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(contentColor2)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m6624boximpl(absoluteElevation))}, ComposableLambdaKt.rememberComposableLambda(-1391199439, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$5
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x01a8  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
                    /*
                        Method dump skipped, instructions count: 428
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SurfaceKt$Surface$5.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentColor3 = contentColor2;
            shape4 = shape3;
            color2 = color3;
            border3 = border2;
            elevation3 = elevation2;
            interactionSource3 = interactionSource2;
            enabled3 = enabled2;
            modifier4 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$6
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

                public final void invoke(Composer composer, int i14) {
                    SurfaceKt.m1662SurfaceNy5ogXk(selected, function0, modifier4, enabled3, shape4, color2, contentColor3, border3, elevation3, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* renamed from: Surface-Ny5ogXk, reason: not valid java name */
    public static final void m1663SurfaceNy5ogXk(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean enabled, Shape shape, long color, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function1<? super Boolean, Unit> function12;
        Modifier modifier2;
        boolean z2;
        Shape shape2;
        final long color2;
        int $dirty;
        int $dirty1;
        int i2;
        int i3;
        int i4;
        int $dirty12;
        Modifier.Companion modifier3;
        long contentColor2;
        final MutableInteractionSource interactionSource2;
        final BorderStroke border2;
        final float elevation2;
        final boolean enabled2;
        final Shape shape3;
        int $dirty2;
        int i5;
        final long color3;
        final long contentColor3;
        final Shape shape4;
        final BorderStroke border3;
        final float elevation3;
        final MutableInteractionSource interactionSource3;
        final boolean enabled3;
        final Modifier modifier4;
        int $dirty3;
        int i6;
        int i7;
        Composer $composer2 = $composer.startRestartGroup(1341569296);
        ComposerKt.sourceInformation($composer2, "C(Surface)P(1,9,8,6,10,2:c#ui.graphics.Color,4:c#ui.graphics.Color!1,5:c#ui.unit.Dp,7)441@22161L6,442@22203L22,*448@22435L7,452@22598L911,449@22459L1050:Surface.kt#jmzs0o");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            z = checked;
        } else if (($changed & 6) == 0) {
            z = checked;
            $dirty4 |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = checked;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty4 |= $composer2.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty4 |= 3072;
            z2 = enabled;
        } else if (($changed & 3072) == 0) {
            z2 = enabled;
            $dirty4 |= $composer2.changed(z2) ? 2048 : 1024;
        } else {
            z2 = enabled;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty4 |= 24576;
            shape2 = shape;
        } else if (($changed & 24576) == 0) {
            shape2 = shape;
            $dirty4 |= $composer2.changed(shape2) ? 16384 : 8192;
        } else {
            shape2 = shape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                color2 = color;
                if ($composer2.changed(color2)) {
                    i7 = 131072;
                    $dirty4 |= i7;
                }
            } else {
                color2 = color;
            }
            i7 = 65536;
            $dirty4 |= i7;
        } else {
            color2 = color;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                if ($composer2.changed(contentColor)) {
                    i6 = 1048576;
                    $dirty = $dirty3 | i6;
                }
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            i6 = 524288;
            $dirty = $dirty3 | i6;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 12582912;
            i2 = i11;
        } else if (($changed & 12582912) == 0) {
            i2 = i11;
            $dirty |= $composer2.changed(border) ? 8388608 : 4194304;
        } else {
            i2 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
            i3 = i12;
        } else if (($changed & 100663296) == 0) {
            i3 = i12;
            $dirty |= $composer2.changed(elevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i12;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty |= 805306368;
            i4 = i13;
        } else if (($changed & 805306368) == 0) {
            i4 = i13;
            $dirty |= $composer2.changed(interactionSource) ? 536870912 : 268435456;
        } else {
            i4 = i13;
        }
        if ((i & 1024) != 0) {
            $dirty12 = $dirty1 | 6;
        } else {
            int $dirty13 = $changed1 & 6;
            if ($dirty13 == 0) {
                $dirty1 |= $composer2.changedInstance(function2) ? 4 : 2;
            }
            $dirty12 = $dirty1;
        }
        if (($dirty & 306783379) == 306783378 && ($dirty12 & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            contentColor3 = contentColor;
            elevation3 = elevation;
            modifier4 = modifier2;
            enabled3 = z2;
            shape4 = shape2;
            border3 = border;
            interactionSource3 = interactionSource;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : z2;
                if (i10 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 32) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1482getSurface0d7_KjU();
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(color2, $composer2, ($dirty >> 15) & 14);
                    $dirty &= -3670017;
                } else {
                    contentColor2 = contentColor;
                }
                BorderStroke border4 = i2 != 0 ? null : border;
                float elevation4 = i3 != 0 ? Dp.m6626constructorimpl(0) : elevation;
                if (i4 != 0) {
                    border2 = border4;
                    elevation2 = elevation4;
                    enabled2 = enabled4;
                    interactionSource2 = null;
                    shape3 = shape2;
                    $dirty2 = $dirty;
                    i5 = 1341569296;
                    color3 = color2;
                } else {
                    interactionSource2 = interactionSource;
                    border2 = border4;
                    elevation2 = elevation4;
                    enabled2 = enabled4;
                    shape3 = shape2;
                    $dirty2 = $dirty;
                    i5 = 1341569296;
                    color3 = color2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    $dirty2 = $dirty & (-3670017);
                    color3 = color2;
                    modifier3 = modifier2;
                    enabled2 = z2;
                    shape3 = shape2;
                    i5 = 1341569296;
                    contentColor2 = contentColor;
                } else {
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled2 = z2;
                    shape3 = shape2;
                    $dirty2 = $dirty;
                    i5 = 1341569296;
                    contentColor2 = contentColor;
                    color3 = color2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i5, $dirty2, $dirty12, "androidx.compose.material.Surface (Surface.kt:447)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) consume).m6640unboximpl();
            final float absoluteElevation = Dp.m6626constructorimpl(arg0$iv + elevation2);
            final Modifier modifier5 = modifier3;
            final boolean z3 = z;
            final Function1<? super Boolean, Unit> function13 = function12;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(contentColor2)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m6624boximpl(absoluteElevation))}, ComposableLambdaKt.rememberComposableLambda(-311657392, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$7
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x01a8  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
                    /*
                        Method dump skipped, instructions count: 428
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SurfaceKt$Surface$7.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentColor3 = contentColor2;
            shape4 = shape3;
            color2 = color3;
            border3 = border2;
            elevation3 = elevation2;
            interactionSource3 = interactionSource2;
            enabled3 = enabled2;
            modifier4 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$8
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

                public final void invoke(Composer composer, int i14) {
                    SurfaceKt.m1663SurfaceNy5ogXk(checked, function1, modifier4, enabled3, shape4, color2, contentColor3, border3, elevation3, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: surface-8ww4TTg, reason: not valid java name */
    public static final Modifier m1666surface8ww4TTg(Modifier $this$surface_u2d8ww4TTg, Shape shape, long backgroundColor, BorderStroke border, float elevation) {
        Modifier $this$surface_u2d8ww4TTg2 = ShadowKt.m3827shadows4CzXII$default($this$surface_u2d8ww4TTg, elevation, shape, false, 0L, 0L, 24, null);
        Modifier.Companion companion = Modifier.INSTANCE;
        if (border != null) {
            companion = BorderKt.border(companion, border, shape);
        }
        return ClipKt.clip(BackgroundKt.m226backgroundbw27NRU($this$surface_u2d8ww4TTg2.then(companion), backgroundColor, shape), shape);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: surfaceColorAtElevation-cq6XJ1M, reason: not valid java name */
    public static final long m1667surfaceColorAtElevationcq6XJ1M(long color, ElevationOverlay elevationOverlay, float absoluteElevation, Composer $composer, int $changed) {
        Composer $composer2;
        long color2;
        ComposerKt.sourceInformationMarkerStart($composer, 1561611256, "C(surfaceColorAtElevation)P(1:c#ui.graphics.Color,2,0:c#ui.unit.Dp)496@24015L6:Surface.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1561611256, $changed, -1, "androidx.compose.material.surfaceColorAtElevation (Surface.kt:495)");
        }
        if (!Color.m4164equalsimpl0(color, MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU()) || elevationOverlay == null) {
            $composer2 = $composer;
            $composer2.startReplaceGroup(1082239839);
            $composer2.endReplaceGroup();
            color2 = color;
        } else {
            $composer.startReplaceGroup(1082171732);
            ComposerKt.sourceInformation($composer, "497@24086L31");
            $composer2 = $composer;
            color2 = elevationOverlay.mo1520apply7g2Lkgo(color, absoluteElevation, $composer2, ($changed & 14) | (($changed >> 3) & 112) | (($changed << 3) & 896));
            $composer2.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return color2;
    }
}
