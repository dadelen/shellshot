package androidx.compose.material3.internal;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AccessibilityServiceStateProvider.android.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0003¢\u0006\u0002\u0010\u000b\u001a'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0001¢\u0006\u0002\u0010\u0011\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"SwitchAccessActivityName", "", "ObserveState", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "handleEvent", "Lkotlin/Function1;", "Landroidx/lifecycle/Lifecycle$Event;", "onDispose", "Lkotlin/Function0;", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "rememberAccessibilityServiceState", "Landroidx/compose/runtime/State;", "", "listenToTouchExplorationState", "listenToSwitchAccessState", "(ZZLandroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AccessibilityServiceStateProvider_androidKt {
    private static final String SwitchAccessActivityName = "SwitchAccess";

    public static final State<Boolean> rememberAccessibilityServiceState(boolean listenToTouchExplorationState, boolean listenToSwitchAccessState, Composer $composer, int $changed, int i) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        ComposerKt.sourceInformationMarkerStart($composer, -1771705152, "C(rememberAccessibilityServiceState)P(1)47@1997L7,52@2152L157,57@2374L7,58@2405L144,63@2571L45,56@2315L307:AccessibilityServiceStateProvider.android.kt#mqatfk");
        boolean listenToTouchExplorationState2 = (i & 1) != 0 ? true : listenToTouchExplorationState;
        boolean listenToSwitchAccessState2 = (i & 2) != 0 ? true : listenToSwitchAccessState;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1771705152, $changed, -1, "androidx.compose.material3.internal.rememberAccessibilityServiceState (AccessibilityServiceStateProvider.android.kt:46)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) consume;
        Object systemService = context.getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        final AccessibilityManager accessibilityManager = (AccessibilityManager) systemService;
        ComposerKt.sourceInformationMarkerStart($composer, 18334252, "CC(remember):AccessibilityServiceStateProvider.android.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(listenToTouchExplorationState2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(listenToSwitchAccessState2)) || ($changed & 48) == 32);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Listener(listenToTouchExplorationState2, listenToSwitchAccessState2);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        final Listener listener = (Listener) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localLifecycleOwner);
        ComposerKt.sourceInformationMarkerEnd($composer);
        LifecycleOwner lifecycleOwner = (LifecycleOwner) consume2;
        ComposerKt.sourceInformationMarkerStart($composer, 18342335, "CC(remember):AccessibilityServiceStateProvider.android.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(listener) | $composer.changedInstance(accessibilityManager);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = (Function1) new Function1<Lifecycle.Event, Unit>() { // from class: androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt$rememberAccessibilityServiceState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Lifecycle.Event event) {
                    invoke2(event);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_RESUME) {
                        Listener.this.register(accessibilityManager);
                    }
                }
            };
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        Function1 function1 = (Function1) value$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 18347548, "CC(remember):AccessibilityServiceStateProvider.android.kt#9igjgp");
        boolean invalid$iv3 = $composer.changed(listener) | $composer.changedInstance(accessibilityManager);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt$rememberAccessibilityServiceState$2$1
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
                    Listener.this.unregister(accessibilityManager);
                }
            };
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        ObserveState(lifecycleOwner, function1, (Function0) value$iv3, $composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ObserveState(final LifecycleOwner lifecycleOwner, Function1<? super Lifecycle.Event, Unit> function1, Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        AccessibilityServiceStateProvider_androidKt$ObserveState$3$1 value$iv;
        Composer $composer2 = $composer.startRestartGroup(-1868327245);
        ComposerKt.sourceInformation($composer2, "C(ObserveState)P(1)75@2842L259,75@2809L292:AccessibilityServiceStateProvider.android.kt#mqatfk");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(lifecycleOwner) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                Function1 handleEvent = new Function1<Lifecycle.Event, Unit>() { // from class: androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt$ObserveState$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Lifecycle.Event event) {
                        invoke2(event);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Lifecycle.Event it) {
                    }
                };
                function1 = handleEvent;
            }
            if (i3 != 0) {
                Function0 onDispose = new Function0<Unit>() { // from class: androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt$ObserveState$2
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                };
                function0 = onDispose;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1868327245, $dirty, -1, "androidx.compose.material3.internal.ObserveState (AccessibilityServiceStateProvider.android.kt:74)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1591666214, "CC(remember):AccessibilityServiceStateProvider.android.kt#9igjgp");
            boolean invalid$iv = (($dirty & 112) == 32) | $composer2.changedInstance(lifecycleOwner) | (($dirty & 896) == 256);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new AccessibilityServiceStateProvider_androidKt$ObserveState$3$1(lifecycleOwner, function1, function0);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(lifecycleOwner, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        final Function1 handleEvent2 = function1;
        final Function0 onDispose2 = function0;
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt$ObserveState$4
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
                    AccessibilityServiceStateProvider_androidKt.ObserveState(LifecycleOwner.this, handleEvent2, onDispose2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
