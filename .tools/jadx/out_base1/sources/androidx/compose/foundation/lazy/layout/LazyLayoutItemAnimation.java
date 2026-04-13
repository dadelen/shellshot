package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyLayoutItemAnimation.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 L2\u00020\u0001:\u0001LB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010C\u001a\u00020\bJ\u0006\u0010D\u001a\u00020\bJ \u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\u00152\u0006\u0010G\u001a\u00020\u001cø\u0001\u0000¢\u0006\u0004\bH\u0010IJ\u0006\u0010J\u001a\u00020\bJ\u0006\u0010K\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\"\u0010\u0014\u001a\u00020\u0015X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R+\u0010#\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\"\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R+\u0010&\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\"\u001a\u0004\b&\u0010\u001e\"\u0004\b'\u0010 R+\u0010)\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\"\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010 R\u001e\u0010,\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\"\u0010.\u001a\u0004\u0018\u00010-2\b\u0010\u001b\u001a\u0004\u0018\u00010-@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\"\u00101\u001a\u00020\u0015X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R1\u00104\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00158F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b7\u0010\"\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R\u001a\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020:09X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u000e\"\u0004\b=\u0010\u0010R\"\u0010>\u001a\u00020\u0015X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b?\u0010\u0017\"\u0004\b@\u0010\u0019R\u001a\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020B09X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006M"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "onLayerPropertyChanged", "Lkotlin/Function0;", "", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function0;)V", "fadeInSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "getFadeInSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setFadeInSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "fadeOutSpec", "getFadeOutSpec", "setFadeOutSpec", "finalOffset", "Landroidx/compose/ui/unit/IntOffset;", "getFinalOffset-nOcc-ac", "()J", "setFinalOffset--gyyYBs", "(J)V", "J", "<set-?>", "", "isAppearanceAnimationInProgress", "()Z", "setAppearanceAnimationInProgress", "(Z)V", "isAppearanceAnimationInProgress$delegate", "Landroidx/compose/runtime/MutableState;", "isDisappearanceAnimationFinished", "setDisappearanceAnimationFinished", "isDisappearanceAnimationFinished$delegate", "isDisappearanceAnimationInProgress", "setDisappearanceAnimationInProgress", "isDisappearanceAnimationInProgress$delegate", "isPlacementAnimationInProgress", "setPlacementAnimationInProgress", "isPlacementAnimationInProgress$delegate", "isRunningMovingAwayAnimation", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layer", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "lookaheadOffset", "getLookaheadOffset-nOcc-ac", "setLookaheadOffset--gyyYBs", "placementDelta", "getPlacementDelta-nOcc-ac", "setPlacementDelta--gyyYBs", "placementDelta$delegate", "placementDeltaAnimation", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector2D;", "placementSpec", "getPlacementSpec", "setPlacementSpec", "rawOffset", "getRawOffset-nOcc-ac", "setRawOffset--gyyYBs", "visibilityAnimation", "Landroidx/compose/animation/core/AnimationVector1D;", "animateAppearance", "animateDisappearance", "animatePlacementDelta", "delta", "isMovingAway", "animatePlacementDelta-ar5cAso", "(JZ)V", "cancelPlacementAnimation", "release", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutItemAnimation {
    private final CoroutineScope coroutineScope;
    private FiniteAnimationSpec<Float> fadeInSpec;
    private FiniteAnimationSpec<Float> fadeOutSpec;
    private long finalOffset;
    private final GraphicsContext graphicsContext;

    /* renamed from: isAppearanceAnimationInProgress$delegate, reason: from kotlin metadata */
    private final MutableState isAppearanceAnimationInProgress;

    /* renamed from: isDisappearanceAnimationFinished$delegate, reason: from kotlin metadata */
    private final MutableState isDisappearanceAnimationFinished;

    /* renamed from: isDisappearanceAnimationInProgress$delegate, reason: from kotlin metadata */
    private final MutableState isDisappearanceAnimationInProgress;

    /* renamed from: isPlacementAnimationInProgress$delegate, reason: from kotlin metadata */
    private final MutableState isPlacementAnimationInProgress;
    private boolean isRunningMovingAwayAnimation;
    private GraphicsLayer layer;
    private long lookaheadOffset;
    private final Function0<Unit> onLayerPropertyChanged;

    /* renamed from: placementDelta$delegate, reason: from kotlin metadata */
    private final MutableState placementDelta;
    private final Animatable<IntOffset, AnimationVector2D> placementDeltaAnimation;
    private FiniteAnimationSpec<IntOffset> placementSpec;
    private long rawOffset;
    private final Animatable<Float, AnimationVector1D> visibilityAnimation;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final long NotInitialized = IntOffsetKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public LazyLayoutItemAnimation(CoroutineScope coroutineScope, GraphicsContext graphicsContext, Function0<Unit> function0) {
        this.coroutineScope = coroutineScope;
        this.graphicsContext = graphicsContext;
        this.onLayerPropertyChanged = function0;
        this.isPlacementAnimationInProgress = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.isAppearanceAnimationInProgress = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.isDisappearanceAnimationInProgress = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.isDisappearanceAnimationFinished = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.rawOffset = NotInitialized;
        this.finalOffset = IntOffset.INSTANCE.m6764getZeronOccac();
        GraphicsContext graphicsContext2 = this.graphicsContext;
        this.layer = graphicsContext2 != null ? graphicsContext2.createGraphicsLayer() : null;
        String str = null;
        this.placementDeltaAnimation = new Animatable<>(IntOffset.m6745boximpl(IntOffset.INSTANCE.m6764getZeronOccac()), VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), null, str, 12, null);
        this.visibilityAnimation = new Animatable<>(Float.valueOf(1.0f), VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), str, null, 12, null);
        this.placementDelta = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m6745boximpl(IntOffset.INSTANCE.m6764getZeronOccac()), null, 2, null);
        this.lookaheadOffset = NotInitialized;
    }

    public /* synthetic */ LazyLayoutItemAnimation(CoroutineScope coroutineScope, GraphicsContext graphicsContext, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineScope, (i & 2) != 0 ? null : graphicsContext, (i & 4) != 0 ? new Function0<Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        } : anonymousClass1);
    }

    public final FiniteAnimationSpec<Float> getFadeInSpec() {
        return this.fadeInSpec;
    }

    public final void setFadeInSpec(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.fadeInSpec = finiteAnimationSpec;
    }

    public final FiniteAnimationSpec<IntOffset> getPlacementSpec() {
        return this.placementSpec;
    }

    public final void setPlacementSpec(FiniteAnimationSpec<IntOffset> finiteAnimationSpec) {
        this.placementSpec = finiteAnimationSpec;
    }

    public final FiniteAnimationSpec<Float> getFadeOutSpec() {
        return this.fadeOutSpec;
    }

    public final void setFadeOutSpec(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.fadeOutSpec = finiteAnimationSpec;
    }

    /* renamed from: isRunningMovingAwayAnimation, reason: from getter */
    public final boolean getIsRunningMovingAwayAnimation() {
        return this.isRunningMovingAwayAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlacementAnimationInProgress(boolean z) {
        MutableState $this$setValue$iv = this.isPlacementAnimationInProgress;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isPlacementAnimationInProgress() {
        State $this$getValue$iv = this.isPlacementAnimationInProgress;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAppearanceAnimationInProgress(boolean z) {
        MutableState $this$setValue$iv = this.isAppearanceAnimationInProgress;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isAppearanceAnimationInProgress() {
        State $this$getValue$iv = this.isAppearanceAnimationInProgress;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDisappearanceAnimationInProgress(boolean z) {
        MutableState $this$setValue$iv = this.isDisappearanceAnimationInProgress;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isDisappearanceAnimationInProgress() {
        State $this$getValue$iv = this.isDisappearanceAnimationInProgress;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDisappearanceAnimationFinished(boolean z) {
        MutableState $this$setValue$iv = this.isDisappearanceAnimationFinished;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isDisappearanceAnimationFinished() {
        State $this$getValue$iv = this.isDisappearanceAnimationFinished;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* renamed from: getRawOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getRawOffset() {
        return this.rawOffset;
    }

    /* renamed from: setRawOffset--gyyYBs, reason: not valid java name */
    public final void m835setRawOffsetgyyYBs(long j) {
        this.rawOffset = j;
    }

    /* renamed from: getFinalOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getFinalOffset() {
        return this.finalOffset;
    }

    /* renamed from: setFinalOffset--gyyYBs, reason: not valid java name */
    public final void m833setFinalOffsetgyyYBs(long j) {
        this.finalOffset = j;
    }

    public final GraphicsLayer getLayer() {
        return this.layer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setPlacementDelta--gyyYBs, reason: not valid java name */
    public final void m827setPlacementDeltagyyYBs(long j) {
        MutableState $this$setValue$iv = this.placementDelta;
        $this$setValue$iv.setValue(IntOffset.m6745boximpl(j));
    }

    /* renamed from: getPlacementDelta-nOcc-ac, reason: not valid java name */
    public final long m831getPlacementDeltanOccac() {
        State $this$getValue$iv = this.placementDelta;
        return ((IntOffset) $this$getValue$iv.getValue()).getPackedValue();
    }

    public final void cancelPlacementAnimation() {
        if (isPlacementAnimationInProgress()) {
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$cancelPlacementAnimation$1(this, null), 3, null);
        }
    }

    /* renamed from: getLookaheadOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getLookaheadOffset() {
        return this.lookaheadOffset;
    }

    /* renamed from: setLookaheadOffset--gyyYBs, reason: not valid java name */
    public final void m834setLookaheadOffsetgyyYBs(long j) {
        this.lookaheadOffset = j;
    }

    /* renamed from: animatePlacementDelta-ar5cAso, reason: not valid java name */
    public final void m828animatePlacementDeltaar5cAso(long delta, boolean isMovingAway) {
        FiniteAnimationSpec spec = this.placementSpec;
        if (spec == null) {
            return;
        }
        long totalDelta = IntOffset.m6757minusqkQi6aY(m831getPlacementDeltanOccac(), delta);
        m827setPlacementDeltagyyYBs(totalDelta);
        setPlacementAnimationInProgress(true);
        this.isRunningMovingAwayAnimation = isMovingAway;
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$animatePlacementDelta$1(this, spec, totalDelta, null), 3, null);
    }

    public final void animateAppearance() {
        GraphicsLayer layer = this.layer;
        FiniteAnimationSpec spec = this.fadeInSpec;
        if (isAppearanceAnimationInProgress() || spec == null || layer == null) {
            if (isDisappearanceAnimationInProgress()) {
                if (layer != null) {
                    layer.setAlpha(1.0f);
                }
                BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$animateAppearance$1(this, null), 3, null);
                return;
            }
            return;
        }
        setAppearanceAnimationInProgress(true);
        boolean shouldResetValue = !isDisappearanceAnimationInProgress();
        if (shouldResetValue) {
            layer.setAlpha(0.0f);
        }
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$animateAppearance$2(shouldResetValue, this, spec, layer, null), 3, null);
    }

    public final void animateDisappearance() {
        GraphicsLayer layer = this.layer;
        FiniteAnimationSpec spec = this.fadeOutSpec;
        if (layer == null || isDisappearanceAnimationInProgress() || spec == null) {
            return;
        }
        setDisappearanceAnimationInProgress(true);
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$animateDisappearance$1(this, spec, layer, null), 3, null);
    }

    public final void release() {
        GraphicsContext graphicsContext;
        if (isPlacementAnimationInProgress()) {
            setPlacementAnimationInProgress(false);
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$release$1(this, null), 3, null);
        }
        if (isAppearanceAnimationInProgress()) {
            setAppearanceAnimationInProgress(false);
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$release$2(this, null), 3, null);
        }
        if (isDisappearanceAnimationInProgress()) {
            setDisappearanceAnimationInProgress(false);
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$release$3(this, null), 3, null);
        }
        this.isRunningMovingAwayAnimation = false;
        m827setPlacementDeltagyyYBs(IntOffset.INSTANCE.m6764getZeronOccac());
        this.rawOffset = NotInitialized;
        GraphicsLayer it = this.layer;
        if (it != null && (graphicsContext = this.graphicsContext) != null) {
            graphicsContext.releaseGraphicsLayer(it);
        }
        this.layer = null;
        this.fadeInSpec = null;
        this.fadeOutSpec = null;
        this.placementSpec = null;
    }

    /* compiled from: LazyLayoutItemAnimation.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation$Companion;", "", "()V", "NotInitialized", "Landroidx/compose/ui/unit/IntOffset;", "getNotInitialized-nOcc-ac", "()J", "J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: getNotInitialized-nOcc-ac, reason: not valid java name */
        public final long m836getNotInitializednOccac() {
            return LazyLayoutItemAnimation.NotInitialized;
        }
    }
}
