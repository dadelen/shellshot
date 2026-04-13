package androidx.compose.material3.internal;

import androidx.compose.foundation.R;
import androidx.compose.material3.TooltipState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BasicTooltip.android.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aa\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0001¢\u0006\u0002\u0010\u000f\u001a@\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0002\u0010\u0013\u001a:\u0010\u0014\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0002\u0010\u0015\u001a,\u0010\u0016\u001a\u00020\n*\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\u001c\u0010\u001a\u001a\u00020\n*\u00020\n2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u001b"}, d2 = {"BasicTooltipBox", "", "positionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "tooltip", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "state", "Landroidx/compose/material3/TooltipState;", "modifier", "Landroidx/compose/ui/Modifier;", "focusable", "", "enableUserInput", "content", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TooltipPopup", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/ui/window/PopupPositionProvider;Landroidx/compose/material3/TooltipState;Lkotlinx/coroutines/CoroutineScope;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "WrappedAnchor", "(ZLandroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "anchorSemantics", "label", "", "enabled", "handleGestures", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class BasicTooltip_androidKt {
    /* JADX WARN: Removed duplicated region for block: B:58:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTooltipBox(final androidx.compose.ui.window.PopupPositionProvider r35, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, androidx.compose.material3.TooltipState r37, androidx.compose.ui.Modifier r38, boolean r39, boolean r40, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instructions count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicTooltip_androidKt.BasicTooltipBox(androidx.compose.ui.window.PopupPositionProvider, kotlin.jvm.functions.Function2, androidx.compose.material3.TooltipState, androidx.compose.ui.Modifier, boolean, boolean, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0235  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void WrappedAnchor(final boolean r28, final androidx.compose.material3.TooltipState r29, androidx.compose.ui.Modifier r30, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, androidx.compose.runtime.Composer r32, final int r33, final int r34) {
        /*
            Method dump skipped, instructions count: 595
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicTooltip_androidKt.WrappedAnchor(boolean, androidx.compose.material3.TooltipState, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TooltipPopup(final PopupPositionProvider positionProvider, final TooltipState state, final CoroutineScope scope, final boolean focusable, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        PopupPositionProvider popupPositionProvider;
        boolean z;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-273292979);
        ComposerKt.sourceInformation($composer2, "C(TooltipPopup)P(2,4,3,1)136@5181L44,139@5314L109,145@5491L251,137@5230L512:BasicTooltip.android.kt#mqatfk");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            popupPositionProvider = positionProvider;
            $dirty |= $composer2.changed(popupPositionProvider) ? 4 : 2;
        } else {
            popupPositionProvider = positionProvider;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(scope) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            z = focusable;
            $dirty |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = focusable;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 16384 : 8192;
        }
        if (($dirty & 9363) != 9362 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-273292979, $dirty, -1, "androidx.compose.material3.internal.TooltipPopup (BasicTooltip.android.kt:135)");
            }
            boolean z2 = false;
            final String tooltipDescription = StringResources_androidKt.stringResource(R.string.tooltip_description, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 162917156, "CC(remember):BasicTooltip.android.kt#9igjgp");
            if (($dirty & 112) == 32 || (($dirty & 64) != 0 && $composer2.changedInstance(state))) {
                z2 = true;
            }
            boolean invalid$iv = z2 | $composer2.changedInstance(scope);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.internal.BasicTooltip_androidKt$TooltipPopup$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* compiled from: BasicTooltip.android.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$TooltipPopup$1$1$1", f = "BasicTooltip.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.internal.BasicTooltip_androidKt$TooltipPopup$1$1$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ TooltipState $state;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(TooltipState tooltipState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$state = tooltipState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$state, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure(obj);
                                    this.$state.dismiss();
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (TooltipState.this.getIsVisible()) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(TooltipState.this, null), 3, null);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AndroidPopup_androidKt.Popup(popupPositionProvider, (Function0) value$iv, new PopupProperties(z, false, false, false, 14, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(610617071, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.BasicTooltip_androidKt$TooltipPopup$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:29:0x018c  */
                /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r27, int r28) {
                    /*
                        Method dump skipped, instructions count: 400
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicTooltip_androidKt$TooltipPopup$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 3072, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.BasicTooltip_androidKt$TooltipPopup$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    BasicTooltip_androidKt.TooltipPopup(PopupPositionProvider.this, state, scope, focusable, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final Modifier handleGestures(Modifier $this$handleGestures, boolean enabled, TooltipState state) {
        if (enabled) {
            return SuspendingPointerInputFilterKt.pointerInput(SuspendingPointerInputFilterKt.pointerInput($this$handleGestures, state, new BasicTooltip_androidKt$handleGestures$1(state, null)), state, new BasicTooltip_androidKt$handleGestures$2(state, null));
        }
        return $this$handleGestures;
    }

    private static final Modifier anchorSemantics(Modifier $this$anchorSemantics, final String label, boolean enabled, final TooltipState state, final CoroutineScope scope) {
        if (enabled) {
            return SemanticsModifierKt.semantics($this$anchorSemantics, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.internal.BasicTooltip_androidKt$anchorSemantics$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    String str = label;
                    final CoroutineScope coroutineScope = scope;
                    final TooltipState tooltipState = state;
                    SemanticsPropertiesKt.onLongClick($this$semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.internal.BasicTooltip_androidKt$anchorSemantics$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* compiled from: BasicTooltip.android.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltip_androidKt$anchorSemantics$1$1$1", f = "BasicTooltip.android.kt", i = {}, l = {241}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: androidx.compose.material3.internal.BasicTooltip_androidKt$anchorSemantics$1$1$1, reason: invalid class name and collision with other inner class name */
                        static final class C00611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ TooltipState $state;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C00611(TooltipState tooltipState, Continuation<? super C00611> continuation) {
                                super(2, continuation);
                                this.$state = tooltipState;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new C00611(this.$state, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((C00611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        this.label = 1;
                                        if (TooltipState.show$default(this.$state, null, this, 1, null) != coroutine_suspended) {
                                            break;
                                        } else {
                                            return coroutine_suspended;
                                        }
                                    case 1:
                                        ResultKt.throwOnFailure($result);
                                        break;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new C00611(tooltipState, null), 3, null);
                            return true;
                        }
                    });
                }
            });
        }
        return $this$anchorSemantics;
    }
}
