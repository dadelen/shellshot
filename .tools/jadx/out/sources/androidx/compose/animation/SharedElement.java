package androidx.compose.animation;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedElement.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020#J\b\u0010/\u001a\u00020\u0013H\u0002J\u0006\u00100\u001a\u00020\u0013J(\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u00020#2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206ø\u0001\u0000¢\u0006\u0004\b7\u00108J\u0006\u00109\u001a\u00020\u001eJ\u000e\u0010:\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020#J\u0006\u0010+\u001a\u00020\u001eJ\u0006\u0010;\u001a\u00020\u001eR/\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR/\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0010\u0010\n\"\u0004\b\u0011\u0010\fR+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00138F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010&\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b'\u0010\nR\"\u0010(\u001a\u0004\u0018\u00010#2\b\u0010\u0006\u001a\u0004\u0018\u00010#@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001a\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001e0,X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"Landroidx/compose/animation/SharedElement;", "", "key", "scope", "Landroidx/compose/animation/SharedTransitionScopeImpl;", "(Ljava/lang/Object;Landroidx/compose/animation/SharedTransitionScopeImpl;)V", "<set-?>", "Landroidx/compose/ui/geometry/Rect;", "_targetBounds", "get_targetBounds", "()Landroidx/compose/ui/geometry/Rect;", "set_targetBounds", "(Landroidx/compose/ui/geometry/Rect;)V", "_targetBounds$delegate", "Landroidx/compose/runtime/MutableState;", "currentBounds", "getCurrentBounds", "setCurrentBounds", "currentBounds$delegate", "", "foundMatch", "getFoundMatch", "()Z", "setFoundMatch", "(Z)V", "foundMatch$delegate", "getKey", "()Ljava/lang/Object;", "observingVisibilityChange", "Lkotlin/Function0;", "", "getScope", "()Landroidx/compose/animation/SharedTransitionScopeImpl;", "states", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/animation/SharedElementInternalState;", "getStates", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "targetBounds", "getTargetBounds", "targetBoundsProvider", "getTargetBoundsProvider$animation_release", "()Landroidx/compose/animation/SharedElementInternalState;", "updateMatch", "Lkotlin/Function1;", "addState", "sharedElementState", "hasVisibleContent", "isAnimating", "onLookaheadResult", "state", "lookaheadSize", "Landroidx/compose/ui/geometry/Size;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "onLookaheadResult-v_w8tDc", "(Landroidx/compose/animation/SharedElementInternalState;JJ)V", "onSharedTransitionFinished", "removeState", "updateTargetBoundsProvider", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SharedElement {
    public static final int $stable = 8;
    private final Object key;
    private final SharedTransitionScopeImpl scope;
    private SharedElementInternalState targetBoundsProvider;

    /* renamed from: _targetBounds$delegate, reason: from kotlin metadata */
    private final MutableState _targetBounds = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* renamed from: foundMatch$delegate, reason: from kotlin metadata */
    private final MutableState foundMatch = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* renamed from: currentBounds$delegate, reason: from kotlin metadata */
    private final MutableState currentBounds = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    private final SnapshotStateList<SharedElementInternalState> states = SnapshotStateKt.mutableStateListOf();
    private final Function1<SharedElement, Unit> updateMatch = new Function1<SharedElement, Unit>() { // from class: androidx.compose.animation.SharedElement$updateMatch$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SharedElement sharedElement) {
            invoke2(sharedElement);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SharedElement it) {
            SharedElement.this.updateMatch();
        }
    };
    private final Function0<Unit> observingVisibilityChange = new Function0<Unit>() { // from class: androidx.compose.animation.SharedElement$observingVisibilityChange$1
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
            SharedElement.this.hasVisibleContent();
        }
    };

    public SharedElement(Object key, SharedTransitionScopeImpl scope) {
        this.key = key;
        this.scope = scope;
    }

    public final Object getKey() {
        return this.key;
    }

    public final SharedTransitionScopeImpl getScope() {
        return this.scope;
    }

    public final boolean isAnimating() {
        List $this$fastAny$iv;
        List $this$fastAny$iv2 = this.states;
        int index$iv$iv = 0;
        int size = $this$fastAny$iv2.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = $this$fastAny$iv2.get(index$iv$iv);
                SharedElementInternalState it = (SharedElementInternalState) item$iv$iv;
                if (it.getBoundsAnimation().isRunning()) {
                    $this$fastAny$iv = 1;
                    break;
                }
                index$iv$iv++;
            } else {
                $this$fastAny$iv = null;
                break;
            }
        }
        return $this$fastAny$iv != null && getFoundMatch();
    }

    private final Rect get_targetBounds() {
        State $this$getValue$iv = this._targetBounds;
        return (Rect) $this$getValue$iv.getValue();
    }

    private final void set_targetBounds(Rect rect) {
        MutableState $this$setValue$iv = this._targetBounds;
        $this$setValue$iv.setValue(rect);
    }

    public final Rect getTargetBounds() {
        SharedElementInternalState $this$_get_targetBounds__u24lambda_u241 = this.targetBoundsProvider;
        set_targetBounds($this$_get_targetBounds__u24lambda_u241 != null ? RectKt.m3962Recttz77jQw($this$_get_targetBounds__u24lambda_u241.m99calculateLookaheadOffsetF1C5BW0(), $this$_get_targetBounds__u24lambda_u241.m100getNonNullLookaheadSizeNHjbRc()) : null);
        return get_targetBounds();
    }

    public final void updateMatch() {
        boolean hasVisibleContent = hasVisibleContent();
        if (this.states.size() > 1 && hasVisibleContent) {
            setFoundMatch(true);
        } else if (this.scope.isTransitionActive()) {
            if (!hasVisibleContent) {
                setFoundMatch(false);
            }
        } else {
            setFoundMatch(false);
        }
        if (!this.states.isEmpty()) {
            SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(this, this.updateMatch, this.observingVisibilityChange);
        }
    }

    private final void setFoundMatch(boolean z) {
        MutableState $this$setValue$iv = this.foundMatch;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getFoundMatch() {
        State $this$getValue$iv = this.foundMatch;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final Rect getCurrentBounds() {
        State $this$getValue$iv = this.currentBounds;
        return (Rect) $this$getValue$iv.getValue();
    }

    public final void setCurrentBounds(Rect rect) {
        MutableState $this$setValue$iv = this.currentBounds;
        $this$setValue$iv.setValue(rect);
    }

    /* renamed from: getTargetBoundsProvider$animation_release, reason: from getter */
    public final SharedElementInternalState getTargetBoundsProvider() {
        return this.targetBoundsProvider;
    }

    /* renamed from: onLookaheadResult-v_w8tDc, reason: not valid java name */
    public final void m98onLookaheadResultv_w8tDc(SharedElementInternalState state, long lookaheadSize, long topLeft) {
        if (state.getBoundsAnimation().getTarget()) {
            this.targetBoundsProvider = state;
            Rect rect = get_targetBounds();
            Offset m3911boximpl = rect != null ? Offset.m3911boximpl(rect.m3957getTopLeftF1C5BW0()) : null;
            if (m3911boximpl == null ? false : Offset.m3919equalsimpl0(m3911boximpl.getPackedValue(), topLeft)) {
                Rect rect2 = get_targetBounds();
                Size m3979boximpl = rect2 != null ? Size.m3979boximpl(rect2.m3955getSizeNHjbRc()) : null;
                if (m3979boximpl != null ? Size.m3987equalsimpl0(m3979boximpl.getPackedValue(), lookaheadSize) : false) {
                    return;
                }
            }
            Rect target = RectKt.m3962Recttz77jQw(topLeft, lookaheadSize);
            set_targetBounds(target);
            List $this$fastForEach$iv = this.states;
            int size = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                SharedElementInternalState it = (SharedElementInternalState) item$iv;
                BoundsAnimation boundsAnimation = it.getBoundsAnimation();
                Rect currentBounds = getCurrentBounds();
                Intrinsics.checkNotNull(currentBounds);
                boundsAnimation.animate(currentBounds, target);
            }
        }
    }

    public final SnapshotStateList<SharedElementInternalState> getStates() {
        return this.states;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasVisibleContent() {
        List $this$fastAny$iv = this.states;
        int size = $this$fastAny$iv.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = $this$fastAny$iv.get(index$iv$iv);
            SharedElementInternalState it = (SharedElementInternalState) item$iv$iv;
            if (it.getBoundsAnimation().getTarget()) {
                return true;
            }
        }
        return false;
    }

    public final void updateTargetBoundsProvider() {
        SharedElementInternalState sharedElementInternalState = null;
        List $this$fastForEachReversed$iv = this.states;
        int size = $this$fastForEachReversed$iv.size() - 1;
        if (size >= 0) {
            do {
                int index$iv = size;
                size--;
                Object item$iv = $this$fastForEachReversed$iv.get(index$iv);
                SharedElementInternalState it = (SharedElementInternalState) item$iv;
                if (it.getBoundsAnimation().getTarget()) {
                    sharedElementInternalState = it;
                }
            } while (size >= 0);
        }
        if (Intrinsics.areEqual(sharedElementInternalState, this.targetBoundsProvider)) {
            return;
        }
        this.targetBoundsProvider = sharedElementInternalState;
        set_targetBounds(null);
    }

    public final void onSharedTransitionFinished() {
        setFoundMatch(this.states.size() > 1 && hasVisibleContent());
        set_targetBounds(null);
    }

    public final void addState(SharedElementInternalState sharedElementState) {
        this.states.add(sharedElementState);
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(this, this.updateMatch, this.observingVisibilityChange);
    }

    public final void removeState(SharedElementInternalState sharedElementState) {
        this.states.remove(sharedElementState);
        if (this.states.isEmpty()) {
            updateMatch();
            SharedTransitionScopeKt.getSharedTransitionObserver().clear(this);
        } else {
            SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(this, this.updateMatch, this.observingVisibilityChange);
        }
    }
}
