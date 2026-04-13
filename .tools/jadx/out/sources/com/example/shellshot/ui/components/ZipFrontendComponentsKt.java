package com.example.shellshot.ui.components;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ZipFrontendComponents.kt */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\u001aS\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a1\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001a1\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001aO\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\"\u001a\u00020\u0007H\u0007¢\u0006\u0004\b#\u0010$\u001aA\u0010%\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010'2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u0007H\u0007¢\u0006\u0004\b(\u0010)\u001a+\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u0007H\u0007¢\u0006\u0004\b,\u0010-\u001a+\u0010.\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010/\u001a\u00020\u0007H\u0007¢\u0006\u0004\b0\u00101\u001a2\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u0002042\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010'¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u00105¨\u00066²\u0006\n\u00107\u001a\u000208X\u008a\u0084\u0002²\u0006\n\u00109\u001a\u000208X\u008a\u0084\u0002²\u0006\n\u0010:\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"ZipGlassCard", "", "darkTheme", "", "modifier", "Landroidx/compose/ui/Modifier;", "cornerRadius", "Landroidx/compose/ui/unit/Dp;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "ZipGlassCard-TN_CM5M", "(ZLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ZipBadge", "text", "", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "textColor", "ZipBadge-eopBjH0", "(Ljava/lang/String;JJLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "ZipCountPill", "dotColor", "ZipCountPill-sW7UJKQ", "(Ljava/lang/String;JZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "ZipIconPlate", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "tint", "size", "iconSize", "ZipIconPlate-qVRk3xM", "(Landroidx/compose/ui/graphics/vector/ImageVector;JJLandroidx/compose/ui/Modifier;FFFLandroidx/compose/runtime/Composer;II)V", "ZipGlassIconButton", "onClick", "Lkotlin/Function0;", "ZipGlassIconButton-FJfuzF0", "(Landroidx/compose/ui/graphics/vector/ImageVector;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "ZipPulsingDot", "color", "ZipPulsingDot-DxzAY5Q", "(JLandroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "ZipSectionDivider", "startIndent", "ZipSectionDivider-6a0pyJM", "(ZLandroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "ZipStaggeredReveal", "index", "", "(ILandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app_debug", "pulseScale", "", "pulseAlpha", "visible"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ZipFrontendComponentsKt {
    static final Unit ZipBadge_eopBjH0$lambda$2(String str, long j, long j2, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m7021ZipBadgeeopBjH0(str, j, j2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipCountPill_sW7UJKQ$lambda$4(String str, long j, boolean z, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m7022ZipCountPillsW7UJKQ(str, j, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipGlassCard_TN_CM5M$lambda$1(boolean z, Modifier modifier, float f, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        m7023ZipGlassCardTN_CM5M(z, modifier, f, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipGlassIconButton_FJfuzF0$lambda$8(ImageVector imageVector, boolean z, Function0 function0, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        m7024ZipGlassIconButtonFJfuzF0(imageVector, z, function0, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipIconPlate_qVRk3xM$lambda$6(ImageVector imageVector, long j, long j2, Modifier modifier, float f, float f2, float f3, int i, int i2, Composer composer, int i3) {
        m7025ZipIconPlateqVRk3xM(imageVector, j, j2, modifier, f, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipPulsingDot_DxzAY5Q$lambda$12(long j, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        m7026ZipPulsingDotDxzAY5Q(j, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipSectionDivider_6a0pyJM$lambda$13(boolean z, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        m7027ZipSectionDivider6a0pyJM(z, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipStaggeredReveal$lambda$24(int i, Modifier modifier, Function2 function2, int i2, int i3, Composer composer, int i4) {
        ZipStaggeredReveal(i, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03e7  */
    /* renamed from: ZipGlassCard-TN_CM5M, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7023ZipGlassCardTN_CM5M(final boolean r65, androidx.compose.ui.Modifier r66, float r67, androidx.compose.foundation.layout.PaddingValues r68, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r69, androidx.compose.runtime.Composer r70, final int r71, final int r72) {
        /*
            Method dump skipped, instructions count: 1459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M(boolean, androidx.compose.ui.Modifier, float, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: ZipBadge-eopBjH0, reason: not valid java name */
    public static final void m7021ZipBadgeeopBjH0(final String text, final long backgroundColor, final long textColor, Modifier modifier, Composer $composer, final int $changed, final int i) {
        long j;
        Modifier modifier2;
        long m4161copywmQWz5c;
        Composer $composer2;
        final Modifier modifier3;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer3 = $composer.startRestartGroup(-393398404);
        ComposerKt.sourceInformation($composer3, "C(ZipBadge)P(2,0:c#ui.graphics.Color,3:c#ui.graphics.Color)140@5375L394:ZipFrontendComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(backgroundColor) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j = textColor;
            $dirty |= $composer3.changed(j) ? 256 : 128;
        } else {
            j = textColor;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($dirty & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-393398404, $dirty, -1, "com.example.shellshot.ui.components.ZipBadge (ZipFrontendComponents.kt:139)");
            }
            long sp = TextUnitKt.getSp(11);
            long sp2 = TextUnitKt.getSp(14);
            FontWeight medium = FontWeight.INSTANCE.getMedium();
            Modifier m226backgroundbw27NRU = BackgroundKt.m226backgroundbw27NRU(modifier4, backgroundColor, RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(999)));
            float m6626constructorimpl = Dp.m6626constructorimpl(1);
            m4161copywmQWz5c = Color.m4161copywmQWz5c(j, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(j) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(j) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(j) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(j) : 0.0f);
            $composer2 = $composer3;
            Modifier modifier5 = modifier4;
            TextKt.m2697Text4IGK_g(text, PaddingKt.m673paddingVpY3zN4(BorderKt.m238borderxT4_qwU(m226backgroundbw27NRU, m6626constructorimpl, m4161copywmQWz5c, RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(999))), Dp.m6626constructorimpl(10), Dp.m6626constructorimpl(5)), textColor, sp, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, sp2, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, ($dirty & 14) | 199680 | ($dirty & 896), 6, 130000);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.ZipFrontendComponentsKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ZipFrontendComponentsKt.ZipBadge_eopBjH0$lambda$2(text, backgroundColor, textColor, modifier3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x029a  */
    /* renamed from: ZipCountPill-sW7UJKQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7022ZipCountPillsW7UJKQ(final java.lang.String r52, final long r53, final boolean r55, androidx.compose.ui.Modifier r56, androidx.compose.runtime.Composer r57, final int r58, final int r59) {
        /*
            Method dump skipped, instructions count: 816
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ZipFrontendComponentsKt.m7022ZipCountPillsW7UJKQ(java.lang.String, long, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x02e1  */
    /* renamed from: ZipIconPlate-qVRk3xM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7025ZipIconPlateqVRk3xM(final androidx.compose.ui.graphics.vector.ImageVector r39, final long r40, final long r42, androidx.compose.ui.Modifier r44, float r45, float r46, float r47, androidx.compose.runtime.Composer r48, final int r49, final int r50) {
        /*
            Method dump skipped, instructions count: 770
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ZipFrontendComponentsKt.m7025ZipIconPlateqVRk3xM(androidx.compose.ui.graphics.vector.ImageVector, long, long, androidx.compose.ui.Modifier, float, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0288  */
    /* renamed from: ZipGlassIconButton-FJfuzF0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7024ZipGlassIconButtonFJfuzF0(final androidx.compose.ui.graphics.vector.ImageVector r39, final boolean r40, final kotlin.jvm.functions.Function0<kotlin.Unit> r41, androidx.compose.ui.Modifier r42, float r43, androidx.compose.runtime.Composer r44, final int r45, final int r46) {
        /*
            Method dump skipped, instructions count: 769
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ZipFrontendComponentsKt.m7024ZipGlassIconButtonFJfuzF0(androidx.compose.ui.graphics.vector.ImageVector, boolean, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x029a  */
    /* renamed from: ZipPulsingDot-DxzAY5Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7026ZipPulsingDotDxzAY5Q(final long r36, androidx.compose.ui.Modifier r38, float r39, androidx.compose.runtime.Composer r40, final int r41, final int r42) {
        /*
            Method dump skipped, instructions count: 693
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ZipFrontendComponentsKt.m7026ZipPulsingDotDxzAY5Q(long, androidx.compose.ui.Modifier, float, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float ZipPulsingDot_DxzAY5Q$lambda$9(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float ZipPulsingDot_DxzAY5Q$lambda$10(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* renamed from: ZipSectionDivider-6a0pyJM, reason: not valid java name */
    public static final void m7027ZipSectionDivider6a0pyJM(final boolean darkTheme, Modifier modifier, float startIndent, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final Modifier.Companion modifier3;
        float startIndent2;
        long m4161copywmQWz5c;
        final float startIndent3;
        Composer $composer2 = $composer.startRestartGroup(-1542582484);
        ComposerKt.sourceInformation($composer2, "C(ZipSectionDivider)P(!,2:c#ui.unit.Dp)309@10556L242:ZipFrontendComponents.kt#e5eupx");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(darkTheme) ? 4 : 2;
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
            f = startIndent;
        } else if (($changed & 384) == 0) {
            f = startIndent;
            $dirty |= $composer2.changed(f) ? 256 : 128;
        } else {
            f = startIndent;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            startIndent3 = f;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i3 == 0) {
                startIndent2 = f;
            } else {
                startIndent2 = Dp.m6626constructorimpl(60);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1542582484, $dirty2, -1, "com.example.shellshot.ui.components.ZipSectionDivider (ZipFrontendComponents.kt:308)");
            }
            Modifier m703height3ABfNKs = SizeKt.m703height3ABfNKs(PaddingKt.m676paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), startIndent2, 0.0f, 0.0f, 0.0f, 14, null), Dp.m6626constructorimpl(1));
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : darkTheme ? 0.1f : 0.4f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            BoxKt.Box(BackgroundKt.m227backgroundbw27NRU$default(m703height3ABfNKs, m4161copywmQWz5c, null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            startIndent3 = startIndent2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.ZipFrontendComponentsKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ZipFrontendComponentsKt.ZipSectionDivider_6a0pyJM$lambda$13(darkTheme, modifier3, startIndent3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ZipStaggeredReveal(final int r28, androidx.compose.ui.Modifier r29, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.runtime.Composer r31, final int r32, final int r33) {
        /*
            Method dump skipped, instructions count: 565
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ZipFrontendComponentsKt.ZipStaggeredReveal(int, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean ZipStaggeredReveal$lambda$15(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ZipStaggeredReveal$lambda$16(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final int ZipStaggeredReveal$lambda$19$lambda$18(int it) {
        return it / 5;
    }

    static final int ZipStaggeredReveal$lambda$21$lambda$20(int it) {
        return (-it) / 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit ZipStaggeredReveal$lambda$23(kotlin.jvm.functions.Function2 r27, androidx.compose.animation.AnimatedVisibilityScope r28, androidx.compose.runtime.Composer r29, int r30) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ZipFrontendComponentsKt.ZipStaggeredReveal$lambda$23(kotlin.jvm.functions.Function2, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }
}
