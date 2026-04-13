package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Card.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0083\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a_\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0018\u001aw\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001a\u001aS\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001b\u001a\u0081\u0001\u0010\u001c\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a]\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0018¨\u0006\u001d"}, d2 = {"Card", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/CardColors;", "elevation", "Landroidx/compose/material3/CardElevation;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ElevatedCard", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "OutlinedCard", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CardKt {
    public static final void Card(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        Shape shape3;
        CardColors colors2;
        CardElevation elevation2;
        BorderStroke border2;
        Modifier modifier3;
        Shape shape4;
        final CardColors colors3;
        final CardElevation elevation3;
        final Modifier modifier4;
        final Shape shape5;
        final BorderStroke border3;
        int i2;
        int i3;
        int i4;
        Composer $composer2 = $composer.startRestartGroup(1179621553);
        ComposerKt.sourceInformation($composer2, "C(Card)P(4,5,1,3)82@3741L5,83@3786L12,84@3844L15,93@4163L57,95@4259L41,88@3951L349:Card.kt#uh7d8r");
        int $dirty = $changed;
        int i5 = i & 1;
        if (i5 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                if ($composer2.changed(shape2)) {
                    i4 = 32;
                    $dirty |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 16;
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                if ($composer2.changed(cardColors)) {
                    i3 = 256;
                    $dirty |= i3;
                }
            } else {
                cardColors = colors;
            }
            i3 = 128;
            $dirty |= i3;
        } else {
            cardColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                if ($composer2.changed(cardElevation)) {
                    i2 = 2048;
                    $dirty |= i2;
                }
            } else {
                cardElevation = elevation;
            }
            i2 = 1024;
            $dirty |= i2;
        } else {
            cardElevation = elevation;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            borderStroke = border;
        } else if (($changed & 24576) == 0) {
            borderStroke = border;
            $dirty |= $composer2.changed(borderStroke) ? 16384 : 8192;
        } else {
            borderStroke = border;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = cardColors;
            border3 = borderStroke;
            shape5 = shape2;
            elevation3 = cardElevation;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.cardColors($composer2, 6);
                    $dirty &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1834cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty &= -7169;
                } else {
                    elevation2 = elevation;
                }
                if (i6 != 0) {
                    shape4 = shape3;
                    border2 = null;
                    modifier3 = modifier5;
                } else {
                    border2 = border;
                    modifier3 = modifier5;
                    shape4 = shape3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                border2 = borderStroke;
                shape4 = shape2;
                colors2 = cardColors;
                elevation2 = cardElevation;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1179621553, $dirty, -1, "androidx.compose.material3.Card (Card.kt:87)");
            }
            int $dirty2 = $dirty;
            SurfaceKt.m2547SurfaceT9BRK9s(modifier3, shape4, colors2.m1826containerColorvNxB06k$material3_release(true), colors2.m1827contentColorvNxB06k$material3_release(true), 0.0f, elevation2.shadowElevation$material3_release(true, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m6640unboximpl(), border2, ComposableLambdaKt.rememberComposableLambda(664103990, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$Card$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x013d  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r24, int r25) {
                    /*
                        Method dump skipped, instructions count: 321
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.CardKt$Card$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 12582912 | ($dirty2 & 112) | (($dirty2 << 6) & 3670016), 16);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            elevation3 = elevation2;
            modifier4 = modifier3;
            shape5 = shape4;
            border3 = border2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$Card$2
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

                public final void invoke(Composer composer, int i7) {
                    CardKt.Card(Modifier.this, shape5, colors3, elevation3, border3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void Card(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        CardColors colors2;
        Composer $composer2;
        int i2;
        CardElevation elevation2;
        MutableInteractionSource interactionSource2;
        BorderStroke border2;
        Modifier modifier3;
        boolean enabled3;
        Shape shape3;
        MutableInteractionSource interactionSource3;
        Composer $composer3;
        final CardColors colors3;
        final CardElevation elevation3;
        final MutableInteractionSource interactionSource4;
        final Modifier modifier4;
        final boolean enabled4;
        final Shape shape4;
        final BorderStroke border3;
        Object value$iv;
        int i3;
        int i4;
        int i5;
        Composer $composer4 = $composer.startRestartGroup(-2024281376);
        ComposerKt.sourceInformation($composer4, "C(Card)P(7,6,4,8,1,3!1,5)141@6394L5,142@6439L12,143@6497L15,157@7034L43,160@7163L41,150@6782L422:Card.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer4.changedInstance(function0) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i5 = 2048;
                    $dirty |= i5;
                }
            } else {
                shape2 = shape;
            }
            i5 = 1024;
            $dirty |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                cardColors = colors;
                if ($composer4.changed(cardColors)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                cardColors = colors;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            cardColors = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                if ($composer4.changed(cardElevation)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                cardElevation = elevation;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            cardElevation = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            borderStroke = border;
        } else if ((1572864 & $changed) == 0) {
            borderStroke = border;
            $dirty |= $composer4.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer4.changed(interactionSource) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & $dirty) == 38347922 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = cardColors;
            $composer3 = $composer4;
            enabled4 = enabled2;
            shape4 = shape2;
            elevation3 = cardElevation;
            border3 = borderStroke;
            interactionSource4 = interactionSource;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = CardDefaults.INSTANCE.getShape($composer4, 6);
                }
                if ((i & 16) != 0) {
                    colors2 = CardDefaults.INSTANCE.cardColors($composer4, 6);
                    $dirty &= -57345;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    i2 = i9;
                    elevation2 = CardDefaults.INSTANCE.m1834cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer4, 1572864, 63);
                    $composer2 = $composer4;
                    $dirty &= -458753;
                } else {
                    $composer2 = $composer4;
                    i2 = i9;
                    elevation2 = elevation;
                }
                BorderStroke border4 = i8 != 0 ? null : border;
                if (i2 != 0) {
                    interactionSource2 = null;
                    border2 = border4;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                } else {
                    interactionSource2 = interactionSource;
                    border2 = border4;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    interactionSource2 = interactionSource;
                    $dirty &= -458753;
                    $composer2 = $composer4;
                    shape3 = shape2;
                    colors2 = cardColors;
                    elevation2 = cardElevation;
                    border2 = borderStroke;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                } else {
                    interactionSource2 = interactionSource;
                    $composer2 = $composer4;
                    shape3 = shape2;
                    colors2 = cardColors;
                    elevation2 = cardElevation;
                    border2 = borderStroke;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2024281376, $dirty, -1, "androidx.compose.material3.Card (Card.kt:147)");
            }
            $composer2.startReplaceGroup(1976524431);
            ComposerKt.sourceInformation($composer2, "149@6738L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer2, 1976525082, "CC(remember):Card.kt#9igjgp");
                Composer $this$cache$iv = $composer2;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                interactionSource3 = (MutableInteractionSource) value$iv;
            } else {
                interactionSource3 = interactionSource2;
            }
            $composer2.endReplaceGroup();
            $composer3 = $composer2;
            SurfaceKt.m2550Surfaceo_FOJdg(function0, modifier3, enabled3, shape3, colors2.m1826containerColorvNxB06k$material3_release(enabled3), colors2.m1827contentColorvNxB06k$material3_release(enabled3), 0.0f, elevation2.shadowElevation$material3_release(enabled3, interactionSource3, $composer2, (($dirty >> 6) & 14) | (($dirty >> 9) & 896)).getValue().m6640unboximpl(), border2, interactionSource3, ComposableLambdaKt.rememberComposableLambda(776921067, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$Card$3
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x013d  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r24, int r25) {
                    /*
                        Method dump skipped, instructions count: 321
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.CardKt$Card$3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024), 6, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            elevation3 = elevation2;
            interactionSource4 = interactionSource2;
            modifier4 = modifier3;
            enabled4 = enabled3;
            shape4 = shape3;
            border3 = border2;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$Card$4
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

                public final void invoke(Composer composer, int i10) {
                    CardKt.Card(function0, modifier4, enabled4, shape4, colors3, elevation3, border3, interactionSource4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ElevatedCard(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        Shape shape3;
        CardColors colors2;
        Composer $composer2;
        CardElevation elevation2;
        Modifier modifier3;
        Shape shape4;
        CardColors colors3;
        Composer $composer3;
        final Shape shape5;
        final CardColors colors4;
        final CardElevation elevation3;
        final Modifier modifier4;
        int i2;
        int i3;
        int i4;
        Composer $composer4 = $composer.startRestartGroup(895940201);
        ComposerKt.sourceInformation($composer4, "C(ElevatedCard)P(3,4!1,2)195@8668L13,196@8721L20,197@8787L23,200@8868L168:Card.kt#uh7d8r");
        int $dirty = $changed;
        int i5 = i & 1;
        if (i5 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i4 = 32;
                    $dirty |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 16;
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                if ($composer4.changed(cardColors)) {
                    i3 = 256;
                    $dirty |= i3;
                }
            } else {
                cardColors = colors;
            }
            i3 = 128;
            $dirty |= i3;
        } else {
            cardColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                if ($composer4.changed(cardElevation)) {
                    i2 = 2048;
                    $dirty |= i2;
                }
            } else {
                cardElevation = elevation;
            }
            i2 = 1024;
            $dirty |= i2;
        } else {
            cardElevation = elevation;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            modifier4 = modifier2;
            shape5 = shape2;
            colors4 = cardColors;
            elevation3 = cardElevation;
            $composer3 = $composer4;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getElevatedShape($composer4, 6);
                    $dirty &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.elevatedCardColors($composer4, 6);
                    $dirty &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    $composer2 = $composer4;
                    $dirty &= -7169;
                    modifier3 = modifier5;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation2 = CardDefaults.INSTANCE.m1836elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                } else {
                    $composer2 = $composer4;
                    elevation2 = elevation;
                    modifier3 = modifier5;
                    shape4 = shape3;
                    colors3 = colors2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                shape4 = shape2;
                colors3 = cardColors;
                elevation2 = cardElevation;
                $composer2 = $composer4;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(895940201, $dirty, -1, "androidx.compose.material3.ElevatedCard (Card.kt:200)");
            }
            Composer $composer5 = $composer2;
            Card(modifier3, shape4, colors3, elevation2, null, function3, $composer5, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752), 0);
            $composer3 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape5 = shape4;
            colors4 = colors3;
            elevation3 = elevation2;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$ElevatedCard$1
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

                public final void invoke(Composer composer, int i6) {
                    CardKt.ElevatedCard(Modifier.this, shape5, colors4, elevation3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ElevatedCard(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        MutableInteractionSource mutableInteractionSource;
        CardColors colors2;
        Composer $composer2;
        CardElevation elevation2;
        CardColors colors3;
        CardElevation elevation3;
        Modifier modifier3;
        boolean enabled3;
        Shape shape3;
        Composer $composer3;
        MutableInteractionSource interactionSource2;
        Composer $composer4;
        final MutableInteractionSource interactionSource3;
        final Modifier modifier4;
        final boolean enabled4;
        final Shape shape4;
        final CardColors colors4;
        final CardElevation elevation4;
        int i2;
        int i3;
        int i4;
        Composer $composer5 = $composer.startRestartGroup(-1850977784);
        ComposerKt.sourceInformation($composer5, "C(ElevatedCard)P(6,5,3,7!1,2,4)248@11071L13,249@11124L20,250@11190L23,254@11328L269:Card.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty |= $composer5.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer5.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer5.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer5.changed(shape2)) {
                    i4 = 2048;
                    $dirty |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 1024;
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                cardColors = colors;
                if ($composer5.changed(cardColors)) {
                    i3 = 16384;
                    $dirty |= i3;
                }
            } else {
                cardColors = colors;
            }
            i3 = 8192;
            $dirty |= i3;
        } else {
            cardColors = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                if ($composer5.changed(cardElevation)) {
                    i2 = 131072;
                    $dirty |= i2;
                }
            } else {
                cardElevation = elevation;
            }
            i2 = 65536;
            $dirty |= i2;
        } else {
            cardElevation = elevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 1572864) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty |= $composer5.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer5.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) == 4793490 && $composer5.getSkipping()) {
            $composer5.skipToGroupEnd();
            modifier4 = modifier2;
            colors4 = cardColors;
            $composer4 = $composer5;
            enabled4 = enabled2;
            shape4 = shape2;
            elevation4 = cardElevation;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer5.startDefaults();
            if (($changed & 1) == 0 || $composer5.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = CardDefaults.INSTANCE.getElevatedShape($composer5, 6);
                }
                if ((i & 16) != 0) {
                    colors2 = CardDefaults.INSTANCE.elevatedCardColors($composer5, 6);
                    $dirty &= -57345;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    $composer2 = $composer5;
                    elevation2 = CardDefaults.INSTANCE.m1836elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    $composer2 = $composer5;
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    colors3 = colors2;
                    elevation3 = elevation2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    $composer3 = $composer2;
                    interactionSource2 = null;
                } else {
                    colors3 = colors2;
                    elevation3 = elevation2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    $composer3 = $composer2;
                    interactionSource2 = interactionSource;
                }
            } else {
                $composer5.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    $composer3 = $composer5;
                    shape3 = shape2;
                    colors3 = cardColors;
                    elevation3 = cardElevation;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                } else {
                    $composer3 = $composer5;
                    shape3 = shape2;
                    colors3 = cardColors;
                    elevation3 = cardElevation;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1850977784, $dirty, -1, "androidx.compose.material3.ElevatedCard (Card.kt:254)");
            }
            Card(function02, modifier3, enabled3, shape3, colors3, elevation3, null, interactionSource2, function3, $composer3, ($dirty & 14) | 1572864 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (($dirty << 3) & 29360128) | (234881024 & ($dirty << 3)), 0);
            MutableInteractionSource interactionSource4 = interactionSource2;
            $composer4 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource3 = interactionSource4;
            modifier4 = modifier3;
            enabled4 = enabled3;
            shape4 = shape3;
            colors4 = colors3;
            elevation4 = elevation3;
        }
        ScopeUpdateScope endRestartGroup = $composer4.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$ElevatedCard$2
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

                public final void invoke(Composer composer, int i8) {
                    CardKt.ElevatedCard(function0, modifier4, enabled4, shape4, colors4, elevation4, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void OutlinedCard(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        Shape shape3;
        CardColors colors2;
        Composer $composer2;
        CardElevation elevation2;
        BorderStroke border2;
        Modifier modifier3;
        Shape shape4;
        CardColors colors3;
        CardElevation elevation3;
        Composer $composer3;
        final Shape shape5;
        final CardColors colors4;
        final CardElevation elevation4;
        final BorderStroke border3;
        final Modifier modifier4;
        int i2;
        int i3;
        int i4;
        int i5;
        Composer $composer4 = $composer.startRestartGroup(740336179);
        ComposerKt.sourceInformation($composer4, "C(OutlinedCard)P(4,5,1,3)297@13151L13,298@13204L20,299@13270L23,300@13335L20,303@13413L170:Card.kt#uh7d8r");
        int $dirty = $changed;
        int i6 = i & 1;
        if (i6 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i5 = 32;
                    $dirty |= i5;
                }
            } else {
                shape2 = shape;
            }
            i5 = 16;
            $dirty |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                if ($composer4.changed(cardColors)) {
                    i4 = 256;
                    $dirty |= i4;
                }
            } else {
                cardColors = colors;
            }
            i4 = 128;
            $dirty |= i4;
        } else {
            cardColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                if ($composer4.changed(cardElevation)) {
                    i3 = 2048;
                    $dirty |= i3;
                }
            } else {
                cardElevation = elevation;
            }
            i3 = 1024;
            $dirty |= i3;
        } else {
            cardElevation = elevation;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                borderStroke = border;
                if ($composer4.changed(borderStroke)) {
                    i2 = 16384;
                    $dirty |= i2;
                }
            } else {
                borderStroke = border;
            }
            i2 = 8192;
            $dirty |= i2;
        } else {
            borderStroke = border;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            modifier4 = modifier2;
            shape5 = shape2;
            colors4 = cardColors;
            elevation4 = cardElevation;
            border3 = borderStroke;
            $composer3 = $composer4;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer4, 6);
                    $dirty &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.outlinedCardColors($composer4, 6);
                    $dirty &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    $composer2 = $composer4;
                    elevation2 = CardDefaults.INSTANCE.m1838outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty &= -7169;
                } else {
                    $composer2 = $composer4;
                    elevation2 = cardElevation;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    modifier3 = modifier5;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(false, $composer2, 48, 1);
                } else {
                    border2 = border;
                    modifier3 = modifier5;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                shape4 = shape2;
                colors3 = cardColors;
                elevation3 = cardElevation;
                border2 = borderStroke;
                $composer2 = $composer4;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(740336179, $dirty, -1, "androidx.compose.material3.OutlinedCard (Card.kt:303)");
            }
            Composer $composer5 = $composer2;
            Card(modifier3, shape4, colors3, elevation3, border2, function3, $composer5, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 0);
            $composer3 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape5 = shape4;
            colors4 = colors3;
            elevation4 = elevation3;
            border3 = border2;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$OutlinedCard$1
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

                public final void invoke(Composer composer, int i7) {
                    CardKt.OutlinedCard(Modifier.this, shape5, colors4, elevation4, border3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void OutlinedCard(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        CardColors colors2;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        int i2;
        CardElevation elevation2;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        CardElevation elevation3;
        Modifier modifier3;
        BorderStroke border3;
        boolean enabled3;
        Shape shape3;
        CardColors colors3;
        Composer $composer3;
        final Modifier modifier4;
        final boolean enabled4;
        final Shape shape4;
        final CardColors colors4;
        final CardElevation elevation4;
        final BorderStroke border4;
        final MutableInteractionSource interactionSource3;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer4 = $composer.startRestartGroup(-727137250);
        ComposerKt.sourceInformation($composer4, "C(OutlinedCard)P(7,6,4,8,1,3!1,5)352@15710L13,353@15763L20,354@15829L23,355@15894L27,359@16036L271:Card.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer4.changedInstance(function0) ? 4 : 2;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i6 = 2048;
                    $dirty |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 1024;
            $dirty |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer4.changed(colors2)) {
                    i5 = 16384;
                    $dirty |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 8192;
            $dirty |= i5;
        } else {
            colors2 = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                if ($composer4.changed(cardElevation)) {
                    i4 = 131072;
                    $dirty |= i4;
                }
            } else {
                cardElevation = elevation;
            }
            i4 = 65536;
            $dirty |= i4;
        } else {
            cardElevation = elevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                borderStroke = border;
                if ($composer4.changed(borderStroke)) {
                    i3 = 1048576;
                    $dirty |= i3;
                }
            } else {
                borderStroke = border;
            }
            i3 = 524288;
            $dirty |= i3;
        } else {
            borderStroke = border;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
            mutableInteractionSource = interactionSource;
        } else if ((12582912 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty |= $composer4.changed(mutableInteractionSource) ? 8388608 : 4194304;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty & 38347923) == 38347922 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            $composer3 = $composer4;
            enabled4 = enabled2;
            shape4 = shape2;
            elevation4 = cardElevation;
            border4 = borderStroke;
            interactionSource3 = mutableInteractionSource;
            modifier4 = modifier2;
            colors4 = colors2;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = CardDefaults.INSTANCE.getOutlinedShape($composer4, 6);
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    colors2 = CardDefaults.INSTANCE.outlinedCardColors($composer4, 6);
                }
                if ((i & 32) != 0) {
                    i2 = i9;
                    elevation2 = CardDefaults.INSTANCE.m1838outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer4, 1572864, 63);
                    $composer2 = $composer4;
                    $dirty &= -458753;
                } else {
                    $composer2 = $composer4;
                    i2 = i9;
                    elevation2 = elevation;
                }
                if ((i & 64) != 0) {
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(enabled2, $composer2, (($dirty >> 6) & 14) | 48, 0);
                    $dirty &= -3670017;
                } else {
                    border2 = border;
                }
                if (i2 != 0) {
                    interactionSource2 = null;
                    elevation3 = elevation2;
                    modifier3 = modifier2;
                    border3 = border2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    modifier3 = modifier2;
                    border3 = border2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    $composer2 = $composer4;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    elevation3 = cardElevation;
                    border3 = borderStroke;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    shape3 = shape2;
                } else {
                    $composer2 = $composer4;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    elevation3 = cardElevation;
                    border3 = borderStroke;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    shape3 = shape2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-727137250, $dirty, -1, "androidx.compose.material3.OutlinedCard (Card.kt:359)");
            }
            $composer3 = $composer2;
            Card(function0, modifier3, enabled3, shape3, colors3, elevation3, border3, interactionSource2, function3, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            enabled4 = enabled3;
            shape4 = shape3;
            colors4 = colors3;
            elevation4 = elevation3;
            border4 = border3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt$OutlinedCard$2
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

                public final void invoke(Composer composer, int i10) {
                    CardKt.OutlinedCard(function0, modifier4, enabled4, shape4, colors4, elevation4, border4, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
