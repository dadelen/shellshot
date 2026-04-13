package androidx.compose.material;

import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: TextFieldDefaults.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JP\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J×\u0001\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u0002042\u0013\b\u0002\u00105\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+H\u0007¢\u0006\u0002\u00106Já\u0001\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u0002042\u0013\b\u0002\u00105\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+H\u0007¢\u0006\u0002\u00107JÂ\u0001\u00108\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u000204H\u0007¢\u0006\u0002\u00109JÌ\u0001\u00108\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u000204H\u0007¢\u0006\u0002\u0010:Jä\u0001\u0010;\u001a\u00020 2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=2\b\b\u0002\u0010A\u001a\u00020=2\b\b\u0002\u0010B\u001a\u00020=2\b\b\u0002\u0010C\u001a\u00020=2\b\b\u0002\u0010D\u001a\u00020=2\b\b\u0002\u0010E\u001a\u00020=2\b\b\u0002\u0010F\u001a\u00020=2\b\b\u0002\u0010G\u001a\u00020=2\b\b\u0002\u0010H\u001a\u00020=2\b\b\u0002\u0010I\u001a\u00020=2\b\b\u0002\u0010J\u001a\u00020=2\b\b\u0002\u0010K\u001a\u00020=2\b\b\u0002\u0010L\u001a\u00020=2\b\b\u0002\u0010M\u001a\u00020=2\b\b\u0002\u0010N\u001a\u00020=2\b\b\u0002\u0010O\u001a\u00020=2\b\b\u0002\u0010P\u001a\u00020=2\b\b\u0002\u0010Q\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ:\u0010T\u001a\u0002042\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u00062\b\b\u0002\u0010W\u001a\u00020\u00062\b\b\u0002\u0010X\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bY\u0010ZJä\u0001\u0010[\u001a\u00020 2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=2\b\b\u0002\u0010A\u001a\u00020=2\b\b\u0002\u0010\\\u001a\u00020=2\b\b\u0002\u0010]\u001a\u00020=2\b\b\u0002\u0010^\u001a\u00020=2\b\b\u0002\u0010_\u001a\u00020=2\b\b\u0002\u0010F\u001a\u00020=2\b\b\u0002\u0010G\u001a\u00020=2\b\b\u0002\u0010H\u001a\u00020=2\b\b\u0002\u0010I\u001a\u00020=2\b\b\u0002\u0010J\u001a\u00020=2\b\b\u0002\u0010K\u001a\u00020=2\b\b\u0002\u0010L\u001a\u00020=2\b\b\u0002\u0010M\u001a\u00020=2\b\b\u0002\u0010N\u001a\u00020=2\b\b\u0002\u0010O\u001a\u00020=2\b\b\u0002\u0010P\u001a\u00020=2\b\b\u0002\u0010Q\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\b`\u0010SJ:\u0010a\u001a\u0002042\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010W\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u00062\b\b\u0002\u0010X\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bb\u0010ZJ:\u0010c\u001a\u0002042\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u00062\b\b\u0002\u0010W\u001a\u00020\u00062\b\b\u0002\u0010X\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bd\u0010ZJJ\u0010e\u001a\u00020f*\u00020f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010g\u001a\u00020\u00062\b\b\u0002\u0010h\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bi\u0010jR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\f\u0010\bR\u0019\u0010\r\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0015\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0016\u0010\bR\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006k"}, d2 = {"Landroidx/compose/material/TextFieldDefaults;", "", "()V", "BackgroundOpacity", "", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "IconOpacity", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "OutlinedTextFieldShape", "Landroidx/compose/ui/graphics/Shape;", "getOutlinedTextFieldShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "TextFieldShape", "getTextFieldShape", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "UnfocusedIndicatorLineOpacity", "BorderBox", "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material/TextFieldColors;", "shape", "focusedBorderThickness", "unfocusedBorderThickness", "BorderBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "OutlinedTextFieldDecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", OutlinedTextFieldKt.BorderId, "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "TextFieldDecorationBox", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "outlinedTextFieldColors", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", "backgroundColor", "cursorColor", "errorCursorColor", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "outlinedTextFieldColors-dx8h9Zs", "(JJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "outlinedTextFieldPadding", "start", "top", "end", "bottom", "outlinedTextFieldPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "textFieldColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "textFieldColors-dx8h9Zs", "textFieldWithLabelPadding", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "indicatorLine", "Landroidx/compose/ui/Modifier;", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldDefaults {
    public static final int $stable = 0;
    public static final float BackgroundOpacity = 0.12f;
    public static final float IconOpacity = 0.54f;
    public static final float UnfocusedIndicatorLineOpacity = 0.42f;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m6626constructorimpl(56);
    private static final float MinWidth = Dp.m6626constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m6626constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m6626constructorimpl(2);

    private TextFieldDefaults() {
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1700getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1701getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    public final Shape getTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1117199624, "C233@8406L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1117199624, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-TextFieldShape> (TextFieldDefaults.kt:233)");
        }
        CornerBasedShape copy$default = CornerBasedShape.copy$default(MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall(), null, null, CornerSizeKt.getZeroCornerSize(), CornerSizeKt.getZeroCornerSize(), 3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return copy$default;
    }

    public final Shape getOutlinedTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1899109048, "C242@8708L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1899109048, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-OutlinedTextFieldShape> (TextFieldDefaults.kt:242)");
        }
        CornerBasedShape small = MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return small;
    }

    /* renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1702getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1699getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1694indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedBorderThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedBorderThickness;
        }
        return textFieldDefaults.m1703indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    /* renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m1703indicatorLinegv0btCI(Modifier $this$indicatorLine_u2dgv0btCI, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, final float focusedIndicatorLineThickness, final float unfocusedIndicatorLineThickness) {
        return ComposedModifierKt.composed($this$indicatorLine_u2dgv0btCI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
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
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.TextFieldDefaults$indicatorLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                State stroke;
                $composer.startReplaceGroup(1398930845);
                ComposerKt.sourceInformation($composer, "C299@11110L217:TextFieldDefaults.kt#jmzs0o");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1398930845, $changed, -1, "androidx.compose.material.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:299)");
                }
                stroke = TextFieldDefaultsKt.m1710animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness, $composer, 0);
                Modifier drawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.INSTANCE, (BorderStroke) stroke.getValue());
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return drawIndicatorLine;
            }
        });
    }

    /* renamed from: BorderBox-nbWgWpA, reason: not valid java name */
    public final void m1698BorderBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        InteractionSource interactionSource2;
        TextFieldColors textFieldColors;
        Shape shape2;
        float focusedBorderThickness2;
        float f;
        float focusedBorderThickness3;
        float unfocusedBorderThickness2;
        State borderStroke;
        Composer $composer2;
        final float focusedBorderThickness4;
        final float unfocusedBorderThickness3;
        final Shape shape3;
        int i2;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(943754022);
        ComposerKt.sourceInformation($composer3, "C(BorderBox)P(1,4,3!1,5,2:c#ui.unit.Dp,6:c#ui.unit.Dp)332@12464L22,336@12648L203,344@12860L47:TextFieldDefaults.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            z = enabled;
        } else if (($changed & 6) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = enabled;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            z2 = isError;
        } else if (($changed & 48) == 0) {
            z2 = isError;
            $dirty |= $composer3.changed(z2) ? 32 : 16;
        } else {
            z2 = isError;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
            interactionSource2 = interactionSource;
        } else if (($changed & 384) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer3.changed(interactionSource2) ? 256 : 128;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            textFieldColors = colors;
        } else if (($changed & 3072) == 0) {
            textFieldColors = colors;
            $dirty |= $composer3.changed(textFieldColors) ? 2048 : 1024;
        } else {
            textFieldColors = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                focusedBorderThickness2 = focusedBorderThickness;
                if ($composer3.changed(focusedBorderThickness2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                focusedBorderThickness2 = focusedBorderThickness;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            focusedBorderThickness2 = focusedBorderThickness;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                f = unfocusedBorderThickness;
                if ($composer3.changed(f)) {
                    i2 = 1048576;
                    $dirty |= i2;
                }
            } else {
                f = unfocusedBorderThickness;
            }
            i2 = 524288;
            $dirty |= i2;
        } else {
            f = unfocusedBorderThickness;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(this) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            unfocusedBorderThickness3 = f;
            focusedBorderThickness4 = focusedBorderThickness2;
            shape3 = shape2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if ((i & 16) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer3, ($dirty >> 21) & 14);
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    focusedBorderThickness2 = FocusedBorderThickness;
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                } else {
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                } else {
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(943754022, $dirty, -1, "androidx.compose.material.TextFieldDefaults.BorderBox (TextFieldDefaults.kt:335)");
            }
            borderStroke = TextFieldDefaultsKt.m1710animateBorderStrokeAsStateNuRrP5Q(z, z2, interactionSource2, textFieldColors, focusedBorderThickness3, unfocusedBorderThickness2, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (458752 & ($dirty >> 3)));
            $composer2 = $composer3;
            BoxKt.Box(BorderKt.border(Modifier.INSTANCE, (BorderStroke) borderStroke.getValue(), shape2), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            focusedBorderThickness4 = focusedBorderThickness3;
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
            shape3 = shape2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$BorderBox$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    TextFieldDefaults.this.m1698BorderBoxnbWgWpA(enabled, isError, interactionSource, colors, shape3, focusedBorderThickness4, unfocusedBorderThickness3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1696textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getFirstBaselineOffset();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldBottomPadding();
        }
        return textFieldDefaults.m1707textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1707textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m668PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1697textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m1708textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1708textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m668PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1695outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m1705outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1705outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m668PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    /* renamed from: textFieldColors-dx8h9Zs, reason: not valid java name */
    public final androidx.compose.material.TextFieldColors m1706textFieldColorsdx8h9Zs(long r53, long r55, long r57, long r59, long r61, long r63, long r65, long r67, long r69, long r71, long r73, long r75, long r77, long r79, long r81, long r83, long r85, long r87, long r89, long r91, long r93, androidx.compose.runtime.Composer r95, int r96, int r97, int r98, int r99) {
        /*
            Method dump skipped, instructions count: 902
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldDefaults.m1706textFieldColorsdx8h9Zs(long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, androidx.compose.runtime.Composer, int, int, int, int):androidx.compose.material.TextFieldColors");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    /* renamed from: outlinedTextFieldColors-dx8h9Zs, reason: not valid java name */
    public final androidx.compose.material.TextFieldColors m1704outlinedTextFieldColorsdx8h9Zs(long r53, long r55, long r57, long r59, long r61, long r63, long r65, long r67, long r69, long r71, long r73, long r75, long r77, long r79, long r81, long r83, long r85, long r87, long r89, long r91, long r93, androidx.compose.runtime.Composer r95, int r96, int r97, int r98, int r99) {
        /*
            Method dump skipped, instructions count: 888
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldDefaults.m1704outlinedTextFieldColorsdx8h9Zs(long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, long, androidx.compose.runtime.Composer, int, int, int, int):androidx.compose.material.TextFieldColors");
    }

    public final void TextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Composer $composer, final int $changed, final int $changed1, int i) {
        boolean z;
        Function2 function26;
        int i2;
        int i3;
        Shape shape2;
        boolean isError2;
        Composer $composer2;
        int $dirty;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        boolean isError3;
        TextFieldColors colors3;
        int $dirty1;
        final Function2 label;
        final Function2 placeholder;
        final Function2 leadingIcon;
        final Function2 trailingIcon;
        Shape shape3;
        int $dirty2;
        final boolean isError4;
        final PaddingValues contentPadding3;
        final Shape shape4;
        final TextFieldColors colors4;
        int i4;
        int i5 = i;
        Composer $composer3 = $composer.startRestartGroup(-1391531252);
        ComposerKt.sourceInformation($composer3, "C(TextFieldDecorationBox)P(12,3,2,10,13,4,5,6,8,7,11,9)573@25758L14,574@25808L17,582@26042L624:TextFieldDefaults.kt#jmzs0o");
        int $dirty3 = $changed;
        int $dirty12 = $changed1;
        if ((i5 & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i5 & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        int i6 = 128;
        if ((i5 & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 256 : 128;
        }
        int i7 = 1024;
        if ((i5 & 8) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        if ((i5 & 16) != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty3 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i5 & 32) != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        int i8 = i5 & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
            z = isError;
        } else if (($changed & 1572864) == 0) {
            z = isError;
            $dirty3 |= $composer3.changed(z) ? 1048576 : 524288;
        } else {
            z = isError;
        }
        int i9 = i5 & 128;
        if (i9 != 0) {
            $dirty3 |= 12582912;
            function26 = function22;
        } else if (($changed & 12582912) == 0) {
            function26 = function22;
            $dirty3 |= $composer3.changedInstance(function26) ? 8388608 : 4194304;
        } else {
            function26 = function22;
        }
        int i10 = i5 & 256;
        if (i10 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i11 = i5 & 512;
        if (i11 != 0) {
            $dirty3 |= 805306368;
            i2 = i11;
        } else if (($changed & 805306368) == 0) {
            i2 = i11;
            $dirty3 |= $composer3.changedInstance(function24) ? 536870912 : 268435456;
        } else {
            i2 = i11;
        }
        int i12 = i5 & 1024;
        if (i12 != 0) {
            $dirty12 |= 6;
            i3 = i12;
        } else if (($changed1 & 6) == 0) {
            i3 = i12;
            $dirty12 |= $composer3.changedInstance(function25) ? 4 : 2;
        } else {
            i3 = i12;
        }
        if (($changed1 & 48) == 0) {
            if ((i5 & 2048) == 0 && $composer3.changed(shape)) {
                i4 = 32;
                $dirty12 |= i4;
            }
            i4 = 16;
            $dirty12 |= i4;
        }
        if (($changed1 & 384) == 0) {
            if ((i5 & 4096) == 0 && $composer3.changed(colors)) {
                i6 = 256;
            }
            $dirty12 |= i6;
        }
        if (($changed1 & 3072) == 0) {
            if ((i5 & 8192) == 0 && $composer3.changed(contentPadding)) {
                i7 = 2048;
            }
            $dirty12 |= i7;
        }
        if ((i5 & 16384) != 0) {
            $dirty12 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty12 |= $composer3.changed(this) ? 16384 : 8192;
        }
        if ((306783379 & $dirty3) == 306783378 && ($dirty12 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            leadingIcon = function24;
            trailingIcon = function25;
            colors4 = colors;
            contentPadding3 = contentPadding;
            $composer2 = $composer3;
            $dirty2 = $dirty3;
            isError4 = z;
            label = function26;
            placeholder = function23;
            shape4 = shape;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                boolean isError5 = i8 != 0 ? false : z;
                Function2 label2 = i9 != 0 ? null : function26;
                Function2 placeholder2 = i10 != 0 ? null : function23;
                Function2 leadingIcon2 = i2 != 0 ? null : function24;
                Function2 trailingIcon2 = i3 != 0 ? null : function25;
                if ((i5 & 2048) != 0) {
                    Shape shape5 = getTextFieldShape($composer3, ($dirty12 >> 12) & 14);
                    $dirty12 &= -113;
                    shape2 = shape5;
                } else {
                    shape2 = shape;
                }
                if ((i5 & 4096) != 0) {
                    isError2 = isError5;
                    $dirty = $dirty3;
                    colors2 = m1706textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty12 >> 9) & 112, 2097151);
                    $composer2 = $composer3;
                    $dirty12 &= -897;
                } else {
                    isError2 = isError5;
                    $composer2 = $composer3;
                    $dirty = $dirty3;
                    colors2 = colors;
                }
                i5 = i;
                if ((i5 & 8192) != 0) {
                    isError3 = isError2;
                    contentPadding2 = label2 == null ? m1697textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : m1696textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty1 = $dirty12 & (-7169);
                    colors3 = colors2;
                    label = label2;
                    placeholder = placeholder2;
                    leadingIcon = leadingIcon2;
                    trailingIcon = trailingIcon2;
                    shape3 = shape2;
                } else {
                    contentPadding2 = contentPadding;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty12;
                    label = label2;
                    placeholder = placeholder2;
                    leadingIcon = leadingIcon2;
                    trailingIcon = trailingIcon2;
                    shape3 = shape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i5 & 2048) != 0) {
                    $dirty12 &= -113;
                }
                if ((i5 & 4096) != 0) {
                    $dirty12 &= -897;
                }
                if ((i5 & 8192) != 0) {
                    $dirty1 = $dirty12 & (-7169);
                    leadingIcon = function24;
                    trailingIcon = function25;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    $composer2 = $composer3;
                    $dirty = $dirty3;
                    isError3 = z;
                    label = function26;
                    placeholder = function23;
                } else {
                    leadingIcon = function24;
                    trailingIcon = function25;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    $composer2 = $composer3;
                    $dirty = $dirty3;
                    $dirty1 = $dirty12;
                    isError3 = z;
                    label = function26;
                    placeholder = function23;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty2 = $dirty;
                ComposerKt.traceEventStart(-1391531252, $dirty2, $dirty1, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:581)");
            } else {
                $dirty2 = $dirty;
            }
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, function2, visualTransformation, label, placeholder, leadingIcon, trailingIcon, singleLine, enabled, isError3, interactionSource, contentPadding2, shape3, colors3, null, $composer2, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896) | (($dirty2 >> 3) & 7168) | (($dirty2 >> 9) & 57344) | (($dirty2 >> 9) & 458752) | (($dirty2 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty2 << 15) & 234881024) | (($dirty2 << 21) & 1879048192), (($dirty2 >> 18) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty2 >> 12) & 112) | (($dirty1 >> 3) & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isError4 = isError3;
            contentPadding3 = contentPadding2;
            shape4 = shape3;
            colors4 = colors3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final int i13 = i5;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$TextFieldDecorationBox$1
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

                public final void invoke(Composer composer, int i14) {
                    TextFieldDefaults.this.TextFieldDecorationBox(value, function2, enabled, singleLine, visualTransformation, interactionSource, isError4, label, placeholder, leadingIcon, trailingIcon, shape4, colors4, contentPadding3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i13);
                }
            });
        }
    }

    public final void OutlinedTextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function26, Composer $composer, final int $changed, final int $changed1, int i) {
        boolean z;
        Function2 function27;
        int i2;
        int i3;
        Shape shape2;
        boolean isError2;
        int i4;
        Composer $composer2;
        int $dirty;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        Function2 border;
        PaddingValues contentPadding3;
        TextFieldColors colors3;
        Shape shape3;
        int $dirty1;
        boolean isError3;
        final Function2 label;
        final Function2 placeholder;
        final Function2 leadingIcon;
        final Function2 trailingIcon;
        int $dirty2;
        Composer $composer3;
        final boolean isError4;
        final PaddingValues contentPadding4;
        final Shape shape4;
        final TextFieldColors colors4;
        final Function2 border2;
        int i5;
        int i6 = i;
        Composer $composer4 = $composer.startRestartGroup(-920823490);
        ComposerKt.sourceInformation($composer4, "C(OutlinedTextFieldDecorationBox)P(13,4,3,11,14,5,6,7,9,8,12,10,1,2)669@31256L22,670@31314L25,672@31450L85,676@31552L628:TextFieldDefaults.kt#jmzs0o");
        int $dirty3 = $changed;
        int $dirty12 = $changed1;
        if ((i6 & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer4.changed(value) ? 4 : 2;
        }
        if ((i6 & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer4.changedInstance(function2) ? 32 : 16;
        }
        int i7 = 128;
        if ((i6 & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer4.changed(enabled) ? 256 : 128;
        }
        int i8 = 1024;
        if ((i6 & 8) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer4.changed(singleLine) ? 2048 : 1024;
        }
        if ((i6 & 16) != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty3 |= $composer4.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i6 & 32) != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer4.changed(interactionSource) ? 131072 : 65536;
        }
        int i9 = i6 & 64;
        if (i9 != 0) {
            $dirty3 |= 1572864;
            z = isError;
        } else if (($changed & 1572864) == 0) {
            z = isError;
            $dirty3 |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = isError;
        }
        int i10 = i6 & 128;
        if (i10 != 0) {
            $dirty3 |= 12582912;
            function27 = function22;
        } else if (($changed & 12582912) == 0) {
            function27 = function22;
            $dirty3 |= $composer4.changedInstance(function27) ? 8388608 : 4194304;
        } else {
            function27 = function22;
        }
        int i11 = i6 & 256;
        if (i11 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer4.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i12 = i6 & 512;
        if (i12 != 0) {
            $dirty3 |= 805306368;
            i2 = i12;
        } else if (($changed & 805306368) == 0) {
            i2 = i12;
            $dirty3 |= $composer4.changedInstance(function24) ? 536870912 : 268435456;
        } else {
            i2 = i12;
        }
        int i13 = i6 & 1024;
        if (i13 != 0) {
            $dirty12 |= 6;
            i3 = i13;
        } else if (($changed1 & 6) == 0) {
            i3 = i13;
            $dirty12 |= $composer4.changedInstance(function25) ? 4 : 2;
        } else {
            i3 = i13;
        }
        if (($changed1 & 48) == 0) {
            if ((i6 & 2048) == 0 && $composer4.changed(shape)) {
                i5 = 32;
                $dirty12 |= i5;
            }
            i5 = 16;
            $dirty12 |= i5;
        }
        if (($changed1 & 384) == 0) {
            if ((i6 & 4096) == 0 && $composer4.changed(colors)) {
                i7 = 256;
            }
            $dirty12 |= i7;
        }
        if (($changed1 & 3072) == 0) {
            if ((i6 & 8192) == 0 && $composer4.changed(contentPadding)) {
                i8 = 2048;
            }
            $dirty12 |= i8;
        }
        int i14 = i6 & 16384;
        if (i14 != 0) {
            $dirty12 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty12 |= $composer4.changedInstance(function26) ? 16384 : 8192;
        }
        if ((i6 & 32768) != 0) {
            $dirty12 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty12 |= $composer4.changed(this) ? 131072 : 65536;
        }
        if (($dirty3 & 306783379) == 306783378 && (74899 & $dirty12) == 74898 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            leadingIcon = function24;
            trailingIcon = function25;
            colors4 = colors;
            contentPadding4 = contentPadding;
            border2 = function26;
            $composer3 = $composer4;
            $dirty2 = $dirty3;
            isError4 = z;
            label = function27;
            placeholder = function23;
            shape4 = shape;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                boolean isError5 = i9 != 0 ? false : z;
                Function2 label2 = i10 != 0 ? null : function27;
                Function2 placeholder2 = i11 != 0 ? null : function23;
                Function2 leadingIcon2 = i2 != 0 ? null : function24;
                Function2 trailingIcon2 = i3 != 0 ? null : function25;
                if ((i6 & 2048) != 0) {
                    Shape shape5 = getOutlinedTextFieldShape($composer4, ($dirty12 >> 15) & 14);
                    $dirty12 &= -113;
                    shape2 = shape5;
                } else {
                    shape2 = shape;
                }
                if ((i6 & 4096) != 0) {
                    i4 = i14;
                    isError2 = isError5;
                    $dirty = $dirty3;
                    colors2 = m1704outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, ($dirty12 >> 12) & 112, 2097151);
                    $composer2 = $composer4;
                    $dirty12 &= -897;
                } else {
                    isError2 = isError5;
                    i4 = i14;
                    $composer2 = $composer4;
                    $dirty = $dirty3;
                    colors2 = colors;
                }
                i6 = i;
                if ((i6 & 8192) != 0) {
                    contentPadding2 = m1695outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty12 &= -7169;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i4 != 0) {
                    final boolean isError6 = isError2;
                    final TextFieldColors colors5 = colors2;
                    final Shape shape6 = shape2;
                    contentPadding3 = contentPadding2;
                    border = ComposableLambdaKt.rememberComposableLambda(2147252344, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$OutlinedTextFieldDecorationBox$1
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
                            ComposerKt.sourceInformation($composer5, "C673@31464L61:TextFieldDefaults.kt#jmzs0o");
                            if (($changed2 & 3) == 2 && $composer5.getSkipping()) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2147252344, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:673)");
                            }
                            TextFieldDefaults.INSTANCE.m1698BorderBoxnbWgWpA(enabled, isError6, interactionSource, colors5, shape6, 0.0f, 0.0f, $composer5, 12582912, 96);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54);
                    colors3 = colors5;
                    shape3 = shape6;
                    $dirty1 = $dirty12;
                    isError3 = isError6;
                    label = label2;
                    placeholder = placeholder2;
                    leadingIcon = leadingIcon2;
                    trailingIcon = trailingIcon2;
                } else {
                    border = function26;
                    contentPadding3 = contentPadding2;
                    colors3 = colors2;
                    shape3 = shape2;
                    $dirty1 = $dirty12;
                    isError3 = isError2;
                    label = label2;
                    placeholder = placeholder2;
                    leadingIcon = leadingIcon2;
                    trailingIcon = trailingIcon2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i6 & 2048) != 0) {
                    $dirty12 &= -113;
                }
                if ((i6 & 4096) != 0) {
                    $dirty12 &= -897;
                }
                if ((i6 & 8192) != 0) {
                    $dirty1 = $dirty12 & (-7169);
                    leadingIcon = function24;
                    trailingIcon = function25;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    border = function26;
                    $composer2 = $composer4;
                    $dirty = $dirty3;
                    isError3 = z;
                    label = function27;
                    placeholder = function23;
                } else {
                    leadingIcon = function24;
                    trailingIcon = function25;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    border = function26;
                    $composer2 = $composer4;
                    $dirty = $dirty3;
                    $dirty1 = $dirty12;
                    isError3 = z;
                    label = function27;
                    placeholder = function23;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty2 = $dirty;
                ComposerKt.traceEventStart(-920823490, $dirty2, $dirty1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:675)");
            } else {
                $dirty2 = $dirty;
            }
            Composer $composer5 = $composer2;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, function2, visualTransformation, label, placeholder, leadingIcon, trailingIcon, singleLine, enabled, isError3, interactionSource, contentPadding3, shape3, colors3, border, $composer5, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896) | (($dirty2 >> 3) & 7168) | (($dirty2 >> 9) & 57344) | (($dirty2 >> 9) & 458752) | (3670016 & ($dirty2 >> 9)) | (($dirty1 << 21) & 29360128) | (($dirty2 << 15) & 234881024) | (($dirty2 << 21) & 1879048192), (($dirty2 >> 18) & 14) | (($dirty2 >> 12) & 112) | (($dirty1 >> 3) & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (458752 & ($dirty1 << 3)));
            $composer3 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isError4 = isError3;
            contentPadding4 = contentPadding3;
            shape4 = shape3;
            colors4 = colors3;
            border2 = border;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            final int i15 = i6;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$OutlinedTextFieldDecorationBox$2
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

                public final void invoke(Composer composer, int i16) {
                    TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, function2, enabled, singleLine, visualTransformation, interactionSource, isError4, label, placeholder, leadingIcon, trailingIcon, shape4, colors4, contentPadding4, border2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i15);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with `shape` parameter.")
    public final /* synthetic */ void TextFieldDecorationBox(final String value, final Function2 innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, TextFieldColors colors, PaddingValues contentPadding, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function2 function2;
        int i2;
        int i3;
        int $dirty;
        int $dirty2;
        int $dirty1;
        TextFieldColors colors2;
        TextFieldDefaults textFieldDefaults;
        final PaddingValues contentPadding2;
        final TextFieldColors colors3;
        boolean isError2;
        Function2 label2;
        Function2 placeholder2;
        Function2 leadingIcon2;
        Function2 trailingIcon2;
        PaddingValues contentPadding3;
        int $dirty3;
        Composer $composer2;
        final Function2 trailingIcon3;
        final Function2 trailingIcon4;
        final Function2 leadingIcon3;
        final Function2 placeholder3;
        final boolean isError3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(1171040065);
        ComposerKt.sourceInformation($composer3, "C(TextFieldDecorationBox)P(11,3,2,9,12,4,5,6,8,7,10)714@32930L17,733@33577L14,721@33156L508:TextFieldDefaults.kt#jmzs0o");
        int $dirty4 = $changed;
        int $dirty12 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer3.changedInstance(innerTextField) ? 32 : 16;
        }
        int i5 = 128;
        if ((i & 4) != 0) {
            $dirty4 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty4 |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty4 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty4 |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty4 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty4 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty4 |= 1572864;
            z = isError;
        } else if (($changed & 1572864) == 0) {
            z = isError;
            $dirty4 |= $composer3.changed(z) ? 1048576 : 524288;
        } else {
            z = isError;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty4 |= 12582912;
            function2 = label;
        } else if (($changed & 12582912) == 0) {
            function2 = label;
            $dirty4 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        } else {
            function2 = label;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty4 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty4 |= $composer3.changedInstance(placeholder) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty4 |= 805306368;
            i2 = i9;
        } else if (($changed & 805306368) == 0) {
            i2 = i9;
            $dirty4 |= $composer3.changedInstance(leadingIcon) ? 536870912 : 268435456;
        } else {
            i2 = i9;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty12 |= 6;
            i3 = i10;
        } else if (($changed1 & 6) == 0) {
            i3 = i10;
            $dirty12 |= $composer3.changedInstance(trailingIcon) ? 4 : 2;
        } else {
            i3 = i10;
        }
        if (($changed1 & 48) == 0) {
            if ((i & 2048) == 0 && $composer3.changed(colors)) {
                i4 = 32;
                $dirty12 |= i4;
            }
            i4 = 16;
            $dirty12 |= i4;
        }
        if (($changed1 & 384) == 0) {
            if ((i & 4096) == 0 && $composer3.changed(contentPadding)) {
                i5 = 256;
            }
            $dirty12 |= i5;
        }
        if ((i & 8192) != 0) {
            $dirty12 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty12 |= $composer3.changed(this) ? 2048 : 1024;
        }
        int $dirty13 = $dirty12;
        if ((306783379 & $dirty4) == 306783378 && ($dirty13 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            leadingIcon3 = placeholder;
            trailingIcon4 = leadingIcon;
            colors3 = colors;
            contentPadding2 = contentPadding;
            isError3 = z;
            placeholder3 = function2;
            $composer2 = $composer3;
            trailingIcon3 = trailingIcon;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                boolean isError4 = i6 != 0 ? false : z;
                Function2 label3 = i7 != 0 ? null : function2;
                Function2 placeholder4 = i8 != 0 ? null : placeholder;
                Function2 leadingIcon4 = i2 != 0 ? null : leadingIcon;
                Function2 trailingIcon5 = i3 != 0 ? null : trailingIcon;
                if ((i & 2048) != 0) {
                    $dirty2 = $dirty4;
                    $dirty = i;
                    TextFieldColors colors4 = m1706textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty13 >> 6) & 112, 2097151);
                    $composer3 = $composer3;
                    $dirty1 = $dirty13 & (-113);
                    colors2 = colors4;
                } else {
                    $dirty = i;
                    $dirty2 = $dirty4;
                    $dirty1 = $dirty13;
                    colors2 = colors;
                }
                if (($dirty & 4096) != 0) {
                    if (label3 == null) {
                        textFieldDefaults = this;
                        contentPadding3 = m1697textFieldWithoutLabelPaddinga9UjIt4$default(textFieldDefaults, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        textFieldDefaults = this;
                        contentPadding3 = m1696textFieldWithLabelPaddinga9UjIt4$default(textFieldDefaults, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    contentPadding2 = contentPadding3;
                    $dirty13 = $dirty1 & (-897);
                    colors3 = colors2;
                    isError2 = isError4;
                    label2 = label3;
                    placeholder2 = placeholder4;
                    leadingIcon2 = leadingIcon4;
                    trailingIcon2 = trailingIcon5;
                } else {
                    textFieldDefaults = this;
                    contentPadding2 = contentPadding;
                    colors3 = colors2;
                    $dirty13 = $dirty1;
                    isError2 = isError4;
                    label2 = label3;
                    placeholder2 = placeholder4;
                    leadingIcon2 = leadingIcon4;
                    trailingIcon2 = trailingIcon5;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty13 &= -113;
                }
                if ((i & 4096) != 0) {
                    leadingIcon2 = leadingIcon;
                    trailingIcon2 = trailingIcon;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    $dirty13 &= -897;
                    $dirty2 = $dirty4;
                    isError2 = z;
                    label2 = function2;
                    textFieldDefaults = this;
                    placeholder2 = placeholder;
                } else {
                    textFieldDefaults = this;
                    leadingIcon2 = leadingIcon;
                    trailingIcon2 = trailingIcon;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    $dirty2 = $dirty4;
                    isError2 = z;
                    label2 = function2;
                    placeholder2 = placeholder;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty3 = $dirty2;
                ComposerKt.traceEventStart(1171040065, $dirty3, $dirty13, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:721)");
            } else {
                $dirty3 = $dirty2;
            }
            textFieldDefaults.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError2, label2, placeholder2, leadingIcon2, trailingIcon2, textFieldDefaults.getTextFieldShape($composer3, ($dirty13 >> 9) & 14), colors3, contentPadding2, $composer3, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | (3670016 & $dirty3) | (29360128 & $dirty3) | (234881024 & $dirty3) | (1879048192 & $dirty3), ($dirty13 & 14) | (($dirty13 << 3) & 896) | (($dirty13 << 3) & 7168) | (57344 & ($dirty13 << 3)), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            trailingIcon3 = trailingIcon2;
            trailingIcon4 = leadingIcon2;
            leadingIcon3 = placeholder2;
            placeholder3 = label2;
            isError3 = isError2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$TextFieldDecorationBox$2
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

                public final void invoke(Composer composer, int i11) {
                    TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, placeholder3, leadingIcon3, trailingIcon4, trailingIcon3, colors3, contentPadding2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x01b3, code lost:
    
        if (r7.changed(r71) != false) goto L141;
     */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with `shape` parameter.")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ void OutlinedTextFieldDecorationBox(final java.lang.String r59, final kotlin.jvm.functions.Function2 r60, final boolean r61, final boolean r62, final androidx.compose.ui.text.input.VisualTransformation r63, final androidx.compose.foundation.interaction.InteractionSource r64, boolean r65, kotlin.jvm.functions.Function2 r66, kotlin.jvm.functions.Function2 r67, kotlin.jvm.functions.Function2 r68, kotlin.jvm.functions.Function2 r69, androidx.compose.material.TextFieldColors r70, androidx.compose.foundation.layout.PaddingValues r71, kotlin.jvm.functions.Function2 r72, androidx.compose.runtime.Composer r73, final int r74, final int r75, final int r76) {
        /*
            Method dump skipped, instructions count: 1092
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox(java.lang.String, kotlin.jvm.functions.Function2, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.material.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
