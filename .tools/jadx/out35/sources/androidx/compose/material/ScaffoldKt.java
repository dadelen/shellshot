package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Scaffold.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a§\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0013\b\u0002\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u00122\u0013\b\u0002\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u00122\u0019\b\u0002\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\u0002\b\u00122\u0013\b\u0002\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2 \b\u0002\u0010\u001c\u001a\u001a\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001b2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020$2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u009f\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0013\b\u0002\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u00122\u0013\b\u0002\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u00122\u0019\b\u0002\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\u0002\b\u00122\u0013\b\u0002\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2 \b\u0002\u0010\u001c\u001a\u001a\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001b2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020$2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a¨\u0001\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u00192\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u0012¢\u0006\u0002\b22\u001c\u0010)\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\u0002\b\u0012¢\u0006\u0002\b22\u0016\u00103\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u0012¢\u0006\u0002\b22\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u0012¢\u0006\u0002\b22\u0006\u0010\n\u001a\u00020\u000b2\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u0012¢\u0006\u0002\b2H\u0003ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a!\u00107\u001a\u00020\u000f2\b\b\u0002\u00108\u001a\u0002092\b\b\u0002\u0010:\u001a\u00020\u0016H\u0007¢\u0006\u0002\u0010;\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u001c\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/FabPlacement;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "Scaffold", "", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/ScaffoldState;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "Lkotlin/Function1;", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "isFloatingActionButtonDocked", "", "drawerContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "drawerGesturesEnabled", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerScrimColor", "backgroundColor", "contentColor", "content", "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-u4IkXBM", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Landroidx/compose/material/ScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Scaffold-27mzLpw", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/ScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ScaffoldLayout", "isFabDocked", "fabPosition", "Landroidx/compose/ui/UiComposable;", "snackbar", "fab", "ScaffoldLayout-i1QSOvI", "(ZILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "rememberScaffoldState", "drawerState", "Landroidx/compose/material/DrawerState;", "snackbarHostState", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ScaffoldState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ScaffoldKt {
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(new Function0<FabPlacement>() { // from class: androidx.compose.material.ScaffoldKt$LocalFabPlacement$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FabPlacement invoke() {
            return null;
        }
    });
    private static final float FabSpacing = Dp.m6626constructorimpl(16);

    public static final ScaffoldState rememberScaffoldState(DrawerState drawerState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Object value$iv;
        Object value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, 1569641925, "C(rememberScaffoldState)72@2753L39,73@2837L32,74@2889L62:Scaffold.kt#jmzs0o");
        if ((i & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, $composer, 6, 2);
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -694661784, "CC(remember):Scaffold.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new SnackbarHostState();
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv;
            }
            snackbarHostState = (SnackbarHostState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1569641925, $changed, -1, "androidx.compose.material.rememberScaffoldState (Scaffold.kt:74)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -694660090, "CC(remember):Scaffold.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv = new ScaffoldState(drawerState, snackbarHostState);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv2;
        }
        ScaffoldState scaffoldState = (ScaffoldState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return scaffoldState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x01fb, code lost:
    
        if (r4.changed(r55) != false) goto L163;
     */
    /* JADX WARN: Removed duplicated region for block: B:146:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0572  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x054c  */
    /* renamed from: Scaffold-u4IkXBM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1632Scaffoldu4IkXBM(final androidx.compose.foundation.layout.WindowInsets r40, androidx.compose.ui.Modifier r41, androidx.compose.material.ScaffoldState r42, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r43, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r44, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r45, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r46, int r47, final boolean r48, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r49, boolean r50, androidx.compose.ui.graphics.Shape r51, float r52, long r53, long r55, long r57, long r59, long r61, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r63, androidx.compose.runtime.Composer r64, final int r65, final int r66, final int r67) {
        /*
            Method dump skipped, instructions count: 1465
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ScaffoldKt.m1632Scaffoldu4IkXBM(androidx.compose.foundation.layout.WindowInsets, androidx.compose.ui.Modifier, androidx.compose.material.ScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, kotlin.jvm.functions.Function3, boolean, androidx.compose.ui.graphics.Shape, float, long, long, long, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x01df, code lost:
    
        if (r3.changed(r4) != false) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01fd, code lost:
    
        if (r3.changed(r54) != false) goto L165;
     */
    /* renamed from: Scaffold-27mzLpw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1631Scaffold27mzLpw(androidx.compose.ui.Modifier r38, androidx.compose.material.ScaffoldState r39, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r43, int r44, boolean r45, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r46, boolean r47, androidx.compose.ui.graphics.Shape r48, float r49, long r50, long r52, long r54, long r56, long r58, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r60, androidx.compose.runtime.Composer r61, final int r62, final int r63, final int r64) {
        /*
            Method dump skipped, instructions count: 1319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ScaffoldKt.m1631Scaffold27mzLpw(androidx.compose.ui.Modifier, androidx.compose.material.ScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, kotlin.jvm.functions.Function3, boolean, androidx.compose.ui.graphics.Shape, float, long, long, long, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ScaffoldLayout-i1QSOvI, reason: not valid java name */
    public static final void m1633ScaffoldLayouti1QSOvI(final boolean isFabDocked, final int fabPosition, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final WindowInsets contentWindowInsets, final Function2<? super Composer, ? super Integer, Unit> function24, Composer $composer, final int $changed) {
        boolean z;
        final int i;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function32;
        final Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        WindowInsets windowInsets;
        Function2<? super Composer, ? super Integer, Unit> function28;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-468424875);
        ComposerKt.sourceInformation($composer2, "C(ScaffoldLayout)P(5,4:c#material.FabPosition,7,1,6,3,2)391@17701L6677,391@17684L6694:Scaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            z = isFabDocked;
            $dirty |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = isFabDocked;
        }
        if (($changed & 48) == 0) {
            i = fabPosition;
            $dirty |= $composer2.changed(i) ? 32 : 16;
        } else {
            i = fabPosition;
        }
        if (($changed & 384) == 0) {
            function25 = function2;
            $dirty |= $composer2.changedInstance(function25) ? 256 : 128;
        } else {
            function25 = function2;
        }
        if (($changed & 3072) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 2048 : 1024;
        } else {
            function32 = function3;
        }
        if (($changed & 24576) == 0) {
            function26 = function22;
            $dirty |= $composer2.changedInstance(function26) ? 16384 : 8192;
        } else {
            function26 = function22;
        }
        if ((196608 & $changed) == 0) {
            function27 = function23;
            $dirty |= $composer2.changedInstance(function27) ? 131072 : 65536;
        } else {
            function27 = function23;
        }
        if (($changed & 1572864) == 0) {
            windowInsets = contentWindowInsets;
            $dirty |= $composer2.changed(windowInsets) ? 1048576 : 524288;
        } else {
            windowInsets = contentWindowInsets;
        }
        if (($changed & 12582912) == 0) {
            function28 = function24;
            $dirty |= $composer2.changedInstance(function28) ? 8388608 : 4194304;
        } else {
            function28 = function24;
        }
        if (($dirty & 4793491) != 4793490 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-468424875, $dirty, -1, "androidx.compose.material.ScaffoldLayout (Scaffold.kt:390)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -771533477, "CC(remember):Scaffold.kt#9igjgp");
            boolean invalid$iv = (($dirty & 896) == 256) | ((57344 & $dirty) == 16384) | ((3670016 & $dirty) == 1048576) | ((458752 & $dirty) == 131072) | (($dirty & 112) == 32) | (($dirty & 14) == 4) | ((29360128 & $dirty) == 8388608) | (($dirty & 7168) == 2048);
            Object value$iv = $composer2.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                final boolean z2 = z;
                final WindowInsets windowInsets2 = windowInsets;
                final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function33 = function32;
                final Function2<? super Composer, ? super Integer, Unit> function29 = function28;
                i2 = 0;
                final Function2<? super Composer, ? super Integer, Unit> function210 = function27;
                value$iv = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1635invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1635invoke0kLqBqw(final SubcomposeMeasureScope $this$SubcomposeLayout, long constraints) {
                        long looseConstraints;
                        Object maxElem$iv;
                        Object maxElem$iv2;
                        FabPlacement fabPlacement;
                        Object maxElem$iv3;
                        final Integer fabOffsetFromBottom;
                        final int snackbarOffsetFromBottom;
                        long looseConstraints2;
                        float f;
                        int i3;
                        float f2;
                        Object maxElem$iv4;
                        Object maxElem$iv5;
                        int fabLeftOffset;
                        float f3;
                        float f4;
                        float f5;
                        float f6;
                        int layoutWidth = Constraints.m6579getMaxWidthimpl(constraints);
                        final int layoutHeight = Constraints.m6578getMaxHeightimpl(constraints);
                        looseConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
                        List $this$fastMap$iv = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.TopBar, function25);
                        List target$iv = new ArrayList($this$fastMap$iv.size());
                        int index$iv$iv = 0;
                        int size = $this$fastMap$iv.size();
                        while (index$iv$iv < size) {
                            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                            List $this$fastMap$iv2 = $this$fastMap$iv;
                            Measurable it = (Measurable) item$iv$iv;
                            target$iv.add(it.mo5491measureBRTryo0(looseConstraints));
                            index$iv$iv++;
                            $this$fastMap$iv = $this$fastMap$iv2;
                        }
                        List topBarPlaceables = target$iv;
                        if (topBarPlaceables.isEmpty()) {
                            maxElem$iv = null;
                        } else {
                            maxElem$iv = topBarPlaceables.get(0);
                            Placeable it2 = (Placeable) maxElem$iv;
                            int maxValue$iv = it2.getHeight();
                            int i$iv = 1;
                            int lastIndex = CollectionsKt.getLastIndex(topBarPlaceables);
                            if (1 <= lastIndex) {
                                while (true) {
                                    Object e$iv = topBarPlaceables.get(i$iv);
                                    Placeable it3 = (Placeable) e$iv;
                                    int v$iv = it3.getHeight();
                                    if (maxValue$iv < v$iv) {
                                        maxElem$iv = e$iv;
                                        maxValue$iv = v$iv;
                                    }
                                    if (i$iv == lastIndex) {
                                        break;
                                    }
                                    i$iv++;
                                }
                            }
                        }
                        Placeable placeable = (Placeable) maxElem$iv;
                        final int topBarHeight = placeable != null ? placeable.getHeight() : 0;
                        List $this$fastMap$iv3 = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.Snackbar, function26);
                        WindowInsets windowInsets3 = windowInsets2;
                        int $i$f$fastMap = 0;
                        List target$iv2 = new ArrayList($this$fastMap$iv3.size());
                        int index$iv$iv2 = 0;
                        int size2 = $this$fastMap$iv3.size();
                        while (index$iv$iv2 < size2) {
                            Object item$iv$iv2 = $this$fastMap$iv3.get(index$iv$iv2);
                            List $this$fastMap$iv4 = $this$fastMap$iv3;
                            Measurable it4 = (Measurable) item$iv$iv2;
                            List topBarPlaceables2 = topBarPlaceables;
                            int $i$f$fastMap2 = $i$f$fastMap;
                            int leftInset = windowInsets3.getLeft($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
                            int i4 = size2;
                            int rightInset = windowInsets3.getRight($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
                            int bottomInset = windowInsets3.getBottom($this$SubcomposeLayout);
                            WindowInsets windowInsets4 = windowInsets3;
                            int i5 = (-leftInset) - rightInset;
                            int leftInset2 = -bottomInset;
                            target$iv2.add(it4.mo5491measureBRTryo0(ConstraintsKt.m6598offsetNN6EwU(looseConstraints, i5, leftInset2)));
                            index$iv$iv2++;
                            $this$fastMap$iv3 = $this$fastMap$iv4;
                            topBarPlaceables = topBarPlaceables2;
                            $i$f$fastMap = $i$f$fastMap2;
                            size2 = i4;
                            windowInsets3 = windowInsets4;
                        }
                        final List topBarPlaceables3 = topBarPlaceables;
                        final List snackbarPlaceables = target$iv2;
                        if (snackbarPlaceables.isEmpty()) {
                            maxElem$iv2 = null;
                        } else {
                            maxElem$iv2 = snackbarPlaceables.get(0);
                            Placeable it5 = (Placeable) maxElem$iv2;
                            int maxValue$iv2 = it5.getHeight();
                            int i$iv2 = 1;
                            int lastIndex2 = CollectionsKt.getLastIndex(snackbarPlaceables);
                            if (1 <= lastIndex2) {
                                while (true) {
                                    Object e$iv2 = snackbarPlaceables.get(i$iv2);
                                    Placeable it6 = (Placeable) e$iv2;
                                    int v$iv2 = it6.getHeight();
                                    if (maxValue$iv2 < v$iv2) {
                                        maxElem$iv2 = e$iv2;
                                        maxValue$iv2 = v$iv2;
                                    }
                                    if (i$iv2 == lastIndex2) {
                                        break;
                                    }
                                    i$iv2++;
                                }
                            }
                        }
                        Placeable placeable2 = (Placeable) maxElem$iv2;
                        int snackbarHeight = placeable2 != null ? placeable2.getHeight() : 0;
                        List $this$fastMap$iv5 = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.Fab, function210);
                        WindowInsets windowInsets5 = windowInsets2;
                        int $i$f$fastMap3 = 0;
                        ArrayList target$iv3 = new ArrayList($this$fastMap$iv5.size());
                        List $this$fastForEach$iv$iv = $this$fastMap$iv5;
                        int $i$f$fastForEach = 0;
                        int index$iv$iv3 = 0;
                        int size3 = $this$fastForEach$iv$iv.size();
                        while (index$iv$iv3 < size3) {
                            Object item$iv$iv3 = $this$fastForEach$iv$iv.get(index$iv$iv3);
                            List $this$fastMap$iv6 = $this$fastMap$iv5;
                            ArrayList arrayList = target$iv3;
                            int $i$f$fastMap4 = $i$f$fastMap3;
                            Measurable measurable = (Measurable) item$iv$iv3;
                            ArrayList target$iv4 = target$iv3;
                            List $this$fastForEach$iv$iv2 = $this$fastForEach$iv$iv;
                            int leftInset3 = windowInsets5.getLeft($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
                            int $i$f$fastForEach2 = $i$f$fastForEach;
                            int rightInset2 = windowInsets5.getRight($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
                            int bottomInset2 = windowInsets5.getBottom($this$SubcomposeLayout);
                            WindowInsets windowInsets6 = windowInsets5;
                            int i6 = (-leftInset3) - rightInset2;
                            int leftInset4 = -bottomInset2;
                            arrayList.add(measurable.mo5491measureBRTryo0(ConstraintsKt.m6598offsetNN6EwU(looseConstraints, i6, leftInset4)));
                            index$iv$iv3++;
                            $this$fastMap$iv5 = $this$fastMap$iv6;
                            $i$f$fastMap3 = $i$f$fastMap4;
                            target$iv3 = target$iv4;
                            $this$fastForEach$iv$iv = $this$fastForEach$iv$iv2;
                            $i$f$fastForEach = $i$f$fastForEach2;
                            windowInsets5 = windowInsets6;
                        }
                        final ArrayList fabPlaceables = target$iv3;
                        if (fabPlaceables.isEmpty()) {
                            fabPlacement = null;
                        } else {
                            if (fabPlaceables.isEmpty()) {
                                maxElem$iv4 = null;
                            } else {
                                maxElem$iv4 = fabPlaceables.get(0);
                                Placeable it7 = (Placeable) maxElem$iv4;
                                int maxValue$iv3 = it7.getWidth();
                                int i$iv3 = 1;
                                int lastIndex3 = CollectionsKt.getLastIndex(fabPlaceables);
                                if (1 <= lastIndex3) {
                                    while (true) {
                                        Object e$iv3 = fabPlaceables.get(i$iv3);
                                        Placeable it8 = (Placeable) e$iv3;
                                        int v$iv3 = it8.getWidth();
                                        if (maxValue$iv3 < v$iv3) {
                                            maxElem$iv4 = e$iv3;
                                            maxValue$iv3 = v$iv3;
                                        }
                                        if (i$iv3 == lastIndex3) {
                                            break;
                                        }
                                        i$iv3++;
                                    }
                                }
                            }
                            Placeable placeable3 = (Placeable) maxElem$iv4;
                            int fabWidth = placeable3 != null ? placeable3.getWidth() : 0;
                            List $this$fastMaxBy$iv = fabPlaceables;
                            if ($this$fastMaxBy$iv.isEmpty()) {
                                maxElem$iv5 = null;
                            } else {
                                maxElem$iv5 = $this$fastMaxBy$iv.get(0);
                                Placeable it9 = (Placeable) maxElem$iv5;
                                int maxValue$iv4 = it9.getHeight();
                                int i$iv4 = 1;
                                int lastIndex4 = CollectionsKt.getLastIndex($this$fastMaxBy$iv);
                                if (1 <= lastIndex4) {
                                    while (true) {
                                        Object e$iv4 = $this$fastMaxBy$iv.get(i$iv4);
                                        Placeable it10 = (Placeable) e$iv4;
                                        int height = it10.getHeight();
                                        List $this$fastMaxBy$iv2 = $this$fastMaxBy$iv;
                                        if (maxValue$iv4 < height) {
                                            maxElem$iv5 = e$iv4;
                                            maxValue$iv4 = height;
                                        }
                                        if (i$iv4 == lastIndex4) {
                                            break;
                                        }
                                        i$iv4++;
                                        $this$fastMaxBy$iv = $this$fastMaxBy$iv2;
                                    }
                                }
                            }
                            Placeable placeable4 = (Placeable) maxElem$iv5;
                            int fabHeight = placeable4 != null ? placeable4.getHeight() : 0;
                            if (fabWidth == 0 || fabHeight == 0) {
                                fabPlacement = null;
                            } else {
                                int i7 = i;
                                if (FabPosition.m1553equalsimpl0(i7, FabPosition.INSTANCE.m1559getStart5ygKITE())) {
                                    if ($this$SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr) {
                                        f6 = ScaffoldKt.FabSpacing;
                                        fabLeftOffset = $this$SubcomposeLayout.mo358roundToPx0680j_4(f6);
                                    } else {
                                        f5 = ScaffoldKt.FabSpacing;
                                        fabLeftOffset = (layoutWidth - $this$SubcomposeLayout.mo358roundToPx0680j_4(f5)) - fabWidth;
                                    }
                                } else if (!FabPosition.m1553equalsimpl0(i7, FabPosition.INSTANCE.m1558getEnd5ygKITE())) {
                                    fabLeftOffset = (layoutWidth - fabWidth) / 2;
                                } else if ($this$SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr) {
                                    f4 = ScaffoldKt.FabSpacing;
                                    fabLeftOffset = (layoutWidth - $this$SubcomposeLayout.mo358roundToPx0680j_4(f4)) - fabWidth;
                                } else {
                                    f3 = ScaffoldKt.FabSpacing;
                                    fabLeftOffset = $this$SubcomposeLayout.mo358roundToPx0680j_4(f3);
                                }
                                fabPlacement = new FabPlacement(z2, fabLeftOffset, fabWidth, fabHeight);
                            }
                        }
                        final FabPlacement fabPlacement2 = fabPlacement;
                        ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                        final Function2<Composer, Integer, Unit> function211 = function29;
                        List $this$fastMap$iv7 = $this$SubcomposeLayout.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(424088350, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$bottomBarPlaceables$1
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

                            public final void invoke(Composer $composer3, int $changed2) {
                                ComposerKt.sourceInformation($composer3, "C476@21201L132:Scaffold.kt#jmzs0o");
                                if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(424088350, $changed2, -1, "androidx.compose.material.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:476)");
                                    }
                                    CompositionLocalKt.CompositionLocalProvider(ScaffoldKt.getLocalFabPlacement().provides(FabPlacement.this), function211, $composer3, ProvidedValue.$stable);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer3.skipToGroupEnd();
                            }
                        }));
                        int $i$f$fastMap5 = 0;
                        List target$iv5 = new ArrayList($this$fastMap$iv7.size());
                        int index$iv$iv4 = 0;
                        int size4 = $this$fastMap$iv7.size();
                        while (index$iv$iv4 < size4) {
                            Object item$iv$iv4 = $this$fastMap$iv7.get(index$iv$iv4);
                            int $i$f$fastMap6 = $i$f$fastMap5;
                            Measurable it11 = (Measurable) item$iv$iv4;
                            target$iv5.add(it11.mo5491measureBRTryo0(looseConstraints));
                            index$iv$iv4++;
                            $this$fastMap$iv7 = $this$fastMap$iv7;
                            $i$f$fastMap5 = $i$f$fastMap6;
                        }
                        final List bottomBarPlaceables = target$iv5;
                        List $this$fastMaxBy$iv3 = bottomBarPlaceables;
                        if ($this$fastMaxBy$iv3.isEmpty()) {
                            maxElem$iv3 = null;
                        } else {
                            maxElem$iv3 = $this$fastMaxBy$iv3.get(0);
                            Placeable it12 = (Placeable) maxElem$iv3;
                            int maxValue$iv5 = it12.getHeight();
                            int i$iv5 = 1;
                            int lastIndex5 = CollectionsKt.getLastIndex($this$fastMaxBy$iv3);
                            if (1 <= lastIndex5) {
                                while (true) {
                                    Object e$iv5 = $this$fastMaxBy$iv3.get(i$iv5);
                                    Placeable it13 = (Placeable) e$iv5;
                                    int height2 = it13.getHeight();
                                    List $this$fastMaxBy$iv4 = $this$fastMaxBy$iv3;
                                    if (maxValue$iv5 < height2) {
                                        maxElem$iv3 = e$iv5;
                                        maxValue$iv5 = height2;
                                    }
                                    if (i$iv5 == lastIndex5) {
                                        break;
                                    }
                                    i$iv5++;
                                    $this$fastMaxBy$iv3 = $this$fastMaxBy$iv4;
                                }
                            }
                        }
                        Placeable placeable5 = (Placeable) maxElem$iv3;
                        final Integer bottomBarHeight = placeable5 != null ? Integer.valueOf(placeable5.getHeight()) : null;
                        if (fabPlacement2 != null) {
                            WindowInsets windowInsets7 = windowInsets2;
                            boolean z3 = z2;
                            if (bottomBarHeight == null) {
                                int height3 = fabPlacement2.getHeight();
                                f2 = ScaffoldKt.FabSpacing;
                                i3 = height3 + $this$SubcomposeLayout.mo358roundToPx0680j_4(f2) + windowInsets7.getBottom($this$SubcomposeLayout);
                            } else if (z3) {
                                i3 = (fabPlacement2.getHeight() / 2) + bottomBarHeight.intValue();
                            } else {
                                int intValue = bottomBarHeight.intValue() + fabPlacement2.getHeight();
                                f = ScaffoldKt.FabSpacing;
                                i3 = $this$SubcomposeLayout.mo358roundToPx0680j_4(f) + intValue;
                            }
                            fabOffsetFromBottom = Integer.valueOf(i3);
                        } else {
                            fabOffsetFromBottom = null;
                        }
                        if (snackbarHeight != 0) {
                            snackbarOffsetFromBottom = snackbarHeight + (fabOffsetFromBottom != null ? fabOffsetFromBottom.intValue() : bottomBarHeight != null ? bottomBarHeight.intValue() : windowInsets2.getBottom($this$SubcomposeLayout));
                        } else {
                            snackbarOffsetFromBottom = 0;
                        }
                        int bodyContentHeight = layoutHeight - topBarHeight;
                        ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                        final WindowInsets windowInsets8 = windowInsets2;
                        final Function3<PaddingValues, Composer, Integer, Unit> function34 = function33;
                        List $this$fastMap$iv8 = $this$SubcomposeLayout.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(-570781649, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$bodyContentPlaceables$1
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

                            public final void invoke(Composer $composer3, int $changed2) {
                                float m6626constructorimpl;
                                float bottom;
                                ComposerKt.sourceInformation($composer3, "C527@23357L21:Scaffold.kt#jmzs0o");
                                if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-570781649, $changed2, -1, "androidx.compose.material.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:510)");
                                    }
                                    PaddingValues insets = WindowInsetsKt.asPaddingValues(WindowInsets.this, $this$SubcomposeLayout);
                                    if (topBarPlaceables3.isEmpty()) {
                                        m6626constructorimpl = insets.getTop();
                                    } else {
                                        m6626constructorimpl = Dp.m6626constructorimpl(0);
                                    }
                                    if (bottomBarPlaceables.isEmpty() || bottomBarHeight == null) {
                                        bottom = insets.getBottom();
                                    } else {
                                        bottom = $this$SubcomposeLayout.mo361toDpu2uoSUM(bottomBarHeight.intValue());
                                    }
                                    PaddingValues innerPadding = PaddingKt.m668PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(insets, $this$SubcomposeLayout.getLayoutDirection()), m6626constructorimpl, PaddingKt.calculateEndPadding(insets, $this$SubcomposeLayout.getLayoutDirection()), bottom);
                                    function34.invoke(innerPadding, $composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer3.skipToGroupEnd();
                            }
                        }));
                        List target$iv6 = new ArrayList($this$fastMap$iv8.size());
                        int size5 = $this$fastMap$iv8.size();
                        int index$iv$iv5 = 0;
                        while (index$iv$iv5 < size5) {
                            Object item$iv$iv5 = $this$fastMap$iv8.get(index$iv$iv5);
                            int index$iv$iv6 = index$iv$iv5;
                            Measurable it14 = (Measurable) item$iv$iv5;
                            long looseConstraints3 = looseConstraints;
                            looseConstraints2 = Constraints.m6569copyZbe2FdA(looseConstraints3, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(looseConstraints3) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(looseConstraints3) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(looseConstraints3) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(looseConstraints3) : bodyContentHeight);
                            target$iv6.add(it14.mo5491measureBRTryo0(looseConstraints2));
                            index$iv$iv5 = index$iv$iv6 + 1;
                            size5 = size5;
                            looseConstraints = looseConstraints3;
                        }
                        final List bodyContentPlaceables = target$iv6;
                        final Integer bottomBarHeight2 = bottomBarHeight;
                        return MeasureScope.layout$default($this$SubcomposeLayout, layoutWidth, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope $this$layout) {
                                List $this$fastForEach$iv = bodyContentPlaceables;
                                int i8 = topBarHeight;
                                int size6 = $this$fastForEach$iv.size();
                                for (int index$iv = 0; index$iv < size6; index$iv++) {
                                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                                    Placeable it15 = (Placeable) item$iv;
                                    Placeable.PlacementScope.place$default($this$layout, it15, 0, i8, 0.0f, 4, null);
                                }
                                List $this$fastForEach$iv2 = topBarPlaceables3;
                                int size7 = $this$fastForEach$iv2.size();
                                for (int index$iv2 = 0; index$iv2 < size7; index$iv2++) {
                                    Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                                    Placeable it16 = (Placeable) item$iv2;
                                    Placeable.PlacementScope.place$default($this$layout, it16, 0, 0, 0.0f, 4, null);
                                }
                                List $this$fastForEach$iv3 = snackbarPlaceables;
                                int i9 = layoutHeight;
                                int i10 = snackbarOffsetFromBottom;
                                int size8 = $this$fastForEach$iv3.size();
                                for (int index$iv3 = 0; index$iv3 < size8; index$iv3++) {
                                    Object item$iv3 = $this$fastForEach$iv3.get(index$iv3);
                                    Placeable it17 = (Placeable) item$iv3;
                                    Placeable.PlacementScope.place$default($this$layout, it17, 0, i9 - i10, 0.0f, 4, null);
                                }
                                List $this$fastForEach$iv4 = bottomBarPlaceables;
                                int i11 = layoutHeight;
                                Integer num = bottomBarHeight2;
                                int index$iv4 = 0;
                                int size9 = $this$fastForEach$iv4.size();
                                while (true) {
                                    int i12 = 0;
                                    if (index$iv4 >= size9) {
                                        break;
                                    }
                                    Object item$iv4 = $this$fastForEach$iv4.get(index$iv4);
                                    Placeable it18 = (Placeable) item$iv4;
                                    if (num != null) {
                                        i12 = num.intValue();
                                    }
                                    Placeable.PlacementScope.place$default($this$layout, it18, 0, i11 - i12, 0.0f, 4, null);
                                    index$iv4++;
                                }
                                List $this$fastForEach$iv5 = fabPlaceables;
                                FabPlacement fabPlacement3 = fabPlacement2;
                                int i13 = layoutHeight;
                                Integer num2 = fabOffsetFromBottom;
                                int size10 = $this$fastForEach$iv5.size();
                                for (int index$iv5 = 0; index$iv5 < size10; index$iv5++) {
                                    Object item$iv5 = $this$fastForEach$iv5.get(index$iv5);
                                    Placeable it19 = (Placeable) item$iv5;
                                    Placeable.PlacementScope.place$default($this$layout, it19, fabPlacement3 != null ? fabPlacement3.getLeft() : 0, i13 - (num2 != null ? num2.intValue() : 0), 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                i2 = 0;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) value$iv, $composer2, i2, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$2
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

                public final void invoke(Composer composer, int i3) {
                    ScaffoldKt.m1633ScaffoldLayouti1QSOvI(isFabDocked, fabPosition, function2, function3, function22, function23, contentWindowInsets, function24, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }
}
