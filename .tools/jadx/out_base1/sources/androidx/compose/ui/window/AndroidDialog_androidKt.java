package androidx.compose.ui.window;

import android.view.View;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AndroidDialog.android.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\b\u001a*\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007H\u0003¢\u0006\u0002\u0010\f¨\u0006\r²\u0006\u0015\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007X\u008a\u0084\u0002"}, d2 = {"Dialog", "", "onDismissRequest", "Lkotlin/Function0;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DialogLayout", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ui_release", "currentContent"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidDialog_androidKt {
    public static final void Dialog(final Function0<Unit> function0, DialogProperties properties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        DialogProperties properties2;
        final LayoutDirection layoutDirection;
        int $dirty;
        final DialogProperties properties3;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(-2032877254);
        ComposerKt.sourceInformation($composer2, "C(Dialog)P(1,2)167@7096L7,168@7135L7,169@7190L7,170@7220L28,171@7275L29,172@7324L38,173@7380L616,194@8027L129,194@8002L154,203@8173L182,203@8162L193:AndroidDialog.android.kt#2oxthz");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            properties2 = properties;
        } else if (($changed & 48) == 0) {
            properties2 = properties;
            $dirty2 |= $composer2.changed(properties2) ? 32 : 16;
        } else {
            properties2 = properties;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                properties2 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2032877254, $dirty2, -1, "androidx.compose.ui.window.Dialog (AndroidDialog.android.kt:166)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            View view = (View) consume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) consume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume3 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection2 = (LayoutDirection) consume3;
            CompositionContext composition = ComposablesKt.rememberCompositionContext($composer2, 0);
            final State currentContent$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty2 >> 6) & 14);
            UUID dialogId = (UUID) RememberSaveableKt.m3749rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<UUID>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$dialogId$1
                @Override // kotlin.jvm.functions.Function0
                public final UUID invoke() {
                    return UUID.randomUUID();
                }
            }, $composer2, 3072, 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -464840651, "CC(remember):AndroidDialog.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(view) | $composer2.changed(density);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                layoutDirection = layoutDirection2;
                $dirty = $dirty2;
                properties3 = properties2;
                DialogWrapper $this$Dialog_u24lambda_u242_u24lambda_u241 = new DialogWrapper(function0, properties3, view, layoutDirection, density, dialogId);
                $this$Dialog_u24lambda_u242_u24lambda_u241.setContent(composition, ComposableLambdaKt.composableLambdaInstance(488261145, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$dialog$1$1$1
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

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C187@7910L56,185@7825L141:AndroidDialog.android.kt#2oxthz");
                        if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(488261145, $changed2, -1, "androidx.compose.ui.window.Dialog.<anonymous>.<anonymous>.<anonymous> (AndroidDialog.android.kt:185)");
                            }
                            Modifier semantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$dialog$1$1$1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                    SemanticsPropertiesKt.dialog($this$semantics);
                                }
                            }, 1, null);
                            final State<Function2<Composer, Integer, Unit>> state = currentContent$delegate;
                            AndroidDialog_androidKt.DialogLayout(semantics$default, ComposableLambdaKt.rememberComposableLambda(-533674951, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$dialog$1$1$1.2
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

                                public final void invoke(Composer $composer4, int $changed3) {
                                    Function2 Dialog$lambda$0;
                                    ComposerKt.sourceInformation($composer4, "C188@7932L16:AndroidDialog.android.kt#2oxthz");
                                    if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-533674951, $changed3, -1, "androidx.compose.ui.window.Dialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AndroidDialog.android.kt:188)");
                                        }
                                        Dialog$lambda$0 = AndroidDialog_androidKt.Dialog$lambda$0(state);
                                        Dialog$lambda$0.invoke($composer4, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer4.skipToGroupEnd();
                                }
                            }, $composer3, 54), $composer3, 48, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }));
                value$iv = $this$Dialog_u24lambda_u242_u24lambda_u241;
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
                layoutDirection = layoutDirection2;
                $dirty = $dirty2;
                properties3 = properties2;
            }
            final DialogWrapper dialog = (DialogWrapper) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -464820434, "CC(remember):AndroidDialog.android.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(dialog);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        DialogWrapper.this.show();
                        final DialogWrapper dialogWrapper = DialogWrapper.this;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                DialogWrapper.this.dismiss();
                                DialogWrapper.this.disposeComposition();
                            }
                        };
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(dialog, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv2, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -464815709, "CC(remember):AndroidDialog.android.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changedInstance(dialog) | (($dirty & 14) == 4) | (($dirty & 112) == 32) | $composer2.changed(layoutDirection);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$2$1
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
                        DialogWrapper.this.updateParameters(function0, properties3, layoutDirection);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) value$iv3, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            properties3 = properties2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$3
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
                    AndroidDialog_androidKt.Dialog(function0, properties3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> Dialog$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DialogLayout(androidx.compose.ui.Modifier r21, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r22, androidx.compose.runtime.Composer r23, final int r24, final int r25) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidDialog_androidKt.DialogLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }
}
