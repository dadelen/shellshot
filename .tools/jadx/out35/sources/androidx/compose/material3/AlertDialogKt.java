package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.DialogTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AlertDialog.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u001aB\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001a¦\u0001\u0010\u0019\u001a\u00020\u000f2\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020!H\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a5\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00012\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u0017H\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001aÇ\u0001\u0010.\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0013\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0001ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001aB\u00103\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0005\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\b\u0010\u0006\"\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"ButtonsCrossAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonsMainAxisSpacing", "DialogMaxWidth", "getDialogMaxWidth", "()F", "DialogMinWidth", "getDialogMinWidth", "DialogPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "IconPadding", "TextPadding", "TitlePadding", "AlertDialog", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "AlertDialogContent", "buttons", "icon", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "buttonContentColor", "iconContentColor", "titleContentColor", "textContentColor", "AlertDialogContent-4hvqGtA", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JFJJJJLandroidx/compose/runtime/Composer;III)V", "AlertDialogFlowRow", "mainAxisSpacing", "crossAxisSpacing", "AlertDialogFlowRow-ixp7dh8", "(FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "AlertDialogImpl", "confirmButton", "dismissButton", "AlertDialogImpl-wrnwzgE", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJJJFLandroidx/compose/ui/window/DialogProperties;Landroidx/compose/runtime/Composer;II)V", "BasicAlertDialog", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AlertDialogKt {
    private static final float DialogMinWidth = Dp.m6626constructorimpl(280);
    private static final float DialogMaxWidth = Dp.m6626constructorimpl(560);
    private static final float ButtonsMainAxisSpacing = Dp.m6626constructorimpl(8);
    private static final float ButtonsCrossAxisSpacing = Dp.m6626constructorimpl(12);
    private static final PaddingValues DialogPadding = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(24));
    private static final PaddingValues IconPadding = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m6626constructorimpl(16), 7, null);
    private static final PaddingValues TitlePadding = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m6626constructorimpl(16), 7, null);
    private static final PaddingValues TextPadding = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m6626constructorimpl(24), 7, null);

    public static final void BasicAlertDialog(final Function0<Unit> function0, Modifier modifier, DialogProperties properties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        DialogProperties dialogProperties;
        final Modifier modifier3;
        final DialogProperties properties2;
        Composer $composer2 = $composer.startRestartGroup(-1922902937);
        ComposerKt.sourceInformation($composer2, "C(BasicAlertDialog)P(2,1,3)149@6931L384,146@6839L476:AlertDialog.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            dialogProperties = properties;
        } else if (($changed & 384) == 0) {
            dialogProperties = properties;
            $dirty |= $composer2.changed(dialogProperties) ? 256 : 128;
        } else {
            dialogProperties = properties;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            properties2 = dialogProperties;
        } else {
            final Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            DialogProperties properties3 = i3 != 0 ? new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null) : dialogProperties;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1922902937, $dirty2, -1, "androidx.compose.material3.BasicAlertDialog (AlertDialog.kt:145)");
            }
            AndroidDialog_androidKt.Dialog(function02, properties3, ComposableLambdaKt.rememberComposableLambda(905289008, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$BasicAlertDialog$1
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

                /* JADX WARN: Removed duplicated region for block: B:29:0x01af  */
                /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 435
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AlertDialogKt$BasicAlertDialog$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 384 | (($dirty2 >> 3) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            properties2 = properties3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$BasicAlertDialog$2
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
                    AlertDialogKt.BasicAlertDialog(function0, modifier3, properties2, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(message = "Use BasicAlertDialog instead", replaceWith = @ReplaceWith(expression = "BasicAlertDialog(onDismissRequest, modifier, properties, content)", imports = {}))
    public static final void AlertDialog(final Function0<Unit> function0, Modifier modifier, DialogProperties properties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        DialogProperties dialogProperties;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Modifier.Companion modifier3;
        DialogProperties properties2;
        final Modifier modifier4;
        final DialogProperties properties3;
        Composer $composer2 = $composer.startRestartGroup(325249497);
        ComposerKt.sourceInformation($composer2, "C(AlertDialog)P(2,1,3)201@8986L65:AlertDialog.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            dialogProperties = properties;
        } else if (($changed & 384) == 0) {
            dialogProperties = properties;
            $dirty |= $composer2.changed(dialogProperties) ? 256 : 128;
        } else {
            dialogProperties = properties;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            function22 = function2;
        } else if (($changed & 3072) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 2048 : 1024;
        } else {
            function22 = function2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            properties3 = dialogProperties;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i3 == 0) {
                properties2 = dialogProperties;
            } else {
                properties2 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(325249497, $dirty2, -1, "androidx.compose.material3.AlertDialog (AlertDialog.kt:201)");
            }
            Modifier modifier5 = modifier3;
            BasicAlertDialog(function02, modifier5, properties2, function22, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            properties3 = properties2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialog$1
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
                    AlertDialogKt.AlertDialog(function0, modifier4, properties3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: AlertDialogImpl-wrnwzgE, reason: not valid java name */
    public static final void m1763AlertDialogImplwrnwzgE(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, final Shape shape, final long containerColor, final long iconContentColor, final long titleContentColor, final long textContentColor, final float tonalElevation, final DialogProperties properties, Composer $composer, final int $changed, final int $changed1) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function28;
        Function2<? super Composer, ? super Integer, Unit> function29;
        Shape shape2;
        long j;
        int $dirty;
        long j2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-919826268);
        ComposerKt.sourceInformation($composer3, "C(AlertDialogImpl)P(6!1,5,2,3,11,9,8,1:c#ui.graphics.Color,4:c#ui.graphics.Color,12:c#ui.graphics.Color,10:c#ui.graphics.Color,13:c#ui.unit.Dp)251@10652L1109,247@10522L1239:AlertDialog.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            function26 = function22;
            $dirty2 |= $composer3.changedInstance(function26) ? 2048 : 1024;
        } else {
            function26 = function22;
        }
        if (($changed & 24576) == 0) {
            function27 = function23;
            $dirty2 |= $composer3.changedInstance(function27) ? 16384 : 8192;
        } else {
            function27 = function23;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function28 = function24;
            $dirty2 |= $composer3.changedInstance(function28) ? 131072 : 65536;
        } else {
            function28 = function24;
        }
        if (($changed & 1572864) == 0) {
            function29 = function25;
            $dirty2 |= $composer3.changedInstance(function29) ? 1048576 : 524288;
        } else {
            function29 = function25;
        }
        if (($changed & 12582912) == 0) {
            shape2 = shape;
            $dirty2 |= $composer3.changed(shape2) ? 8388608 : 4194304;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            j = containerColor;
            $dirty2 |= $composer3.changed(j) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            j = containerColor;
        }
        if (($changed & 805306368) == 0) {
            $dirty = $dirty2 | ($composer3.changed(iconContentColor) ? 536870912 : 268435456);
        } else {
            $dirty = $dirty2;
        }
        int $dirty3 = $dirty;
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(titleContentColor) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            j2 = textContentColor;
            $dirty1 |= $composer3.changed(j2) ? 32 : 16;
        } else {
            j2 = textContentColor;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(tonalElevation) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(properties) ? 2048 : 1024;
        }
        if (($dirty3 & 306783379) != 306783378 || ($dirty1 & 1171) != 1170 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-919826268, $dirty3, $dirty1, "androidx.compose.material3.AlertDialogImpl (AlertDialog.kt:247)");
            }
            final long j3 = j2;
            final Shape shape3 = shape2;
            final long j4 = j;
            final Function2<? super Composer, ? super Integer, Unit> function210 = function26;
            final Function2<? super Composer, ? super Integer, Unit> function211 = function27;
            final Function2<? super Composer, ? super Integer, Unit> function212 = function28;
            final Function2<? super Composer, ? super Integer, Unit> function213 = function29;
            BasicAlertDialog(function02, modifier, properties, ComposableLambdaKt.rememberComposableLambda(-1852840226, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogImpl$1
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
                    ComposerKt.sourceInformation($composer4, "C253@10704L294,272@11590L5,252@10662L1093:AlertDialog.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1852840226, $changed2, -1, "androidx.compose.material3.AlertDialogImpl.<anonymous> (AlertDialog.kt:252)");
                        }
                        final Function2<Composer, Integer, Unit> function214 = function210;
                        final Function2<Composer, Integer, Unit> function215 = function2;
                        AlertDialogKt.m1761AlertDialogContent4hvqGtA(ComposableLambdaKt.rememberComposableLambda(1163543932, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogImpl$1.1
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                float f;
                                float f2;
                                ComposerKt.sourceInformation($composer5, "C257@10885L99,254@10722L262:AlertDialog.kt#uh7d8r");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1163543932, $changed3, -1, "androidx.compose.material3.AlertDialogImpl.<anonymous>.<anonymous> (AlertDialog.kt:254)");
                                    }
                                    f = AlertDialogKt.ButtonsMainAxisSpacing;
                                    f2 = AlertDialogKt.ButtonsCrossAxisSpacing;
                                    final Function2<Composer, Integer, Unit> function216 = function214;
                                    final Function2<Composer, Integer, Unit> function217 = function215;
                                    AlertDialogKt.m1762AlertDialogFlowRowixp7dh8(f, f2, ComposableLambdaKt.rememberComposableLambda(1887135077, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt.AlertDialogImpl.1.1.1
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

                                        public final void invoke(Composer $composer6, int $changed4) {
                                            ComposerKt.sourceInformation($composer6, "C259@10951L15:AlertDialog.kt#uh7d8r");
                                            if (($changed4 & 3) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1887135077, $changed4, -1, "androidx.compose.material3.AlertDialogImpl.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:258)");
                                                }
                                                Function2<Composer, Integer, Unit> function218 = function216;
                                                $composer6.startReplaceGroup(1497073862);
                                                ComposerKt.sourceInformation($composer6, "258@10922L8");
                                                if (function218 != null) {
                                                    function218.invoke($composer6, 0);
                                                    Unit unit = Unit.INSTANCE;
                                                }
                                                $composer6.endReplaceGroup();
                                                function217.invoke($composer6, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }, $composer5, 54), $composer5, 438);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }, $composer4, 54), null, function211, function212, function213, shape3, j4, tonalElevation, ColorSchemeKt.getValue(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer4, 6), iconContentColor, titleContentColor, j3, $composer4, 6, 0, 2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ($dirty3 & 14) | 3072 | (($dirty3 >> 3) & 112) | (($dirty1 >> 3) & 896), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogImpl$2
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
                    AlertDialogKt.m1763AlertDialogImplwrnwzgE(function0, function2, modifier, function22, function23, function24, function25, shape, containerColor, iconContentColor, titleContentColor, textContentColor, tonalElevation, properties, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }

    /* renamed from: AlertDialogContent-4hvqGtA, reason: not valid java name */
    public static final void m1761AlertDialogContent4hvqGtA(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Shape shape, final long containerColor, final float tonalElevation, final long buttonContentColor, final long iconContentColor, final long titleContentColor, final long textContentColor, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function25;
        Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Shape shape2;
        int i2;
        float f;
        int $dirty;
        long j;
        Composer $composer2;
        final Modifier modifier3;
        Composer $composer3 = $composer.startRestartGroup(1522575799);
        ComposerKt.sourceInformation($composer3, "C(AlertDialogContent)P(1,5,3,9,7,6,2:c#ui.graphics.Color,11:c#ui.unit.Dp,0:c#ui.graphics.Color,4:c#ui.graphics.Color,10:c#ui.graphics.Color,8:c#ui.graphics.Color)299@12328L2062,294@12188L2202:AlertDialog.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function25 = function2;
        } else if (($changed & 6) == 0) {
            function25 = function2;
            $dirty2 |= $composer3.changedInstance(function25) ? 4 : 2;
        } else {
            function25 = function2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
            function26 = function22;
        } else if (($changed & 384) == 0) {
            function26 = function22;
            $dirty2 |= $composer3.changedInstance(function26) ? 256 : 128;
        } else {
            function26 = function22;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
            function27 = function23;
        } else if (($changed & 3072) == 0) {
            function27 = function23;
            $dirty2 |= $composer3.changedInstance(function27) ? 2048 : 1024;
        } else {
            function27 = function23;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function24) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            shape2 = shape;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            shape2 = shape;
            $dirty2 |= $composer3.changed(shape2) ? 131072 : 65536;
        } else {
            shape2 = shape;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
            i2 = i3;
        } else if (($changed & 1572864) == 0) {
            i2 = i3;
            $dirty2 |= $composer3.changed(containerColor) ? 1048576 : 524288;
        } else {
            i2 = i3;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
            f = tonalElevation;
        } else if (($changed & 12582912) == 0) {
            f = tonalElevation;
            $dirty2 |= $composer3.changed(f) ? 8388608 : 4194304;
        } else {
            f = tonalElevation;
        }
        if ((i & 256) != 0) {
            $dirty = $dirty2 | 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty = $dirty2 | ($composer3.changed(buttonContentColor) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432);
        } else {
            $dirty = $dirty2;
        }
        if ((i & 512) != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changed(iconContentColor) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty;
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
            j = titleContentColor;
        } else if (($changed1 & 6) == 0) {
            j = titleContentColor;
            $dirty1 |= $composer3.changed(j) ? 4 : 2;
        } else {
            j = titleContentColor;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(textContentColor) ? 32 : 16;
        }
        if (($dirty3 & 306783379) != 306783378 || ($dirty1 & 19) != 18 || !$composer3.getSkipping()) {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1522575799, $dirty3, $dirty1, "androidx.compose.material3.AlertDialogContent (AlertDialog.kt:293)");
            }
            final long j2 = j;
            final Function2<? super Composer, ? super Integer, Unit> function28 = function25;
            final Function2<? super Composer, ? super Integer, Unit> function29 = function27;
            Modifier modifier4 = modifier2;
            SurfaceKt.m2547SurfaceT9BRK9s(modifier4, shape2, containerColor, 0L, f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-2126308228, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x016f  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x01b6  */
                /* JADX WARN: Removed duplicated region for block: B:30:0x0210  */
                /* JADX WARN: Removed duplicated region for block: B:33:0x02a3  */
                /* JADX WARN: Removed duplicated region for block: B:36:0x02af  */
                /* JADX WARN: Removed duplicated region for block: B:39:0x02e1  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x0397  */
                /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:48:0x02f7  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x02b3  */
                /* JADX WARN: Removed duplicated region for block: B:50:0x0213  */
                /* JADX WARN: Removed duplicated region for block: B:51:0x01c1  */
                /* JADX WARN: Removed duplicated region for block: B:52:0x0176  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r55, int r56) {
                    /*
                        Method dump skipped, instructions count: 923
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, (($dirty3 >> 3) & 14) | 12582912 | (($dirty3 >> 12) & 112) | (($dirty3 >> 12) & 896) | (($dirty3 >> 9) & 57344), LocationRequestCompat.QUALITY_LOW_POWER);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        } else {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$2
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
                    AlertDialogKt.m1761AlertDialogContent4hvqGtA(function2, modifier3, function22, function23, function24, shape, containerColor, tonalElevation, buttonContentColor, iconContentColor, titleContentColor, textContentColor, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x017a  */
    /* renamed from: AlertDialogFlowRow-ixp7dh8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1762AlertDialogFlowRowixp7dh8(final float r23, final float r24, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r25, androidx.compose.runtime.Composer r26, final int r27) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AlertDialogKt.m1762AlertDialogFlowRowixp7dh8(float, float, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    public static final float getDialogMinWidth() {
        return DialogMinWidth;
    }

    public static final float getDialogMaxWidth() {
        return DialogMaxWidth;
    }
}
