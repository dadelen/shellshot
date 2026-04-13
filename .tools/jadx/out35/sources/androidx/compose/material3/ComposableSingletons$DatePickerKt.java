package androidx.compose.material3;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeftKt;
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRightKt;
import androidx.compose.material.icons.filled.DateRangeKt;
import androidx.compose.material.icons.filled.EditKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: DatePicker.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ComposableSingletons$DatePickerKt {
    public static final ComposableSingletons$DatePickerKt INSTANCE = new ComposableSingletons$DatePickerKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f44lambda1 = ComposableLambdaKt.composableLambdaInstance(1244569435, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$DatePickerKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1360@63206L46,1358@63114L152:DatePicker.kt#uh7d8r");
            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1244569435, $changed, -1, "androidx.compose.material3.ComposableSingletons$DatePickerKt.lambda-1.<anonymous> (DatePicker.kt:1358)");
                }
                ImageVector edit = EditKt.getEdit(Icons.Filled.INSTANCE);
                Strings.Companion companion = Strings.INSTANCE;
                IconKt.m2154Iconww6aTOc(edit, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_picker_switch_to_input_mode), $composer, 0), (Modifier) null, 0L, $composer, 0, 12);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f45lambda2 = ComposableLambdaKt.composableLambdaInstance(668820324, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$DatePickerKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1367@63496L49,1365@63399L160:DatePicker.kt#uh7d8r");
            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(668820324, $changed, -1, "androidx.compose.material3.ComposableSingletons$DatePickerKt.lambda-2.<anonymous> (DatePicker.kt:1365)");
                }
                ImageVector dateRange = DateRangeKt.getDateRange(Icons.Filled.INSTANCE);
                Strings.Companion companion = Strings.INSTANCE;
                IconKt.m2154Iconww6aTOc(dateRange, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_picker_switch_to_calendar_mode), $composer, 0), (Modifier) null, 0L, $composer, 0, 12);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f46lambda3 = ComposableLambdaKt.composableLambdaInstance(1233169686, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$DatePickerKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2165@97204L50,2163@97076L204:DatePicker.kt#uh7d8r");
            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1233169686, $changed, -1, "androidx.compose.material3.ComposableSingletons$DatePickerKt.lambda-3.<anonymous> (DatePicker.kt:2163)");
                }
                ImageVector keyboardArrowLeft = KeyboardArrowLeftKt.getKeyboardArrowLeft(Icons.AutoMirrored.Filled.INSTANCE);
                Strings.Companion companion = Strings.INSTANCE;
                IconKt.m2154Iconww6aTOc(keyboardArrowLeft, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_picker_switch_to_previous_month), $composer, 0), (Modifier) null, 0L, $composer, 0, 12);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f47lambda4 = ComposableLambdaKt.composableLambdaInstance(412350847, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$DatePickerKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2171@97539L46,2169@97410L201:DatePicker.kt#uh7d8r");
            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(412350847, $changed, -1, "androidx.compose.material3.ComposableSingletons$DatePickerKt.lambda-4.<anonymous> (DatePicker.kt:2169)");
                }
                ImageVector keyboardArrowRight = KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE);
                Strings.Companion companion = Strings.INSTANCE;
                IconKt.m2154Iconww6aTOc(keyboardArrowRight, Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_date_picker_switch_to_next_month), $composer, 0), (Modifier) null, 0L, $composer, 0, 12);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: getLambda-1$material3_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1965getLambda1$material3_release() {
        return f44lambda1;
    }

    /* renamed from: getLambda-2$material3_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1966getLambda2$material3_release() {
        return f45lambda2;
    }

    /* renamed from: getLambda-3$material3_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1967getLambda3$material3_release() {
        return f46lambda3;
    }

    /* renamed from: getLambda-4$material3_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1968getLambda4$material3_release() {
        return f47lambda4;
    }
}
