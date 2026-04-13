package androidx.core.view;

import android.view.ViewConfiguration;
import androidx.core.util.Supplier;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* compiled from: D8$$SyntheticClass */
@LambdaMethod(holder = "Landroid/view/ViewConfiguration;", method = "getScaledMaximumFlingVelocity", proto = "()I")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
/* loaded from: classes13.dex */
public final /* synthetic */ class ViewConfigurationCompat$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ ViewConfiguration f$0;

    @Override // androidx.core.util.Supplier
    public final Object get() {
        int scaledMaximumFlingVelocity;
        scaledMaximumFlingVelocity = this.f$0.getScaledMaximumFlingVelocity();
        return Integer.valueOf(scaledMaximumFlingVelocity);
    }
}
