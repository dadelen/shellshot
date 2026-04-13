package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: LazyLayout.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aP\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u001d\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001aJ\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u001d\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0012\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"MaxItemsToRetainForReuse", "", "LazyLayout", "", "itemProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "modifier", "Landroidx/compose/ui/Modifier;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "measurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutKt {
    private static final int MaxItemsToRetainForReuse = 7;

    @Deprecated(message = "Use an overload accepting a lambda prodicing an item provider instead", replaceWith = @ReplaceWith(expression = "LazyLayout({ itemProvider }, modifier, prefetchState, measurePolicy)", imports = {}))
    public static final void LazyLayout(final LazyLayoutItemProvider itemProvider, Modifier modifier, LazyLayoutPrefetchState prefetchState, final Function2<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> function2, Composer $composer, final int $changed, final int i) {
        LazyLayoutPrefetchState lazyLayoutPrefetchState;
        Function2<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> function22;
        Object value$iv;
        final Modifier modifier2;
        final LazyLayoutPrefetchState prefetchState2;
        Composer $composer2 = $composer.startRestartGroup(852831187);
        ComposerKt.sourceInformation($composer2, "C(LazyLayout)P(!1,2,3)54@2185L16,54@2174L68:LazyLayout.kt#wow0x6");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(itemProvider) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            lazyLayoutPrefetchState = prefetchState;
        } else if (($changed & 384) == 0) {
            lazyLayoutPrefetchState = prefetchState;
            $dirty |= $composer2.changed(lazyLayoutPrefetchState) ? 256 : 128;
        } else {
            lazyLayoutPrefetchState = prefetchState;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            function22 = function2;
        } else if (($changed & 3072) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 2048 : 1024;
        } else {
            function22 = function2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            prefetchState2 = lazyLayoutPrefetchState;
        } else {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            LazyLayoutPrefetchState prefetchState3 = i3 != 0 ? null : lazyLayoutPrefetchState;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(852831187, $dirty2, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:53)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1956949074, "CC(remember):LazyLayout.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 14) == 4;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<LazyLayoutItemProvider>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$1$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final LazyLayoutItemProvider invoke() {
                        return LazyLayoutItemProvider.this;
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyLayout((Function0<? extends LazyLayoutItemProvider>) value$iv, modifier3, prefetchState3, function22, $composer2, ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            prefetchState2 = prefetchState3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$2
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
                    LazyLayoutKt.LazyLayout(LazyLayoutItemProvider.this, modifier2, prefetchState2, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void LazyLayout(final Function0<? extends LazyLayoutItemProvider> function0, final Modifier modifier, final LazyLayoutPrefetchState prefetchState, final Function2<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> function2, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(2002163445);
        ComposerKt.sourceInformation($composer2, "C(LazyLayout)P(!1,2,3)79@3266L34,81@3338L1509,81@3306L1541:LazyLayout.kt#wow0x6");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(prefetchState) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) != 1170 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (i3 != 0) {
                prefetchState = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2002163445, $dirty2, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:78)");
            }
            final State currentItemProvider = SnapshotStateKt.rememberUpdatedState(function0, $composer2, $dirty2 & 14);
            LazySaveableStateHolderKt.LazySaveableStateHolderProvider(ComposableLambdaKt.rememberComposableLambda(-1488997347, true, new Function3<SaveableStateHolder, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(SaveableStateHolder saveableStateHolder, Composer composer, Integer num) {
                    invoke(saveableStateHolder, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(SaveableStateHolder saveableStateHolder, Composer $composer3, int $changed2) {
                    Object value$iv;
                    Object value$iv2;
                    Object value$iv3;
                    Object value$iv4;
                    ComposerKt.sourceInformation($composer3, "C82@3396L114,85@3547L101,110@4439L392,107@4312L529:LazyLayout.kt#wow0x6");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1488997347, $changed2, -1, "androidx.compose.foundation.lazy.layout.LazyLayout.<anonymous> (LazyLayout.kt:82)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer3, 6611731, "CC(remember):LazyLayout.kt#9igjgp");
                    final State<Function0<LazyLayoutItemProvider>> state = currentItemProvider;
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = new LazyLayoutItemContentFactory(saveableStateHolder, new Function0<LazyLayoutItemProvider>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$3$itemContentFactory$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final LazyLayoutItemProvider invoke() {
                                return state.getValue().invoke();
                            }
                        });
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    final LazyLayoutItemContentFactory itemContentFactory = (LazyLayoutItemContentFactory) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, 6616550, "CC(remember):LazyLayout.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new SubcomposeLayoutState(new LazyLayoutItemReusePolicy(itemContentFactory));
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv2;
                    }
                    final SubcomposeLayoutState subcomposeLayoutState = (SubcomposeLayoutState) value$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (LazyLayoutPrefetchState.this != null) {
                        $composer3.startReplaceGroup(205264983);
                        ComposerKt.sourceInformation($composer3, "95@3958L334,90@3795L497");
                        final PrefetchScheduler executor = LazyLayoutPrefetchState.this.getPrefetchScheduler();
                        if (executor == null) {
                            $composer3.startReplaceGroup(6622915);
                            ComposerKt.sourceInformation($composer3, "89@3748L34");
                            executor = PrefetchScheduler_androidKt.rememberDefaultPrefetchScheduler($composer3, 0);
                        } else {
                            $composer3.startReplaceGroup(6621830);
                        }
                        $composer3.endReplaceGroup();
                        Object[] objArr = {LazyLayoutPrefetchState.this, itemContentFactory, subcomposeLayoutState, executor};
                        ComposerKt.sourceInformationMarkerStart($composer3, 6629935, "CC(remember):LazyLayout.kt#9igjgp");
                        boolean invalid$iv = $composer3.changed(LazyLayoutPrefetchState.this) | $composer3.changedInstance(itemContentFactory) | $composer3.changedInstance(subcomposeLayoutState) | $composer3.changedInstance(executor);
                        final LazyLayoutPrefetchState lazyLayoutPrefetchState = LazyLayoutPrefetchState.this;
                        Object it$iv3 = $composer3.rememberedValue();
                        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                                    LazyLayoutPrefetchState.this.setPrefetchHandleProvider$foundation_release(new PrefetchHandleProvider(itemContentFactory, subcomposeLayoutState, executor));
                                    final LazyLayoutPrefetchState lazyLayoutPrefetchState2 = LazyLayoutPrefetchState.this;
                                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$3$1$1$invoke$$inlined$onDispose$1
                                        @Override // androidx.compose.runtime.DisposableEffectResult
                                        public void dispose() {
                                            LazyLayoutPrefetchState.this.setPrefetchHandleProvider$foundation_release(null);
                                        }
                                    };
                                }
                            };
                            $composer3.updateRememberedValue(value$iv4);
                        } else {
                            value$iv4 = it$iv3;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        EffectsKt.DisposableEffect(objArr, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv4, $composer3, 0);
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(205858881);
                        $composer3.endReplaceGroup();
                    }
                    Modifier traversablePrefetchState = LazyLayoutPrefetchStateKt.traversablePrefetchState(modifier, LazyLayoutPrefetchState.this);
                    ComposerKt.sourceInformationMarkerStart($composer3, 6645385, "CC(remember):LazyLayout.kt#9igjgp");
                    boolean invalid$iv2 = $composer3.changed(itemContentFactory) | $composer3.changed(function2);
                    final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function22 = function2;
                    Object it$iv4 = $composer3.rememberedValue();
                    if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$3$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m842invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m842invoke0kLqBqw(SubcomposeMeasureScope $this$null, long constraints) {
                                LazyLayoutMeasureScopeImpl $this$invoke_0kLqBqw_u24lambda_u240 = new LazyLayoutMeasureScopeImpl(LazyLayoutItemContentFactory.this, $this$null);
                                return function22.invoke($this$invoke_0kLqBqw_u24lambda_u240, Constraints.m6567boximpl(constraints));
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    SubcomposeLayoutKt.SubcomposeLayout(subcomposeLayoutState, traversablePrefetchState, (Function2) value$iv3, $composer3, SubcomposeLayoutState.$stable, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        final Modifier modifier2 = modifier;
        final LazyLayoutPrefetchState prefetchState2 = prefetchState;
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$4
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
                    LazyLayoutKt.LazyLayout(function0, modifier2, prefetchState2, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
