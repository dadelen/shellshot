package androidx.compose.animation.core;

import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;

/* compiled from: AnimateAsState.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1", f = "AnimateAsState.kt", i = {0}, l = {419}, m = "invokeSuspend", n = {"$this$LaunchedEffect"}, s = {"L$0"})
/* loaded from: classes.dex */
final class AnimateAsStateKt$animateValueAsState$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<AnimationSpec<T>> $animSpec$delegate;
    final /* synthetic */ Animatable<T, V> $animatable;
    final /* synthetic */ Channel<T> $channel;
    final /* synthetic */ State<Function1<T, Unit>> $listener$delegate;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AnimateAsStateKt$animateValueAsState$3$1(Channel<T> channel, Animatable<T, V> animatable, State<? extends AnimationSpec<T>> state, State<? extends Function1<? super T, Unit>> state2, Continuation<? super AnimateAsStateKt$animateValueAsState$3$1> continuation) {
        super(2, continuation);
        this.$channel = channel;
        this.$animatable = animatable;
        this.$animSpec$delegate = state;
        this.$listener$delegate = state2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AnimateAsStateKt$animateValueAsState$3$1 animateAsStateKt$animateValueAsState$3$1 = new AnimateAsStateKt$animateValueAsState$3$1(this.$channel, this.$animatable, this.$animSpec$delegate, this.$listener$delegate, continuation);
        animateAsStateKt$animateValueAsState$3$1.L$0 = obj;
        return animateAsStateKt$animateValueAsState$3$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AnimateAsStateKt$animateValueAsState$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0055  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0046 -> B:7:0x004d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            switch(r1) {
                case 0: goto L24;
                case 1: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L12:
            r1 = r12
            java.lang.Object r2 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r3 = r1.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlin.ResultKt.throwOnFailure(r13)
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r13
            goto L4d
        L24:
            kotlin.ResultKt.throwOnFailure(r13)
            r1 = r12
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            kotlinx.coroutines.channels.Channel<T> r3 = r1.$channel
            kotlinx.coroutines.channels.ChannelIterator r3 = r3.iterator()
            r11 = r3
            r3 = r2
            r2 = r11
        L35:
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r1.L$0 = r3
            r1.L$1 = r2
            r5 = 1
            r1.label = r5
            java.lang.Object r4 = r2.hasNext(r4)
            if (r4 != r0) goto L46
            return r0
        L46:
            r11 = r0
            r0 = r13
            r13 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r11
        L4d:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L84
            java.lang.Object r13 = r3.next()
            kotlinx.coroutines.channels.Channel<T> r5 = r2.$channel
            java.lang.Object r5 = r5.mo8707tryReceivePtdJZtk()
            java.lang.Object r5 = kotlinx.coroutines.channels.ChannelResult.m8718getOrNullimpl(r5)
            if (r5 != 0) goto L66
            goto L67
        L66:
            r13 = r5
        L67:
            r6 = r13
            androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1$1 r5 = new androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1$1
            androidx.compose.animation.core.Animatable<T, V> r7 = r2.$animatable
            androidx.compose.runtime.State<androidx.compose.animation.core.AnimationSpec<T>> r8 = r2.$animSpec$delegate
            androidx.compose.runtime.State<kotlin.jvm.functions.Function1<T, kotlin.Unit>> r9 = r2.$listener$delegate
            r10 = 0
            r5.<init>(r6, r7, r8, r9, r10)
            r7 = r5
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
            r13 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r4
            goto L35
        L84:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: AnimateAsState.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1$1", f = "AnimateAsState.kt", i = {}, l = {428}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ State<AnimationSpec<T>> $animSpec$delegate;
        final /* synthetic */ Animatable<T, V> $animatable;
        final /* synthetic */ State<Function1<T, Unit>> $listener$delegate;
        final /* synthetic */ T $newTarget;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(T t, Animatable<T, V> animatable, State<? extends AnimationSpec<T>> state, State<? extends Function1<? super T, Unit>> state2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$newTarget = t;
            this.$animatable = animatable;
            this.$animSpec$delegate = state;
            this.$listener$delegate = state2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$newTarget, this.$animatable, this.$animSpec$delegate, this.$listener$delegate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                switch(r1) {
                    case 0: goto L17;
                    case 1: goto L12;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L12:
                r0 = r11
                kotlin.ResultKt.throwOnFailure(r12)
                goto L46
            L17:
                kotlin.ResultKt.throwOnFailure(r12)
                r1 = r11
                T r2 = r1.$newTarget
                androidx.compose.animation.core.Animatable<T, V> r3 = r1.$animatable
                java.lang.Object r3 = r3.getTargetValue()
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
                if (r2 != 0) goto L58
                androidx.compose.animation.core.Animatable<T, V> r3 = r1.$animatable
                T r4 = r1.$newTarget
                androidx.compose.runtime.State<androidx.compose.animation.core.AnimationSpec<T>> r2 = r1.$animSpec$delegate
                androidx.compose.animation.core.AnimationSpec r5 = androidx.compose.animation.core.AnimateAsStateKt.access$animateValueAsState$lambda$6(r2)
                r8 = r1
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r2 = 1
                r1.label = r2
                r6 = 0
                r7 = 0
                r9 = 12
                r10 = 0
                java.lang.Object r2 = androidx.compose.animation.core.Animatable.animateTo$default(r3, r4, r5, r6, r7, r8, r9, r10)
                if (r2 != r0) goto L45
                return r0
            L45:
                r0 = r1
            L46:
                androidx.compose.runtime.State<kotlin.jvm.functions.Function1<T, kotlin.Unit>> r1 = r0.$listener$delegate
                kotlin.jvm.functions.Function1 r1 = androidx.compose.animation.core.AnimateAsStateKt.access$animateValueAsState$lambda$4(r1)
                if (r1 == 0) goto L57
                androidx.compose.animation.core.Animatable<T, V> r2 = r0.$animatable
                java.lang.Object r2 = r2.getValue()
                r1.invoke(r2)
            L57:
                r1 = r0
            L58:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
