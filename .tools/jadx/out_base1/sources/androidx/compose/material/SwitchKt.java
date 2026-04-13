package androidx.compose.material;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: Switch.kt */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001aU\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u0010\"\u001a?\u0010#\u001a\u00020\u0016*\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020&2\u0006\u0010\u001e\u001a\u00020'H\u0003¢\u0006\u0002\u0010(\u001a.\u0010)\u001a\u00020\u0016*\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b/\u00100\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\u0007\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0016\u0010\u000b\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\f\u0010\r\"\u0010\u0010\u000e\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u000f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0010\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0016\u0010\u0011\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0012\u0010\r\"\u0016\u0010\u0013\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0014\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061²\u0006\n\u00102\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u0018\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u001aX\u008a\u0084\u0002²\u0006\n\u00104\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\n\u0010+\u001a\u00020,X\u008a\u0084\u0002²\u0006\n\u00105\u001a\u00020,X\u008a\u0084\u0002²\u0006\n\u00106\u001a\u00020,X\u008a\u0084\u0002"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "DefaultSwitchPadding", "Landroidx/compose/ui/unit/Dp;", "F", "SwitchHeight", "SwitchPositionalThreshold", "SwitchVelocityThreshold", "SwitchWidth", "ThumbDefaultElevation", "ThumbDiameter", "getThumbDiameter", "()F", "ThumbPathLength", "ThumbPressedElevation", "ThumbRippleRadius", "TrackStrokeWidth", "getTrackStrokeWidth", "TrackWidth", "getTrackWidth", "Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "colors", "Landroidx/compose/material/SwitchColors;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SwitchColors;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/layout/BoxScope;", "thumbValue", "Lkotlin/Function0;", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/layout/BoxScope;ZZLandroidx/compose/material/SwitchColors;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)V", "drawTrack", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "trackColor", "Landroidx/compose/ui/graphics/Color;", "trackWidth", "strokeWidth", "drawTrack-RPmYEkk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFF)V", "material_release", "forceAnimationCheck", "currentOnCheckedChange", "currentChecked", "thumbColor", "resolvedThumbColor"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final float SwitchPositionalThreshold = 0.7f;
    private static final float SwitchVelocityThreshold;
    private static final float ThumbDefaultElevation;
    private static final float ThumbPathLength;
    private static final float ThumbPressedElevation;
    private static final float TrackWidth = Dp.m6626constructorimpl(34);
    private static final float TrackStrokeWidth = Dp.m6626constructorimpl(14);
    private static final float ThumbDiameter = Dp.m6626constructorimpl(20);
    private static final float ThumbRippleRadius = Dp.m6626constructorimpl(24);
    private static final float DefaultSwitchPadding = Dp.m6626constructorimpl(2);
    private static final float SwitchWidth = TrackWidth;
    private static final float SwitchHeight = ThumbDiameter;

    /* JADX WARN: Removed duplicated region for block: B:102:0x0587  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x052a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03c4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x051c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Switch(final boolean r43, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r44, androidx.compose.ui.Modifier r45, boolean r46, androidx.compose.foundation.interaction.MutableInteractionSource r47, androidx.compose.material.SwitchColors r48, androidx.compose.runtime.Composer r49, final int r50, final int r51) {
        /*
            Method dump skipped, instructions count: 1449
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwitchKt.Switch(boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.SwitchColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Switch$lambda$3(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Switch$lambda$4(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<Boolean, Unit> Switch$lambda$7(State<? extends Function1<? super Boolean, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function1) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Switch$lambda$8(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x025b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SwitchImpl(final androidx.compose.foundation.layout.BoxScope r32, final boolean r33, final boolean r34, final androidx.compose.material.SwitchColors r35, final kotlin.jvm.functions.Function0<java.lang.Float> r36, final androidx.compose.foundation.interaction.InteractionSource r37, androidx.compose.runtime.Composer r38, final int r39) {
        /*
            Method dump skipped, instructions count: 744
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwitchKt.SwitchImpl(androidx.compose.foundation.layout.BoxScope, boolean, boolean, androidx.compose.material.SwitchColors, kotlin.jvm.functions.Function0, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long SwitchImpl$lambda$15(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m4173unboximpl();
    }

    private static final long SwitchImpl$lambda$17(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m4173unboximpl();
    }

    private static final long SwitchImpl$lambda$18(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m4173unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawTrack-RPmYEkk, reason: not valid java name */
    public static final void m1674drawTrackRPmYEkk(DrawScope $this$drawTrack_u2dRPmYEkk, long trackColor, float trackWidth, float strokeWidth) {
        float strokeRadius = strokeWidth / 2.0f;
        DrawScope.m4699drawLineNGM6Ib0$default($this$drawTrack_u2dRPmYEkk, trackColor, OffsetKt.Offset(strokeRadius, Offset.m3923getYimpl($this$drawTrack_u2dRPmYEkk.mo4712getCenterF1C5BW0())), OffsetKt.Offset(trackWidth - strokeRadius, Offset.m3923getYimpl($this$drawTrack_u2dRPmYEkk.mo4712getCenterF1C5BW0())), strokeWidth, StrokeCap.INSTANCE.m4516getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
    }

    static {
        float arg0$iv = TrackWidth;
        float other$iv = ThumbDiameter;
        ThumbPathLength = Dp.m6626constructorimpl(arg0$iv - other$iv);
        AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
        ThumbDefaultElevation = Dp.m6626constructorimpl(1);
        ThumbPressedElevation = Dp.m6626constructorimpl(6);
        SwitchVelocityThreshold = Dp.m6626constructorimpl(125);
    }

    public static final float getTrackWidth() {
        return TrackWidth;
    }

    public static final float getTrackStrokeWidth() {
        return TrackStrokeWidth;
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }
}
