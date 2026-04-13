package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.SheetBottomTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: SheetDefaults.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\r2\b\b\u0002\u0010#\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00048Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\u00020\u00048Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0006R\u0019\u0010\u0014\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0015\u0010\nR\u0019\u0010\u0016\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0017\u0010\nR\u0011\u0010\u0018\u001a\u00020\u00198G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006&"}, d2 = {"Landroidx/compose/material3/BottomSheetDefaults;", "", "()V", "ContainerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM", "()F", "F", "ExpandedShape", "Landroidx/compose/ui/graphics/Shape;", "getExpandedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "HiddenShape", "getHiddenShape", "ScrimColor", "getScrimColor", "SheetMaxWidth", "getSheetMaxWidth-D9Ej5fM", "SheetPeekHeight", "getSheetPeekHeight-D9Ej5fM", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "DragHandle", "", "modifier", "Landroidx/compose/ui/Modifier;", "width", "height", "shape", "color", "DragHandle-lgZ2HuY", "(Landroidx/compose/ui/Modifier;FFLandroidx/compose/ui/graphics/Shape;JLandroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class BottomSheetDefaults {
    public static final int $stable = 0;
    public static final BottomSheetDefaults INSTANCE = new BottomSheetDefaults();
    private static final float Elevation = SheetBottomTokens.INSTANCE.m3487getDockedModalContainerElevationD9Ej5fM();
    private static final float SheetPeekHeight = Dp.m6626constructorimpl(56);
    private static final float SheetMaxWidth = Dp.m6626constructorimpl(640);

    private BottomSheetDefaults() {
    }

    public final Shape getHiddenShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1971658024, "C291@11425L5:SheetDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1971658024, $changed, -1, "androidx.compose.material3.BottomSheetDefaults.<get-HiddenShape> (SheetDefaults.kt:291)");
        }
        Shape value = ShapesKt.getValue(SheetBottomTokens.INSTANCE.getDockedMinimizedContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getExpandedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1683783414, "C295@11623L5:SheetDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1683783414, $changed, -1, "androidx.compose.material3.BottomSheetDefaults.<get-ExpandedShape> (SheetDefaults.kt:295)");
        }
        Shape value = ShapesKt.getValue(SheetBottomTokens.INSTANCE.getDockedContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final long getContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 433375448, "C299@11786L5:SheetDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(433375448, $changed, -1, "androidx.compose.material3.BottomSheetDefaults.<get-ContainerColor> (SheetDefaults.kt:299)");
        }
        long value = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedContainerColor(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m1796getElevationD9Ej5fM() {
        return Elevation;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    public final long getScrimColor(androidx.compose.runtime.Composer r11, int r12) {
        /*
            r10 = this;
            r0 = -2040719176(0xffffffff865d18b8, float:-4.158364E-35)
            java.lang.String r1 = "C306@12070L5:SheetDefaults.kt#uh7d8r"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r11, r0, r1)
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L14
            r1 = -1
            java.lang.String r2 = "androidx.compose.material3.BottomSheetDefaults.<get-ScrimColor> (SheetDefaults.kt:306)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r12, r1, r2)
        L14:
            androidx.compose.material3.tokens.ScrimTokens r0 = androidx.compose.material3.tokens.ScrimTokens.INSTANCE
            androidx.compose.material3.tokens.ColorSchemeKeyTokens r0 = r0.getContainerColor()
            r1 = 6
            long r2 = androidx.compose.material3.ColorSchemeKt.getValue(r0, r11, r1)
            r8 = 14
            r9 = 0
            r4 = 1050924810(0x3ea3d70a, float:0.32)
            r5 = 0
            r6 = 0
            r7 = 0
            long r0 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r2, r4, r5, r6, r7, r8, r9)
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto L35
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L35:
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BottomSheetDefaults.getScrimColor(androidx.compose.runtime.Composer, int):long");
    }

    /* renamed from: getSheetPeekHeight-D9Ej5fM, reason: not valid java name */
    public final float m1798getSheetPeekHeightD9Ej5fM() {
        return SheetPeekHeight;
    }

    /* renamed from: getSheetMaxWidth-D9Ej5fM, reason: not valid java name */
    public final float m1797getSheetMaxWidthD9Ej5fM() {
        return SheetMaxWidth;
    }

    public final WindowInsets getWindowInsets(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -511309409, "C316@12492L11:SheetDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511309409, $changed, -1, "androidx.compose.material3.BottomSheetDefaults.<get-windowInsets> (SheetDefaults.kt:316)");
        }
        WindowInsets m743onlybOOhFvg = WindowInsetsKt.m743onlybOOhFvg(WindowInsets_androidKt.getSafeDrawing(WindowInsets.INSTANCE, $composer, 6), WindowInsetsSides.INSTANCE.m763getBottomJoeWqyM());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m743onlybOOhFvg;
    }

    /* renamed from: DragHandle-lgZ2HuY, reason: not valid java name */
    public final void m1795DragHandlelgZ2HuY(Modifier modifier, float width, float height, Shape shape, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final float width2;
        float height2;
        Shape shape2;
        long color2;
        Modifier.Companion modifier3;
        int $dirty;
        final float height3;
        float f;
        Object value$iv;
        Composer $composer2;
        final Shape shape3;
        final float height4;
        final Modifier modifier4;
        final float width3;
        final long color3;
        int i2;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-1364277227);
        ComposerKt.sourceInformation($composer3, "C(DragHandle)P(2,4:c#ui.unit.Dp,1:c#ui.unit.Dp,3,0:c#ui.graphics.Color)324@12873L6,325@12955L5,327@13006L51,330@13179L82,335@13326L74,328@13066L334:SheetDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty2 |= 48;
            width2 = width;
        } else if (($changed & 48) == 0) {
            width2 = width;
            $dirty2 |= $composer3.changed(width2) ? 32 : 16;
        } else {
            width2 = width;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty2 |= 384;
            height2 = height;
        } else if (($changed & 384) == 0) {
            height2 = height;
            $dirty2 |= $composer3.changed(height2) ? 256 : 128;
        } else {
            height2 = height;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i3 = 2048;
                    $dirty2 |= i3;
                }
            } else {
                shape2 = shape;
            }
            i3 = 1024;
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                color2 = color;
                if ($composer3.changed(color2)) {
                    i2 = 16384;
                    $dirty2 |= i2;
                }
            } else {
                color2 = color;
            }
            i2 = 8192;
            $dirty2 |= i2;
        } else {
            color2 = color;
        }
        if (($dirty2 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            shape3 = shape2;
            modifier4 = modifier2;
            height4 = height2;
            width3 = width2;
            color3 = color2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if (i5 != 0) {
                    width2 = SheetBottomTokens.INSTANCE.m3486getDockedDragHandleWidthD9Ej5fM();
                }
                if (i6 != 0) {
                    height2 = SheetBottomTokens.INSTANCE.m3485getDockedDragHandleHeightD9Ej5fM();
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getExtraLarge();
                }
                if ((i & 16) != 0) {
                    color2 = ColorSchemeKt.getValue(SheetBottomTokens.INSTANCE.getDockedDragHandleColor(), $composer3, 6);
                    $dirty = $dirty2 & (-57345);
                    height3 = height2;
                } else {
                    $dirty = $dirty2;
                    height3 = height2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    modifier3 = modifier2;
                    $dirty = $dirty2 & (-57345);
                    height3 = height2;
                } else {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    height3 = height2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1364277227, $dirty, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle (SheetDefaults.kt:326)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String dragHandleDescription = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_bottom_sheet_drag_handle_description), $composer3, 0);
            f = SheetDefaultsKt.DragHandleVerticalPadding;
            Modifier m674paddingVpY3zN4$default = PaddingKt.m674paddingVpY3zN4$default(modifier3, 0.0f, f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1620989881, "CC(remember):SheetDefaults.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(dragHandleDescription);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.BottomSheetDefaults$DragHandle$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setContentDescription($this$semantics, dragHandleDescription);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            SurfaceKt.m2547SurfaceT9BRK9s(SemanticsModifierKt.semantics$default(m674paddingVpY3zN4$default, false, (Function1) value$iv, 1, null), shape2, color2, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1039573072, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetDefaults$DragHandle$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C336@13340L50:SheetDefaults.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1039573072, $changed2, -1, "androidx.compose.material3.BottomSheetDefaults.DragHandle.<anonymous> (SheetDefaults.kt:336)");
                    }
                    BoxKt.Box(SizeKt.m719sizeVpY3zN4(Modifier.INSTANCE, width2, height3), $composer4, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty >> 6) & 112) | 12582912 | (($dirty >> 6) & 896), 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape2;
            height4 = height3;
            modifier4 = modifier3;
            width3 = width2;
            color3 = color2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetDefaults$DragHandle$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    BottomSheetDefaults.this.m1795DragHandlelgZ2HuY(modifier4, width3, height4, shape3, color3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
