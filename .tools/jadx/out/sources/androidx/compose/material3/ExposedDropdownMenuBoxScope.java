package androidx.compose.material3;

import android.view.View;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
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

/* compiled from: ExposedDropdownMenu.android.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002JU\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0016J\u0098\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J¢\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010#\u001a\u00020\n2\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u0016\u0010&\u001a\u00020\u000e*\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\nH&J\f\u0010'\u001a\u00020\u000e*\u00020\u000eH\u0007J(\u0010'\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010(\u001a\u00020\u00042\b\b\u0002\u0010)\u001a\u00020\nH&ø\u0001\u0000¢\u0006\u0004\b*\u0010+R\u0018\u0010\u0003\u001a\u00020\u0004X \u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001,\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006-"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuBoxScope;", "", "()V", "anchorType", "Landroidx/compose/material3/MenuAnchorType;", "getAnchorType-Mg6Rgbw$material3_release", "()Ljava/lang/String;", "ExposedDropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "matchTextFieldWidth", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "ExposedDropdownMenu-vNxi1II", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;ZLandroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "focusable", "ExposedDropdownMenu-kbRbctU", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;ZZLandroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "exposedDropdownSize", "menuAnchor", "type", "enabled", "menuAnchor-fsE2BvY", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Z)Landroidx/compose/ui/Modifier;", "Landroidx/compose/material3/ExposedDropdownMenuBoxScopeImpl;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public abstract class ExposedDropdownMenuBoxScope {
    public static final int $stable = 0;

    public /* synthetic */ ExposedDropdownMenuBoxScope(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Modifier exposedDropdownSize(Modifier modifier, boolean z);

    /* renamed from: getAnchorType-Mg6Rgbw$material3_release, reason: not valid java name */
    public abstract String mo2086getAnchorTypeMg6Rgbw$material3_release();

    /* renamed from: menuAnchor-fsE2BvY, reason: not valid java name */
    public abstract Modifier mo2087menuAnchorfsE2BvY(Modifier modifier, String str, boolean z);

    private ExposedDropdownMenuBoxScope() {
    }

    /* renamed from: menuAnchor-fsE2BvY$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2083menuAnchorfsE2BvY$default(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: menuAnchor-fsE2BvY");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return exposedDropdownMenuBoxScope.mo2087menuAnchorfsE2BvY(modifier, str, z);
    }

    public static /* synthetic */ Modifier exposedDropdownSize$default(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exposedDropdownSize");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return exposedDropdownMenuBoxScope.exposedDropdownSize(modifier, z);
    }

    /* renamed from: ExposedDropdownMenu-vNxi1II, reason: not valid java name */
    public final void m2085ExposedDropdownMenuvNxi1II(final boolean expanded, final Function0<Unit> function0, Modifier modifier, ScrollState scrollState, boolean matchTextFieldWidth, Shape shape, long containerColor, float tonalElevation, float shadowElevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        ScrollState scrollState2;
        boolean matchTextFieldWidth2;
        Shape shape2;
        int i2;
        long containerColor2;
        int i3;
        int i4;
        final BorderStroke border2;
        final Modifier modifier3;
        final boolean matchTextFieldWidth3;
        final float tonalElevation2;
        final Shape shape3;
        final long containerColor3;
        final float shadowElevation2;
        final ScrollState scrollState3;
        Object value$iv;
        Object value$iv2;
        int $dirty;
        Object value$iv3;
        Object value$iv4;
        Composer $composer2;
        int $dirty1;
        final Modifier modifier4;
        final boolean matchTextFieldWidth4;
        final ScrollState scrollState4;
        final Shape shape4;
        final long containerColor4;
        final float tonalElevation3;
        final float shadowElevation3;
        final BorderStroke border3;
        Object value$iv5;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(720925481);
        ComposerKt.sourceInformation($composer3, "C(ExposedDropdownMenu)P(3,6,5,7,4,9,1:c#ui.graphics.Color,10:c#ui.unit.Dp,8:c#ui.unit.Dp)336@15550L21,338@15654L5,339@15706L14,347@16155L53,348@16238L7,349@16281L7,350@16332L10,357@16596L42,361@16795L51,363@16903L486,377@17583L27,378@17626L587,374@17403L810:ExposedDropdownMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(expanded) ? 4 : 2;
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
        int i8 = i & 4;
        if (i8 != 0) {
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
                scrollState2 = scrollState;
                if ($composer3.changed(scrollState2)) {
                    i7 = 2048;
                    $dirty2 |= i7;
                }
            } else {
                scrollState2 = scrollState;
            }
            i7 = 1024;
            $dirty2 |= i7;
        } else {
            scrollState2 = scrollState;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= 24576;
            matchTextFieldWidth2 = matchTextFieldWidth;
        } else if (($changed & 24576) == 0) {
            matchTextFieldWidth2 = matchTextFieldWidth;
            $dirty2 |= $composer3.changed(matchTextFieldWidth2) ? 16384 : 8192;
        } else {
            matchTextFieldWidth2 = matchTextFieldWidth;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 131072;
                    $dirty2 |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 65536;
            $dirty2 |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                i2 = i8;
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i5 = 1048576;
                    $dirty2 |= i5;
                }
            } else {
                i2 = i8;
                containerColor2 = containerColor;
            }
            i5 = 524288;
            $dirty2 |= i5;
        } else {
            i2 = i8;
            containerColor2 = containerColor;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(tonalElevation) ? 8388608 : 4194304;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty2 |= 100663296;
            i3 = i11;
        } else if (($changed & 100663296) == 0) {
            i3 = i11;
            $dirty2 |= $composer3.changed(shadowElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i11;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty2 |= 805306368;
            i4 = i12;
        } else if (($changed & 805306368) == 0) {
            i4 = i12;
            $dirty2 |= $composer3.changed(border) ? 536870912 : 268435456;
        } else {
            i4 = i12;
        }
        if ((i & 1024) != 0) {
            $dirty12 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty12 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty12 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty12 |= $composer3.changed(this) ? 32 : 16;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty12 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            $dirty1 = $dirty12;
            matchTextFieldWidth4 = matchTextFieldWidth2;
            shape4 = shape2;
            containerColor4 = containerColor2;
            modifier4 = modifier2;
            scrollState4 = scrollState2;
            tonalElevation3 = tonalElevation;
            shadowElevation3 = shadowElevation;
            border3 = border;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty2 &= -7169;
                }
                if (i9 != 0) {
                    matchTextFieldWidth2 = true;
                }
                if ((i & 32) != 0) {
                    shape2 = MenuDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    containerColor2 = MenuDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty2 &= -3670017;
                }
                float tonalElevation4 = i10 != 0 ? MenuDefaults.INSTANCE.m2232getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i3 != 0 ? MenuDefaults.INSTANCE.m2231getShadowElevationD9Ej5fM() : shadowElevation;
                if (i4 != 0) {
                    modifier3 = modifier5;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    tonalElevation2 = tonalElevation4;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    shadowElevation2 = shadowElevation4;
                    scrollState3 = scrollState2;
                    border2 = null;
                } else {
                    border2 = border;
                    modifier3 = modifier5;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    tonalElevation2 = tonalElevation4;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    shadowElevation2 = shadowElevation4;
                    scrollState3 = scrollState2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    $dirty2 = (-3670017) & $dirty2;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    modifier3 = modifier2;
                    scrollState3 = scrollState2;
                } else {
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    modifier3 = modifier2;
                    scrollState3 = scrollState2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(720925481, $dirty2, $dirty12, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.android.kt:344)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 321492941, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState keyboardSignalState = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            View view = (View) consume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density = (Density) consume2;
            int topWindowInsets = WindowInsets_androidKt.getStatusBars(WindowInsets.INSTANCE, $composer3, 6).getTop(density);
            $composer3.startReplaceGroup(321499814);
            ComposerKt.sourceInformation($composer3, "353@16432L36,353@16396L72");
            if (expanded) {
                ComposerKt.sourceInformationMarkerStart($composer3, 321501788, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv5 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            keyboardSignalState.setValue(Unit.INSTANCE);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ExposedDropdownMenu_androidKt.SoftKeyboardListener(view, density, (Function0) value$iv5, $composer3, 384);
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart($composer3, 321507042, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new MutableTransitionState(false);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv3;
            }
            final MutableTransitionState expandedState = (MutableTransitionState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            expandedState.setTargetState$animation_core_release(Boolean.valueOf(expanded));
            if (((Boolean) expandedState.getCurrentState()).booleanValue() || ((Boolean) expandedState.getTargetState()).booleanValue()) {
                ComposerKt.sourceInformationMarkerStart($composer3, 321513419, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                Object it$iv4 = $composer3.rememberedValue();
                if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                    $dirty = $dirty2;
                    value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m4540boximpl(TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ()), null, 2, null);
                    $composer3.updateRememberedValue(value$iv3);
                } else {
                    $dirty = $dirty2;
                    value$iv3 = it$iv4;
                }
                final MutableState transformOriginState = (MutableState) value$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 321517310, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                boolean invalid$iv = $composer3.changed(density) | $composer3.changed(topWindowInsets);
                Object it$iv5 = $composer3.rememberedValue();
                if (invalid$iv || it$iv5 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new ExposedDropdownMenuPositionProvider(density, topWindowInsets, keyboardSignalState, 0, new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$popupPositionProvider$1$1
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
                        public final void invoke2(IntRect anchorBounds, IntRect menuBounds) {
                            transformOriginState.setValue(TransformOrigin.m4540boximpl(MenuKt.calculateTransformOrigin(anchorBounds, menuBounds)));
                        }
                    }, 8, null);
                    $composer3.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv5;
                }
                ExposedDropdownMenuPositionProvider popupPositionProvider = (ExposedDropdownMenuPositionProvider) value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                AndroidPopup_androidKt.Popup(popupPositionProvider, function02, ExposedDropdownMenuDefaults.INSTANCE.m2091popupPropertiespR6Bxps$material3_release(mo2086getAnchorTypeMg6Rgbw$material3_release(), $composer3, 48), ComposableLambdaKt.rememberComposableLambda(-1082380263, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$2
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
                        ComposerKt.sourceInformation($composer4, "C379@17644L555:ExposedDropdownMenu.android.kt#uh7d8r");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1082380263, $changed2, -1, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.<anonymous> (ExposedDropdownMenu.android.kt:379)");
                            }
                            MenuKt.m2245DropdownMenuContentQj0Zi0g(ExposedDropdownMenuBoxScope.this.exposedDropdownSize(modifier3, matchTextFieldWidth3), expandedState, transformOriginState, scrollState3, shape3, containerColor3, tonalElevation2, shadowElevation2, border2, function3, $composer4, (MutableTransitionState.$stable << 3) | 384);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54), $composer3, ($dirty & 112) | 3072, 0);
                $composer2 = $composer3;
            } else {
                $composer2 = $composer3;
                $dirty = $dirty2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $dirty1 = $dirty12;
            modifier4 = modifier3;
            matchTextFieldWidth4 = matchTextFieldWidth3;
            scrollState4 = scrollState3;
            shape4 = shape3;
            containerColor4 = containerColor3;
            tonalElevation3 = tonalElevation2;
            shadowElevation3 = shadowElevation2;
            border3 = border2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$3
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
                    ExposedDropdownMenuBoxScope.this.m2085ExposedDropdownMenuvNxi1II(expanded, function0, modifier4, scrollState4, matchTextFieldWidth4, shape4, containerColor4, tonalElevation3, shadowElevation3, border3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use overload that takes MenuAnchorType and enabled parameters", replaceWith = @ReplaceWith(expression = "menuAnchor(type, enabled)", imports = {}))
    public final Modifier menuAnchor(Modifier $this$menuAnchor) {
        return m2083menuAnchorfsE2BvY$default(this, $this$menuAnchor, MenuAnchorType.INSTANCE.m2229getPrimaryNotEditableMg6Rgbw(), false, 2, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "The `focusable` parameter is unused. Pass the proper MenuAnchorType to Modifier.menuAnchor instead, which will handle focusability automatically.")
    /* renamed from: ExposedDropdownMenu-kbRbctU, reason: not valid java name */
    public final void m2084ExposedDropdownMenukbRbctU(final boolean expanded, final Function0<Unit> function0, Modifier modifier, ScrollState scrollState, boolean focusable, boolean matchTextFieldWidth, Shape shape, long containerColor, float tonalElevation, float shadowElevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function02;
        Modifier modifier2;
        ScrollState scrollState2;
        boolean matchTextFieldWidth2;
        Shape shape2;
        long containerColor2;
        int i2;
        int i3;
        ScrollState scrollState3;
        boolean focusable2;
        BorderStroke border2;
        Modifier modifier3;
        float tonalElevation2;
        ScrollState scrollState4;
        boolean matchTextFieldWidth3;
        Shape shape3;
        float shadowElevation2;
        long containerColor3;
        int i4;
        Composer $composer2;
        int $dirty1;
        final boolean focusable3;
        final Modifier modifier4;
        final ScrollState scrollState5;
        final boolean matchTextFieldWidth4;
        final Shape shape4;
        final long containerColor4;
        final float tonalElevation3;
        final float shadowElevation3;
        final BorderStroke border3;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(366140493);
        ComposerKt.sourceInformation($composer3, "C(ExposedDropdownMenu)P(3,7,6,8,4,5,10,1:c#ui.graphics.Color,11:c#ui.unit.Dp,9:c#ui.unit.Dp)414@19050L21,417@19189L5,418@19241L14,424@19482L463:ExposedDropdownMenu.android.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty12 = $changed1;
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
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                scrollState2 = scrollState;
                if ($composer3.changed(scrollState2)) {
                    i7 = 2048;
                    $dirty |= i7;
                }
            } else {
                scrollState2 = scrollState;
            }
            i7 = 1024;
            $dirty |= i7;
        } else {
            scrollState2 = scrollState;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            matchTextFieldWidth2 = matchTextFieldWidth;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            matchTextFieldWidth2 = matchTextFieldWidth;
            $dirty |= $composer3.changed(matchTextFieldWidth2) ? 131072 : 65536;
        } else {
            matchTextFieldWidth2 = matchTextFieldWidth;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i6 = 1048576;
                    $dirty |= i6;
                }
            } else {
                shape2 = shape;
            }
            i6 = 524288;
            $dirty |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i5 = 8388608;
                    $dirty |= i5;
                }
            } else {
                containerColor2 = containerColor;
            }
            i5 = 4194304;
            $dirty |= i5;
        } else {
            containerColor2 = containerColor;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(tonalElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i11 = i & 512;
        if (i11 != 0) {
            $dirty |= 805306368;
            i2 = i11;
        } else if (($changed & 805306368) == 0) {
            i2 = i11;
            $dirty |= $composer3.changed(shadowElevation) ? 536870912 : 268435456;
        } else {
            i2 = i11;
        }
        int i12 = i & 1024;
        if (i12 != 0) {
            $dirty12 |= 6;
            i3 = i12;
        } else if (($changed1 & 6) == 0) {
            i3 = i12;
            $dirty12 |= $composer3.changed(border) ? 4 : 2;
        } else {
            i3 = i12;
        }
        if ((i & 2048) != 0) {
            $dirty12 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty12 |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty12 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty12 |= $composer3.changed(this) ? 256 : 128;
        }
        if (($dirty & 306775187) == 306775186 && ($dirty12 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            focusable3 = focusable;
            shadowElevation3 = shadowElevation;
            border3 = border;
            $composer2 = $composer3;
            $dirty1 = $dirty12;
            modifier4 = modifier2;
            scrollState5 = scrollState2;
            matchTextFieldWidth4 = matchTextFieldWidth2;
            shape4 = shape2;
            containerColor4 = containerColor2;
            tonalElevation3 = tonalElevation;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty &= -7169;
                } else {
                    scrollState3 = scrollState2;
                }
                focusable2 = (i & 16) != 0 ? true : focusable;
                if (i9 != 0) {
                    matchTextFieldWidth2 = true;
                }
                if ((i & 64) != 0) {
                    shape2 = MenuDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    containerColor2 = MenuDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty &= -29360129;
                }
                float tonalElevation4 = i10 != 0 ? MenuDefaults.INSTANCE.m2232getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i2 != 0 ? MenuDefaults.INSTANCE.m2231getShadowElevationD9Ej5fM() : shadowElevation;
                if (i3 != 0) {
                    modifier3 = modifier5;
                    tonalElevation2 = tonalElevation4;
                    scrollState4 = scrollState3;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    shape3 = shape2;
                    shadowElevation2 = shadowElevation4;
                    border2 = null;
                    containerColor3 = containerColor2;
                    i4 = 366140493;
                } else {
                    border2 = border;
                    modifier3 = modifier5;
                    tonalElevation2 = tonalElevation4;
                    scrollState4 = scrollState3;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    shape3 = shape2;
                    shadowElevation2 = shadowElevation4;
                    containerColor3 = containerColor2;
                    i4 = 366140493;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    $dirty = (-29360129) & $dirty;
                    modifier3 = modifier2;
                    scrollState4 = scrollState2;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    i4 = 366140493;
                    focusable2 = focusable;
                } else {
                    tonalElevation2 = tonalElevation;
                    shadowElevation2 = shadowElevation;
                    border2 = border;
                    modifier3 = modifier2;
                    scrollState4 = scrollState2;
                    matchTextFieldWidth3 = matchTextFieldWidth2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    i4 = 366140493;
                    focusable2 = focusable;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty, $dirty12, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.android.kt:424)");
            }
            $composer2 = $composer3;
            m2085ExposedDropdownMenuvNxi1II(z, function02, modifier3, scrollState4, matchTextFieldWidth3, shape3, containerColor3, tonalElevation2, shadowElevation2, border2, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty >> 3) & 234881024) | (($dirty12 << 27) & 1879048192), (($dirty12 >> 3) & 14) | (($dirty12 >> 3) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $dirty1 = $dirty12;
            focusable3 = focusable2;
            modifier4 = modifier3;
            scrollState5 = scrollState4;
            matchTextFieldWidth4 = matchTextFieldWidth3;
            shape4 = shape3;
            containerColor4 = containerColor3;
            tonalElevation3 = tonalElevation2;
            shadowElevation3 = shadowElevation2;
            border3 = border2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$4
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
                    ExposedDropdownMenuBoxScope.this.m2084ExposedDropdownMenukbRbctU(expanded, function0, modifier4, scrollState5, focusable3, matchTextFieldWidth4, shape4, containerColor4, tonalElevation3, shadowElevation3, border3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with customization options parameters.")
    public final /* synthetic */ void ExposedDropdownMenu(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, ScrollState scrollState, final Function3 content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0 function0;
        final Modifier modifier2;
        final ScrollState scrollState2;
        Function3 function3;
        ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope;
        Modifier modifier3;
        ScrollState scrollState3;
        Composer $composer2;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(1729549851);
        ComposerKt.sourceInformation($composer3, "C(ExposedDropdownMenu)P(1,3,2,4)449@20337L21,458@20677L5,459@20726L14,452@20429L498:ExposedDropdownMenu.android.kt#uh7d8r");
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
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                scrollState2 = scrollState;
                if ($composer3.changed(scrollState2)) {
                    i2 = 2048;
                    $dirty |= i2;
                }
            } else {
                scrollState2 = scrollState;
            }
            i2 = 1024;
            $dirty |= i2;
        } else {
            scrollState2 = scrollState;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
            function3 = content;
        } else if (($changed & 24576) == 0) {
            function3 = content;
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        } else {
            function3 = content;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            exposedDropdownMenuBoxScope = this;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            exposedDropdownMenuBoxScope = this;
            $dirty |= $composer3.changed(exposedDropdownMenuBoxScope) ? 131072 : 65536;
        } else {
            exposedDropdownMenuBoxScope = this;
        }
        if ((74899 & $dirty) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    modifier3 = modifier4;
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                } else {
                    modifier3 = modifier4;
                    scrollState3 = scrollState2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier3 = modifier2;
                scrollState3 = scrollState2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1729549851, $dirty, -1, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.android.kt:452)");
            }
            $composer2 = $composer3;
            exposedDropdownMenuBoxScope.m2085ExposedDropdownMenuvNxi1II(z, function0, modifier3, scrollState3, true, MenuDefaults.INSTANCE.getShape($composer3, 6), MenuDefaults.INSTANCE.getContainerColor($composer3, 6), MenuDefaults.INSTANCE.m2232getTonalElevationD9Ej5fM(), MenuDefaults.INSTANCE.m2231getShadowElevationD9Ej5fM(), null, function3, $composer2, ($dirty & 14) | 918577152 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), (($dirty >> 12) & 14) | (($dirty >> 12) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            scrollState2 = scrollState3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$5
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

                public final void invoke(Composer composer, int i4) {
                    ExposedDropdownMenuBoxScope.this.ExposedDropdownMenu(expanded, onDismissRequest, modifier2, scrollState2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
