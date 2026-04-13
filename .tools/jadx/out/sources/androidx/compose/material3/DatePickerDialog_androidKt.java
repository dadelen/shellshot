package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DatePickerDialog.android.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u009a\u0001\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00070\u0017¢\u0006\u0002\b\u000b¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"DialogButtonsCrossAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "DialogButtonsMainAxisSpacing", "DialogButtonsPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "dismissButton", "shape", "Landroidx/compose/ui/graphics/Shape;", "tonalElevation", "colors", "Landroidx/compose/material3/DatePickerColors;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DatePickerDialog-GmEhDVc", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class DatePickerDialog_androidKt {
    private static final PaddingValues DialogButtonsPadding = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, 0.0f, Dp.m6626constructorimpl(6), Dp.m6626constructorimpl(8), 3, null);
    private static final float DialogButtonsMainAxisSpacing = Dp.m6626constructorimpl(8);
    private static final float DialogButtonsCrossAxisSpacing = Dp.m6626constructorimpl(12);

    /* renamed from: DatePickerDialog-GmEhDVc, reason: not valid java name */
    public static final void m2029DatePickerDialogGmEhDVc(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Shape shape, float tonalElevation, DatePickerColors colors, DialogProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        Function2 dismissButton;
        Shape shape2;
        float tonalElevation2;
        final DatePickerColors colors2;
        int $dirty;
        DialogProperties properties2;
        Modifier modifier3;
        final Function2 dismissButton2;
        final float tonalElevation3;
        int $dirty2;
        final Shape shape3;
        Composer $composer2;
        final Shape shape4;
        final float tonalElevation4;
        final Function2 dismissButton3;
        final DialogProperties properties3;
        final Modifier modifier4;
        final DatePickerColors colors3;
        int i2;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-36517340);
        ComposerKt.sourceInformation($composer3, "C(DatePickerDialog)P(5,1,4,3,7,8:c#ui.unit.Dp!1,6)71@3518L5,73@3635L8,81@3937L1615,77@3787L1765:DatePickerDialog.android.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            dismissButton = function22;
        } else if (($changed & 3072) == 0) {
            dismissButton = function22;
            $dirty3 |= $composer3.changedInstance(dismissButton) ? 2048 : 1024;
        } else {
            dismissButton = function22;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i3 = 16384;
                    $dirty3 |= i3;
                }
            } else {
                shape2 = shape;
            }
            i3 = 8192;
            $dirty3 |= i3;
        } else {
            shape2 = shape;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tonalElevation2 = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty3 |= $composer3.changed(tonalElevation2) ? 131072 : 65536;
        } else {
            tonalElevation2 = tonalElevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i2 = 1048576;
                    $dirty3 |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 524288;
            $dirty3 |= i2;
        } else {
            colors2 = colors;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(properties) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            dismissButton3 = dismissButton;
            shape4 = shape2;
            tonalElevation4 = tonalElevation2;
            properties3 = properties;
            colors3 = colors2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    dismissButton = null;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty4 & (-57345);
                    shape2 = DatePickerDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty4;
                }
                if (i6 != 0) {
                    tonalElevation2 = DatePickerDefaults.INSTANCE.m2028getTonalElevationD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    colors2 = DatePickerDefaults.INSTANCE.colors($composer3, 6);
                }
                if (i7 != 0) {
                    modifier3 = modifier2;
                    tonalElevation3 = tonalElevation2;
                    properties2 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    dismissButton2 = dismissButton;
                    shape3 = shape2;
                    $dirty2 = $dirty;
                } else {
                    properties2 = properties;
                    modifier3 = modifier2;
                    dismissButton2 = dismissButton;
                    tonalElevation3 = tonalElevation2;
                    $dirty2 = $dirty;
                    shape3 = shape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 16) != 0 ? $dirty4 & (-57345) : $dirty4;
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                properties2 = properties;
                dismissButton2 = dismissButton;
                tonalElevation3 = tonalElevation2;
                $dirty2 = $dirty5;
                modifier3 = modifier2;
                shape3 = shape2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-36517340, $dirty2, -1, "androidx.compose.material3.DatePickerDialog (DatePickerDialog.android.kt:76)");
            }
            AlertDialogKt.BasicAlertDialog(function02, SizeKt.wrapContentHeight$default(modifier3, null, false, 3, null), properties2, ComposableLambdaKt.rememberComposableLambda(-10625622, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C89@4257L1289,82@3947L1599:DatePickerDialog.android.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-10625622, $changed2, -1, "androidx.compose.material3.DatePickerDialog.<anonymous> (DatePickerDialog.android.kt:82)");
                        }
                        Modifier m705heightInVpY3zN4$default = SizeKt.m705heightInVpY3zN4$default(SizeKt.m714requiredWidth3ABfNKs(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m3144getContainerWidthD9Ej5fM()), 0.0f, DatePickerModalTokens.INSTANCE.m3143getContainerHeightD9Ej5fM(), 1, null);
                        Shape shape5 = Shape.this;
                        long containerColor = colors2.getContainerColor();
                        float f = tonalElevation3;
                        final Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                        final Function2<Composer, Integer, Unit> function23 = dismissButton2;
                        final Function2<Composer, Integer, Unit> function24 = function2;
                        SurfaceKt.m2547SurfaceT9BRK9s(m705heightInVpY3zN4$default, shape5, containerColor, 0L, f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1706202235, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1.1
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

                            /* JADX WARN: Removed duplicated region for block: B:24:0x01b1  */
                            /* JADX WARN: Removed duplicated region for block: B:27:0x01bd  */
                            /* JADX WARN: Removed duplicated region for block: B:35:0x02e2  */
                            /* JADX WARN: Removed duplicated region for block: B:38:0x02ee  */
                            /* JADX WARN: Removed duplicated region for block: B:41:0x0325  */
                            /* JADX WARN: Removed duplicated region for block: B:46:0x03ea  */
                            /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
                            /* JADX WARN: Removed duplicated region for block: B:50:0x033b  */
                            /* JADX WARN: Removed duplicated region for block: B:51:0x02f4  */
                            /* JADX WARN: Removed duplicated region for block: B:54:0x01c3  */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final void invoke(androidx.compose.runtime.Composer r59, int r60) {
                                /*
                                    Method dump skipped, instructions count: 1006
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1.AnonymousClass1.invoke(androidx.compose.runtime.Composer, int):void");
                            }
                        }, $composer4, 54), $composer4, 12582918, LocationRequestCompat.QUALITY_LOW_POWER);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ($dirty2 & 14) | 3072 | (($dirty2 >> 15) & 896), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            tonalElevation4 = tonalElevation3;
            dismissButton3 = dismissButton2;
            properties3 = properties2;
            modifier4 = modifier3;
            colors3 = colors2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$2
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
                    DatePickerDialog_androidKt.m2029DatePickerDialogGmEhDVc(function0, function2, modifier4, dismissButton3, shape4, tonalElevation4, colors3, properties3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
