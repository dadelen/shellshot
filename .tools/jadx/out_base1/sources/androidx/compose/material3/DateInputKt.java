package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.google.android.material.internal.ViewUtils;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* compiled from: DateInput.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001ad\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2#\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\u0010\u001a\u001a\u0098\u0001\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0013\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010 ¢\u0006\u0002\b!2\u0013\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010 ¢\u0006\u0002\b!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\n\u0010)\u001a\u00060*j\u0002`+2\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006.²\u0006\n\u0010/\u001a\u000200X\u008a\u008e\u0002"}, d2 = {"InputTextFieldPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getInputTextFieldPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "InputTextNonErroneousBottomPadding", "Landroidx/compose/ui/unit/Dp;", "F", "DateInputContent", "", "selectedDateMillis", "", "onDateSelectionChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dateInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "colors", "Landroidx/compose/material3/DatePickerColors;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "DateInputTextField", "modifier", "Landroidx/compose/ui/Modifier;", "initialDateMillis", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "inputIdentifier", "Landroidx/compose/material3/InputIdentifier;", "dateInputValidator", "Landroidx/compose/material3/DateInputValidator;", "dateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateInputTextField-tQNruF0", "(Landroidx/compose/ui/Modifier;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/DateInputValidator;Landroidx/compose/material3/internal/DateInputFormat;Ljava/util/Locale;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "material3_release", "text", "Landroidx/compose/ui/text/input/TextFieldValue;"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class DateInputKt {
    private static final PaddingValues InputTextFieldPadding = PaddingKt.m669PaddingValuesa9UjIt4$default(Dp.m6626constructorimpl(24), Dp.m6626constructorimpl(10), Dp.m6626constructorimpl(24), 0.0f, 8, null);
    private static final float InputTextNonErroneousBottomPadding = Dp.m6626constructorimpl(16);

    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    public static final void DateInputContent(final Long selectedDateMillis, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, Composer $composer, final int $changed) {
        SelectableDates selectableDates2;
        Object value$iv;
        DateInputFormat dateInputFormat;
        ?? r3;
        Composer $composer2;
        int $dirty;
        Composer $composer3;
        Composer $composer4 = $composer.startRestartGroup(643325609);
        ComposerKt.sourceInformation($composer4, "C(DateInputContent)P(5,3!1,6,2,4)62@2605L15,64@2655L75,65@2758L45,66@2838L44,67@2916L45,69@2999L551,82@3639L42,86@3836L164,92@4024L62,83@3686L901:DateInput.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer4.changed(selectedDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer4.changedInstance(calendarModel) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer4.changedInstance(yearRange) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= ($changed & 32768) == 0 ? $composer4.changed(dateFormatter) : $composer4.changedInstance(dateFormatter) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            selectableDates2 = selectableDates;
            $dirty2 |= $composer4.changed(selectableDates2) ? 131072 : 65536;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer4.changed(colors) ? 1048576 : 524288;
        }
        if ((599187 & $dirty2) != 599186 || !$composer4.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(643325609, $dirty2, -1, "androidx.compose.material3.DateInputContent (DateInput.kt:60)");
            }
            Locale defaultLocale = CalendarLocale_androidKt.defaultLocale($composer4, 0);
            ComposerKt.sourceInformationMarkerStart($composer4, 1825155115, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv = $composer4.changed(defaultLocale);
            Object it$iv = $composer4.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = calendarModel.getDateInputFormat(defaultLocale);
                $composer4.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            DateInputFormat dateInputFormat2 = (DateInputFormat) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            Strings.Companion companion = Strings.INSTANCE;
            String errorDatePattern = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_input_invalid_for_pattern), $composer4, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            String errorDateOutOfYearRange = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_input_invalid_year_range), $composer4, 0);
            Strings.Companion companion3 = Strings.INSTANCE;
            String errorInvalidNotAllowed = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_input_invalid_not_allowed), $composer4, 0);
            ComposerKt.sourceInformationMarkerStart($composer4, 1825166599, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv2 = $composer4.changed(dateInputFormat2) | ((57344 & $dirty2) == 16384 || (($dirty2 & 32768) != 0 && $composer4.changed(dateFormatter)));
            Object value$iv2 = $composer4.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                dateInputFormat = dateInputFormat2;
                r3 = 1;
                $composer2 = $composer4;
                $dirty = $dirty2;
                value$iv2 = new DateInputValidator(yearRange, selectableDates2, dateInputFormat, dateFormatter, errorDatePattern, errorDateOutOfYearRange, errorInvalidNotAllowed, "", null, null, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
                $composer4.updateRememberedValue(value$iv2);
            } else {
                $dirty = $dirty2;
                dateInputFormat = dateInputFormat2;
                r3 = 1;
                $composer2 = $composer4;
            }
            DateInputValidator dateInputValidator = (DateInputValidator) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final String pattern = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(pattern, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            Strings.Companion companion4 = Strings.INSTANCE;
            final String labelText = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_input_label), $composer2, 0);
            Modifier padding = PaddingKt.padding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, r3, null), InputTextFieldPadding);
            int m2179getSingleDateInputJ2x2o4M = InputIdentifier.INSTANCE.m2179getSingleDateInputJ2x2o4M();
            dateInputValidator.setCurrentStartDateMillis$material3_release(selectedDateMillis);
            Composer $composer5 = $composer2;
            m1997DateInputTextFieldtQNruF0(padding, selectedDateMillis, function1, calendarModel, ComposableLambdaKt.rememberComposableLambda(-1819015125, r3, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer6, int $changed2) {
                    Object value$iv3;
                    ComposerKt.sourceInformation($composer6, "C89@3929L47,87@3850L140:DateInput.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer6.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1819015125, $changed2, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:87)");
                        }
                        String str = labelText;
                        Modifier.Companion companion5 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer6, -49706674, "CC(remember):DateInput.kt#9igjgp");
                        boolean invalid$iv3 = $composer6.changed(labelText) | $composer6.changed(pattern);
                        final String str2 = labelText;
                        final String str3 = pattern;
                        Object it$iv2 = $composer6.rememberedValue();
                        if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                                    SemanticsPropertiesKt.setContentDescription($this$semantics, str2 + ", " + str3);
                                }
                            };
                            $composer6.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv2;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer6);
                        TextKt.m2697Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion5, false, (Function1) value$iv3, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 0, 0, 131068);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer6.skipToGroupEnd();
                }
            }, $composer2, 54), ComposableLambdaKt.rememberComposableLambda(-564233108, r3, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "C92@4026L58:DateInput.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer6.getSkipping()) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-564233108, $changed2, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:92)");
                    }
                    TextKt.m2697Text4IGK_g(pattern, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$3.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
                        }
                    }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer6, 0, 0, 131068);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), m2179getSingleDateInputJ2x2o4M, dateInputValidator, dateInputFormat, defaultLocale, colors, $composer5, (($dirty << 3) & 112) | 1794054 | (($dirty << 3) & 896) | (($dirty << 3) & 7168), ($dirty >> 18) & 14);
            $composer3 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer4.skipToGroupEnd();
            $composer3 = $composer4;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$4
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

                public final void invoke(Composer composer, int i) {
                    DateInputKt.DateInputContent(selectedDateMillis, function1, calendarModel, yearRange, dateFormatter, selectableDates, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* renamed from: DateInputTextField-tQNruF0, reason: not valid java name */
    public static final void m1997DateInputTextFieldtQNruF0(final Modifier modifier, final Long initialDateMillis, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final int inputIdentifier, final DateInputValidator dateInputValidator, DateInputFormat dateInputFormat, final Locale locale, final DatePickerColors colors, Composer $composer, final int $changed, final int $changed1) {
        Function1<? super Long, Unit> function12;
        int i;
        Object value$iv;
        final MutableState errorText;
        int $dirty;
        Object value$iv2;
        float f;
        Object value$iv3;
        Composer $composer2;
        final DateInputFormat dateInputFormat2 = dateInputFormat;
        Composer $composer3 = $composer.startRestartGroup(-857008589);
        ComposerKt.sourceInformation($composer3, "C(DateInputTextField)P(8,4,9!1,6,10,5:c#material3.InputIdentifier,3,2,7)122@5091L39,124@5207L488,124@5155L540,142@5766L1458,188@7730L60,191@7875L59,140@5701L2642:DateInput.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(initialDateMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            function12 = function1;
            $dirty2 |= $composer3.changedInstance(function12) ? 256 : 128;
        } else {
            function12 = function1;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(calendarModel) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(function22) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            i = inputIdentifier;
            $dirty2 |= $composer3.changed(i) ? 1048576 : 524288;
        } else {
            i = inputIdentifier;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(dateInputValidator) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(dateInputFormat2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changedInstance(locale) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(colors) ? 4 : 2;
        }
        if (($dirty2 & 306783379) != 306783378 || ($dirty1 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-857008589, $dirty2, $dirty1, "androidx.compose.material3.DateInputTextField (DateInput.kt:121)");
            }
            MutableState errorText2 = (MutableState) RememberSaveableKt.m3749rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<MutableState<String>>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$errorText$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final MutableState<String> invoke() {
                    return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                }
            }, $composer3, 3072, 6);
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, 236419420, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 112) == 32) | $composer3.changedInstance(calendarModel) | (($dirty2 & 234881024) == 67108864) | $composer3.changedInstance(locale);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<MutableState<TextFieldValue>>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$text$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final MutableState<TextFieldValue> invoke() {
                        String str;
                        Long l = initialDateMillis;
                        if (l != null) {
                            CalendarModel calendarModel2 = calendarModel;
                            DateInputFormat dateInputFormat3 = dateInputFormat2;
                            Locale locale2 = locale;
                            long it = l.longValue();
                            String formatWithPattern = calendarModel2.formatWithPattern(it, dateInputFormat3.getPatternWithoutDelimiters(), locale2);
                            if (formatWithPattern != null) {
                                str = formatWithPattern;
                                return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(str, TextRangeKt.TextRange(0, 0), (TextRange) null, 4, (DefaultConstructorMarker) null), null, 2, null);
                            }
                        }
                        str = "";
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(str, TextRangeKt.TextRange(0, 0), (TextRange) null, 4, (DefaultConstructorMarker) null), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final MutableState text$delegate = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer3, 0, 4);
            TextFieldValue DateInputTextField_tQNruF0$lambda$4 = DateInputTextField_tQNruF0$lambda$4(text$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 236438278, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 896) == 256) | (($dirty2 & 234881024) == 67108864) | $composer3.changed(text$delegate) | $composer3.changed(errorText2) | $composer3.changedInstance(calendarModel) | (($dirty2 & 29360128) == 8388608) | (($dirty2 & 3670016) == 1048576) | $composer3.changedInstance(locale);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                final int i2 = i;
                final Function1<? super Long, Unit> function13 = function12;
                errorText = errorText2;
                $dirty = $dirty2;
                Object obj = new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                        invoke2(textFieldValue);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextFieldValue input) {
                        CharSequence $this$all$iv;
                        if (input.getText().length() <= DateInputFormat.this.getPatternWithoutDelimiters().length()) {
                            CharSequence $this$all$iv2 = input.getText();
                            int i3 = 0;
                            while (true) {
                                if (i3 < $this$all$iv2.length()) {
                                    char element$iv = $this$all$iv2.charAt(i3);
                                    if (!Character.isDigit(element$iv)) {
                                        $this$all$iv = null;
                                        break;
                                    }
                                    i3++;
                                } else {
                                    $this$all$iv = 1;
                                    break;
                                }
                            }
                            if ($this$all$iv == null) {
                                return;
                            }
                            text$delegate.setValue(input);
                            String trimmedText = StringsKt.trim((CharSequence) input.getText()).toString();
                            Long l = null;
                            if ((trimmedText.length() == 0) || trimmedText.length() < DateInputFormat.this.getPatternWithoutDelimiters().length()) {
                                errorText.setValue("");
                                function13.invoke(null);
                                return;
                            }
                            CalendarDate parsedDate = calendarModel.parse(trimmedText, DateInputFormat.this.getPatternWithoutDelimiters());
                            errorText.setValue(dateInputValidator.m1998validateXivgLIo(parsedDate, i2, locale));
                            Function1<Long, Unit> function14 = function13;
                            if ((errorText.getValue().length() == 0) && parsedDate != null) {
                                l = Long.valueOf(parsedDate.getUtcTimeMillis());
                            }
                            function14.invoke(l);
                        }
                    }
                };
                dateInputFormat2 = dateInputFormat2;
                value$iv2 = (Function1) obj;
                $composer3.updateRememberedValue(value$iv2);
            } else {
                $dirty = $dirty2;
                errorText = errorText2;
                value$iv2 = it$iv2;
            }
            Function1 function14 = (Function1) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (StringsKt.isBlank((CharSequence) errorText.getValue())) {
                f = InputTextNonErroneousBottomPadding;
            } else {
                f = Dp.m6626constructorimpl(0);
            }
            Modifier m676paddingqDBjuR0$default = PaddingKt.m676paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, f, 7, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 236499728, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changed(errorText);
            Object it$iv3 = $composer3.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        if (StringsKt.isBlank(errorText.getValue())) {
                            return;
                        }
                        SemanticsPropertiesKt.error($this$semantics, errorText.getValue());
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            OutlinedTextFieldKt.OutlinedTextField(DateInputTextField_tQNruF0$lambda$4, (Function1<? super TextFieldValue, Unit>) function14, SemanticsModifierKt.semantics$default(m676paddingqDBjuR0$default, false, (Function1) value$iv3, 1, null), false, false, (TextStyle) null, function2, function22, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-591991974, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$3
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
                    ComposerKt.sourceInformation($composer4, "C191@7911L21:DateInput.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-591991974, $changed2, -1, "androidx.compose.material3.DateInputTextField.<anonymous> (DateInput.kt:191)");
                    }
                    if (!StringsKt.isBlank(errorText.getValue())) {
                        TextKt.m2697Text4IGK_g(errorText.getValue(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131070);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), !StringsKt.isBlank((CharSequence) errorText.getValue()), (VisualTransformation) new DateVisualTransformation(dateInputFormat2), new KeyboardOptions(0, (Boolean) false, KeyboardType.INSTANCE.m6326getNumberPjHm6EE(), ImeAction.INSTANCE.m6270getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 113, (DefaultConstructorMarker) null), (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, colors.getDateTextFieldColors(), $composer3, (($dirty << 6) & 3670016) | (($dirty << 6) & 29360128), 12779904, 0, 4001592);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$4
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

                public final void invoke(Composer composer, int i3) {
                    DateInputKt.m1997DateInputTextFieldtQNruF0(Modifier.this, initialDateMillis, function1, calendarModel, function2, function22, inputIdentifier, dateInputValidator, dateInputFormat2, locale, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }

    private static final TextFieldValue DateInputTextField_tQNruF0$lambda$4(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final PaddingValues getInputTextFieldPadding() {
        return InputTextFieldPadding;
    }
}
