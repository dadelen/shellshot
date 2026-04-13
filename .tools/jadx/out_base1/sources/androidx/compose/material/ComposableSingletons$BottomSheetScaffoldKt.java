package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: BottomSheetScaffold.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ComposableSingletons$BottomSheetScaffoldKt {
    public static final ComposableSingletons$BottomSheetScaffoldKt INSTANCE = new ComposableSingletons$BottomSheetScaffoldKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<SnackbarHostState, Composer, Integer, Unit> f8lambda1 = ComposableLambdaKt.composableLambdaInstance(239945703, false, new Function3<SnackbarHostState, Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$BottomSheetScaffoldKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SnackbarHostState snackbarHostState, Composer composer, Integer num) {
            invoke(snackbarHostState, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(SnackbarHostState it, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C336@13478L16:BottomSheetScaffold.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer.changed(it) ? 4 : 2;
            }
            if (($dirty & 19) == 18 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(239945703, $dirty, -1, "androidx.compose.material.ComposableSingletons$BottomSheetScaffoldKt.lambda-1.<anonymous> (BottomSheetScaffold.kt:336)");
            }
            SnackbarHostKt.SnackbarHost(it, null, null, $composer, $dirty & 14, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f9lambda2 = ComposableLambdaKt.composableLambdaInstance(690018774, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$BottomSheetScaffoldKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C:BottomSheetScaffold.kt#jmzs0o");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(690018774, $changed, -1, "androidx.compose.material.ComposableSingletons$BottomSheetScaffoldKt.lambda-2.<anonymous> (BottomSheetScaffold.kt:504)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f10lambda3 = ComposableLambdaKt.composableLambdaInstance(1054313561, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$BottomSheetScaffoldKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C:BottomSheetScaffold.kt#jmzs0o");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1054313561, $changed, -1, "androidx.compose.material.ComposableSingletons$BottomSheetScaffoldKt.lambda-3.<anonymous> (BottomSheetScaffold.kt:507)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$material_release, reason: not valid java name */
    public final Function3<SnackbarHostState, Composer, Integer, Unit> m1505getLambda1$material_release() {
        return f8lambda1;
    }

    /* renamed from: getLambda-2$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1506getLambda2$material_release() {
        return f9lambda2;
    }

    /* renamed from: getLambda-3$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1507getLambda3$material_release() {
        return f10lambda3;
    }
}
