package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FloatingActionButton.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0002\u0010\u0012J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014J\u000e\u0010\u0015\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u0016J3\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001a\u001a\u00020\u0003*\u0004\u0018\u00010\rH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\u00020\u0003X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u0003X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u0003X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\u00020\u0003X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d"}, d2 = {"Landroidx/compose/material/FloatingActionButtonElevationAnimatable;", "", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "hoveredElevation", "focusedElevation", "(FFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "animatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "F", "lastTargetInteraction", "Landroidx/compose/foundation/interaction/Interaction;", "targetInteraction", "animateElevation", "", "to", "(Landroidx/compose/foundation/interaction/Interaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asState", "Landroidx/compose/runtime/State;", "snapElevation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateElevation", "updateElevation-lDy3nrA", "(FFFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateTarget", "calculateTarget-u2uoSUM", "(Landroidx/compose/foundation/interaction/Interaction;)F", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class FloatingActionButtonElevationAnimatable {
    private final Animatable<Dp, AnimationVector1D> animatable;
    private float defaultElevation;
    private float focusedElevation;
    private float hoveredElevation;
    private Interaction lastTargetInteraction;
    private float pressedElevation;
    private Interaction targetInteraction;

    public /* synthetic */ FloatingActionButtonElevationAnimatable(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }

    private FloatingActionButtonElevationAnimatable(float defaultElevation, float pressedElevation, float hoveredElevation, float focusedElevation) {
        this.defaultElevation = defaultElevation;
        this.pressedElevation = pressedElevation;
        this.hoveredElevation = hoveredElevation;
        this.focusedElevation = focusedElevation;
        this.animatable = new Animatable<>(Dp.m6624boximpl(this.defaultElevation), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, null, 12, null);
    }

    /* renamed from: calculateTarget-u2uoSUM, reason: not valid java name */
    private final float m1565calculateTargetu2uoSUM(Interaction $this$calculateTarget_u2du2uoSUM) {
        return $this$calculateTarget_u2du2uoSUM instanceof PressInteraction.Press ? this.pressedElevation : $this$calculateTarget_u2du2uoSUM instanceof HoverInteraction.Enter ? this.hoveredElevation : $this$calculateTarget_u2du2uoSUM instanceof FocusInteraction.Focus ? this.focusedElevation : this.defaultElevation;
    }

    /* renamed from: updateElevation-lDy3nrA, reason: not valid java name */
    public final Object m1566updateElevationlDy3nrA(float defaultElevation, float pressedElevation, float hoveredElevation, float focusedElevation, Continuation<? super Unit> continuation) {
        this.defaultElevation = defaultElevation;
        this.pressedElevation = pressedElevation;
        this.hoveredElevation = hoveredElevation;
        this.focusedElevation = focusedElevation;
        Object snapElevation = snapElevation(continuation);
        return snapElevation == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? snapElevation : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object snapElevation(kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.compose.material.FloatingActionButtonElevationAnimatable$snapElevation$1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.material.FloatingActionButtonElevationAnimatable$snapElevation$1 r0 = (androidx.compose.material.FloatingActionButtonElevationAnimatable$snapElevation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            androidx.compose.material.FloatingActionButtonElevationAnimatable$snapElevation$1 r0 = new androidx.compose.material.FloatingActionButtonElevationAnimatable$snapElevation$1
            r0.<init>(r8, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L37;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L2d:
            java.lang.Object r1 = r0.L$0
            androidx.compose.material.FloatingActionButtonElevationAnimatable r1 = (androidx.compose.material.FloatingActionButtonElevationAnimatable) r1
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L35
            goto L67
        L35:
            r2 = move-exception
            goto L70
        L37:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r8
            androidx.compose.foundation.interaction.Interaction r3 = r2.targetInteraction
            float r3 = r2.m1565calculateTargetu2uoSUM(r3)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r4 = r2.animatable
            java.lang.Object r4 = r4.getTargetValue()
            androidx.compose.ui.unit.Dp r4 = (androidx.compose.ui.unit.Dp) r4
            float r4 = r4.m6640unboximpl()
            boolean r4 = androidx.compose.ui.unit.Dp.m6631equalsimpl0(r4, r3)
            if (r4 != 0) goto L75
        L54:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r4 = r2.animatable     // Catch: java.lang.Throwable -> L6c
            androidx.compose.ui.unit.Dp r5 = androidx.compose.ui.unit.Dp.m6624boximpl(r3)     // Catch: java.lang.Throwable -> L6c
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L6c
            r6 = 1
            r0.label = r6     // Catch: java.lang.Throwable -> L6c
            java.lang.Object r4 = r4.snapTo(r5, r0)     // Catch: java.lang.Throwable -> L6c
            if (r4 != r1) goto L66
            return r1
        L66:
            r1 = r2
        L67:
            androidx.compose.foundation.interaction.Interaction r2 = r1.targetInteraction
            r1.lastTargetInteraction = r2
            goto L75
        L6c:
            r1 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L70:
            androidx.compose.foundation.interaction.Interaction r3 = r1.targetInteraction
            r1.lastTargetInteraction = r3
            throw r2
        L75:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.FloatingActionButtonElevationAnimatable.snapElevation(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateElevation(androidx.compose.foundation.interaction.Interaction r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.compose.material.FloatingActionButtonElevationAnimatable$animateElevation$1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.material.FloatingActionButtonElevationAnimatable$animateElevation$1 r0 = (androidx.compose.material.FloatingActionButtonElevationAnimatable$animateElevation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            androidx.compose.material.FloatingActionButtonElevationAnimatable$animateElevation$1 r0 = new androidx.compose.material.FloatingActionButtonElevationAnimatable$animateElevation$1
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3b;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            java.lang.Object r9 = r0.L$1
            androidx.compose.foundation.interaction.Interaction r9 = (androidx.compose.foundation.interaction.Interaction) r9
            java.lang.Object r1 = r0.L$0
            androidx.compose.material.FloatingActionButtonElevationAnimatable r1 = (androidx.compose.material.FloatingActionButtonElevationAnimatable) r1
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L39
            goto L6d
        L39:
            r2 = move-exception
            goto L78
        L3b:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
            float r3 = r2.m1565calculateTargetu2uoSUM(r9)
            r2.targetInteraction = r9
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r4 = r2.animatable     // Catch: java.lang.Throwable -> L74
            java.lang.Object r4 = r4.getTargetValue()     // Catch: java.lang.Throwable -> L74
            androidx.compose.ui.unit.Dp r4 = (androidx.compose.ui.unit.Dp) r4     // Catch: java.lang.Throwable -> L74
            float r4 = r4.m6640unboximpl()     // Catch: java.lang.Throwable -> L74
            boolean r4 = androidx.compose.ui.unit.Dp.m6631equalsimpl0(r4, r3)     // Catch: java.lang.Throwable -> L74
            if (r4 != 0) goto L6e
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r4 = r2.animatable     // Catch: java.lang.Throwable -> L74
            androidx.compose.foundation.interaction.Interaction r5 = r2.lastTargetInteraction     // Catch: java.lang.Throwable -> L74
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L74
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L74
            r6 = 1
            r0.label = r6     // Catch: java.lang.Throwable -> L74
            java.lang.Object r3 = androidx.compose.material.ElevationKt.m1543animateElevationrAjV9yQ(r4, r3, r5, r9, r0)     // Catch: java.lang.Throwable -> L74
            if (r3 != r1) goto L6c
            return r1
        L6c:
            r1 = r2
        L6d:
            r2 = r1
        L6e:
            r2.lastTargetInteraction = r9
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L74:
            r1 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L78:
            r1.lastTargetInteraction = r9
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.FloatingActionButtonElevationAnimatable.animateElevation(androidx.compose.foundation.interaction.Interaction, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final State<Dp> asState() {
        return this.animatable.asState();
    }
}
