package androidx.compose.material;

import androidx.compose.material.internal.Icons;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: ExposedDropdownMenu.android.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0007¢\u0006\u0002\u0010\tJî\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\r2\b\b\u0002\u0010 \u001a\u00020\r2\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$Jî\u0001\u0010%\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010&\u001a\u00020\r2\b\b\u0002\u0010'\u001a\u00020\r2\b\b\u0002\u0010(\u001a\u00020\r2\b\b\u0002\u0010)\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\r2\b\b\u0002\u0010 \u001a\u00020\r2\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010$\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/material/ExposedDropdownMenuDefaults;", "", "()V", "TrailingIcon", "", "expanded", "", "onIconClick", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "outlinedTextFieldColors", "Landroidx/compose/material/TextFieldColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", "backgroundColor", "cursorColor", "errorCursorColor", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "focusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "outlinedTextFieldColors-DlUQjxs", "(JJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "textFieldColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "textFieldColors-DlUQjxs", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExposedDropdownMenuDefaults {
    public static final int $stable = 0;
    public static final ExposedDropdownMenuDefaults INSTANCE = new ExposedDropdownMenuDefaults();

    private ExposedDropdownMenuDefaults() {
    }

    public final void TrailingIcon(final boolean expanded, Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        final Function0 onIconClick;
        Composer $composer2 = $composer.startRestartGroup(1752693020);
        ComposerKt.sourceInformation($composer2, "C(TrailingIcon)297@11600L314,297@11520L394:ExposedDropdownMenu.android.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(expanded) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            onIconClick = function0;
        } else {
            onIconClick = i2 != 0 ? new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$TrailingIcon$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            } : function0;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1752693020, $dirty2, -1, "androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon (ExposedDropdownMenu.android.kt:292)");
            }
            IconButtonKt.IconButton(onIconClick, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$TrailingIcon$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                }
            }), false, null, ComposableLambdaKt.rememberComposableLambda(-689144648, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$TrailingIcon$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    float f;
                    ComposerKt.sourceInformation($composer3, "C298@11614L290:ExposedDropdownMenu.android.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-689144648, $changed2, -1, "androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon.<anonymous> (ExposedDropdownMenu.android.kt:298)");
                        }
                        ImageVector arrowDropDown$material_release = Icons.Filled.INSTANCE.getArrowDropDown$material_release();
                        Modifier.Companion companion = Modifier.INSTANCE;
                        if (expanded) {
                            f = 180.0f;
                        } else {
                            f = 360.0f;
                        }
                        IconKt.m1571Iconww6aTOc(arrowDropDown$material_release, "Trailing icon for exposed dropdown menu", RotateKt.rotate(companion, f), 0L, $composer3, 48, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 3) & 14) | 24576, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$TrailingIcon$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    ExposedDropdownMenuDefaults.this.TrailingIcon(expanded, onIconClick, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* renamed from: textFieldColors-DlUQjxs, reason: not valid java name */
    public final androidx.compose.material.TextFieldColors m1549textFieldColorsDlUQjxs(long r55, long r57, long r59, long r61, long r63, long r65, long r67, long r69, long r71, long r73, long r75, long r77, long r79, long r81, long r83, long r85, long r87, long r89, long r91, long r93, long r95, long r97, androidx.compose.runtime.Composer r99, int r100, int r101, int r102, int r103) {
        /*
            Method dump skipped, instructions count: 962
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ExposedDropdownMenuDefaults.m1549textFieldColorsDlUQjxs(long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, androidx.compose.runtime.Composer, int, int, int, int):androidx.compose.material.TextFieldColors");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* renamed from: outlinedTextFieldColors-DlUQjxs, reason: not valid java name */
    public final androidx.compose.material.TextFieldColors m1548outlinedTextFieldColorsDlUQjxs(long r55, long r57, long r59, long r61, long r63, long r65, long r67, long r69, long r71, long r73, long r75, long r77, long r79, long r81, long r83, long r85, long r87, long r89, long r91, long r93, long r95, long r97, androidx.compose.runtime.Composer r99, int r100, int r101, int r102, int r103) {
        /*
            Method dump skipped, instructions count: 947
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ExposedDropdownMenuDefaults.m1548outlinedTextFieldColorsDlUQjxs(long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, androidx.compose.runtime.Composer, int, int, int, int):androidx.compose.material.TextFieldColors");
    }
}
