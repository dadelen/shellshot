package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DateRangePicker.kt */
@Metadata(d1 = {"\u0000¼\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ak\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010\u0012¢\u0006\u0002\b\u00132\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010\u0012¢\u0006\u0002\b\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a°\u0001\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\n0&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003¢\u0006\u0002\u0010.\u001a`\u0010/\u001a\u00020\f2\n\u00100\u001a\u000601j\u0002`22\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010,\u001a\u00020-H\u0007ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a½\u0001\u0010:\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010;\u001a\u0002072:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\n0&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a°\u0001\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020@2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\n0&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003¢\u0006\u0002\u0010A\u001a.\u0010B\u001a\b\u0012\u0004\u0012\u00020D0C2\u0006\u0010\u000b\u001a\u00020@2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020HH\u0002\u001aT\u0010J\u001a\u00020\f2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010,\u001a\u00020-H\u0007ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001ae\u0010M\u001a\u00020\n2\u0006\u0010N\u001a\u00020\u001c2\b\u0010O\u001a\u0004\u0018\u00010\u001c2\b\u0010P\u001a\u0004\u0018\u00010\u001c2:\u0010\u001f\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\n0 H\u0002¢\u0006\u0002\u0010Q\u001a&\u0010R\u001a\u00020\n*\u00020S2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020WH\u0000ø\u0001\u0000¢\u0006\u0004\bX\u0010Y\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Z"}, d2 = {"CalendarMonthSubheadPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getCalendarMonthSubheadPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "DateRangePickerHeadlinePadding", "DateRangePickerTitlePadding", "HeaderHeightOffset", "Landroidx/compose/ui/unit/Dp;", "F", "DateRangePicker", "", "state", "Landroidx/compose/material3/DateRangePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "headline", "showModeToggle", "", "colors", "Landroidx/compose/material3/DatePickerColors;", "(Landroidx/compose/material3/DateRangePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "DateRangePickerContent", "selectedStartDateMillis", "", "selectedEndDateMillis", "displayedMonthMillis", "onDatesSelectionChange", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "startDateMillis", "endDateMillis", "onDisplayedMonthChange", "Lkotlin/Function1;", "monthInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "(Ljava/lang/Long;Ljava/lang/Long;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "DateRangePickerState", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "initialSelectedStartDateMillis", "initialSelectedEndDateMillis", "initialDisplayedMonthMillis", "initialDisplayMode", "Landroidx/compose/material3/DisplayMode;", "DateRangePickerState-HVP43zI", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DateRangePickerState;", "SwitchableDateEntryContent", "displayMode", "SwitchableDateEntryContent-RN-2D1Q", "(Ljava/lang/Long;Ljava/lang/Long;JILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "VerticalMonthsList", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "customScrollActions", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "scrollUpLabel", "", "scrollDownLabel", "rememberDateRangePickerState", "rememberDateRangePickerState-IlFM19s", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lkotlin/ranges/IntRange;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DateRangePickerState;", "updateDateSelection", "dateInMillis", "currentStartDateMillis", "currentEndDateMillis", "(JLjava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;)V", "drawRangeBackground", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "selectedRangeInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "color", "Landroidx/compose/ui/graphics/Color;", "drawRangeBackground-mxwnekA", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;Landroidx/compose/material3/SelectedRangeInfo;J)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class DateRangePickerKt {
    private static final PaddingValues CalendarMonthSubheadPadding = PaddingKt.m669PaddingValuesa9UjIt4$default(Dp.m6626constructorimpl(24), Dp.m6626constructorimpl(20), 0.0f, Dp.m6626constructorimpl(8), 4, null);
    private static final PaddingValues DateRangePickerTitlePadding = PaddingKt.m669PaddingValuesa9UjIt4$default(Dp.m6626constructorimpl(64), 0.0f, Dp.m6626constructorimpl(12), 0.0f, 10, null);
    private static final PaddingValues DateRangePickerHeadlinePadding = PaddingKt.m669PaddingValuesa9UjIt4$default(Dp.m6626constructorimpl(64), 0.0f, Dp.m6626constructorimpl(12), Dp.m6626constructorimpl(12), 2, null);
    private static final float HeaderHeightOffset = Dp.m6626constructorimpl(60);

    /* JADX WARN: Removed duplicated region for block: B:67:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x022c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DateRangePicker(final androidx.compose.material3.DateRangePickerState r27, androidx.compose.ui.Modifier r28, androidx.compose.material3.DatePickerFormatter r29, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, boolean r32, androidx.compose.material3.DatePickerColors r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateRangePickerKt.DateRangePicker(androidx.compose.material3.DateRangePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.DatePickerFormatter, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: rememberDateRangePickerState-IlFM19s, reason: not valid java name */
    public static final DateRangePickerState m2052rememberDateRangePickerStateIlFM19s(Long initialSelectedStartDateMillis, Long initialSelectedEndDateMillis, Long initialDisplayedMonthMillis, IntRange yearRange, int initialDisplayMode, SelectableDates selectableDates, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -2012087461, "C(rememberDateRangePickerState)P(3,2,1,5,0:c#material3.DisplayMode)262@11757L15,263@11866L435,263@11784L517:DateRangePicker.kt#uh7d8r");
        final Long initialSelectedStartDateMillis2 = (i & 1) != 0 ? null : initialSelectedStartDateMillis;
        final Long initialSelectedEndDateMillis2 = (i & 2) != 0 ? null : initialSelectedEndDateMillis;
        final Long initialDisplayedMonthMillis2 = (i & 4) != 0 ? initialSelectedStartDateMillis2 : initialDisplayedMonthMillis;
        final IntRange yearRange2 = (i & 8) != 0 ? DatePickerDefaults.INSTANCE.getYearRange() : yearRange;
        final int initialDisplayMode2 = (i & 16) != 0 ? DisplayMode.INSTANCE.m2073getPickerjFl4v0() : initialDisplayMode;
        final SelectableDates selectableDates2 = (i & 32) != 0 ? DatePickerDefaults.INSTANCE.getAllDates() : selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2012087461, $changed, -1, "androidx.compose.material3.rememberDateRangePickerState (DateRangePicker.kt:261)");
        }
        final Locale locale = CalendarLocale_androidKt.defaultLocale($composer, 0);
        Object[] objArr = new Object[0];
        Saver<DateRangePickerStateImpl, Object> Saver = DateRangePickerStateImpl.INSTANCE.Saver(selectableDates2, locale);
        ComposerKt.sourceInformationMarkerStart($composer, -250595241, "CC(remember):DateRangePicker.kt#9igjgp");
        boolean z = true;
        boolean changedInstance = (((($changed & 112) ^ 48) > 32 && $composer.changed(initialSelectedEndDateMillis2)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialSelectedStartDateMillis2)) || ($changed & 6) == 4) | (((($changed & 896) ^ 384) > 256 && $composer.changed(initialDisplayedMonthMillis2)) || ($changed & 384) == 256) | $composer.changedInstance(yearRange2) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(initialDisplayMode2)) || ($changed & 24576) == 16384);
        if ((((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072 || !$composer.changed(selectableDates2)) && ($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
            z = false;
        }
        boolean invalid$iv = changedInstance | z | $composer.changedInstance(locale);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<DateRangePickerStateImpl>() { // from class: androidx.compose.material3.DateRangePickerKt$rememberDateRangePickerState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DateRangePickerStateImpl invoke() {
                    return new DateRangePickerStateImpl(initialSelectedStartDateMillis2, initialSelectedEndDateMillis2, initialDisplayedMonthMillis2, yearRange2, initialDisplayMode2, selectableDates2, locale, null);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        DateRangePickerStateImpl dateRangePickerStateImpl = (DateRangePickerStateImpl) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return dateRangePickerStateImpl;
    }

    /* renamed from: DateRangePickerState-HVP43zI$default, reason: not valid java name */
    public static /* synthetic */ DateRangePickerState m2048DateRangePickerStateHVP43zI$default(Locale locale, Long l, Long l2, Long l3, IntRange intRange, int i, SelectableDates selectableDates, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            l = null;
        }
        if ((i2 & 4) != 0) {
            l2 = null;
        }
        if ((i2 & 8) != 0) {
            l3 = l;
        }
        if ((i2 & 16) != 0) {
            intRange = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i2 & 32) != 0) {
            i = DisplayMode.INSTANCE.m2073getPickerjFl4v0();
        }
        if ((i2 & 64) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        return m2047DateRangePickerStateHVP43zI(locale, l, l2, l3, intRange, i, selectableDates);
    }

    /* renamed from: DateRangePickerState-HVP43zI, reason: not valid java name */
    public static final DateRangePickerState m2047DateRangePickerStateHVP43zI(Locale locale, Long initialSelectedStartDateMillis, Long initialSelectedEndDateMillis, Long initialDisplayedMonthMillis, IntRange yearRange, int initialDisplayMode, SelectableDates selectableDates) {
        return new DateRangePickerStateImpl(initialSelectedStartDateMillis, initialSelectedEndDateMillis, initialDisplayedMonthMillis, yearRange, initialDisplayMode, selectableDates, locale, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: SwitchableDateEntryContent-RN-2D1Q, reason: not valid java name */
    public static final void m2049SwitchableDateEntryContentRN2D1Q(final Long selectedStartDateMillis, final Long selectedEndDateMillis, final long displayedMonthMillis, int displayMode, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, Composer $composer, final int $changed, final int $changed1) {
        Long l;
        long j;
        int i;
        Function2<? super Long, ? super Long, Unit> function22;
        Function1<? super Long, Unit> function12;
        CalendarModel calendarModel2;
        IntRange intRange;
        DatePickerColors datePickerColors;
        int $dirty;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-532789335);
        ComposerKt.sourceInformation($composer3, "C(SwitchableDateEntryContent)P(9,8,4,3:c#material3.DisplayMode,5,6!1,10,2,7)672@30082L1249,664@29854L1477:DateRangePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedStartDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            l = selectedEndDateMillis;
            $dirty2 |= $composer3.changed(l) ? 32 : 16;
        } else {
            l = selectedEndDateMillis;
        }
        if (($changed & 384) == 0) {
            j = displayedMonthMillis;
            $dirty2 |= $composer3.changed(j) ? 256 : 128;
        } else {
            j = displayedMonthMillis;
        }
        if (($changed & 3072) == 0) {
            i = displayMode;
            $dirty2 |= $composer3.changed(i) ? 2048 : 1024;
        } else {
            i = displayMode;
        }
        if (($changed & 24576) == 0) {
            function22 = function2;
            $dirty2 |= $composer3.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = function2;
        }
        if ((196608 & $changed) == 0) {
            function12 = function1;
            $dirty2 |= $composer3.changedInstance(function12) ? 131072 : 65536;
        } else {
            function12 = function1;
        }
        if ((1572864 & $changed) == 0) {
            calendarModel2 = calendarModel;
            $dirty2 |= $composer3.changedInstance(calendarModel2) ? 1048576 : 524288;
        } else {
            calendarModel2 = calendarModel;
        }
        if (($changed & 12582912) == 0) {
            intRange = yearRange;
            $dirty2 |= $composer3.changedInstance(intRange) ? 8388608 : 4194304;
        } else {
            intRange = yearRange;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= ($changed & 134217728) == 0 ? $composer3.changed(dateFormatter) : $composer3.changedInstance(dateFormatter) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(selectableDates) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            datePickerColors = colors;
            $dirty1 |= $composer3.changed(datePickerColors) ? 4 : 2;
        } else {
            datePickerColors = colors;
        }
        if (($dirty2 & 306783379) != 306783378 || ($dirty1 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-532789335, $dirty2, $dirty1, "androidx.compose.material3.SwitchableDateEntryContent (DateRangePicker.kt:661)");
            }
            int $dirty3 = $dirty2;
            final long j2 = j;
            final IntRange intRange2 = intRange;
            final Long l2 = l;
            final Function1<? super Long, Unit> function13 = function12;
            final CalendarModel calendarModel3 = calendarModel2;
            $dirty = $dirty3;
            final DatePickerColors datePickerColors2 = datePickerColors;
            final Function2<? super Long, ? super Long, Unit> function23 = function22;
            CrossfadeKt.Crossfade(DisplayMode.m2065boximpl(i), SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$SwitchableDateEntryContent$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setContainer($this$semantics, true);
                }
            }, 1, null), AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-1026642619, true, new Function3<DisplayMode, Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$SwitchableDateEntryContent$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DisplayMode displayMode2, Composer composer, Integer num) {
                    m2054invokeQujVXRc(displayMode2.getValue(), composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-QujVXRc, reason: not valid java name */
                public final void m2054invokeQujVXRc(int mode, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "CP(0:c#material3.DisplayMode):DateRangePicker.kt#uh7d8r");
                    int $dirty4 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty4 |= $composer4.changed(mode) ? 4 : 2;
                    }
                    int $dirty5 = $dirty4;
                    if (($dirty5 & 19) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1026642619, $dirty5, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DateRangePicker.kt:673)");
                        }
                        if (DisplayMode.m2068equalsimpl0(mode, DisplayMode.INSTANCE.m2073getPickerjFl4v0())) {
                            $composer4.startReplaceGroup(-1871305297);
                            ComposerKt.sourceInformation($composer4, "675@30164L618");
                            DateRangePickerKt.DateRangePickerContent(selectedStartDateMillis, l2, j2, function23, function13, calendarModel3, intRange2, dateFormatter, selectableDates, datePickerColors2, $composer4, 0);
                            $composer4.endReplaceGroup();
                        } else if (DisplayMode.m2068equalsimpl0(mode, DisplayMode.INSTANCE.m2072getInputjFl4v0())) {
                            $composer4.startReplaceGroup(-1871284056);
                            ComposerKt.sourceInformation($composer4, "688@30832L483");
                            DateRangeInputKt.DateRangeInputContent(selectedStartDateMillis, l2, function23, calendarModel3, intRange2, dateFormatter, selectableDates, datePickerColors2, $composer4, 0);
                            $composer4.endReplaceGroup();
                        } else {
                            $composer4.startReplaceGroup(2120210493);
                            $composer4.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, (($dirty >> 9) & 14) | 24960, 8);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $dirty = $dirty2;
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final int i2 = i;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$SwitchableDateEntryContent$3
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
                    DateRangePickerKt.m2049SwitchableDateEntryContentRN2D1Q(selectedStartDateMillis, selectedEndDateMillis, displayedMonthMillis, i2, function2, function1, calendarModel, yearRange, dateFormatter, selectableDates, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DateRangePickerContent(final java.lang.Long r34, final java.lang.Long r35, final long r36, final kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> r38, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r39, final androidx.compose.material3.internal.CalendarModel r40, final kotlin.ranges.IntRange r41, final androidx.compose.material3.DatePickerFormatter r42, final androidx.compose.material3.SelectableDates r43, final androidx.compose.material3.DatePickerColors r44, androidx.compose.runtime.Composer r45, final int r46) {
        /*
            Method dump skipped, instructions count: 729
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateRangePickerKt.DateRangePickerContent(java.lang.Long, java.lang.Long, long, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, androidx.compose.material3.internal.CalendarModel, kotlin.ranges.IntRange, androidx.compose.material3.DatePickerFormatter, androidx.compose.material3.SelectableDates, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalMonthsList(final LazyListState lazyListState, final Long selectedStartDateMillis, final Long selectedEndDateMillis, final Function2<? super Long, ? super Long, Unit> function2, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, Composer $composer, final int $changed) {
        Long l;
        Long l2;
        final SelectableDates selectableDates2;
        DateRangePickerKt$VerticalMonthsList$2$1 value$iv;
        final LazyListState lazyListState2 = lazyListState;
        Composer $composer2 = $composer.startRestartGroup(1257365001);
        ComposerKt.sourceInformation($composer2, "C(VerticalMonthsList)P(3,8,7,4,5!1,9,2,6)756@33410L158,762@33630L5,762@33637L3748,762@33573L3812,839@37420L228,839@37390L258:DateRangePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(lazyListState2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            l = selectedStartDateMillis;
            $dirty |= $composer2.changed(l) ? 32 : 16;
        } else {
            l = selectedStartDateMillis;
        }
        if (($changed & 384) == 0) {
            l2 = selectedEndDateMillis;
            $dirty |= $composer2.changed(l2) ? 256 : 128;
        } else {
            l2 = selectedEndDateMillis;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(calendarModel) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(yearRange) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= (16777216 & $changed) == 0 ? $composer2.changed(dateFormatter) : $composer2.changedInstance(dateFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            selectableDates2 = selectableDates;
            $dirty |= $composer2.changed(selectableDates2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((805306368 & $changed) == 0) {
            $dirty |= $composer2.changed(colors) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) != 306783378 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1257365001, $dirty, -1, "androidx.compose.material3.VerticalMonthsList (DateRangePicker.kt:753)");
            }
            final CalendarDate today = calendarModel.getToday();
            ComposerKt.sourceInformationMarkerStart($composer2, -2140165432, "CC(remember):DateRangePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(yearRange);
            Object value$iv2 = $composer2.rememberedValue();
            if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = calendarModel.getMonth(yearRange.getFirst(), 1);
                $composer2.updateRememberedValue(value$iv2);
            }
            final CalendarMonth firstMonth = (CalendarMonth) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $dirty2 = $dirty;
            final Long l3 = l;
            final Long l4 = l2;
            TextKt.ProvideTextStyle(TypographyKt.getValue(DatePickerModalTokens.INSTANCE.getDateLabelTextFont(), $composer2, 6), ComposableLambdaKt.rememberComposableLambda(1090773432, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1
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

                /* JADX WARN: Removed duplicated region for block: B:26:0x01c0  */
                /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r33, int r34) {
                    /*
                        Method dump skipped, instructions count: 452
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, 48);
            ComposerKt.sourceInformationMarkerStart($composer2, -2140037042, "CC(remember):DateRangePicker.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 14) == 4) | ((57344 & $dirty2) == 16384) | $composer2.changedInstance(calendarModel) | $composer2.changedInstance(yearRange);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                lazyListState2 = lazyListState;
                value$iv = new DateRangePickerKt$VerticalMonthsList$2$1(lazyListState2, function1, calendarModel, yearRange, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                lazyListState2 = lazyListState;
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(lazyListState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv, $composer2, $dirty2 & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerKt$VerticalMonthsList$3
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
                    DateRangePickerKt.VerticalMonthsList(LazyListState.this, selectedStartDateMillis, selectedEndDateMillis, function2, function1, calendarModel, yearRange, dateFormatter, selectableDates, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDateSelection(long dateInMillis, Long currentStartDateMillis, Long currentEndDateMillis, Function2<? super Long, ? super Long, Unit> function2) {
        if ((currentStartDateMillis == null && currentEndDateMillis == null) || (currentStartDateMillis != null && currentEndDateMillis != null)) {
            function2.invoke(Long.valueOf(dateInMillis), null);
        } else if (currentStartDateMillis == null || dateInMillis < currentStartDateMillis.longValue()) {
            function2.invoke(Long.valueOf(dateInMillis), null);
        } else {
            function2.invoke(currentStartDateMillis, Long.valueOf(dateInMillis));
        }
    }

    public static final PaddingValues getCalendarMonthSubheadPadding() {
        return CalendarMonthSubheadPadding;
    }

    /* renamed from: drawRangeBackground-mxwnekA, reason: not valid java name */
    public static final void m2051drawRangeBackgroundmxwnekA(ContentDrawScope $this$drawRangeBackground_u2dmxwnekA, SelectedRangeInfo selectedRangeInfo, long color) {
        float m3991getWidthimpl;
        ContentDrawScope contentDrawScope = $this$drawRangeBackground_u2dmxwnekA;
        float itemContainerWidth = contentDrawScope.mo364toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float itemContainerHeight = contentDrawScope.mo364toPx0680j_4(DatePickerKt.getRecommendedSizeForAccessibility());
        float itemStateLayerHeight = contentDrawScope.mo364toPx0680j_4(DatePickerModalTokens.INSTANCE.m3147getDateStateLayerHeightD9Ej5fM());
        float stateLayerVerticalPadding = (itemContainerHeight - itemStateLayerHeight) / 2.0f;
        float horizontalSpaceBetweenItems = (Size.m3991getWidthimpl(contentDrawScope.mo4713getSizeNHjbRc()) - (7.0f * itemContainerWidth)) / 7.0f;
        long gridStartCoordinates = selectedRangeInfo.getGridStartCoordinates();
        int m6754getXimpl = IntOffset.m6754getXimpl(gridStartCoordinates);
        int y1 = IntOffset.m6755getYimpl(gridStartCoordinates);
        long gridEndCoordinates = selectedRangeInfo.getGridEndCoordinates();
        int m6754getXimpl2 = IntOffset.m6754getXimpl(gridEndCoordinates);
        int y2 = IntOffset.m6755getYimpl(gridEndCoordinates);
        float startX = (m6754getXimpl * (itemContainerWidth + horizontalSpaceBetweenItems)) + (selectedRangeInfo.getFirstIsSelectionStart() ? itemContainerWidth / 2.0f : 0.0f) + (horizontalSpaceBetweenItems / 2.0f);
        float startY = (y1 * itemContainerHeight) + stateLayerVerticalPadding;
        float endX = (m6754getXimpl2 * (itemContainerWidth + horizontalSpaceBetweenItems)) + (selectedRangeInfo.getLastIsSelectionEnd() ? itemContainerWidth / 2.0f : itemContainerWidth) + (horizontalSpaceBetweenItems / 2.0f);
        float endY = (y2 * itemContainerHeight) + stateLayerVerticalPadding;
        boolean isRtl = contentDrawScope.getLayoutDirection() == LayoutDirection.Rtl;
        if (isRtl) {
            startX = Size.m3991getWidthimpl(contentDrawScope.mo4713getSizeNHjbRc()) - startX;
            endX = Size.m3991getWidthimpl(contentDrawScope.mo4713getSizeNHjbRc()) - endX;
        }
        ContentDrawScope contentDrawScope2 = contentDrawScope;
        long Offset = OffsetKt.Offset(startX, startY);
        if (y1 == y2) {
            m3991getWidthimpl = endX - startX;
        } else {
            m3991getWidthimpl = isRtl ? -startX : Size.m3991getWidthimpl(contentDrawScope.mo4713getSizeNHjbRc()) - startX;
        }
        DrawScope.m4707drawRectnJ9OG0$default(contentDrawScope2, color, Offset, SizeKt.Size(m3991getWidthimpl, itemStateLayerHeight), 0.0f, null, null, 0, 120, null);
        if (y1 != y2) {
            int y = (y2 - y1) - 1;
            while (y > 0) {
                DrawScope.m4707drawRectnJ9OG0$default(contentDrawScope, color, OffsetKt.Offset(0.0f, (y * itemContainerHeight) + startY), SizeKt.Size(Size.m3991getWidthimpl($this$drawRangeBackground_u2dmxwnekA.mo4713getSizeNHjbRc()), itemStateLayerHeight), 0.0f, null, null, 0, 120, null);
                y--;
                contentDrawScope = $this$drawRangeBackground_u2dmxwnekA;
                itemContainerWidth = itemContainerWidth;
            }
            float topLeftX = $this$drawRangeBackground_u2dmxwnekA.getLayoutDirection() == LayoutDirection.Ltr ? 0.0f : Size.m3991getWidthimpl($this$drawRangeBackground_u2dmxwnekA.mo4713getSizeNHjbRc());
            DrawScope.m4707drawRectnJ9OG0$default($this$drawRangeBackground_u2dmxwnekA, color, OffsetKt.Offset(topLeftX, endY), SizeKt.Size(isRtl ? endX - Size.m3991getWidthimpl($this$drawRangeBackground_u2dmxwnekA.mo4713getSizeNHjbRc()) : endX, itemStateLayerHeight), 0.0f, null, null, 0, 120, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomAccessibilityAction> customScrollActions(final LazyListState state, final CoroutineScope coroutineScope, String scrollUpLabel, String scrollDownLabel) {
        Function0 scrollUpAction = new Function0<Boolean>() { // from class: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollUpAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z;
                if (LazyListState.this.getCanScrollBackward()) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(LazyListState.this, null), 3, null);
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }

            /* compiled from: DateRangePicker.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollUpAction$1$1", f = "DateRangePicker.kt", i = {}, l = {1032}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollUpAction$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LazyListState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$state = lazyListState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (LazyListState.scrollToItem$default(this.$state, this.$state.getFirstVisibleItemIndex() - 1, 0, this, 2, null) != coroutine_suspended) {
                                break;
                            } else {
                                return coroutine_suspended;
                            }
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }
        };
        Function0 scrollDownAction = new Function0<Boolean>() { // from class: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollDownAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z;
                if (LazyListState.this.getCanScrollForward()) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(LazyListState.this, null), 3, null);
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }

            /* compiled from: DateRangePicker.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollDownAction$1$1", f = "DateRangePicker.kt", i = {}, l = {1040}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.material3.DateRangePickerKt$customScrollActions$scrollDownAction$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LazyListState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LazyListState lazyListState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$state = lazyListState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (LazyListState.scrollToItem$default(this.$state, this.$state.getFirstVisibleItemIndex() + 1, 0, this, 2, null) != coroutine_suspended) {
                                break;
                            } else {
                                return coroutine_suspended;
                            }
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }
        };
        return CollectionsKt.listOf((Object[]) new CustomAccessibilityAction[]{new CustomAccessibilityAction(scrollUpLabel, scrollUpAction), new CustomAccessibilityAction(scrollDownLabel, scrollDownAction)});
    }
}
