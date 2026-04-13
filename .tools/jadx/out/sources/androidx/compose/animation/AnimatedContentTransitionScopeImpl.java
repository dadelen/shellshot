package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimatedContent.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002UVB%\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\"\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u0017\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0004\b>\u0010?JK\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u0002002\f\u0010C\u001a\b\u0012\u0004\u0012\u0002060D2!\u0010E\u001a\u001d\u0012\u0013\u0012\u00110G¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020G0FH\u0016ø\u0001\u0000¢\u0006\u0004\bK\u0010LJK\u0010M\u001a\u00020N2\u0006\u0010B\u001a\u0002002\f\u0010C\u001a\b\u0012\u0004\u0012\u0002060D2!\u0010O\u001a\u001d\u0012\u0013\u0012\u00110G¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020G0FH\u0016ø\u0001\u0000¢\u0006\u0004\bP\u0010QJ\u0017\u0010R\u001a\u00020=*\u00020=2\b\u0010S\u001a\u0004\u0018\u00010TH\u0096\u0004R\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\f8BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR1\u0010 \u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f8@@@X\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010#R&\u0010&\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0'X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u001aR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0018\u0010.\u001a\u00020/*\u0002008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0018\u00103\u001a\u00020/*\u0002008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00102\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006W²\u0006\u0010\u0010X\u001a\u00020/\"\u0004\b\u0000\u0010\u0001X\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "S", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "transition", "Landroidx/compose/animation/core/Transition;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/unit/LayoutDirection;)V", "animatedSize", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/unit/IntSize;", "getAnimatedSize$animation_release", "()Landroidx/compose/runtime/State;", "setAnimatedSize$animation_release", "(Landroidx/compose/runtime/State;)V", "getContentAlignment", "()Landroidx/compose/ui/Alignment;", "setContentAlignment", "(Landroidx/compose/ui/Alignment;)V", "currentSize", "getCurrentSize-YbymL2g", "()J", "initialState", "getInitialState", "()Ljava/lang/Object;", "getLayoutDirection$animation_release", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection$animation_release", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "<set-?>", "measuredSize", "getMeasuredSize-YbymL2g$animation_release", "setMeasuredSize-ozmzZPI$animation_release", "(J)V", "measuredSize$delegate", "Landroidx/compose/runtime/MutableState;", "targetSizeMap", "Landroidx/collection/MutableScatterMap;", "getTargetSizeMap$animation_release", "()Landroidx/collection/MutableScatterMap;", "targetState", "getTargetState", "getTransition$animation_release", "()Landroidx/compose/animation/core/Transition;", "isLeft", "", "Landroidx/compose/animation/AnimatedContentTransitionScope$SlideDirection;", "isLeft-gWo6LJ4", "(I)Z", "isRight", "isRight-gWo6LJ4", "calculateOffset", "Landroidx/compose/ui/unit/IntOffset;", "fullSize", "calculateOffset-emnUabE", "(JJ)J", "createSizeAnimationModifier", "Landroidx/compose/ui/Modifier;", "contentTransform", "Landroidx/compose/animation/ContentTransform;", "createSizeAnimationModifier$animation_release", "(Landroidx/compose/animation/ContentTransform;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "slideIntoContainer", "Landroidx/compose/animation/EnterTransition;", "towards", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "initialOffset", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "offsetForFullSlide", "slideIntoContainer-mOhB8PU", "(ILandroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function1;)Landroidx/compose/animation/EnterTransition;", "slideOutOfContainer", "Landroidx/compose/animation/ExitTransition;", "targetOffset", "slideOutOfContainer-mOhB8PU", "(ILandroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function1;)Landroidx/compose/animation/ExitTransition;", "using", "sizeTransform", "Landroidx/compose/animation/SizeTransform;", "ChildData", "SizeModifier", "animation_release", "shouldAnimateSize"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimatedContentTransitionScopeImpl<S> implements AnimatedContentTransitionScope<S> {
    public static final int $stable = 8;
    private State<IntSize> animatedSize;
    private Alignment contentAlignment;
    private LayoutDirection layoutDirection;

    /* renamed from: measuredSize$delegate, reason: from kotlin metadata */
    private final MutableState measuredSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m6788boximpl(IntSize.INSTANCE.m6801getZeroYbymL2g()), null, 2, null);
    private final MutableScatterMap<S, State<IntSize>> targetSizeMap = ScatterMapKt.mutableScatterMapOf();
    private final Transition<S> transition;

    public AnimatedContentTransitionScopeImpl(Transition<S> transition, Alignment contentAlignment, LayoutDirection layoutDirection) {
        this.transition = transition;
        this.contentAlignment = contentAlignment;
        this.layoutDirection = layoutDirection;
    }

    public final Transition<S> getTransition$animation_release() {
        return this.transition;
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    public Alignment getContentAlignment() {
        return this.contentAlignment;
    }

    public void setContentAlignment(Alignment alignment) {
        this.contentAlignment = alignment;
    }

    /* renamed from: getLayoutDirection$animation_release, reason: from getter */
    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    public final void setLayoutDirection$animation_release(LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
    }

    @Override // androidx.compose.animation.core.Transition.Segment
    public S getInitialState() {
        return this.transition.getSegment().getInitialState();
    }

    @Override // androidx.compose.animation.core.Transition.Segment
    public S getTargetState() {
        return this.transition.getSegment().getTargetState();
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    public ContentTransform using(ContentTransform $this$using, SizeTransform sizeTransform) {
        $this$using.setSizeTransform$animation_release(sizeTransform);
        return $this$using;
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    /* renamed from: slideIntoContainer-mOhB8PU */
    public EnterTransition mo35slideIntoContainermOhB8PU(int towards, FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> initialOffset) {
        return m54isLeftgWo6LJ4(towards) ? EnterExitTransitionKt.slideInHorizontally(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m53getCurrentSizeYbymL2g;
                long m53getCurrentSizeYbymL2g2;
                long m52calculateOffsetemnUabE;
                Function1<Integer, Integer> function1 = initialOffset;
                m53getCurrentSizeYbymL2g = this.m53getCurrentSizeYbymL2g();
                int m6796getWidthimpl = IntSize.m6796getWidthimpl(m53getCurrentSizeYbymL2g);
                AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl = this;
                long IntSize = IntSizeKt.IntSize(it, it);
                m53getCurrentSizeYbymL2g2 = this.m53getCurrentSizeYbymL2g();
                m52calculateOffsetemnUabE = animatedContentTransitionScopeImpl.m52calculateOffsetemnUabE(IntSize, m53getCurrentSizeYbymL2g2);
                return function1.invoke(Integer.valueOf(m6796getWidthimpl - IntOffset.m6754getXimpl(m52calculateOffsetemnUabE)));
            }
        }) : m55isRightgWo6LJ4(towards) ? EnterExitTransitionKt.slideInHorizontally(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m53getCurrentSizeYbymL2g;
                long m52calculateOffsetemnUabE;
                Function1<Integer, Integer> function1 = initialOffset;
                AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl = this;
                long IntSize = IntSizeKt.IntSize(it, it);
                m53getCurrentSizeYbymL2g = this.m53getCurrentSizeYbymL2g();
                m52calculateOffsetemnUabE = animatedContentTransitionScopeImpl.m52calculateOffsetemnUabE(IntSize, m53getCurrentSizeYbymL2g);
                return function1.invoke(Integer.valueOf((-IntOffset.m6754getXimpl(m52calculateOffsetemnUabE)) - it));
            }
        }) : AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0(towards, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m49getUpDKzdypw()) ? EnterExitTransitionKt.slideInVertically(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m53getCurrentSizeYbymL2g;
                long m53getCurrentSizeYbymL2g2;
                long m52calculateOffsetemnUabE;
                Function1<Integer, Integer> function1 = initialOffset;
                m53getCurrentSizeYbymL2g = this.m53getCurrentSizeYbymL2g();
                int m6795getHeightimpl = IntSize.m6795getHeightimpl(m53getCurrentSizeYbymL2g);
                AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl = this;
                long IntSize = IntSizeKt.IntSize(it, it);
                m53getCurrentSizeYbymL2g2 = this.m53getCurrentSizeYbymL2g();
                m52calculateOffsetemnUabE = animatedContentTransitionScopeImpl.m52calculateOffsetemnUabE(IntSize, m53getCurrentSizeYbymL2g2);
                return function1.invoke(Integer.valueOf(m6795getHeightimpl - IntOffset.m6755getYimpl(m52calculateOffsetemnUabE)));
            }
        }) : AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0(towards, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m44getDownDKzdypw()) ? EnterExitTransitionKt.slideInVertically(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m53getCurrentSizeYbymL2g;
                long m52calculateOffsetemnUabE;
                Function1<Integer, Integer> function1 = initialOffset;
                AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl = this;
                long IntSize = IntSizeKt.IntSize(it, it);
                m53getCurrentSizeYbymL2g = this.m53getCurrentSizeYbymL2g();
                m52calculateOffsetemnUabE = animatedContentTransitionScopeImpl.m52calculateOffsetemnUabE(IntSize, m53getCurrentSizeYbymL2g);
                return function1.invoke(Integer.valueOf((-IntOffset.m6755getYimpl(m52calculateOffsetemnUabE)) - it));
            }
        }) : EnterTransition.INSTANCE.getNone();
    }

    /* renamed from: isLeft-gWo6LJ4, reason: not valid java name */
    private final boolean m54isLeftgWo6LJ4(int $this$isLeft) {
        return AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0($this$isLeft, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m46getLeftDKzdypw()) || (AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0($this$isLeft, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m48getStartDKzdypw()) && this.layoutDirection == LayoutDirection.Ltr) || (AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0($this$isLeft, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m45getEndDKzdypw()) && this.layoutDirection == LayoutDirection.Rtl);
    }

    /* renamed from: isRight-gWo6LJ4, reason: not valid java name */
    private final boolean m55isRightgWo6LJ4(int $this$isRight) {
        return AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0($this$isRight, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m47getRightDKzdypw()) || (AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0($this$isRight, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m48getStartDKzdypw()) && this.layoutDirection == LayoutDirection.Rtl) || (AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0($this$isRight, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m45getEndDKzdypw()) && this.layoutDirection == LayoutDirection.Ltr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateOffset-emnUabE, reason: not valid java name */
    public final long m52calculateOffsetemnUabE(long fullSize, long currentSize) {
        return getContentAlignment().mo3768alignKFBX0sM(fullSize, currentSize, LayoutDirection.Ltr);
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    /* renamed from: slideOutOfContainer-mOhB8PU */
    public ExitTransition mo36slideOutOfContainermOhB8PU(int towards, FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> targetOffset) {
        return m54isLeftgWo6LJ4(towards) ? EnterExitTransitionKt.slideOutHorizontally(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$1
            final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m52calculateOffsetemnUabE;
                State state = (State) this.this$0.getTargetSizeMap$animation_release().get(this.this$0.getTransition$animation_release().getTargetState());
                long targetSize = state != null ? ((IntSize) state.getValue()).getPackedValue() : IntSize.INSTANCE.m6801getZeroYbymL2g();
                Function1<Integer, Integer> function1 = targetOffset;
                m52calculateOffsetemnUabE = this.this$0.m52calculateOffsetemnUabE(IntSizeKt.IntSize(it, it), targetSize);
                return function1.invoke(Integer.valueOf((-IntOffset.m6754getXimpl(m52calculateOffsetemnUabE)) - it));
            }
        }) : m55isRightgWo6LJ4(towards) ? EnterExitTransitionKt.slideOutHorizontally(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$2
            final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m52calculateOffsetemnUabE;
                State state = (State) this.this$0.getTargetSizeMap$animation_release().get(this.this$0.getTransition$animation_release().getTargetState());
                long targetSize = state != null ? ((IntSize) state.getValue()).getPackedValue() : IntSize.INSTANCE.m6801getZeroYbymL2g();
                Function1<Integer, Integer> function1 = targetOffset;
                m52calculateOffsetemnUabE = this.this$0.m52calculateOffsetemnUabE(IntSizeKt.IntSize(it, it), targetSize);
                return function1.invoke(Integer.valueOf((-IntOffset.m6754getXimpl(m52calculateOffsetemnUabE)) + IntSize.m6796getWidthimpl(targetSize)));
            }
        }) : AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0(towards, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m49getUpDKzdypw()) ? EnterExitTransitionKt.slideOutVertically(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$3
            final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m52calculateOffsetemnUabE;
                State state = (State) this.this$0.getTargetSizeMap$animation_release().get(this.this$0.getTransition$animation_release().getTargetState());
                long targetSize = state != null ? ((IntSize) state.getValue()).getPackedValue() : IntSize.INSTANCE.m6801getZeroYbymL2g();
                Function1<Integer, Integer> function1 = targetOffset;
                m52calculateOffsetemnUabE = this.this$0.m52calculateOffsetemnUabE(IntSizeKt.IntSize(it, it), targetSize);
                return function1.invoke(Integer.valueOf((-IntOffset.m6755getYimpl(m52calculateOffsetemnUabE)) - it));
            }
        }) : AnimatedContentTransitionScope.SlideDirection.m40equalsimpl0(towards, AnimatedContentTransitionScope.SlideDirection.INSTANCE.m44getDownDKzdypw()) ? EnterExitTransitionKt.slideOutVertically(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$4
            final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int it) {
                long m52calculateOffsetemnUabE;
                State state = (State) this.this$0.getTargetSizeMap$animation_release().get(this.this$0.getTransition$animation_release().getTargetState());
                long targetSize = state != null ? ((IntSize) state.getValue()).getPackedValue() : IntSize.INSTANCE.m6801getZeroYbymL2g();
                Function1<Integer, Integer> function1 = targetOffset;
                m52calculateOffsetemnUabE = this.this$0.m52calculateOffsetemnUabE(IntSizeKt.IntSize(it, it), targetSize);
                return function1.invoke(Integer.valueOf((-IntOffset.m6755getYimpl(m52calculateOffsetemnUabE)) + IntSize.m6795getHeightimpl(targetSize)));
            }
        }) : ExitTransition.INSTANCE.getNone();
    }

    /* renamed from: getMeasuredSize-YbymL2g$animation_release, reason: not valid java name */
    public final long m56getMeasuredSizeYbymL2g$animation_release() {
        State $this$getValue$iv = this.measuredSize;
        return ((IntSize) $this$getValue$iv.getValue()).getPackedValue();
    }

    /* renamed from: setMeasuredSize-ozmzZPI$animation_release, reason: not valid java name */
    public final void m57setMeasuredSizeozmzZPI$animation_release(long j) {
        MutableState $this$setValue$iv = this.measuredSize;
        $this$setValue$iv.setValue(IntSize.m6788boximpl(j));
    }

    public final MutableScatterMap<S, State<IntSize>> getTargetSizeMap$animation_release() {
        return this.targetSizeMap;
    }

    public final State<IntSize> getAnimatedSize$animation_release() {
        return this.animatedSize;
    }

    public final void setAnimatedSize$animation_release(State<IntSize> state) {
        this.animatedSize = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getCurrentSize-YbymL2g, reason: not valid java name */
    public final long m53getCurrentSizeYbymL2g() {
        State<IntSize> state = this.animatedSize;
        return state != null ? state.getValue().getPackedValue() : m56getMeasuredSizeYbymL2g$animation_release();
    }

    public final Modifier createSizeAnimationModifier$animation_release(ContentTransform contentTransform, Composer $composer, int $changed) {
        Object value$iv;
        Modifier.Companion companion;
        Object value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, 93755870, "C(createSizeAnimationModifier)574@27302L40,575@27371L52:AnimatedContent.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(93755870, $changed, -1, "androidx.compose.animation.AnimatedContentTransitionScopeImpl.createSizeAnimationModifier (AnimatedContent.kt:573)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -546171924, "CC(remember):AnimatedContent.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this);
        Object it$iv = $composer.rememberedValue();
        boolean z = false;
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState shouldAnimateSize$delegate = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        State sizeTransform = SnapshotStateKt.rememberUpdatedState(contentTransform.getSizeTransform(), $composer, 0);
        if (Intrinsics.areEqual(this.transition.getCurrentState(), this.transition.getTargetState())) {
            createSizeAnimationModifier$lambda$3(shouldAnimateSize$delegate, false);
        } else if (sizeTransform.getValue() != null) {
            createSizeAnimationModifier$lambda$3(shouldAnimateSize$delegate, true);
        }
        if (createSizeAnimationModifier$lambda$2(shouldAnimateSize$delegate)) {
            $composer.startReplaceGroup(249037309);
            ComposerKt.sourceInformation($composer, "585@27840L48,586@27901L205");
            Transition.DeferredAnimation sizeAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(this.transition, VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), null, $composer, 0, 2);
            ComposerKt.sourceInformationMarkerStart($composer, -546152591, "CC(remember):AnimatedContent.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(sizeAnimation);
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                SizeTransform sizeTransform2 = (SizeTransform) sizeTransform.getValue();
                if (sizeTransform2 != null && !sizeTransform2.getClip()) {
                    z = true;
                }
                value$iv2 = (z ? Modifier.INSTANCE : ClipKt.clipToBounds(Modifier.INSTANCE)).then(new SizeModifier(sizeAnimation, sizeTransform));
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            companion = (Modifier) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(249353726);
            $composer.endReplaceGroup();
            this.animatedSize = null;
            companion = Modifier.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return companion;
    }

    private static final boolean createSizeAnimationModifier$lambda$2(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    private static final void createSizeAnimationModifier$lambda$3(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* compiled from: AnimatedContent.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b*\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0016R+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0002\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u000e"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScopeImpl$ChildData;", "Landroidx/compose/ui/layout/ParentDataModifier;", "isTarget", "", "(Z)V", "<set-?>", "()Z", "setTarget", "isTarget$delegate", "Landroidx/compose/runtime/MutableState;", "modifyParentData", "", "Landroidx/compose/ui/unit/Density;", "parentData", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ChildData implements ParentDataModifier {
        public static final int $stable = 0;

        /* renamed from: isTarget$delegate, reason: from kotlin metadata */
        private final MutableState isTarget;

        public ChildData(boolean isTarget) {
            this.isTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(isTarget), null, 2, null);
        }

        public final boolean isTarget() {
            State $this$getValue$iv = this.isTarget;
            return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
        }

        public final void setTarget(boolean z) {
            MutableState $this$setValue$iv = this.isTarget;
            $this$setValue$iv.setValue(Boolean.valueOf(z));
        }

        @Override // androidx.compose.ui.layout.ParentDataModifier
        public Object modifyParentData(Density $this$modifyParentData, Object parentData) {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AnimatedContent.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B3\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003R\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\u0002\u0010\nJ&\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017R'\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003R\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScopeImpl$SizeModifier;", "Landroidx/compose/animation/LayoutModifierWithPassThroughIntrinsics;", "sizeAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/animation/core/Transition;", "sizeTransform", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/SizeTransform;", "(Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/runtime/State;)V", "getSizeAnimation", "()Landroidx/compose/animation/core/Transition$DeferredAnimation;", "getSizeTransform", "()Landroidx/compose/runtime/State;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    final class SizeModifier extends LayoutModifierWithPassThroughIntrinsics {
        private final Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> sizeAnimation;
        private final State<SizeTransform> sizeTransform;

        /* JADX WARN: Multi-variable type inference failed */
        public SizeModifier(Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation, State<? extends SizeTransform> state) {
            this.sizeAnimation = deferredAnimation;
            this.sizeTransform = state;
        }

        public final Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> getSizeAnimation() {
            return this.sizeAnimation;
        }

        public final State<SizeTransform> getSizeTransform() {
            return this.sizeTransform;
        }

        @Override // androidx.compose.ui.layout.LayoutModifier
        /* renamed from: measure-3p2s80s, reason: not valid java name */
        public MeasureResult mo58measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
            final long packedValue;
            final Placeable mo5491measureBRTryo0 = measurable.mo5491measureBRTryo0(j);
            Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation = this.sizeAnimation;
            final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl = AnimatedContentTransitionScopeImpl.this;
            Function1<Transition.Segment<S>, FiniteAnimationSpec<IntSize>> function1 = new Function1<Transition.Segment<S>, FiniteAnimationSpec<IntSize>>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifier$measure$size$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final FiniteAnimationSpec<IntSize> invoke(Transition.Segment<S> segment) {
                    FiniteAnimationSpec<IntSize> mo115createAnimationSpecTemP2vQ;
                    State<IntSize> state = animatedContentTransitionScopeImpl.getTargetSizeMap$animation_release().get(segment.getInitialState());
                    long initial = state != null ? state.getValue().getPackedValue() : IntSize.INSTANCE.m6801getZeroYbymL2g();
                    State<IntSize> state2 = animatedContentTransitionScopeImpl.getTargetSizeMap$animation_release().get(segment.getTargetState());
                    long target = state2 != null ? state2.getValue().getPackedValue() : IntSize.INSTANCE.m6801getZeroYbymL2g();
                    SizeTransform value = this.getSizeTransform().getValue();
                    return (value == null || (mo115createAnimationSpecTemP2vQ = value.mo115createAnimationSpecTemP2vQ(initial, target)) == null) ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : mo115createAnimationSpecTemP2vQ;
                }
            };
            final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl2 = AnimatedContentTransitionScopeImpl.this;
            State<IntSize> animate = deferredAnimation.animate(function1, new Function1<S, IntSize>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifier$measure$size$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(Object obj) {
                    return IntSize.m6788boximpl(m59invokeYEO4UFw(obj));
                }

                /* renamed from: invoke-YEO4UFw, reason: not valid java name */
                public final long m59invokeYEO4UFw(S s) {
                    State<IntSize> state = animatedContentTransitionScopeImpl2.getTargetSizeMap$animation_release().get(s);
                    return state != null ? state.getValue().getPackedValue() : IntSize.INSTANCE.m6801getZeroYbymL2g();
                }
            });
            AnimatedContentTransitionScopeImpl.this.setAnimatedSize$animation_release(animate);
            if (measureScope.isLookingAhead()) {
                packedValue = IntSizeKt.IntSize(mo5491measureBRTryo0.getWidth(), mo5491measureBRTryo0.getHeight());
            } else {
                packedValue = animate.getValue().getPackedValue();
            }
            int m6796getWidthimpl = IntSize.m6796getWidthimpl(packedValue);
            int m6795getHeightimpl = IntSize.m6795getHeightimpl(packedValue);
            final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl3 = AnimatedContentTransitionScopeImpl.this;
            return MeasureScope.layout$default(measureScope, m6796getWidthimpl, m6795getHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifier$measure$1
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
                public final void invoke2(Placeable.PlacementScope $this$layout) {
                    long offset = animatedContentTransitionScopeImpl3.getContentAlignment().mo3768alignKFBX0sM(IntSizeKt.IntSize(mo5491measureBRTryo0.getWidth(), mo5491measureBRTryo0.getHeight()), packedValue, LayoutDirection.Ltr);
                    Placeable.PlacementScope.m5555place70tqf50$default($this$layout, mo5491measureBRTryo0, offset, 0.0f, 2, null);
                }
            }, 4, null);
        }
    }
}
