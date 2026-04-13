package androidx.compose.material;

import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: SwipeToDismiss.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0081\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00040\r¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00040\r¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u0014\u001a\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u001a-\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u00172\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001c0\rH\u0007¢\u0006\u0002\u0010\u001d\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006\u001e"}, d2 = {"DISMISS_THRESHOLD", "Landroidx/compose/ui/unit/Dp;", "F", "SwipeToDismiss", "", "state", "Landroidx/compose/material/DismissState;", "modifier", "Landroidx/compose/ui/Modifier;", "directions", "", "Landroidx/compose/material/DismissDirection;", "dismissThresholds", "Lkotlin/Function1;", "Landroidx/compose/material/ThresholdConfig;", "background", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "dismissContent", "(Landroidx/compose/material/DismissState;Landroidx/compose/ui/Modifier;Ljava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "getDismissDirection", "from", "Landroidx/compose/material/DismissValue;", "to", "rememberDismissState", "initialValue", "confirmStateChange", "", "(Landroidx/compose/material/DismissValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/DismissState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SwipeToDismissKt {
    private static final float DISMISS_THRESHOLD = Dp.m6626constructorimpl(56);

    public static final DismissState rememberDismissState(final DismissValue initialValue, final Function1<? super DismissValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1753522702, "C(rememberDismissState)P(1)153@5472L62,153@5407L127:SwipeToDismiss.kt#jmzs0o");
        if ((i & 1) != 0) {
            initialValue = DismissValue.Default;
        }
        if ((i & 2) != 0) {
            Function1 confirmStateChange = new Function1<DismissValue, Boolean>() { // from class: androidx.compose.material.SwipeToDismissKt$rememberDismissState$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DismissValue it) {
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1753522702, $changed, -1, "androidx.compose.material.rememberDismissState (SwipeToDismiss.kt:152)");
        }
        Object[] objArr = new Object[0];
        Saver<DismissState, DismissValue> Saver = DismissState.INSTANCE.Saver(function1);
        ComposerKt.sourceInformationMarkerStart($composer, 1006276056, "CC(remember):SwipeToDismiss.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue)) || ($changed & 6) == 4);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<DismissState>() { // from class: androidx.compose.material.SwipeToDismissKt$rememberDismissState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DismissState invoke() {
                    return new DismissState(DismissValue.this, function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        DismissState dismissState = (DismissState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return dismissState;
    }

    public static final void SwipeToDismiss(final DismissState state, Modifier modifier, Set<? extends DismissDirection> set, Function1<? super DismissDirection, ? extends ThresholdConfig> function1, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function32, Composer $composer, final int $changed, final int i) {
        DismissState dismissState;
        Modifier modifier2;
        Set set2;
        Function1 function12;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function33;
        final Set directions;
        final Modifier modifier3;
        final Function1 dismissThresholds;
        Composer $composer2 = $composer.startRestartGroup(634380143);
        ComposerKt.sourceInformation($composer2, "C(SwipeToDismiss)P(5,4,1,3)183@6690L1378,183@6661L1407:SwipeToDismiss.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            dismissState = state;
        } else if (($changed & 6) == 0) {
            dismissState = state;
            $dirty |= $composer2.changed(dismissState) ? 4 : 2;
        } else {
            dismissState = state;
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
            set2 = set;
        } else if (($changed & 384) == 0) {
            set2 = set;
            $dirty |= $composer2.changedInstance(set2) ? 256 : 128;
        } else {
            set2 = set;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            function12 = function1;
        } else if (($changed & 3072) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        } else {
            function12 = function1;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
            function33 = function3;
        } else if (($changed & 24576) == 0) {
            function33 = function3;
            $dirty |= $composer2.changedInstance(function33) ? 16384 : 8192;
        } else {
            function33 = function3;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            directions = set2;
            dismissThresholds = function12;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            final Set directions2 = i3 != 0 ? SetsKt.setOf((Object[]) new DismissDirection[]{DismissDirection.EndToStart, DismissDirection.StartToEnd}) : set2;
            final Function1 dismissThresholds2 = i4 != 0 ? new Function1<DismissDirection, FixedThreshold>() { // from class: androidx.compose.material.SwipeToDismissKt$SwipeToDismiss$1
                @Override // kotlin.jvm.functions.Function1
                public final FixedThreshold invoke(DismissDirection it) {
                    float f;
                    f = SwipeToDismissKt.DISMISS_THRESHOLD;
                    return new FixedThreshold(f, null);
                }
            } : function12;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(634380143, $dirty, -1, "androidx.compose.material.SwipeToDismiss (SwipeToDismiss.kt:183)");
            }
            final DismissState dismissState2 = dismissState;
            final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function34 = function33;
            directions = directions2;
            Modifier modifier5 = modifier4;
            BoxWithConstraintsKt.BoxWithConstraints(modifier5, null, false, ComposableLambdaKt.rememberComposableLambda(338007641, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.SwipeToDismissKt$SwipeToDismiss$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
                    invoke(boxWithConstraintsScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:53:0x02d7  */
                /* JADX WARN: Removed duplicated region for block: B:56:0x02e3  */
                /* JADX WARN: Removed duplicated region for block: B:64:0x039f  */
                /* JADX WARN: Removed duplicated region for block: B:69:0x042a  */
                /* JADX WARN: Removed duplicated region for block: B:72:0x0436  */
                /* JADX WARN: Removed duplicated region for block: B:75:0x046d  */
                /* JADX WARN: Removed duplicated region for block: B:80:0x04f1  */
                /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:84:0x0483  */
                /* JADX WARN: Removed duplicated region for block: B:85:0x043c  */
                /* JADX WARN: Removed duplicated region for block: B:87:0x03ac A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:90:0x02e9  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope r62, androidx.compose.runtime.Composer r63, int r64) {
                    /*
                        Method dump skipped, instructions count: 1269
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwipeToDismissKt$SwipeToDismiss$2.invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, (($dirty >> 3) & 14) | 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            dismissThresholds = dismissThresholds2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SwipeToDismissKt$SwipeToDismiss$3
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
                    SwipeToDismissKt.SwipeToDismiss(DismissState.this, modifier3, directions, dismissThresholds, function3, function32, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DismissDirection getDismissDirection(DismissValue from, DismissValue to) {
        if (from == to && from == DismissValue.Default) {
            return null;
        }
        if (from == to && from == DismissValue.DismissedToEnd) {
            return DismissDirection.StartToEnd;
        }
        if (from == to && from == DismissValue.DismissedToStart) {
            return DismissDirection.EndToStart;
        }
        if (from == DismissValue.Default && to == DismissValue.DismissedToEnd) {
            return DismissDirection.StartToEnd;
        }
        if (from == DismissValue.Default && to == DismissValue.DismissedToStart) {
            return DismissDirection.EndToStart;
        }
        if (from == DismissValue.DismissedToEnd && to == DismissValue.Default) {
            return DismissDirection.StartToEnd;
        }
        if (from == DismissValue.DismissedToStart && to == DismissValue.Default) {
            return DismissDirection.EndToStart;
        }
        return null;
    }
}
