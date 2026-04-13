package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.vector.VectorProperty;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/* compiled from: VectorPainter.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\u0010\t\u001a$\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0000\u001a*\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u0015\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0017H\u0007¢\u0006\u0002\u0010$\u001a\u0096\u0001\u0010\"\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010(\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2@\u0010)\u001a<\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b+\u0012\b\b(\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b+\u0012\b\b(\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00030*¢\u0006\u0002\b,¢\u0006\u0002\b-H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a \u0001\u0010\"\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010(\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u0002012@\u0010)\u001a<\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b+\u0012\b\b(\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b+\u0012\b\b(\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00030*¢\u0006\u0002\b,¢\u0006\u0002\b-H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001aD\u00104\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001b2\b\b\u0002\u0010(\u001a\u00020\u00012\b\u00106\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u00100\u001a\u000201H\u0000ø\u0001\u0000¢\u0006\u0004\b7\u00108\u001a\u0014\u00109\u001a\u00020\u0019*\u00020\u00192\u0006\u0010:\u001a\u00020\u0005H\u0000\u001a&\u0010;\u001a\u00020\u0003*\u00020<2\u0017\u0010=\u001a\u0013\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00030>¢\u0006\u0002\b?H\u0082\b\u001a&\u0010@\u001a\u00020\u001b*\u00020\u00152\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0002ø\u0001\u0000¢\u0006\u0004\bA\u0010B\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"RootGroupName", "", "RenderVectorGroup", "", "group", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "configs", "", "Landroidx/compose/ui/graphics/vector/VectorConfig;", "(Landroidx/compose/ui/graphics/vector/VectorGroup;Ljava/util/Map;Landroidx/compose/runtime/Composer;II)V", "createColorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "tintColor", "Landroidx/compose/ui/graphics/Color;", "tintBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "createColorFilter-xETnrds", "(JI)Landroidx/compose/ui/graphics/ColorFilter;", "createVectorPainterFromImageVector", "Landroidx/compose/ui/graphics/vector/VectorPainter;", "density", "Landroidx/compose/ui/unit/Density;", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "root", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "obtainViewportSize", "Landroidx/compose/ui/geometry/Size;", "defaultSize", "viewportWidth", "", "viewportHeight", "obtainViewportSize-Pq9zytI", "(JFF)J", "rememberVectorPainter", "image", "(Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/vector/VectorPainter;", "defaultWidth", "Landroidx/compose/ui/unit/Dp;", "defaultHeight", HintConstants.AUTOFILL_HINT_NAME, "content", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/graphics/vector/VectorComposable;", "rememberVectorPainter-mlNsNFs", "(FFFFLjava/lang/String;JILkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "autoMirror", "", "rememberVectorPainter-vIP8VLU", "(FFFFLjava/lang/String;JIZLkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "configureVectorPainter", "viewportSize", "intrinsicColorFilter", "configureVectorPainter-T4PVSW8", "(Landroidx/compose/ui/graphics/vector/VectorPainter;JJLjava/lang/String;Landroidx/compose/ui/graphics/ColorFilter;Z)Landroidx/compose/ui/graphics/vector/VectorPainter;", "createGroupComponent", "currentGroup", "mirror", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "obtainSizePx", "obtainSizePx-VpY3zN4", "(Landroidx/compose/ui/unit/Density;FF)J", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class VectorPainterKt {
    public static final String RootGroupName = "VectorRootGroup";

    @Deprecated(message = "Replace rememberVectorPainter graphicsLayer that consumes the auto mirror flag", replaceWith = @ReplaceWith(expression = "rememberVectorPainter(defaultWidth, defaultHeight, viewportWidth, viewportHeight, name, tintColor, tintBlendMode, false, content)", imports = {"androidx.compose.ui.graphics.vector"}))
    /* renamed from: rememberVectorPainter-mlNsNFs, reason: not valid java name */
    public static final VectorPainter m4874rememberVectorPaintermlNsNFs(float defaultWidth, float defaultHeight, float viewportWidth, float viewportHeight, String name, long tintColor, int tintBlendMode, Function4<? super Float, ? super Float, ? super Composer, ? super Integer, Unit> function4, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -964365210, "C(rememberVectorPainter)P(2:c#ui.unit.Dp,1:c#ui.unit.Dp,7,6,3,5:c#ui.graphics.Color,4:c#ui.graphics.BlendMode)86@3732L207:VectorPainter.kt#huu6hf");
        float viewportWidth2 = (i & 4) != 0 ? Float.NaN : viewportWidth;
        float viewportHeight2 = (i & 8) != 0 ? Float.NaN : viewportHeight;
        String name2 = (i & 16) != 0 ? RootGroupName : name;
        long tintColor2 = (i & 32) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : tintColor;
        int tintBlendMode2 = (i & 64) != 0 ? BlendMode.INSTANCE.m4103getSrcIn0nO6VwU() : tintBlendMode;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-964365210, $changed, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:86)");
        }
        VectorPainter m4875rememberVectorPaintervIP8VLU = m4875rememberVectorPaintervIP8VLU(defaultWidth, defaultHeight, viewportWidth2, viewportHeight2, name2, tintColor2, tintBlendMode2, false, function4, $composer, ($changed & 14) | 12582912 | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (($changed << 3) & 234881024), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m4875rememberVectorPaintervIP8VLU;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x018b, code lost:
    
        if (r32.changed(r3) == false) goto L78;
     */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0215  */
    /* renamed from: rememberVectorPainter-vIP8VLU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.graphics.vector.VectorPainter m4875rememberVectorPaintervIP8VLU(float r22, float r23, float r24, float r25, java.lang.String r26, long r27, int r29, boolean r30, kotlin.jvm.functions.Function4<? super java.lang.Float, ? super java.lang.Float, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instructions count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.VectorPainterKt.m4875rememberVectorPaintervIP8VLU(float, float, float, float, java.lang.String, long, int, boolean, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):androidx.compose.ui.graphics.vector.VectorPainter");
    }

    public static final VectorPainter rememberVectorPainter(ImageVector image, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1413834416, "C(rememberVectorPainter)172@7145L7,174@7229L215:VectorPainter.kt#huu6hf");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1413834416, $changed, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:171)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density density = (Density) consume;
        float val1$iv = image.getGenId();
        float val2$iv = density.getDensity();
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        long key = (v1$iv << 32) | (4294967295L & v2$iv);
        ComposerKt.sourceInformationMarkerStart($composer, -1837470287, "CC(remember):VectorPainter.kt#9igjgp");
        boolean invalid$iv = $composer.changed(key);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            GroupComponent $this$rememberVectorPainter_u24lambda_u245_u24lambda_u244 = new GroupComponent();
            createGroupComponent($this$rememberVectorPainter_u24lambda_u245_u24lambda_u244, image.getRoot());
            Unit unit = Unit.INSTANCE;
            value$iv = createVectorPainterFromImageVector(density, image, $this$rememberVectorPainter_u24lambda_u245_u24lambda_u244);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        VectorPainter vectorPainter = (VectorPainter) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return vectorPainter;
    }

    private static final void mirror(DrawScope $this$mirror, Function1<? super DrawScope, Unit> function1) {
        long pivot$iv = $this$mirror.mo4712getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$mirror.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo4634getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
        try {
            DrawTransform $this$scale_Fgt4K4Q_u24lambda_u242$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
            $this$scale_Fgt4K4Q_u24lambda_u242$iv.mo4641scale0AR0LA0(-1.0f, 1.0f, pivot$iv);
        } catch (Throwable th) {
            th = th;
        }
        try {
            function1.invoke($this$mirror);
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
        } catch (Throwable th2) {
            th = th2;
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
            throw th;
        }
    }

    /* renamed from: obtainSizePx-VpY3zN4, reason: not valid java name */
    private static final long m4872obtainSizePxVpY3zN4(Density $this$obtainSizePx_u2dVpY3zN4, float defaultWidth, float defaultHeight) {
        return SizeKt.Size($this$obtainSizePx_u2dVpY3zN4.mo364toPx0680j_4(defaultWidth), $this$obtainSizePx_u2dVpY3zN4.mo364toPx0680j_4(defaultHeight));
    }

    /* renamed from: obtainViewportSize-Pq9zytI, reason: not valid java name */
    private static final long m4873obtainViewportSizePq9zytI(long defaultSize, float viewportWidth, float viewportHeight) {
        return SizeKt.Size(Float.isNaN(viewportWidth) ? Size.m3991getWidthimpl(defaultSize) : viewportWidth, Float.isNaN(viewportHeight) ? Size.m3988getHeightimpl(defaultSize) : viewportHeight);
    }

    /* renamed from: createColorFilter-xETnrds, reason: not valid java name */
    private static final ColorFilter m4871createColorFilterxETnrds(long tintColor, int tintBlendMode) {
        if (tintColor != 16) {
            return ColorFilter.INSTANCE.m4207tintxETnrds(tintColor, tintBlendMode);
        }
        return null;
    }

    /* renamed from: configureVectorPainter-T4PVSW8$default, reason: not valid java name */
    public static /* synthetic */ VectorPainter m4870configureVectorPainterT4PVSW8$default(VectorPainter vectorPainter, long j, long j2, String str, ColorFilter colorFilter, boolean z, int i, Object obj) {
        String str2;
        boolean z2;
        if ((i & 4) == 0) {
            str2 = str;
        } else {
            str2 = RootGroupName;
        }
        if ((i & 16) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        return m4869configureVectorPainterT4PVSW8(vectorPainter, j, j2, str2, colorFilter, z2);
    }

    /* renamed from: configureVectorPainter-T4PVSW8, reason: not valid java name */
    public static final VectorPainter m4869configureVectorPainterT4PVSW8(VectorPainter $this$configureVectorPainter_u2dT4PVSW8, long defaultSize, long viewportSize, String name, ColorFilter intrinsicColorFilter, boolean autoMirror) {
        $this$configureVectorPainter_u2dT4PVSW8.m4867setSizeuvyYCjk$ui_release(defaultSize);
        $this$configureVectorPainter_u2dT4PVSW8.setAutoMirror$ui_release(autoMirror);
        $this$configureVectorPainter_u2dT4PVSW8.setIntrinsicColorFilter$ui_release(intrinsicColorFilter);
        $this$configureVectorPainter_u2dT4PVSW8.m4868setViewportSizeuvyYCjk$ui_release(viewportSize);
        $this$configureVectorPainter_u2dT4PVSW8.setName$ui_release(name);
        return $this$configureVectorPainter_u2dT4PVSW8;
    }

    public static final VectorPainter createVectorPainterFromImageVector(Density density, ImageVector imageVector, GroupComponent root) {
        long defaultSize = m4872obtainSizePxVpY3zN4(density, imageVector.getDefaultWidth(), imageVector.getDefaultHeight());
        long viewport = m4873obtainViewportSizePq9zytI(defaultSize, imageVector.getViewportWidth(), imageVector.getViewportHeight());
        return m4869configureVectorPainterT4PVSW8(new VectorPainter(root), defaultSize, viewport, imageVector.getName(), m4871createColorFilterxETnrds(imageVector.getTintColor(), imageVector.getTintBlendMode()), imageVector.getAutoMirror());
    }

    public static final GroupComponent createGroupComponent(GroupComponent $this$createGroupComponent, VectorGroup currentGroup) {
        int size = currentGroup.getSize();
        for (int index = 0; index < size; index++) {
            VectorNode vectorNode = currentGroup.get(index);
            if (vectorNode instanceof VectorPath) {
                PathComponent $this$createGroupComponent_u24lambda_u247 = new PathComponent();
                $this$createGroupComponent_u24lambda_u247.setPathData(((VectorPath) vectorNode).getPathData());
                $this$createGroupComponent_u24lambda_u247.m4853setPathFillTypeoQ8Xj4U(((VectorPath) vectorNode).getPathFillType());
                $this$createGroupComponent_u24lambda_u247.setName(((VectorPath) vectorNode).getName());
                $this$createGroupComponent_u24lambda_u247.setFill(((VectorPath) vectorNode).getFill());
                $this$createGroupComponent_u24lambda_u247.setFillAlpha(((VectorPath) vectorNode).getFillAlpha());
                $this$createGroupComponent_u24lambda_u247.setStroke(((VectorPath) vectorNode).getStroke());
                $this$createGroupComponent_u24lambda_u247.setStrokeAlpha(((VectorPath) vectorNode).getStrokeAlpha());
                $this$createGroupComponent_u24lambda_u247.setStrokeLineWidth(((VectorPath) vectorNode).getStrokeLineWidth());
                $this$createGroupComponent_u24lambda_u247.m4854setStrokeLineCapBeK7IIE(((VectorPath) vectorNode).getStrokeLineCap());
                $this$createGroupComponent_u24lambda_u247.m4855setStrokeLineJoinWw9F2mQ(((VectorPath) vectorNode).getStrokeLineJoin());
                $this$createGroupComponent_u24lambda_u247.setStrokeLineMiter(((VectorPath) vectorNode).getStrokeLineMiter());
                $this$createGroupComponent_u24lambda_u247.setTrimPathStart(((VectorPath) vectorNode).getTrimPathStart());
                $this$createGroupComponent_u24lambda_u247.setTrimPathEnd(((VectorPath) vectorNode).getTrimPathEnd());
                $this$createGroupComponent_u24lambda_u247.setTrimPathOffset(((VectorPath) vectorNode).getTrimPathOffset());
                $this$createGroupComponent.insertAt(index, $this$createGroupComponent_u24lambda_u247);
            } else if (vectorNode instanceof VectorGroup) {
                GroupComponent $this$createGroupComponent_u24lambda_u248 = new GroupComponent();
                $this$createGroupComponent_u24lambda_u248.setName(((VectorGroup) vectorNode).getName());
                $this$createGroupComponent_u24lambda_u248.setRotation(((VectorGroup) vectorNode).getRotation());
                $this$createGroupComponent_u24lambda_u248.setScaleX(((VectorGroup) vectorNode).getScaleX());
                $this$createGroupComponent_u24lambda_u248.setScaleY(((VectorGroup) vectorNode).getScaleY());
                $this$createGroupComponent_u24lambda_u248.setTranslationX(((VectorGroup) vectorNode).getTranslationX());
                $this$createGroupComponent_u24lambda_u248.setTranslationY(((VectorGroup) vectorNode).getTranslationY());
                $this$createGroupComponent_u24lambda_u248.setPivotX(((VectorGroup) vectorNode).getPivotX());
                $this$createGroupComponent_u24lambda_u248.setPivotY(((VectorGroup) vectorNode).getPivotY());
                $this$createGroupComponent_u24lambda_u248.setClipPathData(((VectorGroup) vectorNode).getClipPathData());
                createGroupComponent($this$createGroupComponent_u24lambda_u248, (VectorGroup) vectorNode);
                $this$createGroupComponent.insertAt(index, $this$createGroupComponent_u24lambda_u248);
            }
        }
        return $this$createGroupComponent;
    }

    public static final void RenderVectorGroup(final VectorGroup group, Map<String, ? extends VectorConfig> map, Composer $composer, final int $changed, final int i) {
        final Map configs;
        Composer $composer2 = $composer.startRestartGroup(-446179233);
        ComposerKt.sourceInformation($composer2, "C(RenderVectorGroup)P(1):VectorPainter.kt#huu6hf");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(group) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            configs = map;
        } else if (($changed & 48) == 0) {
            configs = map;
            $dirty |= $composer2.changedInstance(configs) ? 32 : 16;
        } else {
            configs = map;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            Map configs2 = i2 != 0 ? MapsKt.emptyMap() : configs;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-446179233, $dirty, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup (VectorPainter.kt:430)");
            }
            Iterator<VectorNode> it = group.iterator();
            while (it.hasNext()) {
                final VectorNode vectorNode = it.next();
                if (vectorNode instanceof VectorPath) {
                    $composer2.startReplaceGroup(-23647808);
                    ComposerKt.sourceInformation($composer2, "434@16086L1719");
                    VectorConfig config = configs2.get(((VectorPath) vectorNode).getName());
                    if (config == null) {
                        config = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$1
                        };
                    }
                    Composer $composer3 = $composer2;
                    VectorComposeKt.m4859Path9cdaXJ4((List) config.getOrDefault(VectorProperty.PathData.INSTANCE, ((VectorPath) vectorNode).getPathData()), ((VectorPath) vectorNode).getPathFillType(), ((VectorPath) vectorNode).getName(), (Brush) config.getOrDefault(VectorProperty.Fill.INSTANCE, ((VectorPath) vectorNode).getFill()), ((Number) config.getOrDefault(VectorProperty.FillAlpha.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getFillAlpha()))).floatValue(), (Brush) config.getOrDefault(VectorProperty.Stroke.INSTANCE, ((VectorPath) vectorNode).getStroke()), ((Number) config.getOrDefault(VectorProperty.StrokeAlpha.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getStrokeAlpha()))).floatValue(), ((Number) config.getOrDefault(VectorProperty.StrokeLineWidth.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getStrokeLineWidth()))).floatValue(), ((VectorPath) vectorNode).getStrokeLineCap(), ((VectorPath) vectorNode).getStrokeLineJoin(), ((VectorPath) vectorNode).getStrokeLineMiter(), ((Number) config.getOrDefault(VectorProperty.TrimPathStart.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getTrimPathStart()))).floatValue(), ((Number) config.getOrDefault(VectorProperty.TrimPathEnd.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getTrimPathEnd()))).floatValue(), ((Number) config.getOrDefault(VectorProperty.TrimPathOffset.INSTANCE, Float.valueOf(((VectorPath) vectorNode).getTrimPathOffset()))).floatValue(), $composer3, 0, 0, 0);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                    it = it;
                    $dirty = $dirty;
                    configs2 = configs2;
                } else {
                    Iterator<VectorNode> it2 = it;
                    int $dirty2 = $dirty;
                    final Map configs3 = configs2;
                    if (vectorNode instanceof VectorGroup) {
                        $composer2.startReplaceGroup(-21815553);
                        ComposerKt.sourceInformation($composer2, "513@19224L88,479@17944L1368");
                        VectorConfig config2 = configs3.get(((VectorGroup) vectorNode).getName());
                        if (config2 == null) {
                            config2 = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$2
                            };
                        }
                        VectorComposeKt.Group(((VectorGroup) vectorNode).getName(), ((Number) config2.getOrDefault(VectorProperty.Rotation.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getRotation()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.PivotX.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getPivotX()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.PivotY.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getPivotY()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.ScaleX.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getScaleX()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.ScaleY.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getScaleY()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.TranslateX.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getTranslationX()))).floatValue(), ((Number) config2.getOrDefault(VectorProperty.TranslateY.INSTANCE, Float.valueOf(((VectorGroup) vectorNode).getTranslationY()))).floatValue(), (List) config2.getOrDefault(VectorProperty.PathData.INSTANCE, ((VectorGroup) vectorNode).getClipPathData()), ComposableLambdaKt.rememberComposableLambda(1450046638, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$1
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
                                ComposerKt.sourceInformation($composer4, "C514@19242L56:VectorPainter.kt#huu6hf");
                                if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1450046638, $changed2, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup.<anonymous> (VectorPainter.kt:514)");
                                }
                                VectorPainterKt.RenderVectorGroup((VectorGroup) VectorNode.this, configs3, $composer4, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer2, 54), $composer2, 805306368, 0);
                        $composer2.endReplaceGroup();
                        it = it2;
                        $dirty = $dirty2;
                        configs2 = configs3;
                    } else {
                        $composer2.startReplaceGroup(-20402883);
                        $composer2.endReplaceGroup();
                        it = it2;
                        $dirty = $dirty2;
                        configs2 = configs3;
                    }
                }
            }
            Map configs4 = configs2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            configs = configs4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$2
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
                    VectorPainterKt.RenderVectorGroup(VectorGroup.this, configs, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
