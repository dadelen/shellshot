package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.graphics.Outline;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.DrawContextKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidGraphicsLayer.android.kt */
@Metadata(d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ¶\u00012\u00020\u0001:\u0002¶\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0085\u0001\u001a\u0002082\u0007\u0010\u0086\u0001\u001a\u00020\u0000H\u0002J\t\u0010\u0087\u0001\u001a\u000208H\u0002J\t\u0010\u0088\u0001\u001a\u000208H\u0002J\u000f\u0010\u0089\u0001\u001a\u000208H\u0000¢\u0006\u0003\b\u008a\u0001J$\u0010\u008b\u0001\u001a\u0002082\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0003\b\u008f\u0001J\u0019\u0010\u0090\u0001\u001a\u0002082\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0000¢\u0006\u0003\b\u0091\u0001J\u000f\u0010\u0092\u0001\u001a\u000208H\u0001¢\u0006\u0003\b\u0093\u0001J\t\u0010\u0094\u0001\u001a\u00020\u0015H\u0002J\t\u0010\u0095\u0001\u001a\u000208H\u0002J\t\u0010\u0096\u0001\u001a\u000208H\u0002JE\u0010\u0097\u0001\u001a\u0002082\u0006\u00103\u001a\u0002042\u0006\u0010C\u001a\u00020D2\u0006\u0010r\u001a\u00020q2\u0018\u0010\u0098\u0001\u001a\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020806¢\u0006\u0002\b9ø\u0001\u0000¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\t\u0010\u009b\u0001\u001a\u000208H\u0002J\t\u0010\u009c\u0001\u001a\u000208H\u0002J\u000f\u0010\u009d\u0001\u001a\u000208H\u0000¢\u0006\u0003\b\u009e\u0001J\t\u0010\u009f\u0001\u001a\u000208H\u0002J5\u0010 \u0001\u001a\u0003H¡\u0001\"\u0005\b\u0000\u0010¡\u00012\u001b\u0010\u0098\u0001\u001a\u0016\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u00020f\u0012\u0005\u0012\u0003H¡\u00010¢\u0001H\u0082\b¢\u0006\u0003\u0010£\u0001J\u0010\u0010¤\u0001\u001a\u0002082\u0007\u0010¥\u0001\u001a\u00020JJ%\u0010¦\u0001\u001a\u0002082\u0006\u0010{\u001a\u00020z2\u0006\u0010r\u001a\u00020qH\u0002ø\u0001\u0000¢\u0006\u0006\b§\u0001\u0010¨\u0001J'\u0010©\u0001\u001a\u0002082\b\b\u0002\u0010{\u001a\u00020O2\b\b\u0002\u0010r\u001a\u00020fø\u0001\u0000¢\u0006\u0006\bª\u0001\u0010¨\u0001J2\u0010«\u0001\u001a\u0002082\b\b\u0002\u0010{\u001a\u00020O2\b\b\u0002\u0010r\u001a\u00020f2\t\b\u0002\u0010¬\u0001\u001a\u00020\bø\u0001\u0000¢\u0006\u0006\b\u00ad\u0001\u0010®\u0001J\u0011\u0010¯\u0001\u001a\u00030°\u0001H\u0086@¢\u0006\u0003\u0010±\u0001J\u0013\u0010²\u0001\u001a\u0002082\b\u0010³\u0001\u001a\u00030´\u0001H\u0002J\u0014\u0010µ\u0001\u001a\u0004\u0018\u00010\u00152\u0007\u0010¥\u0001\u001a\u00020JH\u0002R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00168F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u000b\"\u0004\b\u001e\u0010\rR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\"\u001a\u00020!2\u0006\u0010\u0007\u001a\u00020!8F@FX\u0086\u000e¢\u0006\u0012\u0012\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010*\u001a\u0004\u0018\u00010)2\b\u0010\u0007\u001a\u0004\u0018\u00010)8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R*\u00100\u001a\u00020/2\u0006\u0010\u0007\u001a\u00020/8F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b1\u0010\u0019\"\u0004\b2\u0010\u001bR\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u00105\u001a\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020806¢\u0006\u0002\b9X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010?\u001a\u00020!2\u0006\u0010>\u001a\u00020!@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010&R\u0011\u0010@\u001a\u00020A8F¢\u0006\u0006\u001a\u0004\bB\u0010\u0011R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020DX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010E\u001a\u00020=8F¢\u0006\u0006\u001a\u0004\bF\u0010GR\u000e\u0010H\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u0004\u0018\u00010JX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010K\u001a\u00020A8F¢\u0006\u0006\u001a\u0004\bL\u0010\u0011R\u000e\u0010M\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010P\u001a\u00020O2\u0006\u0010\u0007\u001a\u00020O@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010S\u001a\u0004\bQ\u0010\u0011\"\u0004\bR\u0010\u0013R(\u0010U\u001a\u0004\u0018\u00010T2\b\u0010\u0007\u001a\u0004\u0018\u00010T8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR$\u0010Z\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b[\u0010\u000b\"\u0004\b\\\u0010\rR$\u0010]\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b^\u0010\u000b\"\u0004\b_\u0010\rR$\u0010`\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\ba\u0010\u000b\"\u0004\bb\u0010\rR\u0010\u0010c\u001a\u0004\u0018\u00010JX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010e\u001a\u00020fX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010SR\u0016\u0010g\u001a\u00020OX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010SR$\u0010h\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bi\u0010\u000b\"\u0004\bj\u0010\rR$\u0010k\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bl\u0010\u000b\"\u0004\bm\u0010\rR$\u0010n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bo\u0010\u000b\"\u0004\bp\u0010\rR,\u0010r\u001a\u00020q2\u0006\u0010\u0007\u001a\u00020q@BX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010S\u001a\u0004\bs\u0010\u0011\"\u0004\bt\u0010\u0013R\u0010\u0010u\u001a\u0004\u0018\u00010vX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010w\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\bx\u0010\u0011\"\u0004\by\u0010\u0013R,\u0010{\u001a\u00020z2\u0006\u0010\u0007\u001a\u00020z@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010S\u001a\u0004\b|\u0010\u0011\"\u0004\b}\u0010\u0013R%\u0010~\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\r\u001a\u0004\b\u007f\u0010\u000b\"\u0005\b\u0080\u0001\u0010\rR'\u0010\u0081\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0082\u0001\u0010\u000b\"\u0005\b\u0083\u0001\u0010\rR\u000f\u0010\u0084\u0001\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006·\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "", "impl", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "layerManager", "Landroidx/compose/ui/graphics/layer/LayerManager;", "(Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;Landroidx/compose/ui/graphics/layer/LayerManager;)V", "value", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "()J", "setAmbientShadowColor-8_81llA", "(J)V", "androidOutline", "Landroid/graphics/Outline;", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "cameraDistance", "getCameraDistance", "setCameraDistance", "childDependenciesTracker", "Landroidx/compose/ui/graphics/layer/ChildLayerDependenciesTracker;", "", "clip", "getClip$annotations", "()V", "getClip", "()Z", "setClip", "(Z)V", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "setCompositingStrategy-Wpw9cng", "density", "Landroidx/compose/ui/unit/Density;", "drawBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "getImpl$ui_graphics_release", "()Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "internalOutline", "Landroidx/compose/ui/graphics/Outline;", "<set-?>", "isReleased", "layerId", "", "getLayerId", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "outline", "getOutline", "()Landroidx/compose/ui/graphics/Outline;", "outlineDirty", "outlinePath", "Landroidx/compose/ui/graphics/Path;", "ownerViewId", "getOwnerViewId", "parentLayerUsages", "", "Landroidx/compose/ui/geometry/Offset;", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "J", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "roundRectClipPath", "roundRectCornerRadius", "roundRectOutlineSize", "Landroidx/compose/ui/geometry/Size;", "roundRectOutlineTopLeft", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "shadowElevation", "getShadowElevation", "setShadowElevation", "Landroidx/compose/ui/unit/IntSize;", "size", "getSize-YbymL2g", "setSize-ozmzZPI", "softwareLayerPaint", "Landroidx/compose/ui/graphics/Paint;", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "Landroidx/compose/ui/unit/IntOffset;", "topLeft", "getTopLeft-nOcc-ac", "setTopLeft--gyyYBs", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "usePathForClip", "addSubLayer", "graphicsLayer", "configureOutline", "discardContentIfReleasedAndHaveNoParentLayerUsages", "discardDisplayList", "discardDisplayList$ui_graphics_release", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "parentLayer", "draw$ui_graphics_release", "drawForPersistence", "drawForPersistence$ui_graphics_release", "emulateTrimMemory", "emulateTrimMemory$ui_graphics_release", "obtainAndroidOutline", "onAddedToParentLayer", "onRemovedFromParentLayer", "record", "block", "record-mL-hObY", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;JLkotlin/jvm/functions/Function1;)V", "recordInternal", "recreateDisplayListIfNeeded", "release", "release$ui_graphics_release", "resetOutlineParams", "resolveOutlinePosition", "T", "Lkotlin/Function2;", "(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "setPathOutline", "path", "setPosition", "setPosition-VbeCjmY", "(JJ)V", "setRectOutline", "setRectOutline-tz77jQw", "setRoundRectOutline", "cornerRadius", "setRoundRectOutline-TNW_H78", "(JJF)V", "toImageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformCanvas", "androidCanvas", "Landroid/graphics/Canvas;", "updatePathOutline", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class GraphicsLayer {
    private static final LayerSnapshotImpl SnapshotImpl = LayerSnapshotV28.INSTANCE;
    private Outline androidOutline;
    private final GraphicsLayerImpl impl;
    private androidx.compose.ui.graphics.Outline internalOutline;
    private boolean isReleased;
    private final LayerManager layerManager;
    private Path outlinePath;
    private int parentLayerUsages;
    private long pivotOffset;
    private Path roundRectClipPath;
    private float roundRectCornerRadius;
    private long size;
    private Paint softwareLayerPaint;
    private long topLeft;
    private boolean usePathForClip;
    private Density density = DrawContextKt.getDefaultDensity();
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;
    private Function1<? super DrawScope, Unit> drawBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$drawBlock$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
            invoke2(drawScope);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DrawScope $this$null) {
        }
    };
    private boolean outlineDirty = true;
    private long roundRectOutlineTopLeft = Offset.INSTANCE.m3938getZeroF1C5BW0();
    private long roundRectOutlineSize = Size.INSTANCE.m3999getUnspecifiedNHjbRc();
    private final ChildLayerDependenciesTracker childDependenciesTracker = new ChildLayerDependenciesTracker();

    public static /* synthetic */ void getClip$annotations() {
    }

    public GraphicsLayer(GraphicsLayerImpl impl, LayerManager layerManager) {
        this.impl = impl;
        this.layerManager = layerManager;
        this.impl.setClip(false);
        this.topLeft = IntOffset.INSTANCE.m6764getZeronOccac();
        this.size = IntSize.INSTANCE.m6801getZeroYbymL2g();
        this.pivotOffset = Offset.INSTANCE.m3937getUnspecifiedF1C5BW0();
    }

    /* renamed from: getImpl$ui_graphics_release, reason: from getter */
    public final GraphicsLayerImpl getImpl() {
        return this.impl;
    }

    /* renamed from: isReleased, reason: from getter */
    public final boolean getIsReleased() {
        return this.isReleased;
    }

    /* renamed from: getCompositingStrategy-ke2Ky5w, reason: not valid java name */
    public final int m4802getCompositingStrategyke2Ky5w() {
        return this.impl.getCompositingStrategy();
    }

    /* renamed from: setCompositingStrategy-Wpw9cng, reason: not valid java name */
    public final void m4810setCompositingStrategyWpw9cng(int value) {
        if (!CompositingStrategy.m4789equalsimpl0(this.impl.getCompositingStrategy(), value)) {
            this.impl.mo4823setCompositingStrategyWpw9cng(value);
        }
    }

    /* renamed from: getTopLeft-nOcc-ac, reason: not valid java name and from getter */
    public final long getTopLeft() {
        return this.topLeft;
    }

    /* renamed from: setTopLeft--gyyYBs, reason: not valid java name */
    public final void m4815setTopLeftgyyYBs(long value) {
        if (!IntOffset.m6753equalsimpl0(this.topLeft, value)) {
            this.topLeft = value;
            m4796setPositionVbeCjmY(value, this.size);
        }
    }

    /* renamed from: getSize-YbymL2g, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    /* renamed from: setSize-ozmzZPI, reason: not valid java name */
    private final void m4799setSizeozmzZPI(long value) {
        if (!IntSize.m6794equalsimpl0(this.size, value)) {
            this.size = value;
            m4796setPositionVbeCjmY(this.topLeft, value);
            long $this$isUnspecified$iv = this.roundRectOutlineSize;
            if ($this$isUnspecified$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
                this.outlineDirty = true;
                configureOutline();
            }
        }
    }

    public final float getAlpha() {
        return this.impl.getAlpha();
    }

    public final void setAlpha(float value) {
        if (!(this.impl.getAlpha() == value)) {
            this.impl.setAlpha(value);
        }
    }

    /* renamed from: getBlendMode-0nO6VwU, reason: not valid java name */
    public final int m4801getBlendMode0nO6VwU() {
        return this.impl.getBlendMode();
    }

    /* renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m4809setBlendModes9anfk8(int value) {
        if (!BlendMode.m4074equalsimpl0(this.impl.getBlendMode(), value)) {
            this.impl.mo4822setBlendModes9anfk8(value);
        }
    }

    public final ColorFilter getColorFilter() {
        return this.impl.getColorFilter();
    }

    public final void setColorFilter(ColorFilter value) {
        if (!Intrinsics.areEqual(this.impl.getColorFilter(), value)) {
            this.impl.setColorFilter(value);
        }
    }

    /* renamed from: getPivotOffset-F1C5BW0, reason: not valid java name and from getter */
    public final long getPivotOffset() {
        return this.pivotOffset;
    }

    /* renamed from: setPivotOffset-k-4lQ0M, reason: not valid java name */
    public final void m4811setPivotOffsetk4lQ0M(long value) {
        if (!Offset.m3919equalsimpl0(this.pivotOffset, value)) {
            this.pivotOffset = value;
            this.impl.mo4824setPivotOffsetk4lQ0M(value);
        }
    }

    public final float getScaleX() {
        return this.impl.getScaleX();
    }

    public final void setScaleX(float value) {
        if (!(this.impl.getScaleX() == value)) {
            this.impl.setScaleX(value);
        }
    }

    public final float getScaleY() {
        return this.impl.getScaleY();
    }

    public final void setScaleY(float value) {
        if (!(this.impl.getScaleY() == value)) {
            this.impl.setScaleY(value);
        }
    }

    public final float getTranslationX() {
        return this.impl.getTranslationX();
    }

    public final void setTranslationX(float value) {
        if (!(this.impl.getTranslationX() == value)) {
            this.impl.setTranslationX(value);
        }
    }

    public final float getTranslationY() {
        return this.impl.getTranslationY();
    }

    public final void setTranslationY(float value) {
        if (!(this.impl.getTranslationY() == value)) {
            this.impl.setTranslationY(value);
        }
    }

    public final float getShadowElevation() {
        return this.impl.getShadowElevation();
    }

    public final void setShadowElevation(float value) {
        if (!(this.impl.getShadowElevation() == value)) {
            this.impl.setShadowElevation(value);
            this.impl.setClip(getClip() || value > 0.0f);
            this.outlineDirty = true;
            configureOutline();
        }
    }

    public final float getRotationX() {
        return this.impl.getRotationX();
    }

    public final void setRotationX(float value) {
        if (!(this.impl.getRotationX() == value)) {
            this.impl.setRotationX(value);
        }
    }

    public final float getRotationY() {
        return this.impl.getRotationY();
    }

    public final void setRotationY(float value) {
        if (!(this.impl.getRotationY() == value)) {
            this.impl.setRotationY(value);
        }
    }

    public final float getRotationZ() {
        return this.impl.getRotationZ();
    }

    public final void setRotationZ(float value) {
        if (!(this.impl.getRotationZ() == value)) {
            this.impl.setRotationZ(value);
        }
    }

    public final float getCameraDistance() {
        return this.impl.getCameraDistance();
    }

    public final void setCameraDistance(float value) {
        if (!(this.impl.getCameraDistance() == value)) {
            this.impl.setCameraDistance(value);
        }
    }

    public final boolean getClip() {
        return this.impl.getClip();
    }

    public final void setClip(boolean value) {
        if (this.impl.getClip() != value) {
            this.impl.setClip(value);
            this.outlineDirty = true;
            configureOutline();
        }
    }

    public final RenderEffect getRenderEffect() {
        return this.impl.getRenderEffect();
    }

    public final void setRenderEffect(RenderEffect value) {
        if (!Intrinsics.areEqual(this.impl.getRenderEffect(), value)) {
            this.impl.setRenderEffect(value);
        }
    }

    /* renamed from: setPosition-VbeCjmY, reason: not valid java name */
    private final void m4796setPositionVbeCjmY(long topLeft, long size) {
        this.impl.mo4825setPositionH0pRuoY(IntOffset.m6754getXimpl(topLeft), IntOffset.m6755getYimpl(topLeft), size);
    }

    /* renamed from: record-mL-hObY, reason: not valid java name */
    public final void m4807recordmLhObY(Density density, LayoutDirection layoutDirection, long size, Function1<? super DrawScope, Unit> block) {
        m4799setSizeozmzZPI(size);
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.drawBlock = block;
        this.impl.setInvalidated(true);
        recordInternal();
    }

    private final void recordInternal() {
        ChildLayerDependenciesTracker this_$iv = this.childDependenciesTracker;
        this_$iv.oldDependency = this_$iv.dependency;
        MutableScatterSet currentSet$iv = this_$iv.dependenciesSet;
        if (currentSet$iv != null && currentSet$iv.isNotEmpty()) {
            MutableScatterSet it$iv = this_$iv.oldDependenciesSet;
            if (it$iv == null) {
                it$iv = ScatterSetKt.mutableScatterSetOf();
                this_$iv.oldDependenciesSet = it$iv;
            }
            it$iv.addAll(currentSet$iv);
            currentSet$iv.clear();
        }
        this_$iv.trackingInProgress = true;
        this.impl.record(this.density, this.layoutDirection, this, this.drawBlock);
        this_$iv.trackingInProgress = false;
        GraphicsLayer it = this_$iv.oldDependency;
        if (it != null) {
            it.onRemovedFromParentLayer();
        }
        MutableScatterSet oldSet$iv = this_$iv.oldDependenciesSet;
        if (oldSet$iv != null && oldSet$iv.isNotEmpty()) {
            MutableScatterSet this_$iv$iv = oldSet$iv;
            Object[] k$iv$iv = this_$iv$iv.elements;
            long[] m$iv$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            if (0 <= lastIndex$iv$iv$iv) {
                while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                    ChildLayerDependenciesTracker this_$iv2 = this_$iv;
                    if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                        for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull != 0) {
                                int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                ((GraphicsLayer) k$iv$iv[index$iv$iv$iv]).onRemovedFromParentLayer();
                            }
                            slot$iv$iv$iv >>= 8;
                        }
                        if (bitCount$iv$iv$iv != 8) {
                            break;
                        }
                    }
                    if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                        break;
                    }
                    i$iv$iv$iv++;
                    this_$iv = this_$iv2;
                }
            }
            oldSet$iv.clear();
        }
    }

    private final void addSubLayer(GraphicsLayer graphicsLayer) {
        if (this.childDependenciesTracker.onDependencyAdded(graphicsLayer)) {
            graphicsLayer.onAddedToParentLayer();
        }
    }

    private final void transformCanvas(Canvas androidCanvas) {
        Canvas androidCanvas2;
        float left = IntOffset.m6754getXimpl(this.topLeft);
        float top = IntOffset.m6755getYimpl(this.topLeft);
        float right = IntOffset.m6754getXimpl(this.topLeft) + IntSize.m6796getWidthimpl(this.size);
        float bottom = IntOffset.m6755getYimpl(this.topLeft) + IntSize.m6795getHeightimpl(this.size);
        float layerAlpha = getAlpha();
        ColorFilter layerColorFilter = getColorFilter();
        int layerBlendMode = m4801getBlendMode0nO6VwU();
        boolean useSaveLayer = layerAlpha < 1.0f || !BlendMode.m4074equalsimpl0(layerBlendMode, BlendMode.INSTANCE.m4105getSrcOver0nO6VwU()) || layerColorFilter != null || CompositingStrategy.m4789equalsimpl0(m4802getCompositingStrategyke2Ky5w(), CompositingStrategy.INSTANCE.m4795getOffscreenke2Ky5w());
        if (useSaveLayer) {
            Paint it = this.softwareLayerPaint;
            if (it == null) {
                it = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = it;
            }
            Paint it2 = it;
            it2.setAlpha(layerAlpha);
            it2.mo4041setBlendModes9anfk8(layerBlendMode);
            it2.setColorFilter(layerColorFilter);
            Paint paint = it;
            androidCanvas2 = androidCanvas;
            androidCanvas2.saveLayer(left, top, right, bottom, paint.getInternalPaint());
        } else {
            androidCanvas2 = androidCanvas;
            androidCanvas2.save();
        }
        androidCanvas2.translate(left, top);
        androidCanvas2.concat(this.impl.calculateMatrix());
    }

    public final void drawForPersistence$ui_graphics_release(androidx.compose.ui.graphics.Canvas canvas) {
        if (AndroidCanvas_androidKt.getNativeCanvas(canvas).isHardwareAccelerated()) {
            recreateDisplayListIfNeeded();
            this.impl.draw(canvas);
        }
    }

    private final void recreateDisplayListIfNeeded() {
        if (!this.impl.getHasDisplayList()) {
            try {
                recordInternal();
            } catch (Throwable th) {
            }
        }
    }

    public final void draw$ui_graphics_release(androidx.compose.ui.graphics.Canvas canvas, GraphicsLayer parentLayer) {
        if (this.isReleased) {
            return;
        }
        recreateDisplayListIfNeeded();
        configureOutline();
        boolean willClipPath = true;
        boolean useZ = getShadowElevation() > 0.0f;
        if (useZ) {
            canvas.enableZ();
        }
        Canvas androidCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        boolean softwareRendered = !androidCanvas.isHardwareAccelerated();
        if (softwareRendered) {
            androidCanvas.save();
            transformCanvas(androidCanvas);
        }
        if (!this.usePathForClip && (!softwareRendered || !getClip())) {
            willClipPath = false;
        }
        if (willClipPath) {
            canvas.save();
            androidx.compose.ui.graphics.Outline tmpOutline = getOutline();
            if (tmpOutline instanceof Outline.Rectangle) {
                androidx.compose.ui.graphics.Canvas.m4136clipRectmtrdDE$default(canvas, tmpOutline.getRect(), 0, 2, null);
            } else if (tmpOutline instanceof Outline.Rounded) {
                Path rRectPath = this.roundRectClipPath;
                if (rRectPath != null) {
                    rRectPath.rewind();
                } else {
                    rRectPath = AndroidPath_androidKt.Path();
                    this.roundRectClipPath = rRectPath;
                }
                Path.addRoundRect$default(rRectPath, ((Outline.Rounded) tmpOutline).getRoundRect(), null, 2, null);
                androidx.compose.ui.graphics.Canvas.m4134clipPathmtrdDE$default(canvas, rRectPath, 0, 2, null);
            } else if (tmpOutline instanceof Outline.Generic) {
                androidx.compose.ui.graphics.Canvas.m4134clipPathmtrdDE$default(canvas, ((Outline.Generic) tmpOutline).getPath(), 0, 2, null);
            }
        }
        if (parentLayer != null) {
            parentLayer.addSubLayer(this);
        }
        this.impl.draw(canvas);
        if (willClipPath) {
            canvas.restore();
        }
        if (useZ) {
            canvas.disableZ();
        }
        if (softwareRendered) {
            androidCanvas.restore();
        }
    }

    private final void onAddedToParentLayer() {
        this.parentLayerUsages++;
    }

    private final void onRemovedFromParentLayer() {
        this.parentLayerUsages--;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    private final void configureOutline() {
        long outlineSize$iv;
        if (this.outlineDirty) {
            boolean outlineIsNeeded = getClip() || getShadowElevation() > 0.0f;
            if (!outlineIsNeeded) {
                this.impl.setOutline(null);
            } else {
                Path tmpPath = this.outlinePath;
                if (tmpPath != null) {
                    android.graphics.Outline androidOutline = updatePathOutline(tmpPath);
                    setAlpha(getAlpha());
                    this.impl.setOutline(androidOutline);
                } else {
                    android.graphics.Outline roundRectOutline = obtainAndroidOutline();
                    long layerSize$iv = IntSizeKt.m6808toSizeozmzZPI(this.size);
                    long rRectTopLeft$iv = this.roundRectOutlineTopLeft;
                    long rRectSize$iv = this.roundRectOutlineSize;
                    if (rRectSize$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
                        outlineSize$iv = layerSize$iv;
                    } else {
                        outlineSize$iv = rRectSize$iv;
                    }
                    long outlineSize = outlineSize$iv;
                    float $this$fastRoundToInt$iv = Offset.m3922getXimpl(rRectTopLeft$iv);
                    int round = Math.round($this$fastRoundToInt$iv);
                    float $this$fastRoundToInt$iv2 = Offset.m3923getYimpl(rRectTopLeft$iv);
                    int round2 = Math.round($this$fastRoundToInt$iv2);
                    float $this$fastRoundToInt$iv3 = Offset.m3922getXimpl(rRectTopLeft$iv) + Size.m3991getWidthimpl(outlineSize);
                    int round3 = Math.round($this$fastRoundToInt$iv3);
                    float $this$fastRoundToInt$iv4 = Offset.m3923getYimpl(rRectTopLeft$iv) + Size.m3988getHeightimpl(outlineSize);
                    roundRectOutline.setRoundRect(round, round2, round3, Math.round($this$fastRoundToInt$iv4), this.roundRectCornerRadius);
                    roundRectOutline.setAlpha(getAlpha());
                    this.impl.setOutline(roundRectOutline);
                }
            }
        }
        this.outlineDirty = false;
    }

    private final <T> T resolveOutlinePosition(Function2<? super Offset, ? super Size, ? extends T> block) {
        long outlineSize;
        long layerSize = IntSizeKt.m6808toSizeozmzZPI(this.size);
        long rRectTopLeft = this.roundRectOutlineTopLeft;
        long rRectSize = this.roundRectOutlineSize;
        if (rRectSize == InlineClassHelperKt.UnspecifiedPackedFloats) {
            outlineSize = layerSize;
        } else {
            outlineSize = rRectSize;
        }
        return block.invoke(Offset.m3911boximpl(rRectTopLeft), Size.m3979boximpl(outlineSize));
    }

    private final android.graphics.Outline updatePathOutline(Path path) {
        android.graphics.Outline resultOutline = obtainAndroidOutline();
        OutlineVerificationHelper.INSTANCE.setPath(resultOutline, path);
        this.usePathForClip = !resultOutline.canClip();
        this.outlinePath = path;
        return resultOutline;
    }

    private final android.graphics.Outline obtainAndroidOutline() {
        android.graphics.Outline outline = this.androidOutline;
        if (outline != null) {
            return outline;
        }
        android.graphics.Outline it = new android.graphics.Outline();
        this.androidOutline = it;
        return it;
    }

    public final void release$ui_graphics_release() {
        if (!this.isReleased) {
            this.isReleased = true;
            discardContentIfReleasedAndHaveNoParentLayerUsages();
        }
    }

    private final void discardContentIfReleasedAndHaveNoParentLayerUsages() {
        if (this.isReleased && this.parentLayerUsages == 0) {
            if (this.layerManager != null) {
                this.layerManager.release(this);
            } else {
                discardDisplayList$ui_graphics_release();
            }
        }
    }

    public final void discardDisplayList$ui_graphics_release() {
        MutableScatterSet it$iv;
        ChildLayerDependenciesTracker this_$iv = this.childDependenciesTracker;
        int $i$f$removeDependencies = 0;
        GraphicsLayer it$iv2 = this_$iv.dependency;
        if (it$iv2 != null) {
            it$iv2.onRemovedFromParentLayer();
            this_$iv.dependency = null;
        }
        MutableScatterSet it$iv3 = this_$iv.dependenciesSet;
        if (it$iv3 != null) {
            int i = 0;
            MutableScatterSet this_$iv$iv = it$iv3;
            Object[] k$iv$iv = this_$iv$iv.elements;
            long[] m$iv$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            if (0 <= lastIndex$iv$iv$iv) {
                while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                    ChildLayerDependenciesTracker this_$iv2 = this_$iv;
                    int $i$f$removeDependencies2 = $i$f$removeDependencies;
                    it$iv = it$iv3;
                    int i2 = i;
                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                        for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                            if (value$iv$iv$iv$iv < 128) {
                                int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                GraphicsLayer it = (GraphicsLayer) k$iv$iv[index$iv$iv$iv];
                                it.onRemovedFromParentLayer();
                            }
                            slot$iv$iv$iv >>= 8;
                        }
                        if (bitCount$iv$iv$iv != 8) {
                            break;
                        }
                    }
                    if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                        break;
                    }
                    i$iv$iv$iv++;
                    it$iv3 = it$iv;
                    i = i2;
                    this_$iv = this_$iv2;
                    $i$f$removeDependencies = $i$f$removeDependencies2;
                }
            } else {
                it$iv = it$iv3;
            }
            it$iv.clear();
        }
        this.impl.discardDisplayList();
    }

    public final void emulateTrimMemory$ui_graphics_release() {
        this.impl.discardDisplayList();
    }

    public final long getLayerId() {
        return this.impl.getLayerId();
    }

    public final long getOwnerViewId() {
        return this.impl.getOwnerId();
    }

    public final androidx.compose.ui.graphics.Outline getOutline() {
        long outlineSize$iv;
        androidx.compose.ui.graphics.Outline tmpOutline = this.internalOutline;
        Path tmpPath = this.outlinePath;
        if (tmpOutline != null) {
            return tmpOutline;
        }
        if (tmpPath != null) {
            Outline.Generic it = new Outline.Generic(tmpPath);
            this.internalOutline = it;
            return it;
        }
        long layerSize$iv = IntSizeKt.m6808toSizeozmzZPI(this.size);
        long rRectTopLeft$iv = this.roundRectOutlineTopLeft;
        long rRectSize$iv = this.roundRectOutlineSize;
        if (rRectSize$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
            outlineSize$iv = layerSize$iv;
        } else {
            outlineSize$iv = rRectSize$iv;
        }
        long outlineSize = outlineSize$iv;
        float left = Offset.m3922getXimpl(rRectTopLeft$iv);
        float top = Offset.m3923getYimpl(rRectTopLeft$iv);
        float right = left + Size.m3991getWidthimpl(outlineSize);
        float bottom = top + Size.m3988getHeightimpl(outlineSize);
        float cornerRadius = this.roundRectCornerRadius;
        androidx.compose.ui.graphics.Outline it2 = cornerRadius > 0.0f ? new Outline.Rounded(RoundRectKt.m3976RoundRectgG7oq9Y(left, top, right, bottom, CornerRadiusKt.CornerRadius$default(cornerRadius, 0.0f, 2, null))) : new Outline.Rectangle(new Rect(left, top, right, bottom));
        this.internalOutline = it2;
        return it2;
    }

    private final void resetOutlineParams() {
        this.internalOutline = null;
        this.outlinePath = null;
        this.roundRectOutlineSize = Size.INSTANCE.m3999getUnspecifiedNHjbRc();
        this.roundRectOutlineTopLeft = Offset.INSTANCE.m3938getZeroF1C5BW0();
        this.roundRectCornerRadius = 0.0f;
        this.outlineDirty = true;
        this.usePathForClip = false;
    }

    public final void setPathOutline(Path path) {
        resetOutlineParams();
        this.outlinePath = path;
        configureOutline();
    }

    /* renamed from: setRoundRectOutline-TNW_H78, reason: not valid java name */
    public final void m4813setRoundRectOutlineTNW_H78(long topLeft, long size, float cornerRadius) {
        if (Offset.m3919equalsimpl0(this.roundRectOutlineTopLeft, topLeft) && Size.m3987equalsimpl0(this.roundRectOutlineSize, size)) {
            if ((this.roundRectCornerRadius == cornerRadius) && this.outlinePath == null) {
                return;
            }
        }
        resetOutlineParams();
        this.roundRectOutlineTopLeft = topLeft;
        this.roundRectOutlineSize = size;
        this.roundRectCornerRadius = cornerRadius;
        configureOutline();
    }

    /* renamed from: setRectOutline-tz77jQw$default, reason: not valid java name */
    public static /* synthetic */ void m4797setRectOutlinetz77jQw$default(GraphicsLayer graphicsLayer, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.INSTANCE.m3938getZeroF1C5BW0();
        }
        if ((i & 2) != 0) {
            j2 = Size.INSTANCE.m3999getUnspecifiedNHjbRc();
        }
        graphicsLayer.m4812setRectOutlinetz77jQw(j, j2);
    }

    /* renamed from: setRectOutline-tz77jQw, reason: not valid java name */
    public final void m4812setRectOutlinetz77jQw(long topLeft, long size) {
        m4813setRoundRectOutlineTNW_H78(topLeft, size, 0.0f);
    }

    /* renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name */
    public final long m4800getAmbientShadowColor0d7_KjU() {
        return this.impl.getAmbientShadowColor();
    }

    /* renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
    public final void m4808setAmbientShadowColor8_81llA(long value) {
        if (!Color.m4164equalsimpl0(value, this.impl.getAmbientShadowColor())) {
            this.impl.mo4821setAmbientShadowColor8_81llA(value);
        }
    }

    /* renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name */
    public final long m4805getSpotShadowColor0d7_KjU() {
        return this.impl.getSpotShadowColor();
    }

    /* renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
    public final void m4814setSpotShadowColor8_81llA(long value) {
        if (!Color.m4164equalsimpl0(value, this.impl.getSpotShadowColor())) {
            this.impl.mo4826setSpotShadowColor8_81llA(value);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object toImageBitmap(kotlin.coroutines.Continuation<? super androidx.compose.ui.graphics.ImageBitmap> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1 r0 = (androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1 r0 = new androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L2c:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r6
            goto L41
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
            androidx.compose.ui.graphics.layer.LayerSnapshotImpl r3 = androidx.compose.ui.graphics.layer.GraphicsLayer.SnapshotImpl
            r4 = 1
            r0.label = r4
            java.lang.Object r2 = r3.toBitmap(r2, r0)
            if (r2 != r1) goto L41
            return r1
        L41:
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            androidx.compose.ui.graphics.ImageBitmap r1 = androidx.compose.ui.graphics.AndroidImageBitmap_androidKt.asImageBitmap(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsLayer.toImageBitmap(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
