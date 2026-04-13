package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SharedTransitionScope.kt */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002:\u0001uB\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\u0015\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202H\u0000¢\u0006\u0002\b3J\u0015\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001eH\u0000¢\u0006\u0002\b6J\u0015\u00107\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001eH\u0000¢\u0006\u0002\b8J\u0015\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\u0015\u0010=\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020$H\u0017¢\u0006\u0002\u0010BJM\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\nH\u0003¢\u0006\u0002\u0010OJ\u0010\u0010P\u001a\u00020%2\u0006\u0010A\u001a\u00020$H\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J3\u0010Q\u001a\u00020R*\u00020\u00122\u0006\u0010S\u001a\u00020\u00122\b\b\u0002\u0010T\u001a\u00020R2\b\b\u0002\u0010U\u001a\u00020\nH\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ>\u0010X\u001a\u00020Y*\u00020Y2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\n0\u001a2\u0006\u0010L\u001a\u00020M2\u001a\u0010K\u001a\u0016\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020]\u0012\u0006\u0012\u0004\u0018\u00010^0[H\u0016J\\\u0010_\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020i2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016Jp\u0010j\u001a\u00020Y\"\u0004\b\u0000\u0010k*\u00020Y2\u0006\u0010J\u001a\u00020@2\u000e\u0010l\u001a\n\u0012\u0004\u0012\u0002Hk\u0018\u00010m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u0002Hk\u0012\u0004\u0012\u00020\n0'2\u0006\u0010f\u001a\u00020g2\b\b\u0002\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0002JS\u0010o\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010n\u001a\u00020\n2\b\b\u0002\u0010f\u001a\u00020g2\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010N\u001a\u00020\n2\b\b\u0002\u0010L\u001a\u00020M2\b\b\u0002\u0010K\u001a\u00020-H\u0000¢\u0006\u0002\bpJD\u0010D\u001a\u00020Y*\u00020Y2\u0006\u0010q\u001a\u00020@2\u0006\u0010`\u001a\u00020a2\u0006\u0010f\u001a\u00020g2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016JD\u0010r\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010n\u001a\u00020\n2\u0006\u0010f\u001a\u00020g2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016J\f\u0010s\u001a\u00020Y*\u00020YH\u0016J\r\u0010t\u001a\u00020\u0012*\u00020\u0012H\u0096\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\u0012X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0018R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001b0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010(\u001a\u00020\u0012*\u00020)X\u0096\u0005¢\u0006\u0006\u001a\u0004\b*\u0010+\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006v"}, d2 = {"Landroidx/compose/animation/SharedTransitionScopeImpl;", "Landroidx/compose/animation/SharedTransitionScope;", "Landroidx/compose/ui/layout/LookaheadScope;", "lookaheadScope", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/ui/layout/LookaheadScope;Lkotlinx/coroutines/CoroutineScope;)V", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "<set-?>", "", "isTransitionActive", "()Z", "setTransitionActive", "(Z)V", "isTransitionActive$delegate", "Landroidx/compose/runtime/MutableState;", "lookaheadRoot", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getLookaheadRoot$animation_release", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "nullableLookaheadRoot", "getNullableLookaheadRoot$animation_release", "setNullableLookaheadRoot$animation_release", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "observeAnimatingBlock", "Lkotlin/Function0;", "", "renderers", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/animation/LayerRenderer;", "root", "getRoot$animation_release", "setRoot$animation_release", "sharedElements", "Landroidx/collection/MutableScatterMap;", "", "Landroidx/compose/animation/SharedElement;", "updateTransitionActiveness", "Lkotlin/Function1;", "lookaheadScopeCoordinates", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getLookaheadScopeCoordinates", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)Landroidx/compose/ui/layout/LayoutCoordinates;", "OverlayClip", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "drawInOverlay", "scope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawInOverlay$animation_release", "onLayerRendererCreated", "renderer", "onLayerRendererCreated$animation_release", "onLayerRendererRemoved", "onLayerRendererRemoved$animation_release", "onStateAdded", "sharedElementState", "Landroidx/compose/animation/SharedElementInternalState;", "onStateAdded$animation_release", "onStateRemoved", "onStateRemoved$animation_release", "rememberSharedContentState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "key", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "rememberSharedElementState", "sharedElement", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "placeHolderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;", "renderOnlyWhenVisible", "sharedContentState", "clipInOverlayDuringTransition", "zIndexInOverlay", "", "renderInOverlayDuringTransition", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsAnimation;Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;ZLandroidx/compose/animation/SharedTransitionScope$SharedContentState;Landroidx/compose/animation/SharedTransitionScope$OverlayClip;FZLandroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedElementInternalState;", "sharedElementsFor", "localLookaheadPositionOf", "Landroidx/compose/ui/geometry/Offset;", "sourceCoordinates", "relativeToSource", "includeMotionFrameOfReference", "localLookaheadPositionOf-au-aQtc", "(Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "renderInSharedTransitionScopeOverlay", "Landroidx/compose/ui/Modifier;", "renderInOverlay", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/graphics/Path;", "sharedBounds", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "boundsTransform", "Landroidx/compose/animation/BoundsTransform;", "resizeMode", "Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "sharedBoundsImpl", "T", "parentTransition", "Landroidx/compose/animation/core/Transition;", "visible", "sharedBoundsWithCallerManagedVisibility", "sharedBoundsWithCallerManagedVisibility$animation_release", "state", "sharedElementWithCallerManagedVisibility", "skipToLookaheadSize", "toLookaheadCoordinates", "ShapeBasedClip", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SharedTransitionScopeImpl implements SharedTransitionScope, LookaheadScope {
    public static final int $stable = 0;
    private final /* synthetic */ LookaheadScope $$delegate_0;
    private final CoroutineScope coroutineScope;
    private LayoutCoordinates nullableLookaheadRoot;
    public LayoutCoordinates root;

    /* renamed from: isTransitionActive$delegate, reason: from kotlin metadata */
    private final MutableState isTransitionActive = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private final Function0<Unit> observeAnimatingBlock = new Function0<Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$observeAnimatingBlock$1
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
            ScatterMap scatterMap;
            scatterMap = SharedTransitionScopeImpl.this.sharedElements;
            ScatterMap this_$iv = scatterMap;
            Object[] k$iv$iv = this_$iv.keys;
            Object[] v$iv$iv = this_$iv.values;
            long[] m$iv$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            if (0 > lastIndex$iv$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                ScatterMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                        long value$iv$iv$iv$iv = 255 & slot$iv$iv$iv;
                        if (value$iv$iv$iv$iv < 128) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object obj = k$iv$iv[index$iv$iv$iv];
                            Object value$iv = v$iv$iv[index$iv$iv$iv];
                            SharedElement element = (SharedElement) value$iv;
                            if (element.isAnimating()) {
                                return;
                            }
                        }
                        slot$iv$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv$iv != 8) {
                        return;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    return;
                }
                i$iv$iv$iv++;
                this_$iv = this_$iv2;
            }
        }
    };
    private final Function1<SharedTransitionScope, Unit> updateTransitionActiveness = new Function1<SharedTransitionScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$updateTransitionActiveness$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SharedTransitionScope sharedTransitionScope) {
            invoke2(sharedTransitionScope);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SharedTransitionScope it) {
            SharedTransitionScopeImpl.this.updateTransitionActiveness();
        }
    };
    private final SnapshotStateList<LayerRenderer> renderers = SnapshotStateKt.mutableStateListOf();
    private final MutableScatterMap<Object, SharedElement> sharedElements = new MutableScatterMap<>(0, 1, null);

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates getLookaheadScopeCoordinates(Placeable.PlacementScope placementScope) {
        return this.$$delegate_0.getLookaheadScopeCoordinates(placementScope);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    /* renamed from: localLookaheadPositionOf-au-aQtc, reason: not valid java name */
    public long mo102localLookaheadPositionOfauaQtc(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2, long j, boolean z) {
        return this.$$delegate_0.mo102localLookaheadPositionOfauaQtc(layoutCoordinates, layoutCoordinates2, j, z);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates layoutCoordinates) {
        return this.$$delegate_0.toLookaheadCoordinates(layoutCoordinates);
    }

    public SharedTransitionScopeImpl(LookaheadScope lookaheadScope, CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
        this.$$delegate_0 = lookaheadScope;
    }

    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    private void setTransitionActive(boolean z) {
        MutableState $this$setValue$iv = this.isTransitionActive;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public boolean isTransitionActive() {
        State $this$getValue$iv = this.isTransitionActive;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier skipToLookaheadSize(Modifier modifier) {
        return modifier.then(new SkipToLookaheadElement(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier renderInSharedTransitionScopeOverlay(Modifier $this$renderInSharedTransitionScopeOverlay, Function0<Boolean> function0, float zIndexInOverlay, Function2<? super LayoutDirection, ? super Density, ? extends Path> function2) {
        return $this$renderInSharedTransitionScopeOverlay.then(new RenderInTransitionOverlayNodeElement(this, function0, zIndexInOverlay, function2));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedElement(Modifier $this$sharedElement, SharedTransitionScope.SharedContentState state, AnimatedVisibilityScope animatedVisibilityScope, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return sharedBoundsImpl($this$sharedElement, state, animatedVisibilityScope.getTransition(), new Function1<EnterExitState, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedElement$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(EnterExitState it) {
                return Boolean.valueOf(it == EnterExitState.Visible);
            }
        }, boundsTransform, placeHolderSize, true, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedBounds(Modifier $this$sharedBounds, final SharedTransitionScope.SharedContentState sharedContentState, final AnimatedVisibilityScope animatedVisibilityScope, final EnterTransition enter, final ExitTransition exit, BoundsTransform boundsTransform, final SharedTransitionScope.ResizeMode resizeMode, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return ComposedModifierKt.composed$default(sharedBoundsImpl($this$sharedBounds, sharedContentState, animatedVisibilityScope.getTransition(), new Function1<EnterExitState, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(EnterExitState it) {
                return Boolean.valueOf(it == EnterExitState.Visible);
            }
        }, boundsTransform, placeHolderSize, false, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition), null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$2
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
                Modifier.Companion companion;
                Object value$iv2;
                $composer.startReplaceGroup(-419341573);
                ComposerKt.sourceInformation($composer, "C779@41927L35,771@41404L656:SharedTransitionScope.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-419341573, $changed, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBounds.<anonymous> (SharedTransitionScope.kt:770)");
                }
                Transition<EnterExitState> transition = AnimatedVisibilityScope.this.getTransition();
                EnterTransition enterTransition = enter;
                ExitTransition exitTransition = exit;
                ComposerKt.sourceInformationMarkerStart($composer, -1411456741, "CC(remember):SharedTransitionScope.kt#9igjgp");
                boolean invalid$iv = $composer.changedInstance(sharedContentState);
                final SharedTransitionScope.SharedContentState sharedContentState2 = sharedContentState;
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function0) new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$2$1$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(SharedTransitionScope.SharedContentState.this.isMatchFound());
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier createModifier = EnterExitTransitionKt.createModifier(transition, enterTransition, exitTransition, (Function0) value$iv, "enter/exit for " + sharedContentState.getKey(), $composer, 0, 0);
                if (resizeMode instanceof ScaleToBoundsImpl) {
                    $composer.startReplaceGroup(-805247216);
                    ComposerKt.sourceInformation($composer, "784@42227L530");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ScaleToBoundsImpl scaleToBoundsImpl = (ScaleToBoundsImpl) resizeMode;
                    ComposerKt.sourceInformationMarkerStart($composer, -1411446646, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    boolean invalid$iv2 = $composer.changedInstance(sharedContentState);
                    final SharedTransitionScope.SharedContentState sharedContentState3 = sharedContentState;
                    Object it$iv2 = $composer.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = (Function0) new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$2$2$1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                return Boolean.valueOf(SharedTransitionScope.SharedContentState.this.isMatchFound());
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    companion = SharedTransitionScopeKt.createContentScaleModifier(companion2, scaleToBoundsImpl, (Function0) value$iv2);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(-804630006);
                    $composer.endReplaceGroup();
                    companion = Modifier.INSTANCE;
                }
                Modifier then = createModifier.then(companion);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return then;
            }
        }, 1, null);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedElementWithCallerManagedVisibility(Modifier $this$sharedElementWithCallerManagedVisibility, SharedTransitionScope.SharedContentState sharedContentState, final boolean visible, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return sharedBoundsImpl($this$sharedElementWithCallerManagedVisibility, sharedContentState, null, new Function1<Unit, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedElementWithCallerManagedVisibility$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Unit it) {
                return Boolean.valueOf(visible);
            }
        }, boundsTransform, placeHolderSize, true, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition);
    }

    public static /* synthetic */ Modifier sharedBoundsWithCallerManagedVisibility$animation_release$default(SharedTransitionScopeImpl sharedTransitionScopeImpl, Modifier modifier, SharedTransitionScope.SharedContentState sharedContentState, boolean z, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z2, float f, SharedTransitionScope.OverlayClip overlayClip, int i, Object obj) {
        BoundsTransform boundsTransform2;
        SharedTransitionScope.OverlayClip overlayClip2;
        SharedTransitionScope.OverlayClip overlayClip3;
        BoundsTransform boundsTransform3;
        if ((i & 4) != 0) {
            boundsTransform3 = SharedTransitionScopeKt.DefaultBoundsTransform;
            boundsTransform2 = boundsTransform3;
        } else {
            boundsTransform2 = boundsTransform;
        }
        SharedTransitionScope.PlaceHolderSize contentSize = (i & 8) != 0 ? SharedTransitionScope.PlaceHolderSize.INSTANCE.getContentSize() : placeHolderSize;
        boolean z3 = (i & 16) != 0 ? true : z2;
        float f2 = (i & 32) != 0 ? 0.0f : f;
        if ((i & 64) != 0) {
            overlayClip3 = SharedTransitionScopeKt.ParentClip;
            overlayClip2 = overlayClip3;
        } else {
            overlayClip2 = overlayClip;
        }
        return sharedTransitionScopeImpl.sharedBoundsWithCallerManagedVisibility$animation_release(modifier, sharedContentState, z, boundsTransform2, contentSize, z3, f2, overlayClip2);
    }

    public final Modifier sharedBoundsWithCallerManagedVisibility$animation_release(Modifier $this$sharedBoundsWithCallerManagedVisibility, SharedTransitionScope.SharedContentState sharedContentState, final boolean visible, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderInOverlayDuringTransition, float zIndexInOverlay, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return sharedBoundsImpl($this$sharedBoundsWithCallerManagedVisibility, sharedContentState, null, new Function1<Unit, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBoundsWithCallerManagedVisibility$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Unit it) {
                return Boolean.valueOf(visible);
            }
        }, boundsTransform, placeHolderSize, false, renderInOverlayDuringTransition, zIndexInOverlay, clipInOverlayDuringTransition);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public SharedTransitionScope.OverlayClip OverlayClip(Shape clipShape) {
        return new ShapeBasedClip(clipShape);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public SharedTransitionScope.SharedContentState rememberSharedContentState(Object key, Composer $composer, int $changed) {
        Object value$iv;
        $composer.startReplaceGroup(799702514);
        ComposerKt.sourceInformation($composer, "C(rememberSharedContentState)912@49465L53:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(799702514, $changed, -1, "androidx.compose.animation.SharedTransitionScopeImpl.rememberSharedContentState (SharedTransitionScope.kt:912)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 935469886, "CC(remember):SharedTransitionScope.kt#9igjgp");
        boolean invalid$iv = $composer.changed(key);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new SharedTransitionScope.SharedContentState(key);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        SharedTransitionScope.SharedContentState sharedContentState = (SharedTransitionScope.SharedContentState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return sharedContentState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTransitionActiveness() {
        long j;
        boolean isActive;
        ScatterMap this_$iv = this.sharedElements;
        int $i$f$any = 0;
        Object[] k$iv$iv = this_$iv.keys;
        Object[] v$iv$iv = this_$iv.values;
        long[] m$iv$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        int i = 8;
        if (0 <= lastIndex$iv$iv$iv) {
            loop0: while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                j = 255;
                ScatterMap this_$iv2 = this_$iv;
                int $i$f$any2 = $i$f$any;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        if (value$iv$iv$iv$iv < 128) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object obj = k$iv$iv[index$iv$iv$iv];
                            Object value$iv = v$iv$iv[index$iv$iv$iv];
                            SharedElement element = (SharedElement) value$iv;
                            if (element.isAnimating()) {
                                isActive = true;
                                break loop0;
                            }
                        }
                        slot$iv$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv$iv != 8) {
                        break;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    break;
                }
                i$iv$iv$iv++;
                this_$iv = this_$iv2;
                $i$f$any = $i$f$any2;
            }
        } else {
            j = 255;
        }
        isActive = false;
        if (isActive != isTransitionActive()) {
            setTransitionActive(isActive);
            if (!isActive) {
                ScatterMap this_$iv3 = this.sharedElements;
                Object[] k$iv = this_$iv3.keys;
                Object[] v$iv = this_$iv3.values;
                long[] m$iv$iv = this_$iv3.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 <= lastIndex$iv$iv) {
                    while (true) {
                        long slot$iv$iv = m$iv$iv[i$iv$iv];
                        int i2 = i;
                        long slot$iv$iv2 = slot$iv$iv;
                        boolean isActive2 = isActive;
                        ScatterMap this_$iv4 = this_$iv3;
                        if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                            int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                            for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                                long value$iv$iv$iv = slot$iv$iv2 & j;
                                int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                                if ($i$f$isFull != 0) {
                                    int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                    Object obj2 = k$iv[index$iv$iv];
                                    SharedElement element2 = (SharedElement) v$iv[index$iv$iv];
                                    element2.onSharedTransitionFinished();
                                }
                                slot$iv$iv2 >>= i2;
                            }
                            if (bitCount$iv$iv != i2) {
                                break;
                            }
                        }
                        if (i$iv$iv == lastIndex$iv$iv) {
                            break;
                        }
                        i$iv$iv++;
                        isActive = isActive2;
                        this_$iv3 = this_$iv4;
                        i = 8;
                    }
                }
            }
        }
        ScatterMap this_$iv5 = this.sharedElements;
        int $i$f$forEach = 0;
        Object[] k$iv2 = this_$iv5.keys;
        Object[] v$iv2 = this_$iv5.values;
        long[] m$iv$iv2 = this_$iv5.metadata;
        int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
        int i$iv$iv2 = 0;
        if (0 <= lastIndex$iv$iv2) {
            while (true) {
                long slot$iv$iv3 = m$iv$iv2[i$iv$iv2];
                ScatterMap this_$iv6 = this_$iv5;
                int $i$f$forEach2 = $i$f$forEach;
                if ((((~slot$iv$iv3) << 7) & slot$iv$iv3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                    for (int j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                        long value$iv$iv$iv2 = slot$iv$iv3 & j;
                        int $i$f$isFull2 = value$iv$iv$iv2 < 128 ? 1 : 0;
                        if ($i$f$isFull2 != 0) {
                            int index$iv$iv2 = (i$iv$iv2 << 3) + j$iv$iv2;
                            Object obj3 = k$iv2[index$iv$iv2];
                            SharedElement element3 = (SharedElement) v$iv2[index$iv$iv2];
                            element3.updateMatch();
                        }
                        slot$iv$iv3 >>= 8;
                    }
                    if (bitCount$iv$iv2 != 8) {
                        break;
                    }
                }
                if (i$iv$iv2 == lastIndex$iv$iv2) {
                    break;
                }
                i$iv$iv2++;
                this_$iv5 = this_$iv6;
                $i$f$forEach = $i$f$forEach2;
            }
        }
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(this, this.updateTransitionActiveness, this.observeAnimatingBlock);
    }

    static /* synthetic */ Modifier sharedBoundsImpl$default(SharedTransitionScopeImpl sharedTransitionScopeImpl, Modifier modifier, SharedTransitionScope.SharedContentState sharedContentState, Transition transition, Function1 function1, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z, boolean z2, float f, SharedTransitionScope.OverlayClip overlayClip, int i, Object obj) {
        SharedTransitionScope.PlaceHolderSize placeHolderSize2;
        if ((i & 16) == 0) {
            placeHolderSize2 = placeHolderSize;
        } else {
            placeHolderSize2 = SharedTransitionScope.PlaceHolderSize.INSTANCE.getContentSize();
        }
        return sharedTransitionScopeImpl.sharedBoundsImpl(modifier, sharedContentState, transition, function1, boundsTransform, placeHolderSize2, z, z2, f, overlayClip);
    }

    private final <T> Modifier sharedBoundsImpl(Modifier $this$sharedBoundsImpl, final SharedTransitionScope.SharedContentState sharedContentState, final Transition<T> transition, final Function1<? super T, Boolean> function1, final BoundsTransform boundsTransform, final SharedTransitionScope.PlaceHolderSize placeHolderSize, final boolean renderOnlyWhenVisible, final boolean renderInOverlayDuringTransition, final float zIndexInOverlay, final SharedTransitionScope.OverlayClip clipInOverlayDuringTransition) {
        return ComposedModifierKt.composed$default($this$sharedBoundsImpl, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBoundsImpl$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            /* JADX WARN: Removed duplicated region for block: B:21:0x00dc  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x00fd  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x011d  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x0145  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x0159  */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0109  */
            /* JADX WARN: Removed duplicated region for block: B:48:0x00e1  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final androidx.compose.ui.Modifier invoke(androidx.compose.ui.Modifier r21, androidx.compose.runtime.Composer r22, int r23) {
                /*
                    Method dump skipped, instructions count: 678
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.SharedTransitionScopeImpl$sharedBoundsImpl$1.invoke(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):androidx.compose.ui.Modifier");
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElementInternalState rememberSharedElementState(SharedElement sharedElement, BoundsAnimation boundsAnimation, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean renderOnlyWhenVisible, SharedTransitionScope.SharedContentState sharedContentState, SharedTransitionScope.OverlayClip clipInOverlayDuringTransition, float zIndexInOverlay, boolean renderInOverlayDuringTransition, Composer $composer, int $changed) {
        SharedTransitionScope.SharedContentState sharedContentState2;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 2066772852, "C(rememberSharedElementState)P(6!1,2,4,5!1,7)*1032@54440L467:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2066772852, $changed, -1, "androidx.compose.animation.SharedTransitionScopeImpl.rememberSharedElementState (SharedTransitionScope.kt:1032)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -663265575, "CC(remember):SharedTransitionScope.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            sharedContentState2 = sharedContentState;
            value$iv = new SharedElementInternalState(sharedElement, boundsAnimation, placeHolderSize, renderOnlyWhenVisible, clipInOverlayDuringTransition, renderInOverlayDuringTransition, sharedContentState2, zIndexInOverlay);
            $composer.updateRememberedValue(value$iv);
        } else {
            sharedContentState2 = sharedContentState;
            value$iv = it$iv;
        }
        SharedElementInternalState it = (SharedElementInternalState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        sharedContentState2.setInternalState$animation_release(it);
        it.setSharedElement(sharedElement);
        it.setRenderOnlyWhenVisible(renderOnlyWhenVisible);
        it.setBoundsAnimation(boundsAnimation);
        it.setPlaceHolderSize(placeHolderSize);
        it.setOverlayClip(clipInOverlayDuringTransition);
        it.setZIndex(zIndexInOverlay);
        it.setRenderInOverlayDuringTransition(renderInOverlayDuringTransition);
        it.setUserState(sharedContentState2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return it;
    }

    public final LayoutCoordinates getRoot$animation_release() {
        LayoutCoordinates layoutCoordinates = this.root;
        if (layoutCoordinates != null) {
            return layoutCoordinates;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    public final void setRoot$animation_release(LayoutCoordinates layoutCoordinates) {
        this.root = layoutCoordinates;
    }

    public final LayoutCoordinates getLookaheadRoot$animation_release() {
        LayoutCoordinates layoutCoordinates = this.nullableLookaheadRoot;
        if (layoutCoordinates == null) {
            throw new IllegalArgumentException("Error: Uninitialized LayoutCoordinates. Please make sure when using the SharedTransitionScope composable function, the modifier passed to the child content is being used, or use SharedTransitionLayout instead.".toString());
        }
        return layoutCoordinates;
    }

    /* renamed from: getNullableLookaheadRoot$animation_release, reason: from getter */
    public final LayoutCoordinates getNullableLookaheadRoot() {
        return this.nullableLookaheadRoot;
    }

    public final void setNullableLookaheadRoot$animation_release(LayoutCoordinates layoutCoordinates) {
        this.nullableLookaheadRoot = layoutCoordinates;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElement sharedElementsFor(Object key) {
        SharedElement sharedElement = this.sharedElements.get(key);
        if (sharedElement != null) {
            return sharedElement;
        }
        SharedElement it = new SharedElement(key, this);
        this.sharedElements.set(key, it);
        return it;
    }

    public final void drawInOverlay$animation_release(ContentDrawScope scope) {
        List $this$sortBy$iv = this.renderers;
        if ($this$sortBy$iv.size() > 1) {
            CollectionsKt.sortWith($this$sortBy$iv, new Comparator() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$drawInOverlay$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    LayerRenderer it = (LayerRenderer) t;
                    Float valueOf = Float.valueOf((((it.getZIndex() > 0.0f ? 1 : (it.getZIndex() == 0.0f ? 0 : -1)) == 0) && (it instanceof SharedElementInternalState) && ((SharedElementInternalState) it).getParentState() == null) ? -1.0f : it.getZIndex());
                    LayerRenderer it2 = (LayerRenderer) t2;
                    return ComparisonsKt.compareValues(valueOf, Float.valueOf(((it2.getZIndex() == 0.0f) && (it2 instanceof SharedElementInternalState) && ((SharedElementInternalState) it2).getParentState() == null) ? -1.0f : it2.getZIndex()));
                }
            });
        }
        List $this$fastForEach$iv = this.renderers;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            LayerRenderer it = (LayerRenderer) item$iv;
            it.drawInOverlay(scope);
        }
    }

    public final void onStateRemoved$animation_release(SharedElementInternalState sharedElementState) {
        SharedElement $this$onStateRemoved_u24lambda_u2410 = sharedElementState.getSharedElement();
        $this$onStateRemoved_u24lambda_u2410.removeState(sharedElementState);
        this.updateTransitionActiveness.invoke(this);
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads($this$onStateRemoved_u24lambda_u2410.getScope(), this.updateTransitionActiveness, this.observeAnimatingBlock);
        this.renderers.remove(sharedElementState);
        if ($this$onStateRemoved_u24lambda_u2410.getStates().isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default($this$onStateRemoved_u24lambda_u2410.getScope().coroutineScope, null, null, new SharedTransitionScopeImpl$onStateRemoved$1$1($this$onStateRemoved_u24lambda_u2410, null), 3, null);
        }
    }

    public final void onStateAdded$animation_release(SharedElementInternalState sharedElementState) {
        SharedElement $this$onStateAdded_u24lambda_u2412 = sharedElementState.getSharedElement();
        $this$onStateAdded_u24lambda_u2412.addState(sharedElementState);
        this.updateTransitionActiveness.invoke(this);
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads($this$onStateAdded_u24lambda_u2412.getScope(), this.updateTransitionActiveness, this.observeAnimatingBlock);
        List $this$indexOfFirst$iv = this.renderers;
        int index$iv = 0;
        Iterator<LayerRenderer> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                LayerRenderer it2 = (LayerRenderer) item$iv;
                SharedElementInternalState sharedElementInternalState = it2 instanceof SharedElementInternalState ? (SharedElementInternalState) it2 : null;
                if (Intrinsics.areEqual(sharedElementInternalState != null ? sharedElementInternalState.getSharedElement() : null, sharedElementState.getSharedElement())) {
                    break;
                } else {
                    index$iv++;
                }
            } else {
                index$iv = -1;
                break;
            }
        }
        if (index$iv == this.renderers.size() - 1 || index$iv == -1) {
            this.renderers.add(sharedElementState);
        } else {
            this.renderers.add(index$iv + 1, sharedElementState);
        }
    }

    public final void onLayerRendererCreated$animation_release(LayerRenderer renderer) {
        this.renderers.add(renderer);
    }

    public final void onLayerRendererRemoved$animation_release(LayerRenderer renderer) {
        this.renderers.remove(renderer);
    }

    /* compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/animation/SharedTransitionScopeImpl$ShapeBasedClip;", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/graphics/Shape;)V", "getClipShape", "()Landroidx/compose/ui/graphics/Shape;", "path", "Landroidx/compose/ui/graphics/Path;", "getClipPath", "state", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class ShapeBasedClip implements SharedTransitionScope.OverlayClip {
        private final Shape clipShape;
        private final Path path = AndroidPath_androidKt.Path();

        public ShapeBasedClip(Shape clipShape) {
            this.clipShape = clipShape;
        }

        public final Shape getClipShape() {
            return this.clipShape;
        }

        @Override // androidx.compose.animation.SharedTransitionScope.OverlayClip
        public Path getClipPath(SharedTransitionScope.SharedContentState state, Rect bounds, LayoutDirection layoutDirection, Density density) {
            this.path.reset();
            OutlineKt.addOutline(this.path, this.clipShape.mo275createOutlinePq9zytI(bounds.m3955getSizeNHjbRc(), layoutDirection, density));
            this.path.mo4058translatek4lQ0M(bounds.m3957getTopLeftF1C5BW0());
            return this.path;
        }
    }
}
