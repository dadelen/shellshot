package androidx.compose.foundation;

import androidx.compose.foundation.gestures.ScrollableContainerNode;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Clickable.kt */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001ax\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007ø\u0001\u0000¢\u0006\u0002\b\u0012\u001aV\u0010\u0013\u001a\u00020\u0014*\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003ø\u0001\u0000¢\u0006\u0002\b\u0017\u001aB\u0010\u0013\u001a\u00020\u0014*\u00020\u00142\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003ø\u0001\u0000¢\u0006\u0002\b\u0018\u001aA\u0010\u0019\u001a\u00020\u0014*\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u001e\b\u0004\u0010\u001a\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u00140\u001bH\u0080\b\u001a\u0088\u0001\u0010\u001c\u001a\u00020\u0014*\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007ø\u0001\u0000¢\u0006\u0002\b\u001d\u001at\u0010\u001c\u001a\u00020\u0014*\u00020\u00142\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007ø\u0001\u0000¢\u0006\u0002\b\u001e\u001a\u009e\u0001\u0010\u001f\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000ø\u0001\u0000¢\u0006\u0002\b)\u001a\f\u0010*\u001a\u00020\u000e*\u00020+H\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"CombinedClickableNode", "Landroidx/compose/foundation/CombinedClickableNode;", "onClick", "Lkotlin/Function0;", "", "onLongClickLabel", "", "onLongClick", "onDoubleClick", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "enabled", "", "onClickLabel", "role", "Landroidx/compose/ui/semantics/Role;", "CombinedClickableNode-nSzSaCc", "clickable", "Landroidx/compose/ui/Modifier;", "indication", "Landroidx/compose/foundation/Indication;", "clickable-O2vRcR0", "clickable-XHw0xAI", "clickableWithIndicationIfNeeded", "createClickable", "Lkotlin/Function2;", "combinedClickable", "combinedClickable-XVZzFYc", "combinedClickable-cJG_KMw", "genericClickableWithoutGesture", "indicationScope", "Lkotlinx/coroutines/CoroutineScope;", "currentKeyPressInteractions", "", "Landroidx/compose/ui/input/key/Key;", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "keyClickOffset", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/geometry/Offset;", "genericClickableWithoutGesture-Kqv-Bsg", "hasScrollableContainer", "Landroidx/compose/ui/node/TraversableNode;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ClickableKt {
    /* renamed from: clickable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m260clickableXHw0xAI$default(Modifier modifier, boolean z, String str, Role role, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m259clickableXHw0xAI(modifier, z, str, role, function0);
    }

    /* renamed from: clickable-XHw0xAI, reason: not valid java name */
    public static final Modifier m259clickableXHw0xAI(Modifier $this$clickable_u2dXHw0xAI, final boolean enabled, final String onClickLabel, final Role role, final Function0<Unit> function0) {
        return ComposedModifierKt.composed($this$clickable_u2dXHw0xAI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$clickable-XHw0xAI$$inlined$debugInspectorInfo$1
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
                $this$null.setName("clickable");
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("onClickLabel", onClickLabel);
                $this$null.getProperties().set("role", role);
                $this$null.getProperties().set("onClick", function0);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$clickable$2
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
                $composer.startReplaceGroup(-756081143);
                ComposerKt.sourceInformation($composer, "C112@5124L7:Clickable.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-756081143, $changed, -1, "androidx.compose.foundation.clickable.<anonymous> (Clickable.kt:112)");
                }
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Indication localIndication2 = (Indication) consume;
                if (localIndication2 instanceof IndicationNodeFactory) {
                    $composer.startReplaceGroup(617140216);
                    $composer.endReplaceGroup();
                    interactionSource = null;
                } else {
                    $composer.startReplaceGroup(617248189);
                    ComposerKt.sourceInformation($composer, "119@5488L39");
                    ComposerKt.sourceInformationMarkerStart($composer, 1266842702, "CC(remember):Clickable.kt#9igjgp");
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
                Modifier m257clickableO2vRcR0 = ClickableKt.m257clickableO2vRcR0(Modifier.INSTANCE, interactionSource, localIndication2, enabled, onClickLabel, role, function0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return m257clickableO2vRcR0;
            }
        });
    }

    /* renamed from: clickable-O2vRcR0, reason: not valid java name */
    public static final Modifier m257clickableO2vRcR0(Modifier $this$clickable_u2dO2vRcR0, MutableInteractionSource interactionSource, final Indication indication, final boolean enabled, final String onClickLabel, final Role role, final Function0<Unit> function0) {
        ClickableElement composed$default;
        if (indication instanceof IndicationNodeFactory) {
            IndicationNodeFactory indicationNodeFactory = (IndicationNodeFactory) indication;
            composed$default = new ClickableElement(interactionSource, indicationNodeFactory, enabled, onClickLabel, role, function0, null);
        } else if (indication == null) {
            IndicationNodeFactory indicationNodeFactory2 = null;
            composed$default = new ClickableElement(interactionSource, indicationNodeFactory2, enabled, onClickLabel, role, function0, null);
        } else if (interactionSource != null) {
            IndicationNodeFactory indicationNodeFactory3 = null;
            composed$default = IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(new ClickableElement(interactionSource, indicationNodeFactory3, enabled, onClickLabel, role, function0, null));
        } else {
            composed$default = ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$clickable-O2vRcR0$$inlined$clickableWithIndicationIfNeeded$1
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
                    Modifier then = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, Indication.this).then(new ClickableElement(newInteractionSource, null, enabled, onClickLabel, role, function0, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return then;
                }
            }, 1, null);
        }
        return $this$clickable_u2dO2vRcR0.then(composed$default);
    }

    /* renamed from: combinedClickable-cJG_KMw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m264combinedClickablecJG_KMw$default(Modifier modifier, boolean z, String str, Role role, String str2, Function0 function0, Function0 function02, Function0 function03, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        return m263combinedClickablecJG_KMw(modifier, z, str, role, str2, function0, (i & 32) != 0 ? null : function02, function03);
    }

    /* renamed from: combinedClickable-cJG_KMw, reason: not valid java name */
    public static final Modifier m263combinedClickablecJG_KMw(Modifier $this$combinedClickable_u2dcJG_KMw, final boolean enabled, final String onClickLabel, final Role role, final String onLongClickLabel, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03) {
        return ComposedModifierKt.composed($this$combinedClickable_u2dcJG_KMw, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-cJG_KMw$$inlined$debugInspectorInfo$1
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
                $this$null.setName("combinedClickable");
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("onClickLabel", onClickLabel);
                $this$null.getProperties().set("role", role);
                $this$null.getProperties().set("onClick", function03);
                $this$null.getProperties().set("onDoubleClick", function02);
                $this$null.getProperties().set("onLongClick", function0);
                $this$null.getProperties().set("onLongClickLabel", onLongClickLabel);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$2
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
                $composer.startReplaceGroup(1969174843);
                ComposerKt.sourceInformation($composer, "C252@11777L7:Clickable.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1969174843, $changed, -1, "androidx.compose.foundation.combinedClickable.<anonymous> (Clickable.kt:252)");
                }
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Indication localIndication2 = (Indication) consume;
                if (localIndication2 instanceof IndicationNodeFactory) {
                    $composer.startReplaceGroup(-1726989699);
                    $composer.endReplaceGroup();
                    interactionSource = null;
                } else {
                    $composer.startReplaceGroup(-1726881726);
                    ComposerKt.sourceInformation($composer, "259@12141L39");
                    ComposerKt.sourceInformationMarkerStart($composer, -1995363031, "CC(remember):Clickable.kt#9igjgp");
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
                Modifier m261combinedClickableXVZzFYc = ClickableKt.m261combinedClickableXVZzFYc(Modifier.INSTANCE, interactionSource, localIndication2, enabled, onClickLabel, role, onLongClickLabel, function0, function02, function03);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return m261combinedClickableXVZzFYc;
            }
        });
    }

    /* renamed from: combinedClickable-XVZzFYc, reason: not valid java name */
    public static final Modifier m261combinedClickableXVZzFYc(Modifier $this$combinedClickable_u2dXVZzFYc, MutableInteractionSource interactionSource, final Indication indication, final boolean enabled, final String onClickLabel, final Role role, final String onLongClickLabel, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03) {
        CombinedClickableElement composed$default;
        if (indication instanceof IndicationNodeFactory) {
            IndicationNodeFactory indicationNodeFactory = (IndicationNodeFactory) indication;
            composed$default = new CombinedClickableElement(interactionSource, indicationNodeFactory, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, null);
        } else if (indication == null) {
            IndicationNodeFactory indicationNodeFactory2 = null;
            composed$default = new CombinedClickableElement(interactionSource, indicationNodeFactory2, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, null);
        } else if (interactionSource != null) {
            IndicationNodeFactory indicationNodeFactory3 = null;
            composed$default = IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(new CombinedClickableElement(interactionSource, indicationNodeFactory3, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, null));
        } else {
            composed$default = ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-XVZzFYc$$inlined$clickableWithIndicationIfNeeded$1
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
                    Modifier then = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, Indication.this).then(new CombinedClickableElement(newInteractionSource, null, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return then;
                }
            }, 1, null);
        }
        return $this$combinedClickable_u2dXVZzFYc.then(composed$default);
    }

    public static final Modifier clickableWithIndicationIfNeeded(Modifier $this$clickableWithIndicationIfNeeded, MutableInteractionSource interactionSource, final Indication indication, final Function2<? super MutableInteractionSource, ? super IndicationNodeFactory, ? extends Modifier> function2) {
        Modifier then;
        if (indication instanceof IndicationNodeFactory) {
            then = function2.invoke(interactionSource, indication);
        } else if (indication == null) {
            then = function2.invoke(interactionSource, null);
        } else {
            then = interactionSource != null ? IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(function2.invoke(interactionSource, null)) : ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$clickableWithIndicationIfNeeded$1
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
                    Modifier then2 = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, Indication.this).then(function2.invoke(newInteractionSource, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return then2;
                }
            }, 1, null);
        }
        return $this$clickableWithIndicationIfNeeded.then(then);
    }

    private static final Modifier genericClickableWithoutGesture_Kqv_Bsg$detectPressAndClickFromKey(Modifier $this$genericClickableWithoutGesture_Kqv_Bsg_u24detectPressAndClickFromKey, final boolean $enabled, final Map<Key, PressInteraction.Press> map, final State<Offset> state, final CoroutineScope $indicationScope, final Function0<Unit> function0, final MutableInteractionSource $interactionSource) {
        return KeyInputModifierKt.onKeyEvent($this$genericClickableWithoutGesture_Kqv_Bsg_u24detectPressAndClickFromKey, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m267invokeZmokQxo(keyEvent.m5209unboximpl());
            }

            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m267invokeZmokQxo(android.view.KeyEvent keyEvent) {
                boolean z = true;
                if ($enabled && Clickable_androidKt.m274isPressZmokQxo(keyEvent)) {
                    if (!map.containsKey(Key.m4909boximpl(KeyEvent_androidKt.m5220getKeyZmokQxo(keyEvent)))) {
                        PressInteraction.Press press = new PressInteraction.Press(state.getValue().getPackedValue(), null);
                        map.put(Key.m4909boximpl(KeyEvent_androidKt.m5220getKeyZmokQxo(keyEvent)), press);
                        BuildersKt__Builders_commonKt.launch$default($indicationScope, null, null, new AnonymousClass1($interactionSource, press, null), 3, null);
                    } else {
                        z = false;
                    }
                } else if ($enabled && Clickable_androidKt.m272isClickZmokQxo(keyEvent)) {
                    PressInteraction.Press it = map.remove(Key.m4909boximpl(KeyEvent_androidKt.m5220getKeyZmokQxo(keyEvent)));
                    if (it != null) {
                        BuildersKt__Builders_commonKt.launch$default($indicationScope, null, null, new ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$2$1($interactionSource, it, null), 3, null);
                    }
                    function0.invoke();
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }

            /* compiled from: Clickable.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1", f = "Clickable.kt", i = {}, l = {434}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ PressInteraction.Press $press;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(MutableInteractionSource mutableInteractionSource, PressInteraction.Press press, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$interactionSource = mutableInteractionSource;
                    this.$press = press;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$interactionSource, this.$press, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (this.$interactionSource.emit(this.$press, this) != coroutine_suspended) {
                                break;
                            } else {
                                return coroutine_suspended;
                            }
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }
        });
    }

    /* renamed from: genericClickableWithoutGesture-Kqv-Bsg, reason: not valid java name */
    public static final Modifier m265genericClickableWithoutGestureKqvBsg(Modifier $this$genericClickableWithoutGesture_u2dKqv_u2dBsg, MutableInteractionSource interactionSource, Indication indication, CoroutineScope indicationScope, Map<Key, PressInteraction.Press> map, State<Offset> state, boolean enabled, String onClickLabel, Role role, String onLongClickLabel, Function0<Unit> function0, Function0<Unit> function02) {
        return $this$genericClickableWithoutGesture_u2dKqv_u2dBsg.then(FocusableKt.focusableInNonTouchMode(HoverableKt.hoverable(IndicationKt.indication(genericClickableWithoutGesture_Kqv_Bsg$detectPressAndClickFromKey(new ClickableSemanticsElement(enabled, role, onLongClickLabel, function0, onClickLabel, function02, null), enabled, map, state, indicationScope, function02, interactionSource), interactionSource, indication), interactionSource, enabled), enabled, interactionSource));
    }

    /* renamed from: CombinedClickableNode-nSzSaCc, reason: not valid java name */
    public static final CombinedClickableNode m256CombinedClickableNodenSzSaCc(Function0<Unit> function0, String onLongClickLabel, Function0<Unit> function02, Function0<Unit> function03, MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean enabled, String onClickLabel, Role role) {
        return new CombinedClickableNodeImpl(function0, onLongClickLabel, function02, function03, interactionSource, indicationNodeFactory, enabled, onClickLabel, role, null);
    }

    public static final boolean hasScrollableContainer(TraversableNode $this$hasScrollableContainer) {
        final Ref.BooleanRef hasScrollable = new Ref.BooleanRef();
        TraversableNodeKt.traverseAncestors($this$hasScrollableContainer, ScrollableContainerNode.INSTANCE, new Function1<TraversableNode, Boolean>() { // from class: androidx.compose.foundation.ClickableKt$hasScrollableContainer$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(TraversableNode node) {
                boolean z;
                Ref.BooleanRef booleanRef = Ref.BooleanRef.this;
                if (!Ref.BooleanRef.this.element) {
                    Intrinsics.checkNotNull(node, "null cannot be cast to non-null type androidx.compose.foundation.gestures.ScrollableContainerNode");
                    if (!((ScrollableContainerNode) node).getEnabled()) {
                        z = false;
                        booleanRef.element = z;
                        return Boolean.valueOf(!Ref.BooleanRef.this.element);
                    }
                }
                z = true;
                booleanRef.element = z;
                return Boolean.valueOf(!Ref.BooleanRef.this.element);
            }
        });
        return hasScrollable.element;
    }
}
