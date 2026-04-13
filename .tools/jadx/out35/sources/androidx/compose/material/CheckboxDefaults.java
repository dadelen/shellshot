package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* compiled from: Checkbox.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/material/CheckboxDefaults;", "", "()V", "colors", "Landroidx/compose/material/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledColor", "disabledIndeterminateColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/CheckboxColors;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();

    private CheckboxDefaults() {
    }

    /* renamed from: colors-zjMxDiM, reason: not valid java name */
    public final CheckboxColors m1456colorszjMxDiM(long checkedColor, long uncheckedColor, long checkmarkColor, long disabledColor, long disabledIndeterminateColor, Composer $composer, int $changed, int i) {
        long checkedColor2;
        long checkedColor3;
        long checkmarkColor2;
        long checkedColor4;
        long checkedColor5;
        ComposerKt.sourceInformationMarkerStart($composer, 469524104, "C(colors)P(0:c#ui.graphics.Color,4:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)226@9439L6,227@9503L6,228@9586L6,229@9647L6,229@9690L8,230@9784L8,232@9833L922:Checkbox.kt#jmzs0o");
        long checkedColor6 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1480getSecondary0d7_KjU() : checkedColor;
        long uncheckedColor2 = (i & 2) != 0 ? Color.m4161copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r14) : 0.6f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : uncheckedColor;
        long checkmarkColor3 = (i & 4) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1482getSurface0d7_KjU() : checkmarkColor;
        long disabledColor2 = (i & 8) != 0 ? Color.m4161copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r14) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(MaterialTheme.INSTANCE.getColors($composer, 6).m1477getOnSurface0d7_KjU()) : 0.0f) : disabledColor;
        if ((i & 16) != 0) {
            long checkedColor7 = checkedColor6;
            checkedColor3 = Color.m4161copywmQWz5c(checkedColor7, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(checkedColor7) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(checkedColor7) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(checkedColor7) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(checkedColor7) : 0.0f);
            checkedColor2 = checkedColor7;
        } else {
            checkedColor2 = checkedColor6;
            checkedColor3 = disabledIndeterminateColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(469524104, $changed, -1, "androidx.compose.material.CheckboxDefaults.colors (Checkbox.kt:231)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 654676893, "CC(remember):Checkbox.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(checkedColor2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(uncheckedColor2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(checkmarkColor3)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(disabledColor2)) || ($changed & 3072) == 2048) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(checkedColor3)) || ($changed & 24576) == 16384);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            long checkmarkColor4 = checkmarkColor3;
            checkmarkColor2 = Color.m4161copywmQWz5c(checkmarkColor4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(checkmarkColor4) : 0.0f, (r12 & 2) != 0 ? Color.m4169getRedimpl(checkmarkColor4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(checkmarkColor4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(checkmarkColor4) : 0.0f);
            long checkedColor8 = checkedColor2;
            checkedColor4 = Color.m4161copywmQWz5c(checkedColor8, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(checkedColor8) : 0.0f, (r12 & 2) != 0 ? Color.m4169getRedimpl(checkedColor8) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(checkedColor8) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(checkedColor8) : 0.0f);
            long disabledColor3 = disabledColor2;
            checkedColor5 = Color.m4161copywmQWz5c(disabledColor3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(disabledColor3) : 0.0f, (r12 & 2) != 0 ? Color.m4169getRedimpl(disabledColor3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(disabledColor3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(disabledColor3) : 0.0f);
            long disabledColor4 = checkedColor3;
            value$iv = new DefaultCheckboxColors(checkmarkColor4, checkmarkColor2, checkedColor8, checkedColor4, disabledColor3, checkedColor5, disabledColor4, checkedColor8, uncheckedColor2, disabledColor3, checkedColor3, null);
            $composer.updateRememberedValue(value$iv);
        }
        DefaultCheckboxColors defaultCheckboxColors = (DefaultCheckboxColors) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultCheckboxColors;
    }
}
