package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.Placeable;
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
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;

/* compiled from: TextField.kt */
@Metadata(d1 = {"\u0000¤\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u001aØ\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020+H\u0007¢\u0006\u0002\u0010,\u001aØ\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020-2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00060\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020+H\u0007¢\u0006\u0002\u0010.\u001aì\u0001\u0010/\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\u00142\u0013\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0019\u0010\u0015\u001a\u0015\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n¢\u0006\u0002\b\u00142\u0013\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0013\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0006\u0010\"\u001a\u00020\u000e2\u0006\u00103\u001a\u0002042\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\u00142\u0013\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013¢\u0006\u0002\b\u00142\u0006\u00107\u001a\u000208H\u0001¢\u0006\u0002\u00109\u001ar\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020$2\u0006\u0010=\u001a\u00020$2\u0006\u0010>\u001a\u00020$2\u0006\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020$2\u0006\u0010A\u001a\u00020$2\u0006\u0010B\u001a\u00020$2\u0006\u00103\u001a\u0002042\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u0002042\u0006\u00107\u001a\u000208H\u0002ø\u0001\u0000¢\u0006\u0004\bF\u0010G\u001aR\u0010H\u001a\u00020$2\u0006\u0010I\u001a\u00020$2\u0006\u0010J\u001a\u00020$2\u0006\u0010K\u001a\u00020$2\u0006\u0010L\u001a\u00020$2\u0006\u0010M\u001a\u00020$2\u0006\u0010N\u001a\u00020$2\u0006\u0010O\u001a\u00020$2\u0006\u0010C\u001a\u00020DH\u0002ø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a\u001a\u0010R\u001a\u00020\f*\u00020\f2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020U0TH\u0000\u001a\u009a\u0001\u0010V\u001a\u00020\u0006*\u00020W2\u0006\u0010X\u001a\u00020$2\u0006\u0010Y\u001a\u00020$2\u0006\u0010Z\u001a\u00020[2\b\u0010\\\u001a\u0004\u0018\u00010[2\b\u0010]\u001a\u0004\u0018\u00010[2\b\u0010^\u001a\u0004\u0018\u00010[2\b\u0010_\u001a\u0004\u0018\u00010[2\b\u0010`\u001a\u0004\u0018\u00010[2\b\u0010a\u001a\u0004\u0018\u00010[2\u0006\u0010b\u001a\u00020[2\b\u0010c\u001a\u0004\u0018\u00010[2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010d\u001a\u00020$2\u0006\u0010e\u001a\u00020$2\u0006\u00103\u001a\u0002042\u0006\u0010E\u001a\u000204H\u0002\u001a\u0080\u0001\u0010f\u001a\u00020\u0006*\u00020W2\u0006\u0010X\u001a\u00020$2\u0006\u0010Y\u001a\u00020$2\u0006\u0010g\u001a\u00020[2\b\u0010]\u001a\u0004\u0018\u00010[2\b\u0010^\u001a\u0004\u0018\u00010[2\b\u0010_\u001a\u0004\u0018\u00010[2\b\u0010`\u001a\u0004\u0018\u00010[2\b\u0010a\u001a\u0004\u0018\u00010[2\u0006\u0010b\u001a\u00020[2\b\u0010c\u001a\u0004\u0018\u00010[2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010E\u001a\u0002042\u0006\u00107\u001a\u000208H\u0002\u001a\u0014\u0010h\u001a\u00020$*\u00020$2\u0006\u0010i\u001a\u00020$H\u0002\"\u0016\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006j"}, d2 = {"TextFieldWithLabelVerticalPadding", "Landroidx/compose/ui/unit/Dp;", "getTextFieldWithLabelVerticalPadding", "()F", "F", "TextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "TextFieldLayout", "textField", "leading", "trailing", "animationProgress", "", "container", "supporting", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "calculateHeight", "textFieldHeight", "labelHeight", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "placeholderHeight", "supportingHeight", "constraints", "Landroidx/compose/ui/unit/Constraints;", "density", "calculateHeight-mKXJcVc", "(IIIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateWidth", "leadingWidth", "trailingWidth", "prefixWidth", "suffixWidth", "textFieldWidth", "labelWidth", "placeholderWidth", "calculateWidth-yeHjK3Y", "(IIIIIIIJ)I", "drawIndicatorLine", "indicatorBorder", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/BorderStroke;", "placeWithLabel", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "width", "totalHeight", "textfieldPlaceable", "Landroidx/compose/ui/layout/Placeable;", "labelPlaceable", "placeholderPlaceable", "leadingPlaceable", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "containerPlaceable", "supportingPlaceable", "labelEndPosition", "textPosition", "placeWithoutLabel", "textPlaceable", "substractConstraintSafely", "from", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TextFieldKt {
    private static final float TextFieldWithLabelVerticalPadding = Dp.m6626constructorimpl(8);

    public static final void TextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        final String str;
        Function1<? super String, Unit> function12;
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
        final KeyboardActions keyboardActions2;
        final boolean singleLine2;
        final int maxLines2;
        final int minLines2;
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
        final boolean isError2;
        MutableInteractionSource interactionSource2;
        final TextFieldColors colors2;
        final Modifier modifier3;
        final VisualTransformation visualTransformation2;
        final KeyboardOptions keyboardOptions2;
        final boolean readOnly3;
        MutableInteractionSource interactionSource3;
        final MutableInteractionSource interactionSource4;
        final KeyboardOptions keyboardOptions3;
        final int maxLines3;
        final int minLines3;
        final VisualTransformation visualTransformation3;
        final Function2 placeholder2;
        final Function2 trailingIcon2;
        final Function2 prefix2;
        final Function2 suffix2;
        final TextFieldColors colors3;
        final boolean readOnly4;
        final boolean readOnly5;
        final Function2 label3;
        final Shape shape4;
        final TextStyle textStyle4;
        Composer $composer2;
        final boolean isError3;
        final boolean enabled3;
        final KeyboardActions keyboardActions3;
        final Function2 supportingText2;
        final MutableInteractionSource interactionSource5;
        Object value$iv;
        int i10;
        int i11;
        int i12;
        int i13;
        Composer $composer3 = $composer.startRestartGroup(-676242365);
        ComposerKt.sourceInformation($composer3, "C(TextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)201@10573L7,217@11369L5,218@11424L8,230@12004L1956,230@11917L2043:TextField.kt#uh7d8r");
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
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty |= $composer3.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
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
            leadingIcon2 = function23;
            supportingText2 = function27;
            visualTransformation3 = visualTransformation;
            keyboardOptions3 = keyboardOptions;
            keyboardActions3 = keyboardActions;
            readOnly5 = singleLine;
            maxLines3 = maxLines;
            minLines3 = minLines;
            interactionSource5 = interactionSource;
            shape4 = shape;
            colors3 = colors;
            $composer2 = $composer3;
            enabled3 = enabled2;
            readOnly4 = readOnly2;
            textStyle4 = textStyle2;
            label3 = label;
            trailingIcon2 = function24;
            prefix2 = function25;
            suffix2 = function26;
            isError3 = isError;
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
                Function2 leadingIcon3 = i3 != 0 ? null : function23;
                Function2 trailingIcon3 = i4 != 0 ? null : function24;
                Function2 prefix3 = i5 != 0 ? null : function25;
                Function2 suffix3 = i6 != 0 ? null : function26;
                Function2 supportingText3 = i7 != 0 ? null : function27;
                boolean isError4 = i8 != 0 ? false : isError;
                VisualTransformation visualTransformation4 = i9 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions4 = i26 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                keyboardActions2 = i27 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                singleLine2 = i28 != 0 ? false : singleLine;
                if ((i & 262144) != 0) {
                    maxLines2 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                } else {
                    maxLines2 = maxLines;
                }
                minLines2 = i29 != 0 ? 1 : minLines;
                MutableInteractionSource interactionSource6 = i30 != 0 ? null : interactionSource;
                Function2 placeholder4 = placeholder3;
                if ((i & 2097152) != 0) {
                    leadingIcon = leadingIcon3;
                    shape2 = TextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    leadingIcon = leadingIcon3;
                    shape2 = shape;
                }
                if ((i & 4194304) != 0) {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    $dirty2 &= -897;
                    trailingIcon = trailingIcon3;
                    prefix = prefix3;
                    label2 = label;
                    suffix = suffix3;
                    supportingText = supportingText3;
                    isError2 = isError4;
                    interactionSource2 = interactionSource6;
                    colors2 = TextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle3 = textStyle2;
                    visualTransformation2 = visualTransformation4;
                    keyboardOptions2 = keyboardOptions4;
                    modifier3 = modifier2;
                    readOnly3 = readOnly2;
                } else {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    trailingIcon = trailingIcon3;
                    textStyle3 = textStyle2;
                    prefix = prefix3;
                    label2 = label;
                    suffix = suffix3;
                    supportingText = supportingText3;
                    isError2 = isError4;
                    interactionSource2 = interactionSource6;
                    colors2 = colors;
                    modifier3 = modifier2;
                    visualTransformation2 = visualTransformation4;
                    keyboardOptions2 = keyboardOptions4;
                    readOnly3 = readOnly2;
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
                    isError2 = isError;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines2 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    $dirty2 &= -897;
                    readOnly3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    colors2 = colors;
                    modifier3 = modifier2;
                } else {
                    placeholder = function22;
                    leadingIcon2 = function23;
                    trailingIcon = function24;
                    prefix = function25;
                    suffix = function26;
                    supportingText = function27;
                    isError2 = isError;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines2 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    readOnly3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    colors2 = colors;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-676242365, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:219)");
            }
            $composer3.startReplaceGroup(-508515290);
            ComposerKt.sourceInformation($composer3, "221@11518L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, -508514639, "CC(remember):TextField.kt#9igjgp");
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
            $composer3.startReplaceGroup(-508509180);
            ComposerKt.sourceInformation($composer3, "*225@11742L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle3.m6122getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource4, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors2.m2666textColorXeAY9LY$material3_release(enabled2, isError2, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            final TextStyle mergedTextStyle = textStyle3.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            final Function1<? super String, Unit> function13 = function12;
            final boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1859145987, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt$TextField$1
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
                    ComposerKt.sourceInformation($composer4, "C235@12157L38,253@12960L984,231@12014L1940:TextField.kt#uh7d8r");
                    if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1859145987, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:231)");
                        }
                        Modifier modifier4 = Modifier.this;
                        boolean z = isError2;
                        Strings.Companion companion = Strings.INSTANCE;
                        Modifier m701defaultMinSizeVpY3zN4 = SizeKt.m701defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m2684getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m2683getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(colors2.m2616cursorColorvNxB06k$material3_release(isError2), null);
                        final String str2 = str;
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
                        final Shape shape5 = shape3;
                        final TextFieldColors textFieldColors = colors2;
                        BasicTextFieldKt.BasicTextField(str, function13, m701defaultMinSizeVpY3zN4, enabled4, readOnly3, mergedTextStyle, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, minLines2, visualTransformation2, (Function1<? super TextLayoutResult, Unit>) null, interactionSource4, solidColor, ComposableLambdaKt.rememberComposableLambda(-288211827, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt$TextField$1.1
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
                                Function2<? super Composer, ? super Integer, Unit> function216;
                                ComposerKt.sourceInformation($composer5, "C255@13115L811:TextField.kt#uh7d8r");
                                int $dirty3 = $changed4;
                                if (($changed4 & 6) == 0) {
                                    function216 = function215;
                                    $dirty3 |= $composer5.changedInstance(function216) ? 4 : 2;
                                } else {
                                    function216 = function215;
                                }
                                if (($dirty3 & 19) != 18 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-288211827, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:255)");
                                    }
                                    TextFieldDefaults.INSTANCE.DecorationBox(str2, function216, z2, z3, visualTransformation5, mutableInteractionSource, z4, function28, function29, function210, function211, function212, function213, function214, shape5, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
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
            modifier2 = modifier3;
            keyboardOptions3 = keyboardOptions2;
            maxLines3 = maxLines2;
            minLines3 = minLines2;
            visualTransformation3 = visualTransformation2;
            placeholder2 = placeholder;
            trailingIcon2 = trailingIcon;
            prefix2 = prefix;
            suffix2 = suffix;
            colors3 = colors2;
            readOnly4 = readOnly3;
            readOnly5 = singleLine2;
            label3 = label2;
            shape4 = shape3;
            textStyle4 = textStyle3;
            $composer2 = $composer3;
            isError3 = isError2;
            enabled3 = enabled4;
            keyboardActions3 = keyboardActions2;
            supportingText2 = supportingText;
            interactionSource5 = interactionSource3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2 leadingIcon4 = leadingIcon2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt$TextField$2
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
                    TextFieldKt.TextField(value, function1, modifier4, enabled3, readOnly4, textStyle4, label3, placeholder2, leadingIcon4, trailingIcon2, prefix2, suffix2, supportingText2, isError3, visualTransformation3, keyboardOptions3, keyboardActions3, readOnly5, maxLines3, minLines3, interactionSource5, shape4, colors3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
                }
            });
        }
    }

    public static final void TextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        final TextFieldValue textFieldValue;
        Function1<? super TextFieldValue, Unit> function12;
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
        final KeyboardActions keyboardActions2;
        final boolean singleLine2;
        final int maxLines2;
        final int minLines2;
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
        final boolean isError2;
        MutableInteractionSource interactionSource2;
        final TextFieldColors colors2;
        final Modifier modifier3;
        final VisualTransformation visualTransformation2;
        final KeyboardOptions keyboardOptions2;
        final boolean readOnly3;
        MutableInteractionSource interactionSource3;
        final MutableInteractionSource interactionSource4;
        final KeyboardOptions keyboardOptions3;
        final int maxLines3;
        final int minLines3;
        final VisualTransformation visualTransformation3;
        final Function2 placeholder2;
        final Function2 trailingIcon2;
        final Function2 prefix2;
        final Function2 suffix2;
        final TextFieldColors colors3;
        final boolean readOnly4;
        final boolean readOnly5;
        final Function2 label3;
        final Shape shape4;
        final TextStyle textStyle4;
        Composer $composer2;
        final boolean isError3;
        final boolean enabled3;
        final KeyboardActions keyboardActions3;
        final Function2 supportingText2;
        final MutableInteractionSource interactionSource5;
        Object value$iv;
        int i10;
        int i11;
        int i12;
        int i13;
        Composer $composer3 = $composer.startRestartGroup(-1268528240);
        ComposerKt.sourceInformation($composer3, "C(TextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)357@19138L7,373@19934L5,374@19989L8,386@20569L1961,386@20482L2048:TextField.kt#uh7d8r");
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
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty |= $composer3.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
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
            leadingIcon2 = function23;
            supportingText2 = function27;
            visualTransformation3 = visualTransformation;
            keyboardOptions3 = keyboardOptions;
            keyboardActions3 = keyboardActions;
            readOnly5 = singleLine;
            maxLines3 = maxLines;
            minLines3 = minLines;
            interactionSource5 = interactionSource;
            shape4 = shape;
            colors3 = colors;
            $composer2 = $composer3;
            enabled3 = enabled2;
            readOnly4 = readOnly2;
            textStyle4 = textStyle2;
            label3 = label;
            trailingIcon2 = function24;
            prefix2 = function25;
            suffix2 = function26;
            isError3 = isError;
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
                Function2 leadingIcon3 = i3 != 0 ? null : function23;
                Function2 trailingIcon3 = i4 != 0 ? null : function24;
                Function2 prefix3 = i5 != 0 ? null : function25;
                Function2 suffix3 = i6 != 0 ? null : function26;
                Function2 supportingText3 = i7 != 0 ? null : function27;
                boolean isError4 = i8 != 0 ? false : isError;
                VisualTransformation visualTransformation4 = i9 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions4 = i26 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                keyboardActions2 = i27 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                singleLine2 = i28 != 0 ? false : singleLine;
                if ((i & 262144) != 0) {
                    maxLines2 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                } else {
                    maxLines2 = maxLines;
                }
                minLines2 = i29 != 0 ? 1 : minLines;
                MutableInteractionSource interactionSource6 = i30 != 0 ? null : interactionSource;
                Function2 placeholder4 = placeholder3;
                if ((i & 2097152) != 0) {
                    leadingIcon = leadingIcon3;
                    shape2 = TextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    leadingIcon = leadingIcon3;
                    shape2 = shape;
                }
                if ((i & 4194304) != 0) {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    $dirty2 &= -897;
                    trailingIcon = trailingIcon3;
                    prefix = prefix3;
                    label2 = label;
                    suffix = suffix3;
                    supportingText = supportingText3;
                    isError2 = isError4;
                    interactionSource2 = interactionSource6;
                    colors2 = TextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle3 = textStyle2;
                    visualTransformation2 = visualTransformation4;
                    keyboardOptions2 = keyboardOptions4;
                    modifier3 = modifier2;
                    readOnly3 = readOnly2;
                } else {
                    placeholder = placeholder4;
                    leadingIcon2 = leadingIcon;
                    shape3 = shape2;
                    trailingIcon = trailingIcon3;
                    textStyle3 = textStyle2;
                    prefix = prefix3;
                    label2 = label;
                    suffix = suffix3;
                    supportingText = supportingText3;
                    isError2 = isError4;
                    interactionSource2 = interactionSource6;
                    colors2 = colors;
                    modifier3 = modifier2;
                    visualTransformation2 = visualTransformation4;
                    keyboardOptions2 = keyboardOptions4;
                    readOnly3 = readOnly2;
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
                    isError2 = isError;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines2 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    $dirty2 &= -897;
                    readOnly3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    colors2 = colors;
                    modifier3 = modifier2;
                } else {
                    placeholder = function22;
                    leadingIcon2 = function23;
                    trailingIcon = function24;
                    prefix = function25;
                    suffix = function26;
                    supportingText = function27;
                    isError2 = isError;
                    visualTransformation2 = visualTransformation;
                    keyboardOptions2 = keyboardOptions;
                    keyboardActions2 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines2 = maxLines;
                    minLines2 = minLines;
                    interactionSource2 = interactionSource;
                    shape3 = shape;
                    readOnly3 = readOnly2;
                    textStyle3 = textStyle2;
                    label2 = label;
                    colors2 = colors;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1268528240, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:375)");
            }
            $composer3.startReplaceGroup(-508241210);
            ComposerKt.sourceInformation($composer3, "377@20083L39");
            if (interactionSource2 == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, -508240559, "CC(remember):TextField.kt#9igjgp");
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
            $composer3.startReplaceGroup(-508235100);
            ComposerKt.sourceInformation($composer3, "*381@20307L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle3.m6122getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource4, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors2.m2666textColorXeAY9LY$material3_release(enabled2, isError2, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            final TextStyle mergedTextStyle = textStyle3.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            final Function1<? super TextFieldValue, Unit> function13 = function12;
            final boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors2.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-1163788208, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt$TextField$3
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
                    ComposerKt.sourceInformation($composer4, "C391@20722L38,409@21525L989,387@20579L1945:TextField.kt#uh7d8r");
                    if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1163788208, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:387)");
                        }
                        Modifier modifier4 = Modifier.this;
                        boolean z = isError2;
                        Strings.Companion companion = Strings.INSTANCE;
                        Modifier m701defaultMinSizeVpY3zN4 = SizeKt.m701defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m2684getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m2683getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(colors2.m2616cursorColorvNxB06k$material3_release(isError2), null);
                        final TextFieldValue textFieldValue2 = textFieldValue;
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
                        final Shape shape5 = shape3;
                        final TextFieldColors textFieldColors = colors2;
                        BasicTextFieldKt.BasicTextField(textFieldValue, function13, m701defaultMinSizeVpY3zN4, enabled4, readOnly3, mergedTextStyle, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, minLines2, visualTransformation2, (Function1<? super TextLayoutResult, Unit>) null, interactionSource4, solidColor, ComposableLambdaKt.rememberComposableLambda(1751957978, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt$TextField$3.1
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
                                Function2<? super Composer, ? super Integer, Unit> function216;
                                ComposerKt.sourceInformation($composer5, "C411@21680L816:TextField.kt#uh7d8r");
                                int $dirty3 = $changed4;
                                if (($changed4 & 6) == 0) {
                                    function216 = function215;
                                    $dirty3 |= $composer5.changedInstance(function216) ? 4 : 2;
                                } else {
                                    function216 = function215;
                                }
                                if (($dirty3 & 19) == 18 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1751957978, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:411)");
                                }
                                TextFieldDefaults.INSTANCE.DecorationBox(TextFieldValue.this.getText(), function216, z2, z3, visualTransformation5, mutableInteractionSource, z4, function28, function29, function210, function211, function212, function213, function214, shape5, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
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
            modifier2 = modifier3;
            keyboardOptions3 = keyboardOptions2;
            maxLines3 = maxLines2;
            minLines3 = minLines2;
            visualTransformation3 = visualTransformation2;
            placeholder2 = placeholder;
            trailingIcon2 = trailingIcon;
            prefix2 = prefix;
            suffix2 = suffix;
            colors3 = colors2;
            readOnly4 = readOnly3;
            readOnly5 = singleLine2;
            label3 = label2;
            shape4 = shape3;
            textStyle4 = textStyle3;
            $composer2 = $composer3;
            isError3 = isError2;
            enabled3 = enabled4;
            keyboardActions3 = keyboardActions2;
            supportingText2 = supportingText;
            interactionSource5 = interactionSource3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2 leadingIcon4 = leadingIcon2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt$TextField$4
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
                    TextFieldKt.TextField(TextFieldValue.this, function1, modifier4, enabled3, readOnly4, textStyle4, label3, placeholder2, leadingIcon4, trailingIcon2, prefix2, suffix2, supportingText2, isError3, visualTransformation3, keyboardOptions3, keyboardActions3, readOnly5, maxLines3, minLines3, interactionSource5, shape4, colors3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0563  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0596  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x06f5  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x084c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x09bb  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x09ca  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x09ee  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0a75  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0a81  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0ab8  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0b4b  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0cb0  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0c95  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0ace A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0a87  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0a0a  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x09cd  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x09be  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x099d  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x083a  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x06df  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0582  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0223  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextFieldLayout(final androidx.compose.ui.Modifier r61, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r62, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r63, final kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r64, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r65, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r66, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r67, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r68, final boolean r69, float r70, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r71, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r72, androidx.compose.foundation.layout.PaddingValues r73, androidx.compose.runtime.Composer r74, final int r75, final int r76) {
        /*
            Method dump skipped, instructions count: 3297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, float, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int substractConstraintSafely(int $this$substractConstraintSafely, int from) {
        if ($this$substractConstraintSafely == Integer.MAX_VALUE) {
            return $this$substractConstraintSafely;
        }
        return $this$substractConstraintSafely - from;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateWidth-yeHjK3Y, reason: not valid java name */
    public static final int m2695calculateWidthyeHjK3Y(int leadingWidth, int trailingWidth, int prefixWidth, int suffixWidth, int textFieldWidth, int labelWidth, int placeholderWidth, long constraints) {
        int affixTotalWidth = prefixWidth + suffixWidth;
        int middleSection = Math.max(textFieldWidth + affixTotalWidth, Math.max(placeholderWidth + affixTotalWidth, labelWidth));
        int wrappedWidth = leadingWidth + middleSection + trailingWidth;
        return Math.max(wrappedWidth, Constraints.m6581getMinWidthimpl(constraints));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    public static final int m2694calculateHeightmKXJcVc(int textFieldHeight, int labelHeight, int leadingHeight, int trailingHeight, int prefixHeight, int suffixHeight, int placeholderHeight, int supportingHeight, float animationProgress, long constraints, float density, PaddingValues paddingValues) {
        float actualVerticalPadding;
        boolean hasLabel = labelHeight > 0;
        float arg0$iv = paddingValues.getTop();
        float other$iv = paddingValues.getBottom();
        float verticalPadding = Dp.m6626constructorimpl(arg0$iv + other$iv) * density;
        if (hasLabel) {
            float arg0$iv2 = TextFieldImplKt.getTextFieldPadding();
            actualVerticalPadding = MathHelpersKt.lerp(Dp.m6626constructorimpl(2 * arg0$iv2) * density, verticalPadding, animationProgress);
        } else {
            actualVerticalPadding = verticalPadding;
        }
        int inputFieldHeight = ComparisonsKt.maxOf(textFieldHeight, placeholderHeight, prefixHeight, suffixHeight, MathHelpersKt.lerp(labelHeight, 0, animationProgress));
        float middleSectionHeight = MathHelpersKt.lerp(0, labelHeight, animationProgress) + actualVerticalPadding + inputFieldHeight;
        return Math.max(Constraints.m6580getMinHeightimpl(constraints), Math.max(leadingHeight, Math.max(trailingHeight, MathKt.roundToInt(middleSectionHeight))) + supportingHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithLabel(Placeable.PlacementScope $this$placeWithLabel, int width, int totalHeight, Placeable textfieldPlaceable, Placeable labelPlaceable, Placeable placeholderPlaceable, Placeable leadingPlaceable, Placeable trailingPlaceable, Placeable prefixPlaceable, Placeable suffixPlaceable, Placeable containerPlaceable, Placeable supportingPlaceable, boolean singleLine, int labelEndPosition, int textPosition, float animationProgress, float density) {
        int startPosition;
        Placeable.PlacementScope.m5555place70tqf50$default($this$placeWithLabel, containerPlaceable, IntOffset.INSTANCE.m6764getZeronOccac(), 0.0f, 2, null);
        int height = totalHeight - TextFieldImplKt.heightOrZero(supportingPlaceable);
        if (leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, leadingPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (labelPlaceable != null) {
            if (singleLine) {
                startPosition = Alignment.INSTANCE.getCenterVertically().align(labelPlaceable.getHeight(), height);
            } else {
                startPosition = MathKt.roundToInt(TextFieldImplKt.getTextFieldPadding() * density);
            }
            int distance = startPosition - labelEndPosition;
            int positionY = startPosition - MathKt.roundToInt(distance * animationProgress);
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, labelPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), positionY, 0.0f, 4, null);
        }
        if (prefixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, prefixPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), textPosition, 0.0f, 4, null);
        }
        int textHorizontalPosition = TextFieldImplKt.widthOrZero(leadingPlaceable) + TextFieldImplKt.widthOrZero(prefixPlaceable);
        Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, textfieldPlaceable, textHorizontalPosition, textPosition, 0.0f, 4, null);
        if (placeholderPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, placeholderPlaceable, textHorizontalPosition, textPosition, 0.0f, 4, null);
        }
        if (suffixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, suffixPlaceable, (width - TextFieldImplKt.widthOrZero(trailingPlaceable)) - suffixPlaceable.getWidth(), textPosition, 0.0f, 4, null);
        }
        if (trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, trailingPlaceable, width - trailingPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (supportingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, supportingPlaceable, 0, height, 0.0f, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithoutLabel(Placeable.PlacementScope $this$placeWithoutLabel, int width, int totalHeight, Placeable textPlaceable, Placeable placeholderPlaceable, Placeable leadingPlaceable, Placeable trailingPlaceable, Placeable prefixPlaceable, Placeable suffixPlaceable, Placeable containerPlaceable, Placeable supportingPlaceable, boolean singleLine, float density, PaddingValues paddingValues) {
        Placeable.PlacementScope.m5555place70tqf50$default($this$placeWithoutLabel, containerPlaceable, IntOffset.INSTANCE.m6764getZeronOccac(), 0.0f, 2, null);
        int height = totalHeight - TextFieldImplKt.heightOrZero(supportingPlaceable);
        int topPadding = MathKt.roundToInt(paddingValues.getTop() * density);
        if (leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, leadingPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (prefixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, prefixPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), placeWithoutLabel$calculateVerticalPosition(singleLine, height, topPadding, prefixPlaceable), 0.0f, 4, null);
        }
        int textHorizontalPosition = TextFieldImplKt.widthOrZero(prefixPlaceable) + TextFieldImplKt.widthOrZero(leadingPlaceable);
        Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, textPlaceable, textHorizontalPosition, placeWithoutLabel$calculateVerticalPosition(singleLine, height, topPadding, textPlaceable), 0.0f, 4, null);
        if (placeholderPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, placeholderPlaceable, textHorizontalPosition, placeWithoutLabel$calculateVerticalPosition(singleLine, height, topPadding, placeholderPlaceable), 0.0f, 4, null);
        }
        if (suffixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, suffixPlaceable, (width - TextFieldImplKt.widthOrZero(trailingPlaceable)) - suffixPlaceable.getWidth(), placeWithoutLabel$calculateVerticalPosition(singleLine, height, topPadding, suffixPlaceable), 0.0f, 4, null);
        }
        if (trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, trailingPlaceable, width - trailingPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (supportingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, supportingPlaceable, 0, height, 0.0f, 4, null);
        }
    }

    private static final int placeWithoutLabel$calculateVerticalPosition(boolean $singleLine, int height, int topPadding, Placeable placeable) {
        if ($singleLine) {
            return Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), height);
        }
        return topPadding;
    }

    public static final Modifier drawIndicatorLine(Modifier $this$drawIndicatorLine, final State<BorderStroke> state) {
        return DrawModifierKt.drawWithContent($this$drawIndicatorLine, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.TextFieldKt$drawIndicatorLine$1
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
                $this$drawWithContent.drawContent();
                float strokeWidth = $this$drawWithContent.mo364toPx0680j_4(state.getValue().getWidth());
                float y = Size.m3988getHeightimpl($this$drawWithContent.mo4713getSizeNHjbRc()) - (strokeWidth / 2.0f);
                DrawScope.m4698drawLine1RTmtNc$default($this$drawWithContent, state.getValue().getBrush(), OffsetKt.Offset(0.0f, y), OffsetKt.Offset(Size.m3991getWidthimpl($this$drawWithContent.mo4713getSizeNHjbRc()), y), strokeWidth, 0, null, 0.0f, null, 0, 496, null);
            }
        });
    }

    public static final float getTextFieldWithLabelVerticalPadding() {
        return TextFieldWithLabelVerticalPadding;
    }
}
