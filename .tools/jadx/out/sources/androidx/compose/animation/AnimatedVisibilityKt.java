package androidx.compose.animation;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: AnimatedVisibility.kt */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0091\u0001\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0001¢\u0006\u0002\u0010\u001a\u001aa\u0010\u001b\u001a\u00020\u00072\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u001d2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010 \u001a[\u0010\u001b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010!\u001ak\u0010\"\u001a\u00020\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0001¢\u0006\u0002\u0010#\u001am\u0010\u001b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010$\u001ae\u0010\u001b\u001a\u00020\u0007*\u00020%2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u001d2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010&\u001a_\u0010\u001b\u001a\u00020\u0007*\u00020%2\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010'\u001ae\u0010\u001b\u001a\u00020\u0007*\u00020(2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u001d2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010)\u001a_\u0010\u001b\u001a\u00020\u0007*\u00020(2\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010*\u001a9\u0010+\u001a\u00020\u0003\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010,\u001a\u0002H\bH\u0003¢\u0006\u0002\u0010-\"\u001e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006.²\u0006\"\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0013\"\u0004\b\u0000\u0010\bX\u008a\u0084\u0002²\u0006\u0010\u00100\u001a\u00020\u0001\"\u0004\b\u0000\u0010\bX\u008a\u0084\u0002"}, d2 = {"exitFinished", "", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "getExitFinished", "(Landroidx/compose/animation/core/Transition;)Z", "AnimatedEnterExitImpl", "", "T", "transition", "visible", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "shouldDisposeBlock", "Lkotlin/Function2;", "onLookaheadMeasured", "Landroidx/compose/animation/OnLookaheadMeasured;", "content", "Landroidx/compose/animation/AnimatedVisibilityScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function2;Landroidx/compose/animation/OnLookaheadMeasured;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AnimatedVisibility", "visibleState", "Landroidx/compose/animation/core/MutableTransitionState;", "label", "", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AnimatedVisibilityImpl", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/ColumnScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/RowScope;", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/RowScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "targetEnterExit", "targetState", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterExitState;", "animation_release", "shouldDisposeBlockUpdated", "shouldDisposeAfterExit"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimatedVisibilityKt {
    public static final void AnimatedVisibility(boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        String str;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        Modifier modifier3;
        final String label2;
        final EnterTransition enter2;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(2088733774);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)132@6967L32,133@7004L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            z = visible;
        } else if (($changed & 6) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = visible;
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
            enterTransition = enter;
        } else if (($changed & 384) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            exitTransition = exit;
        } else if (($changed & 3072) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
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
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function32 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter2 = enterTransition;
            exit2 = exitTransition;
            label2 = str;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            EnterTransition enter3 = i3 != 0 ? EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null)) : enterTransition;
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)) : exitTransition;
            String label3 = i5 != 0 ? "AnimatedVisibility" : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2088733774, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:131)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, ($dirty & 14) | (($dirty >> 9) & 112), 0);
            EnterTransition enter4 = enter3;
            modifier3 = modifier4;
            AnimatedVisibilityImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$1
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter4, exit3, function32, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            enter2 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final boolean z2 = z;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$2
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility(z2, modifier5, enter2, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final boolean z;
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        String str;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final String label2;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(-1741346906);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)207@11272L32,208@11309L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
            z = visible;
        } else if (($changed & 48) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 32 : 16;
        } else {
            z = visible;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            str = label;
        } else if ((196608 & $changed) == 0) {
            str = label;
            $dirty |= $composer2.changed(str) ? 131072 : 65536;
        } else {
            str = label;
        }
        if ((i & 32) != 0) {
            $dirty |= 1572864;
            function32 = function3;
        } else if (($changed & 1572864) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if ((599185 & $dirty) == 599184 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = str;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null));
            }
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null)) : exitTransition;
            String label3 = i5 != 0 ? "AnimatedVisibility" : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1741346906, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:206)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            Modifier modifier5 = modifier4;
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$3
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier5, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier5;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$4
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility(RowScope.this, z, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final boolean z;
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        String str;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final String label2;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(1766503102);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)280@15555L32,281@15592L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
            z = visible;
        } else if (($changed & 48) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 32 : 16;
        } else {
            z = visible;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            str = label;
        } else if ((196608 & $changed) == 0) {
            str = label;
            $dirty |= $composer2.changed(str) ? 131072 : 65536;
        } else {
            str = label;
        }
        if ((i & 32) != 0) {
            $dirty |= 1572864;
            function32 = function3;
        } else if (($changed & 1572864) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if ((599185 & $dirty) == 599184 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = str;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null));
            }
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null)) : exitTransition;
            String label3 = i5 != 0 ? "AnimatedVisibility" : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1766503102, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:279)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            Modifier modifier5 = modifier4;
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$5
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier5, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier5;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$6
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility(ColumnScope.this, z, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        String str;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        Modifier modifier3;
        final String label2;
        final EnterTransition enter2;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(-222898426);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)385@20955L39,386@20999L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 4 : 2;
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
            enterTransition = enter;
        } else if (($changed & 384) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            exitTransition = exit;
        } else if (($changed & 3072) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
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
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function32 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter2 = enterTransition;
            exit2 = exitTransition;
            label2 = str;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            EnterTransition enter3 = i3 != 0 ? EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null)) : enterTransition;
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null)) : exitTransition;
            String label3 = i5 != 0 ? "AnimatedVisibility" : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-222898426, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:384)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            EnterTransition enter4 = enter3;
            modifier3 = modifier4;
            AnimatedVisibilityImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$7
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter4, exit3, function32, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            enter2 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$8
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility(mutableTransitionState, modifier5, enter2, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        String str;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final String label2;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(836509870);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)460@25318L39,461@25362L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 32 : 16;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            str = label;
        } else if ((196608 & $changed) == 0) {
            str = label;
            $dirty |= $composer2.changed(str) ? 131072 : 65536;
        } else {
            str = label;
        }
        if ((i & 32) != 0) {
            $dirty |= 1572864;
            function32 = function3;
        } else if (($changed & 1572864) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if ((599185 & $dirty) == 599184 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = str;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            }
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)) : exitTransition;
            String label3 = i5 != 0 ? "AnimatedVisibility" : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(836509870, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:459)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            Modifier modifier5 = modifier4;
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$9
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier5, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier5;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$10
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility(RowScope.this, mutableTransitionState, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        String str;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        final String label2;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(-850656618);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)536@29773L39,537@29817L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 32 : 16;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            str = label;
        } else if ((196608 & $changed) == 0) {
            str = label;
            $dirty |= $composer2.changed(str) ? 131072 : 65536;
        } else {
            str = label;
        }
        if ((i & 32) != 0) {
            $dirty |= 1572864;
            function32 = function3;
        } else if (($changed & 1572864) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        } else {
            function32 = function3;
        }
        if ((599185 & $dirty) == 599184 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = str;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            }
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)) : exitTransition;
            String label3 = i5 != 0 ? "AnimatedVisibility" : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-850656618, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:535)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            Modifier modifier5 = modifier4;
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$11
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier5, enter4, exit3, function32, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier5;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$12
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

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility(ColumnScope.this, mutableTransitionState, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final <T> void AnimatedVisibility(final Transition<T> transition, final Function1<? super T, Boolean> function1, Modifier modifier, EnterTransition enter, ExitTransition exit, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Transition<T> transition2;
        Function1<? super T, Boolean> function12;
        final Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function32;
        Modifier.Companion modifier3;
        EnterTransition enter2;
        ExitTransition exit2;
        final EnterTransition enter3;
        final ExitTransition exit3;
        Composer $composer2 = $composer.startRestartGroup(1031950689);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(4,3,1,2)609@34120L79:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & Integer.MIN_VALUE) != 0) {
            $dirty |= 6;
            transition2 = transition;
        } else if (($changed & 6) == 0) {
            transition2 = transition;
            $dirty |= $composer2.changed(transition2) ? 4 : 2;
        } else {
            transition2 = transition;
        }
        if ((i & 1) != 0) {
            $dirty |= 48;
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enterTransition = enter;
        } else if (($changed & 3072) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        if ((i & 16) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function32 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            enter3 = enterTransition;
            exit3 = exitTransition;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i3 == 0) {
                enter2 = enterTransition;
            } else {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            }
            if (i4 != 0) {
                exit2 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit2 = exitTransition;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1031950689, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:609)");
            }
            Modifier modifier4 = modifier3;
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition2, function12, modifier4, enter4, exit2, function32, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            enter3 = enter4;
            exit3 = exit2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$13
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

                public final void invoke(Composer composer, int i5) {
                    AnimatedVisibilityKt.AnimatedVisibility(transition, function1, modifier2, enter3, exit3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final <T> void AnimatedVisibilityImpl(final Transition<T> transition, final Function1<? super T, Boolean> function1, final Modifier modifier, final EnterTransition enter, final ExitTransition exit, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        int i;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(429978603);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibilityImpl)P(4,5,3,1,2)697@38299L415,694@38181L703:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(transition) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) != 74898 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(429978603, $dirty2, -1, "androidx.compose.animation.AnimatedVisibilityImpl (AnimatedVisibility.kt:693)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 689212639, "CC(remember):AnimatedVisibility.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 112) == 32) | (($dirty2 & 14) == 4);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                i = 196608;
                value$iv = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                        return m61invoke3p2s80s(measureScope, measurable, constraints.getValue());
                    }

                    /* renamed from: invoke-3p2s80s, reason: not valid java name */
                    public final MeasureResult m61invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long constraints) {
                        long IntSize;
                        final Placeable placeable = measurable.mo5491measureBRTryo0(constraints);
                        if ($this$layout.isLookingAhead() && !function1.invoke(transition.getTargetState()).booleanValue()) {
                            IntSize = IntSize.INSTANCE.m6801getZeroYbymL2g();
                        } else {
                            IntSize = IntSizeKt.IntSize(placeable.getWidth(), placeable.getHeight());
                        }
                        int m6796getWidthimpl = IntSize.m6796getWidthimpl(IntSize);
                        int h = IntSize.m6795getHeightimpl(IntSize);
                        return MeasureScope.layout$default($this$layout, m6796getWidthimpl, h, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$1$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                Placeable.PlacementScope.place$default($this$layout2, Placeable.this, 0, 0, 0.0f, 4, null);
                            }
                        }, 4, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                i = 196608;
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedEnterExitImpl(transition, function1, LayoutModifierKt.layout(modifier, (Function3) value$iv), enterTransition, exitTransition, new Function2<EnterExitState, EnterExitState, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$2
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(EnterExitState current, EnterExitState target) {
                    return Boolean.valueOf(current == target && target == EnterExitState.PostExit);
                }
            }, null, function3, $composer2, ($dirty2 & 14) | i | ($dirty2 & 112) | ($dirty2 & 7168) | (57344 & $dirty2) | (29360128 & ($dirty2 << 6)), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$3
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

                public final void invoke(Composer composer, int i2) {
                    AnimatedVisibilityKt.AnimatedVisibilityImpl(transition, function1, modifier, enter, exit, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0480 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0309  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> void AnimatedEnterExitImpl(final androidx.compose.animation.core.Transition<T> r26, final kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> r27, final androidx.compose.ui.Modifier r28, final androidx.compose.animation.EnterTransition r29, final androidx.compose.animation.ExitTransition r30, final kotlin.jvm.functions.Function2<? super androidx.compose.animation.EnterExitState, ? super androidx.compose.animation.EnterExitState, java.lang.Boolean> r31, androidx.compose.animation.OnLookaheadMeasured r32, final kotlin.jvm.functions.Function3<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 1274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedEnterExitImpl(androidx.compose.animation.core.Transition, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, kotlin.jvm.functions.Function2, androidx.compose.animation.OnLookaheadMeasured, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<EnterExitState, EnterExitState, Boolean> AnimatedEnterExitImpl$lambda$2(State<? extends Function2<? super EnterExitState, ? super EnterExitState, Boolean>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    private static final boolean AnimatedEnterExitImpl$lambda$4(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getExitFinished(Transition<EnterExitState> transition) {
        return transition.getCurrentState() == EnterExitState.PostExit && transition.getTargetState() == EnterExitState.PostExit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> EnterExitState targetEnterExit(Transition<T> transition, Function1<? super T, Boolean> function1, T t, Composer $composer, int $changed) {
        Object value$iv;
        EnterExitState enterExitState;
        ComposerKt.sourceInformationMarkerStart($composer, 361571134, "C(targetEnterExit)P(1):AnimatedVisibility.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(361571134, $changed, -1, "androidx.compose.animation.targetEnterExit (AnimatedVisibility.kt:855)");
        }
        $composer.startMovableGroup(-902048200, transition);
        ComposerKt.sourceInformation($composer, "");
        if (transition.isSeeking()) {
            $composer.startReplaceGroup(2101296683);
            $composer.endReplaceGroup();
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
        } else {
            $composer.startReplaceGroup(2101530516);
            ComposerKt.sourceInformation($composer, "868@44365L34");
            ComposerKt.sourceInformationMarkerStart($composer, -902039492, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MutableState hasBeenVisible = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                hasBeenVisible.setValue(true);
            }
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (((Boolean) hasBeenVisible.getValue()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
            $composer.endReplaceGroup();
        }
        $composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterExitState;
    }
}
