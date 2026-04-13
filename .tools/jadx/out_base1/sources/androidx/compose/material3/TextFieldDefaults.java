package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.internal.TextFieldType;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.PropertyReference0Impl;

/* compiled from: TextFieldDefaults.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b7\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\\\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010-\u001a\u00020\u00042\b\b\u0002\u0010.\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100J7\u00101\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010\u001c\u001a\u00020\u0015H\u0007¢\u0006\u0002\u00102J¦\u0002\u00103\u001a\u00020$2\u0006\u00104\u001a\u0002052\u0011\u00106\u001a\r\u0012\u0004\u0012\u00020$07¢\u0006\u0002\b82\u0006\u0010%\u001a\u00020&2\u0006\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020;2\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010'\u001a\u00020&2\u0015\b\u0002\u0010<\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010=\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010?\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010@\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010A\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010B\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010C\u001a\u00020D2\u0013\b\u0002\u0010E\u001a\r\u0012\u0004\u0012\u00020$07¢\u0006\u0002\b8H\u0007¢\u0006\u0002\u0010FJ\r\u0010,\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010GJÂ\u0003\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010H\u001a\u00020I2\b\b\u0002\u0010J\u001a\u00020I2\b\b\u0002\u0010K\u001a\u00020I2\b\b\u0002\u0010L\u001a\u00020I2\b\b\u0002\u0010M\u001a\u00020I2\b\b\u0002\u0010N\u001a\u00020I2\b\b\u0002\u0010O\u001a\u00020I2\b\b\u0002\u0010P\u001a\u00020I2\b\b\u0002\u0010Q\u001a\u00020I2\b\b\u0002\u0010R\u001a\u00020I2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010T2\b\b\u0002\u0010U\u001a\u00020I2\b\b\u0002\u0010V\u001a\u00020I2\b\b\u0002\u0010W\u001a\u00020I2\b\b\u0002\u0010X\u001a\u00020I2\b\b\u0002\u0010Y\u001a\u00020I2\b\b\u0002\u0010Z\u001a\u00020I2\b\b\u0002\u0010[\u001a\u00020I2\b\b\u0002\u0010\\\u001a\u00020I2\b\b\u0002\u0010]\u001a\u00020I2\b\b\u0002\u0010^\u001a\u00020I2\b\b\u0002\u0010_\u001a\u00020I2\b\b\u0002\u0010`\u001a\u00020I2\b\b\u0002\u0010a\u001a\u00020I2\b\b\u0002\u0010b\u001a\u00020I2\b\b\u0002\u0010c\u001a\u00020I2\b\b\u0002\u0010d\u001a\u00020I2\b\b\u0002\u0010e\u001a\u00020I2\b\b\u0002\u0010f\u001a\u00020I2\b\b\u0002\u0010g\u001a\u00020I2\b\b\u0002\u0010h\u001a\u00020I2\b\b\u0002\u0010i\u001a\u00020I2\b\b\u0002\u0010j\u001a\u00020I2\b\b\u0002\u0010k\u001a\u00020I2\b\b\u0002\u0010l\u001a\u00020I2\b\b\u0002\u0010m\u001a\u00020I2\b\b\u0002\u0010n\u001a\u00020I2\b\b\u0002\u0010o\u001a\u00020I2\b\b\u0002\u0010p\u001a\u00020I2\b\b\u0002\u0010q\u001a\u00020I2\b\b\u0002\u0010r\u001a\u00020I2\b\b\u0002\u0010s\u001a\u00020I2\b\b\u0002\u0010t\u001a\u00020IH\u0007ø\u0001\u0000¢\u0006\u0004\bu\u0010vJ8\u0010w\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b|\u0010}J8\u0010~\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u007f\u0010}J<\u0010\u0080\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0005\b\u0081\u0001\u0010}J<\u0010\u0082\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0005\b\u0083\u0001\u0010}J<\u0010\u0084\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0005\b\u0085\u0001\u0010}J<\u0010\u0086\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0005\b\u0087\u0001\u0010}JM\u0010\u0088\u0001\u001a\u00020+*\u00020+2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010-\u001a\u00020\u00042\b\b\u0002\u0010.\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0019\u0010\r\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R$\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0007R\u0019\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0013\u0010\u0007R\u001a\u0010\u0014\u001a\u00020\u00158GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00158GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u0002\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u001c\u001a\u00020\u00158G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0018R\u0018\u0010\u001e\u001a\u00020\u001f*\u00020 8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u008b\u0001"}, d2 = {"Landroidx/compose/material3/TextFieldDefaults;", "", "()V", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM$annotations", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "FocusedIndicatorThickness", "getFocusedIndicatorThickness-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM$annotations", "getUnfocusedBorderThickness-D9Ej5fM", "UnfocusedIndicatorThickness", "getUnfocusedIndicatorThickness-D9Ej5fM", "filledShape", "Landroidx/compose/ui/graphics/Shape;", "getFilledShape$annotations", "getFilledShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape$annotations", "getOutlinedShape", "shape", "getShape", "defaultTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultTextFieldColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", TextFieldImplKt.ContainerId, "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "Container-4EFweAY", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "ContainerBox", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "DecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "container", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "contentPaddingWithLabel", "start", "end", "top", "bottom", "contentPaddingWithLabel-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "contentPaddingWithoutLabel", "contentPaddingWithoutLabel-a9UjIt4", "outlinedTextFieldPadding", "outlinedTextFieldPadding-a9UjIt4", "supportingTextPadding", "supportingTextPadding-a9UjIt4$material3_release", "textFieldWithLabelPadding", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "indicatorLine", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TextFieldDefaults {
    public static final int $stable = 0;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m6626constructorimpl(56);
    private static final float MinWidth = Dp.m6626constructorimpl(280);
    private static final float UnfocusedIndicatorThickness = Dp.m6626constructorimpl(1);
    private static final float FocusedIndicatorThickness = Dp.m6626constructorimpl(2);
    private static final float UnfocusedBorderThickness = UnfocusedIndicatorThickness;
    private static final float FocusedBorderThickness = FocusedIndicatorThickness;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.shape", imports = {}))
    public static /* synthetic */ void getFilledShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.FocusedIndicatorThickness` and `OutlinedTextFieldDefaults.FocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.FocusedIndicatorThickness", imports = {}))
    /* renamed from: getFocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m2670getFocusedBorderThicknessD9Ej5fM$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.shape", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    public static /* synthetic */ void getOutlinedShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.UnfocusedIndicatorThickness` and `OutlinedTextFieldDefaults.UnfocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.UnfocusedIndicatorThickness", imports = {}))
    /* renamed from: getUnfocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m2671getUnfocusedBorderThicknessD9Ej5fM$annotations() {
    }

    private TextFieldDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1941327459, "C60@2756L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1941327459, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-shape> (TextFieldDefaults.kt:60)");
        }
        Shape value = ShapesKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m2683getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m2684getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* renamed from: getUnfocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m2686getUnfocusedIndicatorThicknessD9Ej5fM() {
        return UnfocusedIndicatorThickness;
    }

    /* renamed from: getFocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m2682getFocusedIndicatorThicknessD9Ej5fM() {
        return FocusedIndicatorThickness;
    }

    /* renamed from: Container-4EFweAY, reason: not valid java name */
    public final void m2677Container4EFweAY(final boolean enabled, final boolean isError, final InteractionSource interactionSource, Modifier modifier, TextFieldColors colors, Shape shape, float focusedIndicatorLineThickness, float unfocusedIndicatorLineThickness, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TextFieldColors colors2;
        Shape shape2;
        float focusedIndicatorLineThickness2;
        float f;
        Shape shape3;
        float unfocusedIndicatorLineThickness2;
        int $dirty;
        float focusedIndicatorLineThickness3;
        Composer $composer2;
        final TextFieldColors colors3;
        final Modifier modifier3;
        final float focusedIndicatorLineThickness4;
        final float unfocusedIndicatorLineThickness3;
        final Shape shape4;
        int i2;
        int i3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-818661242);
        ComposerKt.sourceInformation($composer3, "C(Container)P(1,4,3,5!1,6,2:c#ui.unit.Dp,7:c#ui.unit.Dp)105@4681L8,106@4732L5,110@4933L25,112@5006L198,116@5213L496:TextFieldDefaults.kt#uh7d8r");
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
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i5 = 16384;
                    $dirty2 |= i5;
                }
            } else {
                colors2 = colors;
            }
            i5 = 8192;
            $dirty2 |= i5;
        } else {
            colors2 = colors;
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
                focusedIndicatorLineThickness2 = focusedIndicatorLineThickness;
                if ($composer3.changed(focusedIndicatorLineThickness2)) {
                    i3 = 1048576;
                    $dirty2 |= i3;
                }
            } else {
                focusedIndicatorLineThickness2 = focusedIndicatorLineThickness;
            }
            i3 = 524288;
            $dirty2 |= i3;
        } else {
            focusedIndicatorLineThickness2 = focusedIndicatorLineThickness;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                f = unfocusedIndicatorLineThickness;
                if ($composer3.changed(f)) {
                    i2 = 8388608;
                    $dirty2 |= i2;
                }
            } else {
                f = unfocusedIndicatorLineThickness;
            }
            i2 = 4194304;
            $dirty2 |= i2;
        } else {
            f = unfocusedIndicatorLineThickness;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & $dirty2) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            colors3 = colors2;
            shape4 = shape2;
            unfocusedIndicatorLineThickness3 = f;
            $composer2 = $composer3;
            modifier3 = modifier2;
            focusedIndicatorLineThickness4 = focusedIndicatorLineThickness2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 16) != 0) {
                    TextFieldColors colors4 = colors($composer3, ($dirty2 >> 24) & 14);
                    $dirty2 &= -57345;
                    colors2 = colors4;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    shape2 = INSTANCE.getShape($composer3, 6);
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                    focusedIndicatorLineThickness2 = FocusedIndicatorThickness;
                }
                if ((i & 128) != 0) {
                    unfocusedIndicatorLineThickness2 = UnfocusedIndicatorThickness;
                    shape3 = shape2;
                    $dirty = $dirty2 & (-29360129);
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                } else {
                    shape3 = shape2;
                    unfocusedIndicatorLineThickness2 = f;
                    $dirty = $dirty2;
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
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
                    Shape shape5 = shape2;
                    $dirty = i7;
                    shape3 = shape5;
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                    unfocusedIndicatorLineThickness2 = f;
                } else {
                    shape3 = shape2;
                    unfocusedIndicatorLineThickness2 = f;
                    $dirty = $dirty2;
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-818661242, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.Container (TextFieldDefaults.kt:109)");
            }
            boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer3, ($dirty >> 6) & 14).getValue().booleanValue();
            final State containerColor = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(colors2.m2614containerColorXeAY9LY$material3_release(enabled, isError, focused), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, $composer3, 48, 12);
            $composer2 = $composer3;
            TextFieldColors colors5 = colors2;
            Shape shape6 = shape3;
            BoxKt.Box(m2687indicatorLinegv0btCI(TextFieldImplKt.textFieldBackground(modifier2, new TextFieldDefaults$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(containerColor) { // from class: androidx.compose.material3.TextFieldDefaults$Container$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            }), shape3), enabled, isError, interactionSource, colors5, focusedIndicatorLineThickness3, unfocusedIndicatorLineThickness2), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            float f2 = focusedIndicatorLineThickness3;
            colors3 = colors5;
            modifier3 = modifier2;
            focusedIndicatorLineThickness4 = f2;
            unfocusedIndicatorLineThickness3 = unfocusedIndicatorLineThickness2;
            shape4 = shape6;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$Container$2
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
                    TextFieldDefaults.this.m2677Container4EFweAY(enabled, isError, interactionSource, modifier3, colors3, shape4, focusedIndicatorLineThickness4, unfocusedIndicatorLineThickness3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2672indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedIndicatorThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedIndicatorThickness;
        }
        return textFieldDefaults.m2687indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    /* renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m2687indicatorLinegv0btCI(Modifier $this$indicatorLine_u2dgv0btCI, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, final float focusedIndicatorLineThickness, final float unfocusedIndicatorLineThickness) {
        return ComposedModifierKt.composed($this$indicatorLine_u2dgv0btCI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("indicatorLine");
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("isError", Boolean.valueOf(isError));
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("colors", colors);
                $this$null.getProperties().set("focusedIndicatorLineThickness", Dp.m6624boximpl(focusedIndicatorLineThickness));
                $this$null.getProperties().set("unfocusedIndicatorLineThickness", Dp.m6624boximpl(unfocusedIndicatorLineThickness));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                $composer.startReplaceGroup(-891038934);
                ComposerKt.sourceInformation($composer, "C169@7666L25,171@7739L263:TextFieldDefaults.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-891038934, $changed, -1, "androidx.compose.material3.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:169)");
                }
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(InteractionSource.this, $composer, 0).getValue().booleanValue();
                State stroke = TextFieldImplKt.m2985animateBorderStrokeAsStateNuRrP5Q(enabled, isError, focused, colors, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness, $composer, 0);
                Modifier drawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.INSTANCE, stroke);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return drawIndicatorLine;
            }
        });
    }

    public final void DecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, Function2<? super Composer, ? super Integer, Unit> function28, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function29, Composer $composer, final int $changed, final int $changed1, final int i) {
        String str;
        boolean z;
        boolean z2;
        InteractionSource interactionSource2;
        boolean isError2;
        Function2 label;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Function2 placeholder;
        Shape shape2;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        boolean isError3;
        final Function2 trailingIcon;
        Shape shape3;
        Function2 container;
        int $dirty1;
        final Function2 label2;
        final Function2 prefix;
        final Function2 supportingText;
        final Function2 placeholder2;
        PaddingValues contentPadding3;
        TextFieldColors colors3;
        final Function2 leadingIcon;
        final Function2 suffix;
        Composer $composer2;
        final Shape shape4;
        final boolean isError4;
        final PaddingValues contentPadding4;
        final TextFieldColors colors4;
        final Function2 container2;
        int i8;
        int i9;
        Composer $composer3 = $composer.startRestartGroup(289640444);
        ComposerKt.sourceInformation($composer3, "C(DecorationBox)P(16,4,3,12,17,5,6,7,9,8,15,10,13,14,11!1,2)256@13034L5,257@13075L8,264@13325L428,277@13770L707:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty12 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
            str = value;
        } else if (($changed & 6) == 0) {
            str = value;
            $dirty |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = value;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            z2 = singleLine;
        } else if (($changed & 3072) == 0) {
            z2 = singleLine;
            $dirty |= $composer3.changed(z2) ? 2048 : 1024;
        } else {
            z2 = singleLine;
        }
        int i10 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            interactionSource2 = interactionSource;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer3.changed(interactionSource2) ? 131072 : 65536;
        } else {
            interactionSource2 = interactionSource;
        }
        int i11 = i & 64;
        if (i11 != 0) {
            $dirty |= 1572864;
            isError2 = isError;
        } else if (($changed & 1572864) == 0) {
            isError2 = isError;
            $dirty |= $composer3.changed(isError2) ? 1048576 : 524288;
        } else {
            isError2 = isError;
        }
        int i12 = i & 128;
        if (i12 != 0) {
            $dirty |= 12582912;
            label = function22;
        } else if (($changed & 12582912) == 0) {
            label = function22;
            $dirty |= $composer3.changedInstance(label) ? 8388608 : 4194304;
        } else {
            label = function22;
        }
        int i13 = i & 256;
        if (i13 != 0) {
            $dirty |= 100663296;
            i2 = i13;
        } else if (($changed & 100663296) == 0) {
            i2 = i13;
            $dirty |= $composer3.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i13;
        }
        int i14 = i & 512;
        if (i14 != 0) {
            $dirty |= 805306368;
            i3 = i14;
        } else if (($changed & 805306368) == 0) {
            i3 = i14;
            $dirty |= $composer3.changedInstance(function24) ? 536870912 : 268435456;
        } else {
            i3 = i14;
        }
        int i15 = i & 1024;
        if (i15 != 0) {
            $dirty12 |= 6;
            i4 = i15;
        } else if (($changed1 & 6) == 0) {
            i4 = i15;
            $dirty12 |= $composer3.changedInstance(function25) ? 4 : 2;
        } else {
            i4 = i15;
        }
        int i16 = i & 2048;
        if (i16 != 0) {
            $dirty12 |= 48;
            i5 = i16;
        } else if (($changed1 & 48) == 0) {
            i5 = i16;
            $dirty12 |= $composer3.changedInstance(function26) ? 32 : 16;
        } else {
            i5 = i16;
        }
        int i17 = i & 4096;
        if (i17 != 0) {
            $dirty12 |= 384;
            i6 = i17;
        } else {
            i6 = i17;
            if (($changed1 & 384) == 0) {
                $dirty12 |= $composer3.changedInstance(function27) ? 256 : 128;
            }
        }
        int i18 = i & 8192;
        if (i18 != 0) {
            $dirty12 |= 3072;
            i7 = i18;
        } else {
            i7 = i18;
            if (($changed1 & 3072) == 0) {
                $dirty12 |= $composer3.changedInstance(function28) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(shape)) {
                i10 = 16384;
            }
            $dirty12 |= i10;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32768) == 0 && $composer3.changed(colors)) {
                i9 = 131072;
                $dirty12 |= i9;
            }
            i9 = 65536;
            $dirty12 |= i9;
        }
        if (($changed1 & 1572864) == 0) {
            if ((i & 65536) == 0 && $composer3.changed(contentPadding)) {
                i8 = 1048576;
                $dirty12 |= i8;
            }
            i8 = 524288;
            $dirty12 |= i8;
        }
        int i19 = i & 131072;
        if (i19 != 0) {
            $dirty12 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty12 |= $composer3.changedInstance(function29) ? 8388608 : 4194304;
        }
        if ((i & 262144) != 0) {
            $dirty12 |= 100663296;
        } else if (($changed1 & 100663296) == 0) {
            $dirty12 |= $composer3.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty & 306783379) == 306783378 && (38347923 & $dirty12) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            placeholder2 = function23;
            leadingIcon = function24;
            prefix = function26;
            suffix = function27;
            shape4 = shape;
            colors4 = colors;
            contentPadding4 = contentPadding;
            container2 = function29;
            $composer2 = $composer3;
            isError4 = isError2;
            label2 = label;
            trailingIcon = function25;
            supportingText = function28;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i11 != 0) {
                    isError2 = false;
                }
                if (i12 != 0) {
                    label = null;
                }
                Function2 placeholder3 = i2 != 0 ? null : function23;
                Function2 leadingIcon2 = i3 != 0 ? null : function24;
                Function2 trailingIcon2 = i4 != 0 ? null : function25;
                Function2 prefix2 = i5 != 0 ? null : function26;
                Function2 suffix2 = i6 != 0 ? null : function27;
                Function2 supportingText2 = i7 != 0 ? null : function28;
                if ((i & 16384) != 0) {
                    placeholder = placeholder3;
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty12 &= -57345;
                } else {
                    placeholder = placeholder3;
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = colors($composer3, ($dirty12 >> 24) & 14);
                    $dirty12 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    contentPadding2 = label == null ? m2669contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : m2668contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty12 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i19 != 0) {
                    final TextFieldColors colors5 = colors2;
                    final Shape shape5 = shape2;
                    final InteractionSource interactionSource3 = interactionSource2;
                    final boolean isError5 = isError2;
                    final boolean z3 = z;
                    PaddingValues contentPadding5 = contentPadding2;
                    Function2 function210 = trailingIcon2;
                    isError3 = isError2;
                    trailingIcon = function210;
                    container = ComposableLambdaKt.rememberComposableLambda(-435523791, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$DecorationBox$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C265@13339L404:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-435523791, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:265)");
                                }
                                TextFieldDefaults.INSTANCE.m2677Container4EFweAY(z3, isError5, interactionSource3, Modifier.INSTANCE, colors5, shape5, TextFieldDefaults.INSTANCE.m2682getFocusedIndicatorThicknessD9Ej5fM(), TextFieldDefaults.INSTANCE.m2686getUnfocusedIndicatorThicknessD9Ej5fM(), $composer4, 114822144, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    }, $composer3, 54);
                    $dirty1 = $dirty12;
                    label2 = label;
                    prefix = prefix2;
                    supportingText = supportingText2;
                    placeholder2 = placeholder;
                    contentPadding3 = contentPadding5;
                    shape3 = shape5;
                    colors3 = colors5;
                    leadingIcon = leadingIcon2;
                    suffix = suffix2;
                } else {
                    TextFieldColors colors6 = colors2;
                    PaddingValues contentPadding6 = contentPadding2;
                    Function2 function211 = trailingIcon2;
                    isError3 = isError2;
                    trailingIcon = function211;
                    shape3 = shape2;
                    container = function29;
                    $dirty1 = $dirty12;
                    label2 = label;
                    prefix = prefix2;
                    supportingText = supportingText2;
                    placeholder2 = placeholder;
                    contentPadding3 = contentPadding6;
                    colors3 = colors6;
                    leadingIcon = leadingIcon2;
                    suffix = suffix2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16384) != 0) {
                    $dirty12 &= -57345;
                }
                if ((32768 & i) != 0) {
                    $dirty12 &= -458753;
                }
                if ((i & 65536) != 0) {
                    placeholder2 = function23;
                    leadingIcon = function24;
                    prefix = function26;
                    suffix = function27;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    container = function29;
                    $dirty1 = (-3670017) & $dirty12;
                    isError3 = isError2;
                    label2 = label;
                    trailingIcon = function25;
                    supportingText = function28;
                    shape3 = shape;
                } else {
                    placeholder2 = function23;
                    leadingIcon = function24;
                    prefix = function26;
                    suffix = function27;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    container = function29;
                    $dirty1 = $dirty12;
                    isError3 = isError2;
                    label2 = label;
                    trailingIcon = function25;
                    supportingText = function28;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(289640444, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.DecorationBox (TextFieldDefaults.kt:276)");
            }
            Shape shape6 = shape3;
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, str, function2, visualTransformation, label2, placeholder2, leadingIcon, trailingIcon, prefix, suffix, supportingText, z2, enabled, isError3, interactionSource, contentPadding3, colors3, container, $composer2, (($dirty << 3) & 896) | (($dirty << 3) & 112) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty >> 6) & 112) | ($dirty & 896) | (($dirty >> 9) & 7168) | (($dirty >> 3) & 57344) | (($dirty1 >> 3) & 458752) | (($dirty1 << 3) & 3670016) | (29360128 & $dirty1), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape6;
            isError4 = isError3;
            contentPadding4 = contentPadding3;
            colors4 = colors3;
            container2 = container;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$DecorationBox$2
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
                    TextFieldDefaults.this.DecorationBox(value, function2, enabled, singleLine, visualTransformation, interactionSource, isError4, label2, placeholder2, leadingIcon, trailingIcon, prefix, suffix, supportingText, shape4, colors4, contentPadding4, container2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* renamed from: contentPaddingWithLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2668contentPaddingWithLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m2679contentPaddingWithLabela9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: contentPaddingWithLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2679contentPaddingWithLabela9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m668PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* renamed from: contentPaddingWithoutLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2669contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m2680contentPaddingWithoutLabela9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: contentPaddingWithoutLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2680contentPaddingWithoutLabela9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m668PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* renamed from: supportingTextPadding-a9UjIt4$material3_release$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2674supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getSupportingTopPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = Dp.m6626constructorimpl(0);
        }
        return textFieldDefaults.m2689supportingTextPaddinga9UjIt4$material3_release(f, f2, f3, f4);
    }

    /* renamed from: supportingTextPadding-a9UjIt4$material3_release, reason: not valid java name */
    public final PaddingValues m2689supportingTextPaddinga9UjIt4$material3_release(float start, float top, float end, float bottom) {
        return PaddingKt.m668PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 831731228, "C(colors)336@16080L11,336@16092L22:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(831731228, $changed, -1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:336)");
        }
        TextFieldColors defaultTextFieldColors = getDefaultTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldColors;
    }

    /* renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m2678colors0hiis_0(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart($composer, 1513344955, "C(colors)P(30:c#ui.graphics.Color,41:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,22:c#ui.graphics.Color,33:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,0:c#ui.graphics.Color,12:c#ui.graphics.Color,32,23:c#ui.graphics.Color,34:c#ui.graphics.Color,2:c#ui.graphics.Color,13:c#ui.graphics.Color,25:c#ui.graphics.Color,36:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,31:c#ui.graphics.Color,42:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,24:c#ui.graphics.Color,35:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,26:c#ui.graphics.Color,37:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,29:c#ui.graphics.Color,40:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,27:c#ui.graphics.Color,38:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,28:c#ui.graphics.Color,39:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)438@22922L11,438@22934L22:TextFieldDefaults.kt#uh7d8r");
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
        long focusedIndicatorColor2 = (i & 2048) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : focusedIndicatorColor;
        long unfocusedIndicatorColor2 = (i & 4096) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : unfocusedIndicatorColor;
        long disabledIndicatorColor2 = (i & 8192) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : disabledIndicatorColor;
        long errorIndicatorColor2 = (i & 16384) != 0 ? Color.INSTANCE.m4199getUnspecified0d7_KjU() : errorIndicatorColor;
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
            ComposerKt.traceEventStart(1513344955, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:438)");
        }
        TextFieldColors m2615copyejIjP34 = getDefaultTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed4 >> 6) & 112).m2615copyejIjP34(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedIndicatorColor2, unfocusedIndicatorColor2, disabledIndicatorColor2, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2615copyejIjP34;
    }

    public final TextFieldColors getDefaultTextFieldColors(ColorScheme $this$defaultTextFieldColors, Composer $composer, int $changed) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        long m4161copywmQWz5c8;
        long m4161copywmQWz5c9;
        ComposerKt.sourceInformationMarkerStart($composer, 1341970309, "C:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1341970309, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-defaultTextFieldColors> (TextFieldDefaults.kt:486)");
        }
        TextFieldColors it = $this$defaultTextFieldColors.getDefaultTextFieldColorsCached();
        $composer.startReplaceGroup(27085453);
        ComposerKt.sourceInformation($composer, "*501@26788L7");
        if (it == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusInputColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputColor());
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r11) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledInputColor())) : 0.0f);
            long fromToken3 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorInputColor());
            long fromToken4 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long fromToken5 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long fromToken6 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long fromToken7 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long fromToken8 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getCaretColor());
            long fromToken9 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor());
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long fromToken10 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor());
            long fromToken11 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor());
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r32, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r32) : FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r32) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r32) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor())) : 0.0f);
            long fromToken12 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor());
            long fromToken13 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor());
            long fromToken14 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getLeadingIconColor());
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r40, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r40) : FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r40) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r40) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
            long fromToken15 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor());
            long fromToken16 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor());
            long fromToken17 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getTrailingIconColor());
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r48, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r48) : FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r48) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r48) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor())) : 0.0f);
            long fromToken18 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor());
            long fromToken19 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusLabelColor());
            long fromToken20 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getLabelColor());
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r56, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r56) : FilledTextFieldTokens.INSTANCE.getDisabledLabelOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r56) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r56) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledLabelColor())) : 0.0f);
            long fromToken21 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorLabelColor());
            long fromToken22 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long fromToken23 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r64, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r64) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r64) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r64) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledInputColor())) : 0.0f);
            long fromToken24 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long fromToken25 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusSupportingColor());
            long fromToken26 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getSupportingColor());
            m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r72, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r72) : FilledTextFieldTokens.INSTANCE.getDisabledSupportingOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r72) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r72) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor())) : 0.0f);
            long fromToken27 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorSupportingColor());
            long fromToken28 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
            long fromToken29 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
            m4161copywmQWz5c8 = Color.m4161copywmQWz5c(r80, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r80) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r80) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r80) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor())) : 0.0f);
            long fromToken30 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
            long fromToken31 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
            long fromToken32 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
            m4161copywmQWz5c9 = Color.m4161copywmQWz5c(r88, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r88) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r88) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r88) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor())) : 0.0f);
            it = new TextFieldColors(fromToken, fromToken2, m4161copywmQWz5c, fromToken3, fromToken4, fromToken5, fromToken6, fromToken7, fromToken8, fromToken9, (SelectionColors) consume, fromToken10, fromToken11, m4161copywmQWz5c2, fromToken12, fromToken13, fromToken14, m4161copywmQWz5c3, fromToken15, fromToken16, fromToken17, m4161copywmQWz5c4, fromToken18, fromToken19, fromToken20, m4161copywmQWz5c5, fromToken21, fromToken22, fromToken23, m4161copywmQWz5c6, fromToken24, fromToken25, fromToken26, m4161copywmQWz5c7, fromToken27, fromToken28, fromToken29, m4161copywmQWz5c8, fromToken30, fromToken31, fromToken32, m4161copywmQWz5c9, ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor()), null);
            $this$defaultTextFieldColors.setDefaultTextFieldColorsCached$material3_release(it);
        }
        $composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return it;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x014d  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.WARNING, message = "Renamed to TextFieldDefaults.Container", replaceWith = @kotlin.ReplaceWith(expression = "Container(\n    enabled = enabled,\n    isError = isError,\n    interactionSource = interactionSource,\n    colors = colors,\n    shape = shape,\n)", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void ContainerBox(final boolean r21, final boolean r22, final androidx.compose.foundation.interaction.InteractionSource r23, final androidx.compose.material3.TextFieldColors r24, androidx.compose.ui.graphics.Shape r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.ContainerBox(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -584749279, "C613@33100L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-584749279, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-outlinedShape> (TextFieldDefaults.kt:613)");
        }
        Shape shape = OutlinedTextFieldDefaults.INSTANCE.getShape($composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return shape;
    }

    public final Shape getFilledShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 611926497, "C621@33346L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(611926497, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-filledShape> (TextFieldDefaults.kt:621)");
        }
        Shape shape = getShape($composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return shape;
    }

    /* renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2685getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2681getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2675textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m2690textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2690textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return m2679contentPaddingWithLabela9UjIt4(start, end, top, bottom);
    }

    /* renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2676textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m2691textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithoutLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithoutLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2691textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return m2680contentPaddingWithoutLabela9UjIt4(start, top, end, bottom);
    }

    /* renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2673outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m2688outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.contentPadding`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.contentPadding(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    /* renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2688outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return OutlinedTextFieldDefaults.INSTANCE.m2348contentPaddinga9UjIt4(start, top, end, bottom);
    }
}
