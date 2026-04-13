package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.state.ToggleableState;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Checkbox.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ%\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0004\b$\u0010%J%\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0004\b'\u0010%J\u001d\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0004\b)\u0010*J\u0088\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u0013\u0010.\u001a\u00020!2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00100\u001a\u000201H\u0016R\u0019\u0010\n\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0014\u0010\u0011R\u0019\u0010\f\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0015\u0010\u0011R\u0019\u0010\u0007\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0016\u0010\u0011R\u0019\u0010\u000e\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0017\u0010\u0011R\u0019\u0010\t\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0018\u0010\u0011R\u0019\u0010\r\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0019\u0010\u0011R\u0019\u0010\b\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001a\u0010\u0011R\u0019\u0010\u000b\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001b\u0010\u0011R\u0019\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001c\u0010\u0011R\u0019\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001d\u0010\u0011\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00062"}, d2 = {"Landroidx/compose/material3/CheckboxColors;", "", "checkedCheckmarkColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedCheckmarkColor", "checkedBoxColor", "uncheckedBoxColor", "disabledCheckedBoxColor", "disabledUncheckedBoxColor", "disabledIndeterminateBoxColor", "checkedBorderColor", "uncheckedBorderColor", "disabledBorderColor", "disabledUncheckedBorderColor", "disabledIndeterminateBorderColor", "(JJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCheckedBorderColor-0d7_KjU", "()J", "J", "getCheckedBoxColor-0d7_KjU", "getCheckedCheckmarkColor-0d7_KjU", "getDisabledBorderColor-0d7_KjU", "getDisabledCheckedBoxColor-0d7_KjU", "getDisabledIndeterminateBorderColor-0d7_KjU", "getDisabledIndeterminateBoxColor-0d7_KjU", "getDisabledUncheckedBorderColor-0d7_KjU", "getDisabledUncheckedBoxColor-0d7_KjU", "getUncheckedBorderColor-0d7_KjU", "getUncheckedBoxColor-0d7_KjU", "getUncheckedCheckmarkColor-0d7_KjU", "borderColor", "Landroidx/compose/runtime/State;", "enabled", "", "state", "Landroidx/compose/ui/state/ToggleableState;", "borderColor$material3_release", "(ZLandroidx/compose/ui/state/ToggleableState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "boxColor", "boxColor$material3_release", "checkmarkColor", "checkmarkColor$material3_release", "(Landroidx/compose/ui/state/ToggleableState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "copy", "copy-2qZNXz8", "(JJJJJJJJJJJJ)Landroidx/compose/material3/CheckboxColors;", "equals", "other", "hashCode", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CheckboxColors {
    public static final int $stable = 0;
    private final long checkedBorderColor;
    private final long checkedBoxColor;
    private final long checkedCheckmarkColor;
    private final long disabledBorderColor;
    private final long disabledCheckedBoxColor;
    private final long disabledIndeterminateBorderColor;
    private final long disabledIndeterminateBoxColor;
    private final long disabledUncheckedBorderColor;
    private final long disabledUncheckedBoxColor;
    private final long uncheckedBorderColor;
    private final long uncheckedBoxColor;
    private final long uncheckedCheckmarkColor;

    /* compiled from: Checkbox.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ CheckboxColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12);
    }

    private CheckboxColors(long checkedCheckmarkColor, long uncheckedCheckmarkColor, long checkedBoxColor, long uncheckedBoxColor, long disabledCheckedBoxColor, long disabledUncheckedBoxColor, long disabledIndeterminateBoxColor, long checkedBorderColor, long uncheckedBorderColor, long disabledBorderColor, long disabledUncheckedBorderColor, long disabledIndeterminateBorderColor) {
        this.checkedCheckmarkColor = checkedCheckmarkColor;
        this.uncheckedCheckmarkColor = uncheckedCheckmarkColor;
        this.checkedBoxColor = checkedBoxColor;
        this.uncheckedBoxColor = uncheckedBoxColor;
        this.disabledCheckedBoxColor = disabledCheckedBoxColor;
        this.disabledUncheckedBoxColor = disabledUncheckedBoxColor;
        this.disabledIndeterminateBoxColor = disabledIndeterminateBoxColor;
        this.checkedBorderColor = checkedBorderColor;
        this.uncheckedBorderColor = uncheckedBorderColor;
        this.disabledBorderColor = disabledBorderColor;
        this.disabledUncheckedBorderColor = disabledUncheckedBorderColor;
        this.disabledIndeterminateBorderColor = disabledIndeterminateBorderColor;
    }

    /* renamed from: getCheckedCheckmarkColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedCheckmarkColor() {
        return this.checkedCheckmarkColor;
    }

    /* renamed from: getUncheckedCheckmarkColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedCheckmarkColor() {
        return this.uncheckedCheckmarkColor;
    }

    /* renamed from: getCheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedBoxColor() {
        return this.checkedBoxColor;
    }

    /* renamed from: getUncheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedBoxColor() {
        return this.uncheckedBoxColor;
    }

    /* renamed from: getDisabledCheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedBoxColor() {
        return this.disabledCheckedBoxColor;
    }

    /* renamed from: getDisabledUncheckedBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedBoxColor() {
        return this.disabledUncheckedBoxColor;
    }

    /* renamed from: getDisabledIndeterminateBoxColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIndeterminateBoxColor() {
        return this.disabledIndeterminateBoxColor;
    }

    /* renamed from: getCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedBorderColor() {
        return this.checkedBorderColor;
    }

    /* renamed from: getUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedBorderColor() {
        return this.uncheckedBorderColor;
    }

    /* renamed from: getDisabledBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledBorderColor() {
        return this.disabledBorderColor;
    }

    /* renamed from: getDisabledUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedBorderColor() {
        return this.disabledUncheckedBorderColor;
    }

    /* renamed from: getDisabledIndeterminateBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIndeterminateBorderColor() {
        return this.disabledIndeterminateBorderColor;
    }

    /* renamed from: copy-2qZNXz8$default, reason: not valid java name */
    public static /* synthetic */ CheckboxColors m1839copy2qZNXz8$default(CheckboxColors checkboxColors, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, int i, Object obj) {
        long j13;
        long j14;
        long j15 = (i & 1) != 0 ? checkboxColors.checkedCheckmarkColor : j;
        long j16 = (i & 2) != 0 ? checkboxColors.uncheckedCheckmarkColor : j2;
        long j17 = (i & 4) != 0 ? checkboxColors.checkedBoxColor : j3;
        long j18 = (i & 8) != 0 ? checkboxColors.uncheckedBoxColor : j4;
        long j19 = (i & 16) != 0 ? checkboxColors.disabledCheckedBoxColor : j5;
        long j20 = (i & 32) != 0 ? checkboxColors.disabledUncheckedBoxColor : j6;
        long j21 = (i & 64) != 0 ? checkboxColors.disabledIndeterminateBoxColor : j7;
        long j22 = j15;
        long j23 = (i & 128) != 0 ? checkboxColors.checkedBorderColor : j8;
        long j24 = (i & 256) != 0 ? checkboxColors.uncheckedBorderColor : j9;
        long j25 = (i & 512) != 0 ? checkboxColors.disabledBorderColor : j10;
        long j26 = (i & 1024) != 0 ? checkboxColors.disabledUncheckedBorderColor : j11;
        if ((i & 2048) != 0) {
            j13 = j26;
            j14 = checkboxColors.disabledIndeterminateBorderColor;
        } else {
            j13 = j26;
            j14 = j12;
        }
        return checkboxColors.m1840copy2qZNXz8(j22, j16, j17, j18, j19, j20, j21, j23, j24, j25, j13, j14);
    }

    /* renamed from: copy-2qZNXz8, reason: not valid java name */
    public final CheckboxColors m1840copy2qZNXz8(long checkedCheckmarkColor, long uncheckedCheckmarkColor, long checkedBoxColor, long uncheckedBoxColor, long disabledCheckedBoxColor, long disabledUncheckedBoxColor, long disabledIndeterminateBoxColor, long checkedBorderColor, long uncheckedBorderColor, long disabledBorderColor, long disabledUncheckedBorderColor, long disabledIndeterminateBorderColor) {
        return new CheckboxColors((checkedCheckmarkColor > 16L ? 1 : (checkedCheckmarkColor == 16L ? 0 : -1)) != 0 ? checkedCheckmarkColor : this.checkedCheckmarkColor, (uncheckedCheckmarkColor > 16L ? 1 : (uncheckedCheckmarkColor == 16L ? 0 : -1)) != 0 ? uncheckedCheckmarkColor : this.uncheckedCheckmarkColor, (checkedBoxColor > 16L ? 1 : (checkedBoxColor == 16L ? 0 : -1)) != 0 ? checkedBoxColor : this.checkedBoxColor, (uncheckedBoxColor > 16L ? 1 : (uncheckedBoxColor == 16L ? 0 : -1)) != 0 ? uncheckedBoxColor : this.uncheckedBoxColor, (disabledCheckedBoxColor > 16L ? 1 : (disabledCheckedBoxColor == 16L ? 0 : -1)) != 0 ? disabledCheckedBoxColor : this.disabledCheckedBoxColor, (disabledUncheckedBoxColor > 16L ? 1 : (disabledUncheckedBoxColor == 16L ? 0 : -1)) != 0 ? disabledUncheckedBoxColor : this.disabledUncheckedBoxColor, (disabledIndeterminateBoxColor > 16L ? 1 : (disabledIndeterminateBoxColor == 16L ? 0 : -1)) != 0 ? disabledIndeterminateBoxColor : this.disabledIndeterminateBoxColor, (checkedBorderColor > 16L ? 1 : (checkedBorderColor == 16L ? 0 : -1)) != 0 ? checkedBorderColor : this.checkedBorderColor, (uncheckedBorderColor > 16L ? 1 : (uncheckedBorderColor == 16L ? 0 : -1)) != 0 ? uncheckedBorderColor : this.uncheckedBorderColor, (disabledBorderColor > 16L ? 1 : (disabledBorderColor == 16L ? 0 : -1)) != 0 ? disabledBorderColor : this.disabledBorderColor, (disabledUncheckedBorderColor > 16L ? 1 : (disabledUncheckedBorderColor == 16L ? 0 : -1)) != 0 ? disabledUncheckedBorderColor : this.disabledUncheckedBorderColor, disabledIndeterminateBorderColor != 16 ? disabledIndeterminateBorderColor : this.disabledIndeterminateBorderColor, null);
    }

    public final State<Color> checkmarkColor$material3_release(ToggleableState state, Composer $composer, int $changed) {
        long j;
        ComposerKt.sourceInformationMarkerStart($composer, -507585681, "C(checkmarkColor)490@21677L61:Checkbox.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-507585681, $changed, -1, "androidx.compose.material3.CheckboxColors.checkmarkColor (Checkbox.kt:481)");
        }
        if (state == ToggleableState.Off) {
            j = this.uncheckedCheckmarkColor;
        } else {
            j = this.checkedCheckmarkColor;
        }
        long target = j;
        int duration = state == ToggleableState.Off ? 100 : 50;
        State<Color> m106animateColorAsStateeuL9pac = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(target, AnimationSpecKt.tween$default(duration, 0, null, 6, null), null, null, $composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m106animateColorAsStateeuL9pac;
    }

    public final State<Color> boxColor$material3_release(boolean enabled, ToggleableState state, Composer $composer, int $changed) {
        long j;
        Composer $composer2;
        State<Color> rememberUpdatedState;
        ComposerKt.sourceInformationMarkerStart($composer, 360729865, "C(boxColor):Checkbox.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(360729865, $changed, -1, "androidx.compose.material3.CheckboxColors.boxColor (Checkbox.kt:501)");
        }
        if (enabled) {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                case 2:
                    j = this.checkedBoxColor;
                    break;
                case 3:
                    j = this.uncheckedBoxColor;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                    j = this.disabledCheckedBoxColor;
                    break;
                case 2:
                    j = this.disabledIndeterminateBoxColor;
                    break;
                case 3:
                    j = this.disabledUncheckedBoxColor;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        long target = j;
        if (enabled) {
            $composer.startReplaceGroup(-392211906);
            ComposerKt.sourceInformation($composer, "521@22941L61");
            int duration = state == ToggleableState.Off ? 100 : 50;
            $composer2 = $composer;
            rememberUpdatedState = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(target, AnimationSpecKt.tween$default(duration, 0, null, 6, null), null, null, $composer2, 0, 12);
            $composer2.endReplaceGroup();
        } else {
            $composer2 = $composer;
            $composer2.startReplaceGroup(-392031362);
            ComposerKt.sourceInformation($composer2, "523@23032L28");
            rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m4153boximpl(target), $composer2, 0);
            $composer2.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return rememberUpdatedState;
    }

    public final State<Color> borderColor$material3_release(boolean enabled, ToggleableState state, Composer $composer, int $changed) {
        long j;
        Composer $composer2;
        State<Color> rememberUpdatedState;
        ComposerKt.sourceInformationMarkerStart($composer, 1009643462, "C(borderColor):Checkbox.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1009643462, $changed, -1, "androidx.compose.material3.CheckboxColors.borderColor (Checkbox.kt:534)");
        }
        if (enabled) {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                case 2:
                    j = this.checkedBorderColor;
                    break;
                case 3:
                    j = this.uncheckedBorderColor;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                    j = this.disabledBorderColor;
                    break;
                case 2:
                    j = this.disabledIndeterminateBorderColor;
                    break;
                case 3:
                    j = this.disabledUncheckedBorderColor;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        long target = j;
        if (enabled) {
            $composer.startReplaceGroup(-1725816497);
            ComposerKt.sourceInformation($composer, "554@24267L61");
            int duration = state == ToggleableState.Off ? 100 : 50;
            $composer2 = $composer;
            rememberUpdatedState = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(target, AnimationSpecKt.tween$default(duration, 0, null, 6, null), null, null, $composer2, 0, 12);
            $composer2.endReplaceGroup();
        } else {
            $composer2 = $composer;
            $composer2.startReplaceGroup(-1725635953);
            ComposerKt.sourceInformation($composer2, "556@24358L28");
            rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m4153boximpl(target), $composer2, 0);
            $composer2.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return rememberUpdatedState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof CheckboxColors) && Color.m4164equalsimpl0(this.checkedCheckmarkColor, ((CheckboxColors) other).checkedCheckmarkColor) && Color.m4164equalsimpl0(this.uncheckedCheckmarkColor, ((CheckboxColors) other).uncheckedCheckmarkColor) && Color.m4164equalsimpl0(this.checkedBoxColor, ((CheckboxColors) other).checkedBoxColor) && Color.m4164equalsimpl0(this.uncheckedBoxColor, ((CheckboxColors) other).uncheckedBoxColor) && Color.m4164equalsimpl0(this.disabledCheckedBoxColor, ((CheckboxColors) other).disabledCheckedBoxColor) && Color.m4164equalsimpl0(this.disabledUncheckedBoxColor, ((CheckboxColors) other).disabledUncheckedBoxColor) && Color.m4164equalsimpl0(this.disabledIndeterminateBoxColor, ((CheckboxColors) other).disabledIndeterminateBoxColor) && Color.m4164equalsimpl0(this.checkedBorderColor, ((CheckboxColors) other).checkedBorderColor) && Color.m4164equalsimpl0(this.uncheckedBorderColor, ((CheckboxColors) other).uncheckedBorderColor) && Color.m4164equalsimpl0(this.disabledBorderColor, ((CheckboxColors) other).disabledBorderColor) && Color.m4164equalsimpl0(this.disabledUncheckedBorderColor, ((CheckboxColors) other).disabledUncheckedBorderColor) && Color.m4164equalsimpl0(this.disabledIndeterminateBorderColor, ((CheckboxColors) other).disabledIndeterminateBorderColor)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Color.m4170hashCodeimpl(this.checkedCheckmarkColor);
        return (((((((((((((((((((((result * 31) + Color.m4170hashCodeimpl(this.uncheckedCheckmarkColor)) * 31) + Color.m4170hashCodeimpl(this.checkedBoxColor)) * 31) + Color.m4170hashCodeimpl(this.uncheckedBoxColor)) * 31) + Color.m4170hashCodeimpl(this.disabledCheckedBoxColor)) * 31) + Color.m4170hashCodeimpl(this.disabledUncheckedBoxColor)) * 31) + Color.m4170hashCodeimpl(this.disabledIndeterminateBoxColor)) * 31) + Color.m4170hashCodeimpl(this.checkedBorderColor)) * 31) + Color.m4170hashCodeimpl(this.uncheckedBorderColor)) * 31) + Color.m4170hashCodeimpl(this.disabledBorderColor)) * 31) + Color.m4170hashCodeimpl(this.disabledUncheckedBorderColor)) * 31) + Color.m4170hashCodeimpl(this.disabledIndeterminateBorderColor);
    }
}
