package androidx.compose.material3;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: ModalBottomSheet.android.kt */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¹\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001aJ\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012H\u0001¢\u0006\u0002\u0010\"\u001a\f\u0010#\u001a\u00020$*\u00020%H\u0000\u001a\u0014\u0010&\u001a\u00020$*\u00020'2\u0006\u0010(\u001a\u00020$H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)²\u0006\u0015\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012X\u008a\u0084\u0002"}, d2 = {"ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "sheetMaxWidth", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-dYc4hso", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/ModalBottomSheetProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ModalBottomSheetDialog", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/ModalBottomSheetProperties;Landroidx/compose/animation/core/Animatable;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isFlagSecureEnabled", "", "Landroid/view/View;", "shouldApplySecureFlag", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isSecureFlagSetOnParent", "material3_release", "currentContent"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ModalBottomSheet_androidKt {

    /* compiled from: ModalBottomSheet.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SecureFlagPolicy.values().length];
            try {
                iArr[SecureFlagPolicy.SecureOff.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SecureFlagPolicy.SecureOn.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SecureFlagPolicy.Inherit.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use constructor with contentWindowInsets parameter.", replaceWith = @ReplaceWith(expression = "ModalBottomSheet(onDismissRequest,modifier,sheetState,sheetMaxWidth,shape,containerColor,contentColor,tonalElevation,scrimColor,dragHandle,{ windowInsets },properties,content,)", imports = {}))
    /* renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    public static final /* synthetic */ void m2252ModalBottomSheetdYc4hso(final Function0 onDismissRequest, Modifier modifier, SheetState sheetState, float sheetMaxWidth, Shape shape, long containerColor, long contentColor, float tonalElevation, long scrimColor, Function2 dragHandle, WindowInsets windowInsets, ModalBottomSheetProperties properties, final Function3 content, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        SheetState sheetState2;
        float f;
        Shape shape2;
        long j;
        int $dirty;
        int $dirty1;
        int i2;
        int $dirty12;
        int i3;
        Function3 function3;
        int i4;
        Shape shape3;
        long containerColor2;
        long contentColor2;
        long scrimColor2;
        float sheetMaxWidth2;
        WindowInsets windowInsets2;
        float sheetMaxWidth3;
        ModalBottomSheetProperties properties2;
        final WindowInsets windowInsets3;
        int $dirty2;
        float tonalElevation2;
        int $dirty13;
        Shape shape4;
        SheetState sheetState3;
        long scrimColor3;
        Function2 dragHandle2;
        Composer $composer2;
        final Modifier modifier3;
        final SheetState sheetState4;
        final float sheetMaxWidth4;
        final Shape shape5;
        final long containerColor3;
        final long contentColor3;
        final float tonalElevation3;
        final long contentColor4;
        final Function2 dragHandle3;
        final ModalBottomSheetProperties properties3;
        final WindowInsets windowInsets4;
        int i5;
        int i6;
        int $dirty3;
        int i7;
        int i8;
        int i9;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(944867294);
        ComposerKt.sourceInformation($composer3, "C(ModalBottomSheet)P(5,4,10,9:c#ui.unit.Dp,8,0:c#ui.graphics.Color,2:c#ui.graphics.Color,11:c#ui.unit.Dp,7:c#ui.graphics.Color,3,12,6)235@10240L31,237@10371L13,238@10434L14,239@10476L31,241@10584L10,243@10731L12,247@10884L485:ModalBottomSheet.android.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(onDismissRequest) ? 4 : 2;
        }
        int i11 = i & 2;
        if (i11 != 0) {
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
                sheetState2 = sheetState;
                if ($composer3.changed(sheetState2)) {
                    i10 = 256;
                    $dirty4 |= i10;
                }
            } else {
                sheetState2 = sheetState;
            }
            i10 = 128;
            $dirty4 |= i10;
        } else {
            sheetState2 = sheetState;
        }
        int i12 = i & 8;
        if (i12 != 0) {
            $dirty4 |= 3072;
            f = sheetMaxWidth;
        } else if (($changed & 3072) == 0) {
            f = sheetMaxWidth;
            $dirty4 |= $composer3.changed(f) ? 2048 : 1024;
        } else {
            f = sheetMaxWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i9 = 16384;
                    $dirty4 |= i9;
                }
            } else {
                shape2 = shape;
            }
            i9 = 8192;
            $dirty4 |= i9;
        } else {
            shape2 = shape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                j = containerColor;
                if ($composer3.changed(j)) {
                    i8 = 131072;
                    $dirty4 |= i8;
                }
            } else {
                j = containerColor;
            }
            i8 = 65536;
            $dirty4 |= i8;
        } else {
            j = containerColor;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                if ($composer3.changed(contentColor)) {
                    i7 = 1048576;
                    $dirty = $dirty3 | i7;
                }
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            i7 = 524288;
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int i13 = i & 128;
        if (i13 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(tonalElevation) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(scrimColor)) {
                i6 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty |= i6;
            }
            i6 = 33554432;
            $dirty |= i6;
        }
        int i14 = i & 512;
        if (i14 != 0) {
            $dirty |= 805306368;
            i2 = i14;
        } else if (($changed & 805306368) == 0) {
            i2 = i14;
            $dirty |= $composer3.changedInstance(dragHandle) ? 536870912 : 268435456;
        } else {
            i2 = i14;
        }
        int $dirty5 = $dirty;
        if (($changed1 & 6) == 0) {
            if ((i & 1024) == 0 && $composer3.changed(windowInsets)) {
                i5 = 4;
                $dirty12 = $dirty1 | i5;
            }
            i5 = 2;
            $dirty12 = $dirty1 | i5;
        } else {
            $dirty12 = $dirty1;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty12 |= 48;
            i3 = i15;
        } else if (($changed1 & 48) == 0) {
            i3 = i15;
            $dirty12 |= $composer3.changed(properties) ? 32 : 16;
        } else {
            i3 = i15;
        }
        int $dirty14 = $dirty12;
        if ((i & 4096) != 0) {
            $dirty14 |= 384;
            function3 = content;
        } else if (($changed1 & 384) == 0) {
            function3 = content;
            $dirty14 |= $composer3.changedInstance(function3) ? 256 : 128;
        } else {
            function3 = content;
        }
        if ((306783379 & $dirty5) == 306783378 && ($dirty14 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            tonalElevation3 = tonalElevation;
            properties3 = properties;
            $composer2 = $composer3;
            sheetMaxWidth4 = f;
            shape5 = shape2;
            containerColor3 = j;
            modifier3 = modifier2;
            sheetState4 = sheetState2;
            contentColor3 = contentColor;
            contentColor4 = scrimColor;
            dragHandle3 = dragHandle;
            windowInsets4 = windowInsets;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    i4 = -3670017;
                    $dirty5 &= -897;
                    sheetState2 = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, $composer3, 0, 3);
                } else {
                    i4 = -3670017;
                }
                float sheetMaxWidth5 = i12 != 0 ? BottomSheetDefaults.INSTANCE.m1797getSheetMaxWidthD9Ej5fM() : f;
                if ((i & 16) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                    $dirty5 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty5 &= -458753;
                } else {
                    containerColor2 = j;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer3, ($dirty5 >> 15) & 14);
                    $dirty5 &= i4;
                } else {
                    contentColor2 = contentColor;
                }
                float tonalElevation4 = i13 != 0 ? Dp.m6626constructorimpl(0) : tonalElevation;
                if ((i & 256) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty5 &= -234881025;
                } else {
                    scrimColor2 = scrimColor;
                }
                Function2 dragHandle4 = i2 != 0 ? ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1976getLambda1$material3_release() : dragHandle;
                int $dirty6 = $dirty5;
                if ((i & 1024) != 0) {
                    sheetMaxWidth2 = sheetMaxWidth5;
                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty14 &= -15;
                } else {
                    sheetMaxWidth2 = sheetMaxWidth5;
                    windowInsets2 = windowInsets;
                }
                if (i3 != 0) {
                    sheetMaxWidth3 = sheetMaxWidth2;
                    properties2 = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    tonalElevation2 = tonalElevation4;
                    windowInsets3 = windowInsets2;
                    $dirty2 = $dirty14;
                    shape4 = shape3;
                    sheetState3 = sheetState2;
                    scrimColor3 = scrimColor2;
                    dragHandle2 = dragHandle4;
                    $dirty13 = $dirty6;
                } else {
                    sheetMaxWidth3 = sheetMaxWidth2;
                    properties2 = properties;
                    windowInsets3 = windowInsets2;
                    $dirty2 = $dirty14;
                    tonalElevation2 = tonalElevation4;
                    $dirty13 = $dirty6;
                    shape4 = shape3;
                    sheetState3 = sheetState2;
                    scrimColor3 = scrimColor2;
                    dragHandle2 = dragHandle4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                }
                if ((i & 1024) != 0) {
                    int i16 = $dirty14 & (-15);
                    tonalElevation2 = tonalElevation;
                    dragHandle2 = dragHandle;
                    properties2 = properties;
                    $dirty13 = $dirty5;
                    $dirty2 = i16;
                    sheetMaxWidth3 = f;
                    shape4 = shape2;
                    containerColor2 = j;
                    sheetState3 = sheetState2;
                    contentColor2 = contentColor;
                    scrimColor3 = scrimColor;
                    windowInsets3 = windowInsets;
                } else {
                    int i17 = $dirty14;
                    $dirty13 = $dirty5;
                    $dirty2 = i17;
                    tonalElevation2 = tonalElevation;
                    dragHandle2 = dragHandle;
                    windowInsets3 = windowInsets;
                    properties2 = properties;
                    sheetMaxWidth3 = f;
                    shape4 = shape2;
                    containerColor2 = j;
                    sheetState3 = sheetState2;
                    contentColor2 = contentColor;
                    scrimColor3 = scrimColor;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $composer2 = $composer3;
                ComposerKt.traceEventStart(944867294, $dirty13, $dirty2, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:247)");
            } else {
                $composer2 = $composer3;
            }
            Function3 function32 = function3;
            Modifier modifier4 = modifier2;
            float tonalElevation5 = tonalElevation2;
            ModalBottomSheetKt.m2246ModalBottomSheetdYc4hso(onDismissRequest, modifier4, sheetState3, sheetMaxWidth3, shape4, containerColor2, contentColor2, tonalElevation5, scrimColor3, dragHandle2, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer, Integer num) {
                    return invoke(composer, num.intValue());
                }

                public final WindowInsets invoke(Composer $composer4, int $changed2) {
                    $composer4.startReplaceGroup(-2061903609);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2061903609, $changed2, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:258)");
                    }
                    WindowInsets windowInsets5 = WindowInsets.this;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer4.endReplaceGroup();
                    return windowInsets5;
                }
            }, properties2, function32, $composer2, ($dirty13 & 14) | ($dirty13 & 112) | ($dirty13 & 896) | ($dirty13 & 7168) | (57344 & $dirty13) | (458752 & $dirty13) | (3670016 & $dirty13) | (29360128 & $dirty13) | (234881024 & $dirty13) | (1879048192 & $dirty13), ($dirty2 & 112) | ($dirty2 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            sheetState4 = sheetState3;
            sheetMaxWidth4 = sheetMaxWidth3;
            shape5 = shape4;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            tonalElevation3 = tonalElevation5;
            contentColor4 = scrimColor3;
            dragHandle3 = dragHandle2;
            properties3 = properties2;
            windowInsets4 = windowInsets3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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

                public final void invoke(Composer composer, int i18) {
                    ModalBottomSheet_androidKt.m2252ModalBottomSheetdYc4hso(onDismissRequest, modifier3, sheetState4, sheetMaxWidth4, shape5, containerColor3, contentColor3, tonalElevation3, contentColor4, dragHandle3, windowInsets4, properties3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0229  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ModalBottomSheetDialog(kotlin.jvm.functions.Function0<kotlin.Unit> r28, androidx.compose.material3.ModalBottomSheetProperties r29, final androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r30, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, androidx.compose.runtime.Composer r32, final int r33) {
        /*
            Method dump skipped, instructions count: 653
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheetDialog(kotlin.jvm.functions.Function0, androidx.compose.material3.ModalBottomSheetProperties, androidx.compose.animation.core.Animatable, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> ModalBottomSheetDialog$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    public static final boolean isFlagSecureEnabled(View $this$isFlagSecureEnabled) {
        ViewGroup.LayoutParams layoutParams = $this$isFlagSecureEnabled.getRootView().getLayoutParams();
        WindowManager.LayoutParams windowParams = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (windowParams == null || (windowParams.flags & 8192) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldApplySecureFlag(SecureFlagPolicy $this$shouldApplySecureFlag, boolean isSecureFlagSetOnParent) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$shouldApplySecureFlag.ordinal()]) {
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return isSecureFlagSetOnParent;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
