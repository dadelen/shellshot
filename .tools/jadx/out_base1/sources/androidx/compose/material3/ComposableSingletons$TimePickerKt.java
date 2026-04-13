package androidx.compose.material3;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: TimePicker.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ComposableSingletons$TimePickerKt {
    public static final ComposableSingletons$TimePickerKt INSTANCE = new ComposableSingletons$TimePickerKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f73lambda1 = ComposableLambdaKt.composableLambdaInstance(1425358052, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$TimePickerKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope $this$ToggleItem, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1277@52655L40,1277@52643L53:TimePicker.kt#uh7d8r");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1425358052, $changed, -1, "androidx.compose.material3.ComposableSingletons$TimePickerKt.lambda-1.<anonymous> (TimePicker.kt:1277)");
                }
                Strings.Companion companion = Strings.INSTANCE;
                TextKt.m2697Text4IGK_g(Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_time_picker_am), $composer, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 131070);
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
    public static Function3<RowScope, Composer, Integer, Unit> f74lambda2 = ComposableLambdaKt.composableLambdaInstance(-1179219109, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ComposableSingletons$TimePickerKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope $this$ToggleItem, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1291@53172L40,1291@53167L46:TimePicker.kt#uh7d8r");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1179219109, $changed, -1, "androidx.compose.material3.ComposableSingletons$TimePickerKt.lambda-2.<anonymous> (TimePicker.kt:1291)");
                }
                Strings.Companion companion = Strings.INSTANCE;
                TextKt.m2697Text4IGK_g(Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(R.string.m3c_time_picker_pm), $composer, 0), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 0, 0, 131070);
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
    public final Function3<RowScope, Composer, Integer, Unit> m1994getLambda1$material3_release() {
        return f73lambda1;
    }

    /* renamed from: getLambda-2$material3_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m1995getLambda2$material3_release() {
        return f74lambda2;
    }
}
