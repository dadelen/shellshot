package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: IconButton.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ab\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001ap\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00152\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0016\u001ab\u0010\u0017\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001ap\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00152\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0016\u001aX\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u001a\u001af\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00152\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u001c\u001an\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010 \u001a|\u0010!\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00152\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\"¨\u0006#"}, d2 = {"FilledIconButton", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/IconButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "FilledIconToggleButton", "checked", "onCheckedChange", "Lkotlin/Function1;", "Landroidx/compose/material3/IconToggleButtonColors;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconToggleButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "FilledTonalIconButton", "FilledTonalIconToggleButton", "IconButton", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "IconToggleButton", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/IconToggleButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "OutlinedIconButton", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "OutlinedIconToggleButton", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconToggleButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class IconButtonKt {
    /* JADX WARN: Removed duplicated region for block: B:59:0x02da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void IconButton(final kotlin.jvm.functions.Function0<kotlin.Unit> r30, androidx.compose.ui.Modifier r31, boolean r32, androidx.compose.material3.IconButtonColors r33, androidx.compose.foundation.interaction.MutableInteractionSource r34, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instructions count: 758
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt.IconButton(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.IconButtonColors, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x031e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void IconToggleButton(final boolean r31, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r32, androidx.compose.ui.Modifier r33, boolean r34, androidx.compose.material3.IconToggleButtonColors r35, androidx.compose.foundation.interaction.MutableInteractionSource r36, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r37, androidx.compose.runtime.Composer r38, final int r39, final int r40) {
        /*
            Method dump skipped, instructions count: 828
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt.IconToggleButton(boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.IconToggleButtonColors, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void FilledIconButton(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, IconButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconButtonColors colors2;
        MutableInteractionSource mutableInteractionSource;
        Modifier.Companion modifier3;
        int $dirty;
        Shape shape3;
        IconButtonColors colors3;
        MutableInteractionSource interactionSource2;
        boolean enabled3;
        Composer $composer2;
        final IconButtonColors colors4;
        final Modifier modifier4;
        final boolean enabled4;
        final Shape shape4;
        final MutableInteractionSource interactionSource3;
        int i2;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(1594730011);
        ComposerKt.sourceInformation($composer3, "C(FilledIconButton)P(5,4,2,6!1,3)217@10330L11,218@10393L24,230@10821L320,222@10520L621:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i3 = 2048;
                    $dirty2 |= i3;
                }
            } else {
                shape2 = shape;
            }
            i3 = 1024;
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i2 = 16384;
                    $dirty2 |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 8192;
            $dirty2 |= i2;
        } else {
            colors2 = colors;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource = interactionSource;
        } else if ((196608 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? 131072 : 65536;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((599187 & $dirty2) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            shape4 = shape2;
            $composer2 = $composer3;
            enabled4 = enabled2;
            colors4 = colors2;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if (i5 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.filledIconButtonColors($composer3, 6);
                }
                if (i6 != 0) {
                    interactionSource2 = null;
                    shape3 = shape2;
                    $dirty = $dirty2;
                    colors3 = colors2;
                    enabled3 = enabled2;
                } else {
                    $dirty = $dirty2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                interactionSource2 = mutableInteractionSource;
                $dirty = $dirty2;
                colors3 = colors2;
                enabled3 = enabled2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1594730011, $dirty, -1, "androidx.compose.material3.FilledIconButton (IconButton.kt:222)");
            }
            $composer2 = $composer3;
            SurfaceKt.m2550Surfaceo_FOJdg(function02, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledIconButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5902getButtono7Vup1c());
                }
            }, 1, null), enabled3, shape3, colors3.m2133containerColorvNxB06k$material3_release(enabled3), colors3.m2134contentColorvNxB06k$material3_release(enabled3), 0.0f, 0.0f, null, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-1560623888, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledIconButton$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0164  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 360
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt$FilledIconButton$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, ($dirty & 14) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 12) & 1879048192), 6, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors4 = colors3;
            modifier4 = modifier3;
            enabled4 = enabled3;
            shape4 = shape3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledIconButton$3
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
                    IconButtonKt.FilledIconButton(function0, modifier4, enabled4, shape4, colors4, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void FilledTonalIconButton(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, IconButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconButtonColors colors2;
        MutableInteractionSource mutableInteractionSource;
        Modifier.Companion modifier3;
        int $dirty;
        Shape shape3;
        IconButtonColors colors3;
        MutableInteractionSource interactionSource2;
        boolean enabled3;
        Composer $composer2;
        final IconButtonColors colors4;
        final Modifier modifier4;
        final boolean enabled4;
        final Shape shape4;
        final MutableInteractionSource interactionSource3;
        int i2;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-783937767);
        ComposerKt.sourceInformation($composer3, "C(FilledTonalIconButton)P(5,4,2,6!1,3)284@13478L11,285@13541L29,297@13974L330,289@13673L631:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i3 = 2048;
                    $dirty2 |= i3;
                }
            } else {
                shape2 = shape;
            }
            i3 = 1024;
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i2 = 16384;
                    $dirty2 |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 8192;
            $dirty2 |= i2;
        } else {
            colors2 = colors;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            mutableInteractionSource = interactionSource;
        } else if ((196608 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? 131072 : 65536;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((599187 & $dirty2) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            shape4 = shape2;
            $composer2 = $composer3;
            enabled4 = enabled2;
            colors4 = colors2;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if (i5 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.filledTonalIconButtonColors($composer3, 6);
                }
                if (i6 != 0) {
                    interactionSource2 = null;
                    shape3 = shape2;
                    $dirty = $dirty2;
                    colors3 = colors2;
                    enabled3 = enabled2;
                } else {
                    $dirty = $dirty2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                modifier3 = modifier2;
                shape3 = shape2;
                interactionSource2 = mutableInteractionSource;
                $dirty = $dirty2;
                colors3 = colors2;
                enabled3 = enabled2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-783937767, $dirty, -1, "androidx.compose.material3.FilledTonalIconButton (IconButton.kt:289)");
            }
            $composer2 = $composer3;
            SurfaceKt.m2550Surfaceo_FOJdg(function02, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledTonalIconButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5902getButtono7Vup1c());
                }
            }, 1, null), enabled3, shape3, colors3.m2133containerColorvNxB06k$material3_release(enabled3), colors3.m2134contentColorvNxB06k$material3_release(enabled3), 0.0f, 0.0f, null, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-1772884636, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledTonalIconButton$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0164  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 360
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt$FilledTonalIconButton$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, ($dirty & 14) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 12) & 1879048192), 6, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors4 = colors3;
            modifier4 = modifier3;
            enabled4 = enabled3;
            shape4 = shape3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledTonalIconButton$3
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
                    IconButtonKt.FilledTonalIconButton(function0, modifier4, enabled4, shape4, colors4, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void FilledIconToggleButton(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean enabled, Shape shape, IconToggleButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconToggleButtonColors colors2;
        MutableInteractionSource mutableInteractionSource;
        int $dirty;
        Modifier modifier3;
        boolean enabled3;
        Shape shape3;
        IconToggleButtonColors colors3;
        MutableInteractionSource interactionSource2;
        final Shape shape4;
        final MutableInteractionSource interactionSource3;
        final IconToggleButtonColors colors4;
        final boolean enabled4;
        final Modifier modifier4;
        int i2;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(-1708189280);
        ComposerKt.sourceInformation($composer2, "C(FilledIconToggleButton)P(!1,6,5,3,7!1,4)349@16472L11,350@16541L30,360@16890L32,361@16960L30,363@17050L320,354@16674L696:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer2.changed(shape2)) {
                    i3 = 16384;
                    $dirty2 |= i3;
                }
            } else {
                shape2 = shape;
            }
            i3 = 8192;
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer2.changed(colors2)) {
                    i2 = 131072;
                    $dirty2 |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 65536;
            $dirty2 |= i2;
        } else {
            colors2 = colors;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty2) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            enabled4 = enabled2;
            shape4 = shape2;
            colors4 = colors2;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer2, 6);
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    colors2 = IconButtonDefaults.INSTANCE.filledIconToggleButtonColors($composer2, 6);
                }
                if (i6 != 0) {
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = null;
                    modifier3 = modifier2;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1708189280, $dirty, -1, "androidx.compose.material3.FilledIconToggleButton (IconButton.kt:354)");
            }
            IconToggleButtonColors colors5 = colors3;
            Modifier modifier5 = modifier3;
            SurfaceKt.m2549Surfaced85dljk(checked, function1, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledIconToggleButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5903getCheckboxo7Vup1c());
                }
            }, 1, null), enabled3, shape3, colors3.containerColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m4173unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m4173unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource2, ComposableLambdaKt.rememberComposableLambda(1235871670, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledIconToggleButton$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0164  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 360
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt$FilledIconToggleButton$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 7168) | (57344 & $dirty), (($dirty >> 18) & 14) | 48, 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            interactionSource3 = interactionSource2;
            colors4 = colors5;
            enabled4 = enabled3;
            modifier4 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledIconToggleButton$3
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
                    IconButtonKt.FilledIconToggleButton(checked, function1, modifier4, enabled4, shape4, colors4, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void FilledTonalIconToggleButton(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean enabled, Shape shape, IconToggleButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconToggleButtonColors colors2;
        MutableInteractionSource mutableInteractionSource;
        int $dirty;
        Modifier modifier3;
        boolean enabled3;
        Shape shape3;
        IconToggleButtonColors colors3;
        MutableInteractionSource interactionSource2;
        final Shape shape4;
        final MutableInteractionSource interactionSource3;
        final IconToggleButtonColors colors4;
        final boolean enabled4;
        final Modifier modifier4;
        int i2;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(1676089246);
        ComposerKt.sourceInformation($composer2, "C(FilledTonalIconToggleButton)P(!1,6,5,3,7!1,4)420@19894L11,421@19963L35,431@20317L32,432@20387L30,434@20477L330,425@20101L706:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer2.changed(shape2)) {
                    i3 = 16384;
                    $dirty2 |= i3;
                }
            } else {
                shape2 = shape;
            }
            i3 = 8192;
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer2.changed(colors2)) {
                    i2 = 131072;
                    $dirty2 |= i2;
                }
            } else {
                colors2 = colors;
            }
            i2 = 65536;
            $dirty2 |= i2;
        } else {
            colors2 = colors;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty2) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            enabled4 = enabled2;
            shape4 = shape2;
            colors4 = colors2;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer2, 6);
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    colors2 = IconButtonDefaults.INSTANCE.filledTonalIconToggleButtonColors($composer2, 6);
                }
                if (i6 != 0) {
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = null;
                    modifier3 = modifier2;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1676089246, $dirty, -1, "androidx.compose.material3.FilledTonalIconToggleButton (IconButton.kt:425)");
            }
            IconToggleButtonColors colors5 = colors3;
            Modifier modifier5 = modifier3;
            SurfaceKt.m2549Surfaced85dljk(checked, function1, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledTonalIconToggleButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5903getCheckboxo7Vup1c());
                }
            }, 1, null), enabled3, shape3, colors3.containerColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m4173unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m4173unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-58218680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledTonalIconToggleButton$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x0164  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 360
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt$FilledTonalIconToggleButton$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 7168) | (57344 & $dirty), (($dirty >> 18) & 14) | 48, 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            interactionSource3 = interactionSource2;
            colors4 = colors5;
            enabled4 = enabled3;
            modifier4 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$FilledTonalIconToggleButton$3
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
                    IconButtonKt.FilledTonalIconToggleButton(checked, function1, modifier4, enabled4, shape4, colors4, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void OutlinedIconButton(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, IconButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconButtonColors colors2;
        BorderStroke border2;
        MutableInteractionSource mutableInteractionSource;
        Modifier.Companion modifier3;
        int $dirty;
        Shape shape3;
        IconButtonColors colors3;
        BorderStroke border3;
        MutableInteractionSource interactionSource2;
        boolean enabled3;
        Composer $composer2;
        final IconButtonColors colors4;
        final boolean enabled4;
        final Shape shape4;
        final BorderStroke border4;
        final MutableInteractionSource interactionSource3;
        final Modifier modifier4;
        int i2;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-1746603025);
        ComposerKt.sourceInformation($composer3, "C(OutlinedIconButton)P(6,5,3,7,1!1,4)491@23280L13,492@23345L26,493@23420L33,506@23882L190,497@23556L516:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty2 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i4 = 2048;
                    $dirty2 |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 1024;
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i3 = 16384;
                    $dirty2 |= i3;
                }
            } else {
                colors2 = colors;
            }
            i3 = 8192;
            $dirty2 |= i3;
        } else {
            colors2 = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                border2 = border;
                if ($composer3.changed(border2)) {
                    i2 = 131072;
                    $dirty2 |= i2;
                }
            } else {
                border2 = border;
            }
            i2 = 65536;
            $dirty2 |= i2;
        } else {
            border2 = border;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty2) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled4 = enabled2;
            shape4 = shape2;
            colors4 = colors2;
            border4 = border2;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if (i6 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.outlinedIconButtonColors($composer3, 6);
                }
                if ((i & 32) != 0) {
                    BorderStroke border5 = IconButtonDefaults.INSTANCE.outlinedIconButtonBorder(enabled2, $composer3, (($dirty2 >> 6) & 14) | 48);
                    $dirty2 &= -458753;
                    border2 = border5;
                }
                if (i7 != 0) {
                    interactionSource2 = null;
                    shape3 = shape2;
                    border3 = border2;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                } else {
                    $dirty = $dirty2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    interactionSource2 = mutableInteractionSource;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    modifier3 = modifier2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    interactionSource2 = mutableInteractionSource;
                    enabled3 = enabled2;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    interactionSource2 = mutableInteractionSource;
                    enabled3 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1746603025, $dirty, -1, "androidx.compose.material3.OutlinedIconButton (IconButton.kt:497)");
            }
            $composer2 = $composer3;
            SurfaceKt.m2550Surfaceo_FOJdg(function02, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt$OutlinedIconButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5902getButtono7Vup1c());
                }
            }, 1, null), enabled3, shape3, colors3.m2133containerColorvNxB06k$material3_release(enabled3), colors3.m2134contentColorvNxB06k$material3_release(enabled3), 0.0f, 0.0f, border3, interactionSource2, ComposableLambdaKt.rememberComposableLambda(582332538, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$OutlinedIconButton$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x015e  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 354
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt$OutlinedIconButton$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, ($dirty & 14) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 9) & 234881024) | (1879048192 & ($dirty << 9)), 6, 192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors4 = colors3;
            enabled4 = enabled3;
            shape4 = shape3;
            border4 = border3;
            interactionSource3 = interactionSource2;
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$OutlinedIconButton$3
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

                public final void invoke(Composer composer, int i8) {
                    IconButtonKt.OutlinedIconButton(function0, modifier4, enabled4, shape4, colors4, border4, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void OutlinedIconToggleButton(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean enabled, Shape shape, IconToggleButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconToggleButtonColors colors2;
        BorderStroke border2;
        MutableInteractionSource mutableInteractionSource;
        int $dirty;
        Modifier modifier3;
        boolean enabled3;
        IconToggleButtonColors colors3;
        MutableInteractionSource interactionSource2;
        Shape shape3;
        BorderStroke border3;
        final Shape shape4;
        final BorderStroke border4;
        final MutableInteractionSource interactionSource3;
        final IconToggleButtonColors colors4;
        final boolean enabled4;
        final Modifier modifier4;
        int i2;
        int i3;
        int i4;
        Composer $composer2 = $composer.startRestartGroup(1470292106);
        ComposerKt.sourceInformation($composer2, "C(OutlinedIconToggleButton)P(1,7,6,4,8,2!1,5)555@26419L13,556@26490L32,557@26571L48,567@26938L32,568@27008L30,571@27123L190,561@26722L591:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer2.changed(shape2)) {
                    i4 = 16384;
                    $dirty2 |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 8192;
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer2.changed(colors2)) {
                    i3 = 131072;
                    $dirty2 |= i3;
                }
            } else {
                colors2 = colors;
            }
            i3 = 65536;
            $dirty2 |= i3;
        } else {
            colors2 = colors;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                border2 = border;
                if ($composer2.changed(border2)) {
                    i2 = 1048576;
                    $dirty2 |= i2;
                }
            } else {
                border2 = border;
            }
            i2 = 524288;
            $dirty2 |= i2;
        } else {
            border2 = border;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty2 |= 12582912;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 12582912) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? 8388608 : 4194304;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & $dirty2) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            shape4 = shape2;
            colors4 = colors2;
            border4 = border2;
            interactionSource3 = mutableInteractionSource;
            enabled4 = enabled2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    shape2 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer2, 6);
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    colors2 = IconButtonDefaults.INSTANCE.outlinedIconToggleButtonColors($composer2, 6);
                }
                if ((i & 64) != 0) {
                    BorderStroke border5 = IconButtonDefaults.INSTANCE.outlinedIconToggleButtonBorder(enabled2, checked, $composer2, (($dirty2 >> 9) & 14) | 384 | (($dirty2 << 3) & 112));
                    $dirty2 &= -3670017;
                    border2 = border5;
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    interactionSource2 = null;
                    modifier3 = modifier2;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    shape3 = shape2;
                    border3 = border2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    shape3 = shape2;
                    border3 = border2;
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    shape3 = shape2;
                    border3 = border2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1470292106, $dirty, -1, "androidx.compose.material3.OutlinedIconToggleButton (IconButton.kt:561)");
            }
            Modifier modifier5 = modifier3;
            IconToggleButtonColors colors5 = colors3;
            SurfaceKt.m2549Surfaced85dljk(checked, function1, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt$OutlinedIconToggleButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5903getCheckboxo7Vup1c());
                }
            }, 1, null), enabled3, shape3, colors3.containerColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m4173unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 896) | (($dirty >> 9) & 14) | (($dirty << 3) & 112)).getValue().m4173unboximpl(), 0.0f, 0.0f, border3, interactionSource2, ComposableLambdaKt.rememberComposableLambda(1207657396, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$OutlinedIconToggleButton$2
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x015e  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                    /*
                        Method dump skipped, instructions count: 354
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.IconButtonKt$OutlinedIconToggleButton$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 7168) | (57344 & $dirty) | (1879048192 & ($dirty << 9)), (($dirty >> 21) & 14) | 48, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            border4 = border3;
            interactionSource3 = interactionSource2;
            colors4 = colors5;
            enabled4 = enabled3;
            modifier4 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt$OutlinedIconToggleButton$3
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

                public final void invoke(Composer composer, int i8) {
                    IconButtonKt.OutlinedIconToggleButton(checked, function1, modifier4, enabled4, shape4, colors4, border4, interactionSource3, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
