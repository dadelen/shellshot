package androidx.core.content;

import androidx.core.util.Predicate;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* compiled from: D8$$SyntheticClass */
@LambdaMethod(holder = "Ljava/lang/String;", method = "equals", proto = "(Ljava/lang/Object;)Z")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
/* loaded from: classes13.dex */
public final /* synthetic */ class IntentSanitizer$Builder$$ExternalSyntheticLambda10 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ IntentSanitizer$Builder$$ExternalSyntheticLambda10(String str) {
        this.f$0 = str;
    }

    @Override // androidx.core.util.Predicate
    public final boolean test(Object obj) {
        boolean equals;
        equals = this.f$0.equals((String) obj);
        return equals;
    }
}
