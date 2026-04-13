package com.example.shellshot.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: LiquidGlassSwitch.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001aS\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\f\u0010\r\u001aS\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\u0011\u0010\r\u001a\r\u0010\u0012\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0013\u001a\r\u0010\u0014\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0013¨\u0006\u0015²\u0006\n\u0010\u0016\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0017\u001a\u00020\nX\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\nX\u008a\u0084\u0002²\u0006\n\u0010\u0019\u001a\u00020\nX\u008a\u0084\u0002²\u0006\n\u0010\u001a\u001a\u00020\u001bX\u008a\u0084\u0002²\u0006\n\u0010\u001c\u001a\u00020\u001bX\u008a\u0084\u0002²\u0006\n\u0010\u001d\u001a\u00020\u001eX\u008a\u0084\u0002²\u0006\n\u0010\u001f\u001a\u00020\u001eX\u008a\u0084\u0002²\u0006\n\u0010\u0002\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"LiquidGlassSwitch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "width", "Landroidx/compose/ui/unit/Dp;", "height", "LiquidGlassSwitch-AGcomas", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZFFLandroidx/compose/runtime/Composer;II)V", "StatefulLiquidGlassSwitch", "initialChecked", "onChange", "StatefulLiquidGlassSwitch-AGcomas", "LiquidGlassSwitchPreview", "(Landroidx/compose/runtime/Composer;I)V", "LiquidGlassSwitchDemoPreview", "app_debug", "isAnimating", "thumbOffset", "thumbWidth", "thumbHeight", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "trackBorderColor", "solidThumbAlpha", "", "glassThumbAlpha"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class LiquidGlassSwitchKt {
    static final Unit LiquidGlassSwitchDemoPreview$lambda$32(int i, Composer composer, int i2) {
        LiquidGlassSwitchDemoPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LiquidGlassSwitchPreview$lambda$28(int i, Composer composer, int i2) {
        LiquidGlassSwitchPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LiquidGlassSwitch_AGcomas$lambda$17(boolean z, Function1 function1, Modifier modifier, boolean z2, float f, float f2, int i, int i2, Composer composer, int i3) {
        m7017LiquidGlassSwitchAGcomas(z, function1, modifier, z2, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit StatefulLiquidGlassSwitch_AGcomas$lambda$24(boolean z, Function1 function1, Modifier modifier, boolean z2, float f, float f2, int i, int i2, Composer composer, int i3) {
        m7018StatefulLiquidGlassSwitchAGcomas(z, function1, modifier, z2, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x059a  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x05a6  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x074d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0759  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0790  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x07a6  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x075f  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x05ac  */
    /* renamed from: LiquidGlassSwitch-AGcomas, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7017LiquidGlassSwitchAGcomas(final boolean r98, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r99, androidx.compose.ui.Modifier r100, boolean r101, float r102, float r103, androidx.compose.runtime.Composer r104, final int r105, final int r106) {
        /*
            Method dump skipped, instructions count: 2265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.LiquidGlassSwitchKt.m7017LiquidGlassSwitchAGcomas(boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiquidGlassSwitch_AGcomas$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiquidGlassSwitch_AGcomas$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float LiquidGlassSwitch_AGcomas$lambda$4(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    private static final float LiquidGlassSwitch_AGcomas$lambda$5(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    private static final float LiquidGlassSwitch_AGcomas$lambda$6(State<Dp> state) {
        return ((Dp) state.getValue()).m6640unboximpl();
    }

    private static final long LiquidGlassSwitch_AGcomas$lambda$7(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final long LiquidGlassSwitch_AGcomas$lambda$8(State<Color> state) {
        return ((Color) state.getValue()).m4173unboximpl();
    }

    private static final float LiquidGlassSwitch_AGcomas$lambda$9(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float LiquidGlassSwitch_AGcomas$lambda$10(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit LiquidGlassSwitch_AGcomas$lambda$13$lambda$12(HapticFeedback $hapticFeedback, Function1 $onCheckedChange, boolean $checked, MutableState $isAnimating$delegate) {
        $hapticFeedback.mo4883performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4891getLongPress5zf0vsI());
        LiquidGlassSwitch_AGcomas$lambda$2($isAnimating$delegate, true);
        $onCheckedChange.invoke(Boolean.valueOf(!$checked));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x01b4  */
    /* renamed from: StatefulLiquidGlassSwitch-AGcomas, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m7018StatefulLiquidGlassSwitchAGcomas(final boolean r19, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r20, androidx.compose.ui.Modifier r21, boolean r22, float r23, float r24, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 465
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.LiquidGlassSwitchKt.m7018StatefulLiquidGlassSwitchAGcomas(boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean StatefulLiquidGlassSwitch_AGcomas$lambda$20(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void StatefulLiquidGlassSwitch_AGcomas$lambda$21(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit StatefulLiquidGlassSwitch_AGcomas$lambda$23$lambda$22(Function1 $onChange, MutableState $checked$delegate, boolean it) {
        StatefulLiquidGlassSwitch_AGcomas$lambda$21($checked$delegate, it);
        $onChange.invoke(Boolean.valueOf(it));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void LiquidGlassSwitchPreview(androidx.compose.runtime.Composer r34, final int r35) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.LiquidGlassSwitchKt.LiquidGlassSwitchPreview(androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x018b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void LiquidGlassSwitchDemoPreview(androidx.compose.runtime.Composer r50, final int r51) {
        /*
            Method dump skipped, instructions count: 473
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.LiquidGlassSwitchKt.LiquidGlassSwitchDemoPreview(androidx.compose.runtime.Composer, int):void");
    }
}
