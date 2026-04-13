package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* compiled from: BackdropScaffold.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ComposableSingletons$BackdropScaffoldKt {
    public static final ComposableSingletons$BackdropScaffoldKt INSTANCE = new ComposableSingletons$BackdropScaffoldKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<SnackbarHostState, Composer, Integer, Unit> f7lambda1 = ComposableLambdaKt.composableLambdaInstance(-1054097158, false, new Function3<SnackbarHostState, Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$BackdropScaffoldKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SnackbarHostState snackbarHostState, Composer composer, Integer num) {
            invoke(snackbarHostState, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(SnackbarHostState it, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C373@16061L16:BackdropScaffold.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer.changed(it) ? 4 : 2;
            }
            if (($dirty & 19) == 18 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1054097158, $dirty, -1, "androidx.compose.material.ComposableSingletons$BackdropScaffoldKt.lambda-1.<anonymous> (BackdropScaffold.kt:373)");
            }
            SnackbarHostKt.SnackbarHost(it, null, null, $composer, $dirty & 14, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$material_release, reason: not valid java name */
    public final Function3<SnackbarHostState, Composer, Integer, Unit> m1504getLambda1$material_release() {
        return f7lambda1;
    }
}
