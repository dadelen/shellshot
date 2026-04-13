package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: AnchoredDraggable.kt */
@Metadata(d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016*\u0001\u0018\b\u0007\u0018\u0000 p*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001pB\u008f\u0001\b\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011\u0012#\b\u0002\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u0007¢\u0006\u0002\u0010\u0015B\u007f\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011\u0012#\b\u0002\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u0007¢\u0006\u0002\u0010\u0016Jy\u0010P\u001a\u00020Q2\u0006\u0010K\u001a\u00028\u00002\b\b\u0002\u0010R\u001a\u00020S2W\u0010T\u001aS\b\u0001\u0012\u0004\u0012\u00020V\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(W\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(K\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0X\u0012\u0006\u0012\u0004\u0018\u00010\u00020U¢\u0006\u0002\bYH\u0086@¢\u0006\u0002\u0010ZJ\\\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020S2B\u0010T\u001a>\b\u0001\u0012\u0004\u0012\u00020V\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0X\u0012\u0006\u0012\u0004\u0018\u00010\u00020[¢\u0006\u0002\bYH\u0086@¢\u0006\u0002\u0010\\J%\u0010]\u001a\u00028\u00002\u0006\u0010:\u001a\u00020\b2\u0006\u0010#\u001a\u00028\u00002\u0006\u0010^\u001a\u00020\bH\u0002¢\u0006\u0002\u0010_J\u000e\u0010`\u001a\u00020\b2\u0006\u0010a\u001a\u00020\bJ\u0015\u0010b\u001a\u00020\b2\u0006\u0010a\u001a\u00020\bH\u0000¢\u0006\u0002\bcJ\u001d\u0010?\u001a\u00020\b2\u0006\u0010d\u001a\u00028\u00002\u0006\u0010e\u001a\u00028\u0000H\u0007¢\u0006\u0002\u0010fJ\u0006\u0010g\u001a\u00020\bJ\u0016\u0010h\u001a\u00020\b2\u0006\u0010^\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010iJ\u0015\u0010j\u001a\u00020\u00142\u0006\u0010K\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010kJ#\u0010l\u001a\u00020Q2\f\u0010m\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\b\b\u0002\u0010n\u001a\u00028\u0000¢\u0006\u0002\u0010oR\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R7\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR/\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R+\u0010#\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010 \u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010-\u001a\u0004\u0018\u00018\u00002\b\u0010\u001a\u001a\u0004\u0018\u00018\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010 \u001a\u0004\b.\u0010%\"\u0004\b/\u0010'R\u0011\u00101\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b1\u00102R+\u00103\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b4\u00105\"\u0004\b6\u00107R+\u0010:\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u00109\u001a\u0004\b;\u00105\"\u0004\b<\u00107R/\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\"R!\u0010?\u001a\u00020\b8GX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bC\u0010D\u0012\u0004\b@\u0010A\u001a\u0004\bB\u00105R+\u0010E\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010 \u001a\u0004\bF\u0010%\"\u0004\bG\u0010'R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u001b\u0010K\u001a\u00028\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bM\u0010D\u001a\u0004\bL\u0010%R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010O¨\u0006q"}, d2 = {"Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "T", "", "initialValue", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "positionalThreshold", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "totalDistance", "velocityThreshold", "Lkotlin/Function0;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "confirmValueChange", "newValue", "", "(Ljava/lang/Object;Landroidx/compose/foundation/gestures/DraggableAnchors;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/jvm/functions/Function1;)V", "anchoredDragScope", "androidx/compose/foundation/gestures/AnchoredDraggableState$anchoredDragScope$1", "Landroidx/compose/foundation/gestures/AnchoredDraggableState$anchoredDragScope$1;", "<set-?>", "getAnchors", "()Landroidx/compose/foundation/gestures/DraggableAnchors;", "setAnchors", "(Landroidx/compose/foundation/gestures/DraggableAnchors;)V", "anchors$delegate", "Landroidx/compose/runtime/MutableState;", "getConfirmValueChange$foundation_release", "()Lkotlin/jvm/functions/Function1;", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "setCurrentValue", "(Ljava/lang/Object;)V", "currentValue$delegate", "getDecayAnimationSpec", "()Landroidx/compose/animation/core/DecayAnimationSpec;", "dragMutex", "Landroidx/compose/foundation/MutatorMutex;", "dragTarget", "getDragTarget", "setDragTarget", "dragTarget$delegate", "isAnimationRunning", "()Z", "lastVelocity", "getLastVelocity", "()F", "setLastVelocity", "(F)V", "lastVelocity$delegate", "Landroidx/compose/runtime/MutableFloatState;", "offset", "getOffset", "setOffset", "offset$delegate", "getPositionalThreshold$foundation_release", NotificationCompat.CATEGORY_PROGRESS, "getProgress$annotations", "()V", "getProgress", "progress$delegate", "Landroidx/compose/runtime/State;", "settledValue", "getSettledValue", "setSettledValue", "settledValue$delegate", "getSnapAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "targetValue", "getTargetValue", "targetValue$delegate", "getVelocityThreshold$foundation_release", "()Lkotlin/jvm/functions/Function0;", "anchoredDrag", "", "dragPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function4;", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchor", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function3;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeTarget", "velocity", "(FLjava/lang/Object;F)Ljava/lang/Object;", "dispatchRawDelta", "delta", "newOffsetForDelta", "newOffsetForDelta$foundation_release", "from", "to", "(Ljava/lang/Object;Ljava/lang/Object;)F", "requireOffset", "settle", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySnapTo", "(Ljava/lang/Object;)Z", "updateAnchors", "newAnchors", "newTarget", "(Landroidx/compose/foundation/gestures/DraggableAnchors;Ljava/lang/Object;)V", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnchoredDraggableState<T> {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDraggableState$anchoredDragScope$1<T> anchoredDragScope;

    /* renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private final Function1<T, Boolean> confirmValueChange;

    /* renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    private final DecayAnimationSpec<Float> decayAnimationSpec;
    private final MutatorMutex dragMutex;

    /* renamed from: dragTarget$delegate, reason: from kotlin metadata */
    private final MutableState dragTarget;

    /* renamed from: lastVelocity$delegate, reason: from kotlin metadata */
    private final MutableFloatState lastVelocity;

    /* renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableFloatState offset;
    private final Function1<Float, Float> positionalThreshold;

    /* renamed from: progress$delegate, reason: from kotlin metadata */
    private final State progress;

    /* renamed from: settledValue$delegate, reason: from kotlin metadata */
    private final MutableState settledValue;
    private final AnimationSpec<Float> snapAnimationSpec;

    /* renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final State targetValue;
    private final Function0<Float> velocityThreshold;

    @Deprecated(message = "Use the progress function to query the progress between two specified anchors.", replaceWith = @ReplaceWith(expression = "progress(state.settledValue, state.targetValue)", imports = {}))
    public static /* synthetic */ void getProgress$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnchoredDraggableState(T t, Function1<? super Float, Float> function1, Function0<Float> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Function1<? super T, Boolean> function12) {
        this.positionalThreshold = function1;
        this.velocityThreshold = function0;
        this.snapAnimationSpec = animationSpec;
        this.decayAnimationSpec = decayAnimationSpec;
        this.confirmValueChange = function12;
        this.dragMutex = new MutatorMutex();
        this.currentValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.settledValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.targetValue = SnapshotStateKt.derivedStateOf(new Function0<T>(this) { // from class: androidx.compose.foundation.gestures.AnchoredDraggableState$targetValue$2
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                T currentValue;
                Object dragTarget;
                dragTarget = this.this$0.getDragTarget();
                T t2 = (T) dragTarget;
                if (t2 != null) {
                    return t2;
                }
                AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
                if (!Float.isNaN(anchoredDraggableState.getOffset())) {
                    currentValue = anchoredDraggableState.getAnchors().closestAnchor(anchoredDraggableState.getOffset());
                    if (currentValue == null) {
                        currentValue = anchoredDraggableState.getCurrentValue();
                    }
                } else {
                    currentValue = anchoredDraggableState.getCurrentValue();
                }
                return currentValue;
            }
        });
        this.offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);
        this.progress = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Float>(this) { // from class: androidx.compose.foundation.gestures.AnchoredDraggableState$progress$2
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                float a = this.this$0.getAnchors().positionOf(this.this$0.getSettledValue());
                float b = this.this$0.getAnchors().positionOf(this.this$0.getTargetValue());
                float distance = Math.abs(b - a);
                float f = 1.0f;
                if (!Float.isNaN(distance) && distance > 1.0E-6f) {
                    float progress = (this.this$0.requireOffset() - a) / (b - a);
                    if (progress < 1.0E-6f) {
                        f = 0.0f;
                    } else if (progress <= 0.999999f) {
                        f = progress;
                    }
                }
                return Float.valueOf(f);
            }
        });
        this.lastVelocity = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.anchors = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AnchoredDraggableKt.emptyDraggableAnchors(), null, 2, null);
        this.anchoredDragScope = new AnchoredDraggableState$anchoredDragScope$1(this);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ AnchoredDraggableState(java.lang.Object r8, kotlin.jvm.functions.Function1 r9, kotlin.jvm.functions.Function0 r10, androidx.compose.animation.core.AnimationSpec r11, androidx.compose.animation.core.DecayAnimationSpec r12, kotlin.jvm.functions.Function1 r13, int r14, kotlin.jvm.internal.DefaultConstructorMarker r15) {
        /*
            r7 = this;
            r14 = r14 & 32
            if (r14 == 0) goto La
            androidx.compose.foundation.gestures.AnchoredDraggableState$1 r13 = new kotlin.jvm.functions.Function1<T, java.lang.Boolean>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableState.1
                static {
                    /*
                        androidx.compose.foundation.gestures.AnchoredDraggableState$1 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableState$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:androidx.compose.foundation.gestures.AnchoredDraggableState$1) androidx.compose.foundation.gestures.AnchoredDraggableState.1.INSTANCE androidx.compose.foundation.gestures.AnchoredDraggableState$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass1.<init>():void");
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Boolean invoke(T r2) {
                    /*
                        r1 = this;
                        r0 = 1
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass1.invoke(java.lang.Object):java.lang.Boolean");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ java.lang.Boolean invoke(java.lang.Object r2) {
                    /*
                        r1 = this;
                        java.lang.Boolean r0 = r1.invoke(r2)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            r6 = r13
            goto Lb
        La:
            r6 = r13
        Lb:
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.<init>(java.lang.Object, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, androidx.compose.animation.core.AnimationSpec, androidx.compose.animation.core.DecayAnimationSpec, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Function1<Float, Float> getPositionalThreshold$foundation_release() {
        return this.positionalThreshold;
    }

    public final Function0<Float> getVelocityThreshold$foundation_release() {
        return this.velocityThreshold;
    }

    public final AnimationSpec<Float> getSnapAnimationSpec() {
        return this.snapAnimationSpec;
    }

    public final DecayAnimationSpec<Float> getDecayAnimationSpec() {
        return this.decayAnimationSpec;
    }

    public final Function1<T, Boolean> getConfirmValueChange$foundation_release() {
        return this.confirmValueChange;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ AnchoredDraggableState(java.lang.Object r10, androidx.compose.foundation.gestures.DraggableAnchors r11, kotlin.jvm.functions.Function1 r12, kotlin.jvm.functions.Function0 r13, androidx.compose.animation.core.AnimationSpec r14, androidx.compose.animation.core.DecayAnimationSpec r15, kotlin.jvm.functions.Function1 r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r9 = this;
            r0 = r17 & 64
            if (r0 == 0) goto La
            androidx.compose.foundation.gestures.AnchoredDraggableState$2 r0 = new kotlin.jvm.functions.Function1<T, java.lang.Boolean>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableState.2
                static {
                    /*
                        androidx.compose.foundation.gestures.AnchoredDraggableState$2 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableState$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:androidx.compose.foundation.gestures.AnchoredDraggableState$2) androidx.compose.foundation.gestures.AnchoredDraggableState.2.INSTANCE androidx.compose.foundation.gestures.AnchoredDraggableState$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass2.<init>():void");
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Boolean invoke(T r2) {
                    /*
                        r1 = this;
                        r0 = 1
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass2.invoke(java.lang.Object):java.lang.Boolean");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ java.lang.Boolean invoke(java.lang.Object r2) {
                    /*
                        r1 = this;
                        java.lang.Boolean r0 = r1.invoke(r2)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.AnonymousClass2.invoke(java.lang.Object):java.lang.Object");
                }
            }
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            r8 = r0
            goto Lc
        La:
            r8 = r16
        Lc:
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.<init>(java.lang.Object, androidx.compose.foundation.gestures.DraggableAnchors, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, androidx.compose.animation.core.AnimationSpec, androidx.compose.animation.core.DecayAnimationSpec, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public AnchoredDraggableState(T t, DraggableAnchors<T> draggableAnchors, Function1<? super Float, Float> function1, Function0<Float> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Function1<? super T, Boolean> function12) {
        this(t, function1, function0, animationSpec, decayAnimationSpec, function12);
        setAnchors(draggableAnchors);
        trySnapTo(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentValue(T t) {
        MutableState $this$setValue$iv = this.currentValue;
        $this$setValue$iv.setValue(t);
    }

    public final T getCurrentValue() {
        State $this$getValue$iv = this.currentValue;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSettledValue(T t) {
        MutableState $this$setValue$iv = this.settledValue;
        $this$setValue$iv.setValue(t);
    }

    public final T getSettledValue() {
        State $this$getValue$iv = this.settledValue;
        return $this$getValue$iv.getValue();
    }

    public final T getTargetValue() {
        return (T) this.targetValue.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOffset(float f) {
        MutableFloatState $this$setValue$iv = this.offset;
        $this$setValue$iv.setFloatValue(f);
    }

    public final float getOffset() {
        FloatState $this$getValue$iv = this.offset;
        return $this$getValue$iv.getFloatValue();
    }

    public final float requireOffset() {
        if (Float.isNaN(getOffset())) {
            throw new IllegalStateException("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?".toString());
        }
        return getOffset();
    }

    public final boolean isAnimationRunning() {
        return getDragTarget() != null;
    }

    public final float progress(T from, T to) {
        float fromOffset = getAnchors().positionOf(from);
        float toOffset = getAnchors().positionOf(to);
        float currentOffset = RangesKt.coerceIn(getOffset(), Math.min(fromOffset, toOffset), Math.max(fromOffset, toOffset));
        float fraction = (currentOffset - fromOffset) / (toOffset - fromOffset);
        if (Float.isNaN(fraction)) {
            return 1.0f;
        }
        if (fraction < 1.0E-6f) {
            return 0.0f;
        }
        if (fraction > 0.999999f) {
            return 1.0f;
        }
        return Math.abs(fraction);
    }

    public final float getProgress() {
        State $this$getValue$iv = this.progress;
        return ((Number) $this$getValue$iv.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLastVelocity(float f) {
        MutableFloatState $this$setValue$iv = this.lastVelocity;
        $this$setValue$iv.setFloatValue(f);
    }

    public final float getLastVelocity() {
        FloatState $this$getValue$iv = this.lastVelocity;
        return $this$getValue$iv.getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T getDragTarget() {
        State $this$getValue$iv = this.dragTarget;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDragTarget(T t) {
        MutableState $this$setValue$iv = this.dragTarget;
        $this$setValue$iv.setValue(t);
    }

    private final void setAnchors(DraggableAnchors<T> draggableAnchors) {
        MutableState $this$setValue$iv = this.anchors;
        $this$setValue$iv.setValue(draggableAnchors);
    }

    public final DraggableAnchors<T> getAnchors() {
        State $this$getValue$iv = this.anchors;
        return (DraggableAnchors) $this$getValue$iv.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateAnchors$default(AnchoredDraggableState anchoredDraggableState, DraggableAnchors draggableAnchors, Object obj, int i, Object obj2) {
        if ((i & 2) != 0) {
            if (!Float.isNaN(anchoredDraggableState.getOffset())) {
                obj = draggableAnchors.closestAnchor(anchoredDraggableState.getOffset());
                if (obj == null) {
                    obj = anchoredDraggableState.getTargetValue();
                }
            } else {
                obj = anchoredDraggableState.getTargetValue();
            }
        }
        anchoredDraggableState.updateAnchors(draggableAnchors, obj);
    }

    public final void updateAnchors(DraggableAnchors<T> newAnchors, T newTarget) {
        if (!Intrinsics.areEqual(getAnchors(), newAnchors)) {
            setAnchors(newAnchors);
            boolean snapSuccessful = trySnapTo(newTarget);
            if (!snapSuccessful) {
                setDragTarget(newTarget);
            }
        }
    }

    public final Object settle(float velocity, Continuation<? super Float> continuation) {
        T currentValue = getCurrentValue();
        T computeTarget = computeTarget(requireOffset(), currentValue, velocity);
        if (this.confirmValueChange.invoke(computeTarget).booleanValue()) {
            return AnchoredDraggableKt.animateToWithDecay(this, computeTarget, velocity, continuation);
        }
        return AnchoredDraggableKt.animateToWithDecay(this, currentValue, velocity, continuation);
    }

    private final T computeTarget(float offset, T currentValue, float velocity) {
        DraggableAnchors currentAnchors = getAnchors();
        float currentAnchorPosition = currentAnchors.positionOf(currentValue);
        float velocityThresholdPx = this.velocityThreshold.invoke().floatValue();
        if (!(currentAnchorPosition == offset) && !Float.isNaN(currentAnchorPosition)) {
            if (Math.abs(velocity) >= Math.abs(velocityThresholdPx)) {
                T closestAnchor = currentAnchors.closestAnchor(offset, Math.signum(velocity) > 0.0f);
                Intrinsics.checkNotNull(closestAnchor);
                return closestAnchor;
            }
            T closestAnchor2 = currentAnchors.closestAnchor(offset, offset - currentAnchorPosition > 0.0f);
            Intrinsics.checkNotNull(closestAnchor2);
            float neighborAnchorPosition = currentAnchors.positionOf(closestAnchor2);
            float distance = Math.abs(currentAnchorPosition - neighborAnchorPosition);
            float relativeThreshold = Math.abs(this.positionalThreshold.invoke(Float.valueOf(distance)).floatValue());
            float relativePosition = Math.abs(currentAnchorPosition - offset);
            if (relativePosition > relativeThreshold) {
                return closestAnchor2;
            }
        }
        return currentValue;
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, MutatePriority mutatePriority, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(mutatePriority, function3, continuation);
    }

    public final Object anchoredDrag(MutatePriority dragPriority, Function3<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object mutate = this.dragMutex.mutate(dragPriority, new AnchoredDraggableState$anchoredDrag$2(this, function3, null), continuation);
        return mutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate : Unit.INSTANCE;
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, Object obj, MutatePriority mutatePriority, Function4 function4, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(obj, mutatePriority, function4, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object anchoredDrag(T r8, androidx.compose.foundation.MutatePriority r9, kotlin.jvm.functions.Function4<? super androidx.compose.foundation.gestures.AnchoredDragScope, ? super androidx.compose.foundation.gestures.DraggableAnchors<T>, ? super T, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$3
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$3 r0 = (androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$3 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$3
            r0.<init>(r7, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            java.lang.Object r8 = r0.L$0
            androidx.compose.foundation.gestures.AnchoredDraggableState r8 = (androidx.compose.foundation.gestures.AnchoredDraggableState) r8
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L36
            goto L5d
        L36:
            r9 = move-exception
            goto L63
        L38:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r7
            androidx.compose.foundation.gestures.DraggableAnchors r4 = r2.getAnchors()
            boolean r4 = r4.hasAnchorFor(r8)
            if (r4 == 0) goto L67
        L47:
            androidx.compose.foundation.MutatorMutex r4 = r2.dragMutex     // Catch: java.lang.Throwable -> L61
            androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$4 r5 = new androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$4     // Catch: java.lang.Throwable -> L61
            r5.<init>(r2, r8, r10, r3)     // Catch: java.lang.Throwable -> L61
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch: java.lang.Throwable -> L61
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L61
            r6 = 1
            r0.label = r6     // Catch: java.lang.Throwable -> L61
            java.lang.Object r4 = r4.mutate(r9, r5, r0)     // Catch: java.lang.Throwable -> L61
            if (r4 != r1) goto L5c
            return r1
        L5c:
            r8 = r2
        L5d:
            r8.setDragTarget(r3)
            goto L7b
        L61:
            r9 = move-exception
            r8 = r2
        L63:
            r8.setDragTarget(r3)
            throw r9
        L67:
            kotlin.jvm.functions.Function1<T, java.lang.Boolean> r9 = r2.confirmValueChange
            java.lang.Object r9 = r9.invoke(r8)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L7b
            r2.setSettledValue(r8)
            r2.setCurrentValue(r8)
        L7b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableState.anchoredDrag(java.lang.Object, androidx.compose.foundation.MutatePriority, kotlin.jvm.functions.Function4, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final float newOffsetForDelta$foundation_release(float delta) {
        return RangesKt.coerceIn((Float.isNaN(getOffset()) ? 0.0f : getOffset()) + delta, getAnchors().minAnchor(), getAnchors().maxAnchor());
    }

    public final float dispatchRawDelta(float delta) {
        float newOffset = newOffsetForDelta$foundation_release(delta);
        float oldOffset = Float.isNaN(getOffset()) ? 0.0f : getOffset();
        setOffset(newOffset);
        return newOffset - oldOffset;
    }

    private final boolean trySnapTo(T targetValue) {
        MutatorMutex this_$iv = this.dragMutex;
        boolean didLock$iv = this_$iv.tryLock();
        if (didLock$iv) {
            try {
                AnchoredDraggableState$anchoredDragScope$1 $this$trySnapTo_u24lambda_u242_u24lambda_u241 = this.anchoredDragScope;
                float targetOffset = getAnchors().positionOf(targetValue);
                if (!Float.isNaN(targetOffset)) {
                    AnchoredDragScope.dragTo$default($this$trySnapTo_u24lambda_u242_u24lambda_u241, targetOffset, 0.0f, 2, null);
                    setDragTarget(null);
                }
                setCurrentValue(targetValue);
                setSettledValue(targetValue);
            } finally {
                this_$iv.unlock();
            }
        }
        return didLock$iv;
    }

    /* compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0087\u0001\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\t0\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00140\rH\u0007¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/gestures/AnchoredDraggableState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "T", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "positionalThreshold", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "distance", "velocityThreshold", "Lkotlin/Function0;", "confirmValueChange", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Saver Saver$default(Companion companion, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, Function1 function1, Function0 function0, Function1 function12, int i, Object obj) {
            Function1 function13;
            if ((i & 16) == 0) {
                function13 = function12;
            } else {
                function13 = new Function1<T, Boolean>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableState$Companion$Saver$1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(T t) {
                        return true;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Object obj2) {
                        return invoke((AnchoredDraggableState$Companion$Saver$1<T>) obj2);
                    }
                };
            }
            return companion.Saver(animationSpec, decayAnimationSpec, function1, function0, function13);
        }

        public final <T> Saver<AnchoredDraggableState<T>, T> Saver(final AnimationSpec<Float> snapAnimationSpec, final DecayAnimationSpec<Float> decayAnimationSpec, final Function1<? super Float, Float> positionalThreshold, final Function0<Float> velocityThreshold, final Function1<? super T, Boolean> confirmValueChange) {
            return SaverKt.Saver(new Function2<SaverScope, AnchoredDraggableState<T>, T>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableState$Companion$Saver$2
                @Override // kotlin.jvm.functions.Function2
                public final T invoke(SaverScope $this$Saver, AnchoredDraggableState<T> anchoredDraggableState) {
                    return anchoredDraggableState.getCurrentValue();
                }
            }, new Function1<T, AnchoredDraggableState<T>>() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableState$Companion$Saver$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke((AnchoredDraggableState$Companion$Saver$3<T>) obj);
                }

                @Override // kotlin.jvm.functions.Function1
                public final AnchoredDraggableState<T> invoke(T t) {
                    return new AnchoredDraggableState<>(t, positionalThreshold, velocityThreshold, snapAnimationSpec, decayAnimationSpec, confirmValueChange);
                }
            });
        }
    }
}
