package androidx.compose.material;

import androidx.compose.material.internal.Icons;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.Color;
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

    /* renamed from: textFieldColors-DlUQjxs, reason: not valid java name */
    public final TextFieldColors m1549textFieldColorsDlUQjxs(long textColor, long disabledTextColor, long backgroundColor, long cursorColor, long errorCursorColor, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long leadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long trailingIconColor, long focusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long textColor2;
        long disabledTextColor2;
        long backgroundColor2;
        long unfocusedIndicatorColor2;
        long disabledIndicatorColor2;
        long leadingIconColor2;
        long leadingIconColor3;
        long trailingIconColor2;
        long trailingIconColor3;
        long unfocusedLabelColor2;
        long unfocusedLabelColor3;
        long disabledPlaceholderColor2;
        long m4161copywmQWz5c;
        long unfocusedIndicatorColor3;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        ComposerKt.sourceInformationMarkerStart($composer, 969536191, "C(textFieldColors)P(18:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,13:c#ui.graphics.Color,20:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.graphics.Color,16:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,19:c#ui.graphics.Color,15:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,21:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,17:c#ui.graphics.Color,5:c#ui.graphics.Color)356@14593L7,356@14624L7,357@14697L8,359@14767L6,360@14878L6,361@14942L6,363@15021L6,363@15062L4,365@15136L6,368@15336L8,369@15398L6,371@15472L6,372@15619L8,375@15748L6,377@15878L6,377@15919L4,378@16013L8,379@16078L6,381@16153L6,381@16194L4,382@16252L6,382@16287L6,383@16370L8,384@16428L6,385@16490L6,385@16525L6,386@16611L8:ExposedDropdownMenu.android.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long m4173unboximpl = ((Color) consume).m4173unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(m4173unboximpl, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(m4173unboximpl) : ((Number) consume2).floatValue(), (r12 & 2) != 0 ? Color.m4169getRedimpl(m4173unboximpl) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(m4173unboximpl) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(m4173unboximpl) : 0.0f);
            textColor2 = m4161copywmQWz5c4;
        } else {
            textColor2 = textColor;
        }
        if ((i & 2) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(textColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(textColor2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(textColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(textColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(textColor2) : 0.0f);
            disabledTextColor2 = m4161copywmQWz5c3;
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if ((i & 4) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r10) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f);
            backgroundColor2 = m4161copywmQWz5c2;
        } else {
            backgroundColor2 = backgroundColor;
        }
        long cursorColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorCursorColor;
        long focusedIndicatorColor2 = (i & 32) != 0 ? Color.m4161copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r14) : ContentAlpha.INSTANCE.getHigh($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU()) : 0.0f) : focusedIndicatorColor;
        long unfocusedIndicatorColor4 = (i & 64) != 0 ? Color.m4161copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r16) : 0.42f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : unfocusedIndicatorColor;
        if ((i & 128) != 0) {
            long unfocusedIndicatorColor5 = unfocusedIndicatorColor4;
            unfocusedIndicatorColor3 = Color.m4161copywmQWz5c(unfocusedIndicatorColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unfocusedIndicatorColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(unfocusedIndicatorColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unfocusedIndicatorColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unfocusedIndicatorColor5) : 0.0f);
            unfocusedIndicatorColor2 = unfocusedIndicatorColor5;
            disabledIndicatorColor2 = unfocusedIndicatorColor3;
        } else {
            unfocusedIndicatorColor2 = unfocusedIndicatorColor4;
            disabledIndicatorColor2 = disabledIndicatorColor;
        }
        long errorIndicatorColor2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorIndicatorColor;
        long leadingIconColor4 = (i & 512) != 0 ? Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : leadingIconColor;
        if ((i & 1024) != 0) {
            long leadingIconColor5 = leadingIconColor4;
            leadingIconColor3 = Color.m4161copywmQWz5c(leadingIconColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(leadingIconColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(leadingIconColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(leadingIconColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(leadingIconColor5) : 0.0f);
            leadingIconColor2 = leadingIconColor5;
        } else {
            leadingIconColor2 = leadingIconColor4;
            leadingIconColor3 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 2048) != 0 ? leadingIconColor2 : errorLeadingIconColor;
        long trailingIconColor4 = (i & 4096) != 0 ? Color.m4161copywmQWz5c(r28, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r28) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r28) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r28) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : trailingIconColor;
        long focusedTrailingIconColor2 = (i & 8192) != 0 ? Color.m4161copywmQWz5c(r30, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r30) : ContentAlpha.INSTANCE.getHigh($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r30) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r30) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU()) : 0.0f) : focusedTrailingIconColor;
        if ((i & 16384) != 0) {
            long trailingIconColor5 = trailingIconColor4;
            trailingIconColor3 = Color.m4161copywmQWz5c(trailingIconColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(trailingIconColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(trailingIconColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(trailingIconColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(trailingIconColor5) : 0.0f);
            trailingIconColor2 = trailingIconColor5;
        } else {
            trailingIconColor2 = trailingIconColor4;
            trailingIconColor3 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (32768 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorTrailingIconColor;
        long focusedLabelColor2 = (65536 & i) != 0 ? Color.m4161copywmQWz5c(r38, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r38) : ContentAlpha.INSTANCE.getHigh($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r38) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r38) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU()) : 0.0f) : focusedLabelColor;
        long unfocusedLabelColor4 = (131072 & i) != 0 ? Color.m4161copywmQWz5c(r40, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r40) : ContentAlpha.INSTANCE.getMedium($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r40) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r40) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : unfocusedLabelColor;
        if ((262144 & i) != 0) {
            long unfocusedLabelColor5 = unfocusedLabelColor4;
            unfocusedLabelColor3 = Color.m4161copywmQWz5c(unfocusedLabelColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unfocusedLabelColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(unfocusedLabelColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unfocusedLabelColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unfocusedLabelColor5) : 0.0f);
            unfocusedLabelColor2 = unfocusedLabelColor5;
        } else {
            unfocusedLabelColor2 = unfocusedLabelColor4;
            unfocusedLabelColor3 = disabledLabelColor;
        }
        long errorLabelColor2 = (524288 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorLabelColor;
        long placeholderColor2 = (1048576 & i) != 0 ? Color.m4161copywmQWz5c(r46, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r46) : ContentAlpha.INSTANCE.getMedium($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r46) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r46) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : placeholderColor;
        if ((i & 2097152) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(placeholderColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(placeholderColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(placeholderColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(placeholderColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(placeholderColor2) : 0.0f);
            disabledPlaceholderColor2 = m4161copywmQWz5c;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(969536191, $changed, $changed1, "androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:388)");
        }
        long unfocusedIndicatorColor6 = unfocusedIndicatorColor2;
        long unfocusedIndicatorColor7 = errorIndicatorColor2;
        DefaultTextFieldForExposedDropdownMenusColors defaultTextFieldForExposedDropdownMenusColors = new DefaultTextFieldForExposedDropdownMenusColors(textColor2, disabledTextColor2, cursorColor2, errorCursorColor2, focusedIndicatorColor2, unfocusedIndicatorColor6, unfocusedIndicatorColor7, disabledIndicatorColor2, leadingIconColor2, leadingIconColor3, errorLeadingIconColor2, trailingIconColor2, focusedTrailingIconColor2, trailingIconColor3, errorTrailingIconColor2, backgroundColor2, focusedLabelColor2, unfocusedLabelColor2, unfocusedLabelColor3, errorLabelColor2, placeholderColor2, disabledPlaceholderColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldForExposedDropdownMenusColors;
    }

    /* renamed from: outlinedTextFieldColors-DlUQjxs, reason: not valid java name */
    public final TextFieldColors m1548outlinedTextFieldColorsDlUQjxs(long textColor, long disabledTextColor, long backgroundColor, long cursorColor, long errorCursorColor, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long leadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long trailingIconColor, long focusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long textColor2;
        long disabledTextColor2;
        long unfocusedBorderColor2;
        long disabledBorderColor2;
        long leadingIconColor2;
        long leadingIconColor3;
        long trailingIconColor2;
        long trailingIconColor3;
        long unfocusedLabelColor2;
        long unfocusedLabelColor3;
        long disabledPlaceholderColor2;
        long m4161copywmQWz5c;
        long unfocusedBorderColor3;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        ComposerKt.sourceInformationMarkerStart($composer, 1841636861, "C(outlinedTextFieldColors)P(18:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,9:c#ui.graphics.Color,13:c#ui.graphics.Color,20:c#ui.graphics.Color,2:c#ui.graphics.Color,8:c#ui.graphics.Color,16:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,19:c#ui.graphics.Color,15:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,21:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,17:c#ui.graphics.Color,5:c#ui.graphics.Color)459@20578L7,459@20609L7,460@20682L8,462@20788L6,463@20852L6,465@20928L6,465@20969L4,467@21040L6,467@21083L8,468@21178L8,469@21237L6,471@21311L6,472@21458L8,475@21587L6,477@21717L6,477@21758L4,478@21852L8,479@21917L6,481@21992L6,481@22033L4,482@22091L6,482@22126L6,483@22209L8,484@22267L6,485@22329L6,485@22364L6,486@22450L8:ExposedDropdownMenu.android.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long m4173unboximpl = ((Color) consume).m4173unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(m4173unboximpl, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(m4173unboximpl) : ((Number) consume2).floatValue(), (r12 & 2) != 0 ? Color.m4169getRedimpl(m4173unboximpl) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(m4173unboximpl) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(m4173unboximpl) : 0.0f);
            textColor2 = m4161copywmQWz5c3;
        } else {
            textColor2 = textColor;
        }
        if ((i & 2) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(textColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(textColor2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(textColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(textColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(textColor2) : 0.0f);
            disabledTextColor2 = m4161copywmQWz5c2;
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long backgroundColor2 = (i & 4) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : backgroundColor;
        long cursorColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorCursorColor;
        long focusedBorderColor2 = (i & 32) != 0 ? Color.m4161copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r14) : ContentAlpha.INSTANCE.getHigh($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU()) : 0.0f) : focusedBorderColor;
        long unfocusedBorderColor4 = (i & 64) != 0 ? Color.m4161copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r16) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : unfocusedBorderColor;
        if ((i & 128) != 0) {
            long unfocusedBorderColor5 = unfocusedBorderColor4;
            unfocusedBorderColor3 = Color.m4161copywmQWz5c(unfocusedBorderColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unfocusedBorderColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(unfocusedBorderColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unfocusedBorderColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unfocusedBorderColor5) : 0.0f);
            unfocusedBorderColor2 = unfocusedBorderColor5;
            disabledBorderColor2 = unfocusedBorderColor3;
        } else {
            unfocusedBorderColor2 = unfocusedBorderColor4;
            disabledBorderColor2 = disabledBorderColor;
        }
        long errorBorderColor2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorBorderColor;
        long leadingIconColor4 = (i & 512) != 0 ? Color.m4161copywmQWz5c(r22, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r22) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r22) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r22) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : leadingIconColor;
        if ((i & 1024) != 0) {
            long leadingIconColor5 = leadingIconColor4;
            leadingIconColor3 = Color.m4161copywmQWz5c(leadingIconColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(leadingIconColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(leadingIconColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(leadingIconColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(leadingIconColor5) : 0.0f);
            leadingIconColor2 = leadingIconColor5;
        } else {
            leadingIconColor2 = leadingIconColor4;
            leadingIconColor3 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 2048) != 0 ? leadingIconColor2 : errorLeadingIconColor;
        long trailingIconColor4 = (i & 4096) != 0 ? Color.m4161copywmQWz5c(r28, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r28) : 0.54f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r28) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r28) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : trailingIconColor;
        long focusedTrailingIconColor2 = (i & 8192) != 0 ? Color.m4161copywmQWz5c(r30, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r30) : ContentAlpha.INSTANCE.getHigh($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r30) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r30) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU()) : 0.0f) : focusedTrailingIconColor;
        if ((i & 16384) != 0) {
            long trailingIconColor5 = trailingIconColor4;
            trailingIconColor3 = Color.m4161copywmQWz5c(trailingIconColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(trailingIconColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(trailingIconColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(trailingIconColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(trailingIconColor5) : 0.0f);
            trailingIconColor2 = trailingIconColor5;
        } else {
            trailingIconColor2 = trailingIconColor4;
            trailingIconColor3 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (32768 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorTrailingIconColor;
        long focusedLabelColor2 = (65536 & i) != 0 ? Color.m4161copywmQWz5c(r38, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r38) : ContentAlpha.INSTANCE.getHigh($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r38) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r38) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1478getPrimary0d7_KjU()) : 0.0f) : focusedLabelColor;
        long unfocusedLabelColor4 = (131072 & i) != 0 ? Color.m4161copywmQWz5c(r40, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r40) : ContentAlpha.INSTANCE.getMedium($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r40) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r40) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : unfocusedLabelColor;
        if ((262144 & i) != 0) {
            long unfocusedLabelColor5 = unfocusedLabelColor4;
            unfocusedLabelColor3 = Color.m4161copywmQWz5c(unfocusedLabelColor5, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(unfocusedLabelColor5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(unfocusedLabelColor5) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(unfocusedLabelColor5) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(unfocusedLabelColor5) : 0.0f);
            unfocusedLabelColor2 = unfocusedLabelColor5;
        } else {
            unfocusedLabelColor2 = unfocusedLabelColor4;
            unfocusedLabelColor3 = disabledLabelColor;
        }
        long errorLabelColor2 = (524288 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1472getError0d7_KjU() : errorLabelColor;
        long placeholderColor2 = (1048576 & i) != 0 ? Color.m4161copywmQWz5c(r46, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r46) : ContentAlpha.INSTANCE.getMedium($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r46) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r46) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : placeholderColor;
        if ((i & 2097152) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(placeholderColor, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(placeholderColor) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(placeholderColor) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(placeholderColor) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(placeholderColor2) : 0.0f);
            disabledPlaceholderColor2 = m4161copywmQWz5c;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1841636861, $changed, $changed1, "androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:488)");
        }
        long unfocusedBorderColor6 = unfocusedBorderColor2;
        long unfocusedBorderColor7 = errorBorderColor2;
        DefaultTextFieldForExposedDropdownMenusColors defaultTextFieldForExposedDropdownMenusColors = new DefaultTextFieldForExposedDropdownMenusColors(textColor2, disabledTextColor2, cursorColor2, errorCursorColor2, focusedBorderColor2, unfocusedBorderColor6, unfocusedBorderColor7, disabledBorderColor2, leadingIconColor2, leadingIconColor3, errorLeadingIconColor2, trailingIconColor2, focusedTrailingIconColor2, trailingIconColor3, errorTrailingIconColor2, backgroundColor2, focusedLabelColor2, unfocusedLabelColor2, unfocusedLabelColor3, errorLabelColor2, placeholderColor2, disabledPlaceholderColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldForExposedDropdownMenusColors;
    }
}
