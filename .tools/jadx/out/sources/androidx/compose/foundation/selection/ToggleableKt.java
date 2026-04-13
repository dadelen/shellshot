package androidx.compose.foundation.selection;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: Toggleable.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aX\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fø\u0001\u0000¢\u0006\u0002\b\u000e\u001aD\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fø\u0001\u0000¢\u0006\u0002\b\u000f\u001aR\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014ø\u0001\u0000¢\u0006\u0002\b\u0015\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014ø\u0001\u0000¢\u0006\u0002\b\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"toggleable", "Landroidx/compose/ui/Modifier;", "value", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indication", "Landroidx/compose/foundation/Indication;", "enabled", "role", "Landroidx/compose/ui/semantics/Role;", "onValueChange", "Lkotlin/Function1;", "", "toggleable-O2vRcR0", "toggleable-XHw0xAI", "triStateToggleable", "state", "Landroidx/compose/ui/state/ToggleableState;", "onClick", "Lkotlin/Function0;", "triStateToggleable-O2vRcR0", "triStateToggleable-XHw0xAI", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ToggleableKt {
    /* renamed from: toggleable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m931toggleableXHw0xAI$default(Modifier modifier, boolean z, boolean z2, Role role, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m930toggleableXHw0xAI(modifier, z, z2, role, function1);
    }

    /* renamed from: toggleable-XHw0xAI, reason: not valid java name */
    public static final Modifier m930toggleableXHw0xAI(Modifier $this$toggleable_u2dXHw0xAI, final boolean value, final boolean enabled, final Role role, final Function1<? super Boolean, Unit> function1) {
        return ComposedModifierKt.composed($this$toggleable_u2dXHw0xAI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$toggleable-XHw0xAI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("toggleable");
                $this$null.getProperties().set("value", Boolean.valueOf(value));
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("role", role);
                $this$null.getProperties().set("onValueChange", function1);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.selection.ToggleableKt$toggleable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv;
                MutableInteractionSource interactionSource;
                $composer.startReplaceGroup(290332169);
                ComposerKt.sourceInformation($composer, "C74@3197L7:Toggleable.kt#gro6r2");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(290332169, $changed, -1, "androidx.compose.foundation.selection.toggleable.<anonymous> (Toggleable.kt:74)");
                }
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Indication localIndication2 = (Indication) consume;
                if (localIndication2 instanceof IndicationNodeFactory) {
                    $composer.startReplaceGroup(-2130154122);
                    $composer.endReplaceGroup();
                    interactionSource = null;
                } else {
                    $composer.startReplaceGroup(-2130046149);
                    ComposerKt.sourceInformation($composer, "81@3561L39");
                    ComposerKt.sourceInformationMarkerStart($composer, 1178220304, "CC(remember):Toggleable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    interactionSource = (MutableInteractionSource) value$iv;
                }
                Modifier m928toggleableO2vRcR0 = ToggleableKt.m928toggleableO2vRcR0(Modifier.INSTANCE, value, interactionSource, localIndication2, enabled, role, function1);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return m928toggleableO2vRcR0;
            }
        });
    }

    /* renamed from: toggleable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m929toggleableO2vRcR0$default(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z2, Role role, Function1 function1, int i, Object obj) {
        boolean z3;
        Role role2;
        if ((i & 8) == 0) {
            z3 = z2;
        } else {
            z3 = true;
        }
        if ((i & 16) == 0) {
            role2 = role;
        } else {
            role2 = null;
        }
        return m928toggleableO2vRcR0(modifier, z, mutableInteractionSource, indication, z3, role2, function1);
    }

    /* renamed from: toggleable-O2vRcR0, reason: not valid java name */
    public static final Modifier m928toggleableO2vRcR0(Modifier $this$toggleable_u2dO2vRcR0, final boolean value, MutableInteractionSource interactionSource, final Indication indication, final boolean enabled, final Role role, final Function1<? super Boolean, Unit> function1) {
        ToggleableElement toggleableElement;
        if (indication instanceof IndicationNodeFactory) {
            IndicationNodeFactory indicationNodeFactory = (IndicationNodeFactory) indication;
            toggleableElement = new ToggleableElement(value, interactionSource, indicationNodeFactory, enabled, role, function1, null);
        } else {
            toggleableElement = indication == null ? new ToggleableElement(value, interactionSource, null, enabled, role, function1, null) : interactionSource != null ? IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(new ToggleableElement(value, interactionSource, null, enabled, role, function1, null)) : ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.selection.ToggleableKt$toggleable-O2vRcR0$$inlined$clickableWithIndicationIfNeeded$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                    return invoke(modifier, composer, num.intValue());
                }

                public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                    Object value$iv;
                    $composer.startReplaceGroup(-1525724089);
                    ComposerKt.sourceInformation($composer, "C375@17877L39:Clickable.kt#71ulvw");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, $changed, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:375)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, -442650590, "CC(remember):Clickable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    MutableInteractionSource newInteractionSource = (MutableInteractionSource) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier then = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, Indication.this).then(new ToggleableElement(value, newInteractionSource, null, enabled, role, function1, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return then;
                }
            }, 1, null);
        }
        return $this$toggleable_u2dO2vRcR0.then(toggleableElement);
    }

    /* renamed from: triStateToggleable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m935triStateToggleableXHw0xAI$default(Modifier modifier, ToggleableState toggleableState, boolean z, Role role, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m934triStateToggleableXHw0xAI(modifier, toggleableState, z, role, function0);
    }

    /* renamed from: triStateToggleable-XHw0xAI, reason: not valid java name */
    public static final Modifier m934triStateToggleableXHw0xAI(Modifier $this$triStateToggleable_u2dXHw0xAI, final ToggleableState state, final boolean enabled, final Role role, final Function0<Unit> function0) {
        return ComposedModifierKt.composed($this$triStateToggleable_u2dXHw0xAI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$triStateToggleable-XHw0xAI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("triStateToggleable");
                $this$null.getProperties().set("state", ToggleableState.this);
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("role", role);
                $this$null.getProperties().set("onClick", function0);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.selection.ToggleableKt$triStateToggleable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv;
                MutableInteractionSource interactionSource;
                $composer.startReplaceGroup(-1808118329);
                ComposerKt.sourceInformation($composer, "C306@12494L7:Toggleable.kt#gro6r2");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1808118329, $changed, -1, "androidx.compose.foundation.selection.triStateToggleable.<anonymous> (Toggleable.kt:306)");
                }
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Indication localIndication2 = (Indication) consume;
                if (localIndication2 instanceof IndicationNodeFactory) {
                    $composer.startReplaceGroup(-1060535216);
                    $composer.endReplaceGroup();
                    interactionSource = null;
                } else {
                    $composer.startReplaceGroup(-1060427243);
                    ComposerKt.sourceInformation($composer, "313@12858L39");
                    ComposerKt.sourceInformationMarkerStart($composer, -588391178, "CC(remember):Toggleable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                    interactionSource = (MutableInteractionSource) value$iv;
                }
                Modifier m932triStateToggleableO2vRcR0 = ToggleableKt.m932triStateToggleableO2vRcR0(Modifier.INSTANCE, ToggleableState.this, interactionSource, localIndication2, enabled, role, function0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return m932triStateToggleableO2vRcR0;
            }
        });
    }

    /* renamed from: triStateToggleable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m933triStateToggleableO2vRcR0$default(Modifier modifier, ToggleableState toggleableState, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, Role role, Function0 function0, int i, Object obj) {
        boolean z2;
        Role role2;
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        if ((i & 16) == 0) {
            role2 = role;
        } else {
            role2 = null;
        }
        return m932triStateToggleableO2vRcR0(modifier, toggleableState, mutableInteractionSource, indication, z2, role2, function0);
    }

    /* renamed from: triStateToggleable-O2vRcR0, reason: not valid java name */
    public static final Modifier m932triStateToggleableO2vRcR0(Modifier $this$triStateToggleable_u2dO2vRcR0, final ToggleableState state, MutableInteractionSource interactionSource, final Indication indication, final boolean enabled, final Role role, final Function0<Unit> function0) {
        TriStateToggleableElement triStateToggleableElement;
        if (indication instanceof IndicationNodeFactory) {
            IndicationNodeFactory indicationNodeFactory = (IndicationNodeFactory) indication;
            triStateToggleableElement = new TriStateToggleableElement(state, interactionSource, indicationNodeFactory, enabled, role, function0, null);
        } else {
            triStateToggleableElement = indication == null ? new TriStateToggleableElement(state, interactionSource, null, enabled, role, function0, null) : interactionSource != null ? IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(new TriStateToggleableElement(state, interactionSource, null, enabled, role, function0, null)) : ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.selection.ToggleableKt$triStateToggleable-O2vRcR0$$inlined$clickableWithIndicationIfNeeded$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                    return invoke(modifier, composer, num.intValue());
                }

                public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                    Object value$iv;
                    $composer.startReplaceGroup(-1525724089);
                    ComposerKt.sourceInformation($composer, "C375@17877L39:Clickable.kt#71ulvw");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, $changed, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:375)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, -442650590, "CC(remember):Clickable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    MutableInteractionSource newInteractionSource = (MutableInteractionSource) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier then = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, Indication.this).then(new TriStateToggleableElement(state, newInteractionSource, null, enabled, role, function0, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return then;
                }
            }, 1, null);
        }
        return $this$triStateToggleable_u2dO2vRcR0.then(triStateToggleableElement);
    }
}
