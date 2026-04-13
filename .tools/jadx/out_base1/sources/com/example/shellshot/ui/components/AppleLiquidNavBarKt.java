package com.example.shellshot.ui.components;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Shader;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidRenderEffect_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.example.shellshot.ui.theme.ShellShotColorTokens;
import com.example.shellshot.ui.theme.ThemeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: AppleLiquidNavBar.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aA\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001a\r\u0010\u0011\u001a\u00020\u0012H\u0003¢\u0006\u0002\u0010\u0013\u001a\r\u0010\u0014\u001a\u00020\u0006H\u0003¢\u0006\u0002\u0010\u0015\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016²\u0006\n\u0010\u0017\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010\u001a\u001a\u00020\u0019X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u001cX\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\u0019X\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\u0019X\u008a\u008e\u0002²\u0006\n\u0010\u001f\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010 \u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010!\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010\"\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010#\u001a\u00020$X\u008a\u0084\u0002²\u0006\n\u0010%\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010&\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010'\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010(\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010)\u001a\u00020\u0019X\u008a\u0084\u0002"}, d2 = {"AppleLiquidNavBarShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "AppleLiquidNavBarPillShape", "DefaultAppleLiquidNavBarMetrics", "Lcom/example/shellshot/ui/components/AppleLiquidNavBarMetrics;", "AppleLiquidNavBar", "", "items", "", "Lcom/example/shellshot/ui/components/NavItem;", "selectedIndex", "", "onItemSelected", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "rememberAppleLiquidChainEffect", "Landroidx/compose/ui/graphics/RenderEffect;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/RenderEffect;", "AppleLiquidNavBarPreview", "(Landroidx/compose/runtime/Composer;I)V", "app_debug", "lastHapticIndex", "pillOffsetPx", "", "previousFrameOffsetPx", "previousFrameTimeNanos", "", "motionDeltaPx", "motionVelocityPxPerSecond", "pillTransformOriginX", "pillScaleX", "pillScaleY", "pillBiasTranslationPx", "iconTint", "Landroidx/compose/ui/graphics/Color;", "iconScale", "iconTranslationY", "iconAlpha", "labelAlpha", "labelLiftPx"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class AppleLiquidNavBarKt {
    private static final RoundedCornerShape AppleLiquidNavBarShape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(32));
    private static final RoundedCornerShape AppleLiquidNavBarPillShape = RoundedCornerShapeKt.m955RoundedCornerShape0680j_4(Dp.m6626constructorimpl(28));
    private static final AppleLiquidNavBarMetrics DefaultAppleLiquidNavBarMetrics = new AppleLiquidNavBarMetrics(0.0f, 0.0f, 0.0f, 0.0f, 15, null);

    static final Unit AppleLiquidNavBar$lambda$0(List list, int i, Function1 function1, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        AppleLiquidNavBar(list, i, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit AppleLiquidNavBar$lambda$65(List list, int i, Function1 function1, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        AppleLiquidNavBar(list, i, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit AppleLiquidNavBarPreview$lambda$68(int i, Composer composer, int i2) {
        AppleLiquidNavBarPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AppleLiquidNavBar(final java.util.List<com.example.shellshot.ui.components.NavItem> r56, final int r57, final kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r58, androidx.compose.ui.Modifier r59, androidx.compose.runtime.Composer r60, final int r61, final int r62) {
        /*
            Method dump skipped, instructions count: 961
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppleLiquidNavBarKt.AppleLiquidNavBar(java.util.List, int, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int AppleLiquidNavBar$lambda$2(MutableIntState $lastHapticIndex$delegate) {
        return $lastHapticIndex$delegate.getIntValue();
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$6$lambda$5(GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.mo4353setCompositingStrategyaDBOjCE(CompositingStrategy.INSTANCE.m4253getOffscreenNrFUSI());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x07d7  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0aa2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0aae  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0bd8  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0be4  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0c1d  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0cb9  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0d2a  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0d3a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0cc9  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0c33 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0bea  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0ab4  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0e09  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x076a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0721  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x05e5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x059c  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x058a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0596  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x065e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x070f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0754  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit AppleLiquidNavBar$lambda$64$lambda$63(java.util.List r137, com.example.shellshot.ui.components.AppleLiquidNavBarMetrics r138, androidx.compose.ui.unit.Density r139, final androidx.compose.ui.graphics.RenderEffect r140, final com.example.shellshot.ui.theme.ShellShotColorTokens r141, int r142, kotlin.jvm.functions.Function1 r143, androidx.compose.foundation.layout.BoxWithConstraintsScope r144, androidx.compose.runtime.Composer r145, int r146) {
        /*
            Method dump skipped, instructions count: 3599
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63(java.util.List, com.example.shellshot.ui.components.AppleLiquidNavBarMetrics, androidx.compose.ui.unit.Density, androidx.compose.ui.graphics.RenderEffect, com.example.shellshot.ui.theme.ShellShotColorTokens, int, kotlin.jvm.functions.Function1, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$9(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$11(MutableFloatState $previousFrameOffsetPx$delegate) {
        return $previousFrameOffsetPx$delegate.getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long AppleLiquidNavBar$lambda$64$lambda$63$lambda$14(MutableLongState $previousFrameTimeNanos$delegate) {
        return $previousFrameTimeNanos$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$17(MutableFloatState $motionDeltaPx$delegate) {
        return $motionDeltaPx$delegate.getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$20(MutableFloatState $motionVelocityPxPerSecond$delegate) {
        return $motionVelocityPxPerSecond$delegate.getFloatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$23(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$24(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$25(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$27(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$29$lambda$28(RenderEffect $blurEffect, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setClip(true);
        graphicsLayer.setShape(AppleLiquidNavBarShape);
        graphicsLayer.mo4353setCompositingStrategyaDBOjCE(CompositingStrategy.INSTANCE.m4253getOffscreenNrFUSI());
        graphicsLayer.setRenderEffect($blurEffect);
        return Unit.INSTANCE;
    }

    static final DrawResult AppleLiquidNavBar$lambda$64$lambda$63$lambda$32$lambda$31(ShellShotColorTokens $colors, CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        final Brush baseBackdrop;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        final Brush coolSweep;
        long m4161copywmQWz5c5;
        final Brush warmBloom;
        long m4161copywmQWz5c6;
        final Brush lowerBloom;
        long m4161copywmQWz5c7;
        long m4161copywmQWz5c8;
        final Brush refractedBand;
        long m4161copywmQWz5c9;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Brush.Companion companion = Brush.INSTANCE;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : 0.94f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7066getBackgroundPrimary0d7_KjU()) : 0.0f);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.9f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7067getBackgroundSecondary0d7_KjU()) : 0.0f);
        baseBackdrop = companion.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(m4161copywmQWz5c2)}), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : Offset.INSTANCE.m3938getZeroF1C5BW0(), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc())), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion2 = Brush.INSTANCE;
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r15, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r15) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r15) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r15) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r15, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r15) : 0.2f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r15) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r15) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7060getAccentBlue0d7_KjU()) : 0.0f);
        coolSweep = companion2.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c3), Color.m4153boximpl(m4161copywmQWz5c4), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.18f, 0.0f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.82f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc())), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion3 = Brush.INSTANCE;
        m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r16) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        warmBloom = companion3.m4126radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c5), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.22f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.25f), (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : Size.m3990getMinDimensionimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.62f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion4 = Brush.INSTANCE;
        m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r17, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r17) : 0.22f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r17) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r17) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7060getAccentBlue0d7_KjU()) : 0.0f);
        lowerBloom = companion4.m4126radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c6), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.8f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.74f), (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : Size.m3990getMinDimensionimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.56f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion5 = Brush.INSTANCE;
        m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : 0.18f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c8 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : 0.08f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl($colors.m7060getAccentBlue0d7_KjU()) : 0.0f);
        refractedBand = companion5.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c7), Color.m4153boximpl(m4161copywmQWz5c8), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.08f, 0.0f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.72f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc())), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion6 = Brush.INSTANCE;
        m4161copywmQWz5c9 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : 0.22f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        final Brush rimLight = Brush.Companion.m4120verticalGradient8A3gB4$default(companion6, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c9), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), 0.0f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.42f, 0, 10, (Object) null);
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.AppleLiquidNavBarKt$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$32$lambda$31$lambda$30(Brush.this, coolSweep, warmBloom, lowerBloom, refractedBand, rimLight, (DrawScope) obj);
            }
        });
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$32$lambda$31$lambda$30(Brush $baseBackdrop, Brush $coolSweep, Brush $warmBloom, Brush $lowerBloom, Brush $refractedBand, Brush $rimLight, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $baseBackdrop, 0L, 0L, 0L, 0.0f, null, null, 0, 254, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $coolSweep, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $warmBloom, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $lowerBloom, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $refractedBand, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $rimLight, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        return Unit.INSTANCE;
    }

    static final DrawResult AppleLiquidNavBar$lambda$64$lambda$63$lambda$35$lambda$34(CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Brush.Companion companion = Brush.INSTANCE;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.18f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        final Brush shellLight = Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), 0.0f, 0.35f * Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 10, (Object) null);
        Brush.Companion companion2 = Brush.INSTANCE;
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.08f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.07f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        final Brush shellEdge = Brush.Companion.m4112horizontalGradient8A3gB4$default(companion2, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c2), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c3)}), 0.0f, 0.0f, 0, 14, (Object) null);
        Brush.Companion companion3 = Brush.INSTANCE;
        m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r12) : 0.06f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4189getBlack0d7_KjU()) : 0.0f);
        final Brush shellDepth = Brush.Companion.m4120verticalGradient8A3gB4$default(companion3, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c4)}), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.66f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 8, (Object) null);
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.AppleLiquidNavBarKt$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$35$lambda$34$lambda$33(Brush.this, shellEdge, shellDepth, (DrawScope) obj);
            }
        });
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$35$lambda$34$lambda$33(Brush $shellLight, Brush $shellEdge, Brush $shellDepth, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $shellLight, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $shellEdge, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $shellDepth, 0L, 0L, 0L, 0.0f, null, null, 0, 254, null);
        return Unit.INSTANCE;
    }

    static final IntOffset AppleLiquidNavBar$lambda$64$lambda$63$lambda$37$lambda$36(State $pillOffsetPx$delegate, Density offset) {
        Intrinsics.checkNotNullParameter(offset, "$this$offset");
        return IntOffset.m6745boximpl(IntOffsetKt.IntOffset(MathKt.roundToInt(AppleLiquidNavBar$lambda$64$lambda$63$lambda$9($pillOffsetPx$delegate)), 0));
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$39$lambda$38(State $pillScaleX$delegate, State $pillScaleY$delegate, State $pillBiasTranslationPx$delegate, State $pillTransformOriginX$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.mo4353setCompositingStrategyaDBOjCE(CompositingStrategy.INSTANCE.m4253getOffscreenNrFUSI());
        graphicsLayer.setScaleX(AppleLiquidNavBar$lambda$64$lambda$63$lambda$24($pillScaleX$delegate));
        graphicsLayer.setScaleY(AppleLiquidNavBar$lambda$64$lambda$63$lambda$25($pillScaleY$delegate));
        graphicsLayer.setTranslationX(AppleLiquidNavBar$lambda$64$lambda$63$lambda$27($pillBiasTranslationPx$delegate));
        graphicsLayer.mo4355setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(AppleLiquidNavBar$lambda$64$lambda$63$lambda$23($pillTransformOriginX$delegate), 0.5f));
        return Unit.INSTANCE;
    }

    static final DrawResult AppleLiquidNavBar$lambda$64$lambda$63$lambda$43$lambda$42$lambda$41(float $motionBias, CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        final Brush travelingGlassBand;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        final Brush edgeBloom;
        long m4161copywmQWz5c8;
        final Brush centerHotspot;
        long m4161copywmQWz5c9;
        long m4161copywmQWz5c10;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        Brush.Companion companion = Brush.INSTANCE;
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r5) : 0.34f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        final Brush gloss = Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), 0.0f, 0.42f * Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 10, (Object) null);
        Brush.Companion companion2 = Brush.INSTANCE;
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.08f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4189getBlack0d7_KjU()) : 0.0f);
        final Brush depth = Brush.Companion.m4120verticalGradient8A3gB4$default(companion2, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c2)}), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.58f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 8, (Object) null);
        Brush.Companion companion3 = Brush.INSTANCE;
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 0.2f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 0.06f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        travelingGlassBand = companion3.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c3), Color.m4153boximpl(m4161copywmQWz5c4), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * (($motionBias * 0.18f) + 0.12f), 0.0f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * ((0.22f * $motionBias) + 0.8f), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc())), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion4 = Brush.INSTANCE;
        m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 0.04f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 0.04f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        edgeBloom = Brush.Companion.m4112horizontalGradient8A3gB4$default(companion4, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c5), Color.m4153boximpl(m4161copywmQWz5c6), Color.m4153boximpl(m4161copywmQWz5c7)}), 0.0f, 0.0f, 0, 14, (Object) null);
        Brush.Companion companion5 = Brush.INSTANCE;
        m4161copywmQWz5c8 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 0.18f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        centerHotspot = companion5.m4126radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(m4161copywmQWz5c8), Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU())}), (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * ((0.18f * $motionBias) + 0.5f), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.38f), (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : Size.m3990getMinDimensionimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.88f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion6 = Brush.INSTANCE;
        m4161copywmQWz5c9 = Color.m4161copywmQWz5c(r18, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r18) : 0.05f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r18) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r18) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4189getBlack0d7_KjU()) : 0.0f);
        m4161copywmQWz5c10 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : 0.1f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4189getBlack0d7_KjU()) : 0.0f);
        final Brush lowerInnerShadow = Brush.Companion.m4120verticalGradient8A3gB4$default(companion6, CollectionsKt.listOf((Object[]) new Color[]{Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU()), Color.m4153boximpl(m4161copywmQWz5c9), Color.m4153boximpl(m4161copywmQWz5c10)}), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.48f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()), 0, 8, (Object) null);
        return drawWithCache.onDrawBehind(new Function1() { // from class: com.example.shellshot.ui.components.AppleLiquidNavBarKt$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppleLiquidNavBarKt.AppleLiquidNavBar$lambda$64$lambda$63$lambda$43$lambda$42$lambda$41$lambda$40(Brush.this, travelingGlassBand, edgeBloom, centerHotspot, depth, lowerInnerShadow, (DrawScope) obj);
            }
        });
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$43$lambda$42$lambda$41$lambda$40(Brush $gloss, Brush $travelingGlassBand, Brush $edgeBloom, Brush $centerHotspot, Brush $depth, Brush $lowerInnerShadow, DrawScope onDrawBehind) {
        Intrinsics.checkNotNullParameter(onDrawBehind, "$this$onDrawBehind");
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $gloss, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $travelingGlassBand, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $edgeBloom, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $centerHotspot, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $depth, 0L, 0L, 0L, 0.0f, null, null, 0, 254, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawBehind, $lowerInnerShadow, 0L, 0L, 0L, 0.0f, null, null, 0, 254, null);
        return Unit.INSTANCE;
    }

    private static final long AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$44(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$45(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$47(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$48(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$49(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$51(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$54$lambda$53(Function1 $onItemSelected, int $index) {
        $onItemSelected.invoke(Integer.valueOf($index));
        return Unit.INSTANCE;
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$60$lambda$59$lambda$56$lambda$55(State $iconScale$delegate, State $iconAlpha$delegate, State $iconTranslationY$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$45($iconScale$delegate));
        graphicsLayer.setScaleY(AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$45($iconScale$delegate));
        graphicsLayer.setAlpha(AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$48($iconAlpha$delegate));
        graphicsLayer.setTranslationY(AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$47($iconTranslationY$delegate));
        return Unit.INSTANCE;
    }

    static final Unit AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$60$lambda$59$lambda$58$lambda$57(State $labelAlpha$delegate, State $labelLiftPx$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha(AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$49($labelAlpha$delegate));
        graphicsLayer.setTranslationY(AppleLiquidNavBar$lambda$64$lambda$63$lambda$62$lambda$61$lambda$51($labelLiftPx$delegate));
        return Unit.INSTANCE;
    }

    private static final RenderEffect rememberAppleLiquidChainEffect(Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformationMarkerStart($composer, -704499779, "C(rememberAppleLiquidChainEffect)537@25255L734:AppleLiquidNavBar.kt#e5eupx");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-704499779, $changed, -1, "com.example.shellshot.ui.components.rememberAppleLiquidChainEffect (AppleLiquidNavBar.kt:536)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1580548571, "CC(remember):AppleLiquidNavBar.kt#9igjgp");
        Object rememberedValue = $composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(1.4f);
            colorMatrix.postConcat(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 8.0f, 0.0f, 1.0f, 0.0f, 0.0f, 8.0f, 0.0f, 0.0f, 1.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
            android.graphics.RenderEffect createBlurEffect = android.graphics.RenderEffect.createBlurEffect(30.0f, 30.0f, Shader.TileMode.CLAMP);
            Intrinsics.checkNotNullExpressionValue(createBlurEffect, "createBlurEffect(...)");
            android.graphics.RenderEffect createColorFilterEffect = android.graphics.RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(colorMatrix));
            Intrinsics.checkNotNullExpressionValue(createColorFilterEffect, "createColorFilterEffect(...)");
            android.graphics.RenderEffect createChainEffect = android.graphics.RenderEffect.createChainEffect(createColorFilterEffect, createBlurEffect);
            Intrinsics.checkNotNullExpressionValue(createChainEffect, "createChainEffect(...)");
            obj = AndroidRenderEffect_androidKt.asComposeRenderEffect(createChainEffect);
            $composer.updateRememberedValue(obj);
        } else {
            obj = rememberedValue;
        }
        RenderEffect renderEffect = (RenderEffect) obj;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return renderEffect;
    }

    private static final void AppleLiquidNavBarPreview(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2003700083);
        ComposerKt.sourceInformation($composer2, "C(AppleLiquidNavBarPreview)568@26302L1006:AppleLiquidNavBar.kt#e5eupx");
        if ($changed == 0 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2003700083, $changed, -1, "com.example.shellshot.ui.components.AppleLiquidNavBarPreview (AppleLiquidNavBar.kt:567)");
            }
            ThemeKt.ShellShotTheme(false, ComposableSingletons$AppleLiquidNavBarKt.INSTANCE.getLambda$1970376422$app_debug(), $composer2, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.AppleLiquidNavBarKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppleLiquidNavBarKt.AppleLiquidNavBarPreview$lambda$68($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
