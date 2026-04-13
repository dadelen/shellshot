package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Density;
import androidx.core.app.NotificationCompat;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: BackdropScaffold.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u0000 02\u00020\u0001:\u00010B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010'\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0018\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0003H\u0007J\b\u0010-\u001a\u00020\u0017H\u0002J\u0006\u0010.\u001a\u00020\u0006J\u000e\u0010/\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020 X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b&\u0010\u0015¨\u00061"}, d2 = {"Landroidx/compose/material/BackdropScaffoldState;", "", "initialValue", "Landroidx/compose/material/BackdropValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "", "snackbarHostState", "Landroidx/compose/material/SnackbarHostState;", "(Landroidx/compose/material/BackdropValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/material/SnackbarHostState;)V", "anchoredDraggableState", "Landroidx/compose/material/AnchoredDraggableState;", "getAnchoredDraggableState$material_release", "()Landroidx/compose/material/AnchoredDraggableState;", "getConfirmValueChange", "()Lkotlin/jvm/functions/Function1;", "currentValue", "getCurrentValue", "()Landroidx/compose/material/BackdropValue;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$material_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$material_release", "(Landroidx/compose/ui/unit/Density;)V", "isConcealed", "()Z", "isRevealed", "nestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection$material_release", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getSnackbarHostState", "()Landroidx/compose/material/SnackbarHostState;", "targetValue", "getTargetValue", "conceal", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", NotificationCompat.CATEGORY_PROGRESS, "from", "to", "requireDensity", "requireOffset", "reveal", "Companion", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BackdropScaffoldState {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDraggableState<BackdropValue> anchoredDraggableState;
    private final Function1<BackdropValue, Boolean> confirmValueChange;
    private Density density;
    private final NestedScrollConnection nestedScrollConnection;
    private final SnackbarHostState snackbarHostState;

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(message = "This constructor is deprecated. Density must be provided by the component. Please use the constructor that provides a [Density].", replaceWith = @ReplaceWith(expression = "\n            BackdropScaffoldState(\n                initialValue = initialValue,\n                density = LocalDensity.current,\n                animationSpec = animationSpec,\n                confirmValueChange = confirmValueChange\n            )\n            ", imports = {}))
    public BackdropScaffoldState(BackdropValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super BackdropValue, Boolean> function1, SnackbarHostState snackbarHostState) {
        this.confirmValueChange = function1;
        this.snackbarHostState = snackbarHostState;
        this.anchoredDraggableState = new AnchoredDraggableState<>(initialValue, new Function1<Float, Float>() { // from class: androidx.compose.material.BackdropScaffoldState$anchoredDraggableState$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return invoke(f.floatValue());
            }

            public final Float invoke(float it) {
                Density $this$invoke_u24lambda_u240;
                float f;
                $this$invoke_u24lambda_u240 = BackdropScaffoldState.this.requireDensity();
                f = BackdropScaffoldKt.PositionalThreshold;
                return Float.valueOf($this$invoke_u24lambda_u240.mo364toPx0680j_4(f));
            }
        }, new Function0<Float>() { // from class: androidx.compose.material.BackdropScaffoldState$anchoredDraggableState$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Density $this$invoke_u24lambda_u240;
                float f;
                $this$invoke_u24lambda_u240 = BackdropScaffoldState.this.requireDensity();
                f = BackdropScaffoldKt.VelocityThreshold;
                return Float.valueOf($this$invoke_u24lambda_u240.mo364toPx0680j_4(f));
            }
        }, animationSpec, this.confirmValueChange);
        this.nestedScrollConnection = BackdropScaffoldKt.ConsumeSwipeNestedScrollConnection(this.anchoredDraggableState, Orientation.Vertical);
    }

    public /* synthetic */ BackdropScaffoldState(BackdropValue backdropValue, AnimationSpec animationSpec, AnonymousClass1 anonymousClass1, SnackbarHostState snackbarHostState, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(backdropValue, (i & 2) != 0 ? BackdropScaffoldDefaults.INSTANCE.getAnimationSpec() : animationSpec, (i & 4) != 0 ? new Function1<BackdropValue, Boolean>() { // from class: androidx.compose.material.BackdropScaffoldState.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackdropValue it) {
                return true;
            }
        } : anonymousClass1, (i & 8) != 0 ? new SnackbarHostState() : snackbarHostState);
    }

    public final Function1<BackdropValue, Boolean> getConfirmValueChange() {
        return this.confirmValueChange;
    }

    public final SnackbarHostState getSnackbarHostState() {
        return this.snackbarHostState;
    }

    public final BackdropValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    public final BackdropValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    public final float requireOffset() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final boolean isRevealed() {
        return this.anchoredDraggableState.getCurrentValue() == BackdropValue.Revealed;
    }

    public final boolean isConcealed() {
        return this.anchoredDraggableState.getCurrentValue() == BackdropValue.Concealed;
    }

    public final Object reveal(Continuation<? super Unit> continuation) {
        Object animateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, BackdropValue.Revealed, 0.0f, continuation, 2, null);
        return animateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animateTo$default : Unit.INSTANCE;
    }

    public final Object conceal(Continuation<? super Unit> continuation) {
        Object animateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, BackdropValue.Concealed, 0.0f, continuation, 2, null);
        return animateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animateTo$default : Unit.INSTANCE;
    }

    public final float progress(BackdropValue from, BackdropValue to) {
        float fromOffset = this.anchoredDraggableState.getAnchors().positionOf(from);
        float toOffset = this.anchoredDraggableState.getAnchors().positionOf(to);
        float currentOffset = RangesKt.coerceIn(this.anchoredDraggableState.getOffset(), Math.min(fromOffset, toOffset), Math.max(fromOffset, toOffset));
        float fraction = (currentOffset - fromOffset) / (toOffset - fromOffset);
        if (Float.isNaN(fraction)) {
            return 1.0f;
        }
        return Math.abs(fraction);
    }

    public final AnchoredDraggableState<BackdropValue> getAnchoredDraggableState$material_release() {
        return this.anchoredDraggableState;
    }

    /* renamed from: getDensity$material_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity$material_release(Density density) {
        this.density = density;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Density requireDensity() {
        Density density = this.density;
        if (density == null) {
            throw new IllegalArgumentException(("The density on BackdropScaffoldState (" + this + ") was not set. Did you use BackdropScaffoldState with the BackdropScaffold composable?").toString());
        }
        return density;
    }

    /* renamed from: getNestedScrollConnection$material_release, reason: from getter */
    public final NestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    /* compiled from: BackdropScaffold.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/material/BackdropScaffoldState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/BackdropScaffoldState;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "Landroidx/compose/material/BackdropValue;", "", "snackbarHostState", "Landroidx/compose/material/SnackbarHostState;", "density", "Landroidx/compose/ui/unit/Density;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<BackdropScaffoldState, ?> Saver(final AnimationSpec<Float> animationSpec, final Function1<? super BackdropValue, Boolean> confirmStateChange, final SnackbarHostState snackbarHostState, final Density density) {
            return SaverKt.Saver(new Function2<SaverScope, BackdropScaffoldState, BackdropValue>() { // from class: androidx.compose.material.BackdropScaffoldState$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final BackdropValue invoke(SaverScope $this$Saver, BackdropScaffoldState it) {
                    return it.getAnchoredDraggableState$material_release().getCurrentValue();
                }
            }, new Function1<BackdropValue, BackdropScaffoldState>() { // from class: androidx.compose.material.BackdropScaffoldState$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final BackdropScaffoldState invoke(BackdropValue it) {
                    return BackdropScaffoldKt.BackdropScaffoldState(it, Density.this, animationSpec, confirmStateChange, snackbarHostState);
                }
            });
        }
    }
}
