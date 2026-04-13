package androidx.compose.material3;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.DropdownMenuPositionProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AndroidMenu.android.kt */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001an\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a¢\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001ad\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u0090\u0001\u0010%\u001a\u00020\u00052\u0011\u0010&\u001a\r\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0002\b\u00142\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0015\b\u0002\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t¢\u0006\u0002\b\u00142\b\b\u0002\u0010*\u001a\u00020\u00072\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0007¢\u0006\u0002\u00101\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"DefaultMenuProperties", "Landroidx/compose/ui/window/PopupProperties;", "getDefaultMenuProperties", "()Landroidx/compose/ui/window/PopupProperties;", "DropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "offset", "Landroidx/compose/ui/unit/DpOffset;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "properties", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenu-4kj-_NE", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "DropdownMenu-IlH_yew", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Landroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "DropdownMenu-ILWXrKs", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItem", "text", "onClick", "leadingIcon", "trailingIcon", "enabled", "colors", "Landroidx/compose/material3/MenuItemColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/MenuItemColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidMenu_androidKt {
    private static final PopupProperties DefaultMenuProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);

    /* renamed from: DropdownMenu-IlH_yew, reason: not valid java name */
    public static final void m1769DropdownMenuIlH_yew(final boolean expanded, final Function0<Unit> function0, Modifier modifier, long offset, ScrollState scrollState, PopupProperties properties, Shape shape, long containerColor, float tonalElevation, float shadowElevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function02;
        Modifier modifier2;
        long offset2;
        final ScrollState scrollState2;
        PopupProperties properties2;
        Shape shape2;
        int $dirty;
        int $dirty1;
        int i2;
        int i3;
        int i4;
        Shape shape3;
        long containerColor2;
        final BorderStroke border2;
        final Modifier modifier3;
        final Shape shape4;
        final ScrollState scrollState3;
        final float tonalElevation2;
        final long containerColor3;
        final float shadowElevation2;
        int $dirty2;
        int $dirty12;
        boolean z2;
        Object value$iv;
        final MutableTransitionState expandedState;
        Object value$iv2;
        Object value$iv3;
        long offset3;
        Composer $composer2;
        final long offset4;
        final Modifier modifier4;
        final Shape shape5;
        final long containerColor4;
        final float tonalElevation3;
        final float shadowElevation3;
        final BorderStroke border3;
        final PopupProperties properties3;
        int $dirty3;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(1431928300);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)P(3,6,4,5:c#ui.unit.DpOffset,8,7,10,1:c#ui.graphics.Color,11:c#ui.unit.Dp,9:c#ui.unit.Dp)182@6555L21,185@6667L5,187@6715L14,55@2073L42,59@2260L51,60@2347L7,62@2403L251,72@2825L494,68@2664L655:AndroidMenu.android.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            z = expanded;
        } else if (($changed & 6) == 0) {
            z = expanded;
            $dirty4 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty4 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty4 |= 3072;
            offset2 = offset;
        } else if (($changed & 3072) == 0) {
            offset2 = offset;
            $dirty4 |= $composer3.changed(offset2) ? 2048 : 1024;
        } else {
            offset2 = offset;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrollState2 = scrollState;
                if ($composer3.changed(scrollState2)) {
                    i7 = 16384;
                    $dirty4 |= i7;
                }
            } else {
                scrollState2 = scrollState;
            }
            i7 = 8192;
            $dirty4 |= i7;
        } else {
            scrollState2 = scrollState;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            properties2 = properties;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            properties2 = properties;
            $dirty4 |= $composer3.changed(properties2) ? 131072 : 65536;
        } else {
            properties2 = properties;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 1048576;
                    $dirty4 |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 524288;
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                if ($composer3.changed(containerColor)) {
                    i5 = 8388608;
                    $dirty = $dirty3 | i5;
                }
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            i5 = 4194304;
            $dirty = $dirty3 | i5;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int $dirty13 = $dirty1;
        int $dirty14 = i & 256;
        if ($dirty14 != 0) {
            $dirty |= 100663296;
            i2 = $dirty14;
        } else if (($changed & 100663296) == 0) {
            i2 = $dirty14;
            $dirty |= $composer3.changed(tonalElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = $dirty14;
        }
        int i11 = i & 512;
        if (i11 != 0) {
            $dirty |= 805306368;
            i3 = i11;
        } else if (($changed & 805306368) == 0) {
            i3 = i11;
            $dirty |= $composer3.changed(shadowElevation) ? 536870912 : 268435456;
        } else {
            i3 = i11;
        }
        int i12 = i & 1024;
        if (i12 != 0) {
            $dirty13 |= 6;
            i4 = i12;
        } else if (($changed1 & 6) == 0) {
            i4 = i12;
            $dirty13 |= $composer3.changed(border) ? 4 : 2;
        } else {
            i4 = i12;
        }
        if ((i & 2048) != 0) {
            $dirty13 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty13 |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        int $dirty15 = $dirty13;
        if (($dirty & 306783379) == 306783378 && ($dirty15 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            tonalElevation3 = tonalElevation;
            border3 = border;
            $dirty12 = $dirty15;
            $composer2 = $composer3;
            shape5 = shape2;
            offset4 = offset2;
            modifier4 = modifier2;
            containerColor4 = containerColor;
            shadowElevation3 = shadowElevation;
            properties3 = properties2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                if (i9 != 0) {
                    offset2 = DpKt.m6647DpOffsetYgX7TsA(Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0));
                }
                if ((i & 16) != 0) {
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty &= -57345;
                }
                if (i10 != 0) {
                    properties2 = DefaultMenuProperties;
                }
                if ((i & 64) != 0) {
                    shape3 = MenuDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    shape3 = shape2;
                }
                if ((i & 128) != 0) {
                    containerColor2 = MenuDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    containerColor2 = containerColor;
                }
                float tonalElevation4 = i2 != 0 ? MenuDefaults.INSTANCE.m2232getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i3 != 0 ? MenuDefaults.INSTANCE.m2231getShadowElevationD9Ej5fM() : shadowElevation;
                if (i4 != 0) {
                    modifier3 = modifier5;
                    shape4 = shape3;
                    scrollState3 = scrollState2;
                    tonalElevation2 = tonalElevation4;
                    containerColor3 = containerColor2;
                    shadowElevation2 = shadowElevation4;
                    border2 = null;
                    $dirty2 = $dirty;
                } else {
                    border2 = border;
                    modifier3 = modifier5;
                    shape4 = shape3;
                    scrollState3 = scrollState2;
                    tonalElevation2 = tonalElevation4;
                    containerColor3 = containerColor2;
                    shadowElevation2 = shadowElevation4;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 = $dirty & (-29360129);
                    containerColor3 = containerColor;
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    scrollState3 = scrollState2;
                    shape4 = shape2;
                    modifier3 = modifier2;
                } else {
                    containerColor3 = containerColor;
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    scrollState3 = scrollState2;
                    shape4 = shape2;
                    modifier3 = modifier2;
                    $dirty2 = $dirty;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1431928300, $dirty2, $dirty15, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:54)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1468213501, "CC(remember):AndroidMenu.android.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                $dirty12 = $dirty15;
                z2 = false;
                value$iv = new MutableTransitionState(false);
                $composer3.updateRememberedValue(value$iv);
            } else {
                $dirty12 = $dirty15;
                z2 = false;
                value$iv = it$iv;
            }
            MutableTransitionState expandedState2 = (MutableTransitionState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            expandedState2.setTargetState$animation_core_release(Boolean.valueOf(z));
            if (((Boolean) expandedState2.getCurrentState()).booleanValue() || ((Boolean) expandedState2.getTargetState()).booleanValue()) {
                ComposerKt.sourceInformationMarkerStart($composer3, 1468219494, "CC(remember):AndroidMenu.android.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    expandedState = expandedState2;
                    value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m4540boximpl(TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ()), null, 2, null);
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    expandedState = expandedState2;
                    value$iv2 = it$iv2;
                }
                final MutableState transformOriginState = (MutableState) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density = (Density) consume;
                ComposerKt.sourceInformationMarkerStart($composer3, 1468224270, "CC(remember):AndroidMenu.android.kt#9igjgp");
                boolean invalid$iv = (($dirty2 & 7168) == 2048 ? true : z2) | $composer3.changed(density);
                Object it$iv3 = $composer3.rememberedValue();
                if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    long offset5 = offset2;
                    value$iv3 = new DropdownMenuPositionProvider(offset5, density, 0, new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                            invoke2(intRect, intRect2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IntRect parentBounds, IntRect menuBounds) {
                            transformOriginState.setValue(TransformOrigin.m4540boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
                        }
                    }, 4, null);
                    offset3 = offset5;
                    $composer3.updateRememberedValue(value$iv3);
                } else {
                    offset3 = offset2;
                    value$iv3 = it$iv3;
                }
                DropdownMenuPositionProvider popupPositionProvider = (DropdownMenuPositionProvider) value$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                AndroidPopup_androidKt.Popup(popupPositionProvider, function02, properties2, ComposableLambdaKt.rememberComposableLambda(2126968933, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
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
                        ComposerKt.sourceInformation($composer4, "C73@2839L470:AndroidMenu.android.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2126968933, $changed2, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:73)");
                            }
                            MenuKt.m2245DropdownMenuContentQj0Zi0g(Modifier.this, expandedState, transformOriginState, scrollState3, shape4, containerColor3, tonalElevation2, shadowElevation2, border2, function3, $composer4, (MutableTransitionState.$stable << 3) | 384);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54), $composer3, ($dirty2 & 112) | 3072 | (($dirty2 >> 9) & 896), 0);
                $composer2 = $composer3;
            } else {
                $composer2 = $composer3;
                offset3 = offset2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            offset4 = offset3;
            modifier4 = modifier3;
            scrollState2 = scrollState3;
            shape5 = shape4;
            containerColor4 = containerColor3;
            tonalElevation3 = tonalElevation2;
            shadowElevation3 = shadowElevation2;
            border3 = border2;
            properties3 = properties2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$2
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

                public final void invoke(Composer composer, int i13) {
                    AndroidMenu_androidKt.m1769DropdownMenuIlH_yew(expanded, function0, modifier4, offset4, scrollState2, properties3, shape5, containerColor4, tonalElevation3, shadowElevation3, border3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with parameters for shape, color, elevation, and border.", replaceWith = @ReplaceWith(expression = "DropdownMenu(\n    expanded = expanded,\n    onDismissRequest = onDismissRequest,\n    modifier = modifier,\n    offset = offset,\n    scrollState = scrollState,\n    properties = properties,\n    shape = MenuDefaults.shape,\n    containerColor = MenuDefaults.containerColor,\n    tonalElevation = MenuDefaults.TonalElevation,\n    shadowElevation = MenuDefaults.ShadowElevation,\n    border = null,\n    content = content,\n)", imports = {}))
    /* renamed from: DropdownMenu-4kj-_NE, reason: not valid java name */
    public static final /* synthetic */ void m1767DropdownMenu4kj_NE(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, long offset, ScrollState scrollState, PopupProperties properties, final Function3 content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0 function0;
        Modifier modifier2;
        long offset2;
        ScrollState scrollState2;
        PopupProperties popupProperties;
        int i2;
        Modifier modifier3;
        ScrollState scrollState3;
        PopupProperties properties2;
        int i3;
        long offset3;
        Composer $composer2;
        final Modifier modifier4;
        final long offset4;
        final ScrollState scrollState4;
        final PopupProperties properties3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-1137929566);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,6,5)119@4573L21,130@4960L5,131@5005L14,123@4721L465:AndroidMenu.android.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            z = expanded;
        } else if (($changed & 6) == 0) {
            z = expanded;
            $dirty |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function0 = onDismissRequest;
        } else if (($changed & 48) == 0) {
            function0 = onDismissRequest;
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        } else {
            function0 = onDismissRequest;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            offset2 = offset;
        } else if (($changed & 3072) == 0) {
            offset2 = offset;
            $dirty |= $composer3.changed(offset2) ? 2048 : 1024;
        } else {
            offset2 = offset;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrollState2 = scrollState;
                if ($composer3.changed(scrollState2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                scrollState2 = scrollState;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            scrollState2 = scrollState;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            popupProperties = properties;
        } else if ((196608 & $changed) == 0) {
            popupProperties = properties;
            $dirty |= $composer3.changed(popupProperties) ? 131072 : 65536;
        } else {
            popupProperties = properties;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(content) ? 1048576 : 524288;
        }
        if ((599187 & $dirty) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            offset4 = offset2;
            scrollState4 = scrollState2;
            properties3 = popupProperties;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if (i6 != 0) {
                    i2 = -57345;
                    offset2 = DpKt.m6647DpOffsetYgX7TsA(Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0));
                } else {
                    i2 = -57345;
                }
                if ((i & 16) != 0) {
                    $dirty &= i2;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                }
                if (i7 != 0) {
                    modifier3 = modifier5;
                    offset3 = offset2;
                    scrollState3 = scrollState2;
                    properties2 = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);
                    i3 = -1137929566;
                } else {
                    modifier3 = modifier5;
                    scrollState3 = scrollState2;
                    properties2 = popupProperties;
                    i3 = -1137929566;
                    offset3 = offset2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                modifier3 = modifier2;
                scrollState3 = scrollState2;
                properties2 = popupProperties;
                i3 = -1137929566;
                offset3 = offset2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:123)");
            }
            $composer2 = $composer3;
            m1769DropdownMenuIlH_yew(z, function0, modifier3, offset3, scrollState3, properties2, MenuDefaults.INSTANCE.getShape($composer3, 6), MenuDefaults.INSTANCE.getContainerColor($composer3, 6), MenuDefaults.INSTANCE.m2232getTonalElevationD9Ej5fM(), MenuDefaults.INSTANCE.m2231getShadowElevationD9Ej5fM(), null, content, $composer2, ($dirty & 14) | 905969664 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), (($dirty >> 15) & 112) | 6, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            offset4 = offset3;
            scrollState4 = scrollState3;
            properties3 = properties2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$3
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
                    AndroidMenu_androidKt.m1767DropdownMenu4kj_NE(expanded, onDismissRequest, modifier4, offset4, scrollState4, properties3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced by a DropdownMenu function with a ScrollState parameter", replaceWith = @ReplaceWith(expression = "DropdownMenu(expanded,onDismissRequest, modifier, offset, rememberScrollState(), properties, content)", imports = {"androidx.compose.foundation.rememberScrollState"}))
    /* renamed from: DropdownMenu-ILWXrKs, reason: not valid java name */
    public static final /* synthetic */ void m1768DropdownMenuILWXrKs(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, long offset, PopupProperties properties, final Function3 content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0 function0;
        Modifier modifier2;
        long offset2;
        PopupProperties popupProperties;
        Function3 function3;
        Composer $composer2;
        final Modifier modifier3;
        final long offset3;
        final PopupProperties properties2;
        Composer $composer3 = $composer.startRestartGroup(354826666);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,5)163@6060L21,158@5896L251:AndroidMenu.android.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            z = expanded;
        } else if (($changed & 6) == 0) {
            z = expanded;
            $dirty |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function0 = onDismissRequest;
        } else if (($changed & 48) == 0) {
            function0 = onDismissRequest;
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        } else {
            function0 = onDismissRequest;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            offset2 = offset;
        } else if (($changed & 3072) == 0) {
            offset2 = offset;
            $dirty |= $composer3.changed(offset2) ? 2048 : 1024;
        } else {
            offset2 = offset;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            popupProperties = properties;
        } else if (($changed & 24576) == 0) {
            popupProperties = properties;
            $dirty |= $composer3.changed(popupProperties) ? 16384 : 8192;
        } else {
            popupProperties = properties;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3 = content;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function3 = content;
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        } else {
            function3 = content;
        }
        if ((74899 & $dirty) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier3 = modifier2;
            offset3 = offset2;
            properties2 = popupProperties;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                offset2 = DpKt.m6647DpOffsetYgX7TsA(Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0));
            }
            PopupProperties properties3 = i4 != 0 ? new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null) : popupProperties;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(354826666, $dirty, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:158)");
            }
            $composer2 = $composer3;
            Modifier modifier5 = modifier4;
            long offset4 = offset2;
            m1769DropdownMenuIlH_yew(z, function0, modifier5, offset4, ScrollKt.rememberScrollState(0, $composer3, 0, 1), properties3, null, 0L, 0.0f, 0.0f, null, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752), ($dirty >> 12) & 112, 1984);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            offset3 = offset4;
            properties2 = properties3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$4
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

                public final void invoke(Composer composer, int i5) {
                    AndroidMenu_androidKt.m1768DropdownMenuILWXrKs(expanded, onDismissRequest, modifier3, offset3, properties2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void DropdownMenuItem(final Function2<? super Composer, ? super Integer, Unit> function2, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, boolean enabled, MenuItemColors colors, PaddingValues contentPadding, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function24;
        Function0<Unit> function02;
        Modifier modifier2;
        Function2 leadingIcon;
        Function2 trailingIcon;
        boolean enabled2;
        MenuItemColors colors2;
        int i2;
        int i3;
        int $dirty;
        MutableInteractionSource interactionSource2;
        PaddingValues contentPadding2;
        Function2 trailingIcon2;
        boolean enabled3;
        MenuItemColors colors3;
        int i4;
        Modifier modifier3;
        Function2 leadingIcon2;
        Composer $composer2;
        final Modifier modifier4;
        final Function2 leadingIcon3;
        final Function2 trailingIcon3;
        final boolean enabled4;
        final MenuItemColors colors4;
        final PaddingValues contentPadding3;
        final MutableInteractionSource interactionSource3;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(1826340448);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenuItem)P(7,6,5,4,8,2)194@8925L12,180@6505L319:AndroidMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function24 = function2;
        } else if (($changed & 6) == 0) {
            function24 = function2;
            $dirty2 |= $composer3.changedInstance(function24) ? 4 : 2;
        } else {
            function24 = function2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty2 |= 3072;
            leadingIcon = function22;
        } else if (($changed & 3072) == 0) {
            leadingIcon = function22;
            $dirty2 |= $composer3.changedInstance(leadingIcon) ? 2048 : 1024;
        } else {
            leadingIcon = function22;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty2 |= 24576;
            trailingIcon = function23;
        } else if (($changed & 24576) == 0) {
            trailingIcon = function23;
            $dirty2 |= $composer3.changedInstance(trailingIcon) ? 16384 : 8192;
        } else {
            trailingIcon = function23;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i5 = 1048576;
                    $dirty2 |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 524288;
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty2 |= 12582912;
            i2 = i10;
        } else if (($changed & 12582912) == 0) {
            i2 = i10;
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        } else {
            i2 = i10;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty2 |= 100663296;
            i3 = i11;
        } else if (($changed & 100663296) == 0) {
            i3 = i11;
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i11;
        }
        int $dirty3 = $dirty2;
        if (($dirty2 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource3 = interactionSource;
            $composer2 = $composer3;
            modifier4 = modifier2;
            leadingIcon3 = leadingIcon;
            trailingIcon3 = trailingIcon;
            enabled4 = enabled2;
            colors4 = colors2;
            contentPadding3 = contentPadding;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    leadingIcon = null;
                }
                if (i8 != 0) {
                    trailingIcon = null;
                }
                if (i9 != 0) {
                    enabled2 = true;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty3 & (-3670017);
                    colors2 = MenuDefaults.INSTANCE.itemColors($composer3, 6);
                } else {
                    $dirty = $dirty3;
                }
                PaddingValues contentPadding4 = i2 != 0 ? MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding() : contentPadding;
                if (i3 != 0) {
                    contentPadding2 = contentPadding4;
                    interactionSource2 = null;
                    trailingIcon2 = trailingIcon;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    i4 = 1826340448;
                    modifier3 = modifier2;
                    leadingIcon2 = leadingIcon;
                } else {
                    interactionSource2 = interactionSource;
                    contentPadding2 = contentPadding4;
                    trailingIcon2 = trailingIcon;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    i4 = 1826340448;
                    modifier3 = modifier2;
                    leadingIcon2 = leadingIcon;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty3 & (-3670017);
                    trailingIcon2 = trailingIcon;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    i4 = 1826340448;
                    modifier3 = modifier2;
                    leadingIcon2 = leadingIcon;
                } else {
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty3;
                    trailingIcon2 = trailingIcon;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    i4 = 1826340448;
                    modifier3 = modifier2;
                    leadingIcon2 = leadingIcon;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty, -1, "androidx.compose.material3.DropdownMenuItem (AndroidMenu.android.kt:179)");
            }
            $composer2 = $composer3;
            MenuKt.DropdownMenuItemContent(function24, function02, modifier3, leadingIcon2, trailingIcon2, enabled3, colors3, contentPadding2, interactionSource2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            leadingIcon3 = leadingIcon2;
            trailingIcon3 = trailingIcon2;
            enabled4 = enabled3;
            colors4 = colors3;
            contentPadding3 = contentPadding2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenuItem$1
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

                public final void invoke(Composer composer, int i12) {
                    AndroidMenu_androidKt.DropdownMenuItem(function2, function0, modifier4, leadingIcon3, trailingIcon3, enabled4, colors4, contentPadding3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final PopupProperties getDefaultMenuProperties() {
        return DefaultMenuProperties;
    }
}
