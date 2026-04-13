package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BottomSheetScaffold.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ar\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0080\u0002\u0010\u001b\u001a\u00020\u00062\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0015\b\u0002\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010&\u001a\u00020\u000f2\b\b\u0002\u0010'\u001a\u00020\u000f2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u009b\u0001\u0010+\u001a\u00020\u00062\u0013\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00060 ¢\u0006\u0002\b\u00172\u0011\u0010-\u001a\r\u0012\u0004\u0012\u00020\u00060 ¢\u0006\u0002\b\u00172\u0013\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010 ¢\u0006\u0002\b\u00172\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00060 ¢\u0006\u0002\b\u00172\u0006\u0010\u0011\u001a\u00020\u00012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0 2\u0006\u0010$\u001a\u00020%2\u0006\u00100\u001a\u00020\bH\u0003ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a\u001c\u00103\u001a\u0002042\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u0003052\u0006\u00106\u001a\u000207H\u0002\u001a!\u00108\u001a\u00020\u001e2\b\b\u0002\u00109\u001a\u00020\b2\b\b\u0002\u0010:\u001a\u00020\"H\u0007¢\u0006\u0002\u0010;\u001a;\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u00020>2\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020/0@2\u0014\b\u0002\u0010A\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\n0\u0015H\u0007¢\u0006\u0002\u0010B\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"BottomSheetScaffoldPositionalThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "BottomSheetScaffoldVelocityThreshold", "FabSpacing", "BottomSheet", "", "state", "Landroidx/compose/material/BottomSheetState;", "sheetGesturesEnabled", "", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetPeekHeight", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "BottomSheet-dAqlCkY", "(Landroidx/compose/material/BottomSheetState;ZLandroidx/compose/ui/graphics/Shape;FJJFLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomSheetScaffold", "sheetContent", "scaffoldState", "Landroidx/compose/material/BottomSheetScaffoldState;", "topBar", "Lkotlin/Function0;", "snackbarHost", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "backgroundColor", "contentColor", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-HnlDQGw", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomSheetScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLandroidx/compose/ui/graphics/Shape;FJJFJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "BottomSheetScaffoldLayout", "body", "bottomSheet", "sheetOffset", "", "sheetState", "BottomSheetScaffoldLayout-HJHHjMs", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLkotlin/jvm/functions/Function0;ILandroidx/compose/material/BottomSheetState;Landroidx/compose/runtime/Composer;I)V", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "rememberBottomSheetScaffoldState", "bottomSheetState", "snackbarHostState", "(Landroidx/compose/material/BottomSheetState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetScaffoldState;", "rememberBottomSheetState", "initialValue", "Landroidx/compose/material/BottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BottomSheetScaffoldKt {
    private static final float FabSpacing = Dp.m6626constructorimpl(16);
    private static final float BottomSheetScaffoldPositionalThreshold = Dp.m6626constructorimpl(56);
    private static final float BottomSheetScaffoldVelocityThreshold = Dp.m6626constructorimpl(125);

    public static final BottomSheetState rememberBottomSheetState(final BottomSheetValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1808153344, "C(rememberBottomSheetState)P(2)237@8802L7,245@9044L210,238@8821L433:BottomSheetScaffold.kt#jmzs0o");
        if ((i & 2) != 0) {
            animationSpec = BottomSheetScaffoldDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i & 4) != 0) {
            Function1 confirmStateChange = new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$rememberBottomSheetState$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BottomSheetValue it) {
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1808153344, $changed, -1, "androidx.compose.material.rememberBottomSheetState (BottomSheetScaffold.kt:236)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) consume;
        Object[] objArr = {animationSpec};
        Saver<BottomSheetState, ?> Saver = BottomSheetState.INSTANCE.Saver(animationSpec, function1, density);
        ComposerKt.sourceInformationMarkerStart($composer, -1916212030, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue)) || ($changed & 6) == 4) | $composer.changed(density) | $composer.changedInstance(animationSpec) | (((($changed & 896) ^ 384) > 256 && $composer.changed(function1)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<BottomSheetState>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$rememberBottomSheetState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BottomSheetState invoke() {
                    return new BottomSheetState(BottomSheetValue.this, density, animationSpec, function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        BottomSheetState bottomSheetState = (BottomSheetState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return bottomSheetState;
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(BottomSheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Composer $composer2;
        Object value$iv;
        Object value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, -1022285988, "C(rememberBottomSheetScaffoldState)275@9938L35,276@10018L32,278@10092L196:BottomSheetScaffold.kt#jmzs0o");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed, null, null, $composer2, 6, 6);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer2, 200089082, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new SnackbarHostState();
                $this$cache$iv.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv;
            }
            snackbarHostState = (SnackbarHostState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1022285988, $changed, -1, "androidx.compose.material.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:277)");
        }
        ComposerKt.sourceInformationMarkerStart($composer2, 200091614, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer2.changed(bottomSheetState)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer2.changed(snackbarHostState)) || ($changed & 48) == 32);
        Composer $this$cache$iv2 = $composer2;
        Object it$iv2 = $this$cache$iv2.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv = new BottomSheetScaffoldState(bottomSheetState, snackbarHostState);
            $this$cache$iv2.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv2;
        }
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return bottomSheetScaffoldState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x01c0, code lost:
    
        if (r3.changed(r5) != false) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01da, code lost:
    
        if (r3.changed(r50) != false) goto L165;
     */
    /* renamed from: BottomSheetScaffold-HnlDQGw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1439BottomSheetScaffoldHnlDQGw(final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, androidx.compose.ui.Modifier r34, androidx.compose.material.BottomSheetScaffoldState r35, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r37, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, int r39, boolean r40, androidx.compose.ui.graphics.Shape r41, float r42, long r43, long r45, float r47, long r48, long r50, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, androidx.compose.runtime.Composer r53, final int r54, final int r55, final int r56) {
        /*
            Method dump skipped, instructions count: 1092
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.m1439BottomSheetScaffoldHnlDQGw(kotlin.jvm.functions.Function3, androidx.compose.ui.Modifier, androidx.compose.material.BottomSheetScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, androidx.compose.ui.graphics.Shape, float, long, long, float, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: BottomSheet-dAqlCkY, reason: not valid java name */
    public static final void m1438BottomSheetdAqlCkY(final BottomSheetState state, final boolean sheetGesturesEnabled, final Shape sheetShape, final float sheetElevation, final long sheetBackgroundColor, final long sheetContentColor, final float sheetPeekHeight, Modifier modifier, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        int i2;
        final Modifier modifier2;
        Object value$iv$iv;
        Modifier anchoredDraggable;
        Object value$iv;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-412323066);
        ComposerKt.sourceInformation($composer3, "C(BottomSheet)P(8,5,7,4:c#ui.unit.Dp,2:c#ui.graphics.Color,3:c#ui.graphics.Color,6:c#ui.unit.Dp,1)409@16545L24,*410@16611L7,416@16805L792,437@17821L893,462@18878L29,411@16651L2262:BottomSheetScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(sheetGesturesEnabled) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(sheetShape) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(sheetElevation) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(sheetBackgroundColor) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changed(sheetContentColor) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(sheetPeekHeight) ? 1048576 : 524288;
        }
        int i3 = i & 128;
        if (i3 != 0) {
            $dirty |= 12582912;
            i2 = 1572864;
            modifier2 = modifier;
        } else if (($changed & 12582912) == 0) {
            i2 = 1572864;
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 8388608 : 4194304;
        } else {
            i2 = 1572864;
            modifier2 = modifier;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty2 = $dirty;
        if ((38347923 & $dirty2) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-412323066, $dirty2, -1, "androidx.compose.material.BottomSheet (BottomSheetScaffold.kt:408)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
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
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$BottomSheet_dAqlCkY_u24lambda_u243 = (Density) consume;
            final float peekHeightPx = $this$BottomSheet_dAqlCkY_u24lambda_u243.mo364toPx0680j_4(sheetPeekHeight);
            AnchoredDraggableState<BottomSheetValue> anchoredDraggableState$material_release = state.getAnchoredDraggableState$material_release();
            Orientation orientation = Orientation.Vertical;
            ComposerKt.sourceInformationMarkerStart($composer3, -1416603002, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(peekHeightPx) | (($dirty2 & 14) == 4);
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function2) new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<BottomSheetValue>, ? extends BottomSheetValue>>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$1$1

                    /* compiled from: BottomSheetScaffold.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[BottomSheetValue.values().length];
                            try {
                                iArr[BottomSheetValue.Collapsed.ordinal()] = 1;
                            } catch (NoSuchFieldError e) {
                            }
                            try {
                                iArr[BottomSheetValue.Expanded.ordinal()] = 2;
                            } catch (NoSuchFieldError e2) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<BottomSheetValue>, ? extends BottomSheetValue> invoke(IntSize intSize, Constraints constraints) {
                        return m1443invokeGpV2Q24(intSize.getPackedValue(), constraints.getValue());
                    }

                    /* renamed from: invoke-GpV2Q24, reason: not valid java name */
                    public final Pair<DraggableAnchors<BottomSheetValue>, BottomSheetValue> m1443invokeGpV2Q24(long sheetSize, long constraints) {
                        BottomSheetValue newTarget;
                        final int layoutHeight = Constraints.m6578getMaxHeightimpl(constraints);
                        final float sheetHeight = IntSize.m6795getHeightimpl(sheetSize);
                        final float f = peekHeightPx;
                        DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<BottomSheetValue>, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$1$1$newAnchors$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<BottomSheetValue> draggableAnchorsConfig) {
                                invoke2(draggableAnchorsConfig);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DraggableAnchorsConfig<BottomSheetValue> draggableAnchorsConfig) {
                                draggableAnchorsConfig.at(BottomSheetValue.Collapsed, layoutHeight - f);
                                if (sheetHeight > 0.0f) {
                                    if (!(sheetHeight == f)) {
                                        draggableAnchorsConfig.at(BottomSheetValue.Expanded, layoutHeight - sheetHeight);
                                    }
                                }
                            }
                        });
                        switch (WhenMappings.$EnumSwitchMapping$0[BottomSheetState.this.getAnchoredDraggableState$material_release().getTargetValue().ordinal()]) {
                            case 1:
                                newTarget = BottomSheetValue.Collapsed;
                                break;
                            case 2:
                                if (!newAnchors.hasAnchorFor(BottomSheetValue.Expanded)) {
                                    newTarget = BottomSheetValue.Collapsed;
                                    break;
                                } else {
                                    newTarget = BottomSheetValue.Expanded;
                                    break;
                                }
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        return TuplesKt.to(newAnchors, newTarget);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            anchoredDraggable = AnchoredDraggableKt.anchoredDraggable(AnchoredDraggableKt.draggableAnchors(modifier3, anchoredDraggableState$material_release, orientation, (Function2) value$iv2), r15, Orientation.Vertical, (r14 & 4) != 0 ? true : sheetGesturesEnabled, (r14 & 8) != 0 ? false : false, (r14 & 16) != 0 ? null : null, (r14 & 32) != 0 ? state.getAnchoredDraggableState$material_release().isAnimationRunning() : false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1416570389, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 14) == 4) | $composer3.changedInstance(scope);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1
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
                        if (BottomSheetState.this.getAnchoredDraggableState$material_release().getAnchors().getSize() > 1) {
                            if (BottomSheetState.this.isCollapsed()) {
                                final BottomSheetState bottomSheetState = BottomSheetState.this;
                                final CoroutineScope coroutineScope = scope;
                                SemanticsPropertiesKt.expand$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* compiled from: BottomSheetScaffold.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {445}, m = "invokeSuspend", n = {}, s = {})
                                    /* renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1$1, reason: invalid class name and collision with other inner class name */
                                    static final class C00311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ BottomSheetState $state;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C00311(BottomSheetState bottomSheetState, Continuation<? super C00311> continuation) {
                                            super(2, continuation);
                                            this.$state = bottomSheetState;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new C00311(this.$state, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((C00311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    this.label = 1;
                                                    if (this.$state.expand(this) != coroutine_suspended) {
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

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        if (BottomSheetState.this.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Expanded).booleanValue()) {
                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00311(BottomSheetState.this, null), 3, null);
                                        }
                                        return true;
                                    }
                                }, 1, null);
                            } else {
                                final BottomSheetState bottomSheetState2 = BottomSheetState.this;
                                final CoroutineScope coroutineScope2 = scope;
                                SemanticsPropertiesKt.collapse$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* compiled from: BottomSheetScaffold.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {452}, m = "invokeSuspend", n = {}, s = {})
                                    /* renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$2$1, reason: invalid class name */
                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ BottomSheetState $state;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(BottomSheetState bottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$state = bottomSheetState;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new AnonymousClass1(this.$state, continuation);
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
                                                    if (this.$state.collapse(this) != coroutine_suspended) {
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

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        if (BottomSheetState.this.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Collapsed).booleanValue()) {
                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(BottomSheetState.this, null), 3, null);
                                        }
                                        return true;
                                    }
                                }, 1, null);
                            }
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SurfaceKt.m1660SurfaceFjzlyU(SemanticsModifierKt.semantics$default(anchoredDraggable, false, (Function1) value$iv, 1, null), sheetShape, sheetBackgroundColor, sheetContentColor, null, sheetElevation, ComposableLambdaKt.rememberComposableLambda(2055704138, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$3
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x013e  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r24, int r25) {
                    /*
                        Method dump skipped, instructions count: 322
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, (($dirty2 >> 3) & 112) | i2 | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (458752 & ($dirty2 << 6)), 16);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$4
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

                public final void invoke(Composer composer, int i4) {
                    BottomSheetScaffoldKt.m1438BottomSheetdAqlCkY(BottomSheetState.this, sheetGesturesEnabled, sheetShape, sheetElevation, sheetBackgroundColor, sheetContentColor, sheetPeekHeight, modifier2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0225  */
    /* renamed from: BottomSheetScaffoldLayout-HJHHjMs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1440BottomSheetScaffoldLayoutHJHHjMs(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, final float r35, final kotlin.jvm.functions.Function0<java.lang.Float> r36, final int r37, final androidx.compose.material.BottomSheetState r38, androidx.compose.runtime.Composer r39, final int r40) {
        /*
            Method dump skipped, instructions count: 714
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.m1440BottomSheetScaffoldLayoutHJHHjMs(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, float, kotlin.jvm.functions.Function0, int, androidx.compose.material.BottomSheetState, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState, Orientation orientation) {
        return new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1(anchoredDraggableState, orientation);
    }
}
