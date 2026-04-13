package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
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

/* compiled from: Drawer.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 =2\u00020\u0001:\u0001=B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\"\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u000bH\u0080@¢\u0006\u0004\b.\u0010/J\u000e\u00100\u001a\u00020+H\u0086@¢\u0006\u0002\u00101J\u0015\u0010\u0006\u001a\u00020\b2\u0006\u00102\u001a\u00020\u0003H\u0000¢\u0006\u0002\b3J\u000e\u00104\u001a\u00020+H\u0086@¢\u0006\u0002\u00101J\u000e\u00105\u001a\u00020+H\u0086@¢\u0006\u0002\u00101J\u0018\u0010$\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u0003H\u0007J\r\u00108\u001a\u00020\u000bH\u0000¢\u0006\u0002\b9J\u0018\u0010:\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0003H\u0080@¢\u0006\u0004\b;\u0010<R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u001b\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u001eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u000b8GX\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010&\u001a\u0004\b'\u0010#R\u0011\u0010(\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b)\u0010\u0013¨\u0006>"}, d2 = {"Landroidx/compose/material/BottomDrawerState;", "", "initialValue", "Landroidx/compose/material/BottomDrawerValue;", "density", "Landroidx/compose/ui/unit/Density;", "confirmStateChange", "Lkotlin/Function1;", "", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "(Landroidx/compose/material/BottomDrawerValue;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/functions/Function1;Landroidx/compose/animation/core/AnimationSpec;)V", "anchoredDraggableState", "Landroidx/compose/material/AnchoredDraggableState;", "getAnchoredDraggableState$material_release", "()Landroidx/compose/material/AnchoredDraggableState;", "currentValue", "getCurrentValue", "()Landroidx/compose/material/BottomDrawerValue;", "getDensity$material_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$material_release", "(Landroidx/compose/ui/unit/Density;)V", "isClosed", "()Z", "isExpanded", "isOpen", "isOpenEnabled", "nestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection$material_release", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "offset", "getOffset", "()F", NotificationCompat.CATEGORY_PROGRESS, "getProgress$annotations", "()V", "getProgress", "targetValue", "getTargetValue", "animateTo", "", "target", "velocity", "animateTo$material_release", "(Landroidx/compose/material/BottomDrawerValue;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "value", "confirmStateChange$material_release", "expand", "open", "from", "to", "requireOffset", "requireOffset$material_release", "snapTo", "snapTo$material_release", "(Landroidx/compose/material/BottomDrawerValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BottomDrawerState {
    private final AnchoredDraggableState<BottomDrawerValue> anchoredDraggableState;
    private Density density;
    private final NestedScrollConnection nestedScrollConnection;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Deprecated(message = "Please use the progress function to query progress explicitly between targets.", replaceWith = @ReplaceWith(expression = "progress(from = , to = )", imports = {}))
    public static /* synthetic */ void getProgress$annotations() {
    }

    public BottomDrawerState(BottomDrawerValue initialValue, final Density density, Function1<? super BottomDrawerValue, Boolean> function1, AnimationSpec<Float> animationSpec) {
        NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection;
        this.anchoredDraggableState = new AnchoredDraggableState<>(initialValue, new Function1<Float, Float>() { // from class: androidx.compose.material.BottomDrawerState$anchoredDraggableState$1
            {
                super(1);
            }

            public final Float invoke(float it) {
                float f;
                Density $this$invoke_u24lambda_u240 = Density.this;
                f = DrawerKt.DrawerPositionalThreshold;
                return Float.valueOf($this$invoke_u24lambda_u240.mo364toPx0680j_4(f));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return invoke(f.floatValue());
            }
        }, new Function0<Float>() { // from class: androidx.compose.material.BottomDrawerState$anchoredDraggableState$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                float f;
                Density $this$invoke_u24lambda_u240 = Density.this;
                f = DrawerKt.DrawerVelocityThreshold;
                return Float.valueOf($this$invoke_u24lambda_u240.mo364toPx0680j_4(f));
            }
        }, animationSpec, function1);
        ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection = DrawerKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(this.anchoredDraggableState);
        this.nestedScrollConnection = ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection;
    }

    public /* synthetic */ BottomDrawerState(BottomDrawerValue bottomDrawerValue, Density density, AnonymousClass1 anonymousClass1, TweenSpec<Float> tweenSpec, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bottomDrawerValue, density, (i & 4) != 0 ? new Function1<BottomDrawerValue, Boolean>() { // from class: androidx.compose.material.BottomDrawerState.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BottomDrawerValue it) {
                return true;
            }
        } : anonymousClass1, (i & 8) != 0 ? DrawerDefaults.INSTANCE.getAnimationSpec() : tweenSpec);
    }

    public final AnchoredDraggableState<BottomDrawerValue> getAnchoredDraggableState$material_release() {
        return this.anchoredDraggableState;
    }

    public final BottomDrawerValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    public final float getOffset() {
        return this.anchoredDraggableState.getOffset();
    }

    public final float requireOffset$material_release() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final BottomDrawerValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    public final boolean isOpen() {
        return this.anchoredDraggableState.getCurrentValue() != BottomDrawerValue.Closed;
    }

    public final boolean isClosed() {
        return this.anchoredDraggableState.getCurrentValue() == BottomDrawerValue.Closed;
    }

    public final boolean isExpanded() {
        return this.anchoredDraggableState.getCurrentValue() == BottomDrawerValue.Expanded;
    }

    public final float getProgress() {
        return this.anchoredDraggableState.getProgress();
    }

    public final float progress(BottomDrawerValue from, BottomDrawerValue to) {
        float fromOffset = this.anchoredDraggableState.getAnchors().positionOf(from);
        float toOffset = this.anchoredDraggableState.getAnchors().positionOf(to);
        float currentOffset = RangesKt.coerceIn(this.anchoredDraggableState.getOffset(), Math.min(fromOffset, toOffset), Math.max(fromOffset, toOffset));
        float fraction = (currentOffset - fromOffset) / (toOffset - fromOffset);
        if (Float.isNaN(fraction)) {
            return 1.0f;
        }
        return Math.abs(fraction);
    }

    public final Object open(Continuation<? super Unit> continuation) {
        BottomDrawerValue targetValue = isOpenEnabled() ? BottomDrawerValue.Open : BottomDrawerValue.Expanded;
        Object animateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, targetValue, 0.0f, continuation, 2, null);
        return animateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animateTo$default : Unit.INSTANCE;
    }

    public final Object close(Continuation<? super Unit> continuation) {
        Object animateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, BottomDrawerValue.Closed, 0.0f, continuation, 2, null);
        return animateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animateTo$default : Unit.INSTANCE;
    }

    public final Object expand(Continuation<? super Unit> continuation) {
        Object animateTo$default = AnchoredDraggableKt.animateTo$default(this.anchoredDraggableState, BottomDrawerValue.Expanded, 0.0f, continuation, 2, null);
        return animateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animateTo$default : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$material_release$default(BottomDrawerState bottomDrawerState, BottomDrawerValue bottomDrawerValue, float f, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            f = bottomDrawerState.anchoredDraggableState.getLastVelocity();
        }
        return bottomDrawerState.animateTo$material_release(bottomDrawerValue, f, continuation);
    }

    public final Object animateTo$material_release(BottomDrawerValue target, float velocity, Continuation<? super Unit> continuation) {
        Object animateTo = AnchoredDraggableKt.animateTo(this.anchoredDraggableState, target, velocity, continuation);
        return animateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? animateTo : Unit.INSTANCE;
    }

    public final Object snapTo$material_release(BottomDrawerValue target, Continuation<? super Unit> continuation) {
        Object snapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, target, continuation);
        return snapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? snapTo : Unit.INSTANCE;
    }

    public final boolean confirmStateChange$material_release(BottomDrawerValue value) {
        return this.anchoredDraggableState.getConfirmValueChange$material_release().invoke(value).booleanValue();
    }

    private final boolean isOpenEnabled() {
        return this.anchoredDraggableState.getAnchors().hasAnchorFor(BottomDrawerValue.Open);
    }

    /* renamed from: getNestedScrollConnection$material_release, reason: from getter */
    public final NestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    /* renamed from: getDensity$material_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity$material_release(Density density) {
        this.density = density;
    }

    /* compiled from: Drawer.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/BottomDrawerState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/BottomDrawerState;", "Landroidx/compose/material/BottomDrawerValue;", "density", "Landroidx/compose/ui/unit/Density;", "confirmStateChange", "Lkotlin/Function1;", "", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<BottomDrawerState, BottomDrawerValue> Saver(final Density density, final Function1<? super BottomDrawerValue, Boolean> confirmStateChange, final AnimationSpec<Float> animationSpec) {
            return SaverKt.Saver(new Function2<SaverScope, BottomDrawerState, BottomDrawerValue>() { // from class: androidx.compose.material.BottomDrawerState$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final BottomDrawerValue invoke(SaverScope $this$Saver, BottomDrawerState it) {
                    return it.getAnchoredDraggableState$material_release().getCurrentValue();
                }
            }, new Function1<BottomDrawerValue, BottomDrawerState>() { // from class: androidx.compose.material.BottomDrawerState$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final BottomDrawerState invoke(BottomDrawerValue it) {
                    return new BottomDrawerState(it, Density.this, confirmStateChange, animationSpec);
                }
            });
        }
    }
}
