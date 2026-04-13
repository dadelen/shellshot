package com.example.shellshot.ui.components;

import android.graphics.BlurMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import com.example.shellshot.ui.theme.ThemeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BottomDock.kt */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\u001aK\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\r\u001aK\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0003¢\u0006\u0004\b\u001a\u0010\u001b\u001a}\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00010&H\u0003¢\u0006\u0004\b'\u0010(\u001a\r\u0010)\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010*\u001a\r\u0010+\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010*¨\u0006,²\u0006\n\u0010-\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020\fX\u008a\u008e\u0002²\u0006\n\u0010/\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u00100\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u00101\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u00102\u001a\u00020\u0014X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u00104\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u00105\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u00106\u001a\u00020\u0011X\u008a\u0084\u0002"}, d2 = {"BottomDock", "", "items", "", "Lcom/example/shellshot/ui/components/NavItem;", "selectedItemId", "", "onItemSelected", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "darkTheme", "", "(Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "SharedDockSlider", "Landroidx/compose/foundation/layout/BoxScope;", "offsetPx", "", "widthPx", "itemHeight", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "sliderBorderColor", "Landroidx/compose/ui/graphics/Color;", "alpha", "SharedDockSlider-j-zsiCA", "(Landroidx/compose/foundation/layout/BoxScope;FFFLandroidx/compose/foundation/shape/RoundedCornerShape;JFZLandroidx/compose/runtime/Composer;I)V", "DockItem", "label", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "selected", "expandedItemWidth", "collapsedItemWidth", "onPlaced", "onWidthChanged", "onClick", "Lkotlin/Function0;", "DockItem-si5o8Gs", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;ZZFFFLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "BottomDockPreview", "(Landroidx/compose/runtime/Composer;I)V", "BottomDockDarkPreview", "app_debug", "previousSelectedId", "sliderVisible", "animatedSliderOffsetPx", "animatedSliderWidthPx", "sliderAlpha", "itemWidth", "iconOffsetY", "labelOffsetY", "labelAlpha", "labelScale"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class BottomDockKt {
    static final Unit BottomDock$lambda$1(List list, String str, Function1 function1, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        BottomDock(list, str, function1, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomDock$lambda$41(List list, String str, Function1 function1, Modifier modifier, boolean z, int i, int i2, Composer composer, int i3) {
        BottomDock(list, str, function1, modifier, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomDockDarkPreview$lambda$65(int i, Composer composer, int i2) {
        BottomDockDarkPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BottomDockPreview$lambda$64(int i, Composer composer, int i2) {
        BottomDockPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DockItem_si5o8Gs$lambda$63(String str, ImageVector imageVector, boolean z, boolean z2, float f, float f2, float f3, Function1 function1, Function1 function12, Function0 function0, int i, Composer composer, int i2) {
        m6939DockItemsi5o8Gs(str, imageVector, z, z2, f, f2, f3, function1, function12, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SharedDockSlider_j_zsiCA$lambda$46(BoxScope boxScope, float f, float f2, float f3, RoundedCornerShape roundedCornerShape, long j, float f4, boolean z, int i, Composer composer, int i2) {
        m6940SharedDockSliderjzsiCA(boxScope, f, f2, f3, roundedCornerShape, j, f4, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0568  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0631  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0689  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0696  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0734  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x076d  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0869  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0875  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x08ac  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0925 A[LOOP:3: B:183:0x091f->B:185:0x0925, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0ab5  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x08c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x087b  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0783  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x073a  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x06a3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x063f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x05b7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0441  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BottomDock(final java.util.List<com.example.shellshot.ui.components.NavItem> r123, final java.lang.String r124, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r125, androidx.compose.ui.Modifier r126, boolean r127, androidx.compose.runtime.Composer r128, final int r129, final int r130) {
        /*
            Method dump skipped, instructions count: 2773
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.BottomDockKt.BottomDock(java.util.List, java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String BottomDock$lambda$5(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean BottomDock$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BottomDock$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float BottomDock$lambda$16(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float BottomDock$lambda$17(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float BottomDock$lambda$18(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final DrawResult BottomDock$lambda$40$lambda$26$lambda$25(long $dockShadowColor, boolean $darkTheme, CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        int m4217toArgb8_81llA;
        long m4161copywmQWz5c2;
        int m4217toArgb8_81llA2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        float shadowOffsetY = drawWithCache.mo364toPx0680j_4(Dp.m6626constructorimpl(8));
        float shadowBlurPx = drawWithCache.mo364toPx0680j_4(Dp.m6626constructorimpl(32));
        final float cornerRadius = Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) / 2.0f;
        final RectF frostRect = new RectF(0.0f, 0.0f, Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()));
        final RectF shadowRect = new RectF(0.0f, shadowOffsetY, Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) + shadowOffsetY);
        final Paint paint = new Paint(1);
        paint.setColor(ColorKt.m4217toArgb8_81llA($dockShadowColor));
        paint.setMaskFilter(new BlurMaskFilter(shadowBlurPx, BlurMaskFilter.Blur.NORMAL));
        final Paint paint2 = new Paint(1);
        if ($darkTheme) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4189getBlack0d7_KjU()) : 0.0f);
            m4217toArgb8_81llA = ColorKt.m4217toArgb8_81llA(m4161copywmQWz5c4);
        } else {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4217toArgb8_81llA = ColorKt.m4217toArgb8_81llA(m4161copywmQWz5c);
        }
        paint2.setColor(m4217toArgb8_81llA);
        paint2.setMaskFilter(new BlurMaskFilter(40.0f, BlurMaskFilter.Blur.NORMAL));
        final Paint paint3 = new Paint(1);
        if ($darkTheme) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.03f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4217toArgb8_81llA2 = ColorKt.m4217toArgb8_81llA(m4161copywmQWz5c3);
        } else {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.08f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
            m4217toArgb8_81llA2 = ColorKt.m4217toArgb8_81llA(m4161copywmQWz5c2);
        }
        paint3.setColor(m4217toArgb8_81llA2);
        return drawWithCache.onDrawWithContent(new Function1() { // from class: com.example.shellshot.ui.components.BottomDockKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomDockKt.BottomDock$lambda$40$lambda$26$lambda$25$lambda$24(shadowRect, cornerRadius, paint, frostRect, paint2, paint3, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit BottomDock$lambda$40$lambda$26$lambda$25$lambda$24(RectF $shadowRect, float $cornerRadius, Paint $shadowPaint, RectF $frostRect, Paint $blurVeilPaint, Paint $blurCorePaint, ContentDrawScope onDrawWithContent) {
        Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
        Canvas canvas = onDrawWithContent.getDrawContext().getCanvas();
        AndroidCanvas_androidKt.getNativeCanvas(canvas).drawRoundRect($shadowRect, $cornerRadius, $cornerRadius, $shadowPaint);
        AndroidCanvas_androidKt.getNativeCanvas(canvas).drawRoundRect($frostRect, $cornerRadius, $cornerRadius, $blurVeilPaint);
        AndroidCanvas_androidKt.getNativeCanvas(canvas).drawRoundRect($frostRect, $cornerRadius, $cornerRadius, $blurCorePaint);
        onDrawWithContent.drawContent();
        return Unit.INSTANCE;
    }

    static final DrawResult BottomDock$lambda$40$lambda$29$lambda$28(boolean $darkTheme, CacheDrawScope drawWithCache) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        final Brush innerBloom;
        long m4161copywmQWz5c7;
        long m4161copywmQWz5c8;
        final Brush leftArcReflection;
        long m4161copywmQWz5c9;
        long m4161copywmQWz5c10;
        final Brush rightArcReflection;
        long m4161copywmQWz5c11;
        final Brush leftRimHighlight;
        long m4161copywmQWz5c12;
        final Brush rightRimHighlight;
        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
        final float edgeHeight = Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.16f;
        Brush.Companion companion = Brush.INSTANCE;
        Color[] colorArr = new Color[3];
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r7) : $darkTheme ? 0.16f : 0.3f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr[0] = Color.m4153boximpl(m4161copywmQWz5c);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r11) : $darkTheme ? 0.08f : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr[1] = Color.m4153boximpl(m4161copywmQWz5c2);
        colorArr[2] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        final Brush topEdgeLine = Brush.Companion.m4120verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) colorArr), 0.0f, edgeHeight, 0, 10, (Object) null);
        Brush.Companion companion2 = Brush.INSTANCE;
        Color[] colorArr2 = new Color[2];
        m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : $darkTheme ? 0.07f : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr2[0] = Color.m4153boximpl(m4161copywmQWz5c3);
        colorArr2[1] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        final Brush topMirror = Brush.Companion.m4120verticalGradient8A3gB4$default(companion2, CollectionsKt.listOf((Object[]) colorArr2), 0.0f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.24f, 0, 10, (Object) null);
        Brush.Companion companion3 = Brush.INSTANCE;
        Color[] colorArr3 = new Color[3];
        m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : $darkTheme ? 0.05f : 0.1f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr3[0] = Color.m4153boximpl(m4161copywmQWz5c4);
        colorArr3[1] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : $darkTheme ? 0.03f : 0.06f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr3[2] = Color.m4153boximpl(m4161copywmQWz5c5);
        final Brush sideFrost = Brush.Companion.m4112horizontalGradient8A3gB4$default(companion3, CollectionsKt.listOf((Object[]) colorArr3), 0.0f, 0.0f, 0, 14, (Object) null);
        Brush.Companion companion4 = Brush.INSTANCE;
        Color[] colorArr4 = new Color[2];
        m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : $darkTheme ? 0.03f : 0.05f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr4[0] = Color.m4153boximpl(m4161copywmQWz5c6);
        colorArr4[1] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        innerBloom = companion4.m4126radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) colorArr4), (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.5f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.18f), (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.52f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion5 = Brush.INSTANCE;
        Color[] colorArr5 = new Color[3];
        m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : $darkTheme ? 0.08f : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr5[0] = Color.m4153boximpl(m4161copywmQWz5c7);
        m4161copywmQWz5c8 = Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : $darkTheme ? 0.04f : 0.07f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr5[1] = Color.m4153boximpl(m4161copywmQWz5c8);
        colorArr5[2] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        leftArcReflection = companion5.m4126radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) colorArr5), (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.1f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.16f), (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.88f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion6 = Brush.INSTANCE;
        Color[] colorArr6 = new Color[3];
        m4161copywmQWz5c9 = Color.m4161copywmQWz5c(r30, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r30) : $darkTheme ? 0.08f : 0.14f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r30) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r30) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr6[0] = Color.m4153boximpl(m4161copywmQWz5c9);
        m4161copywmQWz5c10 = Color.m4161copywmQWz5c(r30, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r30) : $darkTheme ? 0.04f : 0.07f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r30) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r30) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr6[1] = Color.m4153boximpl(m4161copywmQWz5c10);
        colorArr6[2] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        rightArcReflection = companion6.m4126radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) colorArr6), (r12 & 2) != 0 ? Offset.INSTANCE.m3937getUnspecifiedF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.9f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.16f), (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.88f, (r12 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion7 = Brush.INSTANCE;
        Color[] colorArr7 = new Color[2];
        m4161copywmQWz5c11 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : $darkTheme ? 0.06f : 0.1f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr7[0] = Color.m4153boximpl(m4161copywmQWz5c11);
        colorArr7[1] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        leftRimHighlight = companion7.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) colorArr7), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(0.0f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.04f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.18f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.52f), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        Brush.Companion companion8 = Brush.INSTANCE;
        Color[] colorArr8 = new Color[2];
        m4161copywmQWz5c12 = Color.m4161copywmQWz5c(r19, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r19) : $darkTheme ? 0.06f : 0.1f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r19) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r19) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(Color.INSTANCE.m4200getWhite0d7_KjU()) : 0.0f);
        colorArr8[0] = Color.m4153boximpl(m4161copywmQWz5c12);
        colorArr8[1] = Color.m4153boximpl(Color.INSTANCE.m4198getTransparent0d7_KjU());
        rightRimHighlight = companion8.m4124linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) colorArr8), (r14 & 2) != 0 ? Offset.INSTANCE.m3938getZeroF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()), Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.04f), (r14 & 4) != 0 ? Offset.INSTANCE.m3936getInfiniteF1C5BW0() : OffsetKt.Offset(Size.m3991getWidthimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.82f, Size.m3988getHeightimpl(drawWithCache.m3812getSizeNHjbRc()) * 0.52f), (r14 & 8) != 0 ? TileMode.INSTANCE.m4535getClamp3opZhB0() : 0);
        return drawWithCache.onDrawWithContent(new Function1() { // from class: com.example.shellshot.ui.components.BottomDockKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomDockKt.BottomDock$lambda$40$lambda$29$lambda$28$lambda$27(Brush.this, edgeHeight, topMirror, sideFrost, innerBloom, leftArcReflection, rightArcReflection, leftRimHighlight, rightRimHighlight, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit BottomDock$lambda$40$lambda$29$lambda$28$lambda$27(Brush $topEdgeLine, float $edgeHeight, Brush $topMirror, Brush $sideFrost, Brush $innerBloom, Brush $leftArcReflection, Brush $rightArcReflection, Brush $leftRimHighlight, Brush $rightRimHighlight, ContentDrawScope onDrawWithContent) {
        Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
        onDrawWithContent.drawContent();
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $topEdgeLine, 0L, SizeKt.Size(Size.m3991getWidthimpl(onDrawWithContent.mo4713getSizeNHjbRc()), $edgeHeight), 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 122, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $topMirror, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $sideFrost, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $innerBloom, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $leftArcReflection, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $rightArcReflection, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $leftRimHighlight, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        DrawScope.m4708drawRoundRectZuiqVtQ$default(onDrawWithContent, $rightRimHighlight, 0L, 0L, 0L, 0.0f, null, null, BlendMode.INSTANCE.m4099getScreen0nO6VwU(), 126, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit BottomDock$lambda$40$lambda$39$lambda$38$lambda$37$lambda$36(final com.example.shellshot.ui.components.NavItem r19, final com.example.shellshot.ui.components.NavItem r20, boolean r21, float r22, float r23, float r24, final androidx.compose.ui.hapticfeedback.HapticFeedback r25, final kotlin.jvm.functions.Function1 r26, final androidx.compose.runtime.snapshots.SnapshotStateMap r27, final androidx.compose.runtime.snapshots.SnapshotStateMap r28, androidx.compose.animation.AnimatedVisibilityScope r29, androidx.compose.runtime.Composer r30, int r31) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.BottomDockKt.BottomDock$lambda$40$lambda$39$lambda$38$lambda$37$lambda$36(com.example.shellshot.ui.components.NavItem, com.example.shellshot.ui.components.NavItem, boolean, float, float, float, androidx.compose.ui.hapticfeedback.HapticFeedback, kotlin.jvm.functions.Function1, androidx.compose.runtime.snapshots.SnapshotStateMap, androidx.compose.runtime.snapshots.SnapshotStateMap, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit BottomDock$lambda$40$lambda$39$lambda$38$lambda$37$lambda$36$lambda$31$lambda$30(SnapshotStateMap $itemOffsetsPx, NavItem $item, float it) {
        $itemOffsetsPx.put($item.getId(), Float.valueOf(it));
        return Unit.INSTANCE;
    }

    static final Unit BottomDock$lambda$40$lambda$39$lambda$38$lambda$37$lambda$36$lambda$33$lambda$32(SnapshotStateMap $itemWidthsPx, NavItem $item, float it) {
        $itemWidthsPx.put($item.getId(), Float.valueOf(it));
        return Unit.INSTANCE;
    }

    static final Unit BottomDock$lambda$40$lambda$39$lambda$38$lambda$37$lambda$36$lambda$35$lambda$34(NavItem $item, NavItem $currentItem, HapticFeedback $hapticFeedback, Function1 $onItemSelected) {
        if (!Intrinsics.areEqual($item.getId(), $currentItem.getId())) {
            $hapticFeedback.mo4883performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4891getLongPress5zf0vsI());
            $onItemSelected.invoke($item.getId());
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x04dc  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02b8  */
    /* renamed from: SharedDockSlider-j-zsiCA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6940SharedDockSliderjzsiCA(final androidx.compose.foundation.layout.BoxScope r47, final float r48, final float r49, final float r50, final androidx.compose.foundation.shape.RoundedCornerShape r51, final long r52, final float r54, final boolean r55, androidx.compose.runtime.Composer r56, final int r57) {
        /*
            Method dump skipped, instructions count: 1382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.BottomDockKt.m6940SharedDockSliderjzsiCA(androidx.compose.foundation.layout.BoxScope, float, float, float, androidx.compose.foundation.shape.RoundedCornerShape, long, float, boolean, androidx.compose.runtime.Composer, int):void");
    }

    static final Unit SharedDockSlider_j_zsiCA$lambda$43$lambda$42(float $offsetPx, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationX($offsetPx);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x05eb  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0675  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x05f9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0551 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0301  */
    /* renamed from: DockItem-si5o8Gs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6939DockItemsi5o8Gs(final java.lang.String r82, final androidx.compose.ui.graphics.vector.ImageVector r83, final boolean r84, final boolean r85, final float r86, final float r87, final float r88, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r89, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r90, final kotlin.jvm.functions.Function0<kotlin.Unit> r91, androidx.compose.runtime.Composer r92, final int r93) {
        /*
            Method dump skipped, instructions count: 1694
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.BottomDockKt.m6939DockItemsi5o8Gs(java.lang.String, androidx.compose.ui.graphics.vector.ImageVector, boolean, boolean, float, float, float, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    private static final float DockItem_si5o8Gs$lambda$47(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    private static final float DockItem_si5o8Gs$lambda$48(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float DockItem_si5o8Gs$lambda$49(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float DockItem_si5o8Gs$lambda$50(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float DockItem_si5o8Gs$lambda$51(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit DockItem_si5o8Gs$lambda$53$lambda$52(Function1 $onPlaced, LayoutCoordinates coordinates) {
        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
        $onPlaced.invoke(Float.valueOf(Offset.m3922getXimpl(LayoutCoordinatesKt.positionInParent(coordinates))));
        return Unit.INSTANCE;
    }

    static final Unit DockItem_si5o8Gs$lambda$55$lambda$54(Function1 $onWidthChanged, IntSize it) {
        $onWidthChanged.invoke(Float.valueOf(IntSize.m6796getWidthimpl(it.getPackedValue())));
        return Unit.INSTANCE;
    }

    static final Unit DockItem_si5o8Gs$lambda$62$lambda$61$lambda$58$lambda$57(State $iconOffsetY$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationY(DockItem_si5o8Gs$lambda$48($iconOffsetY$delegate));
        return Unit.INSTANCE;
    }

    static final Unit DockItem_si5o8Gs$lambda$62$lambda$61$lambda$60$lambda$59(State $labelOffsetY$delegate, State $labelScale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationY(DockItem_si5o8Gs$lambda$49($labelOffsetY$delegate));
        graphicsLayer.setScaleX(DockItem_si5o8Gs$lambda$51($labelScale$delegate));
        graphicsLayer.setScaleY(DockItem_si5o8Gs$lambda$51($labelScale$delegate));
        return Unit.INSTANCE;
    }

    private static final void BottomDockPreview(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-129217781);
        ComposerKt.sourceInformation($composer2, "C(BottomDockPreview)549@22919L575:BottomDock.kt#e5eupx");
        if ($changed == 0 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-129217781, $changed, -1, "com.example.shellshot.ui.components.BottomDockPreview (BottomDock.kt:548)");
            }
            ThemeKt.ShellShotTheme(false, ComposableSingletons$BottomDockKt.INSTANCE.m6941getLambda$1687026242$app_debug(), $composer2, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.BottomDockKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomDockKt.BottomDockPreview$lambda$64($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void BottomDockDarkPreview(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1157033311);
        ComposerKt.sourceInformation($composer2, "C(BottomDockDarkPreview)571@23671L592:BottomDock.kt#e5eupx");
        if ($changed == 0 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1157033311, $changed, -1, "com.example.shellshot.ui.components.BottomDockDarkPreview (BottomDock.kt:570)");
            }
            ThemeKt.ShellShotTheme(true, ComposableSingletons$BottomDockKt.INSTANCE.m6942getLambda$1969472556$app_debug(), $composer2, 54, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.components.BottomDockKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomDockKt.BottomDockDarkPreview$lambda$65($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
