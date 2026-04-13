package com.example.shellshot.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: AppComponents.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ComposableSingletons$AppComponentsKt {
    public static final ComposableSingletons$AppComponentsKt INSTANCE = new ComposableSingletons$AppComponentsKt();
    private static Function2<Composer, Integer, Unit> lambda$1370548465 = ComposableLambdaKt.composableLambdaInstance(1370548465, false, new Function2() { // from class: com.example.shellshot.ui.components.ComposableSingletons$AppComponentsKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AppComponentsKt.lambda_1370548465$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$1370548465$app_debug() {
        return lambda$1370548465;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit lambda_1370548465$lambda$6(androidx.compose.runtime.Composer r32, int r33) {
        /*
            Method dump skipped, instructions count: 618
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.components.ComposableSingletons$AppComponentsKt.lambda_1370548465$lambda$6(androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final int lambda_1370548465$lambda$6$lambda$1(MutableIntState $selectedIndex$delegate) {
        return $selectedIndex$delegate.getIntValue();
    }

    static final Unit lambda_1370548465$lambda$6$lambda$5$lambda$4$lambda$3(MutableIntState $selectedIndex$delegate, int it) {
        $selectedIndex$delegate.setIntValue(it);
        return Unit.INSTANCE;
    }
}
