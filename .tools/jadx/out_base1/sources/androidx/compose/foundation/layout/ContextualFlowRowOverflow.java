package androidx.compose.foundation.layout;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FlowLayoutOverflow.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0087\u0001\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00120\b\u0002\u0010\u0007\u001a*\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f\u0018\u00010\b\u00120\b\u0002\u0010\u0010\u001a*\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f\u0018\u00010\b¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "Landroidx/compose/foundation/layout/FlowLayoutOverflow;", "type", "Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;", "minLinesToShowCollapse", "", "minCrossAxisSizeToShowCollapse", "seeMoreGetter", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "state", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "collapseGetter", "(Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;IILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Companion", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextualFlowRowOverflow extends FlowLayoutOverflow {
    public static final int $stable = 0;
    private static final ContextualFlowRowOverflow Clip;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ContextualFlowRowOverflow Visible;

    public /* synthetic */ ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType overflowType, int i, int i2, Function1 function1, Function1 function12, DefaultConstructorMarker defaultConstructorMarker) {
        this(overflowType, i, i2, function1, function12);
    }

    /* synthetic */ ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType overflowType, int i, int i2, Function1 function1, Function1 function12, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(overflowType, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? null : function1, (i3 & 16) != 0 ? null : function12);
    }

    private ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType type, int minLinesToShowCollapse, int minCrossAxisSizeToShowCollapse, Function1<? super FlowLayoutOverflowState, ? extends Function2<? super Composer, ? super Integer, Unit>> function1, Function1<? super FlowLayoutOverflowState, ? extends Function2<? super Composer, ? super Integer, Unit>> function12) {
        super(type, minLinesToShowCollapse, minCrossAxisSizeToShowCollapse, function1, function12, null);
    }

    /* compiled from: FlowLayoutOverflow.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u000b\u001a\u00020\u00042\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\u0012Jb\u0010\u0013\u001a\u00020\u00042\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0003\u001a\u00020\u00048GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/layout/ContextualFlowRowOverflow$Companion;", "", "()V", "Clip", "Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "getClip$annotations", "getClip", "()Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "Visible", "getVisible$annotations", "getVisible", "expandIndicator", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ContextualFlowRowOverflowScope;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "expandOrCollapseIndicator", "collapseIndicator", "minRowsToShowCollapse", "", "minHeightToShowCollapse", "Landroidx/compose/ui/unit/Dp;", "expandOrCollapseIndicator--jt2gSs", "(Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;IFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getClip$annotations() {
        }

        public static /* synthetic */ void getVisible$annotations() {
        }

        private Companion() {
        }

        public final ContextualFlowRowOverflow getVisible() {
            return ContextualFlowRowOverflow.Visible;
        }

        public final ContextualFlowRowOverflow getClip() {
            return ContextualFlowRowOverflow.Clip;
        }

        public final ContextualFlowRowOverflow expandIndicator(final Function3<? super ContextualFlowRowOverflowScope, ? super Composer, ? super Integer, Unit> content) {
            Function1 seeMoreGetter = new Function1<FlowLayoutOverflowState, Function2<? super Composer, ? super Integer, ? extends Unit>>() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$expandIndicator$seeMoreGetter$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Function2<Composer, Integer, Unit> invoke(final FlowLayoutOverflowState state) {
                    final Function3<ContextualFlowRowOverflowScope, Composer, Integer, Unit> function3 = content;
                    return ComposableLambdaKt.composableLambdaInstance(1850548683, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$expandIndicator$seeMoreGetter$1.1
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

                        public final void invoke(Composer $composer, int $changed) {
                            ComposerKt.sourceInformation($composer, "C415@17942L9:FlowLayoutOverflow.kt#2w3rfo");
                            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1850548683, $changed, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandIndicator.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:414)");
                                }
                                ContextualFlowRowOverflowScopeImpl scope = new ContextualFlowRowOverflowScopeImpl(FlowLayoutOverflowState.this);
                                function3.invoke(scope, $composer, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer.skipToGroupEnd();
                        }
                    });
                }
            };
            return new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.ExpandIndicator, 0, 0, seeMoreGetter, null, 22, null);
        }

        /* renamed from: expandOrCollapseIndicator--jt2gSs, reason: not valid java name */
        public final ContextualFlowRowOverflow m591expandOrCollapseIndicatorjt2gSs(final Function3<? super ContextualFlowRowOverflowScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super ContextualFlowRowOverflowScope, ? super Composer, ? super Integer, Unit> function32, int minRowsToShowCollapse, float minHeightToShowCollapse, Composer $composer, int $changed, int i) {
            Object value$iv;
            ComposerKt.sourceInformationMarkerStart($composer, 1435293820, "C(expandOrCollapseIndicator)P(1!1,3,2:c#ui.unit.Dp)*458@20145L7,461@20241L1109:FlowLayoutOverflow.kt#2w3rfo");
            int minRowsToShowCollapse2 = (i & 4) != 0 ? 1 : minRowsToShowCollapse;
            float minHeightToShowCollapse2 = (i & 8) != 0 ? Dp.m6626constructorimpl(0) : minHeightToShowCollapse;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1435293820, $changed, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator (FlowLayoutOverflow.kt:457)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$expandOrCollapseIndicator__jt2gSs_u24lambda_u240 = (Density) consume;
            int minHeightToShowCollapsePx = $this$expandOrCollapseIndicator__jt2gSs_u24lambda_u240.mo358roundToPx0680j_4(minHeightToShowCollapse2);
            ComposerKt.sourceInformationMarkerStart($composer, 172958269, "CC(remember):FlowLayoutOverflow.kt#9igjgp");
            boolean invalid$iv = (((($changed & 896) ^ 384) > 256 && $composer.changed(minRowsToShowCollapse2)) || ($changed & 384) == 256) | $composer.changed(minHeightToShowCollapsePx) | (((($changed & 14) ^ 6) > 4 && $composer.changed(function3)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(function32)) || ($changed & 48) == 32);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Function1 seeMoreGetter = new Function1<FlowLayoutOverflowState, Function2<? super Composer, ? super Integer, ? extends Unit>>() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$expandOrCollapseIndicator$1$seeMoreGetter$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Function2<Composer, Integer, Unit> invoke(final FlowLayoutOverflowState state) {
                        final Function3<ContextualFlowRowOverflowScope, Composer, Integer, Unit> function33 = function3;
                        return ComposableLambdaKt.composableLambdaInstance(897838875, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$expandOrCollapseIndicator$1$seeMoreGetter$1.1
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

                            public final void invoke(Composer $composer2, int $changed2) {
                                ComposerKt.sourceInformation($composer2, "C470@20630L17:FlowLayoutOverflow.kt#2w3rfo");
                                if (($changed2 & 3) != 2 || !$composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(897838875, $changed2, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator.<anonymous>.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:469)");
                                    }
                                    ContextualFlowRowOverflowScopeImpl scope = new ContextualFlowRowOverflowScopeImpl(FlowLayoutOverflowState.this);
                                    function33.invoke(scope, $composer2, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer2.skipToGroupEnd();
                            }
                        });
                    }
                };
                Function1 collapseGetter = new Function1<FlowLayoutOverflowState, Function2<? super Composer, ? super Integer, ? extends Unit>>() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$expandOrCollapseIndicator$1$collapseGetter$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Function2<Composer, Integer, Unit> invoke(final FlowLayoutOverflowState state) {
                        final Function3<ContextualFlowRowOverflowScope, Composer, Integer, Unit> function33 = function32;
                        return ComposableLambdaKt.composableLambdaInstance(307858874, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.layout.ContextualFlowRowOverflow$Companion$expandOrCollapseIndicator$1$collapseGetter$1.1
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

                            public final void invoke(Composer $composer2, int $changed2) {
                                ComposerKt.sourceInformation($composer2, "C477@20904L19:FlowLayoutOverflow.kt#2w3rfo");
                                if (($changed2 & 3) != 2 || !$composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(307858874, $changed2, -1, "androidx.compose.foundation.layout.ContextualFlowRowOverflow.Companion.expandOrCollapseIndicator.<anonymous>.<anonymous>.<anonymous> (FlowLayoutOverflow.kt:476)");
                                    }
                                    ContextualFlowRowOverflowScopeImpl scope = new ContextualFlowRowOverflowScopeImpl(FlowLayoutOverflowState.this);
                                    function33.invoke(scope, $composer2, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer2.skipToGroupEnd();
                            }
                        });
                    }
                };
                value$iv = new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator, minRowsToShowCollapse2, minHeightToShowCollapsePx, seeMoreGetter, collapseGetter, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ContextualFlowRowOverflow contextualFlowRowOverflow = (ContextualFlowRowOverflow) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return contextualFlowRowOverflow;
        }
    }

    static {
        int i = 0;
        Function1 function1 = null;
        Visible = new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.Visible, 0, i, null, function1, 30, null);
        Clip = new ContextualFlowRowOverflow(FlowLayoutOverflow.OverflowType.Clip, i, 0, function1, null, 30, null);
    }
}
