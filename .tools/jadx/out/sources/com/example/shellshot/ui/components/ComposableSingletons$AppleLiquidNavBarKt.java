package com.example.shellshot.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: AppleLiquidNavBar.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ComposableSingletons$AppleLiquidNavBarKt {
    public static final ComposableSingletons$AppleLiquidNavBarKt INSTANCE = new ComposableSingletons$AppleLiquidNavBarKt();
    private static Function2<Composer, Integer, Unit> lambda$1970376422 = ComposableLambdaKt.composableLambdaInstance(1970376422, false, new Function2() { // from class: com.example.shellshot.ui.components.ComposableSingletons$AppleLiquidNavBarKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AppleLiquidNavBarKt.lambda_1970376422$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$1970376422$app_debug() {
        return lambda$1970376422;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0214  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit lambda_1970376422$lambda$6(androidx.compose.runtime.Composer r35, int r36) {
        /*
            Method dump skipped, instructions count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ComposableSingletons$AppleLiquidNavBarKt.lambda_1970376422$lambda$6(androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final int lambda_1970376422$lambda$6$lambda$1(MutableIntState $selectedIndex$delegate) {
        return $selectedIndex$delegate.getIntValue();
    }

    static final Unit lambda_1970376422$lambda$6$lambda$5$lambda$4$lambda$3(MutableIntState $selectedIndex$delegate, int it) {
        $selectedIndex$delegate.setIntValue(it);
        return Unit.INSTANCE;
    }
}
