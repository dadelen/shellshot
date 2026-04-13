package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: AlertDialog.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a{\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a5\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\fH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a;\u0010\u001e\u001a\u00020\t*\u00020\u001f2\u0013\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\fH\u0001¢\u0006\u0002\u0010 \"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"TextBaselineDistanceFromTitle", "Landroidx/compose/ui/unit/TextUnit;", "J", "TextBaselineDistanceFromTop", "TextPadding", "Landroidx/compose/ui/Modifier;", "TitleBaselineDistanceFromTop", "TitlePadding", "AlertDialogContent", "", "buttons", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "AlertDialogContent-WMdw5o4", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/runtime/Composer;II)V", "AlertDialogFlowRow", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "content", "AlertDialogFlowRow-ixp7dh8", "(FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "AlertDialogBaselineLayout", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/foundation/layout/ColumnScope;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AlertDialogKt {
    private static final Modifier TitlePadding = PaddingKt.m676paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m6626constructorimpl(24), 0.0f, Dp.m6626constructorimpl(24), 0.0f, 10, null);
    private static final Modifier TextPadding = PaddingKt.m676paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m6626constructorimpl(24), 0.0f, Dp.m6626constructorimpl(24), Dp.m6626constructorimpl(28), 2, null);
    private static final long TitleBaselineDistanceFromTop = TextUnitKt.getSp(40);
    private static final long TextBaselineDistanceFromTitle = TextUnitKt.getSp(36);
    private static final long TextBaselineDistanceFromTop = TextUnitKt.getSp(38);

    /* renamed from: AlertDialogContent-WMdw5o4, reason: not valid java name */
    public static final void m1396AlertDialogContentWMdw5o4(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, long backgroundColor, long contentColor, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Function2 title;
        final Function2 text;
        Shape shape2;
        long backgroundColor2;
        int i2;
        long j;
        int $dirty;
        int $dirty2;
        long contentColor2;
        long backgroundColor3;
        Shape shape3;
        Modifier modifier3;
        Composer $composer2;
        final Function2 text2;
        final Modifier modifier4;
        final Shape shape4;
        final long contentColor3;
        final Function2 title2;
        final long backgroundColor4;
        int i3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-453679601);
        ComposerKt.sourceInformation($composer3, "C(AlertDialogContent)P(1,3,6,5,4,0:c#ui.graphics.Color,2:c#ui.graphics.Color)50@1988L6,51@2046L6,52@2088L32,59@2266L911,54@2130L1047:AlertDialog.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty3 |= 384;
            title = function22;
        } else if (($changed & 384) == 0) {
            title = function22;
            $dirty3 |= $composer3.changedInstance(title) ? 256 : 128;
        } else {
            title = function22;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty3 |= 3072;
            text = function23;
        } else if (($changed & 3072) == 0) {
            text = function23;
            $dirty3 |= $composer3.changedInstance(text) ? 2048 : 1024;
        } else {
            text = function23;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i5 = 16384;
                    $dirty3 |= i5;
                }
            } else {
                shape2 = shape;
            }
            i5 = 8192;
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                backgroundColor2 = backgroundColor;
                if ($composer3.changed(backgroundColor2)) {
                    i4 = 131072;
                    $dirty3 |= i4;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i4 = 65536;
            $dirty3 |= i4;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                i2 = 1572864;
                j = contentColor;
                if ($composer3.changed(j)) {
                    i3 = 1048576;
                    $dirty3 |= i3;
                }
            } else {
                i2 = 1572864;
                j = contentColor;
            }
            i3 = 524288;
            $dirty3 |= i3;
        } else {
            i2 = 1572864;
            j = contentColor;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            title2 = title;
            text2 = text;
            shape4 = shape2;
            backgroundColor4 = backgroundColor2;
            contentColor3 = j;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    title = null;
                }
                if (i8 != 0) {
                    text = null;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty4 & (-57345);
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getMedium();
                } else {
                    $dirty = $dirty4;
                }
                if ((i & 32) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1482getSurface0d7_KjU();
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 = $dirty & (-3670017);
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty >> 15) & 14);
                    backgroundColor3 = backgroundColor2;
                    shape3 = shape2;
                    modifier3 = modifier2;
                } else {
                    $dirty2 = $dirty;
                    contentColor2 = j;
                    backgroundColor3 = backgroundColor2;
                    shape3 = shape2;
                    modifier3 = modifier2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty4 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty4 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 = $dirty4 & (-3670017);
                    contentColor2 = j;
                    backgroundColor3 = backgroundColor2;
                    shape3 = shape2;
                    modifier3 = modifier2;
                } else {
                    $dirty2 = $dirty4;
                    contentColor2 = j;
                    backgroundColor3 = backgroundColor2;
                    shape3 = shape2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-453679601, $dirty2, -1, "androidx.compose.material.AlertDialogContent (AlertDialog.kt:53)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1660SurfaceFjzlyU(modifier3, shape3, backgroundColor3, contentColor2, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(629950291, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AlertDialogKt$AlertDialogContent$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0144  */
                /* JADX WARN: Removed duplicated region for block: B:26:0x0179  */
                /* JADX WARN: Removed duplicated region for block: B:29:0x01d2  */
                /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:32:0x0184  */
                /* JADX WARN: Removed duplicated region for block: B:33:0x0151  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r33, int r34) {
                    /*
                        Method dump skipped, instructions count: 470
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AlertDialogKt$AlertDialogContent$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 3) & 14) | i2 | (($dirty2 >> 9) & 112) | (($dirty2 >> 9) & 896) | (($dirty2 >> 9) & 7168), 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            text2 = text;
            modifier4 = modifier3;
            shape4 = shape3;
            contentColor3 = contentColor2;
            title2 = title;
            backgroundColor4 = backgroundColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AlertDialogKt$AlertDialogContent$2
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

                public final void invoke(Composer composer, int i9) {
                    AlertDialogKt.m1396AlertDialogContentWMdw5o4(function2, modifier4, title2, text2, shape4, backgroundColor4, contentColor3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AlertDialogBaselineLayout(final androidx.compose.foundation.layout.ColumnScope r53, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, androidx.compose.runtime.Composer r56, final int r57) {
        /*
            Method dump skipped, instructions count: 1055
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AlertDialogKt.AlertDialogBaselineLayout(androidx.compose.foundation.layout.ColumnScope, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x017b  */
    /* renamed from: AlertDialogFlowRow-ixp7dh8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1397AlertDialogFlowRowixp7dh8(final float r23, final float r24, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r25, androidx.compose.runtime.Composer r26, final int r27) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AlertDialogKt.m1397AlertDialogFlowRowixp7dh8(float, float, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }
}
