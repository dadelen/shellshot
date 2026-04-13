package androidx.activity.compose;

import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: PredictiveBackHandler.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aZ\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032A\u0010\u0004\u001a=\b\u0001\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u000f\u0012\r\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005H\u0007¢\u0006\u0002\u0010\u000e¨\u0006\u000f²\u0006E\u0010\u0010\u001a=\b\u0001\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u000f\u0012\r\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005X\u008a\u0084\u0002"}, d2 = {"PredictiveBackHandler", "", "enabled", "", "onBack", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/activity/BackEventCompat;", "Lkotlin/jvm/JvmSuppressWildcards;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, NotificationCompat.CATEGORY_PROGRESS, "Lkotlin/coroutines/Continuation;", "", "(ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "activity-compose_release", "currentOnBack"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PredictiveBackHandlerKt {
    public static final void PredictiveBackHandler(boolean enabled, final Function2<Flow<BackEventCompat>, ? super Continuation<Unit>, ? extends Object> function2, Composer $composer, final int $changed, final int i) {
        boolean z;
        final boolean enabled2;
        State currentOnBack$delegate;
        Object value$iv$iv$iv;
        Object value$iv;
        PredictiveBackHandlerKt$PredictiveBackHandler$2$1 value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(-642000585);
        ComposerKt.sourceInformation($composer2, "C(PredictiveBackHandler)77@3028L28,78@3079L24,80@3128L91,85@3317L142,90@3489L38,90@3465L62,*93@3609L7,98@3818L7,100@3880L137,100@3831L186:PredictiveBackHandler.kt#q1dkbc");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            z = enabled;
        } else if (($changed & 6) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = enabled;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(function2) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                enabled2 = true;
            } else {
                enabled2 = z;
            }
            State currentOnBack$delegate2 = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty >> 3) & 14);
            $composer2.startReplaceableGroup(-723524056);
            ComposerKt.sourceInformation($composer2, "C(rememberCoroutineScope)475@19849L144:Effects.kt#9igjgp");
            $composer2.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation($composer2, "C(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer2.rememberedValue();
            int $dirty2 = $dirty;
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                currentOnBack$delegate = currentOnBack$delegate2;
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv$iv);
            } else {
                currentOnBack$delegate = currentOnBack$delegate2;
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer2.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            CoroutineScope onBackScope = wrapper$iv.getCoroutineScope();
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-1071578902);
            ComposerKt.sourceInformation($composer2, "CC(remember):PredictiveBackHandler.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new PredictiveBackHandlerCallback(enabled2, onBackScope, PredictiveBackHandler$lambda$0(currentOnBack$delegate));
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final PredictiveBackHandlerCallback backCallBack = (PredictiveBackHandlerCallback) value$iv;
            $composer2.endReplaceableGroup();
            Object PredictiveBackHandler$lambda$0 = PredictiveBackHandler$lambda$0(currentOnBack$delegate);
            $composer2.startReplaceableGroup(-1071578713);
            ComposerKt.sourceInformation($composer2, "CC(remember):PredictiveBackHandler.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(PredictiveBackHandler$lambda$0) | $composer2.changed(onBackScope);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                backCallBack.setCurrentOnBack(PredictiveBackHandler$lambda$0(currentOnBack$delegate));
                backCallBack.setOnBackScope(onBackScope);
                Object value$iv4 = Unit.INSTANCE;
                $composer2.updateRememberedValue(value$iv4);
            }
            $composer2.endReplaceableGroup();
            Boolean valueOf = Boolean.valueOf(enabled2);
            $composer2.startReplaceableGroup(-1071578541);
            ComposerKt.sourceInformation($composer2, "CC(remember):PredictiveBackHandler.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(backCallBack) | $composer2.changed(enabled2);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new PredictiveBackHandlerKt$PredictiveBackHandler$2$1(backCallBack, enabled2, null);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv3;
            }
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(valueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer2, $dirty2 & 14);
            OnBackPressedDispatcherOwner current = LocalOnBackPressedDispatcherOwner.INSTANCE.getCurrent($composer2, 6);
            if (current == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner".toString());
            }
            final OnBackPressedDispatcher backDispatcher = current.getOnBackPressedDispatcher();
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart($composer2, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final LifecycleOwner lifecycleOwner = (LifecycleOwner) consume;
            $composer2.startReplaceableGroup(-1071578150);
            ComposerKt.sourceInformation($composer2, "CC(remember):PredictiveBackHandler.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changed(backDispatcher) | $composer2.changed(lifecycleOwner) | $composer2.changed(backCallBack);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.activity.compose.PredictiveBackHandlerKt$PredictiveBackHandler$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        OnBackPressedDispatcher.this.addCallback(lifecycleOwner, backCallBack);
                        final PredictiveBackHandlerCallback predictiveBackHandlerCallback = backCallBack;
                        return new DisposableEffectResult() { // from class: androidx.activity.compose.PredictiveBackHandlerKt$PredictiveBackHandler$3$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                PredictiveBackHandlerCallback.this.remove();
                            }
                        };
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv4;
            }
            $composer2.endReplaceableGroup();
            EffectsKt.DisposableEffect(lifecycleOwner, backDispatcher, (Function1) value$iv3, $composer2, 0);
        } else {
            $composer2.skipToGroupEnd();
            enabled2 = z;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.activity.compose.PredictiveBackHandlerKt$PredictiveBackHandler$4
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

                public final void invoke(Composer composer, int i3) {
                    PredictiveBackHandlerKt.PredictiveBackHandler(enabled2, function2, composer, $changed | 1, i);
                }
            });
        }
    }

    private static final Function2<Flow<BackEventCompat>, Continuation<Unit>, Object> PredictiveBackHandler$lambda$0(State<? extends Function2<Flow<BackEventCompat>, ? super Continuation<Unit>, ? extends Object>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }
}
