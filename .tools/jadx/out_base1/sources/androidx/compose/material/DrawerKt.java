package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.shellshot.data.LogRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Drawer.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0093\u0001\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001a2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\n0\u001e¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a0\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u001a2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\u0006\u0010$\u001a\u00020\u0015H\u0003ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u0014\u0010'\u001a\u00020(2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*H\u0002\u001a\u0093\u0001\u0010+\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020,2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001a2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\n0\u001e¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a>\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\u00152\f\u00101\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001aH\u0003ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a \u00105\u001a\u00020\u00022\u0006\u00106\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u00020\u0002H\u0002\u001a;\u00109\u001a\u00020\u00132\u0006\u0010:\u001a\u00020;2\u0014\b\u0002\u0010<\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u00150\f2\u000e\b\u0002\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00020>H\u0007¢\u0006\u0002\u0010?\u001a+\u0010@\u001a\u00020,2\u0006\u0010:\u001a\u00020A2\u0014\b\u0002\u0010<\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u00150\fH\u0007¢\u0006\u0002\u0010B\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C²\u0006\n\u0010D\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "BottomDrawerOpenFraction", "DrawerPositionalThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "DrawerVelocityThreshold", "EndDrawerPadding", "BottomDrawer", "", "drawerContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "drawerState", "Landroidx/compose/material/BottomDrawerState;", "gesturesEnabled", "", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "scrimColor", "content", "Lkotlin/Function0;", "BottomDrawer-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomDrawerState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "BottomDrawerScrim", "color", "onDismiss", "visible", "BottomDrawerScrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/material/AnchoredDraggableState;", "ModalDrawer", "Landroidx/compose/material/DrawerState;", "ModalDrawer-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/DrawerState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Scrim", "open", "onClose", "fraction", "Scrim-Bx497Mc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "calculateFraction", "a", "b", "pos", "rememberBottomDrawerState", "initialValue", "Landroidx/compose/material/BottomDrawerValue;", "confirmStateChange", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/material/BottomDrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomDrawerState;", "rememberDrawerState", "Landroidx/compose/material/DrawerValue;", "(Landroidx/compose/material/DrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/DrawerState;", "material_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DrawerKt {
    private static final float BottomDrawerOpenFraction = 0.5f;
    private static final float EndDrawerPadding = Dp.m6626constructorimpl(56);
    private static final float DrawerPositionalThreshold = Dp.m6626constructorimpl(56);
    private static final float DrawerVelocityThreshold = Dp.m6626constructorimpl(LogRepository.MAX_PERSISTED_COUNT);
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    public static final DrawerState rememberDrawerState(final DrawerValue initialValue, final Function1<? super DrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1435874229, "C(rememberDrawerState)P(1)430@15148L61,430@15084L125:Drawer.kt#jmzs0o");
        if ((i & 2) != 0) {
            Function1 confirmStateChange = new Function1<DrawerValue, Boolean>() { // from class: androidx.compose.material.DrawerKt$rememberDrawerState$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DrawerValue it) {
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1435874229, $changed, -1, "androidx.compose.material.rememberDrawerState (Drawer.kt:429)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.INSTANCE.Saver(function1);
        ComposerKt.sourceInformationMarkerStart($composer, -524628406, "CC(remember):Drawer.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue)) || ($changed & 6) == 4);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<DrawerState>() { // from class: androidx.compose.material.DrawerKt$rememberDrawerState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DrawerState invoke() {
                    return new DrawerState(DrawerValue.this, function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        DrawerState drawerState = (DrawerState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return drawerState;
    }

    public static final BottomDrawerState rememberBottomDrawerState(final BottomDrawerValue initialValue, final Function1<? super BottomDrawerValue, Boolean> function1, final AnimationSpec<Float> animationSpec, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1477366969, "C(rememberBottomDrawerState)P(2,1)449@15823L7,453@15967L91,450@15842L216:Drawer.kt#jmzs0o");
        if ((i & 2) != 0) {
            Function1 confirmStateChange = new Function1<BottomDrawerValue, Boolean>() { // from class: androidx.compose.material.DrawerKt$rememberBottomDrawerState$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BottomDrawerValue it) {
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if ((i & 4) != 0) {
            AnimationSpec animationSpec2 = DrawerDefaults.INSTANCE.getAnimationSpec();
            animationSpec = animationSpec2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1477366969, $changed, -1, "androidx.compose.material.rememberBottomDrawerState (Drawer.kt:448)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) consume;
        Object[] objArr = {density};
        Saver<BottomDrawerState, BottomDrawerValue> Saver = BottomDrawerState.Companion.Saver(density, function1, animationSpec);
        ComposerKt.sourceInformationMarkerStart($composer, -1985305955, "CC(remember):Drawer.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue)) || ($changed & 6) == 4) | $composer.changed(density) | (((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32) | $composer.changedInstance(animationSpec);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<BottomDrawerState>() { // from class: androidx.compose.material.DrawerKt$rememberBottomDrawerState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BottomDrawerState invoke() {
                    return new BottomDrawerState(BottomDrawerValue.this, density, function1, animationSpec);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        BottomDrawerState bottomDrawerState = (BottomDrawerState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return bottomDrawerState;
    }

    /* renamed from: ModalDrawer-Gs3lGvM, reason: not valid java name */
    public static final void m1530ModalDrawerGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, Shape drawerShape, float drawerElevation, long drawerBackgroundColor, long drawerContentColor, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DrawerState drawerState2;
        boolean z;
        Shape shape;
        float f;
        int i2;
        long drawerBackgroundColor2;
        int $dirty;
        int $dirty2;
        Modifier modifier3;
        DrawerState drawerState3;
        Shape drawerShape2;
        long drawerContentColor2;
        final long scrimColor2;
        final DrawerState drawerState4;
        final long drawerBackgroundColor3;
        final boolean gesturesEnabled2;
        final Shape drawerShape3;
        final float drawerElevation2;
        final long drawerContentColor3;
        Object value$iv$iv;
        Composer $composer2;
        final Modifier modifier4;
        final DrawerState drawerState5;
        final boolean gesturesEnabled3;
        final long scrimColor3;
        final Shape drawerShape4;
        final long drawerBackgroundColor4;
        final long drawerContentColor4;
        final float drawerElevation3;
        int i3;
        int $dirty3;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(1305806945);
        ComposerKt.sourceInformation($composer3, "C(ModalDrawer)P(2,8,6,7,5,4:c#ui.unit.Dp,1:c#ui.graphics.Color,3:c#ui.graphics.Color,9:c#ui.graphics.Color)492@17868L39,494@17986L5,496@18095L15,497@18144L38,498@18223L10,501@18291L24,502@18363L3449,502@18320L3492:Drawer.kt#jmzs0o");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                if ($composer3.changed(drawerState2)) {
                    i7 = 256;
                    $dirty4 |= i7;
                }
            } else {
                drawerState2 = drawerState;
            }
            i7 = 128;
            $dirty4 |= i7;
        } else {
            drawerState2 = drawerState;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty4 |= 3072;
            z = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            z = gesturesEnabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = gesturesEnabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape = drawerShape;
                if ($composer3.changed(shape)) {
                    i6 = 16384;
                    $dirty4 |= i6;
                }
            } else {
                shape = drawerShape;
            }
            i6 = 8192;
            $dirty4 |= i6;
        } else {
            shape = drawerShape;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = drawerElevation;
        } else if ((196608 & $changed) == 0) {
            f = drawerElevation;
            $dirty4 |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = drawerElevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                i2 = i8;
                drawerBackgroundColor2 = drawerBackgroundColor;
                if ($composer3.changed(drawerBackgroundColor2)) {
                    i5 = 1048576;
                    $dirty4 |= i5;
                }
            } else {
                i2 = i8;
                drawerBackgroundColor2 = drawerBackgroundColor;
            }
            i5 = 524288;
            $dirty4 |= i5;
        } else {
            i2 = i8;
            drawerBackgroundColor2 = drawerBackgroundColor;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                if ($composer3.changed(drawerContentColor)) {
                    i4 = 8388608;
                    $dirty = $dirty3 | i4;
                }
            } else {
                $dirty3 = $dirty4;
            }
            i4 = 4194304;
            $dirty = $dirty3 | i4;
        } else {
            $dirty = $dirty4;
        }
        if (($changed & 100663296) == 0) {
            int $dirty5 = $dirty;
            if ((i & 256) == 0 && $composer3.changed(scrimColor)) {
                i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty = $dirty5 | i3;
            }
            i3 = 33554432;
            $dirty = $dirty5 | i3;
        }
        int $dirty6 = $dirty;
        if ((i & 512) != 0) {
            $dirty2 = $dirty6 | 805306368;
        } else {
            if (($changed & 805306368) == 0) {
                $dirty6 |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
            }
            $dirty2 = $dirty6;
        }
        if (($dirty2 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            drawerState5 = drawerState2;
            drawerElevation3 = f;
            scrimColor3 = scrimColor;
            drawerBackgroundColor4 = drawerBackgroundColor2;
            gesturesEnabled3 = z;
            drawerShape4 = shape;
            drawerContentColor4 = drawerContentColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    modifier3 = modifier5;
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    modifier3 = modifier5;
                    drawerState3 = drawerState2;
                }
                boolean gesturesEnabled4 = i9 != 0 ? true : z;
                if ((i & 16) != 0) {
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                float drawerElevation4 = i10 != 0 ? DrawerDefaults.INSTANCE.m1527getElevationD9Ej5fM() : f;
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = DrawerDefaults.INSTANCE.getBackgroundColor($composer3, 6);
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1496contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                    drawerState4 = drawerState3;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    gesturesEnabled2 = gesturesEnabled4;
                    drawerShape3 = drawerShape2;
                    drawerElevation2 = drawerElevation4;
                    drawerContentColor3 = drawerContentColor2;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    modifier2 = modifier3;
                } else {
                    scrimColor2 = scrimColor;
                    drawerState4 = drawerState3;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    gesturesEnabled2 = gesturesEnabled4;
                    drawerShape3 = drawerShape2;
                    drawerElevation2 = drawerElevation4;
                    drawerContentColor3 = drawerContentColor2;
                    modifier2 = modifier3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                drawerContentColor3 = drawerContentColor;
                scrimColor2 = scrimColor;
                drawerBackgroundColor3 = drawerBackgroundColor2;
                drawerState4 = drawerState2;
                gesturesEnabled2 = z;
                drawerShape3 = shape;
                drawerElevation2 = f;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1305806945, $dirty2, -1, "androidx.compose.material.ModalDrawer (Drawer.kt:500)");
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
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifier2, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(816674999, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
                    invoke(boxWithConstraintsScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:26:0x00d4  */
                /* JADX WARN: Removed duplicated region for block: B:29:0x0182  */
                /* JADX WARN: Removed duplicated region for block: B:32:0x018e  */
                /* JADX WARN: Removed duplicated region for block: B:40:0x0283  */
                /* JADX WARN: Removed duplicated region for block: B:43:0x028f  */
                /* JADX WARN: Removed duplicated region for block: B:46:0x02c6  */
                /* JADX WARN: Removed duplicated region for block: B:51:0x0367  */
                /* JADX WARN: Removed duplicated region for block: B:56:0x03a0  */
                /* JADX WARN: Removed duplicated region for block: B:61:0x042c  */
                /* JADX WARN: Removed duplicated region for block: B:66:0x047e  */
                /* JADX WARN: Removed duplicated region for block: B:71:0x04ec  */
                /* JADX WARN: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:79:0x02dc A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:80:0x0295  */
                /* JADX WARN: Removed duplicated region for block: B:83:0x0192  */
                /* JADX WARN: Removed duplicated region for block: B:84:0x00d6  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope r71, androidx.compose.runtime.Composer r72, int r73) {
                    /*
                        Method dump skipped, instructions count: 1272
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.DrawerKt$ModalDrawer$1.invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, 3072, 6);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier2;
            drawerState5 = drawerState4;
            gesturesEnabled3 = gesturesEnabled2;
            scrimColor3 = scrimColor2;
            drawerShape4 = drawerShape3;
            drawerBackgroundColor4 = drawerBackgroundColor3;
            drawerContentColor4 = drawerContentColor3;
            drawerElevation3 = drawerElevation2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$2
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

                public final void invoke(Composer composer, int i11) {
                    DrawerKt.m1530ModalDrawerGs3lGvM(function3, modifier4, drawerState5, gesturesEnabled3, drawerShape4, drawerElevation3, drawerBackgroundColor4, drawerContentColor4, scrimColor3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: BottomDrawer-Gs3lGvM, reason: not valid java name */
    public static final void m1528BottomDrawerGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, BottomDrawerState drawerState, boolean gesturesEnabled, Shape drawerShape, float drawerElevation, long drawerBackgroundColor, long drawerContentColor, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32;
        BottomDrawerState bottomDrawerState;
        boolean z;
        Shape shape;
        float f;
        long j;
        int i2;
        Modifier modifier2;
        int $dirty;
        BottomDrawerState drawerState2;
        Shape drawerShape2;
        long drawerBackgroundColor2;
        long drawerContentColor2;
        final long scrimColor2;
        final BottomDrawerState drawerState3;
        final boolean gesturesEnabled2;
        final Shape drawerShape3;
        final float drawerElevation2;
        final long drawerBackgroundColor3;
        final long drawerContentColor3;
        Modifier modifier3;
        int $dirty2;
        Object value$iv$iv;
        Composer $composer2;
        final Modifier modifier4;
        final boolean gesturesEnabled3;
        final BottomDrawerState drawerState4;
        final long scrimColor3;
        final Shape drawerShape4;
        final long drawerBackgroundColor4;
        final long drawerContentColor4;
        final float drawerElevation3;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(625649286);
        ComposerKt.sourceInformation($composer3, "C(BottomDrawer)P(2,8,6,7,5,4:c#ui.unit.Dp,1:c#ui.graphics.Color,3:c#ui.graphics.Color,9:c#ui.graphics.Color)625@23702L33,627@23814L5,629@23923L15,630@23972L38,631@24051L10,634@24119L24,635@24191L4601,635@24148L4644:Drawer.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function32 = function3;
        } else if (($changed & 6) == 0) {
            function32 = function3;
            $dirty3 |= $composer3.changedInstance(function32) ? 4 : 2;
        } else {
            function32 = function3;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                bottomDrawerState = drawerState;
                if ($composer3.changedInstance(bottomDrawerState)) {
                    i7 = 256;
                    $dirty3 |= i7;
                }
            } else {
                bottomDrawerState = drawerState;
            }
            i7 = 128;
            $dirty3 |= i7;
        } else {
            bottomDrawerState = drawerState;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty3 |= 3072;
            z = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            z = gesturesEnabled;
            $dirty3 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = gesturesEnabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape = drawerShape;
                if ($composer3.changed(shape)) {
                    i6 = 16384;
                    $dirty3 |= i6;
                }
            } else {
                shape = drawerShape;
            }
            i6 = 8192;
            $dirty3 |= i6;
        } else {
            shape = drawerShape;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = drawerElevation;
        } else if ((196608 & $changed) == 0) {
            f = drawerElevation;
            $dirty3 |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = drawerElevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                j = drawerBackgroundColor;
                if ($composer3.changed(j)) {
                    i5 = 1048576;
                    $dirty3 |= i5;
                }
            } else {
                j = drawerBackgroundColor;
            }
            i5 = 524288;
            $dirty3 |= i5;
        } else {
            j = drawerBackgroundColor;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                i2 = i8;
                if ($composer3.changed(drawerContentColor)) {
                    i4 = 8388608;
                    $dirty3 |= i4;
                }
            } else {
                i2 = i8;
            }
            i4 = 4194304;
            $dirty3 |= i4;
        } else {
            i2 = i8;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0 && $composer3.changed(scrimColor)) {
                i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty3 |= i3;
            }
            i3 = 33554432;
            $dirty3 |= i3;
        }
        if ((i & 512) != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        }
        if (($dirty3 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier;
            $composer2 = $composer3;
            drawerState4 = bottomDrawerState;
            gesturesEnabled3 = z;
            drawerContentColor4 = drawerContentColor;
            drawerBackgroundColor4 = j;
            drawerShape4 = shape;
            drawerElevation3 = f;
            scrimColor3 = scrimColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    int $dirty4 = $dirty3;
                    modifier2 = modifier5;
                    drawerState2 = rememberBottomDrawerState(BottomDrawerValue.Closed, null, null, $composer3, 6, 6);
                    $dirty = $dirty4 & (-897);
                } else {
                    modifier2 = modifier5;
                    $dirty = $dirty3;
                    drawerState2 = bottomDrawerState;
                }
                boolean gesturesEnabled4 = i9 != 0 ? true : z;
                if ((i & 16) != 0) {
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                float drawerElevation4 = i10 != 0 ? DrawerDefaults.INSTANCE.m1527getElevationD9Ej5fM() : f;
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = DrawerDefaults.INSTANCE.getBackgroundColor($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1496contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    drawerState3 = drawerState2;
                    gesturesEnabled2 = gesturesEnabled4;
                    drawerShape3 = drawerShape2;
                    drawerElevation2 = drawerElevation4;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty3 = $dirty & (-234881025);
                    modifier3 = modifier2;
                } else {
                    scrimColor2 = scrimColor;
                    drawerState3 = drawerState2;
                    gesturesEnabled2 = gesturesEnabled4;
                    drawerShape3 = drawerShape2;
                    drawerElevation2 = drawerElevation4;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    $dirty3 = $dirty;
                    modifier3 = modifier2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                if ((i & 256) != 0) {
                    drawerContentColor3 = drawerContentColor;
                    scrimColor2 = scrimColor;
                    $dirty3 &= -234881025;
                    drawerBackgroundColor3 = j;
                    drawerState3 = bottomDrawerState;
                    gesturesEnabled2 = z;
                    drawerShape3 = shape;
                    drawerElevation2 = f;
                    modifier3 = modifier;
                } else {
                    modifier3 = modifier;
                    drawerContentColor3 = drawerContentColor;
                    scrimColor2 = scrimColor;
                    drawerBackgroundColor3 = j;
                    drawerState3 = bottomDrawerState;
                    gesturesEnabled2 = z;
                    drawerShape3 = shape;
                    drawerElevation2 = f;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(625649286, $dirty3, -1, "androidx.compose.material.BottomDrawer (Drawer.kt:633)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                $dirty2 = $dirty3;
                value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                $dirty2 = $dirty3;
                value$iv$iv = it$iv$iv;
            }
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33 = function32;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(1220102512, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
                    invoke(boxWithConstraintsScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:39:0x0250  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x0280  */
                /* JADX WARN: Removed duplicated region for block: B:47:0x02b9  */
                /* JADX WARN: Removed duplicated region for block: B:52:0x02f6  */
                /* JADX WARN: Removed duplicated region for block: B:57:0x033c  */
                /* JADX WARN: Removed duplicated region for block: B:62:0x03a4  */
                /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:67:0x0303 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:69:0x02c6 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:70:0x0283  */
                /* JADX WARN: Removed duplicated region for block: B:72:0x025e  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope r55, androidx.compose.runtime.Composer r56, int r57) {
                    /*
                        Method dump skipped, instructions count: 936
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.DrawerKt$BottomDrawer$1.invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, 3072, 6);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            gesturesEnabled3 = gesturesEnabled2;
            drawerState4 = drawerState3;
            scrimColor3 = scrimColor2;
            drawerShape4 = drawerShape3;
            drawerBackgroundColor4 = drawerBackgroundColor3;
            drawerContentColor4 = drawerContentColor3;
            drawerElevation3 = drawerElevation2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2
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

                public final void invoke(Composer composer, int i11) {
                    DrawerKt.m1528BottomDrawerGs3lGvM(function3, modifier4, drawerState4, gesturesEnabled3, drawerShape4, drawerElevation3, drawerBackgroundColor4, drawerContentColor4, scrimColor3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateFraction(float a, float b, float pos) {
        float $this$fastCoerceIn$iv = (pos - a) / (b - a);
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: BottomDrawerScrim-3J-VO9M, reason: not valid java name */
    public static final void m1529BottomDrawerScrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Object value$iv;
        DrawerKt$BottomDrawerScrim$dismissModifier$1$1 value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(-513067266);
        ComposerKt.sourceInformation($composer2, "C(BottomDrawerScrim)P(0:c#ui.graphics.Color):Drawer.kt#jmzs0o");
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
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-513067266, $dirty, -1, "androidx.compose.material.BottomDrawerScrim (Drawer.kt:793)");
            }
            if ((color != 16 ? 1 : 0) != 0) {
                $composer2.startReplaceGroup(1552753222);
                ComposerKt.sourceInformation($composer2, "795@30128L121,799@30276L30,817@30829L62,813@30720L171");
                int $dirty2 = $dirty;
                final State alpha$delegate = AnimateAsStateKt.animateFloatAsState(visible ? 1065353216 : 0, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                final String closeDrawer = Strings_androidKt.m1659getString4foXLRw(Strings.INSTANCE.m1652getCloseDrawerUdPEhr4(), $composer2, 6);
                if (visible) {
                    $composer2.startReplaceGroup(1552981692);
                    ComposerKt.sourceInformation($composer2, "802@30414L73,805@30540L122");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1612470003, "CC(remember):Drawer.kt#9igjgp");
                    boolean invalid$iv = ($dirty2 & 112) == 32;
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new DrawerKt$BottomDrawerScrim$dismissModifier$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1612465922, "CC(remember):Drawer.kt#9igjgp");
                    boolean invalid$iv2 = $composer2.changed(closeDrawer) | (($dirty2 & 112) == 32);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$dismissModifier$2$1
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
                                SemanticsPropertiesKt.setContentDescription($this$semantics, closeDrawer);
                                final Function0<Unit> function02 = function0;
                                SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$dismissModifier$2$1.1
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
                    dismissModifier = SemanticsModifierKt.semantics(pointerInput, true, (Function1) value$iv3);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(1553298078);
                    $composer2.endReplaceGroup();
                    dismissModifier = Modifier.INSTANCE;
                }
                Modifier then = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModifier);
                ComposerKt.sourceInformationMarkerStart($composer2, -1612456734, "CC(remember):Drawer.kt#9igjgp");
                boolean invalid$iv3 = $composer2.changed(alpha$delegate) | (($dirty2 & 14) == 4);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$1$1
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
                            float BottomDrawerScrim_3J_VO9M$lambda$2;
                            long j = color;
                            BottomDrawerScrim_3J_VO9M$lambda$2 = DrawerKt.BottomDrawerScrim_3J_VO9M$lambda$2(alpha$delegate);
                            DrawScope.m4707drawRectnJ9OG0$default($this$Canvas, j, 0L, 0L, BottomDrawerScrim_3J_VO9M$lambda$2, null, null, 0, 118, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(then, (Function1) value$iv, $composer2, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1553514334);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$2
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
                    DrawerKt.m1529BottomDrawerScrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float BottomDrawerScrim_3J_VO9M$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim-Bx497Mc, reason: not valid java name */
    public static final void m1531ScrimBx497Mc(final boolean open, final Function0<Unit> function0, final Function0<Float> function02, final long color, Composer $composer, final int $changed) {
        Modifier.Companion dismissDrawer;
        Object value$iv;
        DrawerKt$Scrim$dismissDrawer$1$1 value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(1983403750);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(3,2,1,0:c#ui.graphics.Color)830@31046L30,846@31490L51,842@31399L142:Drawer.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(open) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function02) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(color) ? 2048 : 1024;
        }
        if (($dirty & 1171) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1983403750, $dirty, -1, "androidx.compose.material.Scrim (Drawer.kt:829)");
            }
            final String closeDrawer = Strings_androidKt.m1659getString4foXLRw(Strings.INSTANCE.m1652getCloseDrawerUdPEhr4(), $composer2, 6);
            if (open) {
                $composer2.startReplaceGroup(487729414);
                ComposerKt.sourceInformation($composer2, "833@31165L35,834@31249L108");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 1262660707, "CC(remember):Drawer.kt#9igjgp");
                boolean invalid$iv = ($dirty & 112) == 32;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new DrawerKt$Scrim$dismissDrawer$1$1(function0, null);
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1262663468, "CC(remember):Drawer.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changed(closeDrawer) | (($dirty & 112) == 32);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$Scrim$dismissDrawer$2$1
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
                            SemanticsPropertiesKt.setContentDescription($this$semantics, closeDrawer);
                            final Function0<Unit> function03 = function0;
                            SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$Scrim$dismissDrawer$2$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    function03.invoke();
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
                dismissDrawer = SemanticsModifierKt.semantics(pointerInput, true, (Function1) value$iv3);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(487978282);
                $composer2.endReplaceGroup();
                dismissDrawer = Modifier.INSTANCE;
            }
            Modifier then = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissDrawer);
            ComposerKt.sourceInformationMarkerStart($composer2, 1262671123, "CC(remember):Drawer.kt#9igjgp");
            boolean invalid$iv3 = (($dirty & 7168) == 2048) | (($dirty & 896) == 256);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.DrawerKt$Scrim$1$1
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
                        DrawScope.m4707drawRectnJ9OG0$default($this$Canvas, color, 0L, 0L, function02.invoke().floatValue(), null, null, 0, 118, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(then, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$Scrim$2
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
                    DrawerKt.m1531ScrimBx497Mc(open, function0, function02, color, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState) {
        return new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1(anchoredDraggableState);
    }
}
