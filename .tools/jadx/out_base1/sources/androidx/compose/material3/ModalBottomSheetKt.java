package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: ModalBottomSheet.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÄ\u0001\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00132\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\n¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\b0\u001e¢\u0006\u0002\b\u0018¢\u0006\u0002\b H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a0\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u00132\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010%\u001a\u00020&H\u0003ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a-\u0010)\u001a\u00020\u000e2\b\b\u0002\u0010*\u001a\u00020&2\u0014\b\u0002\u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020&0\u001eH\u0007¢\u0006\u0002\u0010-\u001aó\u0001\u0010.\u001a\u00020\b*\u00020/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u000203012\u0006\u00104\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\b0\n2!\u00107\u001a\u001d\u0012\u0013\u0012\u001102¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b0\u001e2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\n¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b\u00182\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\b0\u001e¢\u0006\u0002\b\u0018¢\u0006\u0002\b H\u0001ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a\u0014\u0010=\u001a\u000202*\u00020>2\u0006\u0010?\u001a\u000202H\u0002\u001a\u0014\u0010@\u001a\u000202*\u00020>2\u0006\u0010?\u001a\u000202H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006A²\u0006\n\u0010B\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"PredictiveBackChildTransformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "J", "PredictiveBackMaxScaleXDistance", "Landroidx/compose/ui/unit/Dp;", "F", "PredictiveBackMaxScaleYDistance", "ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "sheetMaxWidth", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-dYc4hso", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/ModalBottomSheetProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Scrim", "color", "visible", "", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberModalBottomSheetState", "skipPartiallyExpanded", "confirmValueChange", "Landroidx/compose/material3/SheetValue;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "ModalBottomSheetContent", "Landroidx/compose/foundation/layout/BoxScope;", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "animateToDismiss", "settleToDismiss", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "velocity", "ModalBottomSheetContent-IQkwcL4", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/animation/core/Animatable;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", NotificationCompat.CATEGORY_PROGRESS, "calculatePredictiveBackScaleY", "material3_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ModalBottomSheetKt {
    private static final float PredictiveBackMaxScaleXDistance = Dp.m6626constructorimpl(48);
    private static final float PredictiveBackMaxScaleYDistance = Dp.m6626constructorimpl(24);
    private static final long PredictiveBackChildTransformOrigin = TransformOriginKt.TransformOrigin(0.5f, 0.0f);

    /* renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    public static final void m2246ModalBottomSheetdYc4hso(final Function0<Unit> function0, Modifier modifier, SheetState sheetState, float sheetMaxWidth, Shape shape, long containerColor, long contentColor, float tonalElevation, long scrimColor, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, ? extends WindowInsets> function22, ModalBottomSheetProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        final SheetState sheetState2;
        Shape shape2;
        long containerColor2;
        long j;
        int $dirty;
        int $dirty1;
        int i2;
        int i3;
        int i4;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32;
        boolean z;
        long contentColor2;
        int $dirty2;
        float tonalElevation2;
        int $dirty3;
        long scrimColor2;
        Function2 contentWindowInsets;
        final float tonalElevation3;
        ModalBottomSheetProperties properties2;
        final Function2 contentWindowInsets2;
        final float sheetMaxWidth2;
        final long contentColor3;
        final long containerColor3;
        final Function2 dragHandle;
        final Modifier modifier3;
        final Shape shape3;
        final long scrimColor3;
        Object value$iv$iv;
        Object value$iv;
        Object value$iv2;
        int $dirty12;
        Object value$iv3;
        Object value$iv4;
        Composer $composer2;
        final SheetState sheetState3;
        final ModalBottomSheetProperties properties3;
        final long scrimColor4;
        final float sheetMaxWidth3;
        final Shape shape4;
        final long containerColor4;
        final long contentColor4;
        final float tonalElevation4;
        final Function2 contentWindowInsets3;
        ModalBottomSheetKt$ModalBottomSheet$4$1 value$iv5;
        int i5;
        int $dirty4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(2132719801);
        ComposerKt.sourceInformation($composer3, "C(ModalBottomSheet)P(6,5,11,10:c#ui.unit.Dp,9,0:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.unit.Dp,8:c#ui.graphics.Color,4,3,7)121@6012L31,123@6143L13,124@6206L14,125@6248L31,127@6356L10,133@6697L24,134@6761L327,145@7142L149,151@7326L42,155@7458L708,167@8231L771,153@7374L1628,193@9077L21,193@9050L48:ModalBottomSheet.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        int i11 = i & 2;
        if (i11 != 0) {
            $dirty5 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                sheetState2 = sheetState;
                if ($composer3.changed(sheetState2)) {
                    i10 = 256;
                    $dirty5 |= i10;
                }
            } else {
                sheetState2 = sheetState;
            }
            i10 = 128;
            $dirty5 |= i10;
        } else {
            sheetState2 = sheetState;
        }
        int i12 = i & 8;
        if (i12 != 0) {
            $dirty5 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty5 |= $composer3.changed(sheetMaxWidth) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i9 = 16384;
                    $dirty5 |= i9;
                }
            } else {
                shape2 = shape;
            }
            i9 = 8192;
            $dirty5 |= i9;
        } else {
            shape2 = shape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i8 = 131072;
                    $dirty5 |= i8;
                }
            } else {
                containerColor2 = containerColor;
            }
            i8 = 65536;
            $dirty5 |= i8;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                j = contentColor;
                if ($composer3.changed(j)) {
                    i7 = 1048576;
                    $dirty5 |= i7;
                }
            } else {
                j = contentColor;
            }
            i7 = 524288;
            $dirty5 |= i7;
        } else {
            j = contentColor;
        }
        int i13 = i & 128;
        if (i13 != 0) {
            $dirty5 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty5 |= $composer3.changed(tonalElevation) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                $dirty4 = $dirty5;
                $dirty1 = $changed1;
                if ($composer3.changed(scrimColor)) {
                    i6 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    $dirty = $dirty4 | i6;
                }
            } else {
                $dirty4 = $dirty5;
                $dirty1 = $changed1;
            }
            i6 = 33554432;
            $dirty = $dirty4 | i6;
        } else {
            $dirty = $dirty5;
            $dirty1 = $changed1;
        }
        int $dirty13 = $dirty1;
        int $dirty14 = i & 512;
        if ($dirty14 != 0) {
            $dirty |= 805306368;
            i2 = $dirty14;
        } else if (($changed & 805306368) == 0) {
            i2 = $dirty14;
            $dirty |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        } else {
            i2 = $dirty14;
        }
        int $dirty6 = $dirty;
        if (($changed1 & 6) == 0) {
            i3 = i13;
            if ((i & 1024) == 0 && $composer3.changedInstance(function22)) {
                i5 = 4;
                $dirty13 |= i5;
            }
            i5 = 2;
            $dirty13 |= i5;
        } else {
            i3 = i13;
        }
        int i14 = i & 2048;
        if (i14 != 0) {
            $dirty13 |= 48;
            i4 = i14;
        } else if (($changed1 & 48) == 0) {
            i4 = i14;
            $dirty13 |= $composer3.changed(properties) ? 32 : 16;
        } else {
            i4 = i14;
        }
        int $dirty15 = $dirty13;
        if ((i & 4096) != 0) {
            $dirty15 |= 384;
            function32 = function3;
        } else if (($changed1 & 384) == 0) {
            function32 = function3;
            $dirty15 |= $composer3.changedInstance(function32) ? 256 : 128;
        } else {
            function32 = function3;
        }
        if ((306783379 & $dirty6) == 306783378 && ($dirty15 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            dragHandle = function2;
            $composer2 = $composer3;
            containerColor4 = containerColor2;
            contentColor4 = j;
            sheetState3 = sheetState2;
            shape4 = shape2;
            sheetMaxWidth3 = sheetMaxWidth;
            tonalElevation4 = tonalElevation;
            scrimColor4 = scrimColor;
            contentWindowInsets3 = function22;
            properties3 = properties;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                    sheetState2 = rememberModalBottomSheetState(false, null, $composer3, 0, 3);
                }
                float sheetMaxWidth4 = i12 != 0 ? BottomSheetDefaults.INSTANCE.m1797getSheetMaxWidthD9Ej5fM() : sheetMaxWidth;
                if ((i & 16) != 0) {
                    z = false;
                    $dirty6 &= -57345;
                    shape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                } else {
                    z = false;
                }
                if ((i & 32) != 0) {
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty6 &= -458753;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer3, ($dirty6 >> 15) & 14);
                    $dirty6 &= -3670017;
                } else {
                    contentColor2 = j;
                }
                if (i3 != 0) {
                    $dirty2 = $dirty6;
                    tonalElevation2 = Dp.m6626constructorimpl(0);
                } else {
                    $dirty2 = $dirty6;
                    tonalElevation2 = tonalElevation;
                }
                if ((i & 256) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty3 = $dirty2 & (-234881025);
                } else {
                    $dirty3 = $dirty2;
                    scrimColor2 = scrimColor;
                }
                Function2 dragHandle2 = i2 != 0 ? ComposableSingletons$ModalBottomSheetKt.INSTANCE.m1974getLambda1$material3_release() : function2;
                float tonalElevation5 = tonalElevation2;
                if ((i & 1024) != 0) {
                    contentWindowInsets = new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer $composer4, int $changed2) {
                            $composer4.startReplaceGroup(58488196);
                            ComposerKt.sourceInformation($composer4, "C129@6530L12:ModalBottomSheet.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(58488196, $changed2, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.kt:129)");
                            }
                            WindowInsets windowInsets = BottomSheetDefaults.INSTANCE.getWindowInsets($composer4, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            $composer4.endReplaceGroup();
                            return windowInsets;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer, Integer num) {
                            return invoke(composer, num.intValue());
                        }
                    };
                    $dirty15 &= -15;
                } else {
                    contentWindowInsets = function22;
                }
                if (i4 != 0) {
                    properties2 = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    tonalElevation3 = tonalElevation5;
                    contentWindowInsets2 = contentWindowInsets;
                    sheetMaxWidth2 = sheetMaxWidth4;
                    contentColor3 = contentColor2;
                    containerColor3 = containerColor2;
                    $dirty6 = $dirty3;
                    dragHandle = dragHandle2;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scrimColor3 = scrimColor2;
                } else {
                    tonalElevation3 = tonalElevation5;
                    properties2 = properties;
                    contentWindowInsets2 = contentWindowInsets;
                    sheetMaxWidth2 = sheetMaxWidth4;
                    contentColor3 = contentColor2;
                    containerColor3 = containerColor2;
                    $dirty6 = $dirty3;
                    dragHandle = dragHandle2;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    scrimColor3 = scrimColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty6 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty6 &= -3670017;
                }
                if ((i & 256) != 0) {
                    $dirty6 &= -234881025;
                }
                if ((i & 1024) != 0) {
                    $dirty15 &= -15;
                }
                sheetMaxWidth2 = sheetMaxWidth;
                tonalElevation3 = tonalElevation;
                scrimColor3 = scrimColor;
                dragHandle = function2;
                contentWindowInsets2 = function22;
                properties2 = properties;
                z = false;
                containerColor3 = containerColor2;
                contentColor3 = j;
                modifier3 = modifier2;
                shape3 = shape2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2132719801, $dirty6, $dirty15, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.kt:132)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954363344, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2011393839, "CC(remember):ModalBottomSheet.kt#9igjgp");
            boolean invalid$iv = ((((($dirty6 & 896) ^ 384) <= 256 || !$composer3.changed(sheetState2)) && ($dirty6 & 384) != 256) ? z : true) | $composer3.changedInstance(scope) | (($dirty6 & 14) == 4 ? true : z);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Job launch$default;
                        if (SheetState.this.getAnchoredDraggableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Hidden).booleanValue()) {
                            launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(SheetState.this, null), 3, null);
                            final SheetState sheetState4 = SheetState.this;
                            final Function0<Unit> function02 = function0;
                            launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                    invoke2(th);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable it) {
                                    if (!SheetState.this.isVisible()) {
                                        function02.invoke();
                                    }
                                }
                            });
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$animateToDismiss$1$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.hide(this) != coroutine_suspended) {
                                        break;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final Function0 animateToDismiss = (Function0) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2011381825, "CC(remember):ModalBottomSheet.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changedInstance(scope) | ((((($dirty6 & 896) ^ 384) <= 256 || !$composer3.changed(sheetState2)) && ($dirty6 & 384) != 256) ? z : true) | (($dirty6 & 14) == 4 ? true : z);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                        invoke(f.floatValue());
                        return Unit.INSTANCE;
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $it;
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                            this.$it = f;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, this.$it, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.settle$material3_release(this.$it, this) != coroutine_suspended) {
                                        break;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    public final void invoke(float it) {
                        Job launch$default;
                        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(sheetState2, it, null), 3, null);
                        final SheetState sheetState4 = sheetState2;
                        final Function0<Unit> function02 = function0;
                        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$settleToDismiss$1$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable it2) {
                                if (SheetState.this.isVisible()) {
                                    return;
                                }
                                function02.invoke();
                            }
                        });
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final Function1 settleToDismiss = (Function1) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2011376044, "CC(remember):ModalBottomSheet.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                $dirty12 = $dirty15;
                value$iv3 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                $dirty12 = $dirty15;
                value$iv3 = it$iv3;
            }
            final Animatable predictiveBackProgress = (Animatable) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2011371154, "CC(remember):ModalBottomSheet.kt#9igjgp");
            boolean invalid$iv3 = ((((($dirty6 & 896) ^ 384) <= 256 || !$composer3.changed(sheetState2)) && ($dirty6 & 384) != 256) ? z : true) | $composer3.changedInstance(scope) | $composer3.changedInstance(predictiveBackProgress) | (($dirty6 & 14) == 4 ? true : z);
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Job launch$default;
                        if (SheetState.this.getCurrentValue() != SheetValue.Expanded || !SheetState.this.getHasPartiallyExpandedState()) {
                            launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass3(SheetState.this, null), 3, null);
                            final Function0<Unit> function02 = function0;
                            launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                    invoke2(th);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable it) {
                                    function02.invoke();
                                }
                            });
                        } else {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(predictiveBackProgress, null), 3, null);
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass2(SheetState.this, null), 3, null);
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1", f = "ModalBottomSheet.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$predictiveBackProgress = animatable;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$predictiveBackProgress, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object animateTo;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    animateTo = r2.animateTo(Boxing.boxFloat(0.0f), (r12 & 2) != 0 ? r2.defaultSpringSpec : null, (r12 & 4) != 0 ? this.$predictiveBackProgress.getVelocity() : null, (r12 & 8) != 0 ? null : null, this);
                                    if (animateTo != coroutine_suspended) {
                                        break;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2", f = "ModalBottomSheet.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$2, reason: invalid class name */
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass2(SheetState sheetState, Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.partialExpand(this) != coroutine_suspended) {
                                        break;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* compiled from: ModalBottomSheet.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3", f = "ModalBottomSheet.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$2$1$3, reason: invalid class name */
                    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass3(SheetState sheetState, Continuation<? super AnonymousClass3> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass3(this.$sheetState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$sheetState.hide(this) != coroutine_suspended) {
                                        break;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33 = function32;
            final SheetState sheetState4 = sheetState2;
            ModalBottomSheet_androidKt.ModalBottomSheetDialog((Function0) value$iv4, properties2, predictiveBackProgress, ComposableLambdaKt.rememberComposableLambda(-314673510, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0180  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x01e5  */
                /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:30:0x0183  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r46, int r47) {
                    /*
                        Method dump skipped, instructions count: 489
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, ($dirty12 & 112) | 3072 | (Animatable.$stable << 6));
            $composer2 = $composer3;
            if (sheetState2.getHasExpandedState()) {
                ComposerKt.sourceInformationMarkerStart($composer2, -2011320033, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean invalid$iv4 = (((($dirty6 & 896) ^ 384) <= 256 || !$composer2.changed(sheetState2)) && ($dirty6 & 384) != 256) ? z : true;
                Object it$iv5 = $composer2.rememberedValue();
                if (invalid$iv4 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                    value$iv5 = new ModalBottomSheetKt$ModalBottomSheet$4$1(sheetState2, null);
                    $composer2.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(sheetState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv5, $composer2, ($dirty6 >> 6) & 14);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sheetState3 = sheetState2;
            properties3 = properties2;
            scrimColor4 = scrimColor3;
            modifier2 = modifier3;
            sheetMaxWidth3 = sheetMaxWidth2;
            shape4 = shape3;
            containerColor4 = containerColor3;
            contentColor4 = contentColor3;
            tonalElevation4 = tonalElevation3;
            contentWindowInsets3 = contentWindowInsets2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2 dragHandle3 = dragHandle;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheet$5
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

                public final void invoke(Composer composer, int i15) {
                    ModalBottomSheetKt.m2246ModalBottomSheetdYc4hso(function0, modifier4, sheetState3, sheetMaxWidth3, shape4, containerColor4, contentColor4, tonalElevation4, scrimColor4, dragHandle3, contentWindowInsets3, properties3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x01c8, code lost:
    
        if (r6.changedInstance(r55) != false) goto L154;
     */
    /* JADX WARN: Removed duplicated region for block: B:129:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0473 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0411  */
    /* renamed from: ModalBottomSheetContent-IQkwcL4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2247ModalBottomSheetContentIQkwcL4(final androidx.compose.foundation.layout.BoxScope r40, final androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r41, final kotlinx.coroutines.CoroutineScope r42, final kotlin.jvm.functions.Function0<kotlin.Unit> r43, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r44, androidx.compose.ui.Modifier r45, androidx.compose.material3.SheetState r46, float r47, androidx.compose.ui.graphics.Shape r48, long r49, long r51, float r53, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, ? extends androidx.compose.foundation.layout.WindowInsets> r55, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, androidx.compose.runtime.Composer r57, final int r58, final int r59, final int r60) {
        /*
            Method dump skipped, instructions count: 1426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ModalBottomSheetKt.m2247ModalBottomSheetContentIQkwcL4(androidx.compose.foundation.layout.BoxScope, androidx.compose.animation.core.Animatable, kotlinx.coroutines.CoroutineScope, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.material3.SheetState, float, androidx.compose.ui.graphics.Shape, long, long, float, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleX(GraphicsLayerScope $this$calculatePredictiveBackScaleX, float progress) {
        float width = Size.m3991getWidthimpl($this$calculatePredictiveBackScaleX.getSize());
        if (Float.isNaN(width)) {
            return 1.0f;
        }
        if (width == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min($this$calculatePredictiveBackScaleX.mo364toPx0680j_4(PredictiveBackMaxScaleXDistance), width), progress) / width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleY(GraphicsLayerScope $this$calculatePredictiveBackScaleY, float progress) {
        float height = Size.m3988getHeightimpl($this$calculatePredictiveBackScaleY.getSize());
        if (Float.isNaN(height)) {
            return 1.0f;
        }
        if (height == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min($this$calculatePredictiveBackScaleY.mo364toPx0680j_4(PredictiveBackMaxScaleYDistance), height), progress) / height);
    }

    public static final SheetState rememberModalBottomSheetState(boolean skipPartiallyExpanded, Function1<? super SheetValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Function1 confirmValueChange;
        ComposerKt.sourceInformationMarkerStart($composer, -778250030, "C(rememberModalBottomSheetState)P(1)400@18058L160:ModalBottomSheet.kt#uh7d8r");
        boolean skipPartiallyExpanded2 = (i & 1) != 0 ? false : skipPartiallyExpanded;
        if ((i & 2) != 0) {
            Function1 confirmValueChange2 = new Function1<SheetValue, Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$rememberModalBottomSheetState$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SheetValue it) {
                    return true;
                }
            };
            confirmValueChange = confirmValueChange2;
        } else {
            confirmValueChange = function1;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-778250030, $changed, -1, "androidx.compose.material3.rememberModalBottomSheetState (ModalBottomSheet.kt:400)");
        }
        SheetState rememberSheetState = SheetDefaultsKt.rememberSheetState(skipPartiallyExpanded2, confirmValueChange, SheetValue.Hidden, false, $composer, ($changed & 14) | 384 | ($changed & 112), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return rememberSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m2248Scrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissSheet;
        Object value$iv;
        ModalBottomSheetKt$Scrim$dismissSheet$1$1 value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(951870469);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(0:c#ui.graphics.Color)410@18376L87,411@18489L29,426@19112L79,426@19062L129:ModalBottomSheet.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(color) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(visible) ? 256 : 128;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(951870469, $dirty, -1, "androidx.compose.material3.Scrim (ModalBottomSheet.kt:407)");
            }
            if ((color != 16 ? 1 : 0) != 0) {
                int $dirty2 = $dirty;
                final State alpha$delegate = AnimateAsStateKt.animateFloatAsState(visible ? 1065353216 : 0, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                Strings.Companion companion = Strings.INSTANCE;
                final String closeSheet = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(androidx.compose.ui.R.string.close_sheet), $composer2, 0);
                $composer2.startReplaceGroup(-1785653838);
                ComposerKt.sourceInformation($composer2, "414@18629L44,415@18730L263");
                if (visible) {
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1785652017, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv = ($dirty2 & 112) == 32;
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new ModalBottomSheetKt$Scrim$dismissSheet$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1785648566, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv2 = $composer2.changed(closeSheet) | (($dirty2 & 112) == 32);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                SemanticsPropertiesKt.setTraversalIndex($this$semantics, 1.0f);
                                SemanticsPropertiesKt.setContentDescription($this$semantics, closeSheet);
                                final Function0<Unit> function02 = function0;
                                SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$dismissSheet$2$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        function02.invoke();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    dismissSheet = SemanticsModifierKt.semantics(pointerInput, true, (Function1) value$iv3);
                } else {
                    dismissSheet = Modifier.INSTANCE;
                }
                $composer2.endReplaceGroup();
                Modifier then = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissSheet);
                ComposerKt.sourceInformationMarkerStart($composer2, -1785636526, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean invalid$iv3 = $composer2.changed(alpha$delegate) | (($dirty2 & 14) == 4);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                            invoke2(drawScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DrawScope $this$Canvas) {
                            float Scrim_3J_VO9M$lambda$10;
                            long j = color;
                            Scrim_3J_VO9M$lambda$10 = ModalBottomSheetKt.Scrim_3J_VO9M$lambda$10(alpha$delegate);
                            DrawScope.m4707drawRectnJ9OG0$default($this$Canvas, j, 0L, 0L, RangesKt.coerceIn(Scrim_3J_VO9M$lambda$10, 0.0f, 1.0f), null, null, 0, 118, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(then, (Function1) value$iv, $composer2, 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheetKt$Scrim$2
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
                    ModalBottomSheetKt.m2248Scrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$10(State<Float> state) {
        return state.getValue().floatValue();
    }
}
