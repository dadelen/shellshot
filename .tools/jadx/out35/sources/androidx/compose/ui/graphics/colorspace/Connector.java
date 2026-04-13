package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Connector.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001e\u001fB\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0007J\u0012\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0018\u001a\u00020\fH\u0017J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0010ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\n\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector;", "", "source", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "destination", "intent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "transformSource", "transformDestination", "renderIntent", "transform", "", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;I[FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDestination", "()Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "getRenderIntent-uksYyKA", "()I", "I", "getSource", "r", "", "g", "b", "v", "transformToColor", "Landroidx/compose/ui/graphics/Color;", "color", "transformToColor-l2rxGTc$ui_graphics_release", "(J)J", "Companion", "RgbConnector", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public class Connector {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ColorSpace destination;
    private final int renderIntent;
    private final ColorSpace source;
    private final float[] transform;
    private final ColorSpace transformDestination;
    private final ColorSpace transformSource;

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, i);
    }

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, ColorSpace colorSpace4, int i, float[] fArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, colorSpace3, colorSpace4, i, fArr);
    }

    private Connector(ColorSpace source, ColorSpace destination, ColorSpace transformSource, ColorSpace transformDestination, int renderIntent, float[] transform) {
        this.source = source;
        this.destination = destination;
        this.transformSource = transformSource;
        this.transformDestination = transformDestination;
        this.renderIntent = renderIntent;
        this.transform = transform;
    }

    public final ColorSpace getSource() {
        return this.source;
    }

    public final ColorSpace getDestination() {
        return this.destination;
    }

    /* renamed from: getRenderIntent-uksYyKA, reason: not valid java name and from getter */
    public final int getRenderIntent() {
        return this.renderIntent;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private Connector(androidx.compose.ui.graphics.colorspace.ColorSpace r12, androidx.compose.ui.graphics.colorspace.ColorSpace r13, int r14) {
        /*
            r11 = this;
            long r0 = r12.getModel()
            androidx.compose.ui.graphics.colorspace.ColorModel$Companion r2 = androidx.compose.ui.graphics.colorspace.ColorModel.INSTANCE
            long r2 = r2.m4576getRgbxdoWZVw()
            boolean r0 = androidx.compose.ui.graphics.colorspace.ColorModel.m4569equalsimpl0(r0, r2)
            r1 = 2
            r2 = 0
            if (r0 == 0) goto L20
            androidx.compose.ui.graphics.colorspace.Illuminant r0 = androidx.compose.ui.graphics.colorspace.Illuminant.INSTANCE
            androidx.compose.ui.graphics.colorspace.WhitePoint r0 = r0.getD50()
            androidx.compose.ui.graphics.colorspace.ColorSpace r0 = androidx.compose.ui.graphics.colorspace.ColorSpaceKt.adapt$default(r12, r0, r2, r1, r2)
            r6 = r0
            goto L21
        L20:
            r6 = r12
        L21:
            long r3 = r13.getModel()
            androidx.compose.ui.graphics.colorspace.ColorModel$Companion r0 = androidx.compose.ui.graphics.colorspace.ColorModel.INSTANCE
            long r7 = r0.m4576getRgbxdoWZVw()
            boolean r0 = androidx.compose.ui.graphics.colorspace.ColorModel.m4569equalsimpl0(r3, r7)
            if (r0 == 0) goto L3d
            androidx.compose.ui.graphics.colorspace.Illuminant r0 = androidx.compose.ui.graphics.colorspace.Illuminant.INSTANCE
            androidx.compose.ui.graphics.colorspace.WhitePoint r0 = r0.getD50()
            androidx.compose.ui.graphics.colorspace.ColorSpace r0 = androidx.compose.ui.graphics.colorspace.ColorSpaceKt.adapt$default(r13, r0, r2, r1, r2)
            r7 = r0
            goto L3e
        L3d:
            r7 = r13
        L3e:
            androidx.compose.ui.graphics.colorspace.Connector$Companion r0 = androidx.compose.ui.graphics.colorspace.Connector.INSTANCE
            float[] r9 = androidx.compose.ui.graphics.colorspace.Connector.Companion.m4585access$computeTransformYBCOT_4(r0, r12, r13, r14)
            r10 = 0
            r3 = r11
            r4 = r12
            r5 = r13
            r8 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Connector.<init>(androidx.compose.ui.graphics.colorspace.ColorSpace, androidx.compose.ui.graphics.colorspace.ColorSpace, int):void");
    }

    public final float[] transform(float r, float g, float b) {
        return transform(new float[]{r, g, b});
    }

    public float[] transform(float[] v) {
        float[] xyz = this.transformSource.toXyz(v);
        if (this.transform != null) {
            xyz[0] = xyz[0] * this.transform[0];
            xyz[1] = xyz[1] * this.transform[1];
            xyz[2] = xyz[2] * this.transform[2];
        }
        return this.transformDestination.fromXyz(xyz);
    }

    /* compiled from: Connector.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J*\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0010ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector$RgbConnector;", "Landroidx/compose/ui/graphics/colorspace/Connector;", "mSource", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "mDestination", "intent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "(Landroidx/compose/ui/graphics/colorspace/Rgb;Landroidx/compose/ui/graphics/colorspace/Rgb;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "mTransform", "", "computeTransform", "source", "destination", "computeTransform-YBCOT_4", "(Landroidx/compose/ui/graphics/colorspace/Rgb;Landroidx/compose/ui/graphics/colorspace/Rgb;I)[F", "transform", "v", "transformToColor", "Landroidx/compose/ui/graphics/Color;", "color", "transformToColor-l2rxGTc$ui_graphics_release", "(J)J", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RgbConnector extends Connector {
        private final Rgb mDestination;
        private final Rgb mSource;
        private final float[] mTransform;

        public /* synthetic */ RgbConnector(Rgb rgb, Rgb rgb2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(rgb, rgb2, i);
        }

        private RgbConnector(Rgb mSource, Rgb mDestination, int intent) {
            super(mSource, mDestination, mSource, mDestination, intent, null, null);
            this.mSource = mSource;
            this.mDestination = mDestination;
            this.mTransform = m4587computeTransformYBCOT_4(this.mSource, this.mDestination, intent);
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        public float[] transform(float[] v) {
            v[0] = (float) this.mSource.getEotfFunc().invoke(v[0]);
            v[1] = (float) this.mSource.getEotfFunc().invoke(v[1]);
            v[2] = (float) this.mSource.getEotfFunc().invoke(v[2]);
            ColorSpaceKt.mul3x3Float3(this.mTransform, v);
            v[0] = (float) this.mDestination.getOetfFunc().invoke(v[0]);
            v[1] = (float) this.mDestination.getOetfFunc().invoke(v[1]);
            v[2] = (float) this.mDestination.getOetfFunc().invoke(v[2]);
            return v;
        }

        /* renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        private final float[] m4587computeTransformYBCOT_4(Rgb source, Rgb destination, int intent) {
            if (ColorSpaceKt.compare(source.getWhitePoint(), destination.getWhitePoint())) {
                return ColorSpaceKt.mul3x3(destination.getInverseTransform(), source.getTransform());
            }
            float[] transform = source.getTransform();
            float[] inverseTransform = destination.getInverseTransform();
            float[] srcXYZ = source.getWhitePoint().toXyz$ui_graphics_release();
            float[] dstXYZ = destination.getWhitePoint().toXyz$ui_graphics_release();
            if (!ColorSpaceKt.compare(source.getWhitePoint(), Illuminant.INSTANCE.getD50())) {
                float[] transform2 = Adaptation.INSTANCE.getBradford().getTransform();
                float[] d50Xyz$ui_graphics_release = Illuminant.INSTANCE.getD50Xyz$ui_graphics_release();
                float[] copyOf = Arrays.copyOf(d50Xyz$ui_graphics_release, d50Xyz$ui_graphics_release.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                float[] srcAdaptation = ColorSpaceKt.chromaticAdaptation(transform2, srcXYZ, copyOf);
                transform = ColorSpaceKt.mul3x3(srcAdaptation, source.getTransform());
            }
            if (!ColorSpaceKt.compare(destination.getWhitePoint(), Illuminant.INSTANCE.getD50())) {
                float[] transform3 = Adaptation.INSTANCE.getBradford().getTransform();
                float[] d50Xyz$ui_graphics_release2 = Illuminant.INSTANCE.getD50Xyz$ui_graphics_release();
                float[] copyOf2 = Arrays.copyOf(d50Xyz$ui_graphics_release2, d50Xyz$ui_graphics_release2.length);
                Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
                float[] dstAdaptation = ColorSpaceKt.chromaticAdaptation(transform3, dstXYZ, copyOf2);
                inverseTransform = ColorSpaceKt.inverse3x3(ColorSpaceKt.mul3x3(dstAdaptation, destination.getTransform()));
            }
            if (RenderIntent.m4592equalsimpl0(intent, RenderIntent.INSTANCE.m4596getAbsoluteuksYyKA())) {
                transform = ColorSpaceKt.mul3x3Diag(new float[]{srcXYZ[0] / dstXYZ[0], srcXYZ[1] / dstXYZ[1], srcXYZ[2] / dstXYZ[2]}, transform);
            }
            return ColorSpaceKt.mul3x3(inverseTransform, transform);
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        /* renamed from: transformToColor-l2rxGTc$ui_graphics_release */
        public long mo4584transformToColorl2rxGTc$ui_graphics_release(long color) {
            float r = Color.m4169getRedimpl(color);
            float g = Color.m4168getGreenimpl(color);
            float b = Color.m4166getBlueimpl(color);
            float a = Color.m4165getAlphaimpl(color);
            float v0 = (float) this.mSource.getEotfFunc().invoke(r);
            float v1 = (float) this.mSource.getEotfFunc().invoke(g);
            float v2 = (float) this.mSource.getEotfFunc().invoke(b);
            float[] lhs$iv = this.mTransform;
            float v01 = (lhs$iv[0] * v0) + (lhs$iv[3] * v1) + (lhs$iv[6] * v2);
            float[] lhs$iv2 = this.mTransform;
            float v11 = (lhs$iv2[1] * v0) + (lhs$iv2[4] * v1) + (lhs$iv2[7] * v2);
            float[] lhs$iv3 = this.mTransform;
            float v21 = (lhs$iv3[2] * v0) + (lhs$iv3[5] * v1) + (lhs$iv3[8] * v2);
            float v02 = (float) this.mDestination.getOetfFunc().invoke(v01);
            float v12 = (float) this.mDestination.getOetfFunc().invoke(v11);
            float v22 = (float) this.mDestination.getOetfFunc().invoke(v21);
            return ColorKt.Color(v02, v12, v22, a, this.mDestination);
        }
    }

    /* compiled from: Connector.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector$Companion;", "", "()V", "computeTransform", "", "source", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "destination", "intent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "computeTransform-YBCOT_4", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;I)[F", "identity", "Landroidx/compose/ui/graphics/colorspace/Connector;", "identity$ui_graphics_release", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        public final float[] m4586computeTransformYBCOT_4(ColorSpace source, ColorSpace destination, int intent) {
            if (!RenderIntent.m4592equalsimpl0(intent, RenderIntent.INSTANCE.m4596getAbsoluteuksYyKA())) {
                return null;
            }
            boolean srcRGB = ColorModel.m4569equalsimpl0(source.getModel(), ColorModel.INSTANCE.m4576getRgbxdoWZVw());
            boolean dstRGB = ColorModel.m4569equalsimpl0(destination.getModel(), ColorModel.INSTANCE.m4576getRgbxdoWZVw());
            if (srcRGB && dstRGB) {
                return null;
            }
            if (!srcRGB && !dstRGB) {
                return null;
            }
            ColorSpace colorSpace = srcRGB ? source : destination;
            Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
            Rgb rgb = (Rgb) colorSpace;
            float[] srcXYZ = srcRGB ? rgb.getWhitePoint().toXyz$ui_graphics_release() : Illuminant.INSTANCE.getD50Xyz$ui_graphics_release();
            float[] dstXYZ = dstRGB ? rgb.getWhitePoint().toXyz$ui_graphics_release() : Illuminant.INSTANCE.getD50Xyz$ui_graphics_release();
            return new float[]{srcXYZ[0] / dstXYZ[0], srcXYZ[1] / dstXYZ[1], srcXYZ[2] / dstXYZ[2]};
        }

        public final Connector identity$ui_graphics_release(final ColorSpace source) {
            final int m4598getRelativeuksYyKA = RenderIntent.INSTANCE.m4598getRelativeuksYyKA();
            return new Connector(source, m4598getRelativeuksYyKA) { // from class: androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1
                {
                    super(source, source, m4598getRelativeuksYyKA, null);
                }

                @Override // androidx.compose.ui.graphics.colorspace.Connector
                public float[] transform(float[] v) {
                    return v;
                }

                @Override // androidx.compose.ui.graphics.colorspace.Connector
                /* renamed from: transformToColor-l2rxGTc$ui_graphics_release */
                public long mo4584transformToColorl2rxGTc$ui_graphics_release(long color) {
                    return color;
                }
            };
        }
    }

    /* renamed from: transformToColor-l2rxGTc$ui_graphics_release, reason: not valid java name */
    public long mo4584transformToColorl2rxGTc$ui_graphics_release(long color) {
        float z;
        float y;
        float y2;
        float r = Color.m4169getRedimpl(color);
        float g = Color.m4168getGreenimpl(color);
        float b = Color.m4166getBlueimpl(color);
        float m4165getAlphaimpl = Color.m4165getAlphaimpl(color);
        long packed = this.transformSource.toXy$ui_graphics_release(r, g, b);
        int bits$iv$iv = (int) (packed >> 32);
        float x = Float.intBitsToFloat(bits$iv$iv);
        int bits$iv$iv2 = (int) (4294967295L & packed);
        float y3 = Float.intBitsToFloat(bits$iv$iv2);
        float z2 = this.transformSource.toZ$ui_graphics_release(r, g, b);
        if (this.transform != null) {
            float x2 = x * this.transform[0];
            float y4 = y3 * this.transform[1];
            z = z2 * this.transform[2];
            y = y4;
            y2 = x2;
        } else {
            z = z2;
            y = y3;
            y2 = x;
        }
        return this.transformDestination.mo4579xyzaToColorJlNiLsg$ui_graphics_release(y2, y, z, m4165getAlphaimpl, this.destination);
    }
}
