package androidx.compose.material;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: Icon.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u000f\u001a8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0012\u001a\u0014\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"DefaultIconSizeModifier", "Landroidx/compose/ui/Modifier;", "Icon", "", "bitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "contentDescription", "", "modifier", "tint", "Landroidx/compose/ui/graphics/Color;", "Icon-ww6aTOc", "(Landroidx/compose/ui/graphics/ImageBitmap;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "defaultSizeFor", "isInfinite", "", "Landroidx/compose/ui/geometry/Size;", "isInfinite-uvyYCjk", "(J)Z", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IconKt {
    private static final Modifier DefaultIconSizeModifier = SizeKt.m717size3ABfNKs(Modifier.INSTANCE, Dp.m6626constructorimpl(24));

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1571Iconww6aTOc(androidx.compose.ui.graphics.vector.ImageVector r18, java.lang.String r19, androidx.compose.ui.Modifier r20, long r21, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            r5 = r23
            r8 = r24
            r0 = -800853103(0xffffffffd043f391, float:-1.3150078E10)
            java.lang.String r1 = "C(Icon)P(1!,3:c#ui.graphics.Color)65@3149L7,65@3188L7,68@3229L34,67@3205L163:Icon.kt#jmzs0o"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r5, r0, r1)
            r1 = r25 & 4
            if (r1 == 0) goto L16
            androidx.compose.ui.Modifier$Companion r1 = androidx.compose.ui.Modifier.INSTANCE
            androidx.compose.ui.Modifier r1 = (androidx.compose.ui.Modifier) r1
            r2 = r1
            goto L18
        L16:
            r2 = r20
        L18:
            r1 = r25 & 8
            if (r1 == 0) goto L5d
            androidx.compose.runtime.ProvidableCompositionLocal r1 = androidx.compose.material.ContentColorKt.getLocalContentColor()
            androidx.compose.runtime.CompositionLocal r1 = (androidx.compose.runtime.CompositionLocal) r1
            r3 = 6
            r4 = 0
            r6 = 2023513938(0x789c5f52, float:2.5372864E34)
            java.lang.String r7 = "CC:CompositionLocal.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r5, r6, r7)
            java.lang.Object r9 = r5.consume(r1)
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r5)
            androidx.compose.ui.graphics.Color r9 = (androidx.compose.ui.graphics.Color) r9
            long r10 = r9.m4173unboximpl()
            androidx.compose.runtime.ProvidableCompositionLocal r1 = androidx.compose.material.ContentAlphaKt.getLocalContentAlpha()
            androidx.compose.runtime.CompositionLocal r1 = (androidx.compose.runtime.CompositionLocal) r1
            r3 = 6
            r4 = 0
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r5, r6, r7)
            java.lang.Object r6 = r5.consume(r1)
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r5)
            java.lang.Number r6 = (java.lang.Number) r6
            float r12 = r6.floatValue()
            r16 = 14
            r17 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            long r3 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r10, r12, r13, r14, r15, r16, r17)
            goto L5f
        L5d:
            r3 = r21
        L5f:
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L6c
            r1 = -1
            java.lang.String r6 = "androidx.compose.material.Icon (Icon.kt:66)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r8, r1, r6)
        L6c:
            r0 = r8 & 14
            r9 = r18
            androidx.compose.ui.graphics.vector.VectorPainter r0 = androidx.compose.ui.graphics.vector.VectorPainterKt.rememberVectorPainter(r9, r5, r0)
            androidx.compose.ui.graphics.painter.Painter r0 = (androidx.compose.ui.graphics.painter.Painter) r0
            int r1 = androidx.compose.ui.graphics.vector.VectorPainter.$stable
            r6 = r8 & 112(0x70, float:1.57E-43)
            r1 = r1 | r6
            r6 = r8 & 896(0x380, float:1.256E-42)
            r1 = r1 | r6
            r6 = r8 & 7168(0x1c00, float:1.0045E-41)
            r6 = r6 | r1
            r7 = 0
            r1 = r19
            m1570Iconww6aTOc(r0, r1, r2, r3, r5, r6, r7)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto L92
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L92:
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r23)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.IconKt.m1571Iconww6aTOc(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, androidx.compose.ui.Modifier, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1569Iconww6aTOc(androidx.compose.ui.graphics.ImageBitmap r19, java.lang.String r20, androidx.compose.ui.Modifier r21, long r22, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            r5 = r24
            r8 = r25
            r0 = -554892675(0xffffffffdeed027d, float:-8.539175E18)
            java.lang.String r1 = "C(Icon)P(!,3:c#ui.graphics.Color)99@4800L7,99@4839L7,101@4870L42,102@4917L136:Icon.kt#jmzs0o"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r5, r0, r1)
            r1 = r26 & 4
            if (r1 == 0) goto L16
            androidx.compose.ui.Modifier$Companion r1 = androidx.compose.ui.Modifier.INSTANCE
            androidx.compose.ui.Modifier r1 = (androidx.compose.ui.Modifier) r1
            r2 = r1
            goto L18
        L16:
            r2 = r21
        L18:
            r1 = r26 & 8
            if (r1 == 0) goto L5d
            androidx.compose.runtime.ProvidableCompositionLocal r1 = androidx.compose.material.ContentColorKt.getLocalContentColor()
            androidx.compose.runtime.CompositionLocal r1 = (androidx.compose.runtime.CompositionLocal) r1
            r3 = 6
            r4 = 0
            r6 = 2023513938(0x789c5f52, float:2.5372864E34)
            java.lang.String r7 = "CC:CompositionLocal.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r5, r6, r7)
            java.lang.Object r9 = r5.consume(r1)
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r5)
            androidx.compose.ui.graphics.Color r9 = (androidx.compose.ui.graphics.Color) r9
            long r10 = r9.m4173unboximpl()
            androidx.compose.runtime.ProvidableCompositionLocal r1 = androidx.compose.material.ContentAlphaKt.getLocalContentAlpha()
            androidx.compose.runtime.CompositionLocal r1 = (androidx.compose.runtime.CompositionLocal) r1
            r3 = 6
            r4 = 0
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r5, r6, r7)
            java.lang.Object r6 = r5.consume(r1)
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r5)
            java.lang.Number r6 = (java.lang.Number) r6
            float r12 = r6.floatValue()
            r16 = 14
            r17 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            long r3 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r10, r12, r13, r14, r15, r16, r17)
            goto L5f
        L5d:
            r3 = r22
        L5f:
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L6c
            r1 = -1
            java.lang.String r6 = "androidx.compose.material.Icon (Icon.kt:100)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r8, r1, r6)
        L6c:
            r0 = 724306569(0x2b2c0a89, float:6.1121296E-13)
            java.lang.String r1 = "CC(remember):Icon.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r5, r0, r1)
            r10 = r19
            boolean r0 = r5.changed(r10)
            r1 = r24
            r6 = 0
            java.lang.Object r7 = r1.rememberedValue()
            r17 = 0
            if (r0 != 0) goto L91
            androidx.compose.runtime.Composer$Companion r9 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r9 = r9.getEmpty()
            if (r7 != r9) goto L8f
            goto L91
        L8f:
            r9 = r7
            goto La4
        L91:
            r18 = 0
            androidx.compose.ui.graphics.painter.BitmapPainter r9 = new androidx.compose.ui.graphics.painter.BitmapPainter
            r15 = 6
            r16 = 0
            r11 = 0
            r13 = 0
            r9.<init>(r10, r11, r13, r15, r16)
            r1.updateRememberedValue(r9)
        La4:
            androidx.compose.ui.graphics.painter.BitmapPainter r9 = (androidx.compose.ui.graphics.painter.BitmapPainter) r9
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r5)
            r0 = r9
            androidx.compose.ui.graphics.painter.Painter r0 = (androidx.compose.ui.graphics.painter.Painter) r0
            r1 = r8 & 112(0x70, float:1.57E-43)
            r6 = r8 & 896(0x380, float:1.256E-42)
            r1 = r1 | r6
            r6 = r8 & 7168(0x1c00, float:1.0045E-41)
            r6 = r6 | r1
            r7 = 0
            r1 = r20
            m1570Iconww6aTOc(r0, r1, r2, r3, r5, r6, r7)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto Lc7
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        Lc7:
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r24)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.IconKt.m1569Iconww6aTOc(androidx.compose.ui.graphics.ImageBitmap, java.lang.String, androidx.compose.ui.Modifier, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1570Iconww6aTOc(androidx.compose.ui.graphics.painter.Painter r25, java.lang.String r26, androidx.compose.ui.Modifier r27, long r28, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instructions count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.IconKt.m1570Iconww6aTOc(androidx.compose.ui.graphics.painter.Painter, java.lang.String, androidx.compose.ui.Modifier, long, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final Modifier defaultSizeFor(Modifier $this$defaultSizeFor, Painter painter) {
        Modifier.Companion companion;
        if (Size.m3987equalsimpl0(painter.getIntrinsicSize(), Size.INSTANCE.m3999getUnspecifiedNHjbRc()) || m1572isInfiniteuvyYCjk(painter.getIntrinsicSize())) {
            companion = DefaultIconSizeModifier;
        } else {
            companion = Modifier.INSTANCE;
        }
        return $this$defaultSizeFor.then(companion);
    }

    /* renamed from: isInfinite-uvyYCjk, reason: not valid java name */
    private static final boolean m1572isInfiniteuvyYCjk(long $this$isInfinite_u2duvyYCjk) {
        return Float.isInfinite(Size.m3991getWidthimpl($this$isInfinite_u2duvyYCjk)) && Float.isInfinite(Size.m3988getHeightimpl($this$isInfinite_u2duvyYCjk));
    }
}
