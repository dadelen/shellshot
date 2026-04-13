package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.IntSize;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/* compiled from: AnimatedContent.kt */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a´\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u0017\u001aP\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2>\b\u0002\u0010\u001c\u001a8\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0 0\u0014\u001a¬\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020!2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\"\u001a\u0015\u0010#\u001a\u00020\t*\u00020$2\u0006\u0010%\u001a\u00020&H\u0086\u0004\u001a\u0015\u0010'\u001a\u00020\t*\u00020$2\u0006\u0010%\u001a\u00020&H\u0087\u0004¨\u0006("}, d2 = {"AnimatedContent", "", "S", "targetState", "modifier", "Landroidx/compose/ui/Modifier;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/animation/ContentTransform;", "Lkotlin/ExtensionFunctionType;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "label", "", "contentKey", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "", "content", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "SizeTransform", "Landroidx/compose/animation/SizeTransform;", "clip", "", "sizeAnimationSpec", "Landroidx/compose/ui/unit/IntSize;", "initialSize", "targetSize", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "togetherWith", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "with", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimatedContentKt {
    public static final <S> void AnimatedContent(final S s, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment contentAlignment, String label, Function1<? super S, ? extends Object> function12, final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function1 transitionSpec;
        Alignment contentAlignment2;
        String str;
        Function1 contentKey;
        Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function42;
        Modifier modifier3;
        final String label2;
        final Function1 transitionSpec2;
        final Alignment contentAlignment3;
        final Function1 contentKey2;
        Composer $composer2 = $composer.startRestartGroup(2132720749);
        ComposerKt.sourceInformation($composer2, "C(AnimatedContent)P(5,4,6,1,3,2)141@7507L58,142@7581L136:AnimatedContent.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(s) : $composer2.changedInstance(s) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            transitionSpec = function1;
        } else if (($changed & 384) == 0) {
            transitionSpec = function1;
            $dirty |= $composer2.changedInstance(transitionSpec) ? 256 : 128;
        } else {
            transitionSpec = function1;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            contentAlignment2 = contentAlignment;
        } else if (($changed & 3072) == 0) {
            contentAlignment2 = contentAlignment;
            $dirty |= $composer2.changed(contentAlignment2) ? 2048 : 1024;
        } else {
            contentAlignment2 = contentAlignment;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            str = label;
        } else if (($changed & 24576) == 0) {
            str = label;
            $dirty |= $composer2.changed(str) ? 16384 : 8192;
        } else {
            str = label;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            contentKey = function12;
        } else if ((196608 & $changed) == 0) {
            contentKey = function12;
            $dirty |= $composer2.changedInstance(contentKey) ? 131072 : 65536;
        } else {
            contentKey = function12;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
            function42 = function4;
        } else if (($changed & 1572864) == 0) {
            function42 = function4;
            $dirty |= $composer2.changedInstance(function42) ? 1048576 : 524288;
        } else {
            function42 = function4;
        }
        if (($dirty & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            transitionSpec2 = transitionSpec;
            contentAlignment3 = contentAlignment2;
            label2 = str;
            contentKey2 = contentKey;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                transitionSpec = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$1
                    @Override // kotlin.jvm.functions.Function1
                    public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m67scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                    }
                };
            }
            if (i4 != 0) {
                contentAlignment2 = Alignment.INSTANCE.getTopStart();
            }
            String label3 = i5 != 0 ? "AnimatedContent" : str;
            if (i6 != 0) {
                contentKey = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$2
                    @Override // kotlin.jvm.functions.Function1
                    public final S invoke(S s2) {
                        return s2;
                    }
                };
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2132720749, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:140)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(s, label3, $composer2, ($dirty & 8) | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            modifier3 = modifier4;
            Function1 transitionSpec3 = transitionSpec;
            Alignment contentAlignment4 = contentAlignment2;
            AnimatedContent(transition, modifier3, transitionSpec3, contentAlignment4, contentKey, function42, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (458752 & ($dirty >> 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            transitionSpec2 = transitionSpec3;
            contentAlignment3 = contentAlignment4;
            contentKey2 = contentKey;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$3
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedContentKt.AnimatedContent(s, modifier5, transitionSpec2, contentAlignment3, label2, contentKey2, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static /* synthetic */ SizeTransform SizeTransform$default(boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            function2 = new Function2<IntSize, IntSize, SpringSpec<IntSize>>() { // from class: androidx.compose.animation.AnimatedContentKt$SizeTransform$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ SpringSpec<IntSize> invoke(IntSize intSize, IntSize intSize2) {
                    return m31invokeTemP2vQ(intSize.getPackedValue(), intSize2.getPackedValue());
                }

                /* renamed from: invoke-TemP2vQ, reason: not valid java name */
                public final SpringSpec<IntSize> m31invokeTemP2vQ(long j, long j2) {
                    return AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m6788boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
                }
            };
        }
        return SizeTransform(z, function2);
    }

    public static final SizeTransform SizeTransform(boolean clip, Function2<? super IntSize, ? super IntSize, ? extends FiniteAnimationSpec<IntSize>> function2) {
        return new SizeTransformImpl(clip, function2);
    }

    public static final ContentTransform togetherWith(EnterTransition $this$togetherWith, ExitTransition exit) {
        return new ContentTransform($this$togetherWith, exit, 0.0f, null, 12, null);
    }

    @Deprecated(message = "Infix fun EnterTransition.with(ExitTransition) has been renamed to togetherWith", replaceWith = @ReplaceWith(expression = "togetherWith(exit)", imports = {}))
    public static final ContentTransform with(EnterTransition $this$with, ExitTransition exit) {
        return new ContentTransform($this$with, exit, 0.0f, null, 12, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02d5 A[LOOP:2: B:140:0x02d3->B:141:0x02d5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0280 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <S> void AnimatedContent(final androidx.compose.animation.core.Transition<S> r29, androidx.compose.ui.Modifier r30, kotlin.jvm.functions.Function1<? super androidx.compose.animation.AnimatedContentTransitionScope<S>, androidx.compose.animation.ContentTransform> r31, androidx.compose.ui.Alignment r32, kotlin.jvm.functions.Function1<? super S, ? extends java.lang.Object> r33, final kotlin.jvm.functions.Function4<? super androidx.compose.animation.AnimatedContentScope, ? super S, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, androidx.compose.runtime.Composer r35, final int r36, final int r37) {
        /*
            Method dump skipped, instructions count: 1295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt.AnimatedContent(androidx.compose.animation.core.Transition, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, androidx.compose.ui.Alignment, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int):void");
    }
}
