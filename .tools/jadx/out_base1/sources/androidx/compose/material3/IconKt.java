package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.IconButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.PainterModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Icon.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a3\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0010\u001a8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0011\u001a8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0014\u001a\u0014\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0014\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0016\u0010\u0017\u001a\u00020\u0018*\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"DefaultIconSizeModifier", "Landroidx/compose/ui/Modifier;", "Icon", "", "bitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "contentDescription", "", "modifier", "tint", "Landroidx/compose/ui/graphics/Color;", "Icon-ww6aTOc", "(Landroidx/compose/ui/graphics/ImageBitmap;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/ui/graphics/ColorProducer;", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/ColorProducer;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "defaultSizeFor", "defaultSizeForColorProducer", "isInfinite", "", "Landroidx/compose/ui/geometry/Size;", "isInfinite-uvyYCjk", "(J)Z", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class IconKt {
    private static final Modifier DefaultIconSizeModifier = SizeKt.m717size3ABfNKs(Modifier.INSTANCE, IconButtonTokens.INSTANCE.m3293getIconSizeD9Ej5fM());

    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m2154Iconww6aTOc(final ImageVector imageVector, final String contentDescription, Modifier modifier, long tint, Composer $composer, final int $changed, final int i) {
        String str;
        final Modifier modifier2;
        final long tint2;
        Modifier modifier3;
        long tint3;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-126890956);
        ComposerKt.sourceInformation($composer2, "C(Icon)P(1!,3:c#ui.graphics.Color)69@3394L7,72@3434L34,71@3410L163:Icon.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(imageVector) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            str = contentDescription;
        } else if (($changed & 48) == 0) {
            str = contentDescription;
            $dirty |= $composer2.changed(str) ? 32 : 16;
        } else {
            str = contentDescription;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                tint2 = tint;
                if ($composer2.changed(tint2)) {
                    i2 = 2048;
                    $dirty |= i2;
                }
            } else {
                tint2 = tint;
            }
            i2 = 1024;
            $dirty |= i2;
        } else {
            tint2 = tint;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty &= -7169;
                    modifier3 = modifier4;
                    tint3 = ((Color) consume).m4173unboximpl();
                } else {
                    modifier3 = modifier4;
                    tint3 = tint2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier3 = modifier2;
                tint3 = tint2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-126890956, $dirty, -1, "androidx.compose.material3.Icon (Icon.kt:70)");
            }
            m2153Iconww6aTOc(VectorPainterKt.rememberVectorPainter(imageVector, $composer2, $dirty & 14), str, modifier3, tint3, $composer2, VectorPainter.$stable | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            tint2 = tint3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconKt$Icon$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i4) {
                    IconKt.m2154Iconww6aTOc(ImageVector.this, contentDescription, modifier2, tint2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m2152Iconww6aTOc(final ImageBitmap bitmap, final String contentDescription, Modifier modifier, long tint, Composer $composer, final int $changed, final int i) {
        String str;
        Modifier modifier2;
        long j;
        int $dirty;
        Modifier modifier3;
        long tint2;
        Composer $composer2;
        final Modifier modifier4;
        final long tint3;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(-1092052280);
        ComposerKt.sourceInformation($composer3, "C(Icon)P(!,3:c#ui.graphics.Color)105@5106L7,107@5136L42,108@5183L136:Icon.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(bitmap) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            str = contentDescription;
        } else if (($changed & 48) == 0) {
            str = contentDescription;
            $dirty2 |= $composer3.changed(str) ? 32 : 16;
        } else {
            str = contentDescription;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = tint;
                if ($composer3.changed(j)) {
                    i2 = 2048;
                    $dirty2 |= i2;
                }
            } else {
                j = tint;
            }
            i2 = 1024;
            $dirty2 |= i2;
        } else {
            j = tint;
        }
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            tint3 = j;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    long tint4 = ((Color) consume).m4173unboximpl();
                    $dirty = $dirty2 & (-7169);
                    modifier3 = modifier5;
                    tint2 = tint4;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    tint2 = j;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                tint2 = j;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1092052280, $dirty, -1, "androidx.compose.material3.Icon (Icon.kt:106)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -2144951290, "CC(remember):Icon.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(bitmap);
            Object value$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new BitmapPainter(bitmap, 0L, 0L, 6, null);
                $composer3.updateRememberedValue(value$iv);
            }
            BitmapPainter painter = (BitmapPainter) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            m2153Iconww6aTOc(painter, str, modifier3, tint2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            tint3 = tint2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconKt$Icon$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i4) {
                    IconKt.m2152Iconww6aTOc(ImageBitmap.this, contentDescription, modifier4, tint3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m2153Iconww6aTOc(final Painter painter, final String contentDescription, Modifier modifier, long tint, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long tint2;
        Modifier modifier3;
        int i2;
        Modifier.Companion semantics;
        Modifier paint;
        final Modifier modifier4;
        final long tint3;
        Object value$iv;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(-2142239481);
        ComposerKt.sourceInformation($composer2, "C(Icon)P(2!,3:c#ui.graphics.Color)142@6849L7,145@6891L82,155@7235L217:Icon.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(painter) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(contentDescription) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = tint;
                if ($composer2.changed(j)) {
                    i3 = 2048;
                    $dirty |= i3;
                }
            } else {
                j = tint;
            }
            i3 = 1024;
            $dirty |= i3;
        } else {
            j = tint;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            tint3 = j;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty &= -7169;
                    modifier3 = modifier5;
                    tint2 = ((Color) consume).m4173unboximpl();
                } else {
                    tint2 = j;
                    modifier3 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                tint2 = j;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2142239481, $dirty, -1, "androidx.compose.material3.Icon (Icon.kt:143)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -2144895090, "CC(remember):Icon.kt#9igjgp");
            boolean invalid$iv = ((($dirty & 7168) ^ 3072) > 2048 && $composer2.changed(tint2)) || ($dirty & 3072) == 2048;
            Object value$iv2 = $composer2.rememberedValue();
            if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = Color.m4164equalsimpl0(tint2, Color.INSTANCE.m4199getUnspecified0d7_KjU()) ? null : ColorFilter.Companion.m4204tintxETnrds$default(ColorFilter.INSTANCE, tint2, 0, 2, null);
                $composer2.updateRememberedValue(value$iv2);
            }
            ColorFilter colorFilter = (ColorFilter) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.startReplaceGroup(-2144891392);
            ComposerKt.sourceInformation($composer2, "148@7067L115");
            if (contentDescription != null) {
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -2144889425, "CC(remember):Icon.kt#9igjgp");
                boolean invalid$iv2 = ($dirty & 112) == 32;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconKt$Icon$semantics$1$1
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
                            SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription);
                            SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5905getImageo7Vup1c());
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                i2 = 0;
                semantics = SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv, 1, null);
            } else {
                i2 = 0;
                semantics = Modifier.INSTANCE;
            }
            $composer2.endReplaceGroup();
            Modifier semantics2 = semantics;
            paint = PainterModifierKt.paint(defaultSizeFor(GraphicsLayerModifierKt.toolingGraphicsLayer(modifier3), painter), painter, (r14 & 2) != 0, (r14 & 4) != 0 ? Alignment.INSTANCE.getCenter() : null, (r14 & 8) != 0 ? ContentScale.INSTANCE.getInside() : ContentScale.INSTANCE.getFit(), (r14 & 16) != 0 ? 1.0f : 0.0f, (r14 & 32) != 0 ? null : colorFilter);
            BoxKt.Box(paint.then(semantics2), $composer2, i2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            tint3 = tint2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconKt$Icon$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    IconKt.m2153Iconww6aTOc(Painter.this, contentDescription, modifier4, tint3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void Icon(final Painter painter, final ColorProducer tint, final String contentDescription, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier.Companion semantics;
        Object value$iv;
        final Modifier modifier3;
        Object value$iv2;
        Composer $composer2 = $composer.startRestartGroup(1755070997);
        ComposerKt.sourceInformation($composer2, "C(Icon)P(2,3)206@9303L163,202@9171L330:Icon.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(painter) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(tint) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(contentDescription) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) != 1170 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1755070997, $dirty2, -1, "androidx.compose.material3.Icon (Icon.kt:191)");
            }
            $composer2.startReplaceGroup(-2144829472);
            ComposerKt.sourceInformation($composer2, "194@9002L115");
            if (contentDescription != null) {
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -2144827505, "CC(remember):Icon.kt#9igjgp");
                boolean invalid$iv = ($dirty2 & 896) == 256;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconKt$Icon$semantics$2$1
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
                            SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription);
                            SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5905getImageo7Vup1c());
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                semantics = SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv2, 1, null);
            } else {
                semantics = Modifier.INSTANCE;
            }
            $composer2.endReplaceGroup();
            Modifier defaultSizeForColorProducer = defaultSizeForColorProducer(GraphicsLayerModifierKt.toolingGraphicsLayer(modifier2), painter);
            ComposerKt.sourceInformationMarkerStart($composer2, -2144817825, "CC(remember):Icon.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(painter) | $composer2.changedInstance(tint);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.IconKt$Icon$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$drawBehind) {
                        ColorFilter colorFilter;
                        Painter $this$invoke_u24lambda_u241 = Painter.this;
                        ColorProducer colorProducer = tint;
                        long mo4713getSizeNHjbRc = $this$drawBehind.mo4713getSizeNHjbRc();
                        if (colorProducer != null) {
                            long it = colorProducer.mo1521invoke0d7_KjU();
                            colorFilter = ColorFilter.Companion.m4204tintxETnrds$default(ColorFilter.INSTANCE, it, 0, 2, null);
                        } else {
                            colorFilter = null;
                        }
                        Painter.m4837drawx_KDEd0$default($this$invoke_u24lambda_u241, $this$drawBehind, mo4713getSizeNHjbRc, 0.0f, colorFilter, 2, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BoxKt.Box(DrawModifierKt.drawBehind(defaultSizeForColorProducer, (Function1) value$iv).then(semantics), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconKt$Icon$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    IconKt.Icon(Painter.this, tint, contentDescription, modifier3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final Modifier defaultSizeForColorProducer(Modifier $this$defaultSizeForColorProducer, Painter painter) {
        Modifier modifier;
        if (Size.m3987equalsimpl0(painter.getIntrinsicSize(), Size.INSTANCE.m3999getUnspecifiedNHjbRc()) || m2155isInfiniteuvyYCjk(painter.getIntrinsicSize())) {
            modifier = DefaultIconSizeModifier;
        } else {
            long intrinsicSize = painter.getIntrinsicSize();
            final float srcWidth = Size.m3991getWidthimpl(intrinsicSize);
            final float srcHeight = Size.m3988getHeightimpl(intrinsicSize);
            modifier = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.IconKt$defaultSizeForColorProducer$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                    return m2156invoke3p2s80s(measureScope, measurable, constraints.getValue());
                }

                /* renamed from: invoke-3p2s80s, reason: not valid java name */
                public final MeasureResult m2156invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                    final Placeable placeable = measurable.mo5491measureBRTryo0(Constraints.INSTANCE.m6589fixedJhjzzOo((int) srcWidth, (int) srcHeight));
                    return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.IconKt$defaultSizeForColorProducer$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                            Placeable.PlacementScope.place$default($this$layout2, Placeable.this, 0, 0, 0.0f, 4, null);
                        }
                    }, 4, null);
                }
            });
        }
        return $this$defaultSizeForColorProducer.then(modifier);
    }

    private static final Modifier defaultSizeFor(Modifier $this$defaultSizeFor, Painter painter) {
        Modifier.Companion companion;
        if (Size.m3987equalsimpl0(painter.getIntrinsicSize(), Size.INSTANCE.m3999getUnspecifiedNHjbRc()) || m2155isInfiniteuvyYCjk(painter.getIntrinsicSize())) {
            companion = DefaultIconSizeModifier;
        } else {
            companion = Modifier.INSTANCE;
        }
        return $this$defaultSizeFor.then(companion);
    }

    /* renamed from: isInfinite-uvyYCjk, reason: not valid java name */
    private static final boolean m2155isInfiniteuvyYCjk(long $this$isInfinite_u2duvyYCjk) {
        return Float.isInfinite(Size.m3991getWidthimpl($this$isInfinite_u2duvyYCjk)) && Float.isInfinite(Size.m3988getHeightimpl($this$isInfinite_u2duvyYCjk));
    }
}
