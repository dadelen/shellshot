package androidx.compose.material;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
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
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
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
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001an\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001ad\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001ac\u0010\u0017\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u001f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"DropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "offset", "Landroidx/compose/ui/unit/DpOffset;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenu-4kj-_NE", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenu-ILWXrKs", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItem", "onClick", "enabled", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/layout/RowScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidMenu_androidKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced by a DropdownMenu function with a ScrollState parameter", replaceWith = @ReplaceWith(expression = "DropdownMenu(expanded,onDismissRequest, modifier, offset, rememberScrollState(), properties, content)", imports = {"androidx.compose.foundation.rememberScrollState"}))
    /* renamed from: DropdownMenu-ILWXrKs, reason: not valid java name */
    public static final /* synthetic */ void m1401DropdownMenuILWXrKs(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, long offset, PopupProperties properties, final Function3 content, Composer $composer, final int $changed, final int i) {
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
        Composer $composer3 = $composer.startRestartGroup(-840283139);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,5)59@2347L21,54@2203L219:AndroidMenu.android.kt#jmzs0o");
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
                ComposerKt.traceEventStart(-840283139, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:54)");
            }
            $composer2 = $composer3;
            Modifier modifier5 = modifier4;
            long offset4 = offset2;
            m1400DropdownMenu4kj_NE(z, function0, modifier5, offset4, ScrollKt.rememberScrollState(0, $composer3, 0, 1), properties3, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (3670016 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            offset3 = offset4;
            properties2 = properties3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$1
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
                    AndroidMenu_androidKt.m1401DropdownMenuILWXrKs(expanded, onDismissRequest, modifier3, offset3, properties2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: DropdownMenu-4kj-_NE, reason: not valid java name */
    public static final void m1400DropdownMenu4kj_NE(boolean expanded, final Function0<Unit> function0, Modifier modifier, long offset, ScrollState scrollState, PopupProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0<Unit> function02;
        Modifier modifier2;
        long offset2;
        ScrollState scrollState2;
        PopupProperties popupProperties;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32;
        int $dirty;
        final Modifier modifier3;
        long offset3;
        final ScrollState scrollState3;
        PopupProperties properties2;
        Object value$iv;
        int $dirty2;
        final MutableTransitionState expandedStates;
        Object value$iv2;
        Object value$iv3;
        Composer $composer2;
        final PopupProperties properties3;
        final ScrollState scrollState4;
        final Modifier modifier4;
        final long offset4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(-2135362555);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,6,5)116@5501L21,120@5670L42:AndroidMenu.android.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            z = expanded;
        } else if (($changed & 6) == 0) {
            z = expanded;
            $dirty3 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            offset2 = offset;
        } else if (($changed & 3072) == 0) {
            offset2 = offset;
            $dirty3 |= $composer3.changed(offset2) ? 2048 : 1024;
        } else {
            offset2 = offset;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrollState2 = scrollState;
                if ($composer3.changed(scrollState2)) {
                    i2 = 16384;
                    $dirty3 |= i2;
                }
            } else {
                scrollState2 = scrollState;
            }
            i2 = 8192;
            $dirty3 |= i2;
        } else {
            scrollState2 = scrollState;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            popupProperties = properties;
        } else if ((196608 & $changed) == 0) {
            popupProperties = properties;
            $dirty3 |= $composer3.changed(popupProperties) ? 131072 : 65536;
        } else {
            popupProperties = properties;
        }
        if ((i & 64) != 0) {
            $dirty3 |= 1572864;
            function32 = function3;
        } else if (($changed & 1572864) == 0) {
            function32 = function3;
            $dirty3 |= $composer3.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if (($dirty3 & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            offset4 = offset2;
            modifier4 = modifier2;
            scrollState4 = scrollState2;
            properties3 = popupProperties;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    offset2 = DpKt.m6647DpOffsetYgX7TsA(Dp.m6626constructorimpl(0), Dp.m6626constructorimpl(0));
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                }
                if (i5 != 0) {
                    $dirty = $dirty3;
                    modifier3 = modifier2;
                    offset3 = offset2;
                    scrollState3 = scrollState2;
                    properties2 = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);
                } else {
                    $dirty = $dirty3;
                    modifier3 = modifier2;
                    offset3 = offset2;
                    scrollState3 = scrollState2;
                    properties2 = popupProperties;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                $dirty = $dirty3;
                modifier3 = modifier2;
                offset3 = offset2;
                scrollState3 = scrollState2;
                properties2 = popupProperties;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2135362555, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:119)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 875822944, "CC(remember):AndroidMenu.android.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MutableTransitionState(false);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MutableTransitionState expandedStates2 = (MutableTransitionState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            expandedStates2.setTargetState$animation_core_release(Boolean.valueOf(z));
            if (((Boolean) expandedStates2.getCurrentState()).booleanValue() || ((Boolean) expandedStates2.getTargetState()).booleanValue()) {
                $composer3.startReplaceGroup(1380883942);
                ComposerKt.sourceInformation($composer3, "124@5860L51,125@5947L7,129@6071L131,137@6373L282,133@6212L443");
                ComposerKt.sourceInformationMarkerStart($composer3, 875829033, "CC(remember):AndroidMenu.android.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    $dirty2 = $dirty;
                    expandedStates = expandedStates2;
                    value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m4540boximpl(TransformOrigin.INSTANCE.m4553getCenterSzJe1aQ()), null, 2, null);
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    $dirty2 = $dirty;
                    expandedStates = expandedStates2;
                    value$iv2 = it$iv2;
                }
                final MutableState transformOriginState = (MutableState) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density = (Density) consume;
                ComposerKt.sourceInformationMarkerStart($composer3, 875835865, "CC(remember):AndroidMenu.android.kt#9igjgp");
                Object it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
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
                DropdownMenuPositionProvider popupPositionProvider = new DropdownMenuPositionProvider(offset3, density, (Function2) value$iv3, null);
                final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33 = function32;
                AndroidPopup_androidKt.Popup(popupPositionProvider, function02, properties2, ComposableLambdaKt.rememberComposableLambda(-47803778, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$2
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
                        ComposerKt.sourceInformation($composer4, "C138@6387L258:AndroidMenu.android.kt#jmzs0o");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-47803778, $changed2, -1, "androidx.compose.material.DropdownMenu.<anonymous> (AndroidMenu.android.kt:138)");
                            }
                            MenuKt.DropdownMenuContent(expandedStates, transformOriginState, scrollState3, modifier3, function33, $composer4, MutableTransitionState.$stable | 48, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54), $composer3, (($dirty2 >> 9) & 896) | ($dirty2 & 112) | 3072, 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1381689260);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties3 = properties2;
            scrollState4 = scrollState3;
            modifier4 = modifier3;
            offset4 = offset3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final boolean z2 = z;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$3
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
                    AndroidMenu_androidKt.m1400DropdownMenu4kj_NE(z2, function0, modifier4, offset4, scrollState4, properties3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void DropdownMenuItem(final Function0<Unit> function0, Modifier modifier, boolean enabled, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean z;
        PaddingValues contentPadding2;
        MutableInteractionSource mutableInteractionSource;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function32;
        final Modifier modifier3;
        final boolean enabled2;
        final PaddingValues contentPadding3;
        final MutableInteractionSource interactionSource2;
        Composer $composer2 = $composer.startRestartGroup(-1988562892);
        ComposerKt.sourceInformation($composer2, "C(DropdownMenuItem)P(5,4,2,1,3)175@7877L227:AndroidMenu.android.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            contentPadding2 = contentPadding;
        } else if (($changed & 3072) == 0) {
            contentPadding2 = contentPadding;
            $dirty |= $composer2.changed(contentPadding2) ? 2048 : 1024;
        } else {
            contentPadding2 = contentPadding;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 24576) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty |= $composer2.changed(mutableInteractionSource) ? 16384 : 8192;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function32 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled2 = z;
            contentPadding3 = contentPadding2;
            interactionSource2 = mutableInteractionSource;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            boolean enabled3 = i3 != 0 ? true : z;
            if (i4 != 0) {
                contentPadding2 = MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding();
            }
            MutableInteractionSource interactionSource3 = i5 != 0 ? null : mutableInteractionSource;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1988562892, $dirty, -1, "androidx.compose.material.DropdownMenuItem (AndroidMenu.android.kt:174)");
            }
            boolean enabled4 = enabled3;
            PaddingValues contentPadding4 = contentPadding2;
            Modifier modifier5 = modifier4;
            MenuKt.DropdownMenuItemContent(function02, modifier5, enabled4, contentPadding4, interactionSource3, function32, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            enabled2 = enabled4;
            contentPadding3 = contentPadding4;
            interactionSource2 = interactionSource3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenuItem$1
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
                    AndroidMenu_androidKt.DropdownMenuItem(function0, modifier3, enabled2, contentPadding3, interactionSource2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
