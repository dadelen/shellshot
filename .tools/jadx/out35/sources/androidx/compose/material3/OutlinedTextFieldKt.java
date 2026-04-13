package androidx.compose.material3;

import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.TypeScaleTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: OutlinedTextField.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aØ\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020'2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.H\u0007¢\u0006\u0002\u0010/\u001aØ\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u0002002\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020'2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.H\u0007¢\u0006\u0002\u00101\u001a\u0080\u0002\u00102\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\t0\u0016¢\u0006\u0002\b\u00172\u0019\u0010\u0018\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\r¢\u0006\u0002\b\u00172\u0013\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u00104\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u00105\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0006\u0010%\u001a\u00020\u00112\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\t0\r2\u0011\u0010:\u001a\r\u0012\u0004\u0012\u00020\t0\u0016¢\u0006\u0002\b\u00172\u0013\u0010;\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016¢\u0006\u0002\b\u00172\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\u0010>\u001ar\u0010?\u001a\u00020'2\u0006\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020'2\u0006\u0010B\u001a\u00020'2\u0006\u0010C\u001a\u00020'2\u0006\u0010D\u001a\u00020'2\u0006\u0010E\u001a\u00020'2\u0006\u0010F\u001a\u00020'2\u0006\u0010G\u001a\u00020'2\u0006\u00106\u001a\u0002072\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u0002072\u0006\u0010<\u001a\u00020=H\u0002ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001aj\u0010M\u001a\u00020'2\u0006\u0010N\u001a\u00020'2\u0006\u0010O\u001a\u00020'2\u0006\u0010P\u001a\u00020'2\u0006\u0010Q\u001a\u00020'2\u0006\u0010R\u001a\u00020'2\u0006\u0010S\u001a\u00020'2\u0006\u0010T\u001a\u00020'2\u0006\u00106\u001a\u0002072\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u0002072\u0006\u0010<\u001a\u00020=H\u0002ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001a\"\u0010W\u001a\u00020\u000f*\u00020\u000f2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002090\u00162\u0006\u0010<\u001a\u00020=H\u0000\u001a\u009a\u0001\u0010Y\u001a\u00020\t*\u00020Z2\u0006\u0010[\u001a\u00020'2\u0006\u0010\\\u001a\u00020'2\b\u0010]\u001a\u0004\u0018\u00010^2\b\u0010_\u001a\u0004\u0018\u00010^2\b\u0010`\u001a\u0004\u0018\u00010^2\b\u0010a\u001a\u0004\u0018\u00010^2\u0006\u0010b\u001a\u00020^2\b\u0010c\u001a\u0004\u0018\u00010^2\b\u0010d\u001a\u0004\u0018\u00010^2\u0006\u0010e\u001a\u00020^2\b\u0010f\u001a\u0004\u0018\u00010^2\u0006\u00106\u001a\u0002072\u0006\u0010%\u001a\u00020\u00112\u0006\u0010J\u001a\u0002072\u0006\u0010g\u001a\u00020h2\u0006\u0010<\u001a\u00020=H\u0002\u001a\u0014\u0010i\u001a\u00020'*\u00020'2\u0006\u0010j\u001a\u00020'H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006k"}, d2 = {"OutlinedTextFieldInnerPadding", "Landroidx/compose/ui/unit/Dp;", "F", "OutlinedTextFieldTopPadding", "Landroidx/compose/ui/unit/TextUnit;", "getOutlinedTextFieldTopPadding", "()J", "J", "OutlinedTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "OutlinedTextFieldLayout", "textField", "leading", "trailing", "animationProgress", "", "onLabelMeasured", "Landroidx/compose/ui/geometry/Size;", "container", "supporting", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZFLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "calculateHeight", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "textFieldHeight", "labelHeight", "placeholderHeight", "supportingHeight", "constraints", "Landroidx/compose/ui/unit/Constraints;", "density", "calculateHeight-mKXJcVc", "(IIIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateWidth", "leadingPlaceableWidth", "trailingPlaceableWidth", "prefixPlaceableWidth", "suffixPlaceableWidth", "textFieldPlaceableWidth", "labelPlaceableWidth", "placeholderPlaceableWidth", "calculateWidth-DHJA7U0", "(IIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "outlineCutout", "labelSize", "place", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "totalHeight", "width", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", "containerPlaceable", "supportingPlaceable", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "substractConstraintSafely", "from", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class OutlinedTextFieldKt {
    private static final float OutlinedTextFieldInnerPadding = Dp.m6626constructorimpl(4);
    private static final long OutlinedTextFieldTopPadding;

    public static final void OutlinedTextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        String str;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2 label;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int maxLines2;
        Function2 leadingIcon;
        Shape shape2;
        final Function2 placeholder;
        final Function2 leadingIcon2;
        final Shape shape3;
        final Function2 trailingIcon;
        TextStyle textStyle3;
        final Function2 prefix;
        final Function2 label2;
        final Function2 suffix;
        final Function2 supportingText;
        final VisualTransformation visualTransformation2;
        final boolean isError2;
        final int minLines2;
        MutableInteractionSource interactionSource2;
        TextFieldColors colors2;
        final boolean isError3;
        final boolean singleLine2;
        final int maxLines3;
        final Modifier modifier3;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        MutableInteractionSource interactionSource3;
        final MutableInteractionSource interactionSource4;
        final MutableInteractionSource interactionSource5;
        Composer $composer2;
        final Modifier modifier4;
        final boolean isError4;
        final boolean enabled3;
        final boolean readOnly3;
        final KeyboardActions keyboardActions3;
        final boolean singleLine3;
        final int maxLines4;
        final int minLines3;
        final VisualTransformation visualTransformation3;
        final Function2 placeholder2;
        final Function2 leadingIcon3;
        final Function2 trailingIcon2;
        final Function2 prefix2;
        final Function2 suffix2;
        final Function2 label3;
        final TextFieldColors colors3;
        final KeyboardOptions keyboardOptions3;
        final Function2 label4;
        final Shape shape4;
        final TextStyle textStyle4;
        Object value$iv;
        int i10;
        int i11;
        int i12;
        int i13;
        Composer $composer3 = $composer.startRestartGroup(-1922450045);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)175@9821L7,191@10625L5,192@10688L8,204@11208L7,206@11308L2853,206@11221L2940:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
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
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i14 = i & 4;
        if (i14 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i15 = i & 8;
        if (i15 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i16 = i & 16;
        if (i16 != 0) {
            $dirty |= 24576;
            readOnly2 = readOnly;
        } else if (($changed & 24576) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer3.changed(readOnly2) ? 16384 : 8192;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer3.changed(textStyle2)) {
                    i13 = 131072;
                    $dirty |= i13;
                }
            } else {
                textStyle2 = textStyle;
            }
            i13 = 65536;
            $dirty |= i13;
        } else {
            textStyle2 = textStyle;
        }
        int i17 = i & 64;
        if (i17 != 0) {
            $dirty |= 1572864;
            label = function2;
        } else if (($changed & 1572864) == 0) {
            label = function2;
            $dirty |= $composer3.changedInstance(label) ? 1048576 : 524288;
        } else {
            label = function2;
        }
        int i18 = i & 128;
        if (i18 != 0) {
            $dirty |= 12582912;
            i2 = i18;
        } else if (($changed & 12582912) == 0) {
            i2 = i18;
            $dirty |= $composer3.changedInstance(function22) ? 8388608 : 4194304;
        } else {
            i2 = i18;
        }
        int i19 = i & 256;
        if (i19 != 0) {
            $dirty |= 100663296;
            i3 = i19;
        } else if (($changed & 100663296) == 0) {
            i3 = i19;
            $dirty |= $composer3.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i19;
        }
        int i20 = i & 512;
        if (i20 != 0) {
            $dirty |= 805306368;
            i4 = i20;
        } else if (($changed & 805306368) == 0) {
            i4 = i20;
            $dirty |= $composer3.changedInstance(function24) ? 536870912 : 268435456;
        } else {
            i4 = i20;
        }
        int i21 = i & 1024;
        if (i21 != 0) {
            $dirty1 |= 6;
            i5 = i21;
        } else if (($changed1 & 6) == 0) {
            i5 = i21;
            $dirty1 |= $composer3.changedInstance(function25) ? 4 : 2;
        } else {
            i5 = i21;
        }
        int i22 = i & 2048;
        if (i22 != 0) {
            $dirty1 |= 48;
            i6 = i22;
        } else if (($changed1 & 48) == 0) {
            i6 = i22;
            $dirty1 |= $composer3.changedInstance(function26) ? 32 : 16;
        } else {
            i6 = i22;
        }
        int i23 = i & 4096;
        if (i23 != 0) {
            $dirty1 |= 384;
            i7 = i23;
        } else {
            i7 = i23;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changedInstance(function27) ? 256 : 128;
            }
        }
        int i24 = i & 8192;
        if (i24 != 0) {
            $dirty1 |= 3072;
            i8 = i24;
        } else {
            i8 = i24;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changed(isError) ? 2048 : 1024;
            }
        }
        int i25 = i & 16384;
        if (i25 != 0) {
            $dirty1 |= 24576;
            i9 = i25;
        } else {
            i9 = i25;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
            }
        }
        int i26 = i & 32768;
        if (i26 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i27 = i & 65536;
        if (i27 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i28 = i & 131072;
        if (i28 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer3.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            if ((i & 262144) == 0 && $composer3.changed(maxLines)) {
                i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty1 |= i12;
            }
            i12 = 33554432;
            $dirty1 |= i12;
        }
        int i29 = i & 524288;
        if (i29 != 0) {
            $dirty1 |= 805306368;
        } else if (($changed1 & 805306368) == 0) {
            $dirty1 |= $composer3.changed(minLines) ? 536870912 : 268435456;
        }
        int i30 = i & 1048576;
        if (i30 != 0) {
            $dirty2 |= 6;
        } else if (($changed2 & 6) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            if ((i & 2097152) == 0 && $composer3.changed(shape)) {
                i11 = 32;
                $dirty2 |= i11;
            }
            i11 = 16;
            $dirty2 |= i11;
        }
        if (($changed2 & 384) == 0) {
            if ((i & 4194304) == 0 && $composer3.changed(colors)) {
                i10 = 256;
                $dirty2 |= i10;
            }
            i10 = 128;
            $dirty2 |= i10;
        }
        if (($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            placeholder2 = function22;
            prefix2 = function25;
            isError4 = isError;
            keyboardOptions3 = keyboardOptions;
            keyboardActions3 = keyboardActions;
            singleLine3 = singleLine;
            maxLines4 = maxLines;
            minLines3 = minLines;
            interactionSource5 = interactionSource;
            shape4 = shape;
            colors3 = colors;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled3 = enabled2;
            readOnly3 = readOnly2;
            textStyle4 = textStyle2;
            label3 = label;
            leadingIcon3 = function23;
            trailingIcon2 = function24;
            suffix2 = function26;
            label4 = function27;
            visualTransformation3 = visualTransformation;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i15 != 0) {
                    enabled2 = true;
                }
                if (i16 != 0) {
                    readOnly2 = false;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -458753;
                    textStyle2 = (TextStyle) consume;
                }
                if (i17 != 0) {
                    label = null;
                }
                Function2 placeholder3 = i2 != 0 ? null : function22;
                Function2 leadingIcon4 = i3 != 0 ? null : function23;
                Function2 trailingIcon3 = i4 != 0 ? null : function24;
                Function2 prefix3 = i5 != 0 ? null : function25;
                Function2 suffix3 = i6 != 0 ? null : function26;
                Function2 supportingText2 = i7 != 0 ? null : function27;
                boolean isError5 = i8 != 0 ? false : isError;
                VisualTransformation visualTransformation4 = i9 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions4 = i26 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                KeyboardActions keyboardActions4 = i27 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                boolean singleLine4 = i28 != 0 ? false : singleLine;
                if ((i & 262144) != 0) {
                    maxLines2 = singleLine4 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                } else {
                    maxLines2 = maxLines;
                }
                int minLines4 = i29 != 0 ? 1 : minLines;
                MutableInteractionSource interactionSource6 = i30 != 0 ? null : interactionSource;
                Function2 placeholder4 = placeholder3;
                if ((i & 2097152) != 0) {
                    leadingIcon = leadingIcon4;
                    shape2 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    leadingIcon = leadingIcon4;
                    shape2 = shape;
                }
                if ((i & 4194304) != 0) {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    $dirty2 &= -897;
                    trailingIcon = trailingIcon3;
                    prefix = prefix3;
                    suffix = suffix3;
                    supportingText = supportingText2;
                    visualTransformation2 = visualTransformation4;
                    minLines2 = minLines4;
                    interactionSource2 = interactionSource6;
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle3 = textStyle2;
                    label2 = label;
                    isError2 = isError5;
                    singleLine2 = singleLine4;
                    maxLines3 = maxLines2;
                    isError3 = readOnly2;
                    keyboardOptions2 = keyboardOptions4;
                    keyboardActions2 = keyboardActions4;
                    modifier3 = modifier2;
                } else {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    trailingIcon = trailingIcon3;
                    textStyle3 = textStyle2;
                    prefix = prefix3;
                    label2 = label;
                    suffix = suffix3;
                    supportingText = supportingText2;
                    visualTransformation2 = visualTransformation4;
                    isError2 = isError5;
                    minLines2 = minLines4;
                    interactionSource2 = interactionSource6;
                    colors2 = colors;
                    isError3 = readOnly2;
                    singleLine2 = singleLine4;
                    maxLines3 = maxLines2;
                    modifier3 = modifier2;
                    keyboardOptions2 = keyboardOptions4;
                    keyboardActions2 = keyboardActions4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    placeholder = function22;
                    leadingIcon2 = function23;
                    trailingIcon = function24;
                    prefix = function25;
                    suffix = function26;
                    supportingText = function27;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    colors2 = colors;
                    $dirty2 &= -897;
                    isError3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    isError2 = isError;
                    modifier3 = modifier2;
                } else {
                    placeholder = function22;
                    leadingIcon2 = function23;
                    trailingIcon = function24;
                    prefix = function25;
                    suffix = function26;
                    supportingText = function27;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    colors2 = colors;
                    isError3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    isError2 = isError;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1922450045, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:193)");
            }
            $composer3.startReplaceGroup(30062948);
            ComposerKt.sourceInformation($composer3, "195@10782L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, 30063599, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource3 = interactionSource2;
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                interactionSource4 = (MutableInteractionSource) value$iv;
            } else {
                interactionSource3 = interactionSource2;
                interactionSource4 = interactionSource3;
            }
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(30069058);
            ComposerKt.sourceInformation($composer3, "*199@11006L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle3.m6122getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource4, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors2.m2666textColorXeAY9LY$material3_release(enabled2, isError2, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            final TextStyle mergedTextStyle = textStyle3.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            TextStyle textStyle5 = textStyle3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) consume2;
            final TextFieldColors colors4 = colors2;
            final String str2 = str;
            final boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1886965181, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1
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

                public final void invoke(Composer $composer4, int $changed3) {
                    Modifier.Companion companion;
                    ComposerKt.sourceInformation($composer4, "C222@12041L38,240@12860L1285,207@11318L2837:OutlinedTextField.kt#uh7d8r");
                    if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1886965181, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:207)");
                        }
                        Modifier modifier5 = Modifier.this;
                        if (label2 != null) {
                            Modifier semantics = SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                }
                            });
                            Density $this$invoke_u24lambda_u240 = density;
                            companion = PaddingKt.m676paddingqDBjuR0$default(semantics, 0.0f, $this$invoke_u24lambda_u240.mo359toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                        } else {
                            companion = Modifier.INSTANCE;
                        }
                        Modifier then = modifier5.then(companion);
                        boolean z = isError2;
                        Strings.Companion companion2 = Strings.INSTANCE;
                        Modifier m701defaultMinSizeVpY3zN4 = SizeKt.m701defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), OutlinedTextFieldDefaults.INSTANCE.m2351getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2350getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(colors4.m2616cursorColorvNxB06k$material3_release(isError2), null);
                        final String str3 = str2;
                        final boolean z2 = enabled4;
                        final boolean z3 = singleLine2;
                        final VisualTransformation visualTransformation5 = visualTransformation2;
                        final MutableInteractionSource mutableInteractionSource = interactionSource4;
                        final boolean z4 = isError2;
                        final Function2<Composer, Integer, Unit> function28 = label2;
                        final Function2<Composer, Integer, Unit> function29 = placeholder;
                        final Function2<Composer, Integer, Unit> function210 = leadingIcon2;
                        final Function2<Composer, Integer, Unit> function211 = trailingIcon;
                        final Function2<Composer, Integer, Unit> function212 = prefix;
                        final Function2<Composer, Integer, Unit> function213 = suffix;
                        final Function2<Composer, Integer, Unit> function214 = supportingText;
                        final TextFieldColors textFieldColors = colors4;
                        final Shape shape5 = shape3;
                        BasicTextFieldKt.BasicTextField(str2, function1, m701defaultMinSizeVpY3zN4, enabled4, isError3, mergedTextStyle, keyboardOptions2, keyboardActions2, singleLine2, maxLines3, minLines2, visualTransformation2, (Function1<? super TextLayoutResult, Unit>) null, interactionSource4, solidColor, ComposableLambdaKt.rememberComposableLambda(1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function215, Composer composer, Integer num) {
                                invoke((Function2<? super Composer, ? super Integer, Unit>) function215, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function215, Composer $composer5, int $changed4) {
                                ComposerKt.sourceInformation($composer5, "C257@13714L391,241@12926L1201:OutlinedTextField.kt#uh7d8r");
                                int $dirty3 = $changed4;
                                if (($changed4 & 6) == 0) {
                                    $dirty3 |= $composer5.changedInstance(function215) ? 4 : 2;
                                }
                                if (($dirty3 & 19) != 18 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1474611661, $dirty3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:241)");
                                    }
                                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                    int $dirty4 = $dirty3;
                                    String str4 = str3;
                                    boolean z5 = z2;
                                    boolean z6 = z3;
                                    VisualTransformation visualTransformation6 = visualTransformation5;
                                    MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                                    boolean z7 = z4;
                                    Function2<Composer, Integer, Unit> function216 = function28;
                                    Function2<Composer, Integer, Unit> function217 = function29;
                                    Function2<Composer, Integer, Unit> function218 = function210;
                                    Function2<Composer, Integer, Unit> function219 = function211;
                                    Function2<Composer, Integer, Unit> function220 = function212;
                                    Function2<Composer, Integer, Unit> function221 = function213;
                                    Function2<Composer, Integer, Unit> function222 = function214;
                                    TextFieldColors textFieldColors2 = textFieldColors;
                                    final boolean z8 = z2;
                                    final boolean z9 = z4;
                                    final MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource;
                                    final TextFieldColors textFieldColors3 = textFieldColors;
                                    final Shape shape6 = shape5;
                                    outlinedTextFieldDefaults.DecorationBox(str4, function215, z5, z6, visualTransformation6, mutableInteractionSource2, z7, function216, function217, function218, function219, function220, function221, function222, textFieldColors2, null, ComposableLambdaKt.rememberComposableLambda(2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.3.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                            invoke(composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer $composer6, int $changed5) {
                                            ComposerKt.sourceInformation($composer6, "C258@13770L309:OutlinedTextField.kt#uh7d8r");
                                            if (($changed5 & 3) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(2108828640, $changed5, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:258)");
                                                }
                                                OutlinedTextFieldDefaults.INSTANCE.m2345Container4EFweAY(z8, z9, mutableInteractionSource3, null, textFieldColors3, shape6, 0.0f, 0.0f, $composer6, 100663296, 200);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }, $composer5, 54), $composer5, ($dirty4 << 3) & 112, 14155776, 32768);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }, $composer4, 54), $composer4, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource5 = interactionSource3;
            $composer2 = $composer3;
            modifier4 = modifier3;
            isError4 = isError2;
            enabled3 = enabled4;
            readOnly3 = isError3;
            keyboardActions3 = keyboardActions2;
            singleLine3 = singleLine2;
            maxLines4 = maxLines3;
            minLines3 = minLines2;
            visualTransformation3 = visualTransformation2;
            placeholder2 = placeholder;
            leadingIcon3 = leadingIcon2;
            trailingIcon2 = trailingIcon;
            prefix2 = prefix;
            suffix2 = suffix;
            label3 = label2;
            colors3 = colors4;
            keyboardOptions3 = keyboardOptions2;
            label4 = supportingText;
            shape4 = shape3;
            textStyle4 = textStyle5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$2
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

                public final void invoke(Composer composer, int i31) {
                    OutlinedTextFieldKt.OutlinedTextField(value, function1, modifier4, enabled3, readOnly3, textStyle4, label3, placeholder2, leadingIcon3, trailingIcon2, prefix2, suffix2, label4, isError4, visualTransformation3, keyboardOptions3, keyboardActions3, singleLine3, maxLines4, minLines3, interactionSource5, shape4, colors3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
                }
            });
        }
    }

    public static final void OutlinedTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextFieldValue textFieldValue;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2 label;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int maxLines2;
        Function2 leadingIcon;
        Shape shape2;
        final Function2 placeholder;
        final Function2 leadingIcon2;
        final Shape shape3;
        final Function2 trailingIcon;
        TextStyle textStyle3;
        final Function2 prefix;
        final Function2 label2;
        final Function2 suffix;
        final Function2 supportingText;
        final VisualTransformation visualTransformation2;
        final boolean isError2;
        final int minLines2;
        MutableInteractionSource interactionSource2;
        TextFieldColors colors2;
        final boolean isError3;
        final boolean singleLine2;
        final int maxLines3;
        final Modifier modifier3;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        MutableInteractionSource interactionSource3;
        final MutableInteractionSource interactionSource4;
        final MutableInteractionSource interactionSource5;
        Composer $composer2;
        final Modifier modifier4;
        final boolean isError4;
        final boolean enabled3;
        final boolean readOnly3;
        final KeyboardActions keyboardActions3;
        final boolean singleLine3;
        final int maxLines4;
        final int minLines3;
        final VisualTransformation visualTransformation3;
        final Function2 placeholder2;
        final Function2 leadingIcon3;
        final Function2 trailingIcon2;
        final Function2 prefix2;
        final Function2 suffix2;
        final Function2 label3;
        final TextFieldColors colors3;
        final KeyboardOptions keyboardOptions3;
        final Function2 label4;
        final Shape shape4;
        final TextStyle textStyle4;
        Object value$iv;
        int i10;
        int i11;
        int i12;
        int i13;
        Composer $composer3 = $composer.startRestartGroup(-1570442800);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)350@19364L7,366@20168L5,367@20231L8,379@20751L7,381@20851L2858,381@20764L2945:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
        if ((i & 1) != 0) {
            $dirty |= 6;
            textFieldValue = value;
        } else if (($changed & 6) == 0) {
            textFieldValue = value;
            $dirty |= $composer3.changed(textFieldValue) ? 4 : 2;
        } else {
            textFieldValue = value;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i14 = i & 4;
        if (i14 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i15 = i & 8;
        if (i15 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i16 = i & 16;
        if (i16 != 0) {
            $dirty |= 24576;
            readOnly2 = readOnly;
        } else if (($changed & 24576) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer3.changed(readOnly2) ? 16384 : 8192;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer3.changed(textStyle2)) {
                    i13 = 131072;
                    $dirty |= i13;
                }
            } else {
                textStyle2 = textStyle;
            }
            i13 = 65536;
            $dirty |= i13;
        } else {
            textStyle2 = textStyle;
        }
        int i17 = i & 64;
        if (i17 != 0) {
            $dirty |= 1572864;
            label = function2;
        } else if (($changed & 1572864) == 0) {
            label = function2;
            $dirty |= $composer3.changedInstance(label) ? 1048576 : 524288;
        } else {
            label = function2;
        }
        int i18 = i & 128;
        if (i18 != 0) {
            $dirty |= 12582912;
            i2 = i18;
        } else if (($changed & 12582912) == 0) {
            i2 = i18;
            $dirty |= $composer3.changedInstance(function22) ? 8388608 : 4194304;
        } else {
            i2 = i18;
        }
        int i19 = i & 256;
        if (i19 != 0) {
            $dirty |= 100663296;
            i3 = i19;
        } else if (($changed & 100663296) == 0) {
            i3 = i19;
            $dirty |= $composer3.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i19;
        }
        int i20 = i & 512;
        if (i20 != 0) {
            $dirty |= 805306368;
            i4 = i20;
        } else if (($changed & 805306368) == 0) {
            i4 = i20;
            $dirty |= $composer3.changedInstance(function24) ? 536870912 : 268435456;
        } else {
            i4 = i20;
        }
        int i21 = i & 1024;
        if (i21 != 0) {
            $dirty1 |= 6;
            i5 = i21;
        } else if (($changed1 & 6) == 0) {
            i5 = i21;
            $dirty1 |= $composer3.changedInstance(function25) ? 4 : 2;
        } else {
            i5 = i21;
        }
        int i22 = i & 2048;
        if (i22 != 0) {
            $dirty1 |= 48;
            i6 = i22;
        } else if (($changed1 & 48) == 0) {
            i6 = i22;
            $dirty1 |= $composer3.changedInstance(function26) ? 32 : 16;
        } else {
            i6 = i22;
        }
        int i23 = i & 4096;
        if (i23 != 0) {
            $dirty1 |= 384;
            i7 = i23;
        } else {
            i7 = i23;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changedInstance(function27) ? 256 : 128;
            }
        }
        int i24 = i & 8192;
        if (i24 != 0) {
            $dirty1 |= 3072;
            i8 = i24;
        } else {
            i8 = i24;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changed(isError) ? 2048 : 1024;
            }
        }
        int i25 = i & 16384;
        if (i25 != 0) {
            $dirty1 |= 24576;
            i9 = i25;
        } else {
            i9 = i25;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
            }
        }
        int i26 = i & 32768;
        if (i26 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i27 = i & 65536;
        if (i27 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i28 = i & 131072;
        if (i28 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer3.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            if ((i & 262144) == 0 && $composer3.changed(maxLines)) {
                i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty1 |= i12;
            }
            i12 = 33554432;
            $dirty1 |= i12;
        }
        int i29 = i & 524288;
        if (i29 != 0) {
            $dirty1 |= 805306368;
        } else if (($changed1 & 805306368) == 0) {
            $dirty1 |= $composer3.changed(minLines) ? 536870912 : 268435456;
        }
        int i30 = i & 1048576;
        if (i30 != 0) {
            $dirty2 |= 6;
        } else if (($changed2 & 6) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            if ((i & 2097152) == 0 && $composer3.changed(shape)) {
                i11 = 32;
                $dirty2 |= i11;
            }
            i11 = 16;
            $dirty2 |= i11;
        }
        if (($changed2 & 384) == 0) {
            if ((i & 4194304) == 0 && $composer3.changed(colors)) {
                i10 = 256;
                $dirty2 |= i10;
            }
            i10 = 128;
            $dirty2 |= i10;
        }
        if (($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            placeholder2 = function22;
            prefix2 = function25;
            isError4 = isError;
            keyboardOptions3 = keyboardOptions;
            keyboardActions3 = keyboardActions;
            singleLine3 = singleLine;
            maxLines4 = maxLines;
            minLines3 = minLines;
            interactionSource5 = interactionSource;
            shape4 = shape;
            colors3 = colors;
            $composer2 = $composer3;
            modifier4 = modifier2;
            enabled3 = enabled2;
            readOnly3 = readOnly2;
            textStyle4 = textStyle2;
            label3 = label;
            leadingIcon3 = function23;
            trailingIcon2 = function24;
            suffix2 = function26;
            label4 = function27;
            visualTransformation3 = visualTransformation;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i15 != 0) {
                    enabled2 = true;
                }
                if (i16 != 0) {
                    readOnly2 = false;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -458753;
                    textStyle2 = (TextStyle) consume;
                }
                if (i17 != 0) {
                    label = null;
                }
                Function2 placeholder3 = i2 != 0 ? null : function22;
                Function2 leadingIcon4 = i3 != 0 ? null : function23;
                Function2 trailingIcon3 = i4 != 0 ? null : function24;
                Function2 prefix3 = i5 != 0 ? null : function25;
                Function2 suffix3 = i6 != 0 ? null : function26;
                Function2 supportingText2 = i7 != 0 ? null : function27;
                boolean isError5 = i8 != 0 ? false : isError;
                VisualTransformation visualTransformation4 = i9 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions4 = i26 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                KeyboardActions keyboardActions4 = i27 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                boolean singleLine4 = i28 != 0 ? false : singleLine;
                if ((i & 262144) != 0) {
                    maxLines2 = singleLine4 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                } else {
                    maxLines2 = maxLines;
                }
                int minLines4 = i29 != 0 ? 1 : minLines;
                MutableInteractionSource interactionSource6 = i30 != 0 ? null : interactionSource;
                Function2 placeholder4 = placeholder3;
                if ((i & 2097152) != 0) {
                    leadingIcon = leadingIcon4;
                    shape2 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    leadingIcon = leadingIcon4;
                    shape2 = shape;
                }
                if ((i & 4194304) != 0) {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    $dirty2 &= -897;
                    trailingIcon = trailingIcon3;
                    prefix = prefix3;
                    suffix = suffix3;
                    supportingText = supportingText2;
                    visualTransformation2 = visualTransformation4;
                    minLines2 = minLines4;
                    interactionSource2 = interactionSource6;
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle3 = textStyle2;
                    label2 = label;
                    isError2 = isError5;
                    singleLine2 = singleLine4;
                    maxLines3 = maxLines2;
                    isError3 = readOnly2;
                    keyboardOptions2 = keyboardOptions4;
                    keyboardActions2 = keyboardActions4;
                    modifier3 = modifier2;
                } else {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    trailingIcon = trailingIcon3;
                    textStyle3 = textStyle2;
                    prefix = prefix3;
                    label2 = label;
                    suffix = suffix3;
                    supportingText = supportingText2;
                    visualTransformation2 = visualTransformation4;
                    isError2 = isError5;
                    minLines2 = minLines4;
                    interactionSource2 = interactionSource6;
                    colors2 = colors;
                    isError3 = readOnly2;
                    singleLine2 = singleLine4;
                    maxLines3 = maxLines2;
                    modifier3 = modifier2;
                    keyboardOptions2 = keyboardOptions4;
                    keyboardActions2 = keyboardActions4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    placeholder = function22;
                    leadingIcon2 = function23;
                    trailingIcon = function24;
                    prefix = function25;
                    suffix = function26;
                    supportingText = function27;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    colors2 = colors;
                    $dirty2 &= -897;
                    isError3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    isError2 = isError;
                    modifier3 = modifier2;
                } else {
                    placeholder = function22;
                    leadingIcon2 = function23;
                    trailingIcon = function24;
                    prefix = function25;
                    suffix = function26;
                    supportingText = function27;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    colors2 = colors;
                    isError3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    isError2 = isError;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1570442800, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:368)");
            }
            $composer3.startReplaceGroup(30368324);
            ComposerKt.sourceInformation($composer3, "370@20325L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, 30368975, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource3 = interactionSource2;
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                interactionSource4 = (MutableInteractionSource) value$iv;
            } else {
                interactionSource3 = interactionSource2;
                interactionSource4 = interactionSource3;
            }
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(30374434);
            ComposerKt.sourceInformation($composer3, "*374@20549L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle3.m6122getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource4, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors2.m2666textColorXeAY9LY$material3_release(enabled2, isError2, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            final TextStyle mergedTextStyle = textStyle3.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            TextStyle textStyle5 = textStyle3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) consume2;
            final TextFieldColors colors4 = colors2;
            final TextFieldValue textFieldValue2 = textFieldValue;
            final boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1830921872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3
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

                public final void invoke(Composer $composer4, int $changed3) {
                    Modifier.Companion companion;
                    ComposerKt.sourceInformation($composer4, "C397@21584L38,415@22403L1290,382@20861L2842:OutlinedTextField.kt#uh7d8r");
                    if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1830921872, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
                        }
                        Modifier modifier5 = Modifier.this;
                        if (label2 != null) {
                            Modifier semantics = SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                }
                            });
                            Density $this$invoke_u24lambda_u240 = density;
                            companion = PaddingKt.m676paddingqDBjuR0$default(semantics, 0.0f, $this$invoke_u24lambda_u240.mo359toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                        } else {
                            companion = Modifier.INSTANCE;
                        }
                        Modifier then = modifier5.then(companion);
                        boolean z = isError2;
                        Strings.Companion companion2 = Strings.INSTANCE;
                        Modifier m701defaultMinSizeVpY3zN4 = SizeKt.m701defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(then, z, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), OutlinedTextFieldDefaults.INSTANCE.m2351getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2350getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(colors4.m2616cursorColorvNxB06k$material3_release(isError2), null);
                        final TextFieldValue textFieldValue3 = textFieldValue2;
                        final boolean z2 = enabled4;
                        final boolean z3 = singleLine2;
                        final VisualTransformation visualTransformation5 = visualTransformation2;
                        final MutableInteractionSource mutableInteractionSource = interactionSource4;
                        final boolean z4 = isError2;
                        final Function2<Composer, Integer, Unit> function28 = label2;
                        final Function2<Composer, Integer, Unit> function29 = placeholder;
                        final Function2<Composer, Integer, Unit> function210 = leadingIcon2;
                        final Function2<Composer, Integer, Unit> function211 = trailingIcon;
                        final Function2<Composer, Integer, Unit> function212 = prefix;
                        final Function2<Composer, Integer, Unit> function213 = suffix;
                        final Function2<Composer, Integer, Unit> function214 = supportingText;
                        final TextFieldColors textFieldColors = colors4;
                        final Shape shape5 = shape3;
                        BasicTextFieldKt.BasicTextField(textFieldValue2, function1, m701defaultMinSizeVpY3zN4, enabled4, isError3, mergedTextStyle, keyboardOptions2, keyboardActions2, singleLine2, maxLines3, minLines2, visualTransformation2, (Function1<? super TextLayoutResult, Unit>) null, interactionSource4, solidColor, ComposableLambdaKt.rememberComposableLambda(-757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function215, Composer composer, Integer num) {
                                invoke((Function2<? super Composer, ? super Integer, Unit>) function215, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function215, Composer $composer5, int $changed4) {
                                ComposerKt.sourceInformation($composer5, "C432@23262L391,416@22469L1206:OutlinedTextField.kt#uh7d8r");
                                int $dirty3 = $changed4;
                                if (($changed4 & 6) == 0) {
                                    $dirty3 |= $composer5.changedInstance(function215) ? 4 : 2;
                                }
                                if (($dirty3 & 19) == 18 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-757328870, $dirty3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:416)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                String text = TextFieldValue.this.getText();
                                boolean z5 = z2;
                                int $dirty4 = $dirty3;
                                boolean z6 = z3;
                                VisualTransformation visualTransformation6 = visualTransformation5;
                                MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                                boolean z7 = z4;
                                Function2<Composer, Integer, Unit> function216 = function28;
                                Function2<Composer, Integer, Unit> function217 = function29;
                                Function2<Composer, Integer, Unit> function218 = function210;
                                Function2<Composer, Integer, Unit> function219 = function211;
                                Function2<Composer, Integer, Unit> function220 = function212;
                                Function2<Composer, Integer, Unit> function221 = function213;
                                Function2<Composer, Integer, Unit> function222 = function214;
                                TextFieldColors textFieldColors2 = textFieldColors;
                                final boolean z8 = z2;
                                final boolean z9 = z4;
                                final MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource;
                                final TextFieldColors textFieldColors3 = textFieldColors;
                                final Shape shape6 = shape5;
                                outlinedTextFieldDefaults.DecorationBox(text, function215, z5, z6, visualTransformation6, mutableInteractionSource2, z7, function216, function217, function218, function219, function220, function221, function222, textFieldColors2, null, ComposableLambdaKt.rememberComposableLambda(255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.3.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer6, int $changed5) {
                                        ComposerKt.sourceInformation($composer6, "C433@23318L309:OutlinedTextField.kt#uh7d8r");
                                        if (($changed5 & 3) != 2 || !$composer6.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(255570733, $changed5, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:433)");
                                            }
                                            OutlinedTextFieldDefaults.INSTANCE.m2345Container4EFweAY(z8, z9, mutableInteractionSource3, null, textFieldColors3, shape6, 0.0f, 0.0f, $composer6, 100663296, 200);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer6.skipToGroupEnd();
                                    }
                                }, $composer5, 54), $composer5, ($dirty4 << 3) & 112, 14155776, 32768);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer4, 54), $composer4, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource5 = interactionSource3;
            $composer2 = $composer3;
            modifier4 = modifier3;
            isError4 = isError2;
            enabled3 = enabled4;
            readOnly3 = isError3;
            keyboardActions3 = keyboardActions2;
            singleLine3 = singleLine2;
            maxLines4 = maxLines3;
            minLines3 = minLines2;
            visualTransformation3 = visualTransformation2;
            placeholder2 = placeholder;
            leadingIcon3 = leadingIcon2;
            trailingIcon2 = trailingIcon;
            prefix2 = prefix;
            suffix2 = suffix;
            label3 = label2;
            colors3 = colors4;
            keyboardOptions3 = keyboardOptions2;
            label4 = supportingText;
            shape4 = shape3;
            textStyle4 = textStyle5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$4
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

                public final void invoke(Composer composer, int i31) {
                    OutlinedTextFieldKt.OutlinedTextField(TextFieldValue.this, function1, modifier4, enabled3, readOnly3, textStyle4, label3, placeholder2, leadingIcon3, trailingIcon2, prefix2, suffix2, label4, isError4, visualTransformation3, keyboardOptions3, keyboardActions3, singleLine3, maxLines4, minLines3, interactionSource5, shape4, colors3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x056b  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0722  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0882  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0891  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x093d  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0949  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0980  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0a13  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0b6a  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0cd2  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0cb5  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0b56  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0996 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:269:0x094f  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x08d3  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0894  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0885  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x070a  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x05ab  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x024c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void OutlinedTextFieldLayout(final androidx.compose.ui.Modifier r62, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r63, final kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r64, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r65, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r66, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r67, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r68, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r69, final boolean r70, float r71, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.geometry.Size, kotlin.Unit> r72, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r73, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r74, androidx.compose.foundation.layout.PaddingValues r75, androidx.compose.runtime.Composer r76, final int r77, final int r78) {
        /*
            Method dump skipped, instructions count: 3338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, float, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int substractConstraintSafely(int $this$substractConstraintSafely, int from) {
        if ($this$substractConstraintSafely == Integer.MAX_VALUE) {
            return $this$substractConstraintSafely;
        }
        return $this$substractConstraintSafely - from;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateWidth-DHJA7U0, reason: not valid java name */
    public static final int m2356calculateWidthDHJA7U0(int leadingPlaceableWidth, int trailingPlaceableWidth, int prefixPlaceableWidth, int suffixPlaceableWidth, int textFieldPlaceableWidth, int labelPlaceableWidth, int placeholderPlaceableWidth, float animationProgress, long constraints, float density, PaddingValues paddingValues) {
        int affixTotalWidth = prefixPlaceableWidth + suffixPlaceableWidth;
        int middleSection = Math.max(textFieldPlaceableWidth + affixTotalWidth, Math.max(placeholderPlaceableWidth + affixTotalWidth, MathHelpersKt.lerp(labelPlaceableWidth, 0, animationProgress)));
        int wrappedWidth = leadingPlaceableWidth + middleSection + trailingPlaceableWidth;
        float arg0$iv = paddingValues.mo623calculateLeftPaddingu2uoSUM(LayoutDirection.Ltr);
        float other$iv = paddingValues.mo624calculateRightPaddingu2uoSUM(LayoutDirection.Ltr);
        float labelHorizontalPadding = Dp.m6626constructorimpl(arg0$iv + other$iv) * density;
        int focusedLabelWidth = MathKt.roundToInt((labelPlaceableWidth + labelHorizontalPadding) * animationProgress);
        return Math.max(wrappedWidth, Math.max(focusedLabelWidth, Constraints.m6581getMinWidthimpl(constraints)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    public static final int m2355calculateHeightmKXJcVc(int leadingHeight, int trailingHeight, int prefixHeight, int suffixHeight, int textFieldHeight, int labelHeight, int placeholderHeight, int supportingHeight, float animationProgress, long constraints, float density, PaddingValues paddingValues) {
        int inputFieldHeight = ComparisonsKt.maxOf(textFieldHeight, placeholderHeight, prefixHeight, suffixHeight, MathHelpersKt.lerp(labelHeight, 0, animationProgress));
        float topPadding = paddingValues.getTop() * density;
        float actualTopPadding = MathHelpersKt.lerp(topPadding, Math.max(topPadding, labelHeight / 2.0f), animationProgress);
        float bottomPadding = paddingValues.getBottom() * density;
        float middleSectionHeight = inputFieldHeight + actualTopPadding + bottomPadding;
        return Math.max(Constraints.m6580getMinHeightimpl(constraints), Math.max(leadingHeight, Math.max(trailingHeight, MathKt.roundToInt(middleSectionHeight))) + supportingHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void place(Placeable.PlacementScope $this$place, int totalHeight, int width, Placeable leadingPlaceable, Placeable trailingPlaceable, Placeable prefixPlaceable, Placeable suffixPlaceable, Placeable textFieldPlaceable, Placeable labelPlaceable, Placeable placeholderPlaceable, Placeable containerPlaceable, Placeable supportingPlaceable, float animationProgress, boolean singleLine, float density, LayoutDirection layoutDirection, PaddingValues paddingValues) {
        int startPositionY;
        float widthOrZero;
        Placeable.PlacementScope.m5555place70tqf50$default($this$place, containerPlaceable, IntOffset.INSTANCE.m6764getZeronOccac(), 0.0f, 2, null);
        int height = totalHeight - TextFieldImplKt.heightOrZero(supportingPlaceable);
        int topPadding = MathKt.roundToInt(paddingValues.getTop() * density);
        int startPadding = MathKt.roundToInt(PaddingKt.calculateStartPadding(paddingValues, layoutDirection) * density);
        float iconPadding = TextFieldImplKt.getHorizontalIconPadding() * density;
        if (leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, leadingPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (labelPlaceable != null) {
            if (singleLine) {
                startPositionY = Alignment.INSTANCE.getCenterVertically().align(labelPlaceable.getHeight(), height);
            } else {
                startPositionY = topPadding;
            }
            int positionY = MathHelpersKt.lerp(startPositionY, -(labelPlaceable.getHeight() / 2), animationProgress);
            if (leadingPlaceable == null) {
                widthOrZero = 0.0f;
            } else {
                widthOrZero = (TextFieldImplKt.widthOrZero(leadingPlaceable) - iconPadding) * (1.0f - animationProgress);
            }
            int positionX = MathKt.roundToInt(widthOrZero) + startPadding;
            Placeable.PlacementScope.placeRelative$default($this$place, labelPlaceable, positionX, positionY, 0.0f, 4, null);
        }
        if (prefixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, prefixPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, prefixPlaceable), 0.0f, 4, null);
        }
        int textHorizontalPosition = TextFieldImplKt.widthOrZero(leadingPlaceable) + TextFieldImplKt.widthOrZero(prefixPlaceable);
        Placeable.PlacementScope.placeRelative$default($this$place, textFieldPlaceable, textHorizontalPosition, place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, textFieldPlaceable), 0.0f, 4, null);
        if (placeholderPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, placeholderPlaceable, textHorizontalPosition, place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, placeholderPlaceable), 0.0f, 4, null);
        }
        if (suffixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, suffixPlaceable, (width - TextFieldImplKt.widthOrZero(trailingPlaceable)) - suffixPlaceable.getWidth(), place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, suffixPlaceable), 0.0f, 4, null);
        }
        if (trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, trailingPlaceable, width - trailingPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (supportingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, supportingPlaceable, 0, height, 0.0f, 4, null);
        }
    }

    private static final int place$calculateVerticalPosition(boolean $singleLine, int height, int topPadding, Placeable $labelPlaceable, Placeable placeable) {
        int i;
        if ($singleLine) {
            i = Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), height);
        } else {
            i = topPadding;
        }
        return Math.max(i, TextFieldImplKt.heightOrZero($labelPlaceable) / 2);
    }

    public static final Modifier outlineCutout(Modifier $this$outlineCutout, final Function0<Size> function0, final PaddingValues paddingValues) {
        return DrawModifierKt.drawWithContent($this$outlineCutout, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$outlineCutout$1

            /* compiled from: OutlinedTextField.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[LayoutDirection.values().length];
                    try {
                        iArr[LayoutDirection.Rtl.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope $this$drawWithContent) {
                float f;
                float right;
                long labelSizeValue = function0.invoke().getPackedValue();
                float labelWidth = Size.m3991getWidthimpl(labelSizeValue);
                if (labelWidth > 0.0f) {
                    f = OutlinedTextFieldKt.OutlinedTextFieldInnerPadding;
                    float innerPadding = $this$drawWithContent.mo364toPx0680j_4(f);
                    float leftLtr = $this$drawWithContent.mo364toPx0680j_4(paddingValues.mo623calculateLeftPaddingu2uoSUM($this$drawWithContent.getLayoutDirection())) - innerPadding;
                    float rightLtr = leftLtr + labelWidth + (2.0f * innerPadding);
                    float left = WhenMappings.$EnumSwitchMapping$0[$this$drawWithContent.getLayoutDirection().ordinal()] == 1 ? Size.m3991getWidthimpl($this$drawWithContent.mo4713getSizeNHjbRc()) - rightLtr : RangesKt.coerceAtLeast(leftLtr, 0.0f);
                    if (WhenMappings.$EnumSwitchMapping$0[$this$drawWithContent.getLayoutDirection().ordinal()] == 1) {
                        right = Size.m3991getWidthimpl($this$drawWithContent.mo4713getSizeNHjbRc()) - RangesKt.coerceAtLeast(leftLtr, 0.0f);
                    } else {
                        right = rightLtr;
                    }
                    float labelHeight = Size.m3988getHeightimpl(labelSizeValue);
                    ContentDrawScope $this$clipRect_u2drOu3jXo$iv = $this$drawWithContent;
                    float top$iv = (-labelHeight) / 2.0f;
                    float bottom$iv = labelHeight / 2.0f;
                    int clipOp$iv = ClipOp.INSTANCE.m4151getDifferencertfAjoo();
                    DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo$iv.getDrawContext();
                    long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo4634getSizeNHjbRc();
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
                    try {
                        DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                        try {
                            $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo4637clipRectN_I0leg(left, top$iv, right, bottom$iv, clipOp$iv);
                            $this$drawWithContent.drawContent();
                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                        } catch (Throwable th) {
                            th = th;
                            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv;
                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    $this$drawWithContent.drawContent();
                }
            }
        });
    }

    static {
        long arg0$iv = TypeScaleTokens.INSTANCE.m3580getBodySmallLineHeightXSAIIZE();
        TextUnitKt.m6832checkArithmeticR2X_6o(arg0$iv);
        OutlinedTextFieldTopPadding = TextUnitKt.pack(TextUnit.m6817getRawTypeimpl(arg0$iv), TextUnit.m6819getValueimpl(arg0$iv) / 2);
    }

    public static final long getOutlinedTextFieldTopPadding() {
        return OutlinedTextFieldTopPadding;
    }
}
