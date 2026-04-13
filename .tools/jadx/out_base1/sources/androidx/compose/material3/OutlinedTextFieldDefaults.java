package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.internal.TextFieldType;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.PropertyReference0Impl;

/* compiled from: TextFieldDefaults.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b)\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\\\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$JR\u0010%\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u009c\u0002\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00180,¢\u0006\u0002\b-2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u0002002\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u0018\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u0010 \u001a\u00020\u00132\b\b\u0002\u00108\u001a\u0002092\u0013\b\u0002\u0010:\u001a\r\u0012\u0004\u0012\u00020\u00180,¢\u0006\u0002\b-H\u0007¢\u0006\u0002\u0010;J\r\u0010 \u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010<JÂ\u0003\u0010 \u001a\u00020\u00132\b\b\u0002\u0010=\u001a\u00020>2\b\b\u0002\u0010?\u001a\u00020>2\b\b\u0002\u0010@\u001a\u00020>2\b\b\u0002\u0010A\u001a\u00020>2\b\b\u0002\u0010B\u001a\u00020>2\b\b\u0002\u0010C\u001a\u00020>2\b\b\u0002\u0010D\u001a\u00020>2\b\b\u0002\u0010E\u001a\u00020>2\b\b\u0002\u0010F\u001a\u00020>2\b\b\u0002\u0010G\u001a\u00020>2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010J\u001a\u00020>2\b\b\u0002\u0010K\u001a\u00020>2\b\b\u0002\u0010L\u001a\u00020>2\b\b\u0002\u0010M\u001a\u00020>2\b\b\u0002\u0010N\u001a\u00020>2\b\b\u0002\u0010O\u001a\u00020>2\b\b\u0002\u0010P\u001a\u00020>2\b\b\u0002\u0010Q\u001a\u00020>2\b\b\u0002\u0010R\u001a\u00020>2\b\b\u0002\u0010S\u001a\u00020>2\b\b\u0002\u0010T\u001a\u00020>2\b\b\u0002\u0010U\u001a\u00020>2\b\b\u0002\u0010V\u001a\u00020>2\b\b\u0002\u0010W\u001a\u00020>2\b\b\u0002\u0010X\u001a\u00020>2\b\b\u0002\u0010Y\u001a\u00020>2\b\b\u0002\u0010Z\u001a\u00020>2\b\b\u0002\u0010[\u001a\u00020>2\b\b\u0002\u0010\\\u001a\u00020>2\b\b\u0002\u0010]\u001a\u00020>2\b\b\u0002\u0010^\u001a\u00020>2\b\b\u0002\u0010_\u001a\u00020>2\b\b\u0002\u0010`\u001a\u00020>2\b\b\u0002\u0010a\u001a\u00020>2\b\b\u0002\u0010b\u001a\u00020>2\b\b\u0002\u0010c\u001a\u00020>2\b\b\u0002\u0010d\u001a\u00020>2\b\b\u0002\u0010e\u001a\u00020>2\b\b\u0002\u0010f\u001a\u00020>2\b\b\u0002\u0010g\u001a\u00020>2\b\b\u0002\u0010h\u001a\u00020>2\b\b\u0002\u0010i\u001a\u00020>H\u0007ø\u0001\u0000¢\u0006\u0004\bj\u0010kJ8\u00108\u001a\u0002092\b\b\u0002\u0010l\u001a\u00020\u00042\b\b\u0002\u0010m\u001a\u00020\u00042\b\b\u0002\u0010n\u001a\u00020\u00042\b\b\u0002\u0010o\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\bp\u0010qR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u000f8G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u00148AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006r"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldDefaults;", "", "()V", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "defaultOutlinedTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultOutlinedTextFieldColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", TextFieldImplKt.ContainerId, "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "focusedBorderThickness", "unfocusedBorderThickness", "Container-4EFweAY", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "ContainerBox", "ContainerBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "DecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "container", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "start", "top", "end", "bottom", "contentPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class OutlinedTextFieldDefaults {
    public static final int $stable = 0;
    public static final OutlinedTextFieldDefaults INSTANCE = new OutlinedTextFieldDefaults();
    private static final float MinHeight = Dp.m6626constructorimpl(56);
    private static final float MinWidth = Dp.m6626constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m6626constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m6626constructorimpl(2);

    private OutlinedTextFieldDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1066756961, "C729@37132L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1066756961, $changed, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-shape> (TextFieldDefaults.kt:729)");
        }
        Shape value = ShapesKt.getValue(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m2350getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m2351getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2352getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2349getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* renamed from: Container-4EFweAY, reason: not valid java name */
    public final void m2345Container4EFweAY(final boolean enabled, final boolean isError, final InteractionSource interactionSource, Modifier modifier, TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TextFieldColors textFieldColors;
        Shape shape2;
        float f;
        float f2;
        TextFieldColors colors2;
        Shape shape3;
        float focusedBorderThickness2;
        Modifier modifier3;
        Shape shape4;
        float unfocusedBorderThickness2;
        int $dirty;
        TextFieldColors colors3;
        float focusedBorderThickness3;
        Composer $composer2;
        final float focusedBorderThickness4;
        final float unfocusedBorderThickness3;
        final Shape shape5;
        final TextFieldColors colors4;
        final Modifier modifier4;
        int i2;
        int i3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(1035477640);
        ComposerKt.sourceInformation($composer3, "C(Container)P(1,4,3,5!1,6,2:c#ui.unit.Dp,7:c#ui.unit.Dp)772@39024L8,773@39083L5,777@39264L25,779@39335L222,788@39599L198,792@39806L153:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 256 : 128;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                textFieldColors = colors;
                if ($composer3.changed(textFieldColors)) {
                    i5 = 16384;
                    $dirty2 |= i5;
                }
            } else {
                textFieldColors = colors;
            }
            i5 = 8192;
            $dirty2 |= i5;
        } else {
            textFieldColors = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i4 = 131072;
                    $dirty2 |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 65536;
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                f = focusedBorderThickness;
                if ($composer3.changed(f)) {
                    i3 = 1048576;
                    $dirty2 |= i3;
                }
            } else {
                f = focusedBorderThickness;
            }
            i3 = 524288;
            $dirty2 |= i3;
        } else {
            f = focusedBorderThickness;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                f2 = unfocusedBorderThickness;
                if ($composer3.changed(f2)) {
                    i2 = 8388608;
                    $dirty2 |= i2;
                }
            } else {
                f2 = unfocusedBorderThickness;
            }
            i2 = 4194304;
            $dirty2 |= i2;
        } else {
            f2 = unfocusedBorderThickness;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & $dirty2) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            shape5 = shape2;
            float f3 = f;
            $composer2 = $composer3;
            focusedBorderThickness4 = f3;
            colors4 = textFieldColors;
            unfocusedBorderThickness3 = f2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 16) != 0) {
                    colors2 = colors($composer3, ($dirty2 >> 24) & 14);
                    $dirty2 &= -57345;
                } else {
                    colors2 = textFieldColors;
                }
                if ((i & 32) != 0) {
                    shape3 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -458753;
                } else {
                    shape3 = shape2;
                }
                if ((i & 64) != 0) {
                    focusedBorderThickness2 = FocusedBorderThickness;
                    $dirty2 &= -3670017;
                } else {
                    focusedBorderThickness2 = f;
                }
                if ((i & 128) != 0) {
                    $dirty = $dirty2 & (-29360129);
                    colors3 = colors2;
                    shape4 = shape3;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    modifier3 = modifier5;
                } else {
                    modifier3 = modifier5;
                    shape4 = shape3;
                    unfocusedBorderThickness2 = f2;
                    $dirty = $dirty2;
                    colors3 = colors2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    int i7 = $dirty2 & (-29360129);
                    colors3 = textFieldColors;
                    focusedBorderThickness3 = f;
                    shape4 = shape2;
                    modifier3 = modifier2;
                    unfocusedBorderThickness2 = f2;
                    $dirty = i7;
                } else {
                    focusedBorderThickness3 = f;
                    shape4 = shape2;
                    modifier3 = modifier2;
                    unfocusedBorderThickness2 = f2;
                    $dirty = $dirty2;
                    colors3 = textFieldColors;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1035477640, $dirty, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.Container (TextFieldDefaults.kt:776)");
            }
            boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer3, ($dirty >> 6) & 14).getValue().booleanValue();
            State borderStroke = TextFieldImplKt.m2985animateBorderStrokeAsStateNuRrP5Q(enabled, isError, focused, colors3, focusedBorderThickness3, unfocusedBorderThickness2, $composer3, ($dirty & 14) | ($dirty & 112) | (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752));
            Modifier modifier6 = modifier3;
            Shape shape6 = shape4;
            final State containerColor = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(colors3.m2614containerColorXeAY9LY$material3_release(enabled, isError, focused), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, $composer3, 48, 12);
            $composer2 = $composer3;
            BoxKt.Box(TextFieldImplKt.textFieldBackground(BorderKt.border(modifier6, borderStroke.getValue(), shape6), new TextFieldDefaults$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(containerColor) { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$Container$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            }), shape6), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            focusedBorderThickness4 = focusedBorderThickness3;
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
            shape5 = shape6;
            colors4 = colors3;
            modifier4 = modifier6;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$Container$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    OutlinedTextFieldDefaults.this.m2345Container4EFweAY(enabled, isError, interactionSource, modifier4, colors4, shape5, focusedBorderThickness4, unfocusedBorderThickness3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public final void DecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, Function2<? super Composer, ? super Integer, Unit> function28, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function29, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function2 label;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        final TextFieldColors colors2;
        PaddingValues contentPadding2;
        PaddingValues contentPadding3;
        Function2 placeholder;
        int $dirty1;
        Function2 leadingIcon;
        Function2 trailingIcon;
        Function2 supportingText;
        Function2 prefix;
        Function2 leadingIcon2;
        final TextFieldColors colors3;
        Composer $composer2;
        Function2 label2;
        boolean isError2;
        Function2 container;
        Composer $composer3;
        final Function2 prefix2;
        final boolean isError3;
        final Function2 leadingIcon3;
        final Function2 trailingIcon2;
        final Function2 suffix;
        final Function2 container2;
        final Function2 suffix2;
        final PaddingValues contentPadding4;
        final Function2 supportingText2;
        final Function2 supportingText3;
        int i8;
        Composer $composer4 = $composer.startRestartGroup(-350442135);
        ComposerKt.sourceInformation($composer4, "C(DecorationBox)P(15,4,3,11,16,5,6,7,9,8,14,10,12,13!1,2)870@44562L8,872@44674L408,885@45099L709:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty12 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer4.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer4.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer4.changed(singleLine) ? 2048 : 1024;
        }
        int i9 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer4.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer4.changed(interactionSource) ? 131072 : 65536;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
            z = isError;
        } else if (($changed & 1572864) == 0) {
            z = isError;
            $dirty |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = isError;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 12582912;
            label = function22;
        } else if (($changed & 12582912) == 0) {
            label = function22;
            $dirty |= $composer4.changedInstance(label) ? 8388608 : 4194304;
        } else {
            label = function22;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
            i2 = i12;
        } else if (($changed & 100663296) == 0) {
            i2 = i12;
            $dirty |= $composer4.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i12;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty |= 805306368;
            i3 = i13;
        } else if (($changed & 805306368) == 0) {
            i3 = i13;
            $dirty |= $composer4.changedInstance(function24) ? 536870912 : 268435456;
        } else {
            i3 = i13;
        }
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty12 |= 6;
            i4 = i14;
        } else if (($changed1 & 6) == 0) {
            i4 = i14;
            $dirty12 |= $composer4.changedInstance(function25) ? 4 : 2;
        } else {
            i4 = i14;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty12 |= 48;
            i5 = i15;
        } else if (($changed1 & 48) == 0) {
            i5 = i15;
            $dirty12 |= $composer4.changedInstance(function26) ? 32 : 16;
        } else {
            i5 = i15;
        }
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty12 |= 384;
            i6 = i16;
        } else {
            i6 = i16;
            if (($changed1 & 384) == 0) {
                $dirty12 |= $composer4.changedInstance(function27) ? 256 : 128;
            }
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            $dirty12 |= 3072;
            i7 = i17;
        } else {
            i7 = i17;
            if (($changed1 & 3072) == 0) {
                $dirty12 |= $composer4.changedInstance(function28) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer4.changed(colors)) {
                i9 = 16384;
            }
            $dirty12 |= i9;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32768) == 0 && $composer4.changed(contentPadding)) {
                i8 = 131072;
                $dirty12 |= i8;
            }
            i8 = 65536;
            $dirty12 |= i8;
        }
        int i18 = i & 65536;
        if (i18 != 0) {
            $dirty12 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty12 |= $composer4.changedInstance(function29) ? 1048576 : 524288;
        }
        if ((i & 131072) != 0) {
            $dirty12 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty12 |= $composer4.changed(this) ? 8388608 : 4194304;
        }
        if (($dirty & 306783379) == 306783378 && (4793491 & $dirty12) == 4793490 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            supportingText3 = function23;
            leadingIcon3 = function24;
            trailingIcon2 = function25;
            suffix = function27;
            supportingText2 = function28;
            colors3 = colors;
            contentPadding4 = contentPadding;
            container2 = function29;
            $composer3 = $composer4;
            isError3 = z;
            suffix2 = label;
            prefix2 = function26;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                final boolean isError4 = i10 != 0 ? false : z;
                if (i11 != 0) {
                    label = null;
                }
                Function2 placeholder2 = i2 != 0 ? null : function23;
                Function2 leadingIcon4 = i3 != 0 ? null : function24;
                Function2 trailingIcon3 = i4 != 0 ? null : function25;
                Function2 prefix3 = i5 != 0 ? null : function26;
                Function2 suffix3 = i6 != 0 ? null : function27;
                Function2 supportingText4 = i7 != 0 ? null : function28;
                Function2 placeholder3 = placeholder2;
                if ((i & 16384) != 0) {
                    colors2 = colors($composer4, ($dirty12 >> 21) & 14);
                    $dirty12 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = m2344contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty12 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i18 != 0) {
                    boolean isError5 = isError4;
                    Function2 rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1448570018, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer5, int $changed2) {
                            ComposerKt.sourceInformation($composer5, "C879@44918L5,873@44688L384:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 3) != 2 || !$composer5.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1448570018, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:873)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2345Container4EFweAY(enabled, isError4, interactionSource, Modifier.INSTANCE, colors2, OutlinedTextFieldDefaults.INSTANCE.getShape($composer5, 6), OutlinedTextFieldDefaults.INSTANCE.m2349getFocusedBorderThicknessD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2352getUnfocusedBorderThicknessD9Ej5fM(), $composer5, 114822144, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer5.skipToGroupEnd();
                        }
                    }, $composer4, 54);
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty12;
                    leadingIcon = leadingIcon4;
                    trailingIcon = trailingIcon3;
                    supportingText = supportingText4;
                    prefix = prefix3;
                    leadingIcon2 = suffix3;
                    colors3 = colors2;
                    $composer2 = $composer4;
                    label2 = label;
                    isError2 = isError5;
                    container = rememberComposableLambda;
                    placeholder = placeholder3;
                } else {
                    boolean isError6 = isError4;
                    contentPadding3 = contentPadding2;
                    placeholder = placeholder3;
                    $dirty1 = $dirty12;
                    leadingIcon = leadingIcon4;
                    trailingIcon = trailingIcon3;
                    supportingText = supportingText4;
                    prefix = prefix3;
                    leadingIcon2 = suffix3;
                    colors3 = colors2;
                    $composer2 = $composer4;
                    label2 = label;
                    isError2 = isError6;
                    container = function29;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 16384) != 0) {
                    $dirty12 &= -57345;
                }
                if ((32768 & i) != 0) {
                    int i19 = (-458753) & $dirty12;
                    leadingIcon = function24;
                    trailingIcon = function25;
                    prefix = function26;
                    supportingText = function28;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    container = function29;
                    $dirty1 = i19;
                    $composer2 = $composer4;
                    label2 = label;
                    placeholder = function23;
                    isError2 = z;
                    leadingIcon2 = function27;
                } else {
                    placeholder = function23;
                    trailingIcon = function25;
                    prefix = function26;
                    supportingText = function28;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    container = function29;
                    $composer2 = $composer4;
                    $dirty1 = $dirty12;
                    label2 = label;
                    leadingIcon = function24;
                    isError2 = z;
                    leadingIcon2 = function27;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-350442135, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox (TextFieldDefaults.kt:884)");
            }
            Function2 placeholder4 = placeholder;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, function2, visualTransformation, label2, placeholder4, leadingIcon, trailingIcon, prefix, leadingIcon2, supportingText, singleLine, enabled, isError2, interactionSource, contentPadding3, colors3, container, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty >> 6) & 112) | ($dirty & 896) | (($dirty >> 9) & 7168) | (($dirty >> 3) & 57344) | (458752 & $dirty1) | (($dirty1 << 6) & 3670016) | (($dirty1 << 3) & 29360128), 0);
            $composer3 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            boolean z2 = isError2;
            prefix2 = prefix;
            isError3 = z2;
            leadingIcon3 = leadingIcon;
            trailingIcon2 = trailingIcon;
            suffix = leadingIcon2;
            container2 = container;
            suffix2 = label2;
            contentPadding4 = contentPadding3;
            supportingText2 = supportingText;
            supportingText3 = placeholder4;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$2
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

                public final void invoke(Composer composer, int i20) {
                    OutlinedTextFieldDefaults.this.DecorationBox(value, function2, enabled, singleLine, visualTransformation, interactionSource, isError3, suffix2, supportingText3, leadingIcon3, trailingIcon2, prefix2, suffix, supportingText2, colors3, contentPadding4, container2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* renamed from: contentPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2344contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults outlinedTextFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return outlinedTextFieldDefaults.m2348contentPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: contentPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2348contentPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m668PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -471651810, "C(colors)921@46417L11,921@46429L30:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-471651810, $changed, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:921)");
        }
        TextFieldColors defaultOutlinedTextFieldColors = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultOutlinedTextFieldColors;
    }

    /* renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m2347colors0hiis_0(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart($composer, 1767617725, "C(colors)P(30:c#ui.graphics.Color,41:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,23:c#ui.graphics.Color,34:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.graphics.Color,0:c#ui.graphics.Color,13:c#ui.graphics.Color,32,22:c#ui.graphics.Color,33:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,25:c#ui.graphics.Color,36:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,31:c#ui.graphics.Color,42:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,24:c#ui.graphics.Color,35:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,26:c#ui.graphics.Color,37:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,29:c#ui.graphics.Color,40:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,27:c#ui.graphics.Color,38:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,28:c#ui.graphics.Color,39:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)1023@53240L11,1023@53252L30:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorCursorColor;
        SelectionColors selectionColors2 = (i & 1024) != 0 ? null : selectionColors;
        long focusedBorderColor2 = (i & 2048) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedBorderColor;
        long unfocusedBorderColor2 = (i & 4096) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedBorderColor;
        long disabledBorderColor2 = (i & 8192) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledBorderColor;
        long errorBorderColor2 = (i & 16384) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorBorderColor;
        long focusedLeadingIconColor2 = (32768 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (65536 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedLeadingIconColor;
        long disabledLeadingIconColor2 = (131072 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledLeadingIconColor;
        long errorLeadingIconColor2 = (262144 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (524288 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (1048576 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedTrailingIconColor;
        long disabledTrailingIconColor2 = (2097152 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledTrailingIconColor;
        long errorTrailingIconColor2 = (4194304 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorTrailingIconColor;
        long focusedLabelColor2 = (8388608 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedLabelColor;
        long unfocusedLabelColor2 = (16777216 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedLabelColor;
        long disabledLabelColor2 = (33554432 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledLabelColor;
        long errorLabelColor2 = (67108864 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorLabelColor;
        long focusedPlaceholderColor2 = (134217728 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (268435456 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedPlaceholderColor;
        long disabledPlaceholderColor2 = (536870912 & i) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledPlaceholderColor;
        long errorPlaceholderColor2 = (i & BasicMeasure.EXACTLY) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (i2 & 1) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i2 & 2) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedSupportingTextColor;
        long disabledSupportingTextColor2 = (i2 & 4) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledSupportingTextColor;
        long errorSupportingTextColor2 = (i2 & 8) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 16) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 32) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedPrefixColor;
        long disabledPrefixColor2 = (i2 & 64) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledPrefixColor;
        long errorPrefixColor2 = (i2 & 128) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 256) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 512) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedSuffixColor;
        long disabledSuffixColor2 = (i2 & 1024) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledSuffixColor;
        long errorSuffixColor2 = (i2 & 2048) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1767617725, $changed, $changed1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1023)");
        }
        TextFieldColors m2615copyejIjP34 = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed4 >> 6) & 112).m2615copyejIjP34(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedBorderColor2, unfocusedBorderColor2, disabledBorderColor2, errorBorderColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2615copyejIjP34;
    }

    public final TextFieldColors getDefaultOutlinedTextFieldColors(ColorScheme $this$defaultOutlinedTextFieldColors, Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        long m4161copywmQWz5c8;
        long m4161copywmQWz5c9;
        ComposerKt.sourceInformationMarkerStart($composer, -292363577, "C:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-292363577, $changed, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-defaultOutlinedTextFieldColors> (TextFieldDefaults.kt:1071)");
        }
        TextFieldColors it = $this$defaultOutlinedTextFieldColors.getDefaultOutlinedTextFieldColorsCached();
        $composer.startReplaceGroup(1540400102);
        ComposerKt.sourceInformation($composer, "*1086@57012L7");
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusInputColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputColor());
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r11) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor())) : 0.0f);
            long fromToken3 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorInputColor());
            long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
            long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
            long m4198getTransparent0d7_KjU3 = Color.INSTANCE.m4198getTransparent0d7_KjU();
            long m4198getTransparent0d7_KjU4 = Color.INSTANCE.m4198getTransparent0d7_KjU();
            long fromToken4 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getCaretColor());
            long fromToken5 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor());
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long fromToken6 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor());
            long fromToken7 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getOutlineColor());
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r32, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r32) : 0.12f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r32) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r32) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor())) : 0.0f);
            long fromToken8 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor());
            long fromToken9 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor());
            long fromToken10 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor());
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r40, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r40) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r40) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r40) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
            long fromToken11 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor());
            long fromToken12 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor());
            long fromToken13 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor());
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r48, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r48) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r48) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r48) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor())) : 0.0f);
            long fromToken14 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor());
            long fromToken15 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor());
            long fromToken16 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getLabelColor());
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r56, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r56) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r56) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r56) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor())) : 0.0f);
            long fromToken17 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor());
            long fromToken18 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long fromToken19 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r64, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r64) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r64) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r64) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor())) : 0.0f);
            long fromToken20 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long fromToken21 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor());
            long fromToken22 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getSupportingColor());
            m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r72, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r72) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r72) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r72) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor())) : 0.0f);
            long fromToken23 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor());
            long fromToken24 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor());
            long fromToken25 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor());
            m4161copywmQWz5c8 = Color.m4161copywmQWz5c(r80, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r80) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r80) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r80) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor())) : 0.0f);
            long fromToken26 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor());
            long fromToken27 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor());
            long fromToken28 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor());
            m4161copywmQWz5c9 = Color.m4161copywmQWz5c(r88, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r88) : 0.38f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r88) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r88) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor())) : 0.0f);
            it = new TextFieldColors(fromToken, fromToken2, m4161copywmQWz5c, fromToken3, m4198getTransparent0d7_KjU, m4198getTransparent0d7_KjU2, m4198getTransparent0d7_KjU3, m4198getTransparent0d7_KjU4, fromToken4, fromToken5, (SelectionColors) consume, fromToken6, fromToken7, m4161copywmQWz5c2, fromToken8, fromToken9, fromToken10, m4161copywmQWz5c3, fromToken11, fromToken12, fromToken13, m4161copywmQWz5c4, fromToken14, fromToken15, fromToken16, m4161copywmQWz5c5, fromToken17, fromToken18, fromToken19, m4161copywmQWz5c6, fromToken20, fromToken21, fromToken22, m4161copywmQWz5c7, fromToken23, fromToken24, fromToken25, m4161copywmQWz5c8, fromToken26, fromToken27, fromToken28, m4161copywmQWz5c9, ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor()), null);
            $this$defaultOutlinedTextFieldColors.setDefaultOutlinedTextFieldColorsCached$material3_release(it);
        }
        $composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return it;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to OutlinedTextFieldDefaults.Container", replaceWith = @ReplaceWith(expression = "Container(\n    enabled = enabled,\n    isError = isError,\n    interactionSource = interactionSource,\n    colors = colors,\n    shape = shape,\n    focusedBorderThickness = focusedBorderThickness,\n    unfocusedBorderThickness = unfocusedBorderThickness,\n)", imports = {}))
    /* renamed from: ContainerBox-nbWgWpA, reason: not valid java name */
    public final void m2346ContainerBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        InteractionSource interactionSource2;
        TextFieldColors colors2;
        Shape shape2;
        float focusedBorderThickness2;
        float unfocusedBorderThickness2;
        int i2;
        int $dirty;
        final TextFieldColors colors3;
        final Shape shape3;
        final float focusedBorderThickness3;
        final float unfocusedBorderThickness3;
        Composer $composer2;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(1461761386);
        ComposerKt.sourceInformation($composer3, "C(ContainerBox)P(1,4,3!1,5,2:c#ui.unit.Dp,6:c#ui.unit.Dp)1174@62656L8,1175@62715L5,1179@62864L348:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            z = enabled;
        } else if (($changed & 6) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = enabled;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            z2 = isError;
        } else if (($changed & 48) == 0) {
            z2 = isError;
            $dirty2 |= $composer3.changed(z2) ? 32 : 16;
        } else {
            z2 = isError;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
            interactionSource2 = interactionSource;
        } else if (($changed & 384) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 256 : 128;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i6 = 2048;
                    $dirty2 |= i6;
                }
            } else {
                colors2 = colors;
            }
            i6 = 1024;
            $dirty2 |= i6;
        } else {
            colors2 = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i5 = 16384;
                    $dirty2 |= i5;
                }
            } else {
                shape2 = shape;
            }
            i5 = 8192;
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                focusedBorderThickness2 = focusedBorderThickness;
                if ($composer3.changed(focusedBorderThickness2)) {
                    i4 = 131072;
                    $dirty2 |= i4;
                }
            } else {
                focusedBorderThickness2 = focusedBorderThickness;
            }
            i4 = 65536;
            $dirty2 |= i4;
        } else {
            focusedBorderThickness2 = focusedBorderThickness;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                unfocusedBorderThickness2 = unfocusedBorderThickness;
                if ($composer3.changed(unfocusedBorderThickness2)) {
                    i3 = 1048576;
                    $dirty2 |= i3;
                }
            } else {
                unfocusedBorderThickness2 = unfocusedBorderThickness;
            }
            i3 = 524288;
            $dirty2 |= i3;
        } else {
            unfocusedBorderThickness2 = unfocusedBorderThickness;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(this) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty2) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            colors3 = colors2;
            shape3 = shape2;
            focusedBorderThickness3 = focusedBorderThickness2;
            $composer2 = $composer3;
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if ((i & 8) != 0) {
                    colors2 = colors($composer3, ($dirty2 >> 21) & 14);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    i2 = -3670017;
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    i2 = -3670017;
                }
                if ((i & 32) != 0) {
                    focusedBorderThickness2 = FocusedBorderThickness;
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    $dirty = $dirty2 & i2;
                } else {
                    $dirty = $dirty2;
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
                    $dirty2 &= -458753;
                }
                $dirty = (i & 64) != 0 ? $dirty2 & (-3670017) : $dirty2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1461761386, $dirty, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.ContainerBox (TextFieldDefaults.kt:1179)");
            }
            boolean z3 = z;
            InteractionSource interactionSource3 = interactionSource2;
            colors3 = colors2;
            shape3 = shape2;
            focusedBorderThickness3 = focusedBorderThickness2;
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
            m2345Container4EFweAY(z3, z2, interactionSource3, Modifier.INSTANCE, colors3, shape3, focusedBorderThickness3, unfocusedBorderThickness3, $composer3, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (234881024 & ($dirty << 3)), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$ContainerBox$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    OutlinedTextFieldDefaults.this.m2346ContainerBoxnbWgWpA(enabled, isError, interactionSource, colors3, shape3, focusedBorderThickness3, unfocusedBorderThickness3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
