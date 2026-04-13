package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GraphicsLayerScope.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010k\u001a\u00020lJ\r\u0010m\u001a\u00020lH\u0000¢\u0006\u0002\bnR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR,\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\tR$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0014@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R,\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u001a@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0007R\u0014\u0010#\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0007R\u001a\u0010%\u001a\u00020&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001d\"\u0004\b4\u0010\u001fR(\u00107\u001a\u0004\u0018\u0001062\b\u00105\u001a\u0004\u0018\u000106@AX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R(\u0010=\u001a\u0004\u0018\u00010<2\b\u0010\u0003\u001a\u0004\u0018\u00010<@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010B\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0007\"\u0004\bD\u0010\tR$\u0010E\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0007\"\u0004\bG\u0010\tR$\u0010H\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0007\"\u0004\bJ\u0010\tR$\u0010K\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0007\"\u0004\bM\u0010\tR$\u0010N\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0007\"\u0004\bP\u0010\tR$\u0010Q\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0007\"\u0004\bS\u0010\tR$\u0010U\u001a\u00020T2\u0006\u0010\u0003\u001a\u00020T@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\"\u0010Z\u001a\u00020[X\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\\\u0010\r\"\u0004\b]\u0010\u000fR,\u0010^\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b_\u0010\r\"\u0004\b`\u0010\u000fR,\u0010b\u001a\u00020a2\u0006\u0010\u0003\u001a\u00020a@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\bc\u0010\r\"\u0004\bd\u0010\u000fR$\u0010e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010\u0007\"\u0004\bg\u0010\tR$\u0010h\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u0007\"\u0004\bj\u0010\t\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006o"}, d2 = {"Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "()V", "value", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "()J", "setAmbientShadowColor-8_81llA", "(J)V", "J", "cameraDistance", "getCameraDistance", "setCameraDistance", "", "clip", "getClip", "()Z", "setClip", "(Z)V", "Landroidx/compose/ui/graphics/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy--NrFUSI", "()I", "setCompositingStrategy-aDBOjCE", "(I)V", "I", "density", "getDensity", "fontScale", "getFontScale", "graphicsDensity", "Landroidx/compose/ui/unit/Density;", "getGraphicsDensity$ui_release", "()Landroidx/compose/ui/unit/Density;", "setGraphicsDensity$ui_release", "(Landroidx/compose/ui/unit/Density;)V", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection$ui_release", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection$ui_release", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "mutatedFields", "", "getMutatedFields$ui_release", "setMutatedFields$ui_release", "<set-?>", "Landroidx/compose/ui/graphics/Outline;", "outline", "getOutline$ui_release", "()Landroidx/compose/ui/graphics/Outline;", "setOutline$ui_release", "(Landroidx/compose/ui/graphics/Outline;)V", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "shadowElevation", "getShadowElevation", "setShadowElevation", "Landroidx/compose/ui/graphics/Shape;", "shape", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "size", "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "setSize-uvyYCjk", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin", "getTransformOrigin-SzJe1aQ", "setTransformOrigin-__ExYCQ", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "reset", "", "updateOutline", "updateOutline$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ReusableGraphicsLayerScope implements GraphicsLayerScope {
    public static final int $stable = 0;
    private boolean clip;
    private int mutatedFields;
    private Outline outline;
    private RenderEffect renderEffect;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float shadowElevation;
    private float translationX;
    private float translationY;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float alpha = 1.0f;
    private long ambientShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
    private long spotShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
    private float cameraDistance = 8.0f;
    private long transformOrigin = TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ();
    private Shape shape = RectangleShapeKt.getRectangleShape();
    private int compositingStrategy = CompositingStrategy.INSTANCE.m4251getAutoNrFUSI();
    private long size = Size.INSTANCE.m3999getUnspecifiedNHjbRc();
    private Density graphicsDensity = DensityKt.Density$default(1.0f, 0.0f, 2, null);
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;

    /* renamed from: getMutatedFields$ui_release, reason: from getter */
    public final int getMutatedFields() {
        return this.mutatedFields;
    }

    public final void setMutatedFields$ui_release(int i) {
        this.mutatedFields = i;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getScaleX() {
        return this.scaleX;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setScaleX(float value) {
        if (!(this.scaleX == value)) {
            this.mutatedFields |= 1;
            this.scaleX = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getScaleY() {
        return this.scaleY;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setScaleY(float value) {
        if (!(this.scaleY == value)) {
            this.mutatedFields |= 2;
            this.scaleY = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setAlpha(float value) {
        if (!(this.alpha == value)) {
            this.mutatedFields |= 4;
            this.alpha = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getTranslationX() {
        return this.translationX;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setTranslationX(float value) {
        if (!(this.translationX == value)) {
            this.mutatedFields |= 8;
            this.translationX = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getTranslationY() {
        return this.translationY;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setTranslationY(float value) {
        if (!(this.translationY == value)) {
            this.mutatedFields |= 16;
            this.translationY = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getShadowElevation() {
        return this.shadowElevation;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setShadowElevation(float value) {
        if (!(this.shadowElevation == value)) {
            this.mutatedFields |= 32;
            this.shadowElevation = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: getAmbientShadowColor-0d7_KjU, reason: from getter */
    public long getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: setAmbientShadowColor-8_81llA */
    public void mo4352setAmbientShadowColor8_81llA(long value) {
        if (!Color.m4164equalsimpl0(this.ambientShadowColor, value)) {
            this.mutatedFields |= 64;
            this.ambientShadowColor = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: getSpotShadowColor-0d7_KjU, reason: from getter */
    public long getSpotShadowColor() {
        return this.spotShadowColor;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: setSpotShadowColor-8_81llA */
    public void mo4354setSpotShadowColor8_81llA(long value) {
        if (!Color.m4164equalsimpl0(this.spotShadowColor, value)) {
            this.mutatedFields |= 128;
            this.spotShadowColor = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getRotationX() {
        return this.rotationX;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRotationX(float value) {
        if (!(this.rotationX == value)) {
            this.mutatedFields |= 256;
            this.rotationX = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getRotationY() {
        return this.rotationY;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRotationY(float value) {
        if (!(this.rotationY == value)) {
            this.mutatedFields |= 512;
            this.rotationY = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getRotationZ() {
        return this.rotationZ;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRotationZ(float value) {
        if (!(this.rotationZ == value)) {
            this.mutatedFields |= 1024;
            this.rotationZ = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getCameraDistance() {
        return this.cameraDistance;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setCameraDistance(float value) {
        if (!(this.cameraDistance == value)) {
            this.mutatedFields |= 2048;
            this.cameraDistance = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: getTransformOrigin-SzJe1aQ, reason: from getter */
    public long getTransformOrigin() {
        return this.transformOrigin;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: setTransformOrigin-__ExYCQ */
    public void mo4355setTransformOrigin__ExYCQ(long value) {
        if (!TransformOrigin.m4547equalsimpl0(this.transformOrigin, value)) {
            this.mutatedFields |= 4096;
            this.transformOrigin = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public Shape getShape() {
        return this.shape;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setShape(Shape value) {
        if (!Intrinsics.areEqual(this.shape, value)) {
            this.mutatedFields |= 8192;
            this.shape = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public boolean getClip() {
        return this.clip;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setClip(boolean value) {
        if (this.clip != value) {
            this.mutatedFields |= 16384;
            this.clip = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: getCompositingStrategy--NrFUSI, reason: from getter */
    public int getCompositingStrategy() {
        return this.compositingStrategy;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: setCompositingStrategy-aDBOjCE */
    public void mo4353setCompositingStrategyaDBOjCE(int value) {
        if (!CompositingStrategy.m4247equalsimpl0(this.compositingStrategy, value)) {
            this.mutatedFields |= 32768;
            this.compositingStrategy = value;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* renamed from: getSize-NH-jbRc, reason: from getter */
    public long getSize() {
        return this.size;
    }

    /* renamed from: setSize-uvyYCjk, reason: not valid java name */
    public void m4474setSizeuvyYCjk(long j) {
        this.size = j;
    }

    /* renamed from: getGraphicsDensity$ui_release, reason: from getter */
    public final Density getGraphicsDensity() {
        return this.graphicsDensity;
    }

    public final void setGraphicsDensity$ui_release(Density density) {
        this.graphicsDensity = density;
    }

    /* renamed from: getLayoutDirection$ui_release, reason: from getter */
    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    public final void setLayoutDirection$ui_release(LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.graphicsDensity.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.graphicsDensity.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRenderEffect(RenderEffect value) {
        if (!Intrinsics.areEqual(this.renderEffect, value)) {
            this.mutatedFields |= 131072;
            this.renderEffect = value;
        }
    }

    /* renamed from: getOutline$ui_release, reason: from getter */
    public final Outline getOutline() {
        return this.outline;
    }

    public final void setOutline$ui_release(Outline outline) {
        this.outline = outline;
    }

    public final void reset() {
        setScaleX(1.0f);
        setScaleY(1.0f);
        setAlpha(1.0f);
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        setShadowElevation(0.0f);
        mo4352setAmbientShadowColor8_81llA(GraphicsLayerScopeKt.getDefaultShadowColor());
        mo4354setSpotShadowColor8_81llA(GraphicsLayerScopeKt.getDefaultShadowColor());
        setRotationX(0.0f);
        setRotationY(0.0f);
        setRotationZ(0.0f);
        setCameraDistance(8.0f);
        mo4355setTransformOrigin__ExYCQ(TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ());
        setShape(RectangleShapeKt.getRectangleShape());
        setClip(false);
        setRenderEffect(null);
        mo4353setCompositingStrategyaDBOjCE(CompositingStrategy.INSTANCE.m4251getAutoNrFUSI());
        m4474setSizeuvyYCjk(Size.INSTANCE.m3999getUnspecifiedNHjbRc());
        this.outline = null;
        this.mutatedFields = 0;
    }

    public final void updateOutline$ui_release() {
        this.outline = getShape().mo275createOutlinePq9zytI(getSize(), this.layoutDirection, this.graphicsDensity);
    }
}
