package androidx.compose.material;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: ExposedDropdownMenu.android.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JU\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00040\u000e¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\n*\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u0006H&¨\u0006\u0015"}, d2 = {"Landroidx/compose/material/ExposedDropdownMenuBoxScope;", "", "()V", "ExposedDropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "exposedDropdownSize", "matchTextFieldWidth", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ExposedDropdownMenuBoxScope {
    public static final int $stable = 0;

    public abstract Modifier exposedDropdownSize(Modifier modifier, boolean z);

    public static /* synthetic */ Modifier exposedDropdownSize$default(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exposedDropdownSize");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return exposedDropdownMenuBoxScope.exposedDropdownSize(modifier, z);
    }

    public final void ExposedDropdownMenu(boolean expanded, final Function0<Unit> function0, Modifier modifier, ScrollState scrollState, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        ScrollState scrollState2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32;
        final ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope;
        final Modifier modifier3;
        final ScrollState scrollState3;
        int $dirty;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Composer $composer2;
        final ScrollState scrollState4;
        final Modifier modifier4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(63962088);
        ComposerKt.sourceInformation($composer3, "C(ExposedDropdownMenu)P(1,3,2,4)234@9061L21,246@9561L42:ExposedDropdownMenu.android.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            z = expanded;
        } else if (($changed & 6) == 0) {
            z = expanded;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 32 : 16;
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
                scrollState2 = scrollState;
                if ($composer3.changed(scrollState2)) {
                    i2 = 2048;
                    $dirty2 |= i2;
                }
            } else {
                scrollState2 = scrollState;
            }
            i2 = 1024;
            $dirty2 |= i2;
        } else {
            scrollState2 = scrollState;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
            function32 = function3;
        } else if (($changed & 24576) == 0) {
            function32 = function3;
            $dirty2 |= $composer3.changedInstance(function32) ? 16384 : 8192;
        } else {
            function32 = function3;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            exposedDropdownMenuBoxScope = this;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            exposedDropdownMenuBoxScope = this;
            $dirty2 |= $composer3.changed(exposedDropdownMenuBoxScope) ? 131072 : 65536;
        } else {
            exposedDropdownMenuBoxScope = this;
        }
        if ((74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            scrollState4 = scrollState2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    $dirty = $dirty2 & (-7169);
                    modifier3 = modifier5;
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                } else {
                    modifier3 = modifier5;
                    scrollState3 = scrollState2;
                    $dirty = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                modifier3 = modifier2;
                scrollState3 = scrollState2;
                $dirty = $dirty2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(63962088, $dirty, -1, "androidx.compose.material.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.android.kt:236)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1129923617, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MutableTransitionState(false);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableTransitionState expandedStates = (MutableTransitionState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            expandedStates.setTargetState$animation_core_release(Boolean.valueOf(z));
            if (((Boolean) expandedStates.getCurrentState()).booleanValue() || ((Boolean) expandedStates.getTargetState()).booleanValue()) {
                $composer3.startReplaceGroup(-667706424);
                ComposerKt.sourceInformation($composer3, "250@9763L51,251@9854L7,255@10001L139,262@10309L336,259@10154L491");
                ComposerKt.sourceInformationMarkerStart($composer3, -1129917144, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m4540boximpl(TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ()), null, 2, null);
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                final MutableState transformOriginState = (MutableState) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density = (Density) consume;
                long m6697getZeroRKDOV3M = DpOffset.INSTANCE.m6697getZeroRKDOV3M();
                ComposerKt.sourceInformationMarkerStart($composer3, -1129909440, "CC(remember):ExposedDropdownMenu.android.kt#9igjgp");
                Object it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$popupPositionProvider$1$1
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
                    };
                    $composer3.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                DropdownMenuPositionProvider popupPositionProvider = new DropdownMenuPositionProvider(m6697getZeroRKDOV3M, density, (Function2) value$iv3, null);
                final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33 = function32;
                ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup(function0, popupPositionProvider, ComposableLambdaKt.rememberComposableLambda(1001349006, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$1
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
                        ComposerKt.sourceInformation($composer4, "C263@10327L304:ExposedDropdownMenu.android.kt#jmzs0o");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1001349006, $changed2, -1, "androidx.compose.material.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.<anonymous> (ExposedDropdownMenu.android.kt:263)");
                            }
                            MenuKt.DropdownMenuContent(expandedStates, transformOriginState, scrollState3, ExposedDropdownMenuBoxScope.exposedDropdownSize$default(exposedDropdownMenuBoxScope, modifier3, false, 1, null), function33, $composer4, MutableTransitionState.$stable | 48, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 384, 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-666809811);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            scrollState4 = scrollState3;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final boolean z2 = z;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$2
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
                    ExposedDropdownMenuBoxScope.this.ExposedDropdownMenu(z2, function0, modifier4, scrollState4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
