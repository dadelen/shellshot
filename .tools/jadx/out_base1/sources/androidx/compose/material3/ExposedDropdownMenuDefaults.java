package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowDropDownKt;
import androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt;
import androidx.compose.material3.tokens.FilledAutocompleteTokens;
import androidx.compose.material3.tokens.OutlinedAutocompleteTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.PopupProperties;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ExposedDropdownMenu.android.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u000bJ\u001f\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000eJ\u0082\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010#\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u00122\b\b\u0002\u0010%\u001a\u00020\u00122\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u00122\b\b\u0002\u0010(\u001a\u00020\u00122\b\b\u0002\u0010)\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u0084\u0003\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010-\u001a\u00020\u00122\b\b\u0002\u0010.\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010/\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u00100\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010#\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u00122\b\b\u0002\u0010%\u001a\u00020\u00122\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u00122\b\b\u0002\u0010(\u001a\u00020\u00122\b\b\u0002\u00101\u001a\u00020\u00122\b\b\u0002\u00102\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\u00122\b\b\u0002\u00103\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u00020\u00122\b\b\u0002\u00105\u001a\u00020\u00122\b\b\u0002\u00106\u001a\u00020\u00122\b\b\u0002\u00107\u001a\u00020\u00122\b\b\u0002\u00108\u001a\u00020\u00122\b\b\u0002\u00109\u001a\u00020\u00122\b\b\u0002\u0010:\u001a\u00020\u00122\b\b\u0002\u0010;\u001a\u00020\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=J\u0098\u0003\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010-\u001a\u00020\u00122\b\b\u0002\u0010.\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010/\u001a\u00020\u00122\b\b\u0002\u0010>\u001a\u00020\u00122\b\b\u0002\u0010?\u001a\u00020\u00122\b\b\u0002\u0010@\u001a\u00020\u00122\b\b\u0002\u00100\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010#\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u00122\b\b\u0002\u0010%\u001a\u00020\u00122\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u00122\b\b\u0002\u0010(\u001a\u00020\u00122\b\b\u0002\u00101\u001a\u00020\u00122\b\b\u0002\u00102\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\u00122\b\b\u0002\u00103\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u00020\u00122\b\b\u0002\u00105\u001a\u00020\u00122\b\b\u0002\u00106\u001a\u00020\u00122\b\b\u0002\u00107\u001a\u00020\u00122\b\b\u0002\u00108\u001a\u00020\u00122\b\b\u0002\u00109\u001a\u00020\u00122\b\b\u0002\u0010:\u001a\u00020\u00122\b\b\u0002\u0010;\u001a\u00020\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\bA\u0010BJ\u001a\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0001ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ\u0082\u0002\u0010I\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010J\u001a\u00020\u00122\b\b\u0002\u0010K\u001a\u00020\u00122\b\b\u0002\u0010L\u001a\u00020\u00122\b\b\u0002\u0010M\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010#\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u00122\b\b\u0002\u0010%\u001a\u00020\u00122\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u00122\b\b\u0002\u0010(\u001a\u00020\u00122\b\b\u0002\u0010)\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\bN\u0010,J\u0084\u0003\u0010I\u001a\u00020\u00102\b\b\u0002\u0010-\u001a\u00020\u00122\b\b\u0002\u0010.\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010/\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u00100\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010J\u001a\u00020\u00122\b\b\u0002\u0010K\u001a\u00020\u00122\b\b\u0002\u0010L\u001a\u00020\u00122\b\b\u0002\u0010M\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010#\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u00122\b\b\u0002\u0010%\u001a\u00020\u00122\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u00122\b\b\u0002\u0010(\u001a\u00020\u00122\b\b\u0002\u00101\u001a\u00020\u00122\b\b\u0002\u00102\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\u00122\b\b\u0002\u00103\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u00020\u00122\b\b\u0002\u00105\u001a\u00020\u00122\b\b\u0002\u00106\u001a\u00020\u00122\b\b\u0002\u00107\u001a\u00020\u00122\b\b\u0002\u00108\u001a\u00020\u00122\b\b\u0002\u00109\u001a\u00020\u00122\b\b\u0002\u0010:\u001a\u00020\u00122\b\b\u0002\u0010;\u001a\u00020\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\bO\u0010=J\u0098\u0003\u0010I\u001a\u00020\u00102\b\b\u0002\u0010-\u001a\u00020\u00122\b\b\u0002\u0010.\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010/\u001a\u00020\u00122\b\b\u0002\u0010>\u001a\u00020\u00122\b\b\u0002\u0010?\u001a\u00020\u00122\b\b\u0002\u0010@\u001a\u00020\u00122\b\b\u0002\u00100\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010J\u001a\u00020\u00122\b\b\u0002\u0010K\u001a\u00020\u00122\b\b\u0002\u0010L\u001a\u00020\u00122\b\b\u0002\u0010M\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010#\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u00122\b\b\u0002\u0010%\u001a\u00020\u00122\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u00122\b\b\u0002\u0010(\u001a\u00020\u00122\b\b\u0002\u00101\u001a\u00020\u00122\b\b\u0002\u00102\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\u00122\b\b\u0002\u00103\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u00020\u00122\b\b\u0002\u00105\u001a\u00020\u00122\b\b\u0002\u00106\u001a\u00020\u00122\b\b\u0002\u00107\u001a\u00020\u00122\b\b\u0002\u00108\u001a\u00020\u00122\b\b\u0002\u00109\u001a\u00020\u00122\b\b\u0002\u0010:\u001a\u00020\u00122\b\b\u0002\u0010;\u001a\u00020\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\bP\u0010BR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Q²\u0006\n\u0010R\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuDefaults;", "", "()V", "ItemContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getItemContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "TrailingIcon", "", "expanded", "", "(ZLandroidx/compose/runtime/Composer;I)V", "modifier", "Landroidx/compose/ui/Modifier;", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "outlinedTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", "containerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "outlinedTextFieldColors-St-qZLY", "(JJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "unfocusedTextColor", "errorTextColor", "errorContainerColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "errorPlaceholderColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "outlinedTextFieldColors-tN0la-I", "(JJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIII)Landroidx/compose/material3/TextFieldColors;", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "outlinedTextFieldColors-FD9MK7s", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIII)Landroidx/compose/material3/TextFieldColors;", "popupProperties", "Landroidx/compose/ui/window/PopupProperties;", "anchorType", "Landroidx/compose/material3/MenuAnchorType;", "popupProperties-pR6Bxps$material3_release", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/window/PopupProperties;", "textFieldColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "textFieldColors-St-qZLY", "textFieldColors-tN0la-I", "textFieldColors-FD9MK7s", "material3_release", "a11yServicesEnabled"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ExposedDropdownMenuDefaults {
    public static final int $stable = 0;
    public static final ExposedDropdownMenuDefaults INSTANCE = new ExposedDropdownMenuDefaults();
    private static final PaddingValues ItemContentPadding;

    private ExposedDropdownMenuDefaults() {
    }

    public final void TrailingIcon(final boolean expanded, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Composer $composer2 = $composer.startRestartGroup(-1987096744);
        ComposerKt.sourceInformation($composer2, "C(TrailingIcon)520@22966L83:ExposedDropdownMenu.android.kt#uh7d8r");
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
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
        } else {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1987096744, $dirty2, -1, "androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon (ExposedDropdownMenu.android.kt:519)");
            }
            IconKt.m2154Iconww6aTOc(ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE), (String) null, RotateKt.rotate(modifier3, expanded ? 180.0f : 0.0f), 0L, $composer2, 48, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuDefaults$TrailingIcon$1
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
                    ExposedDropdownMenuDefaults.this.TrailingIcon(expanded, modifier2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: textFieldColors-FD9MK7s, reason: not valid java name */
    public final TextFieldColors m2092textFieldColorsFD9MK7s(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int i, int i2) {
        SelectionColors selectionColors2;
        int i3;
        long focusedIndicatorColor2;
        int i4;
        long errorIndicatorColor2;
        int i5;
        long errorLeadingIconColor2;
        int i6;
        long errorTrailingIconColor2;
        int i7;
        long errorPlaceholderColor2;
        long disabledPrefixColor2;
        int i8;
        long errorPrefixColor2;
        long disabledSuffixColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ComposerKt.sourceInformationMarkerStart($composer, 768358577, "C(textFieldColors)P(27:c#ui.graphics.Color,37:c#ui.graphics.Color,8:c#ui.graphics.Color,18:c#ui.graphics.Color,20:c#ui.graphics.Color,30:c#ui.graphics.Color,1:c#ui.graphics.Color,10:c#ui.graphics.Color,0:c#ui.graphics.Color,11:c#ui.graphics.Color,29,21:c#ui.graphics.Color,31:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.graphics.Color,23:c#ui.graphics.Color,33:c#ui.graphics.Color,4:c#ui.graphics.Color,14:c#ui.graphics.Color,28:c#ui.graphics.Color,38:c#ui.graphics.Color,9:c#ui.graphics.Color,19:c#ui.graphics.Color,22:c#ui.graphics.Color,32:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.graphics.Color,24:c#ui.graphics.Color,34:c#ui.graphics.Color,5:c#ui.graphics.Color,15:c#ui.graphics.Color,25:c#ui.graphics.Color,35:c#ui.graphics.Color,6:c#ui.graphics.Color,16:c#ui.graphics.Color,26:c#ui.graphics.Color,36:c#ui.graphics.Color,7:c#ui.graphics.Color,17:c#ui.graphics.Color)573@27038L5,574@27126L5,576@27233L5,579@27421L5,580@27516L5,581@27613L5,582@27709L5,583@27802L5,584@27883L5,585@27979L5,586@28058L7,588@28178L5,590@28293L5,592@28415L5,596@28640L5,598@28756L5,599@28857L5,601@28977L5,605@29196L5,607@29314L5,609@29429L5,611@29551L5,615@29773L5,616@29865L5,617@29954L5,618@30050L5,619@30140L5,620@30238L5,621@30338L5,623@30457L5,626@30657L5,627@30750L5,628@30845L5,630@30959L5,633@31154L5,634@31247L5,635@31342L5,637@31456L5,640@31651L5,642@31709L2230:ExposedDropdownMenu.android.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldInputTextColor(), $composer, 6) : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f) : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), $composer, 6) : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), $composer, 6) : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), $composer, 6) : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), $composer, 6) : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), $composer, 6) : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 1024) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        } else {
            selectionColors2 = selectionColors;
        }
        if ((i & 2048) != 0) {
            i3 = 6;
            focusedIndicatorColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusActiveIndicatorColor(), $composer, 6);
        } else {
            i3 = 6;
            focusedIndicatorColor2 = focusedIndicatorColor;
        }
        long unfocusedIndicatorColor2 = (i & 4096) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldActiveIndicatorColor(), $composer, i3) : unfocusedIndicatorColor;
        long disabledIndicatorColor2 = (i & 8192) != 0 ? Color.m4161copywmQWz5c(r35, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r35) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r35) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r35) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorColor(), $composer, i3)) : 0.0f) : disabledIndicatorColor;
        SelectionColors selectionColors3 = selectionColors2;
        if ((i & 16384) != 0) {
            i4 = 6;
            errorIndicatorColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorActiveIndicatorColor(), $composer, 6);
        } else {
            i4 = 6;
            errorIndicatorColor2 = errorIndicatorColor;
        }
        long focusedLeadingIconColor2 = (i & 32768) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), $composer, i4) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 65536) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), $composer, i4) : unfocusedLeadingIconColor;
        long disabledLeadingIconColor2 = (i & 131072) != 0 ? Color.m4161copywmQWz5c(r41, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r41) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r41) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r41) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), $composer, i4)) : 0.0f) : disabledLeadingIconColor;
        if ((i & 262144) != 0) {
            i5 = 6;
            errorLeadingIconColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), $composer, 6);
        } else {
            i5 = 6;
            errorLeadingIconColor2 = errorLeadingIconColor;
        }
        long focusedTrailingIconColor2 = (i & 524288) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), $composer, i5) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (i & 1048576) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), $composer, i5) : unfocusedTrailingIconColor;
        long disabledTrailingIconColor2 = (i & 2097152) != 0 ? Color.m4161copywmQWz5c(r49, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r49) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r49) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r49) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), $composer, i5)) : 0.0f) : disabledTrailingIconColor;
        if ((i & 4194304) != 0) {
            i6 = 6;
            errorTrailingIconColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), $composer, 6);
        } else {
            i6 = 6;
            errorTrailingIconColor2 = errorTrailingIconColor;
        }
        long focusedLabelColor2 = (i & 8388608) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), $composer, i6) : focusedLabelColor;
        long unfocusedLabelColor2 = (i & 16777216) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), $composer, i6) : unfocusedLabelColor;
        long disabledLabelColor2 = (i & 33554432) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), $composer, i6) : disabledLabelColor;
        long errorLabelColor2 = (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), $composer, i6) : errorLabelColor;
        long focusedPlaceholderColor2 = (i & 134217728) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i6) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (i & 268435456) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i6) : unfocusedPlaceholderColor;
        long disabledPlaceholderColor2 = (i & 536870912) != 0 ? Color.m4161copywmQWz5c(r65, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r65) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r65) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r65) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i6)) : 0.0f) : disabledPlaceholderColor;
        if ((i & BasicMeasure.EXACTLY) != 0) {
            i7 = 6;
            errorPlaceholderColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i7 = 6;
            errorPlaceholderColor2 = errorPlaceholderColor;
        }
        long focusedPrefixColor2 = (i2 & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 2) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : unfocusedPrefixColor;
        if ((i2 & 4) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r69, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r69) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r69) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r69) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i7)) : 0.0f);
            disabledPrefixColor2 = m4161copywmQWz5c2;
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        if ((i2 & 8) != 0) {
            i8 = 6;
            errorPrefixColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i8 = 6;
            errorPrefixColor2 = errorPrefixColor;
        }
        long focusedSuffixColor2 = (i2 & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : unfocusedSuffixColor;
        if ((i2 & 64) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r69, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r69) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r69) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r69) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i8)) : 0.0f);
            disabledSuffixColor2 = m4161copywmQWz5c;
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(768358577, $changed, $changed1, "androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:642)");
        }
        long errorCursorColor3 = errorCursorColor2;
        long errorCursorColor4 = disabledIndicatorColor2;
        long cursorColor3 = cursorColor2;
        long cursorColor4 = unfocusedIndicatorColor2;
        long unfocusedIndicatorColor3 = unfocusedLeadingIconColor2;
        long unfocusedLeadingIconColor3 = focusedTrailingIconColor2;
        long focusedTrailingIconColor3 = errorTrailingIconColor2;
        long errorTrailingIconColor3 = disabledLabelColor2;
        long disabledLabelColor3 = unfocusedPlaceholderColor2;
        long errorContainerColor3 = errorContainerColor2;
        long errorContainerColor4 = focusedIndicatorColor2;
        long focusedIndicatorColor3 = errorIndicatorColor2;
        long errorIndicatorColor3 = disabledLeadingIconColor2;
        long disabledLeadingIconColor3 = unfocusedTrailingIconColor2;
        long unfocusedTrailingIconColor3 = focusedLabelColor2;
        long focusedLabelColor3 = errorLabelColor2;
        long errorLabelColor3 = disabledPlaceholderColor2;
        long focusedLeadingIconColor3 = focusedLeadingIconColor2;
        long focusedLeadingIconColor4 = errorLeadingIconColor2;
        long errorLeadingIconColor3 = disabledTrailingIconColor2;
        long disabledTrailingIconColor3 = unfocusedLabelColor2;
        long unfocusedLabelColor3 = focusedPlaceholderColor2;
        long focusedPlaceholderColor3 = errorPlaceholderColor2;
        TextFieldColors m2678colors0hiis_0 = TextFieldDefaults.INSTANCE.m2678colors0hiis_0(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor3, cursorColor3, errorCursorColor3, selectionColors3, errorContainerColor4, cursorColor4, errorCursorColor4, focusedIndicatorColor3, focusedLeadingIconColor3, unfocusedIndicatorColor3, errorIndicatorColor3, focusedLeadingIconColor4, unfocusedLeadingIconColor3, disabledLeadingIconColor3, errorLeadingIconColor3, focusedTrailingIconColor3, unfocusedTrailingIconColor3, disabledTrailingIconColor3, errorTrailingIconColor3, focusedLabelColor3, unfocusedLabelColor3, disabledLabelColor3, errorLabelColor3, focusedPlaceholderColor3, 0L, 0L, 0L, 0L, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | ($changed & 458752) | ($changed & 3670016) | ($changed & 29360128) | ($changed & 234881024) | ($changed & 1879048192), ($changed1 & 14) | ($changed1 & 112) | ($changed1 & 896) | ($changed1 & 7168) | ($changed1 & 57344) | ($changed1 & 458752) | ($changed1 & 3670016) | ($changed1 & 29360128) | ($changed1 & 234881024) | ($changed1 & 1879048192), ($changed2 & 14) | ($changed2 & 112) | ($changed2 & 896) | ($changed2 & 7168) | ($changed2 & 57344) | ($changed2 & 458752) | ($changed2 & 3670016) | ($changed2 & 29360128) | ($changed2 & 234881024) | ($changed2 & 1879048192), ($changed3 & 14) | (($changed3 << 12) & 458752) | (($changed3 << 12) & 3670016) | (($changed3 << 12) & 29360128) | (($changed3 << 12) & 234881024) | (($changed3 << 12) & 1879048192), (($changed3 >> 18) & 14) | 3072 | (($changed3 >> 18) & 112) | (($changed3 >> 18) & 896), 0, 15);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2678colors0hiis_0;
    }

    /* renamed from: outlinedTextFieldColors-FD9MK7s, reason: not valid java name */
    public final TextFieldColors m2088outlinedTextFieldColorsFD9MK7s(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int i, int i2) {
        SelectionColors selectionColors2;
        int i3;
        long focusedBorderColor2;
        int i4;
        long errorBorderColor2;
        int i5;
        long errorLeadingIconColor2;
        int i6;
        long errorTrailingIconColor2;
        int i7;
        long errorLabelColor2;
        int i8;
        long errorPlaceholderColor2;
        long disabledPrefixColor2;
        int i9;
        long errorPrefixColor2;
        long disabledSuffixColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ComposerKt.sourceInformationMarkerStart($composer, -1567195085, "C(outlinedTextFieldColors)P(27:c#ui.graphics.Color,37:c#ui.graphics.Color,8:c#ui.graphics.Color,18:c#ui.graphics.Color,21:c#ui.graphics.Color,31:c#ui.graphics.Color,2:c#ui.graphics.Color,11:c#ui.graphics.Color,0:c#ui.graphics.Color,12:c#ui.graphics.Color,29,20:c#ui.graphics.Color,30:c#ui.graphics.Color,1:c#ui.graphics.Color,10:c#ui.graphics.Color,23:c#ui.graphics.Color,33:c#ui.graphics.Color,4:c#ui.graphics.Color,14:c#ui.graphics.Color,28:c#ui.graphics.Color,38:c#ui.graphics.Color,9:c#ui.graphics.Color,19:c#ui.graphics.Color,22:c#ui.graphics.Color,32:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.graphics.Color,24:c#ui.graphics.Color,34:c#ui.graphics.Color,5:c#ui.graphics.Color,15:c#ui.graphics.Color,25:c#ui.graphics.Color,35:c#ui.graphics.Color,6:c#ui.graphics.Color,16:c#ui.graphics.Color,26:c#ui.graphics.Color,36:c#ui.graphics.Color,7:c#ui.graphics.Color,17:c#ui.graphics.Color)734@37917L5,735@38007L5,737@38116L5,740@38308L5,745@38624L5,746@38722L5,747@38801L7,748@38900L5,749@38994L5,751@39107L5,754@39305L5,756@39423L5,758@39538L5,760@39660L5,764@39883L5,766@40003L5,768@40120L5,770@40244L5,774@40470L5,775@40564L5,776@40655L5,778@40765L5,781@40958L5,782@41058L5,784@41172L5,786@41293L5,789@41497L5,790@41592L5,791@41689L5,793@41805L5,796@42004L5,797@42099L5,798@42196L5,800@42312L5,803@42511L5,805@42577L2206:ExposedDropdownMenu.android.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldInputTextColor(), $composer, 6) : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f) : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), $composer, 6) : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 1024) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        } else {
            selectionColors2 = selectionColors;
        }
        if ((i & 2048) != 0) {
            i3 = 6;
            focusedBorderColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusOutlineColor(), $composer, 6);
        } else {
            i3 = 6;
            focusedBorderColor2 = focusedBorderColor;
        }
        long unfocusedBorderColor2 = (i & 4096) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldOutlineColor(), $composer, i3) : unfocusedBorderColor;
        long disabledBorderColor2 = (i & 8192) != 0 ? Color.m4161copywmQWz5c(r35, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r35) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r35) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r35) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineColor(), $composer, i3)) : 0.0f) : disabledBorderColor;
        SelectionColors selectionColors3 = selectionColors2;
        if ((i & 16384) != 0) {
            i4 = 6;
            errorBorderColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorOutlineColor(), $composer, 6);
        } else {
            i4 = 6;
            errorBorderColor2 = errorBorderColor;
        }
        long focusedLeadingIconColor2 = (i & 32768) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), $composer, i4) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 65536) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), $composer, i4) : unfocusedLeadingIconColor;
        long disabledLeadingIconColor2 = (i & 131072) != 0 ? Color.m4161copywmQWz5c(r41, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r41) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r41) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r41) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), $composer, i4)) : 0.0f) : disabledLeadingIconColor;
        if ((i & 262144) != 0) {
            i5 = 6;
            errorLeadingIconColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), $composer, 6);
        } else {
            i5 = 6;
            errorLeadingIconColor2 = errorLeadingIconColor;
        }
        long focusedTrailingIconColor2 = (i & 524288) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), $composer, i5) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (i & 1048576) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), $composer, i5) : unfocusedTrailingIconColor;
        long disabledTrailingIconColor2 = (i & 2097152) != 0 ? Color.m4161copywmQWz5c(r49, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r49) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r49) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r49) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), $composer, i5)) : 0.0f) : disabledTrailingIconColor;
        if ((i & 4194304) != 0) {
            i6 = 6;
            errorTrailingIconColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), $composer, 6);
        } else {
            i6 = 6;
            errorTrailingIconColor2 = errorTrailingIconColor;
        }
        long focusedLabelColor2 = (i & 8388608) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), $composer, i6) : focusedLabelColor;
        long unfocusedLabelColor2 = (i & 16777216) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), $composer, i6) : unfocusedLabelColor;
        long disabledLabelColor2 = (i & 33554432) != 0 ? Color.m4161copywmQWz5c(r57, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r57) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r57) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r57) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), $composer, i6)) : 0.0f) : disabledLabelColor;
        if ((i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0) {
            i7 = 6;
            errorLabelColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), $composer, 6);
        } else {
            i7 = 6;
            errorLabelColor2 = errorLabelColor;
        }
        long focusedPlaceholderColor2 = (i & 134217728) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (i & 268435456) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : unfocusedPlaceholderColor;
        long disabledPlaceholderColor2 = (i & 536870912) != 0 ? Color.m4161copywmQWz5c(r65, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r65) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r65) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r65) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i7)) : 0.0f) : disabledPlaceholderColor;
        if ((i & BasicMeasure.EXACTLY) != 0) {
            i8 = 6;
            errorPlaceholderColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i8 = 6;
            errorPlaceholderColor2 = errorPlaceholderColor;
        }
        long focusedPrefixColor2 = (i2 & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 2) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : unfocusedPrefixColor;
        if ((i2 & 4) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r69, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r69) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r69) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r69) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i8)) : 0.0f);
            disabledPrefixColor2 = m4161copywmQWz5c2;
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        if ((i2 & 8) != 0) {
            i9 = 6;
            errorPrefixColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i9 = 6;
            errorPrefixColor2 = errorPrefixColor;
        }
        long focusedSuffixColor2 = (i2 & 16) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i9) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i9) : unfocusedSuffixColor;
        if ((i2 & 64) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r69, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r69) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r69) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r69) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i9)) : 0.0f);
            disabledSuffixColor2 = m4161copywmQWz5c;
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 128) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1567195085, $changed, $changed1, "androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:805)");
        }
        long errorCursorColor3 = errorCursorColor2;
        long errorCursorColor4 = disabledBorderColor2;
        long cursorColor3 = cursorColor2;
        long cursorColor4 = unfocusedBorderColor2;
        long unfocusedBorderColor3 = unfocusedLeadingIconColor2;
        long unfocusedLeadingIconColor3 = focusedTrailingIconColor2;
        long focusedTrailingIconColor3 = errorTrailingIconColor2;
        long errorTrailingIconColor3 = disabledLabelColor2;
        long disabledLabelColor3 = unfocusedPlaceholderColor2;
        long errorContainerColor3 = errorContainerColor2;
        long errorContainerColor4 = focusedBorderColor2;
        long focusedBorderColor3 = errorBorderColor2;
        long errorBorderColor3 = disabledLeadingIconColor2;
        long disabledLeadingIconColor3 = unfocusedTrailingIconColor2;
        long unfocusedTrailingIconColor3 = focusedLabelColor2;
        long focusedLabelColor3 = errorLabelColor2;
        long errorLabelColor3 = disabledPlaceholderColor2;
        long focusedLeadingIconColor3 = focusedLeadingIconColor2;
        long focusedLeadingIconColor4 = errorLeadingIconColor2;
        long errorLeadingIconColor3 = disabledTrailingIconColor2;
        long disabledTrailingIconColor3 = unfocusedLabelColor2;
        long unfocusedLabelColor3 = focusedPlaceholderColor2;
        long focusedPlaceholderColor3 = errorPlaceholderColor2;
        TextFieldColors m2347colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m2347colors0hiis_0(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor3, cursorColor3, errorCursorColor3, selectionColors3, errorContainerColor4, cursorColor4, errorCursorColor4, focusedBorderColor3, focusedLeadingIconColor3, unfocusedBorderColor3, errorBorderColor3, focusedLeadingIconColor4, unfocusedLeadingIconColor3, disabledLeadingIconColor3, errorLeadingIconColor3, focusedTrailingIconColor3, unfocusedTrailingIconColor3, disabledTrailingIconColor3, errorTrailingIconColor3, focusedLabelColor3, unfocusedLabelColor3, disabledLabelColor3, errorLabelColor3, focusedPlaceholderColor3, 0L, 0L, 0L, 0L, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | ($changed & 458752) | ($changed & 3670016) | ($changed & 29360128) | ($changed & 234881024) | ($changed & 1879048192), ($changed1 & 14) | ($changed1 & 112) | ($changed1 & 896) | ($changed1 & 7168) | ($changed1 & 57344) | ($changed1 & 458752) | ($changed1 & 3670016) | ($changed1 & 29360128) | ($changed1 & 234881024) | ($changed1 & 1879048192), ($changed2 & 14) | ($changed2 & 112) | ($changed2 & 896) | ($changed2 & 7168) | ($changed2 & 57344) | ($changed2 & 458752) | ($changed2 & 3670016) | ($changed2 & 29360128) | ($changed2 & 234881024) | ($changed2 & 1879048192), ($changed3 & 14) | (($changed3 << 12) & 458752) | (($changed3 << 12) & 3670016) | (($changed3 << 12) & 29360128) | (($changed3 << 12) & 234881024) | (($changed3 << 12) & 1879048192), (($changed3 >> 18) & 14) | 3072 | (($changed3 >> 18) & 112) | (($changed3 >> 18) & 896), 0, 15);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2347colors0hiis_0;
    }

    public final PaddingValues getItemContentPadding() {
        return ItemContentPadding;
    }

    static {
        float f;
        f = ExposedDropdownMenu_androidKt.ExposedDropdownMenuItemHorizontalPadding;
        ItemContentPadding = PaddingKt.m666PaddingValuesYgX7TsA(f, Dp.m6626constructorimpl(0));
    }

    /* renamed from: popupProperties-pR6Bxps$material3_release, reason: not valid java name */
    public final PopupProperties m2091popupPropertiespR6Bxps$material3_release(String anchorType, Composer $composer, int $changed) {
        int flags;
        ComposerKt.sourceInformationMarkerStart($composer, -1724259382, "C(popupProperties)P(0:c#material3.MenuAnchorType)861@45439L35:ExposedDropdownMenu.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1724259382, $changed, -1, "androidx.compose.material3.ExposedDropdownMenuDefaults.popupProperties (ExposedDropdownMenu.android.kt:860)");
        }
        boolean imeRequired = false;
        State a11yServicesEnabled$delegate = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(false, false, $composer, 0, 3);
        int flags2 = 393216;
        if (!popupProperties_pR6Bxps$lambda$0(a11yServicesEnabled$delegate)) {
            flags2 = 393216 | 32;
        }
        if (MenuAnchorType.m2224equalsimpl0(anchorType, MenuAnchorType.INSTANCE.m2228getPrimaryEditableMg6Rgbw()) || (MenuAnchorType.m2224equalsimpl0(anchorType, MenuAnchorType.INSTANCE.m2230getSecondaryEditableMg6Rgbw()) && !popupProperties_pR6Bxps$lambda$0(a11yServicesEnabled$delegate))) {
            imeRequired = true;
        }
        if (!imeRequired) {
            flags = flags2;
        } else {
            flags = flags2 | 8;
        }
        PopupProperties popupProperties = new PopupProperties(flags, false, false, false, false, false, 62, (DefaultConstructorMarker) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return popupProperties;
    }

    private static final boolean popupProperties_pR6Bxps$lambda$0(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public final /* synthetic */ void TrailingIcon(boolean expanded, Composer $composer, final int $changed) {
        final boolean expanded2;
        Composer $composer2 = $composer.startRestartGroup(-473088613);
        ComposerKt.sourceInformation($composer2, "C(TrailingIcon)886@46586L32:ExposedDropdownMenu.android.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(expanded) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(this) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            expanded2 = expanded;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-473088613, $dirty, -1, "androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon (ExposedDropdownMenu.android.kt:886)");
            }
            expanded2 = expanded;
            TrailingIcon(expanded2, Modifier.INSTANCE, $composer2, ($dirty & 14) | 48 | (($dirty << 3) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ExposedDropdownMenuDefaults$TrailingIcon$2
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
                    ExposedDropdownMenuDefaults.this.TrailingIcon(expanded2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: textFieldColors-tN0la-I, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2094textFieldColorstN0laI(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long containerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int i, int i2) {
        SelectionColors selectionColors2;
        int i3;
        long focusedIndicatorColor2;
        long disabledIndicatorColor2;
        int i4;
        long errorIndicatorColor2;
        long disabledLeadingIconColor2;
        int i5;
        long errorLeadingIconColor2;
        long disabledTrailingIconColor2;
        int i6;
        long errorTrailingIconColor2;
        long disabledPlaceholderColor2;
        int i7;
        long errorPlaceholderColor2;
        long disabledPrefixColor2;
        int i8;
        long errorPrefixColor2;
        long disabledSuffixColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        ComposerKt.sourceInformationMarkerStart($composer, 611690079, "C(textFieldColors)P(26:c#ui.graphics.Color,35:c#ui.graphics.Color,8:c#ui.graphics.Color,18:c#ui.graphics.Color,0:c#ui.graphics.Color,10:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,28,20:c#ui.graphics.Color,29:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.graphics.Color,22:c#ui.graphics.Color,31:c#ui.graphics.Color,4:c#ui.graphics.Color,14:c#ui.graphics.Color,27:c#ui.graphics.Color,36:c#ui.graphics.Color,9:c#ui.graphics.Color,19:c#ui.graphics.Color,21:c#ui.graphics.Color,30:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.graphics.Color,23:c#ui.graphics.Color,32:c#ui.graphics.Color,5:c#ui.graphics.Color,15:c#ui.graphics.Color,24:c#ui.graphics.Color,33:c#ui.graphics.Color,6:c#ui.graphics.Color,16:c#ui.graphics.Color,25:c#ui.graphics.Color,34:c#ui.graphics.Color,7:c#ui.graphics.Color,17:c#ui.graphics.Color)891@46833L5,892@46921L5,894@47028L5,897@47216L5,898@47304L5,899@47397L5,900@47478L5,901@47574L5,902@47653L7,904@47773L5,906@47888L5,908@48010L5,912@48235L5,914@48351L5,915@48452L5,917@48572L5,921@48791L5,923@48909L5,925@49024L5,927@49146L5,931@49368L5,932@49460L5,933@49549L5,934@49645L5,935@49735L5,936@49833L5,937@49933L5,939@50052L5,942@50252L5,943@50345L5,944@50440L5,946@50554L5,949@50749L5,950@50842L5,951@50937L5,953@51051L5,956@51246L5,958@51286L2215:ExposedDropdownMenu.android.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldInputTextColor(), $composer, 6) : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.m4161copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r11) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f) : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), $composer, 6) : errorTextColor;
        long containerColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), $composer, 6) : containerColor;
        long errorContainerColor2 = (i & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), $composer, 6) : errorContainerColor;
        long cursorColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 256) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        } else {
            selectionColors2 = selectionColors;
        }
        if ((i & 512) != 0) {
            i3 = 6;
            focusedIndicatorColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusActiveIndicatorColor(), $composer, 6);
        } else {
            i3 = 6;
            focusedIndicatorColor2 = focusedIndicatorColor;
        }
        long unfocusedIndicatorColor2 = (i & 1024) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldActiveIndicatorColor(), $composer, i3) : unfocusedIndicatorColor;
        long disabledIndicatorColor3 = (i & 2048) != 0 ? Color.m4161copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r29) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorColor(), $composer, i3)) : 0.0f) : disabledIndicatorColor;
        SelectionColors selectionColors3 = selectionColors2;
        if ((i & 4096) != 0) {
            disabledIndicatorColor2 = disabledIndicatorColor3;
            i4 = 6;
            errorIndicatorColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorActiveIndicatorColor(), $composer, 6);
        } else {
            disabledIndicatorColor2 = disabledIndicatorColor3;
            i4 = 6;
            errorIndicatorColor2 = errorIndicatorColor;
        }
        long focusedLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), $composer, i4) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), $composer, i4) : unfocusedLeadingIconColor;
        if ((32768 & i) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r35, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r35) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r35) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r35) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), $composer, i4)) : 0.0f);
            disabledLeadingIconColor2 = m4161copywmQWz5c5;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        if ((65536 & i) != 0) {
            i5 = 6;
            errorLeadingIconColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), $composer, 6);
        } else {
            i5 = 6;
            errorLeadingIconColor2 = errorLeadingIconColor;
        }
        long focusedTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), $composer, i5) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (262144 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), $composer, i5) : unfocusedTrailingIconColor;
        if ((524288 & i) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r43, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r43) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r43) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r43) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), $composer, i5)) : 0.0f);
            disabledTrailingIconColor2 = m4161copywmQWz5c4;
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        if ((1048576 & i) != 0) {
            i6 = 6;
            errorTrailingIconColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), $composer, 6);
        } else {
            i6 = 6;
            errorTrailingIconColor2 = errorTrailingIconColor;
        }
        long focusedLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), $composer, i6) : focusedLabelColor;
        long unfocusedLabelColor2 = (4194304 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), $composer, i6) : unfocusedLabelColor;
        long disabledLabelColor2 = (8388608 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), $composer, i6) : disabledLabelColor;
        long errorLabelColor2 = (16777216 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), $composer, i6) : errorLabelColor;
        long focusedPlaceholderColor2 = (33554432 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i6) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (67108864 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i6) : unfocusedPlaceholderColor;
        if ((134217728 & i) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r59, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r59) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r59) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r59) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i6)) : 0.0f);
            disabledPlaceholderColor2 = m4161copywmQWz5c3;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if ((268435456 & i) != 0) {
            i7 = 6;
            errorPlaceholderColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i7 = 6;
            errorPlaceholderColor2 = errorPlaceholderColor;
        }
        long focusedPrefixColor2 = (536870912 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i & BasicMeasure.EXACTLY) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : unfocusedPrefixColor;
        if ((i2 & 1) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i7)) : 0.0f);
            disabledPrefixColor2 = m4161copywmQWz5c2;
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        if ((i2 & 2) != 0) {
            i8 = 6;
            errorPrefixColor2 = ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i8 = 6;
            errorPrefixColor2 = errorPrefixColor;
        }
        long focusedSuffixColor2 = (i2 & 4) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : unfocusedSuffixColor;
        if ((i2 & 16) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i8)) : 0.0f);
            disabledSuffixColor2 = m4161copywmQWz5c;
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(611690079, $changed, $changed1, "androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:958)");
        }
        long containerColor3 = containerColor2;
        long unfocusedIndicatorColor3 = unfocusedIndicatorColor2;
        long unfocusedIndicatorColor4 = disabledIndicatorColor2;
        TextFieldColors m2092textFieldColorsFD9MK7s = m2092textFieldColorsFD9MK7s(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, containerColor3, containerColor2, containerColor3, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors3, focusedIndicatorColor2, unfocusedIndicatorColor3, unfocusedIndicatorColor4, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | (($changed << 3) & 458752) | (($changed << 6) & 3670016) | (($changed << 6) & 29360128) | (($changed << 6) & 234881024) | (($changed << 6) & 1879048192), (($changed1 << 6) & 896) | (($changed >> 24) & 14) | (($changed >> 24) & 112) | (($changed1 << 6) & 7168) | (($changed1 << 6) & 57344) | (($changed1 << 6) & 458752) | (($changed1 << 6) & 3670016) | (($changed1 << 6) & 29360128) | (($changed1 << 6) & 234881024) | (($changed1 << 6) & 1879048192), (($changed1 >> 24) & 14) | (($changed1 >> 24) & 112) | (($changed2 << 6) & 896) | (($changed2 << 6) & 7168) | (($changed2 << 6) & 57344) | (($changed2 << 6) & 458752) | (($changed2 << 6) & 3670016) | (($changed2 << 6) & 29360128) | (($changed2 << 6) & 234881024) | (($changed2 << 6) & 1879048192), (($changed2 >> 24) & 14) | (($changed2 >> 24) & 112) | (($changed3 << 6) & 896) | (($changed3 << 6) & 7168) | (($changed3 << 6) & 57344) | (($changed3 << 6) & 458752) | (($changed3 << 6) & 3670016) | (($changed3 << 6) & 29360128) | (($changed3 << 6) & 234881024) | (($changed3 << 6) & 1879048192), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2092textFieldColorsFD9MK7s;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: outlinedTextFieldColors-tN0la-I, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2090outlinedTextFieldColorstN0laI(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long containerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int i, int i2) {
        SelectionColors selectionColors2;
        int i3;
        long focusedBorderColor2;
        long disabledBorderColor2;
        int i4;
        long errorBorderColor2;
        long disabledLeadingIconColor2;
        int i5;
        long errorLeadingIconColor2;
        long disabledTrailingIconColor2;
        int i6;
        long errorTrailingIconColor2;
        long disabledLabelColor2;
        int i7;
        long errorLabelColor2;
        long disabledPlaceholderColor2;
        int i8;
        long errorPlaceholderColor2;
        long disabledPrefixColor2;
        int i9;
        long errorPrefixColor2;
        long disabledSuffixColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        ComposerKt.sourceInformationMarkerStart($composer, -388128543, "C(outlinedTextFieldColors)P(26:c#ui.graphics.Color,35:c#ui.graphics.Color,8:c#ui.graphics.Color,18:c#ui.graphics.Color,0:c#ui.graphics.Color,11:c#ui.graphics.Color,1:c#ui.graphics.Color,12:c#ui.graphics.Color,28,20:c#ui.graphics.Color,29:c#ui.graphics.Color,2:c#ui.graphics.Color,10:c#ui.graphics.Color,22:c#ui.graphics.Color,31:c#ui.graphics.Color,4:c#ui.graphics.Color,14:c#ui.graphics.Color,27:c#ui.graphics.Color,36:c#ui.graphics.Color,9:c#ui.graphics.Color,19:c#ui.graphics.Color,21:c#ui.graphics.Color,30:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.graphics.Color,23:c#ui.graphics.Color,32:c#ui.graphics.Color,5:c#ui.graphics.Color,15:c#ui.graphics.Color,24:c#ui.graphics.Color,33:c#ui.graphics.Color,6:c#ui.graphics.Color,16:c#ui.graphics.Color,25:c#ui.graphics.Color,34:c#ui.graphics.Color,7:c#ui.graphics.Color,17:c#ui.graphics.Color)1003@53726L5,1004@53816L5,1006@53925L5,1009@54117L5,1012@54307L5,1013@54405L5,1014@54484L7,1015@54583L5,1016@54677L5,1018@54790L5,1021@54988L5,1023@55106L5,1025@55221L5,1027@55343L5,1031@55566L5,1033@55686L5,1035@55803L5,1037@55927L5,1041@56153L5,1042@56247L5,1043@56338L5,1045@56448L5,1048@56641L5,1049@56741L5,1051@56855L5,1053@56976L5,1056@57180L5,1057@57275L5,1058@57372L5,1060@57488L5,1063@57687L5,1064@57782L5,1065@57879L5,1067@57995L5,1070@58194L5,1072@58234L2199:ExposedDropdownMenu.android.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusInputTextColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldInputTextColor(), $composer, 6) : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.m4161copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r11) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f) : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorInputTextColor(), $composer, 6) : errorTextColor;
        long containerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : containerColor;
        long errorContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 128) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 256) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        } else {
            selectionColors2 = selectionColors;
        }
        if ((i & 512) != 0) {
            i3 = 6;
            focusedBorderColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusOutlineColor(), $composer, 6);
        } else {
            i3 = 6;
            focusedBorderColor2 = focusedBorderColor;
        }
        long unfocusedBorderColor2 = (i & 1024) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldOutlineColor(), $composer, i3) : unfocusedBorderColor;
        long disabledBorderColor3 = (i & 2048) != 0 ? Color.m4161copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r29) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineColor(), $composer, i3)) : 0.0f) : disabledBorderColor;
        SelectionColors selectionColors3 = selectionColors2;
        if ((i & 4096) != 0) {
            disabledBorderColor2 = disabledBorderColor3;
            i4 = 6;
            errorBorderColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorOutlineColor(), $composer, 6);
        } else {
            disabledBorderColor2 = disabledBorderColor3;
            i4 = 6;
            errorBorderColor2 = errorBorderColor;
        }
        long focusedLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), $composer, i4) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), $composer, i4) : unfocusedLeadingIconColor;
        if ((32768 & i) != 0) {
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r35, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r35) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r35) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r35) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), $composer, i4)) : 0.0f);
            disabledLeadingIconColor2 = m4161copywmQWz5c6;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        if ((65536 & i) != 0) {
            i5 = 6;
            errorLeadingIconColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), $composer, 6);
        } else {
            i5 = 6;
            errorLeadingIconColor2 = errorLeadingIconColor;
        }
        long focusedTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), $composer, i5) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (262144 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), $composer, i5) : unfocusedTrailingIconColor;
        if ((524288 & i) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r43, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r43) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r43) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r43) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), $composer, i5)) : 0.0f);
            disabledTrailingIconColor2 = m4161copywmQWz5c5;
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        if ((1048576 & i) != 0) {
            i6 = 6;
            errorTrailingIconColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), $composer, 6);
        } else {
            i6 = 6;
            errorTrailingIconColor2 = errorTrailingIconColor;
        }
        long focusedLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), $composer, i6) : focusedLabelColor;
        long unfocusedLabelColor2 = (4194304 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), $composer, i6) : unfocusedLabelColor;
        if ((8388608 & i) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r51, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r51) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r51) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r51) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), $composer, i6)) : 0.0f);
            disabledLabelColor2 = m4161copywmQWz5c4;
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        if ((16777216 & i) != 0) {
            i7 = 6;
            errorLabelColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), $composer, 6);
        } else {
            i7 = 6;
            errorLabelColor2 = errorLabelColor;
        }
        long focusedPlaceholderColor2 = (33554432 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (67108864 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i7) : unfocusedPlaceholderColor;
        if ((134217728 & i) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r59, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r59) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r59) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r59) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i7)) : 0.0f);
            disabledPlaceholderColor2 = m4161copywmQWz5c3;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if ((268435456 & i) != 0) {
            i8 = 6;
            errorPlaceholderColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i8 = 6;
            errorPlaceholderColor2 = errorPlaceholderColor;
        }
        long focusedPrefixColor2 = (536870912 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i & BasicMeasure.EXACTLY) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i8) : unfocusedPrefixColor;
        if ((i2 & 1) != 0) {
            m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i8)) : 0.0f);
            disabledPrefixColor2 = m4161copywmQWz5c2;
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        if ((i2 & 2) != 0) {
            i9 = 6;
            errorPrefixColor2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        } else {
            i9 = 6;
            errorPrefixColor2 = errorPrefixColor;
        }
        long focusedSuffixColor2 = (i2 & 4) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i9) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, i9) : unfocusedSuffixColor;
        if ((i2 & 16) != 0) {
            m4161copywmQWz5c = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, i9)) : 0.0f);
            disabledSuffixColor2 = m4161copywmQWz5c;
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-388128543, $changed, $changed1, "androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:1072)");
        }
        long containerColor3 = containerColor2;
        long unfocusedBorderColor3 = unfocusedBorderColor2;
        long unfocusedBorderColor4 = disabledBorderColor2;
        TextFieldColors m2088outlinedTextFieldColorsFD9MK7s = m2088outlinedTextFieldColorsFD9MK7s(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, containerColor3, containerColor2, containerColor3, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors3, focusedBorderColor2, unfocusedBorderColor3, unfocusedBorderColor4, errorBorderColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | (($changed << 3) & 458752) | (($changed << 6) & 3670016) | (($changed << 6) & 29360128) | (($changed << 6) & 234881024) | (($changed << 6) & 1879048192), (($changed1 << 6) & 896) | (($changed >> 24) & 14) | (($changed >> 24) & 112) | (($changed1 << 6) & 7168) | (($changed1 << 6) & 57344) | (($changed1 << 6) & 458752) | (($changed1 << 6) & 3670016) | (($changed1 << 6) & 29360128) | (($changed1 << 6) & 234881024) | (($changed1 << 6) & 1879048192), (($changed1 >> 24) & 14) | (($changed1 >> 24) & 112) | (($changed2 << 6) & 896) | (($changed2 << 6) & 7168) | (($changed2 << 6) & 57344) | (($changed2 << 6) & 458752) | (($changed2 << 6) & 3670016) | (($changed2 << 6) & 29360128) | (($changed2 << 6) & 234881024) | (($changed2 << 6) & 1879048192), (($changed2 >> 24) & 14) | (($changed2 >> 24) & 112) | (($changed3 << 6) & 896) | (($changed3 << 6) & 7168) | (($changed3 << 6) & 57344) | (($changed3 << 6) & 458752) | (($changed3 << 6) & 3670016) | (($changed3 << 6) & 29360128) | (($changed3 << 6) & 234881024) | (($changed3 << 6) & 1879048192), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2088outlinedTextFieldColorsFD9MK7s;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: textFieldColors-St-qZLY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2093textFieldColorsStqZLY(long textColor, long disabledTextColor, long containerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        SelectionColors selectionColors2;
        long disabledIndicatorColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledPlaceholderColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        ComposerKt.sourceInformationMarkerStart($composer, -1343678550, "C(textFieldColors)P(19:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,18,13:c#ui.graphics.Color,20:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.graphics.Color,15:c#ui.graphics.Color,22:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,16:c#ui.graphics.Color,23:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,21:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,17:c#ui.graphics.Color,5:c#ui.graphics.Color)1117@60636L5,1119@60743L5,1122@60930L5,1123@61011L5,1124@61107L5,1125@61186L7,1127@61306L5,1129@61421L5,1131@61543L5,1135@61768L5,1137@61884L5,1138@61985L5,1140@62105L5,1144@62324L5,1146@62442L5,1148@62557L5,1150@62679L5,1154@62901L5,1155@62993L5,1156@63082L5,1157@63178L5,1158@63268L5,1159@63359L5,1161@63473L5,1197@65427L5,1198@65521L5,1200@65638L5,1203@65842L5,1204@65934L5,1205@66028L5,1207@66145L5,1210@66349L5,1165@63611L2754:ExposedDropdownMenu.android.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldInputTextColor(), $composer, 6) : textColor;
        long disabledTextColor2 = (i & 2) != 0 ? Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f) : disabledTextColor;
        long containerColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldContainerColor(), $composer, 6) : containerColor;
        long cursorColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 32) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedIndicatorColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusActiveIndicatorColor(), $composer, 6) : focusedIndicatorColor;
        long unfocusedIndicatorColor2 = (i & 128) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldActiveIndicatorColor(), $composer, 6) : unfocusedIndicatorColor;
        if ((i & 256) != 0) {
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledActiveIndicatorColor(), $composer, 6)) : 0.0f);
            disabledIndicatorColor2 = m4161copywmQWz5c6;
        } else {
            disabledIndicatorColor2 = disabledIndicatorColor;
        }
        long errorIndicatorColor2 = (i & 512) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorActiveIndicatorColor(), $composer, 6) : errorIndicatorColor;
        long focusedLeadingIconColor2 = (i & 1024) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 2048) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((i & 4096) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), $composer, 6)) : 0.0f);
            disabledLeadingIconColor2 = m4161copywmQWz5c5;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (32768 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((65536 & i) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), $composer, 6)) : 0.0f);
            disabledTrailingIconColor2 = m4161copywmQWz5c4;
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (262144 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (524288 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), $composer, 6) : unfocusedLabelColor;
        long disabledLabelColor2 = (1048576 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), $composer, 6) : disabledLabelColor;
        long errorLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), $composer, 6) : errorLabelColor;
        long placeholderColor2 = (4194304 & i) != 0 ? ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6) : placeholderColor;
        if ((i & 8388608) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(FilledAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f);
            disabledPlaceholderColor2 = m4161copywmQWz5c3;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1343678550, $changed, $changed1, "androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.android.kt:1165)");
        }
        long value = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        long value2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, 6)) : 0.0f);
        long value3 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        long value4 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        long value5 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, 6)) : 0.0f);
        long textColor3 = textColor2;
        long containerColor3 = containerColor2;
        TextFieldColors m2092textFieldColorsFD9MK7s = m2092textFieldColorsFD9MK7s(textColor3, textColor2, disabledTextColor2, textColor3, containerColor3, containerColor3, containerColor3, containerColor3, cursorColor2, errorCursorColor2, selectionColors2, focusedIndicatorColor2, unfocusedIndicatorColor2, disabledIndicatorColor2, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, placeholderColor2, disabledPlaceholderColor2, placeholderColor2, value, value2, m4161copywmQWz5c, value3, value4, value5, m4161copywmQWz5c2, ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6), $composer, ($changed & 14) | (($changed << 3) & 112) | (($changed << 3) & 896) | (($changed << 9) & 7168) | (($changed << 6) & 57344) | (($changed << 9) & 458752) | (($changed << 12) & 3670016) | (($changed << 15) & 29360128) | (($changed << 15) & 234881024) | (($changed << 15) & 1879048192), (($changed >> 15) & 14) | (($changed >> 15) & 112) | (($changed >> 15) & 896) | (($changed >> 15) & 7168) | (($changed >> 15) & 57344) | (($changed1 << 15) & 458752) | (($changed1 << 15) & 3670016) | (($changed1 << 15) & 29360128) | (($changed1 << 15) & 234881024) | (($changed1 << 15) & 1879048192), (($changed1 >> 15) & 14) | (($changed1 >> 15) & 112) | (($changed1 >> 15) & 896) | (($changed1 >> 15) & 7168) | (($changed1 >> 15) & 57344) | (($changed2 << 15) & 458752) | (($changed2 << 15) & 3670016) | (($changed2 << 15) & 29360128) | (($changed2 << 18) & 234881024) | (($changed2 << 18) & 1879048192), (($changed2 >> 6) & 14) | (($changed2 << 15) & 1879048192), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2092textFieldColorsFD9MK7s;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: outlinedTextFieldColors-St-qZLY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2089outlinedTextFieldColorsStqZLY(long textColor, long disabledTextColor, long containerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        SelectionColors selectionColors2;
        long disabledBorderColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledLabelColor2;
        long disabledPlaceholderColor2;
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        long m4161copywmQWz5c3;
        long m4161copywmQWz5c4;
        long m4161copywmQWz5c5;
        long m4161copywmQWz5c6;
        long m4161copywmQWz5c7;
        ComposerKt.sourceInformationMarkerStart($composer, -836383316, "C(outlinedTextFieldColors)P(19:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,9:c#ui.graphics.Color,18,13:c#ui.graphics.Color,20:c#ui.graphics.Color,2:c#ui.graphics.Color,8:c#ui.graphics.Color,15:c#ui.graphics.Color,22:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,16:c#ui.graphics.Color,23:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,21:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,17:c#ui.graphics.Color,5:c#ui.graphics.Color)1216@66578L5,1218@66687L5,1222@66922L5,1223@67020L5,1224@67099L7,1225@67198L5,1226@67292L5,1228@67405L5,1231@67603L5,1233@67721L5,1235@67836L5,1237@67958L5,1241@68181L5,1243@68301L5,1245@68418L5,1247@68542L5,1251@68768L5,1252@68862L5,1253@68953L5,1255@69063L5,1258@69256L5,1259@69349L5,1261@69465L5,1297@71405L5,1298@71499L5,1300@71616L5,1303@71820L5,1304@71912L5,1305@72006L5,1307@72123L5,1310@72327L5,1265@69605L2738:ExposedDropdownMenu.android.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldInputTextColor(), $composer, 6) : textColor;
        long disabledTextColor2 = (i & 2) != 0 ? Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f) : disabledTextColor;
        long containerColor2 = (i & 4) != 0 ? Color.INSTANCE.m4198getTransparent0d7_KjU() : containerColor;
        long cursorColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 32) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedBorderColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusOutlineColor(), $composer, 6) : focusedBorderColor;
        long unfocusedBorderColor2 = (i & 128) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldOutlineColor(), $composer, 6) : unfocusedBorderColor;
        if ((i & 256) != 0) {
            m4161copywmQWz5c7 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledOutlineColor(), $composer, 6)) : 0.0f);
            disabledBorderColor2 = m4161copywmQWz5c7;
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        long errorBorderColor2 = (i & 512) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorOutlineColor(), $composer, 6) : errorBorderColor;
        long focusedLeadingIconColor2 = (i & 1024) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 2048) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((i & 4096) != 0) {
            m4161copywmQWz5c6 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledLeadingIconColor(), $composer, 6)) : 0.0f);
            disabledLeadingIconColor2 = m4161copywmQWz5c6;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (32768 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((65536 & i) != 0) {
            m4161copywmQWz5c5 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldDisabledTrailingIconColor(), $composer, 6)) : 0.0f);
            disabledTrailingIconColor2 = m4161copywmQWz5c5;
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getTextFieldErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (262144 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldFocusLabelTextColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (524288 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldLabelTextColor(), $composer, 6) : unfocusedLabelColor;
        if ((1048576 & i) != 0) {
            m4161copywmQWz5c4 = Color.m4161copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r4) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledLabelTextColor(), $composer, 6)) : 0.0f);
            disabledLabelColor2 = m4161copywmQWz5c4;
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldErrorLabelTextColor(), $composer, 6) : errorLabelColor;
        long placeholderColor2 = (4194304 & i) != 0 ? ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6) : placeholderColor;
        if ((i & 8388608) != 0) {
            m4161copywmQWz5c3 = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledInputTextColor(), $composer, 6)) : 0.0f);
            disabledPlaceholderColor2 = m4161copywmQWz5c3;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-836383316, $changed, $changed1, "androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.android.kt:1265)");
        }
        long value = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        long value2 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, 6)) : 0.0f);
        long value3 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        long value4 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        long value5 = ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r3) : OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldDisabledSupportingTextColor(), $composer, 6)) : 0.0f);
        long textColor3 = textColor2;
        long containerColor3 = containerColor2;
        TextFieldColors m2088outlinedTextFieldColorsFD9MK7s = m2088outlinedTextFieldColorsFD9MK7s(textColor3, textColor2, disabledTextColor2, textColor3, containerColor3, containerColor3, containerColor3, containerColor3, cursorColor2, errorCursorColor2, selectionColors2, focusedBorderColor2, unfocusedBorderColor2, disabledBorderColor2, errorBorderColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, placeholderColor2, disabledPlaceholderColor2, placeholderColor2, value, value2, m4161copywmQWz5c, value3, value4, value5, m4161copywmQWz5c2, ColorSchemeKt.getValue(OutlinedAutocompleteTokens.INSTANCE.getFieldSupportingTextColor(), $composer, 6), $composer, ($changed & 14) | (($changed << 3) & 112) | (($changed << 3) & 896) | (($changed << 9) & 7168) | (($changed << 6) & 57344) | (($changed << 9) & 458752) | (($changed << 12) & 3670016) | (($changed << 15) & 29360128) | (($changed << 15) & 234881024) | (($changed << 15) & 1879048192), (($changed >> 15) & 14) | (($changed >> 15) & 112) | (($changed >> 15) & 896) | (($changed >> 15) & 7168) | (($changed >> 15) & 57344) | (($changed1 << 15) & 458752) | (($changed1 << 15) & 3670016) | (($changed1 << 15) & 29360128) | (($changed1 << 15) & 234881024) | (($changed1 << 15) & 1879048192), (($changed1 >> 15) & 14) | (($changed1 >> 15) & 112) | (($changed1 >> 15) & 896) | (($changed1 >> 15) & 7168) | (($changed1 >> 15) & 57344) | (($changed2 << 15) & 458752) | (($changed2 << 15) & 3670016) | (($changed2 << 15) & 29360128) | (($changed2 << 18) & 234881024) | (($changed2 << 18) & 1879048192), (($changed2 >> 6) & 14) | (($changed2 << 15) & 1879048192), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2088outlinedTextFieldColorsFD9MK7s;
    }
}
