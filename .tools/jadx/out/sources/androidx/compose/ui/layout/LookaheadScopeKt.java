package androidx.compose.ui.layout;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LookaheadScope.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a0\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f¢\u0006\u0002\b\bH\u0007¢\u0006\u0002\u0010\u0010\u001a \u0001\u0010\u0011\u001a\u00020\u0012*\u00020\u00122!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00070\f2.\b\u0002\u0010\u0016\u001a(\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001¢\u0006\u0002\b\b2A\u0010\u0017\u001a=\u0012\u0004\u0012\u00020\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0018¢\u0006\u0002\b\b\u001a6\u0010\u001f\u001a\u00020 *\u00020\r2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0007H\u0000ø\u0001\u0000¢\u0006\u0004\b%\u0010&\"4\u0010\u0000\u001a(\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001¢\u0006\u0002\b\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"defaultPlacementApproachInProgress", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "lookaheadCoordinates", "", "Lkotlin/ExtensionFunctionType;", "LookaheadScope", "", "content", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LookaheadScope;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/UiComposable;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "approachLayout", "Landroidx/compose/ui/Modifier;", "isMeasurementApproachInProgress", "Landroidx/compose/ui/unit/IntSize;", "lookaheadSize", "isPlacementApproachInProgress", "approachMeasure", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "localLookaheadPositionOf", "Landroidx/compose/ui/geometry/Offset;", "coordinates", "sourceCoordinates", "relativeToSource", "includeMotionFrameOfReference", "localLookaheadPositionOf-Fgt4K4Q", "(Landroidx/compose/ui/layout/LookaheadScope;Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LookaheadScopeKt {
    private static final Function2<Placeable.PlacementScope, LayoutCoordinates, Boolean> defaultPlacementApproachInProgress = new Function2<Placeable.PlacementScope, LayoutCoordinates, Boolean>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$defaultPlacementApproachInProgress$1
        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(Placeable.PlacementScope $this$null, LayoutCoordinates it) {
            return false;
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    public static final void LookaheadScope(final Function3<? super LookaheadScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        Object obj;
        Composer startRestartGroup = composer.startRestartGroup(-1078066484);
        ComposerKt.sourceInformation(startRestartGroup, "C(LookaheadScope)51@2069L33,52@2107L484:LookaheadScope.kt#80mrfh");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changedInstance(function3) ? 4 : 2;
        }
        if ((i2 & 3) != 2 || !startRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1078066484, i2, -1, "androidx.compose.ui.layout.LookaheadScope (LookaheadScope.kt:50)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2062858814, "CC(remember):LookaheadScope.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new LookaheadScopeImpl(null, 1, 0 == true ? 1 : 0);
                startRestartGroup.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            LookaheadScopeImpl lookaheadScopeImpl = (LookaheadScopeImpl) obj;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            LookaheadScopeKt$LookaheadScope$1 lookaheadScopeKt$LookaheadScope$1 = new Function0<LayoutNode>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LayoutNode invoke() {
                    return new LayoutNode(true, 0, 2, null);
                }
            };
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(lookaheadScopeKt$LookaheadScope$1);
            } else {
                startRestartGroup.useNode();
            }
            Composer m3656constructorimpl = Updater.m3656constructorimpl(startRestartGroup);
            Updater.m3660initimpl(m3656constructorimpl, new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode) {
                    invoke2(layoutNode);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LayoutNode $this$init) {
                    $this$init.setVirtualLookaheadRoot$ui_release(true);
                }
            });
            Updater.m3663setimpl(m3656constructorimpl, lookaheadScopeImpl, new Function2<LayoutNode, LookaheadScopeImpl, Unit>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, LookaheadScopeImpl lookaheadScopeImpl2) {
                    invoke2(layoutNode, lookaheadScopeImpl2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final LayoutNode $this$set, LookaheadScopeImpl scope) {
                    scope.setScopeCoordinates(new Function0<LayoutCoordinates>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2.1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final LayoutCoordinates invoke() {
                            LayoutNode parent$ui_release = LayoutNode.this.getParent$ui_release();
                            Intrinsics.checkNotNull(parent$ui_release);
                            return parent$ui_release.getInnerCoordinator$ui_release().getCoordinates();
                        }
                    });
                }
            });
            int i3 = (6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1405016920, "C64@2566L9:LookaheadScope.kt#80mrfh");
            function3.invoke(lookaheadScopeImpl, startRestartGroup, Integer.valueOf((i2 << 3) & 112));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    LookaheadScopeKt.LookaheadScope(function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static /* synthetic */ Modifier approachLayout$default(Modifier modifier, Function1 function1, Function2 function2, Function3 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = defaultPlacementApproachInProgress;
        }
        return approachLayout(modifier, function1, function2, function3);
    }

    public static final Modifier approachLayout(Modifier $this$approachLayout, Function1<? super IntSize, Boolean> function1, Function2<? super Placeable.PlacementScope, ? super LayoutCoordinates, Boolean> function2, Function3<? super ApproachMeasureScope, ? super Measurable, ? super Constraints, ? extends MeasureResult> function3) {
        return $this$approachLayout.then(new ApproachLayoutElement(function3, function1, function2));
    }

    /* renamed from: localLookaheadPositionOf-Fgt4K4Q, reason: not valid java name */
    public static final long m5520localLookaheadPositionOfFgt4K4Q(LookaheadScope $this$localLookaheadPositionOf_u2dFgt4K4Q, LayoutCoordinates coordinates, LayoutCoordinates sourceCoordinates, long relativeToSource, boolean includeMotionFrameOfReference) {
        LayoutCoordinates lookaheadCoords = $this$localLookaheadPositionOf_u2dFgt4K4Q.toLookaheadCoordinates(coordinates);
        LayoutCoordinates source = $this$localLookaheadPositionOf_u2dFgt4K4Q.toLookaheadCoordinates(sourceCoordinates);
        if (lookaheadCoords instanceof LookaheadLayoutCoordinates) {
            return lookaheadCoords.mo5501localPositionOfS_NoaFU(source, relativeToSource, includeMotionFrameOfReference);
        }
        if (source instanceof LookaheadLayoutCoordinates) {
            return Offset.m3931unaryMinusF1C5BW0(source.mo5501localPositionOfS_NoaFU(lookaheadCoords, relativeToSource, includeMotionFrameOfReference));
        }
        return lookaheadCoords.mo5501localPositionOfS_NoaFU(lookaheadCoords, relativeToSource, includeMotionFrameOfReference);
    }
}
