package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: BasicTextField.kt */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÛ\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u001728\b\u0002\u0010\u0018\u001a2\u0012\u0004\u0012\u00020\u001a\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0019¢\u0006\u0002\b 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010#\u001a\u00020$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010(\u001a\u00020)H\u0007¢\u0006\u0002\u0010*\u001añ\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u001728\b\u0002\u0010\u0018\u001a2\u0012\u0004\u0012\u00020\u001a\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0019¢\u0006\u0002\b 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010#\u001a\u00020$2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010-\u001a\u00020\fH\u0001¢\u0006\u0002\u0010.\u001aâ\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u0002002\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0006022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u00020\f2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u0002092\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0006022\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$23\b\u0002\u0010:\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u001b¢\u0006\u0002\b;¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u000602¢\u0006\u0002\b;H\u0007¢\u0006\u0002\u0010=\u001aî\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u0002002\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0006022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u00020\f2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010>\u001a\u0002072\b\b\u0002\u00108\u001a\u0002092\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0006022\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010#\u001a\u00020$23\b\u0002\u0010:\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u001b¢\u0006\u0002\b;¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u000602¢\u0006\u0002\b;H\u0007¢\u0006\u0002\u0010?\u001aâ\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u00020@2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u0006022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u00020\f2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u0002092\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0006022\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$23\b\u0002\u0010:\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u001b¢\u0006\u0002\b;¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u000602¢\u0006\u0002\b;H\u0007¢\u0006\u0002\u0010A\u001aî\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u00020@2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u0006022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u00020\f2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010>\u001a\u0002072\b\b\u0002\u00108\u001a\u0002092\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0006022\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010#\u001a\u00020$23\b\u0002\u0010:\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u001b¢\u0006\u0002\b;¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u000602¢\u0006\u0002\b;H\u0007¢\u0006\u0002\u0010B\u001a\u0015\u0010C\u001a\u00020\u00062\u0006\u0010D\u001a\u00020EH\u0001¢\u0006\u0002\u0010F\u001a\u0015\u0010G\u001a\u00020\u00062\u0006\u0010D\u001a\u00020EH\u0001¢\u0006\u0002\u0010F\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004¨\u0006H²\u0006\n\u0010I\u001a\u00020JX\u008a\u0084\u0002²\u0006\n\u0010K\u001a\u00020JX\u008a\u0084\u0002²\u0006\n\u0010L\u001a\u00020JX\u008a\u0084\u0002²\u0006\n\u0010M\u001a\u000200X\u008a\u008e\u0002²\u0006\n\u0010N\u001a\u00020@X\u008a\u008e\u0002"}, d2 = {"DefaultTextFieldDecorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "MinTouchTargetSizeForHandles", "Landroidx/compose/ui/unit/DpSize;", "J", "BasicTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "Lkotlin/ExtensionFunctionType;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "decorator", "scrollState", "Landroidx/compose/foundation/ScrollState;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/input/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "codepointTransformation", "Landroidx/compose/foundation/text/input/internal/CodepointTransformation;", "isPassword", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/internal/CodepointTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/input/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;ZLandroidx/compose/runtime/Composer;III)V", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "decorationBox", "Landroidx/compose/runtime/Composable;", "innerTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "minLines", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "TextFieldCursorHandle", "selectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/runtime/Composer;I)V", "TextFieldSelectionHandles", "foundation_release", "cursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "startHandleState", "endHandleState", "textFieldValueState", "lastTextValue"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicTextFieldKt {
    private static final TextFieldDecorator DefaultTextFieldDecorator = BasicTextFieldKt$DefaultTextFieldDecorator$1.INSTANCE;
    private static final long MinTouchTargetSizeForHandles = DpKt.m6648DpSizeYgX7TsA(Dp.m6626constructorimpl(40), Dp.m6626constructorimpl(40));

    public static final void BasicTextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2, MutableInteractionSource interactionSource, Brush cursorBrush, OutputTransformation outputTransformation, TextFieldDecorator decorator, ScrollState scrollState, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean z;
        boolean z2;
        InputTransformation inputTransformation2;
        TextStyle textStyle2;
        KeyboardOptions keyboardOptions2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        InputTransformation inputTransformation3;
        MutableInteractionSource interactionSource2;
        OutputTransformation outputTransformation2;
        boolean readOnly2;
        ScrollState scrollState2;
        int $dirty1;
        Function2 onTextLayout;
        TextFieldDecorator decorator2;
        Brush cursorBrush2;
        boolean enabled2;
        KeyboardActionHandler onKeyboardAction2;
        TextStyle textStyle3;
        TextFieldLineLimits lineLimits2;
        KeyboardOptions keyboardOptions3;
        Composer $composer2;
        final Modifier modifier3;
        final boolean enabled3;
        final boolean readOnly3;
        final InputTransformation inputTransformation4;
        final TextStyle textStyle4;
        final KeyboardOptions keyboardOptions4;
        final KeyboardActionHandler onKeyboardAction3;
        final TextFieldLineLimits lineLimits3;
        final Function2 onTextLayout2;
        final MutableInteractionSource interactionSource3;
        final Brush cursorBrush3;
        final OutputTransformation outputTransformation3;
        final TextFieldDecorator decorator3;
        final ScrollState scrollState3;
        Composer $composer3 = $composer.startRestartGroup(469439921);
        ComposerKt.sourceInformation($composer3, "C(BasicTextField)P(13,7,2,11,3,14,5,8,6,9,4!1,10)184@10988L21,188@11134L610:BasicTextField.kt#423gt5");
        int $dirty = $changed;
        int $dirty12 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        int i9 = i & 2;
        if (i9 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 4;
        if (i10 != 0) {
            $dirty |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i11 = i & 8;
        if (i11 != 0) {
            $dirty |= 3072;
            z2 = readOnly;
        } else if (($changed & 3072) == 0) {
            z2 = readOnly;
            $dirty |= $composer3.changed(z2) ? 2048 : 1024;
        } else {
            z2 = readOnly;
        }
        int i12 = i & 16;
        int i13 = 8192;
        if (i12 != 0) {
            $dirty |= 24576;
            inputTransformation2 = inputTransformation;
        } else if (($changed & 24576) == 0) {
            inputTransformation2 = inputTransformation;
            $dirty |= $composer3.changed(inputTransformation2) ? 16384 : 8192;
        } else {
            inputTransformation2 = inputTransformation;
        }
        int i14 = i & 32;
        if (i14 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textStyle2 = textStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle2 = textStyle;
            $dirty |= $composer3.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        int i15 = i & 64;
        if (i15 != 0) {
            $dirty |= 1572864;
            keyboardOptions2 = keyboardOptions;
        } else if (($changed & 1572864) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i16 = i & 128;
        if (i16 != 0) {
            $dirty |= 12582912;
            i2 = i16;
        } else if (($changed & 12582912) == 0) {
            i2 = i16;
            $dirty |= $composer3.changed(onKeyboardAction) ? 8388608 : 4194304;
        } else {
            i2 = i16;
        }
        int i17 = i & 256;
        if (i17 != 0) {
            $dirty |= 100663296;
            i3 = i17;
        } else if (($changed & 100663296) == 0) {
            i3 = i17;
            $dirty |= $composer3.changed(lineLimits) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i17;
        }
        int i18 = i & 512;
        if (i18 != 0) {
            $dirty |= 805306368;
            i4 = i18;
        } else if (($changed & 805306368) == 0) {
            i4 = i18;
            $dirty |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        } else {
            i4 = i18;
        }
        int i19 = i & 1024;
        if (i19 != 0) {
            $dirty12 |= 6;
            i5 = i19;
        } else if (($changed1 & 6) == 0) {
            i5 = i19;
            $dirty12 |= $composer3.changed(interactionSource) ? 4 : 2;
        } else {
            i5 = i19;
        }
        int i20 = i & 2048;
        if (i20 != 0) {
            $dirty12 |= 48;
            i6 = i20;
        } else if (($changed1 & 48) == 0) {
            i6 = i20;
            $dirty12 |= $composer3.changed(cursorBrush) ? 32 : 16;
        } else {
            i6 = i20;
        }
        int i21 = i & 4096;
        if (i21 != 0) {
            $dirty12 |= 384;
            i7 = i21;
        } else {
            i7 = i21;
            if (($changed1 & 384) == 0) {
                $dirty12 |= $composer3.changed(outputTransformation) ? 256 : 128;
            }
        }
        int i22 = i & 8192;
        if (i22 != 0) {
            $dirty12 |= 3072;
            i8 = i22;
        } else {
            i8 = i22;
            if (($changed1 & 3072) == 0) {
                $dirty12 |= ($changed1 & 4096) == 0 ? $composer3.changed(decorator) : $composer3.changedInstance(decorator) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(scrollState)) {
                i13 = 16384;
            }
            $dirty12 |= i13;
        }
        if (($dirty & 306783379) == 306783378 && ($dirty12 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            onKeyboardAction3 = onKeyboardAction;
            interactionSource3 = interactionSource;
            scrollState3 = scrollState;
            $composer2 = $composer3;
            enabled3 = z;
            inputTransformation4 = inputTransformation2;
            textStyle4 = textStyle2;
            modifier3 = modifier2;
            lineLimits3 = lineLimits;
            cursorBrush3 = cursorBrush;
            outputTransformation3 = outputTransformation;
            decorator3 = decorator;
            keyboardOptions4 = keyboardOptions2;
            readOnly3 = z2;
            onTextLayout2 = function2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i9 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                boolean enabled4 = i10 != 0 ? true : z;
                boolean readOnly4 = i11 != 0 ? false : z2;
                inputTransformation3 = i12 != 0 ? null : inputTransformation2;
                if (i14 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i15 != 0) {
                    keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
                }
                KeyboardActionHandler onKeyboardAction4 = i2 != 0 ? null : onKeyboardAction;
                TextFieldLineLimits lineLimits4 = i3 != 0 ? TextFieldLineLimits.INSTANCE.getDefault() : lineLimits;
                Function2 onTextLayout3 = i4 != 0 ? null : function2;
                interactionSource2 = i5 != 0 ? null : interactionSource;
                SolidColor cursorBrush4 = i6 != 0 ? BasicTextFieldDefaults.INSTANCE.getCursorBrush() : cursorBrush;
                outputTransformation2 = i7 != 0 ? null : outputTransformation;
                TextFieldDecorator decorator4 = i8 != 0 ? null : decorator;
                boolean enabled5 = enabled4;
                if ((i & 16384) != 0) {
                    boolean readOnly5 = readOnly4;
                    $dirty1 = (-57345) & $dirty12;
                    enabled2 = enabled5;
                    readOnly2 = readOnly5;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    onTextLayout = onTextLayout3;
                    decorator2 = decorator4;
                    cursorBrush2 = cursorBrush4;
                    onKeyboardAction2 = onKeyboardAction4;
                    textStyle3 = textStyle2;
                    lineLimits2 = lineLimits4;
                    keyboardOptions3 = keyboardOptions2;
                } else {
                    readOnly2 = readOnly4;
                    scrollState2 = scrollState;
                    $dirty1 = $dirty12;
                    onTextLayout = onTextLayout3;
                    decorator2 = decorator4;
                    cursorBrush2 = cursorBrush4;
                    enabled2 = enabled5;
                    onKeyboardAction2 = onKeyboardAction4;
                    textStyle3 = textStyle2;
                    lineLimits2 = lineLimits4;
                    keyboardOptions3 = keyboardOptions2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16384) != 0) {
                    interactionSource2 = interactionSource;
                    cursorBrush2 = cursorBrush;
                    outputTransformation2 = outputTransformation;
                    decorator2 = decorator;
                    scrollState2 = scrollState;
                    $dirty1 = (-57345) & $dirty12;
                    keyboardOptions3 = keyboardOptions2;
                    enabled2 = z;
                    readOnly2 = z2;
                    inputTransformation3 = inputTransformation2;
                    textStyle3 = textStyle2;
                    onKeyboardAction2 = onKeyboardAction;
                    lineLimits2 = lineLimits;
                    onTextLayout = function2;
                } else {
                    onTextLayout = function2;
                    interactionSource2 = interactionSource;
                    cursorBrush2 = cursorBrush;
                    outputTransformation2 = outputTransformation;
                    decorator2 = decorator;
                    scrollState2 = scrollState;
                    keyboardOptions3 = keyboardOptions2;
                    $dirty1 = $dirty12;
                    enabled2 = z;
                    readOnly2 = z2;
                    inputTransformation3 = inputTransformation2;
                    textStyle3 = textStyle2;
                    onKeyboardAction2 = onKeyboardAction;
                    lineLimits2 = lineLimits;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(469439921, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:187)");
            }
            $composer2 = $composer3;
            Modifier modifier4 = modifier2;
            Function2 onTextLayout4 = onTextLayout;
            BasicTextField(state, modifier4, enabled2, readOnly2, inputTransformation3, textStyle3, keyboardOptions3, onKeyboardAction2, lineLimits2, onTextLayout4, interactionSource2, cursorBrush2, null, outputTransformation2, decorator2, scrollState2, false, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), ($dirty1 & 14) | 384 | ($dirty1 & 112) | (($dirty1 << 3) & 7168) | (($dirty1 << 3) & 57344) | (($dirty1 << 3) & 458752), 65536);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled2;
            readOnly3 = readOnly2;
            inputTransformation4 = inputTransformation3;
            textStyle4 = textStyle3;
            keyboardOptions4 = keyboardOptions3;
            onKeyboardAction3 = onKeyboardAction2;
            lineLimits3 = lineLimits2;
            onTextLayout2 = onTextLayout4;
            interactionSource3 = interactionSource2;
            cursorBrush3 = cursorBrush2;
            outputTransformation3 = outputTransformation2;
            decorator3 = decorator2;
            scrollState3 = scrollState2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$1
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

                public final void invoke(Composer composer, int i23) {
                    BasicTextFieldKt.BasicTextField(TextFieldState.this, modifier3, enabled3, readOnly3, inputTransformation4, textStyle4, keyboardOptions4, onKeyboardAction3, lineLimits3, onTextLayout2, interactionSource3, cursorBrush3, outputTransformation3, decorator3, scrollState3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0603  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x06eb  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0780  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x078c  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x07c3  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x086a  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x07d9  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0792  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0634  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0611  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0605  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x05d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final androidx.compose.foundation.text.input.TextFieldState r57, androidx.compose.ui.Modifier r58, boolean r59, boolean r60, androidx.compose.foundation.text.input.InputTransformation r61, androidx.compose.ui.text.TextStyle r62, androidx.compose.foundation.text.KeyboardOptions r63, androidx.compose.foundation.text.input.KeyboardActionHandler r64, androidx.compose.foundation.text.input.TextFieldLineLimits r65, kotlin.jvm.functions.Function2<? super androidx.compose.ui.unit.Density, ? super kotlin.jvm.functions.Function0<androidx.compose.ui.text.TextLayoutResult>, kotlin.Unit> r66, androidx.compose.foundation.interaction.MutableInteractionSource r67, androidx.compose.ui.graphics.Brush r68, androidx.compose.foundation.text.input.internal.CodepointTransformation r69, androidx.compose.foundation.text.input.OutputTransformation r70, androidx.compose.foundation.text.input.TextFieldDecorator r71, androidx.compose.foundation.ScrollState r72, boolean r73, androidx.compose.runtime.Composer r74, final int r75, final int r76, final int r77) {
        /*
            Method dump skipped, instructions count: 2227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.foundation.text.input.TextFieldState, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.foundation.text.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.input.KeyboardActionHandler, androidx.compose.foundation.text.input.TextFieldLineLimits, kotlin.jvm.functions.Function2, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, androidx.compose.foundation.text.input.internal.CodepointTransformation, androidx.compose.foundation.text.input.OutputTransformation, androidx.compose.foundation.text.input.TextFieldDecorator, androidx.compose.foundation.ScrollState, boolean, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final void TextFieldCursorHandle(final TextFieldSelectionState selectionState, Composer $composer, final int $changed) {
        Object value$iv;
        OffsetProvider value$iv2;
        BasicTextFieldKt$TextFieldCursorHandle$2$1 value$iv3;
        Composer $composer2 = $composer.startRestartGroup(1991581797);
        ComposerKt.sourceInformation($composer2, "C(TextFieldCursorHandle)416@20789L124:BasicTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(selectionState) ? 4 : 2;
        }
        if (($dirty & 3) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1991581797, $dirty, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (BasicTextField.kt:414)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -425876736, "CC(remember):BasicTextField.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt.derivedStateOf(new Function0<TextFieldHandleState>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldCursorHandle$cursorHandleState$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final TextFieldHandleState invoke() {
                        return TextFieldSelectionState.this.getCursorHandleState$foundation_release(false);
                    }
                });
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            State cursorHandleState$delegate = (State) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (TextFieldCursorHandle$lambda$9(cursorHandleState$delegate).getVisible()) {
                $composer2.startReplaceGroup(-317108348);
                ComposerKt.sourceInformation($composer2, "423@21002L142,429@21224L87,422@20959L426");
                ComposerKt.sourceInformationMarkerStart($composer2, -425869902, "CC(remember):BasicTextField.kt#9igjgp");
                boolean invalid$iv = $composer2.changedInstance(selectionState);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldCursorHandle$1$1
                        @Override // androidx.compose.foundation.text.selection.OffsetProvider
                        /* renamed from: provide-F1C5BW0, reason: not valid java name */
                        public final long mo961provideF1C5BW0() {
                            return TextFieldSelectionState.this.getCursorHandleState$foundation_release(true).m1230getPositionF1C5BW0();
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                OffsetProvider offsetProvider = (OffsetProvider) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -425862853, "CC(remember):BasicTextField.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changedInstance(selectionState);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new BasicTextFieldKt$TextFieldCursorHandle$2$1(selectionState, null);
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AndroidCursorHandle_androidKt.m958CursorHandleUSBMPiE(offsetProvider, SuspendingPointerInputFilterKt.pointerInput(companion, selectionState, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv3), MinTouchTargetSizeForHandles, $composer2, 384, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-316683586);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldCursorHandle$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    BasicTextFieldKt.TextFieldCursorHandle(TextFieldSelectionState.this, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final TextFieldHandleState TextFieldCursorHandle$lambda$9(State<TextFieldHandleState> state) {
        Object thisObj$iv = state.getValue();
        return (TextFieldHandleState) thisObj$iv;
    }

    public static final void TextFieldSelectionHandles(final TextFieldSelectionState selectionState, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        OffsetProvider value$iv3;
        BasicTextFieldKt$TextFieldSelectionHandles$4$1 value$iv4;
        OffsetProvider value$iv5;
        OffsetProvider offsetProvider;
        BasicTextFieldKt$TextFieldSelectionHandles$2$1 value$iv6;
        Composer $composer2 = $composer.startRestartGroup(2025287684);
        ComposerKt.sourceInformation($composer2, "C(TextFieldSelectionHandles)442@21589L149,465@22467L150:BasicTextField.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(selectionState) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2025287684, $dirty2, -1, "androidx.compose.foundation.text.TextFieldSelectionHandles (BasicTextField.kt:440)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1983345958, "CC(remember):BasicTextField.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt.derivedStateOf(new Function0<TextFieldHandleState>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$startHandleState$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final TextFieldHandleState invoke() {
                        return TextFieldSelectionState.this.getSelectionHandleState$foundation_release(true, false);
                    }
                });
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            State startHandleState$delegate = (State) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (TextFieldSelectionHandles$lambda$13(startHandleState$delegate).getVisible()) {
                $composer2.startReplaceGroup(-1353986043);
                ComposerKt.sourceInformation($composer2, "449@21829L167,457@22207L86,448@21783L584");
                ComposerKt.sourceInformationMarkerStart($composer2, -1983338260, "CC(remember):BasicTextField.kt#9igjgp");
                boolean invalid$iv = $composer2.changedInstance(selectionState);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv5 = new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$1$1
                        @Override // androidx.compose.foundation.text.selection.OffsetProvider
                        /* renamed from: provide-F1C5BW0 */
                        public final long mo961provideF1C5BW0() {
                            return TextFieldSelectionState.this.getSelectionHandleState$foundation_release(true, true).m1230getPositionF1C5BW0();
                        }
                    };
                    $composer2.updateRememberedValue(value$iv5);
                } else {
                    value$iv5 = it$iv2;
                }
                OffsetProvider offsetProvider2 = (OffsetProvider) value$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ResolvedTextDirection direction = TextFieldSelectionHandles$lambda$13(startHandleState$delegate).getDirection();
                boolean handlesCrossed = TextFieldSelectionHandles$lambda$13(startHandleState$delegate).getHandlesCrossed();
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1983326245, "CC(remember):BasicTextField.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changedInstance(selectionState);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    offsetProvider = offsetProvider2;
                    value$iv6 = new BasicTextFieldKt$TextFieldSelectionHandles$2$1(selectionState, null);
                    $composer2.updateRememberedValue(value$iv6);
                } else {
                    offsetProvider = offsetProvider2;
                    value$iv6 = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AndroidSelectionHandles_androidKt.m1309SelectionHandlepzduO1o(offsetProvider, true, direction, handlesCrossed, MinTouchTargetSizeForHandles, SuspendingPointerInputFilterKt.pointerInput(companion, selectionState, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv6), $composer2, 24624, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1353409443);
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1983317861, "CC(remember):BasicTextField.kt#9igjgp");
            Object it$iv4 = $composer2.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt.derivedStateOf(new Function0<TextFieldHandleState>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$endHandleState$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final TextFieldHandleState invoke() {
                        return TextFieldSelectionState.this.getSelectionHandleState$foundation_release(false, false);
                    }
                });
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv4;
            }
            State endHandleState$delegate = (State) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (TextFieldSelectionHandles$lambda$17(endHandleState$delegate).getVisible()) {
                $composer2.startReplaceGroup(-1353116090);
                ComposerKt.sourceInformation($composer2, "472@22706L168,480@23082L87,471@22660L583");
                ComposerKt.sourceInformationMarkerStart($composer2, -1983310195, "CC(remember):BasicTextField.kt#9igjgp");
                boolean invalid$iv3 = $composer2.changedInstance(selectionState);
                Object it$iv5 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$3$1
                        @Override // androidx.compose.foundation.text.selection.OffsetProvider
                        /* renamed from: provide-F1C5BW0 */
                        public final long mo961provideF1C5BW0() {
                            return TextFieldSelectionState.this.getSelectionHandleState$foundation_release(false, true).m1230getPositionF1C5BW0();
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv5;
                }
                OffsetProvider offsetProvider3 = (OffsetProvider) value$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ResolvedTextDirection direction2 = TextFieldSelectionHandles$lambda$17(endHandleState$delegate).getDirection();
                boolean handlesCrossed2 = TextFieldSelectionHandles$lambda$17(endHandleState$delegate).getHandlesCrossed();
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1983298244, "CC(remember):BasicTextField.kt#9igjgp");
                boolean invalid$iv4 = $composer2.changedInstance(selectionState);
                Object it$iv6 = $composer2.rememberedValue();
                if (invalid$iv4 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new BasicTextFieldKt$TextFieldSelectionHandles$4$1(selectionState, null);
                    $composer2.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv6;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AndroidSelectionHandles_androidKt.m1309SelectionHandlepzduO1o(offsetProvider3, false, direction2, handlesCrossed2, MinTouchTargetSizeForHandles, SuspendingPointerInputFilterKt.pointerInput(companion2, selectionState, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4), $composer2, 24624, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1352540451);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    BasicTextFieldKt.TextFieldSelectionHandles(TextFieldSelectionState.this, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final TextFieldHandleState TextFieldSelectionHandles$lambda$13(State<TextFieldHandleState> state) {
        Object thisObj$iv = state.getValue();
        return (TextFieldHandleState) thisObj$iv;
    }

    private static final TextFieldHandleState TextFieldSelectionHandles$lambda$17(State<TextFieldHandleState> state) {
        Object thisObj$iv = state.getValue();
        return (TextFieldHandleState) thisObj$iv;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x042f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0473  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final java.lang.String r36, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r37, androidx.compose.ui.Modifier r38, boolean r39, boolean r40, androidx.compose.ui.text.TextStyle r41, androidx.compose.foundation.text.KeyboardOptions r42, androidx.compose.foundation.text.KeyboardActions r43, boolean r44, int r45, int r46, androidx.compose.ui.text.input.VisualTransformation r47, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, androidx.compose.ui.graphics.Brush r50, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 1361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue BasicTextField$lambda$21(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String BasicTextField$lambda$25(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:97:0x0433  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final androidx.compose.ui.text.input.TextFieldValue r37, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r38, androidx.compose.ui.Modifier r39, boolean r40, boolean r41, androidx.compose.ui.text.TextStyle r42, androidx.compose.foundation.text.KeyboardOptions r43, androidx.compose.foundation.text.KeyboardActions r44, boolean r45, int r46, int r47, androidx.compose.ui.text.input.VisualTransformation r48, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r49, androidx.compose.foundation.interaction.MutableInteractionSource r50, androidx.compose.ui.graphics.Brush r51, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, androidx.compose.runtime.Composer r53, final int r54, final int r55, final int r56) {
        /*
            Method dump skipped, instructions count: 1130
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ void BasicTextField(final String value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, VisualTransformation visualTransformation, Function1 onTextLayout, MutableInteractionSource interactionSource, Brush cursorBrush, Function3 decorationBox, Composer $composer, final int $changed, final int $changed1, final int i) {
        String str;
        Modifier modifier2;
        boolean z;
        boolean z2;
        TextStyle textStyle2;
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Modifier modifier3;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        MutableInteractionSource interactionSource3;
        Brush cursorBrush2;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled3;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final boolean singleLine2;
        final int maxLines2;
        final VisualTransformation visualTransformation2;
        final Function1 onTextLayout2;
        final MutableInteractionSource interactionSource4;
        final Brush cursorBrush3;
        final Function3 decorationBox2;
        Object value$iv;
        Composer $composer3 = $composer.startRestartGroup(-454732590);
        ComposerKt.sourceInformation($composer3, "C(BasicTextField)P(13,9,7,2,10,12,5,4,11,6,14,8,3)803@41337L39,808@41577L579:BasicTextField.kt#423gt5");
        int $dirty = $changed;
        int $dirty1 = $changed1;
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
            $dirty |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 8;
        if (i10 != 0) {
            $dirty |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i11 = i & 16;
        if (i11 != 0) {
            $dirty |= 24576;
            z2 = readOnly;
        } else if (($changed & 24576) == 0) {
            z2 = readOnly;
            $dirty |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = readOnly;
        }
        int i12 = i & 32;
        if (i12 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textStyle2 = textStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle2 = textStyle;
            $dirty |= $composer3.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        int i13 = i & 64;
        if (i13 != 0) {
            $dirty |= 1572864;
            keyboardOptions2 = keyboardOptions;
        } else if (($changed & 1572864) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i14 = i & 128;
        if (i14 != 0) {
            $dirty |= 12582912;
            keyboardActions2 = keyboardActions;
        } else if (($changed & 12582912) == 0) {
            keyboardActions2 = keyboardActions;
            $dirty |= $composer3.changed(keyboardActions2) ? 8388608 : 4194304;
        } else {
            keyboardActions2 = keyboardActions;
        }
        int i15 = i & 256;
        if (i15 != 0) {
            $dirty |= 100663296;
            i2 = i15;
        } else if (($changed & 100663296) == 0) {
            i2 = i15;
            $dirty |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i15;
        }
        int i16 = i & 512;
        if (i16 != 0) {
            $dirty |= 805306368;
            i3 = i16;
        } else if (($changed & 805306368) == 0) {
            i3 = i16;
            $dirty |= $composer3.changed(maxLines) ? 536870912 : 268435456;
        } else {
            i3 = i16;
        }
        int i17 = i & 1024;
        if (i17 != 0) {
            $dirty1 |= 6;
            i4 = i17;
        } else if (($changed1 & 6) == 0) {
            i4 = i17;
            $dirty1 |= $composer3.changed(visualTransformation) ? 4 : 2;
        } else {
            i4 = i17;
        }
        int i18 = i & 2048;
        if (i18 != 0) {
            $dirty1 |= 48;
            i5 = i18;
        } else if (($changed1 & 48) == 0) {
            i5 = i18;
            $dirty1 |= $composer3.changedInstance(onTextLayout) ? 32 : 16;
        } else {
            i5 = i18;
        }
        int i19 = i & 4096;
        if (i19 != 0) {
            $dirty1 |= 384;
            i6 = i19;
        } else {
            i6 = i19;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changed(interactionSource) ? 256 : 128;
            }
        }
        int i20 = i & 8192;
        if (i20 != 0) {
            $dirty1 |= 3072;
            i7 = i20;
        } else {
            i7 = i20;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changed(cursorBrush) ? 2048 : 1024;
            }
        }
        int i21 = i & 16384;
        if (i21 != 0) {
            $dirty1 |= 24576;
            i8 = i21;
        } else {
            i8 = i21;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer3.changedInstance(decorationBox) ? 16384 : 8192;
            }
        }
        if (($dirty & 306783379) == 306783378 && ($dirty1 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            Modifier modifier5 = modifier2;
            keyboardActions3 = keyboardActions2;
            modifier4 = modifier5;
            maxLines2 = maxLines;
            interactionSource4 = interactionSource;
            decorationBox2 = decorationBox;
            $composer2 = $composer3;
            enabled3 = z;
            readOnly2 = z2;
            textStyle3 = textStyle2;
            keyboardOptions3 = keyboardOptions2;
            singleLine2 = singleLine;
            visualTransformation2 = visualTransformation;
            onTextLayout2 = onTextLayout;
            cursorBrush3 = cursorBrush;
        } else {
            Modifier.Companion modifier6 = i9 != 0 ? Modifier.INSTANCE : modifier2;
            boolean enabled4 = i10 != 0 ? true : z;
            boolean readOnly3 = i11 != 0 ? false : z2;
            TextStyle textStyle4 = i12 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
            if (i13 != 0) {
                keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
            }
            KeyboardActions keyboardActions4 = i14 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
            boolean singleLine3 = i2 != 0 ? false : singleLine;
            int maxLines3 = i3 != 0 ? Integer.MAX_VALUE : maxLines;
            VisualTransformation visualTransformation3 = i4 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
            Function1 onTextLayout3 = i5 != 0 ? new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$13
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                    invoke2(textLayoutResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextLayoutResult it) {
                }
            } : onTextLayout;
            if (i6 != 0) {
                modifier3 = modifier6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1242906675, "CC(remember):BasicTextField.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                enabled2 = enabled4;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                interactionSource2 = (MutableInteractionSource) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                modifier3 = modifier6;
                enabled2 = enabled4;
                interactionSource2 = interactionSource;
            }
            if (i7 != 0) {
                interactionSource3 = interactionSource2;
                cursorBrush2 = new SolidColor(Color.INSTANCE.m4189getBlack0d7_KjU(), null);
            } else {
                interactionSource3 = interactionSource2;
                cursorBrush2 = cursorBrush;
            }
            Function3 decorationBox3 = i8 != 0 ? ComposableSingletons$BasicTextFieldKt.INSTANCE.m979getLambda3$foundation_release() : decorationBox;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-454732590, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:807)");
            }
            int i22 = (($dirty1 << 3) & 112) | 6 | (($dirty1 << 3) & 896) | (($dirty1 << 3) & 7168) | (($dirty1 << 3) & 57344) | (458752 & ($dirty1 << 3));
            KeyboardOptions keyboardOptions4 = keyboardOptions2;
            MutableInteractionSource interactionSource5 = interactionSource3;
            $composer2 = $composer3;
            String str2 = str;
            Modifier modifier7 = modifier3;
            boolean enabled5 = enabled2;
            BasicTextField(str2, (Function1<? super String, Unit>) onValueChange, modifier7, enabled5, readOnly3, textStyle4, keyboardOptions4, keyboardActions4, singleLine3, maxLines3, 1, visualTransformation3, (Function1<? super TextLayoutResult, Unit>) onTextLayout3, interactionSource5, cursorBrush2, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) decorationBox3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), i22, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier7;
            enabled3 = enabled5;
            readOnly2 = readOnly3;
            textStyle3 = textStyle4;
            keyboardOptions3 = keyboardOptions4;
            keyboardActions3 = keyboardActions4;
            singleLine2 = singleLine3;
            maxLines2 = maxLines3;
            visualTransformation2 = visualTransformation3;
            onTextLayout2 = onTextLayout3;
            interactionSource4 = interactionSource5;
            cursorBrush3 = cursorBrush2;
            decorationBox2 = decorationBox3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$15
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

                public final void invoke(Composer composer, int i23) {
                    BasicTextFieldKt.BasicTextField(value, onValueChange, modifier4, enabled3, readOnly2, textStyle3, keyboardOptions3, keyboardActions3, singleLine2, maxLines2, visualTransformation2, onTextLayout2, interactionSource4, cursorBrush3, decorationBox2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ void BasicTextField(final TextFieldValue value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, VisualTransformation visualTransformation, Function1 onTextLayout, MutableInteractionSource interactionSource, Brush cursorBrush, Function3 decorationBox, Composer $composer, final int $changed, final int $changed1, final int i) {
        TextFieldValue textFieldValue;
        Modifier modifier2;
        boolean z;
        boolean z2;
        TextStyle textStyle2;
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Modifier modifier3;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        MutableInteractionSource interactionSource3;
        Brush cursorBrush2;
        Composer $composer2;
        final Modifier modifier4;
        final boolean enabled3;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final boolean singleLine2;
        final int maxLines2;
        final VisualTransformation visualTransformation2;
        final Function1 onTextLayout2;
        final MutableInteractionSource interactionSource4;
        final Brush cursorBrush3;
        final Function3 decorationBox2;
        Object value$iv;
        Composer $composer3 = $composer.startRestartGroup(-560482651);
        ComposerKt.sourceInformation($composer3, "C(BasicTextField)P(13,9,7,2,10,12,5,4,11,6,14,8,3)843@42862L39,848@43102L579:BasicTextField.kt#423gt5");
        int $dirty = $changed;
        int $dirty1 = $changed1;
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
            $dirty |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 8;
        if (i10 != 0) {
            $dirty |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i11 = i & 16;
        if (i11 != 0) {
            $dirty |= 24576;
            z2 = readOnly;
        } else if (($changed & 24576) == 0) {
            z2 = readOnly;
            $dirty |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = readOnly;
        }
        int i12 = i & 32;
        if (i12 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textStyle2 = textStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle2 = textStyle;
            $dirty |= $composer3.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        int i13 = i & 64;
        if (i13 != 0) {
            $dirty |= 1572864;
            keyboardOptions2 = keyboardOptions;
        } else if (($changed & 1572864) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i14 = i & 128;
        if (i14 != 0) {
            $dirty |= 12582912;
            keyboardActions2 = keyboardActions;
        } else if (($changed & 12582912) == 0) {
            keyboardActions2 = keyboardActions;
            $dirty |= $composer3.changed(keyboardActions2) ? 8388608 : 4194304;
        } else {
            keyboardActions2 = keyboardActions;
        }
        int i15 = i & 256;
        if (i15 != 0) {
            $dirty |= 100663296;
            i2 = i15;
        } else if (($changed & 100663296) == 0) {
            i2 = i15;
            $dirty |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i15;
        }
        int i16 = i & 512;
        if (i16 != 0) {
            $dirty |= 805306368;
            i3 = i16;
        } else if (($changed & 805306368) == 0) {
            i3 = i16;
            $dirty |= $composer3.changed(maxLines) ? 536870912 : 268435456;
        } else {
            i3 = i16;
        }
        int i17 = i & 1024;
        if (i17 != 0) {
            $dirty1 |= 6;
            i4 = i17;
        } else if (($changed1 & 6) == 0) {
            i4 = i17;
            $dirty1 |= $composer3.changed(visualTransformation) ? 4 : 2;
        } else {
            i4 = i17;
        }
        int i18 = i & 2048;
        if (i18 != 0) {
            $dirty1 |= 48;
            i5 = i18;
        } else if (($changed1 & 48) == 0) {
            i5 = i18;
            $dirty1 |= $composer3.changedInstance(onTextLayout) ? 32 : 16;
        } else {
            i5 = i18;
        }
        int i19 = i & 4096;
        if (i19 != 0) {
            $dirty1 |= 384;
            i6 = i19;
        } else {
            i6 = i19;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changed(interactionSource) ? 256 : 128;
            }
        }
        int i20 = i & 8192;
        if (i20 != 0) {
            $dirty1 |= 3072;
            i7 = i20;
        } else {
            i7 = i20;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changed(cursorBrush) ? 2048 : 1024;
            }
        }
        int i21 = i & 16384;
        if (i21 != 0) {
            $dirty1 |= 24576;
            i8 = i21;
        } else {
            i8 = i21;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer3.changedInstance(decorationBox) ? 16384 : 8192;
            }
        }
        if (($dirty & 306783379) == 306783378 && ($dirty1 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            Modifier modifier5 = modifier2;
            keyboardActions3 = keyboardActions2;
            modifier4 = modifier5;
            maxLines2 = maxLines;
            interactionSource4 = interactionSource;
            decorationBox2 = decorationBox;
            $composer2 = $composer3;
            enabled3 = z;
            readOnly2 = z2;
            textStyle3 = textStyle2;
            keyboardOptions3 = keyboardOptions2;
            singleLine2 = singleLine;
            visualTransformation2 = visualTransformation;
            onTextLayout2 = onTextLayout;
            cursorBrush3 = cursorBrush;
        } else {
            Modifier.Companion modifier6 = i9 != 0 ? Modifier.INSTANCE : modifier2;
            boolean enabled4 = i10 != 0 ? true : z;
            boolean readOnly3 = i11 != 0 ? false : z2;
            TextStyle textStyle4 = i12 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
            if (i13 != 0) {
                keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
            }
            KeyboardActions keyboardActions4 = i14 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
            boolean singleLine3 = i2 != 0 ? false : singleLine;
            int maxLines3 = i3 != 0 ? Integer.MAX_VALUE : maxLines;
            VisualTransformation visualTransformation3 = i4 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
            Function1 onTextLayout3 = i5 != 0 ? new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$16
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                    invoke2(textLayoutResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextLayoutResult it) {
                }
            } : onTextLayout;
            if (i6 != 0) {
                modifier3 = modifier6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1242857875, "CC(remember):BasicTextField.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                enabled2 = enabled4;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                interactionSource2 = (MutableInteractionSource) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                modifier3 = modifier6;
                enabled2 = enabled4;
                interactionSource2 = interactionSource;
            }
            if (i7 != 0) {
                interactionSource3 = interactionSource2;
                cursorBrush2 = new SolidColor(Color.INSTANCE.m4189getBlack0d7_KjU(), null);
            } else {
                interactionSource3 = interactionSource2;
                cursorBrush2 = cursorBrush;
            }
            Function3 decorationBox3 = i8 != 0 ? ComposableSingletons$BasicTextFieldKt.INSTANCE.m980getLambda4$foundation_release() : decorationBox;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-560482651, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:847)");
            }
            int i22 = (($dirty1 << 3) & 112) | 6 | (($dirty1 << 3) & 896) | (($dirty1 << 3) & 7168) | (($dirty1 << 3) & 57344) | (458752 & ($dirty1 << 3));
            KeyboardOptions keyboardOptions4 = keyboardOptions2;
            MutableInteractionSource interactionSource5 = interactionSource3;
            $composer2 = $composer3;
            TextFieldValue textFieldValue2 = textFieldValue;
            Modifier modifier7 = modifier3;
            boolean enabled5 = enabled2;
            BasicTextField(textFieldValue2, (Function1<? super TextFieldValue, Unit>) onValueChange, modifier7, enabled5, readOnly3, textStyle4, keyboardOptions4, keyboardActions4, singleLine3, maxLines3, 1, visualTransformation3, (Function1<? super TextLayoutResult, Unit>) onTextLayout3, interactionSource5, cursorBrush2, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) decorationBox3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), i22, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier7;
            enabled3 = enabled5;
            readOnly2 = readOnly3;
            textStyle3 = textStyle4;
            keyboardOptions3 = keyboardOptions4;
            keyboardActions3 = keyboardActions4;
            singleLine2 = singleLine3;
            maxLines2 = maxLines3;
            visualTransformation2 = visualTransformation3;
            onTextLayout2 = onTextLayout3;
            interactionSource4 = interactionSource5;
            cursorBrush3 = cursorBrush2;
            decorationBox2 = decorationBox3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$18
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

                public final void invoke(Composer composer, int i23) {
                    BasicTextFieldKt.BasicTextField(TextFieldValue.this, onValueChange, modifier4, enabled3, readOnly2, textStyle3, keyboardOptions3, keyboardActions3, singleLine2, maxLines2, visualTransformation2, onTextLayout2, interactionSource4, cursorBrush3, decorationBox2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }
}
