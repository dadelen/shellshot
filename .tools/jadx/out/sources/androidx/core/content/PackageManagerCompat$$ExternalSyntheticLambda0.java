package androidx.core.content;

import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* compiled from: D8$$SyntheticClass */
@LambdaMethod(holder = "Landroidx/core/content/UnusedAppRestrictionsBackportServiceConnection;", method = "disconnectFromService", proto = "()V")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
/* loaded from: classes13.dex */
public final /* synthetic */ class PackageManagerCompat$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ UnusedAppRestrictionsBackportServiceConnection f$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.disconnectFromService();
    }
}
