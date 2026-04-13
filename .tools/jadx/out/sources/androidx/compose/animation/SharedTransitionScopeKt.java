package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.LookaheadScopeKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SharedTransitionScope.kt */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0018\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001cH\u0003\u001a5\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020)2\u001c\u0010*\u001a\u0018\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020'0+¢\u0006\u0002\b-¢\u0006\u0002\b.H\u0007¢\u0006\u0002\u0010/\u001a1\u00100\u001a\u00020'2\"\u0010*\u001a\u001e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020'0\u0005¢\u0006\u0002\b-¢\u0006\u0002\b.H\u0007¢\u0006\u0002\u00101\u001a\"\u00102\u001a\u00020)*\u00020)2\u0006\u00103\u001a\u00020\u001d2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\"\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u000f\u001a\u00020\u00108\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003\"\u001b\u0010\u0012\u001a\u00020\u00138@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015\"\u000e\u0010\u0018\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000\".\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001a0\u001a8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u0003\"\u0018\u0010\u001f\u001a\u00020\u000b*\u00020\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!\"\u0018\u0010\u001f\u001a\u00020\u000b*\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010\"¨\u00065"}, d2 = {"DefaultBoundsTransform", "Landroidx/compose/animation/BoundsTransform;", "getDefaultBoundsTransform$annotations", "()V", "DefaultClipInOverlayDuringTransition", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/graphics/Path;", "DefaultEnabled", "Lkotlin/Function0;", "", "DefaultSpring", "Landroidx/compose/animation/core/SpringSpec;", "Landroidx/compose/ui/geometry/Rect;", "ParentClip", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "getParentClip$annotations", "SharedTransitionObserver", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "getSharedTransitionObserver", "()Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "SharedTransitionObserver$delegate", "Lkotlin/Lazy;", "VisualDebugging", "cachedScaleToBoundsImplMap", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/ui/layout/ContentScale;", "Landroidx/compose/ui/Alignment;", "Landroidx/compose/animation/ScaleToBoundsImpl;", "getCachedScaleToBoundsImplMap$annotations", "shouldCache", "getShouldCache", "(Landroidx/compose/ui/Alignment;)Z", "(Landroidx/compose/ui/layout/ContentScale;)Z", "ScaleToBoundsCached", "contentScale", "alignment", "SharedTransitionLayout", "", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function1;", "Landroidx/compose/animation/SharedTransitionScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "SharedTransitionScope", "(Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)V", "createContentScaleModifier", "scaleToBounds", "isEnabled", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SharedTransitionScopeKt {
    public static final boolean VisualDebugging = false;
    private static final Function0<Boolean> DefaultEnabled = new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$DefaultEnabled$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return true;
        }
    };
    private static final SpringSpec<Rect> DefaultSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 1, null);
    private static final SharedTransitionScope.OverlayClip ParentClip = new SharedTransitionScope.OverlayClip() { // from class: androidx.compose.animation.SharedTransitionScopeKt$ParentClip$1
        @Override // androidx.compose.animation.SharedTransitionScope.OverlayClip
        public Path getClipPath(SharedTransitionScope.SharedContentState state, Rect bounds, LayoutDirection layoutDirection, Density density) {
            SharedTransitionScope.SharedContentState parentSharedContentState = state.getParentSharedContentState();
            if (parentSharedContentState != null) {
                return parentSharedContentState.getClipPathInOverlay();
            }
            return null;
        }
    };
    private static final Function2<LayoutDirection, Density, Path> DefaultClipInOverlayDuringTransition = new Function2() { // from class: androidx.compose.animation.SharedTransitionScopeKt$DefaultClipInOverlayDuringTransition$1
        @Override // kotlin.jvm.functions.Function2
        public final Void invoke(LayoutDirection layoutDirection, Density density) {
            return null;
        }
    };
    private static final BoundsTransform DefaultBoundsTransform = new BoundsTransform() { // from class: androidx.compose.animation.SharedTransitionScopeKt$$ExternalSyntheticLambda0
        @Override // androidx.compose.animation.BoundsTransform
        public final FiniteAnimationSpec transform(Rect rect, Rect rect2) {
            FiniteAnimationSpec finiteAnimationSpec;
            finiteAnimationSpec = SharedTransitionScopeKt.DefaultSpring;
            return finiteAnimationSpec;
        }
    };
    private static final Lazy SharedTransitionObserver$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<SnapshotStateObserver>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionObserver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SnapshotStateObserver invoke() {
            SnapshotStateObserver it = new SnapshotStateObserver(new Function1<Function0<? extends Unit>, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionObserver$2.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Function0<? extends Unit> function0) {
                    invoke2((Function0<Unit>) function0);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Function0<Unit> function0) {
                    function0.invoke();
                }
            });
            it.start();
            return it;
        }
    });
    private static final MutableScatterMap<ContentScale, MutableScatterMap<Alignment, ScaleToBoundsImpl>> cachedScaleToBoundsImplMap = new MutableScatterMap<>(0, 1, null);

    private static /* synthetic */ void getCachedScaleToBoundsImplMap$annotations() {
    }

    private static /* synthetic */ void getDefaultBoundsTransform$annotations() {
    }

    private static /* synthetic */ void getParentClip$annotations() {
    }

    public static final void SharedTransitionLayout(final Modifier modifier, final Function3<? super SharedTransitionScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(2043053727);
        ComposerKt.sourceInformation($composer2, "C(SharedTransitionLayout)P(1)112@5418L299,112@5396L321:SharedTransitionScope.kt#xbi5r1");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2043053727, $dirty, -1, "androidx.compose.animation.SharedTransitionLayout (SharedTransitionScope.kt:111)");
            }
            SharedTransitionScope(ComposableLambdaKt.rememberComposableLambda(-130587847, true, new Function4<SharedTransitionScope, Modifier, Composer, Integer, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionLayout$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(SharedTransitionScope sharedTransitionScope, Modifier modifier2, Composer composer, Integer num) {
                    invoke(sharedTransitionScope, modifier2, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:38:0x0178  */
                /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.animation.SharedTransitionScope r28, androidx.compose.ui.Modifier r29, androidx.compose.runtime.Composer r30, int r31) {
                    /*
                        Method dump skipped, instructions count: 380
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionLayout$1.invoke(androidx.compose.animation.SharedTransitionScope, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionLayout$2
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
                    SharedTransitionScopeKt.SharedTransitionLayout(Modifier.this, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void SharedTransitionScope(final Function4<? super SharedTransitionScope, ? super Modifier, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-2093217917);
        ComposerKt.sourceInformation($composer2, "C(SharedTransitionScope)139@6613L1099,139@6598L1114:SharedTransitionScope.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 4 : 2;
        }
        if (($dirty & 3) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2093217917, $dirty, -1, "androidx.compose.animation.SharedTransitionScope (SharedTransitionScope.kt:138)");
            }
            LookaheadScopeKt.LookaheadScope(ComposableLambdaKt.rememberComposableLambda(-863967934, true, new Function3<LookaheadScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionScope$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(LookaheadScope lookaheadScope, Composer composer, Integer num) {
                    invoke(lookaheadScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LookaheadScope $this$LookaheadScope, Composer $composer3, int $changed2) {
                    Object value$iv$iv;
                    Object value$iv;
                    Object value$iv2;
                    Object value$iv3;
                    Object value$iv4;
                    ComposerKt.sourceInformation($composer3, "C140@6644L24,141@6695L60,144@6830L586,158@7450L105,142@6776L789,163@7597L109,163@7574L132:SharedTransitionScope.kt#xbi5r1");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-863967934, $changed2, -1, "androidx.compose.animation.SharedTransitionScope.<anonymous> (SharedTransitionScope.kt:140)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
                    Object it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -538772662, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = new SharedTransitionScopeImpl($this$LookaheadScope, coroutineScope);
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    final SharedTransitionScopeImpl sharedScope = (SharedTransitionScopeImpl) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Function4<SharedTransitionScope, Modifier, Composer, Integer, Unit> function42 = function4;
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -538767816, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionScope$1$1$1
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                return m103invoke3p2s80s(measureScope, measurable, constraints.getValue());
                            }

                            /* renamed from: invoke-3p2s80s, reason: not valid java name */
                            public final MeasureResult m103invoke3p2s80s(final MeasureScope $this$layout, Measurable measurable, long constraints) {
                                final Placeable p = measurable.mo5491measureBRTryo0(constraints);
                                int width = p.getWidth();
                                int height = p.getHeight();
                                final SharedTransitionScopeImpl sharedTransitionScopeImpl = SharedTransitionScopeImpl.this;
                                return MeasureScope.layout$default($this$layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionScope$1$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                        LayoutCoordinates coords = $this$layout2.getCoordinates();
                                        if (coords != null) {
                                            boolean isLookingAhead = MeasureScope.this.isLookingAhead();
                                            SharedTransitionScopeImpl sharedTransitionScopeImpl2 = sharedTransitionScopeImpl;
                                            if (!isLookingAhead) {
                                                sharedTransitionScopeImpl2.setRoot$animation_release(coords);
                                            } else {
                                                sharedTransitionScopeImpl2.setNullableLookaheadRoot$animation_release(coords);
                                            }
                                        }
                                        Placeable.PlacementScope.place$default($this$layout2, p, 0, 0, 0.0f, 4, null);
                                    }
                                }, 4, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier layout = LayoutModifierKt.layout(companion, (Function3) value$iv2);
                    ComposerKt.sourceInformationMarkerStart($composer3, -538748457, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    Object it$iv3 = $composer3.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionScope$1$2$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                                invoke2(contentDrawScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ContentDrawScope $this$drawWithContent) {
                                $this$drawWithContent.drawContent();
                                SharedTransitionScopeImpl.this.drawInOverlay$animation_release($this$drawWithContent);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    function42.invoke(sharedScope, DrawModifierKt.drawWithContent(layout, (Function1) value$iv3), $composer3, 6);
                    Unit unit = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -538743749, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    Object it$iv4 = $composer3.rememberedValue();
                    if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv4 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionScope$1$3$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                                final SharedTransitionScopeImpl sharedTransitionScopeImpl = SharedTransitionScopeImpl.this;
                                return new DisposableEffectResult() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionScope$1$3$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                        SharedTransitionScopeKt.getSharedTransitionObserver().clear(SharedTransitionScopeImpl.this);
                                    }
                                };
                            }
                        };
                        $composer3.updateRememberedValue(value$iv4);
                    } else {
                        value$iv4 = it$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.DisposableEffect(unit, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv4, $composer3, 54);
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
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$SharedTransitionScope$2
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

                public final void invoke(Composer composer, int i) {
                    SharedTransitionScopeKt.SharedTransitionScope(function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier createContentScaleModifier(Modifier $this$createContentScaleModifier, ScaleToBoundsImpl scaleToBounds, final Function0<Boolean> function0) {
        Modifier.Companion companion;
        if (Intrinsics.areEqual(scaleToBounds.getContentScale(), ContentScale.INSTANCE.getCrop())) {
            companion = GraphicsLayerModifierKt.graphicsLayer(Modifier.INSTANCE, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeKt$createContentScaleModifier$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                    invoke2(graphicsLayerScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                    $this$graphicsLayer.setClip(function0.invoke().booleanValue());
                }
            });
        } else {
            companion = Modifier.INSTANCE;
        }
        return $this$createContentScaleModifier.then(companion).then(new SkipToLookaheadElement(scaleToBounds, function0));
    }

    public static final SnapshotStateObserver getSharedTransitionObserver() {
        return (SnapshotStateObserver) SharedTransitionObserver$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScaleToBoundsImpl ScaleToBoundsCached(ContentScale contentScale, Alignment alignment) {
        if (getShouldCache(contentScale) && getShouldCache(alignment)) {
            MutableScatterMap this_$iv = cachedScaleToBoundsImplMap;
            MutableScatterMap<Alignment, ScaleToBoundsImpl> this_$iv2 = this_$iv.get(contentScale);
            if (this_$iv2 == null) {
                this_$iv2 = new MutableScatterMap<>(0, 1, null);
                this_$iv.set(contentScale, this_$iv2);
            }
            MutableScatterMap map = this_$iv2;
            ScaleToBoundsImpl scaleToBoundsImpl = map.get(alignment);
            if (scaleToBoundsImpl == null) {
                scaleToBoundsImpl = new ScaleToBoundsImpl(contentScale, alignment);
                map.set(alignment, scaleToBoundsImpl);
            }
            return scaleToBoundsImpl;
        }
        return new ScaleToBoundsImpl(contentScale, alignment);
    }

    private static final boolean getShouldCache(Alignment $this$shouldCache) {
        return $this$shouldCache == Alignment.INSTANCE.getTopStart() || $this$shouldCache == Alignment.INSTANCE.getTopCenter() || $this$shouldCache == Alignment.INSTANCE.getTopEnd() || $this$shouldCache == Alignment.INSTANCE.getCenterStart() || $this$shouldCache == Alignment.INSTANCE.getCenter() || $this$shouldCache == Alignment.INSTANCE.getCenterEnd() || $this$shouldCache == Alignment.INSTANCE.getBottomStart() || $this$shouldCache == Alignment.INSTANCE.getBottomCenter() || $this$shouldCache == Alignment.INSTANCE.getBottomEnd();
    }

    private static final boolean getShouldCache(ContentScale $this$shouldCache) {
        return $this$shouldCache == ContentScale.INSTANCE.getFillWidth() || $this$shouldCache == ContentScale.INSTANCE.getFillHeight() || $this$shouldCache == ContentScale.INSTANCE.getFillBounds() || $this$shouldCache == ContentScale.INSTANCE.getFit() || $this$shouldCache == ContentScale.INSTANCE.getCrop() || $this$shouldCache == ContentScale.INSTANCE.getNone() || $this$shouldCache == ContentScale.INSTANCE.getInside();
    }
}
